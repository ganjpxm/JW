/**
 * $Id: CmArticleCategoryController.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.controller;

import org.ganjp.jpw.cm.model.CmArticleCategory;
import org.ganjp.jpw.cm.model.CmCategory;
import org.ganjp.jpw.cm.service.CmArticleCategoryManager;
import org.ganjp.jpw.cm.service.CmCategoryManager;
import org.ganjp.jpw.core.util.FileUtil;
import org.ganjp.jpw.core.util.StringUtil;
import org.ganjp.jpw.core.web.controller.BaseController;
import org.ganjp.jpw.core.web.util.ServletUtil;
import org.ganjp.jpw.bm.model.BmMenu;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>CmArticleCategoryController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
public class CmArticleCategoryController extends BaseController {
	@RequestMapping(value = "/cmArticleCategory", method = RequestMethod.GET)
	public String toCmArticleCategoryJqmList(HttpServletRequest request) {
		super.setLeftMenu(request, BmMenu.MENU_TYPE_MANAGEMENT_CONSOLE);
		String categoryId = request.getParameter("categoryId");
		String isArticle = "no";
		if (StringUtil.isEmpty(categoryId)) {
			categoryId = cmCategoryManager.getCategoryId(CmCategory.CATEGORY_CD_ARTICLE_CATEGORY, ServletUtil.getLanguage(request));
		}
		List<Map<String,String>> categoryMaps = cmCategoryManager.getCategoryMaps(categoryId, super.getLoginUserRoleIds(request));
		String categoryBaseUrl = super.getBasePath(request) + "/cmArticleCategory?menuId=" + request.getParameter("menuId")
				+ "&categoryId=";
		if (categoryMaps==null) {
			categoryMaps = cmArticleCategoryManager.getArticleMaps(categoryId, super.getLoginUserRoleIds(request));
			Map<String,String> categoryPidNameMap = cmCategoryManager.getCategoryPidsBtnsMap(categoryId,categoryBaseUrl);
			request.setAttribute("categoryIds", categoryPidNameMap.get("categoryIds"));
			request.setAttribute("categoryBtns", categoryPidNameMap.get("categoryBtns"));
			isArticle = "yes";
			request.setAttribute("articleMaps", categoryMaps);
		} else {
			String categoryPid = cmCategoryManager.getCmCategoryById(categoryId).getCategoryPid();
			Map<String,String> categoryPidNameMap = cmCategoryManager.getCategoryPidsBtnsMap(categoryPid,categoryBaseUrl);
			request.setAttribute("categoryIds", categoryPidNameMap.get("categoryIds"));
			request.setAttribute("categoryBtns", categoryPidNameMap.get("categoryBtns"));
			request.setAttribute("categoryMaps", categoryMaps);
		}
		request.setAttribute("isArticle", isArticle);
		request.setAttribute("categoryId", categoryId);
        return "cm/cmArticleCategory/cmArticleCategoryJqmList";
	}
	
	@RequestMapping(value = "/cmArticleCategoryJqmAdd", method = RequestMethod.GET)
	public String toCmArticleCategoryJqmAdd(HttpServletRequest request) {
        return "cm/cmArticleCategory/cmArticleCategoryJqmAdd";
	}
	
	@RequestMapping(value = "/cmArticleCategoryJqmEdit/{articleCategoryId}", method = RequestMethod.GET)
	public String toCmArticleCategoryJqmEdit(@PathVariable String articleCategoryId, HttpServletRequest request) {
		CmArticleCategory cmArticleCategory = cmArticleCategoryManager.getCmArticleCategoryById(articleCategoryId);
		request.setAttribute("cmArticleCategory",cmArticleCategory);
        return "cm/cmArticleCategory/cmArticleCategoryJqmEdit";
	}
	
	@RequestMapping(value = "/saveCmArticleCategory", method = RequestMethod.POST)
	public String saveCmArticleCategory(HttpServletRequest request, RedirectAttributes ra) {
		CmArticleCategory cmArticleCategory = new CmArticleCategory();
		setValue(request, cmArticleCategory);
		cmArticleCategoryManager.save(cmArticleCategory);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmArticleCategory";
	}
	
	@RequestMapping(value = "/updateCmArticleCategory", method = RequestMethod.POST)
	public String updateCmArticleCategory(HttpServletRequest request, RedirectAttributes ra) {
		String articleCategoryId = request.getParameter("articleCategoryId");
		CmArticleCategory cmArticleCategory = cmArticleCategoryManager.getCmArticleCategoryById(articleCategoryId);
		setValue(request, cmArticleCategory);
		cmArticleCategoryManager.update(cmArticleCategory);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmArticleCategory"; 
	}
	
	@RequestMapping(value = "/deleteCmArticleCategory/{articleCategoryId}", method = RequestMethod.GET)
	public String deleteCmArticleCategory(@PathVariable String articleCategoryId, HttpServletRequest request, RedirectAttributes ra) {
		cmArticleCategoryManager.delete(articleCategoryId);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmArticleCategory"; 
	}

	@RequestMapping(value = "/deleteCmArticleCategorys", method = RequestMethod.POST)
	public String deleteCmArticleCategorys(HttpServletRequest request, RedirectAttributes ra) {
		cmArticleCategoryManager.batchDelete(request.getParameter("articleCategoryIds"));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmArticleCategory"; 
	}

	@Resource(name="cmArticleCategoryManagerImpl")
	private CmArticleCategoryManager cmArticleCategoryManager;
	@Resource(name="cmCategoryManagerImpl")
	private CmCategoryManager cmCategoryManager;
}