/**
 * $Id: CmVocabularyController.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.ganjp.jpw.bm.model.BmMenu;
import org.ganjp.jpw.bm.model.BmParam;
import org.ganjp.jpw.cm.model.CmCategory;
import org.ganjp.jpw.cm.model.CmVocabulary;
import org.ganjp.jpw.cm.service.CmCategoryManager;
import org.ganjp.jpw.cm.service.CmPhotoCategoryManager;
import org.ganjp.jpw.cm.service.CmVocabularyCategoryManager;
import org.ganjp.jpw.cm.service.CmVocabularyManager;
import org.ganjp.jpw.core.Config;
import org.ganjp.jpw.core.util.StringUtil;
import org.ganjp.jpw.core.web.controller.BaseController;
import org.ganjp.jpw.core.web.util.ServletUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <p>CmVocabularyController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
public class CmVocabularyController extends BaseController {
	@RequestMapping(value = "/cmVocabulary", method = RequestMethod.GET)
	public String toCmVocabularyJqmList(HttpServletRequest request) {
		List<CmVocabulary> cmVocabularys = cmVocabularyManager.getCmVocabularys();
		request.setAttribute("cmVocabularys", cmVocabularys);
		super.setLeftMenu(request, BmMenu.MENU_TYPE_MANAGEMENT_CONSOLE);
        return "cm/cmVocabulary/cmVocabularyJqmList";
	}
	
	@RequestMapping(value = "/cmVocabularyJqmAdd", method = RequestMethod.GET)
	public String toCmVocabularyJqmAdd(HttpServletRequest request) {
		request.setAttribute("categoryIds", request.getParameter("categoryIds"));
		request.setAttribute("categoryId", request.getParameter("categoryId"));
		request.setAttribute("roleMaps", super.getRoleMapsForJqmCheck(request));
		request.setAttribute("lang", ServletUtil.getLanguage(request));
		request.setAttribute("from", request.getParameter("from"));
		request.setAttribute("articleId", request.getParameter("articleId"));
		request.setAttribute("categoryMaps", cmCategoryManager.getCategorysForJqmCheck(
				CmCategory.CATEGORY_CD_VOCABULARY_CATEGORY, ServletUtil.getLanguage(request)));
		String isSimpleAdd = request.getParameter("isSimpleAdd");
		if ("yes".equals(isSimpleAdd)) {
			return "cm/cmVocabulary/cmVocabularyJqmSimpleAdd";
		} else {
			return "cm/cmVocabulary/cmVocabularyJqmAdd";
		}
	}
	
	@RequestMapping(value = "/cmVocabularyJqmEdit/{vocabularyId}", method = RequestMethod.GET)
	public String toCmVocabularyJqmEdit(@PathVariable String vocabularyId, HttpServletRequest request) {
		CmVocabulary cmVocabulary = cmVocabularyManager.getCmVocabularyById(vocabularyId);
		request.setAttribute("cmVocabulary",cmVocabulary);
		request.setAttribute("categoryId", request.getParameter("categoryId"));
		request.setAttribute("categoryIds", cmVocabularyCategoryManager.getCategoryIds(vocabularyId));
		request.setAttribute("articleId", request.getParameter("articleId"));
		request.setAttribute("roleMaps", super.getRoleMapsForJqmCheck(request));
		request.setAttribute("lang", ServletUtil.getLanguage(request));
		request.setAttribute("from", request.getParameter("from"));
		request.setAttribute("categoryMaps", cmCategoryManager.getCategorysForJqmCheck(
				CmCategory.CATEGORY_CD_VOCABULARY_CATEGORY, ServletUtil.getLanguage(request)));
        return "cm/cmVocabulary/cmVocabularyJqmEdit";
	}
	
	@RequestMapping(value = "/saveCmVocabulary", method = RequestMethod.POST)
	public String saveCmVocabulary(HttpServletRequest request, RedirectAttributes ra) {
		CmVocabulary cmVocabulary = new CmVocabulary();
		setValue(request, cmVocabulary);
		cmVocabularyCategoryManager.saveCmVocabularyAndCategory(cmVocabulary, request.getParameter("categoryIds"));
		
		String from = request.getParameter("from");
		if ("cmVocabularyChooseDialog".equals(from)) {
			return "redirect:/cmVocabularyChooseDialog/"+request.getParameter("articleId");
		}
		
		ra.addAttribute("menuId", request.getParameter("menuId"));
		String categoryId = request.getParameter("categoryId");
		if (StringUtil.isNotEmpty(categoryId)) {
			ra.addAttribute("categoryId", categoryId);		
			return "redirect:/cmVocabularyCategory";		
		} else {
			return "redirect:/cmVocabulary";
		}
	}
	
	@RequestMapping(value = "/updateCmVocabulary", method = RequestMethod.POST)
	public String updateCmVocabulary(HttpServletRequest request, RedirectAttributes ra) {
		String vocabularyId = request.getParameter("vocabularyId");
		CmVocabulary cmVocabulary = cmVocabularyManager.getCmVocabularyById(vocabularyId);
		setValue(request, cmVocabulary);
		cmVocabularyManager.update(cmVocabulary);
		cmVocabularyCategoryManager.updateCmVocabularyAndCategory(cmVocabulary, request.getParameter("newCategoryIds"));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		String from = request.getParameter("from");
		if ("cmArticleJqmDetail".equals(from)) {
			ra.addAttribute("menuId", request.getParameter("menuId"));
			ra.addAttribute("categoryId", request.getParameter("categoryId"));
			return "redirect:/cmArticleJqmDetail/"+request.getParameter("articleId");
		}
		
		String categoryId = request.getParameter("categoryId");
		if (StringUtil.isNotEmpty(categoryId)) {
			ra.addAttribute("categoryId", categoryId);		
			return "redirect:/cmVocabularyCategory";		
		} else {
			return "redirect:/cmVocabulary";
		}
	}
	
	@RequestMapping(value = "/deleteCmVocabulary/{vocabularyId}", method = RequestMethod.GET)
	public String deleteCmVocabulary(@PathVariable String vocabularyId, HttpServletRequest request, RedirectAttributes ra) {
		cmVocabularyManager.delete(vocabularyId);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		String categoryId = request.getParameter("categoryId");
		if (StringUtil.isNotEmpty(categoryId)) {
			ra.addAttribute("categoryId", categoryId);		
			return "redirect:/cmVocabularyCategory";		
		} else {
			return "redirect:/cmVocabulary";
		}
	}

	@RequestMapping(value = "/deleteCmVocabularys", method = RequestMethod.POST)
	public String deleteCmVocabularys(HttpServletRequest request, RedirectAttributes ra) {
		cmVocabularyManager.batchDelete(request.getParameter("vocabularyIds"));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		String categoryId = request.getParameter("categoryId");
		if (StringUtil.isNotEmpty(categoryId)) {
			ra.addAttribute("categoryId", categoryId);		
			return "redirect:/cmVocabularyCategory";		
		} else {
			return "redirect:/cmVocabulary";
		} 
	}
	
	@RequestMapping(value = "/cmVocabularyChooseDialog/{articleId}", method = RequestMethod.GET)
	public String cmVocabularyJqmListForArticle(@PathVariable String articleId, HttpServletRequest request) {
		List<CmVocabulary> cmVocabularys = cmVocabularyManager.getCmVocabularys(super.getLoginUserRoleIds(request));
		request.setAttribute("cmVocabularys", cmVocabularys);
		request.setAttribute("articleId", articleId);
        return "cm/cmVocabulary/cmVocabularyChooseDialog";
	}

	@RequestMapping(value = "/cmVocabularyPdf")
	public String showCmVocabularyPdf(ModelMap mm,HttpServletRequest request) {
		String categoryId = request.getParameter("categoryId");
		mm.addAttribute("categoryName", cmCategoryManager.getCmCategoryById(categoryId).getCategoryName());
		mm.addAttribute("categoryMaps", cmVocabularyCategoryManager.getVocabularyMaps(categoryId, super.getLoginUserRoleIds(request)));
		return "cmVocabularyPdf";
	}
	
	@RequestMapping(value = "/vocabularyJqm", method = RequestMethod.GET)
	public String toArticleJqm(HttpServletRequest request) {
		String activeCategoryId = request.getParameter("categoryId");
		String categoryCd = request.getParameter("categoryCd");
		String menuId = request.getParameter("menuId");
		String secondMenuId = request.getParameter("secondMenuId");
		String categoryJqmMenu = cmCategoryManager.getCategoryJqmHtml(activeCategoryId, categoryCd, super.getBasePath(request) + 
				"/vocabularyJqm?menuId=" + menuId + "&secondMenuId=" + secondMenuId, ServletUtil.getLanguage(request), 
				ServletUtil.getJqmTheme(request), super.getLoginUserRoleIds(request));
		request.setAttribute("categoryJqmMenu",categoryJqmMenu);
		request.setAttribute("vocabularyMaps",cmVocabularyCategoryManager.getVocabularyMaps(activeCategoryId, super.getLoginUserRoleIds(request)));
		
		String categoryId = cmCategoryManager.getCategoryId(CmCategory.CATEGORY_CD_HOME_PHOTO_SCROLLED, ServletUtil.getLanguage(request));
		request.setAttribute("photoMaps", cmPhotoCategoryManager.getPhotoMaps(categoryId, super.getLoginUserRoleIds(request)));
		Map levelAndMenuMapss = Config.getLevelAndMenuMapss(BmParam.PARAM_CD_HOME_MAIN_MENU, 
				ServletUtil.getLanguage(request), super.getLoginUserRoleIds(request), menuId);
		request.setAttribute("mainMenuMaps", levelAndMenuMapss.get("first"));
		request.setAttribute("secondMenuMaps", levelAndMenuMapss.get("second"));
		request.setAttribute("secondMenuId", secondMenuId);
		request.setAttribute("categoryId", activeCategoryId);
        return "/cm/common/jqmStyle/vocabularyJqm";
	}
	
	@Resource(name="cmVocabularyManagerImpl")
	private CmVocabularyManager cmVocabularyManager;
	
	@Resource(name="cmVocabularyCategoryManagerImpl")
	private CmVocabularyCategoryManager cmVocabularyCategoryManager;
	
	@Resource(name="cmCategoryManagerImpl")
	private CmCategoryManager cmCategoryManager;
	
	@Resource(name="cmPhotoCategoryManagerImpl")
	private CmPhotoCategoryManager cmPhotoCategoryManager;
}