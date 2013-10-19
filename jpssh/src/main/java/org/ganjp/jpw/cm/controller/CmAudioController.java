/**
 * $Id: CmAudioController.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
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
import org.ganjp.jpw.cm.model.CmFile;
import org.ganjp.jpw.cm.model.CmAudio;
import org.ganjp.jpw.cm.service.CmCategoryManager;
import org.ganjp.jpw.cm.service.CmFileManager;
import org.ganjp.jpw.cm.service.CmAudioManager;
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
 * <p>CmAudioController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
public class CmAudioController extends BaseController {
	@RequestMapping(value = "/cmAudio", method = RequestMethod.GET)
	public String toCmAudioJqmList(HttpServletRequest request) {
		List<CmAudio> cmAudios = cmAudioManager.getCmAudios();
		request.setAttribute("cmAudios", cmAudios);
		super.setLeftMenu(request, BmMenu.MENU_TYPE_MANAGEMENT_CONSOLE);
        return "cm/cmAudio/cmAudioJqmList";
	}
	
	@RequestMapping(value = "/cmAudioJqmAdd", method = RequestMethod.GET)
	public String toCmAudioJqmAdd(HttpServletRequest request) {
		request.setAttribute("roleMaps", super.getRoleMapsForJqmCheck(request));
		request.setAttribute("categoryMaps", cmCategoryManager.getCategorysForJqmCheck(CmCategory.CATEGORY_CD_AUDIO_CATEGORY, ServletUtil.getLanguage(request)));
		request.setAttribute("from", request.getParameter("from"));
		request.setAttribute("edit", request.getParameter("edit"));
		request.setAttribute("tag", request.getParameter("tag"));
		request.setAttribute("pageSize", request.getParameter("pageSize"));
		request.setAttribute("pageNo", request.getParameter("pageNo"));
		return "cm/cmAudio/cmAudioJqmAdd";
	}
	
	@RequestMapping(value = "/cmAudioJqmEdit/{audioId}", method = RequestMethod.GET)
	public String toCmAudioJqmEdit(@PathVariable String audioId, HttpServletRequest request) {
		CmAudio cmAudio = cmAudioManager.getCmAudioById(audioId);
		request.setAttribute("cmAudio",cmAudio);
		request.setAttribute("roleMaps", super.getRoleMapsForJqmCheck(request));
		request.setAttribute("categoryMaps", cmCategoryManager.getCategorysForJqmCheck(CmCategory.CATEGORY_CD_AUDIO_CATEGORY, ServletUtil.getLanguage(request)));
		request.setAttribute("from", request.getParameter("from"));
		request.setAttribute("edit", request.getParameter("edit"));
		request.setAttribute("tag", request.getParameter("tag"));
		request.setAttribute("pageSize", request.getParameter("pageSize"));
		request.setAttribute("pageNo", request.getParameter("pageNo"));
        return "cm/cmAudio/cmAudioJqmEdit";
	}
	
	@RequestMapping(value = "/saveCmAudio", method = RequestMethod.POST)
	public String saveCmAudio(HttpServletRequest request, RedirectAttributes ra) {
		CmAudio cmAudio = new CmAudio();
		setValue(request, cmAudio);
		cmAudioManager.save(cmAudio);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		String from = request.getParameter("from");
		if (StringUtil.isEmpty(from)) {
			return "redirect:/cmAudio";
		} else {
			ra.addAttribute("edit", request.getParameter("edit"));
			ra.addAttribute("tag", request.getParameter("tag"));
			ra.addAttribute("pageNo", request.getParameter("pageNo"));
			ra.addAttribute("pageSize", request.getParameter("pageSize"));
			return "redirect:" + from;
		}
	}
	
	@RequestMapping(value = "/updateCmAudio", method = RequestMethod.POST)
	public String updateCmAudio(HttpServletRequest request, RedirectAttributes ra) {
		String audioId = request.getParameter("audioId");
		CmAudio cmAudio = cmAudioManager.getCmAudioById(audioId);
		setValue(request, cmAudio);
		cmAudioManager.update(cmAudio);
		
		String from = request.getParameter("from");
		if (StringUtil.isEmpty(from)) {
			ra.addAttribute("menuId", request.getParameter("menuId"));
			String categoryId = request.getParameter("categoryId");
			if (StringUtil.isNotEmpty(categoryId)) {
				ra.addAttribute("categoryId", categoryId);		
				return "redirect:/cmAudioCategory";		
			} else {
				return "redirect:/cmAudio";
			}
		} else {
			ra.addAttribute("edit", request.getParameter("edit"));
			ra.addAttribute("tag", request.getParameter("searchTag"));
			ra.addAttribute("pageNo", request.getParameter("pageNo"));
			ra.addAttribute("pageSize", request.getParameter("pageSize"));
			return "redirect:" + from;
		}
	}
	
	@RequestMapping(value = "/deleteCmAudio/{audioId}", method = RequestMethod.GET)
	public String deleteCmAudio(@PathVariable String audioId, HttpServletRequest request, RedirectAttributes ra) {
		cmAudioManager.delete(audioId);
		String audioName = request.getParameter("audioName");
		if (StringUtil.isNotEmpty(audioName)) {
			FileUtil.delete(new File(FileUtil.getPath(request.getRealPath(""), "resources", "upload", "audio", audioName)));
		}
		
		String from = request.getParameter("from");
		if (StringUtil.isEmpty(from)) {
			ra.addAttribute("menuId", request.getParameter("menuId"));
			return "redirect:/cmAudio";
		} else {
			ra.addAttribute("edit", request.getParameter("edit"));
			ra.addAttribute("tag", request.getParameter("tag"));
			ra.addAttribute("pageNo", request.getParameter("pageNo"));
			ra.addAttribute("pageSize", request.getParameter("pageSize"));			
			return "redirect:" + from;
		}
	}

	@RequestMapping(value = "/deleteCmAudios", method = RequestMethod.POST)
	public String deleteCmAudios(HttpServletRequest request, RedirectAttributes ra) {
		cmAudioManager.batchDelete(request.getParameter("audioIds"));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmAudio"; 
	}
	
	@Resource(name="cmAudioManagerImpl")
	private CmAudioManager cmAudioManager;
	
	@Resource(name="cmCategoryManagerImpl")
	private CmCategoryManager cmCategoryManager;
}