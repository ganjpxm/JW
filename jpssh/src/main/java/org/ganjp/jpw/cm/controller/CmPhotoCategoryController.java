/**
 * $Id: CmPhotoCategoryController.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.ganjp.jpw.bm.model.BmMenu;
import org.ganjp.jpw.cm.model.CmCategory;
import org.ganjp.jpw.cm.model.CmPhoto;
import org.ganjp.jpw.cm.model.CmPhotoCategory;
import org.ganjp.jpw.cm.service.CmCategoryManager;
import org.ganjp.jpw.cm.service.CmPhotoCategoryManager;
import org.ganjp.jpw.core.Const;
import org.ganjp.jpw.core.util.FileUtil;
import org.ganjp.jpw.core.util.StringUtil;
import org.ganjp.jpw.core.web.controller.BaseController;
import org.ganjp.jpw.core.web.util.ServletUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <p>CmPhotoCategoryController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
public class CmPhotoCategoryController extends BaseController {
	@RequestMapping(value = "/cmPhotoCategory", method = RequestMethod.GET)
	public String toCmPhotoCategoryJqmList(HttpServletRequest request) {
		super.setLeftMenu(request, BmMenu.MENU_TYPE_MANAGEMENT_CONSOLE);
		String categoryId = request.getParameter("categoryId");
		String isPhoto = "no";
		if (StringUtil.isEmpty(categoryId)) {
			categoryId = cmCategoryManager.getCategoryId(CmCategory.CATEGORY_CD_PHOTO_CATEGORY, ServletUtil.getLanguage(request));
		}
		List<Map<String,String>> categoryMaps = cmCategoryManager.getCategoryMaps(categoryId,super.getLoginUserRoleIds(request));
		String categoryBaseUrl = super.getBasePath(request) + "/cmPhotoCategory?menuId=" + request.getParameter("menuId")
				+ "&categoryId=";
		if (categoryMaps==null) {
			categoryMaps = cmPhotoCategoryManager.getPhotoMaps(categoryId, super.getLoginUserRoleIds(request));
			Map<String,String> categoryPidNameMap = cmCategoryManager.getCategoryPidsBtnsMap(categoryId,categoryBaseUrl);
			request.setAttribute("categoryIds", categoryPidNameMap.get("categoryIds"));
			request.setAttribute("categoryBtns", categoryPidNameMap.get("categoryBtns"));
			isPhoto = "yes";
			request.setAttribute("photoMaps", categoryMaps);
		} else {
			String categoryPid = cmCategoryManager.getCmCategoryById(categoryId).getCategoryPid();
			Map<String,String> categoryPidNameMap = cmCategoryManager.getCategoryPidsBtnsMap(categoryPid,categoryBaseUrl);
			request.setAttribute("categoryIds", categoryPidNameMap.get("categoryIds"));
			request.setAttribute("categoryBtns", categoryPidNameMap.get("categoryBtns"));
			request.setAttribute("categoryMaps", categoryMaps);
		}
		request.setAttribute("isPhoto", isPhoto);
		request.setAttribute("categoryId", categoryId);
        return "cm/cmPhotoCategory/cmPhotoCategoryJqmList";
	}
	
	@RequestMapping(value = "/cmPhotoCategoryJqmAdd", method = RequestMethod.GET)
	public String toCmPhotoCategoryJqmAdd(HttpServletRequest request) {
        return "cm/cmPhotoCategory/cmPhotoCategoryJqmAdd";
	}
	
	@RequestMapping(value = "/cmPhotoCategoryJqmEdit/{photoCategoryId}", method = RequestMethod.GET)
	public String toCmPhotoCategoryJqmEdit(@PathVariable String photoCategoryId, HttpServletRequest request) {
		CmPhotoCategory cmPhotoCategory = cmPhotoCategoryManager.getCmPhotoCategoryById(photoCategoryId);
		request.setAttribute("cmPhotoCategory",cmPhotoCategory);
        return "cm/cmPhotoCategory/cmPhotoCategoryJqmEdit";
	}
	
	@RequestMapping(value = "/saveCmPhotoCategory", method = RequestMethod.POST)
	public String saveCmPhotoCategory(HttpServletRequest request, RedirectAttributes ra) {
		CmPhotoCategory cmPhotoCategory = new CmPhotoCategory();
		setValue(request, cmPhotoCategory);
		cmPhotoCategoryManager.save(cmPhotoCategory);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmPhotoCategory";
	}
	
	@RequestMapping(value = "/updateCmPhotoCategory", method = RequestMethod.POST)
	public String updateCmPhotoCategory(HttpServletRequest request, RedirectAttributes ra) {
		String photoCategoryId = request.getParameter("photoCategoryId");
		CmPhotoCategory cmPhotoCategory = cmPhotoCategoryManager.getCmPhotoCategoryById(photoCategoryId);
		setValue(request, cmPhotoCategory);
		cmPhotoCategoryManager.update(cmPhotoCategory);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmPhotoCategory"; 
	}
	
	@RequestMapping(value = "/deleteCmPhotoCategory/{photoCategoryId}", method = RequestMethod.GET)
	public String deleteCmPhotoCategory(@PathVariable String photoCategoryId, HttpServletRequest request, RedirectAttributes ra) {
		cmPhotoCategoryManager.delete(photoCategoryId);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmPhotoCategory"; 
	}

	@RequestMapping(value = "/deleteCmPhotoCategorys", method = RequestMethod.POST)
	public String deleteCmPhotoCategorys(HttpServletRequest request, RedirectAttributes ra) {
		cmPhotoCategoryManager.batchDelete(request.getParameter("photoCategoryIds"));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmPhotoCategory"; 
	}
	
	@RequestMapping(value="/uploadPhoto", method=RequestMethod.POST)
	public @ResponseBody Map<String,String> uploadPhoto(@RequestParam("file") MultipartFile file, 
			HttpServletRequest request, RedirectAttributes ra) {
		String saveFullPath = FileUtil.getPath(request.getRealPath(""), "resources", "upload", "photo", file.getOriginalFilename());
		CmPhoto cmPhoto = new CmPhoto();
		cmPhoto.setLang(ServletUtil.getLanguage(request));
		Map<String,String> map = new HashMap<String,String>();
		map.put("result", "success");
		if (file.getSize() > 1000000) {
			map.put("result", file.getOriginalFilename() + " size is more than 1M");
		} else {
			try {
				if (new File(saveFullPath).exists()) {
					map.put("result", file.getOriginalFilename() + " is exist");
					return map;
				}
				FileUtil.copy(file.getInputStream(), new File(saveFullPath));
				cmPhoto.setUrl("/resources/upload/photo/" + file.getOriginalFilename());
				cmPhoto.setPhotoName(file.getOriginalFilename());
				cmPhotoCategoryManager.saveCmPhotoAndCategory(cmPhoto, request.getParameter("categoryIds"));
			} catch (Exception e) {
				log.error(e.getMessage());
				map.put("result", file.getOriginalFilename() + " upload fail");
				return map;
			}
		}
		return map;
	}
	@RequestMapping(value = "/yjl", method = RequestMethod.GET)
	public String toYjlPhoto(HttpServletRequest request, RedirectAttributes ra) {
		ra.addAttribute("showType", CmPhoto.SHOW_TYPE_MERGING_IMAGE_BOXES);
		List<Map<String,String>> photoMaps = cmPhotoCategoryManager.getPhotoMaps(CmCategory.CATEGORY_CD_YANGJL_PHOTO,
				Const.LANGUAGE_ZH_CN, CmPhoto.SHOW_TYPE_MERGING_IMAGE_BOXES, super.getLoginUserRoleIds(request));
		request.setAttribute("photoMaps", photoMaps);
		return "cm/cmPhotoCategory/cmYjlPhoto";
	}

	@RequestMapping(value = "/photo/{categoryCd}/{tag}", method = RequestMethod.GET)
	public String toCmPhotoCategory(@PathVariable String categoryCd, @PathVariable String tag,  
			HttpServletRequest request) {
		List<Map<String,String>> photoMaps = cmPhotoCategoryManager.getPhotoMaps(categoryCd,
				ServletUtil.getLanguage(request), tag, super.getLoginUserRoleIds(request));
		request.setAttribute("photoMaps", photoMaps);
		String showType = request.getParameter("showType");
		if (CmPhoto.SHOW_TYPE_MERGING_IMAGE_BOXES.equals(showType)) {
			return "cm/cmPhotoCategory/cmPhotoMergingImageBoxes";
		} else {
			return "cm/cmPhotoCategory/cmPhotoCategory";
		}
		
	}	
	@Resource(name="cmPhotoCategoryManagerImpl")
	private CmPhotoCategoryManager cmPhotoCategoryManager;
	@Resource(name="cmCategoryManagerImpl")
	private CmCategoryManager cmCategoryManager;
}