/**
 * $Id: CmVocabularyCategoryController.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
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
import org.ganjp.jpw.cm.model.CmCategory;
import org.ganjp.jpw.cm.model.CmVocabularyCategory;
import org.ganjp.jpw.cm.service.CmCategoryManager;
import org.ganjp.jpw.cm.service.CmVocabularyCategoryManager;
import org.ganjp.jpw.core.util.StringUtil;
import org.ganjp.jpw.core.web.controller.BaseController;
import org.ganjp.jpw.core.web.util.ServletUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <p>CmVocabularyCategoryController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
public class CmVocabularyCategoryController extends BaseController {
	@RequestMapping(value = "/cmVocabularyCategory", method = RequestMethod.GET)
	public String toCmVocabularyCategoryJqmList(HttpServletRequest request) {
		super.setLeftMenu(request, BmMenu.MENU_TYPE_MANAGEMENT_CONSOLE);
		String categoryId = request.getParameter("categoryId");
		String isVocabulary = "no";
		if (StringUtil.isEmpty(categoryId)) {
			categoryId = cmCategoryManager.getCategoryId(CmCategory.CATEGORY_CD_VOCABULARY_CATEGORY, ServletUtil.getLanguage(request));
		}
		List<Map<String,String>> categoryMaps = cmCategoryManager.getCategoryMaps(categoryId,super.getLoginUserRoleIds(request));
		String categoryBaseUrl = super.getBasePath(request) + "/cmVocabularyCategory?menuId=" + request.getParameter("menuId")
				+ "&categoryId=";
		if (categoryMaps==null) {
			categoryMaps = cmVocabularyCategoryManager.getVocabularyMaps(categoryId,super.getLoginUserRoleIds(request));
			Map<String,String> categoryPidNameMap = cmCategoryManager.getCategoryPidsBtnsMap(categoryId,categoryBaseUrl);
			request.setAttribute("categoryIds", categoryPidNameMap.get("categoryIds"));
			request.setAttribute("categoryBtns", categoryPidNameMap.get("categoryBtns"));
			isVocabulary = "yes";
			request.setAttribute("vocabularyMaps", categoryMaps);
		} else {
			String categoryPid = cmCategoryManager.getCmCategoryById(categoryId).getCategoryPid();
			Map<String,String> categoryPidNameMap = cmCategoryManager.getCategoryPidsBtnsMap(categoryPid,categoryBaseUrl);
			request.setAttribute("categoryIds", categoryPidNameMap.get("categoryIds"));
			request.setAttribute("categoryBtns", categoryPidNameMap.get("categoryBtns"));
			request.setAttribute("categoryMaps", categoryMaps);
		}
		request.setAttribute("isVocabulary", isVocabulary);
		request.setAttribute("categoryId", categoryId);
		return "cm/cmVocabularyCategory/cmVocabularyCategoryJqmList";
	}
	
	@RequestMapping(value = "/cmVocabularyCategoryJqmAdd", method = RequestMethod.GET)
	public String toCmVocabularyCategoryJqmAdd(HttpServletRequest request) {
        return "cm/cmVocabularyCategory/cmVocabularyCategoryJqmAdd";
	}
	
	@RequestMapping(value = "/cmVocabularyCategoryJqmEdit/{vocabularyCategoryId}", method = RequestMethod.GET)
	public String toCmVocabularyCategoryJqmEdit(@PathVariable String vocabularyCategoryId, HttpServletRequest request) {
		CmVocabularyCategory cmVocabularyCategory = cmVocabularyCategoryManager.getCmVocabularyCategoryById(vocabularyCategoryId);
		request.setAttribute("cmVocabularyCategory",cmVocabularyCategory);
        return "cm/cmVocabularyCategory/cmVocabularyCategoryJqmEdit";
	}
	
	@RequestMapping(value = "/saveCmVocabularyCategory", method = RequestMethod.POST)
	public String saveCmVocabularyCategory(HttpServletRequest request, RedirectAttributes ra) {
		CmVocabularyCategory cmVocabularyCategory = new CmVocabularyCategory();
		setValue(request, cmVocabularyCategory);
		cmVocabularyCategoryManager.save(cmVocabularyCategory);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmVocabularyCategory";
	}
	
	@RequestMapping(value = "/updateCmVocabularyCategory", method = RequestMethod.POST)
	public String updateCmVocabularyCategory(HttpServletRequest request, RedirectAttributes ra) {
		String vocabularyCategoryId = request.getParameter("vocabularyCategoryId");
		CmVocabularyCategory cmVocabularyCategory = cmVocabularyCategoryManager.getCmVocabularyCategoryById(vocabularyCategoryId);
		setValue(request, cmVocabularyCategory);
		cmVocabularyCategoryManager.update(cmVocabularyCategory);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmVocabularyCategory"; 
	}
	
	@RequestMapping(value = "/deleteCmVocabularyCategory/{vocabularyCategoryId}", method = RequestMethod.GET)
	public String deleteCmVocabularyCategory(@PathVariable String vocabularyCategoryId, HttpServletRequest request, RedirectAttributes ra) {
		cmVocabularyCategoryManager.delete(vocabularyCategoryId);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmVocabularyCategory"; 
	}

	@RequestMapping(value = "/deleteCmVocabularyCategorys", method = RequestMethod.POST)
	public String deleteCmVocabularyCategorys(HttpServletRequest request, RedirectAttributes ra) {
		cmVocabularyCategoryManager.batchDelete(request.getParameter("vocabularyCategoryIds"));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmVocabularyCategory"; 
	}
	
	@Resource(name="cmVocabularyCategoryManagerImpl")
	private CmVocabularyCategoryManager cmVocabularyCategoryManager;
	@Resource(name="cmCategoryManagerImpl")
	private CmCategoryManager cmCategoryManager;
}