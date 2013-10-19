/**
 * $Id: CmVocabularyArticleController.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
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
import org.ganjp.jpw.cm.model.CmVocabularyArticle;
import org.ganjp.jpw.cm.service.CmVocabularyArticleManager;
import org.ganjp.jpw.core.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <p>CmVocabularyArticleController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
public class CmVocabularyArticleController extends BaseController {
	@RequestMapping(value = "/cmVocabularyArticle", method = RequestMethod.GET)
	public String toCmVocabularyArticleJqmList(HttpServletRequest request) {
		List<CmVocabularyArticle> cmVocabularyArticles = cmVocabularyArticleManager.getCmVocabularyArticles();
		request.setAttribute("cmVocabularyArticles", cmVocabularyArticles);
		super.setLeftMenu(request, BmMenu.MENU_TYPE_MANAGEMENT_CONSOLE);
        return "cm/cmVocabularyArticle/cmVocabularyArticleJqmList";
	}
	
	@RequestMapping(value = "/cmVocabularyArticleJqmAdd", method = RequestMethod.GET)
	public String toCmVocabularyArticleJqmAdd(HttpServletRequest request) {
        return "cm/cmVocabularyArticle/cmVocabularyArticleJqmAdd";
	}
	
	@RequestMapping(value = "/cmVocabularyArticleJqmEdit/{vocabularyArticleId}", method = RequestMethod.GET)
	public String toCmVocabularyArticleJqmEdit(@PathVariable String vocabularyArticleId, HttpServletRequest request) {
		CmVocabularyArticle cmVocabularyArticle = cmVocabularyArticleManager.getCmVocabularyArticleById(vocabularyArticleId);
		request.setAttribute("cmVocabularyArticle",cmVocabularyArticle);
        return "cm/cmVocabularyArticle/cmVocabularyArticleJqmEdit";
	}
	
	@RequestMapping(value = "/saveCmVocabularyArticle", method = RequestMethod.POST)
	public String saveCmVocabularyArticle(HttpServletRequest request, RedirectAttributes ra) {
		CmVocabularyArticle cmVocabularyArticle = new CmVocabularyArticle();
		setValue(request, cmVocabularyArticle);
		cmVocabularyArticleManager.save(cmVocabularyArticle);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmVocabularyArticle";
	}
	
	@RequestMapping(value = "/updateCmVocabularyArticle", method = RequestMethod.POST)
	public String updateCmVocabularyArticle(HttpServletRequest request, RedirectAttributes ra) {
		String vocabularyArticleId = request.getParameter("vocabularyArticleId");
		CmVocabularyArticle cmVocabularyArticle = cmVocabularyArticleManager.getCmVocabularyArticleById(vocabularyArticleId);
		setValue(request, cmVocabularyArticle);
		cmVocabularyArticleManager.update(cmVocabularyArticle);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmVocabularyArticle"; 
	}
	
	@RequestMapping(value = "/deleteCmVocabularyArticle/{vocabularyArticleId}", method = RequestMethod.GET)
	public String deleteCmVocabularyArticle(@PathVariable String vocabularyArticleId, HttpServletRequest request, RedirectAttributes ra) {
		cmVocabularyArticleManager.delete(vocabularyArticleId);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		ra.addAttribute("categoryId", request.getParameter("categoryId"));
		return "redirect:/cmArticleJqmDetail/"+request.getParameter("articleId"); 
	}

	@RequestMapping(value = "/deleteCmVocabularyArticles", method = RequestMethod.POST)
	public String deleteCmVocabularyArticles(HttpServletRequest request, RedirectAttributes ra) {
		cmVocabularyArticleManager.batchDelete(request.getParameter("vocabularyArticleIds"));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmVocabularyArticle"; 
	}
	
	@RequestMapping(value="/saveCmVocabularyArticles/{articleId}", method=RequestMethod.GET)
	public @ResponseBody Map<String,String> deleteFile(@PathVariable String articleId, HttpServletRequest request) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("result", "success");
		try {
			String vocabularyIds = request.getParameter("vocabularyIds");
			map.put("result", cmVocabularyArticleManager.saveCmVocabularyArticles(articleId, vocabularyIds));
		} catch (Exception e) {
			map.put("result", "fail");
		}
		return map;
	}
	
	@Resource(name="cmVocabularyArticleManagerImpl")
	private CmVocabularyArticleManager cmVocabularyArticleManager;
}