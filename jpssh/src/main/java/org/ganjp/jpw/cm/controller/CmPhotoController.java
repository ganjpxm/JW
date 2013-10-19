/**
 * $Id: CmPhotoController.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
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
import org.ganjp.jpw.cm.model.CmPhoto;
import org.ganjp.jpw.cm.service.CmCategoryManager;
import org.ganjp.jpw.cm.service.CmPhotoCategoryManager;
import org.ganjp.jpw.cm.service.CmPhotoManager;
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
 * <p>CmPhotoController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
public class CmPhotoController extends BaseController {
	@RequestMapping(value = "/cmPhoto", method = RequestMethod.GET)
	public String toCmPhotoJqmList(HttpServletRequest request) {
		List<CmPhoto> cmPhotos = cmPhotoManager.getCmPhotos();
		request.setAttribute("cmPhotos", cmPhotos);
		super.setLeftMenu(request, BmMenu.MENU_TYPE_MANAGEMENT_CONSOLE);
        return "cm/cmPhoto/cmPhotoJqmList";
	}
	
	@RequestMapping(value = "/cmPhotoJqmAdd", method = RequestMethod.GET)
	public String toCmPhotoJqmAdd(HttpServletRequest request) {
		request.setAttribute("roleMaps", super.getRoleMapsForJqmCheck(request));
		request.setAttribute("categoryMaps", cmCategoryManager.getCategorysForJqmCheck(CmCategory.CATEGORY_CD_PHOTO_CATEGORY, ServletUtil.getLanguage(request)));
		request.setAttribute("from", request.getParameter("from"));
		request.setAttribute("edit", request.getParameter("edit"));
		request.setAttribute("tag", request.getParameter("tag"));
		request.setAttribute("pageSize", request.getParameter("pageSize"));
		request.setAttribute("pageNo", request.getParameter("pageNo"));
		request.setAttribute("articleId", request.getParameter("articleId"));
		
		return "cm/cmPhoto/cmPhotoJqmAdd";
	}
	
	@RequestMapping(value = "/cmPhotoJqmEdit/{photoId}", method = RequestMethod.GET)
	public String toCmPhotoJqmEdit(@PathVariable String photoId, HttpServletRequest request) {
		CmPhoto cmPhoto = cmPhotoManager.getCmPhotoById(photoId);
		request.setAttribute("cmPhoto",cmPhoto);
		String categoryId = request.getParameter("categoryId");
		if (StringUtil.isNotEmpty(categoryId)) {
			request.setAttribute("categoryId", categoryId);
			request.setAttribute("categoryIds", cmPhotoCategoryManager.getCategoryIds(photoId));
		}
		request.setAttribute("roleMaps", super.getRoleMapsForJqmCheck(request));
		request.setAttribute("categoryMaps", cmCategoryManager.getCategorysForJqmCheck(CmCategory.CATEGORY_CD_PHOTO_CATEGORY, ServletUtil.getLanguage(request)));
		request.setAttribute("from", request.getParameter("from"));
		request.setAttribute("edit", request.getParameter("edit"));
//		request.setAttribute("tag", request.getParameter("tag"));
		request.setAttribute("pageSize", request.getParameter("pageSize"));
		request.setAttribute("pageNo", request.getParameter("pageNo"));
        return "cm/cmPhoto/cmPhotoJqmEdit";
	}
	
	@RequestMapping(value = "/saveCmPhoto", method = RequestMethod.POST)
	public String saveCmPhoto(HttpServletRequest request, RedirectAttributes ra) {
		CmPhoto cmPhoto = new CmPhoto();
		setValue(request, cmPhoto);
		cmPhotoManager.save(cmPhoto);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		String from = request.getParameter("from");
		if (StringUtil.isEmpty(from)) {
			return "redirect:/cmPhoto";
		} else {
			ra.addAttribute("edit", request.getParameter("edit"));
//			ra.addAttribute("tag", request.getParameter("tag"));
			ra.addAttribute("pageNo", request.getParameter("pageNo"));
			ra.addAttribute("pageSize", request.getParameter("pageSize"));
			return "redirect:" + from;
		}
	}
	
	@RequestMapping(value = "/updateCmPhoto", method = RequestMethod.POST)
	public String updateCmPhoto(HttpServletRequest request, RedirectAttributes ra) {
		String photoId = request.getParameter("photoId");
		String displayNo = request.getParameter("displayNo");
		CmPhoto cmPhoto = cmPhotoManager.getCmPhotoById(photoId);
		setValue(request, cmPhoto);
		cmPhotoCategoryManager.updateCmPhotoAndCategory(cmPhoto, request.getParameter("newCategoryIds"));
		
		String from = request.getParameter("from");
		if (StringUtil.isEmpty(from)) {
			ra.addAttribute("menuId", request.getParameter("menuId"));
			String categoryId = request.getParameter("categoryId");
			if (StringUtil.isNotEmpty(categoryId)) {
				ra.addAttribute("categoryId", categoryId);		
				return "redirect:/cmPhotoCategory";		
			} else {
				return "redirect:/cmPhoto";
			}
		} else {
			ra.addAttribute("edit", request.getParameter("edit"));
//			ra.addAttribute("tag", request.getParameter("searchTag"));
			ra.addAttribute("pageNo", request.getParameter("pageNo"));
			ra.addAttribute("pageSize", request.getParameter("pageSize"));
			return "redirect:" + from;
		}
	}
	
	@RequestMapping(value = "/deleteCmPhoto/{photoId}", method = RequestMethod.GET)
	public String deleteCmPhoto(@PathVariable String photoId, HttpServletRequest request, RedirectAttributes ra) {
		cmPhotoCategoryManager.deleteCmPhotoAndCmPhotoCategory(photoId);
		String photoName = request.getParameter("photoName");
		if (StringUtil.isNotEmpty(photoName)) {
			FileUtil.delete(new File(FileUtil.getPath(request.getRealPath(""), "resources", "upload", "photo", photoName)));
		}
		
		String from = request.getParameter("from");
		if (StringUtil.isEmpty(from)) {
			ra.addAttribute("menuId", request.getParameter("menuId"));
			String categoryId = request.getParameter("categoryId");
			if (StringUtil.isNotEmpty(categoryId)) {
				ra.addAttribute("categoryId", categoryId);		
				return "redirect:/cmPhotoCategory";		
			} else {
				return "redirect:/cmPhoto";
			}
		} else {
			ra.addAttribute("edit", request.getParameter("edit"));
			ra.addAttribute("tag", request.getParameter("tag"));
			ra.addAttribute("pageNo", request.getParameter("pageNo"));
			ra.addAttribute("pageSize", request.getParameter("pageSize"));		
			return "redirect:" + from;
		}
	}

	@RequestMapping(value = "/deleteCmPhotos", method = RequestMethod.POST)
	public String deleteCmPhotos(HttpServletRequest request, RedirectAttributes ra) {
		cmPhotoManager.batchDelete(request.getParameter("photoIds"));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmPhoto"; 
	}
	
	@Resource(name="cmPhotoManagerImpl")
	private CmPhotoManager cmPhotoManager;
	
	@Resource(name="cmPhotoCategoryManagerImpl")
	private CmPhotoCategoryManager cmPhotoCategoryManager;

	@Resource(name="cmCategoryManagerImpl")
	private CmCategoryManager cmCategoryManager;
}