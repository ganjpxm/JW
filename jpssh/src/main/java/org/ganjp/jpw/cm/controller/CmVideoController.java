/**
 * $Id: CmVideoController.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.controller;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.ganjp.jpw.bm.model.BmMenu;
import org.ganjp.jpw.cm.model.CmCategory;
import org.ganjp.jpw.cm.model.CmVideo;
import org.ganjp.jpw.cm.service.CmCategoryManager;
import org.ganjp.jpw.cm.service.CmVideoManager;
import org.ganjp.jpw.core.util.FileUtil;
import org.ganjp.jpw.core.util.StringUtil;
import org.ganjp.jpw.core.web.controller.BaseController;
import org.ganjp.jpw.core.web.util.ServletUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <p>CmVideoController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
public class CmVideoController extends BaseController {
	@RequestMapping(value = "/cmVideo", method = RequestMethod.GET)
	public String toCmVideoJqmList(HttpServletRequest request) {
		List<CmVideo> cmVideos = cmVideoManager.getCmVideos();
		request.setAttribute("cmVideos", cmVideos);
		super.setLeftMenu(request, BmMenu.MENU_TYPE_MANAGEMENT_CONSOLE);
        return "cm/cmVideo/cmVideoJqmList";
	}
	
	@RequestMapping(value = "/cmVideoJqmAdd", method = RequestMethod.GET)
	public String toCmVideoJqmAdd(HttpServletRequest request) {
		request.setAttribute("roleMaps", super.getRoleMapsForJqmCheck(request));
		request.setAttribute("categoryMaps", cmCategoryManager.getCategorysForJqmCheck(CmCategory.CATEGORY_CD_VIDEO_CATEGORY, ServletUtil.getLanguage(request)));
		request.setAttribute("from", request.getParameter("from"));
		request.setAttribute("edit", request.getParameter("edit"));
		request.setAttribute("tag", request.getParameter("tag"));
		request.setAttribute("pageSize", request.getParameter("pageSize"));
		request.setAttribute("pageNo", request.getParameter("pageNo"));
		return "cm/cmVideo/cmVideoJqmAdd";
	}
	
	@RequestMapping(value = "/cmVideoJqmEdit/{videoId}", method = RequestMethod.GET)
	public String toCmVideoJqmEdit(@PathVariable String videoId, HttpServletRequest request) {
		CmVideo cmVideo = cmVideoManager.getCmVideoById(videoId);
		request.setAttribute("cmVideo",cmVideo);
		request.setAttribute("roleMaps", super.getRoleMapsForJqmCheck(request));
		request.setAttribute("categoryMaps", cmCategoryManager.getCategorysForJqmCheck(CmCategory.CATEGORY_CD_VIDEO_CATEGORY, ServletUtil.getLanguage(request)));
		request.setAttribute("from", request.getParameter("from"));
		request.setAttribute("edit", request.getParameter("edit"));
		request.setAttribute("tag", request.getParameter("tag"));
		request.setAttribute("pageSize", request.getParameter("pageSize"));
		request.setAttribute("pageNo", request.getParameter("pageNo"));
        return "cm/cmVideo/cmVideoJqmEdit";
	}
	
	@RequestMapping(value = "/saveCmVideo", method = RequestMethod.POST)
	public String saveCmVideo(HttpServletRequest request, RedirectAttributes ra) {
		CmVideo cmVideo = new CmVideo();
		setValue(request, cmVideo);
		cmVideoManager.save(cmVideo);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		String from = request.getParameter("from");
		if (StringUtil.isEmpty(from)) {
			return "redirect:/cmVideo";
		} else {
			ra.addAttribute("edit", request.getParameter("edit"));
			ra.addAttribute("tag", request.getParameter("tag"));
			ra.addAttribute("pageNo", request.getParameter("pageNo"));
			ra.addAttribute("pageSize", request.getParameter("pageSize"));
			return "redirect:" + from;
		}
	}
	
	@RequestMapping(value = "/updateCmVideo", method = RequestMethod.POST)
	public String updateCmVideo(HttpServletRequest request, RedirectAttributes ra) {
		String videoId = request.getParameter("videoId");
		CmVideo cmVideo = cmVideoManager.getCmVideoById(videoId);
		setValue(request, cmVideo);
		cmVideoManager.update(cmVideo);
		
		String from = request.getParameter("from");
		if (StringUtil.isEmpty(from)) {
			ra.addAttribute("menuId", request.getParameter("menuId"));
			String categoryId = request.getParameter("categoryId");
			if (StringUtil.isNotEmpty(categoryId)) {
				ra.addAttribute("categoryId", categoryId);		
				return "redirect:/cmVideoCategory";		
			} else {
				return "redirect:/cmVideo";
			}
		} else {
			ra.addAttribute("edit", request.getParameter("edit"));
			ra.addAttribute("tag", request.getParameter("searchTag"));
			ra.addAttribute("pageNo", request.getParameter("pageNo"));
			ra.addAttribute("pageSize", request.getParameter("pageSize"));
			return "redirect:" + from;
		}
	}
	
	@RequestMapping(value = "/deleteCmVideo/{videoId}", method = RequestMethod.GET)
	public String deleteCmVideo(@PathVariable String videoId, HttpServletRequest request, RedirectAttributes ra) {
		cmVideoManager.delete(videoId);
		String videoName = request.getParameter("videoName");
		if (StringUtil.isNotEmpty(videoName)) {
			FileUtil.delete(new File(FileUtil.getPath(request.getRealPath(""), "resources", "upload", "video", videoName)));
		}
		
		String from = request.getParameter("from");
		if (StringUtil.isEmpty(from)) {
			ra.addAttribute("menuId", request.getParameter("menuId"));
			return "redirect:/cmVideo";
		} else {
			ra.addAttribute("edit", request.getParameter("edit"));
			ra.addAttribute("tag", request.getParameter("tag"));
			ra.addAttribute("pageNo", request.getParameter("pageNo"));
			ra.addAttribute("pageSize", request.getParameter("pageSize"));	
			return "redirect:" + from;
		}
	}

	@RequestMapping(value = "/deleteCmVideos", method = RequestMethod.POST)
	public String deleteCmVideos(HttpServletRequest request, RedirectAttributes ra) {
		cmVideoManager.batchDelete(request.getParameter("videoIds"));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmVideo"; 
	}
	
	@Resource(name="cmVideoManagerImpl")
	private CmVideoManager cmVideoManager;
	
	@Resource(name="cmCategoryManagerImpl")
	private CmCategoryManager cmCategoryManager;
}