/**
 * $Id: CmArticleController.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.ganjp.jpw.bm.model.BmMenu;
import org.ganjp.jpw.bm.model.BmParam;
import org.ganjp.jpw.cm.model.CmArticle;
import org.ganjp.jpw.cm.model.CmCategory;
import org.ganjp.jpw.cm.service.CmArticleCategoryManager;
import org.ganjp.jpw.cm.service.CmArticleManager;
import org.ganjp.jpw.cm.service.CmCategoryManager;
import org.ganjp.jpw.cm.service.CmPhotoCategoryManager;
import org.ganjp.jpw.cm.service.CmVocabularyArticleManager;
import org.ganjp.jpw.core.Config;
import org.ganjp.jpw.core.util.StringUtil;
import org.ganjp.jpw.core.web.controller.BaseController;
import org.ganjp.jpw.core.web.util.ServletUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <p>CmArticleController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
public class CmArticleController extends BaseController {
	@RequestMapping(value = "/cmArticle", method = RequestMethod.GET)
	public String toCmArticleJqmList(HttpServletRequest request) {
		List<CmArticle> cmArticles = cmArticleManager.getCmArticles();
		request.setAttribute("cmArticles", cmArticles);
		super.setLeftMenu(request, BmMenu.MENU_TYPE_MANAGEMENT_CONSOLE);
        return "cm/cmArticle/cmArticleJqmList";
	}
	
	@RequestMapping(value = "/cmArticleJqmAdd", method = RequestMethod.GET)
	public String toCmArticleJqmAdd(HttpServletRequest request) {
		String articleCategoryCheckboxHtml = cmCategoryManager.getCategoryCheckboxHtml(CmCategory.CATEGORY_CD_ARTICLE_CATEGORY, ServletUtil.getLanguage(request), super.getLoginUserRoleIds(request));
		request.setAttribute("articleCategoryCheckboxHtml", articleCategoryCheckboxHtml);
		request.setAttribute("categoryIds", request.getParameter("categoryIds"));
		request.setAttribute("categoryId", request.getParameter("categoryId"));
		request.setAttribute("roleMaps", super.getRoleMapsForJqmCheck(request));
		request.setAttribute("categoryMaps", cmCategoryManager.getCategorysForJqmCheck(
				CmCategory.CATEGORY_CD_ARTICLE_CATEGORY, ServletUtil.getLanguage(request)));
		request.setAttribute("roleMaps", super.getRoleMapsForJqmCheck(request));
		
		request.setAttribute("from", request.getParameter("from"));
		request.setAttribute("edit", request.getParameter("edit"));
//		request.setAttribute("tag", request.getParameter("tag"));
		request.setAttribute("pageSize", request.getParameter("pageSize"));
		request.setAttribute("pageNo", request.getParameter("pageNo"));
        return "cm/cmArticle/cmArticleJqmAdd";
	}
	
	@RequestMapping(value = "/cmArticleJqmPostAdd", method = RequestMethod.POST)
	public String toCmArticleJqmPostAdd(HttpServletRequest request) {
		request.setAttribute("roleMaps", super.getRoleMapsForJqmCheck(request));
		request.setAttribute("categoryMaps", cmCategoryManager.getCategorysForJqmCheck(
				CmCategory.CATEGORY_CD_ARTICLE_CATEGORY, ServletUtil.getLanguage(request)));
		request.setAttribute("roleMaps", super.getRoleMapsForJqmCheck(request));
		
		request.setAttribute("from", request.getParameter("from"));
		request.setAttribute("edit", request.getParameter("edit"));
		request.setAttribute("tag", request.getParameter("tag"));
		request.setAttribute("pageSize", request.getParameter("pageSize"));
		request.setAttribute("pageNo", request.getParameter("pageNo"));
        return "cm/cmArticle/cmArticleJqmAdd";
	}
	
	@RequestMapping(value = "/cmArticleJqmEdit/{articleId}", method = RequestMethod.GET)
	public String toCmArticleJqmEdit(@PathVariable String articleId, HttpServletRequest request) {
		CmArticle cmArticle = cmArticleManager.getCmArticleById(articleId);
		request.setAttribute("cmArticle",cmArticle);
		request.setAttribute("categoryId", request.getParameter("categoryId"));
		request.setAttribute("categoryIds", cmArticleCategoryManager.getCategoryIds(articleId));
		request.setAttribute("roleMaps", super.getRoleMapsForJqmCheck(request));
		request.setAttribute("categoryMaps", cmCategoryManager.getCategorysForJqmCheck(
				CmCategory.CATEGORY_CD_ARTICLE_CATEGORY, ServletUtil.getLanguage(request)));
		
		request.setAttribute("from", request.getParameter("from"));
		request.setAttribute("edit", request.getParameter("edit"));
//		request.setAttribute("tag", request.getParameter("tag"));
		request.setAttribute("pageSize", request.getParameter("pageSize"));
		request.setAttribute("pageNo", request.getParameter("pageNo"));
        return "cm/cmArticle/cmArticleJqmEdit";
	}
	
	@RequestMapping(value = "/saveCmArticle", method = RequestMethod.POST)
	public String saveCmArticle(HttpServletRequest request, RedirectAttributes ra) {
		CmArticle cmArticle = new CmArticle();
		setValue(request, cmArticle);
		if (cmArticle.getDisplayNo()==null) {
			cmArticle.setDisplayNo(100);
		}
		String roleIds = cmArticle.getRoleIds();
		if (StringUtil.isNotEmpty(roleIds) && roleIds.indexOf("public")!=-1) {
			cmArticle.setRoleIds("");
		}
		cmArticleCategoryManager.saveCmArticleAndCategory(cmArticle, request.getParameter("categoryIds"));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		String categoryId = request.getParameter("categoryId");
		
		String from = request.getParameter("from");
		if (StringUtil.isEmpty(from)) {
			if (StringUtil.isNotEmpty(categoryId)) {
				ra.addAttribute("categoryId", categoryId);		
				return "redirect:/cmArticleCategory";		
			} else {
				return "redirect:/cmArticle";
			}
		} else {
			ra.addAttribute("edit", request.getParameter("edit"));
//			ra.addAttribute("tag", request.getParameter("tag"));
			ra.addAttribute("pageNo", request.getParameter("pageNo"));
			ra.addAttribute("pageSize", request.getParameter("pageSize"));	
			return "redirect:"+from;
		}
	}
	
	@RequestMapping(value = "/updateCmArticle", method = RequestMethod.POST)
	public String updateCmArticle(HttpServletRequest request, RedirectAttributes ra) {
		String articleId = request.getParameter("articleId");
		CmArticle cmArticle = cmArticleManager.getCmArticleById(articleId);
		setValue(request, cmArticle);
		if (cmArticle.getDisplayNo()==null) {
			cmArticle.setDisplayNo(100);
		}
		cmArticleCategoryManager.updateCmArticleAndCategory(cmArticle, request.getParameter("newCategoryIds"));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		ra.addAttribute("articleId", articleId);
		String categoryId = request.getParameter("categoryId");
		
		String from = request.getParameter("from");
		if (StringUtil.isEmpty(from)) {
			if (StringUtil.isNotEmpty(categoryId)) {
				ra.addAttribute("categoryId", categoryId);		
				return "redirect:/cmArticleCategory";		
			} else {
				return "redirect:/cmArticle";
			}
		} else {
			ra.addAttribute("edit", request.getParameter("edit"));
//			ra.addAttribute("tag", request.getParameter("searchTag"));
			ra.addAttribute("pageNo", request.getParameter("pageNo"));
			ra.addAttribute("pageSize", request.getParameter("pageSize"));
			return "redirect:"+from;
		}
		
	}
	
	@RequestMapping(value = "/deleteCmArticle/{articleId}", method = RequestMethod.GET)
	public String deleteCmArticle(@PathVariable String articleId, HttpServletRequest request, RedirectAttributes ra) {
		cmArticleManager.batchDelete(articleId);
		String from = request.getParameter("from");
		if (StringUtil.isEmpty(from)) {
			ra.addAttribute("menuId", request.getParameter("menuId"));
			return "redirect:/cmArticle";
		} else {
			ra.addAttribute("edit", request.getParameter("edit"));
			ra.addAttribute("tag", request.getParameter("tag"));
			ra.addAttribute("pageNo", request.getParameter("pageNo"));
			ra.addAttribute("pageSize", request.getParameter("pageSize"));
			return "redirect:" + from;
		}
	}

	@RequestMapping(value = "/deleteCmArticles", method = RequestMethod.POST)
	public String deleteCmArticles(HttpServletRequest request, RedirectAttributes ra) {
		cmArticleManager.batchDelete(request.getParameter("articleIds"));
		
		String from = request.getParameter("from");
		if (StringUtil.isEmpty(from)) {
			ra.addAttribute("menuId", request.getParameter("menuId"));
			String categoryId = request.getParameter("categoryId");
			if (StringUtil.isNotEmpty(categoryId)) {
				ra.addAttribute("categoryId", categoryId);		
				return "redirect:/cmArticleCategory";		
			} else {
				return "redirect:/cmArticle";
			}
		} else {
			ra.addAttribute("edit", request.getParameter("edit"));
			ra.addAttribute("tag", request.getParameter("tag"));
			ra.addAttribute("pageNo", request.getParameter("pageNo"));
			ra.addAttribute("pageSize", request.getParameter("pageSize"));
			return "redirect:" + from;
		}
	}
	
	@RequestMapping(value = "/cmArticleJqmDetail/{articleId}", method = RequestMethod.GET)
	public String toCmArticleJqmDetail(@PathVariable String articleId, HttpServletRequest request) {
		CmArticle cmArticle = cmArticleManager.getCmArticleById(articleId);
		request.setAttribute("cmArticle",cmArticle);
		request.setAttribute("categoryId", request.getParameter("categoryId"));
		request.setAttribute("vocabularyMaps", cmVocabularyArticleManager.getVocabularyMaps(articleId));
        return "cm/cmArticle/cmArticleJqmDetail";
	}
	
	@RequestMapping(value = "/articleJqm", method = RequestMethod.GET)
	public String toArticleJqm(HttpServletRequest request) {
		String activeCategoryId = request.getParameter("categoryId");
		String categoryCd = request.getParameter("categoryCd");
		String menuId = request.getParameter("menuId");
		String secondMenuId = request.getParameter("secondMenuId");
		String categoryJqmMenu = cmCategoryManager.getCategoryJqmHtml(activeCategoryId, categoryCd, super.getBasePath(request) + 
				"/articleJqm?menuId=" + menuId + "&secondMenuId=" + secondMenuId,ServletUtil.getLanguage(request), 
				ServletUtil.getJqmTheme(request), super.getLoginUserRoleIds(request));
		request.setAttribute("categoryJqmMenu",categoryJqmMenu);
		request.setAttribute("articleMaps",cmArticleCategoryManager.getArticleMaps(activeCategoryId, super.getLoginUserRoleIds(request)));
		
		String categoryId = cmCategoryManager.getCategoryId(CmCategory.CATEGORY_CD_HOME_PHOTO_SCROLLED, ServletUtil.getLanguage(request));
		request.setAttribute("photoMaps", cmPhotoCategoryManager.getPhotoMaps(categoryId, super.getLoginUserRoleIds(request)));
		Map levelAndMenuMapss = Config.getLevelAndMenuMapss(BmParam.PARAM_CD_HOME_MAIN_MENU, 
				ServletUtil.getLanguage(request), super.getLoginUserRoleIds(request), menuId);
		request.setAttribute("mainMenuMaps", levelAndMenuMapss.get("first"));
		request.setAttribute("secondMenuMaps", levelAndMenuMapss.get("second"));
		request.setAttribute("secondMenuId", secondMenuId);
		
        return "/cm/common/jqmStyle/articleJqm";
	}

	@RequestMapping(value = "/articleJqmDetail/{articleId}", method = RequestMethod.GET)
	public String toArticleJqmDetail(@PathVariable String articleId, HttpServletRequest request) {
		CmArticle cmArticle = cmArticleManager.getCmArticleById(articleId);
		request.setAttribute("cmArticle",cmArticle);
		//request.setAttribute("vocabularyMaps", cmVocabularyArticleManager.getVocabularyMaps(articleId));
		
		request.setAttribute("from", request.getParameter("from"));
		request.setAttribute("edit", request.getParameter("edit"));
		return "/cm/common/jqmStyle/articleJqmDetail";
	}
	
	@RequestMapping(value = "/deleteCmArticleByAjax/{articleId}", method = RequestMethod.GET)
	public @ResponseBody Map<String,String> deleteCmArticleByAjax(@PathVariable String articleId, HttpServletRequest request, RedirectAttributes ra) {
		Map<String,String> map = new HashMap<String,String>();
		if (this.getLoginUser(request)==null) {
			map.put("result", "sessionOut");
			return map;
		}
		map.put("result", "Success");
		try {
			cmArticleManager.batchDelete(articleId);
		} catch (Exception e) {
			log.error(e.getMessage());
			map.put("result", "Delete Fail");
		}
		return map;
	}
	
	@Resource(name="cmArticleManagerImpl")
	private CmArticleManager cmArticleManager;
	@Resource(name="cmArticleCategoryManagerImpl")
	private CmArticleCategoryManager cmArticleCategoryManager;
	@Resource(name="cmCategoryManagerImpl")
	private CmCategoryManager cmCategoryManager;
	@Resource(name="cmVocabularyArticleManagerImpl")
	private CmVocabularyArticleManager cmVocabularyArticleManager;
	@Resource(name="cmPhotoCategoryManagerImpl")
	private CmPhotoCategoryManager cmPhotoCategoryManager;
}