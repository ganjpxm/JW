/**
 * $Id: CmArticleRemarkController.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
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
import org.ganjp.jpw.cm.model.CmArticleRemark;
import org.ganjp.jpw.cm.service.CmArticleRemarkManager;
import org.ganjp.jpw.core.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <p>CmArticleRemarkController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
public class CmArticleRemarkController extends BaseController {
	@RequestMapping(value = "/cmArticleRemark", method = RequestMethod.GET)
	public String toCmArticleRemarkJqmList(HttpServletRequest request) {
		List<CmArticleRemark> cmArticleRemarks = cmArticleRemarkManager.getCmArticleRemarks();
		request.setAttribute("cmArticleRemarks", cmArticleRemarks);
		super.setLeftMenu(request, BmMenu.MENU_TYPE_MANAGEMENT_CONSOLE);
        return "cm/cmArticleRemark/cmArticleRemarkJqmList";
	}
	
	@RequestMapping(value = "/cmArticleRemarkJqmAdd", method = RequestMethod.GET)
	public String toCmArticleRemarkJqmAdd(HttpServletRequest request) {
        return "cm/cmArticleRemark/cmArticleRemarkJqmAdd";
	}
	
	@RequestMapping(value = "/cmArticleRemarkJqmEdit/{articleRemarkId}", method = RequestMethod.GET)
	public String toCmArticleRemarkJqmEdit(@PathVariable String articleRemarkId, HttpServletRequest request) {
		CmArticleRemark cmArticleRemark = cmArticleRemarkManager.getCmArticleRemarkById(articleRemarkId);
		request.setAttribute("cmArticleRemark",cmArticleRemark);
        return "cm/cmArticleRemark/cmArticleRemarkJqmEdit";
	}
	
	@RequestMapping(value = "/saveCmArticleRemark", method = RequestMethod.POST)
	public String saveCmArticleRemark(HttpServletRequest request, RedirectAttributes ra) {
		CmArticleRemark cmArticleRemark = new CmArticleRemark();
		setValue(request, cmArticleRemark);
		cmArticleRemarkManager.save(cmArticleRemark);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmArticleRemark";
	}
	
	@RequestMapping(value = "/updateCmArticleRemark", method = RequestMethod.POST)
	public String updateCmArticleRemark(HttpServletRequest request, RedirectAttributes ra) {
		String articleRemarkId = request.getParameter("articleRemarkId");
		CmArticleRemark cmArticleRemark = cmArticleRemarkManager.getCmArticleRemarkById(articleRemarkId);
		setValue(request, cmArticleRemark);
		cmArticleRemarkManager.update(cmArticleRemark);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmArticleRemark"; 
	}
	
	@RequestMapping(value = "/deleteCmArticleRemark/{articleRemarkId}", method = RequestMethod.GET)
	public String deleteCmArticleRemark(@PathVariable String articleRemarkId, HttpServletRequest request, RedirectAttributes ra) {
		cmArticleRemarkManager.delete(articleRemarkId);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmArticleRemark"; 
	}

	@RequestMapping(value = "/deleteCmArticleRemarks", method = RequestMethod.POST)
	public String deleteCmArticleRemarks(HttpServletRequest request, RedirectAttributes ra) {
		cmArticleRemarkManager.batchDelete(request.getParameter("articleRemarkIds"));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmArticleRemark"; 
	}
	
	@RequestMapping(value = "/saveCmArticleRemarkByAjax", method = RequestMethod.GET)
	public @ResponseBody Map<String,String> deleteCmArticleByAjax(HttpServletRequest request, RedirectAttributes ra) {
		String articleId = request.getParameter("articleId");
		String remark = request.getParameter("remark");
		Map<String,String> map = new HashMap<String,String>();
		if (this.getLoginUser(request)==null) {
			map.put("result", "sessionOut");
			return map;
		}
		map.put("result", "Success");
		try {
			CmArticleRemark cmArticleRemark = new CmArticleRemark();
			cmArticleRemark.setArticleId(articleId);
			cmArticleRemark.setRemark(remark);
			cmArticleRemark.setOperatorId(super.getLoginUserId(request));
			cmArticleRemark.setOperatorName(super.getLoginUserName(request));
			cmArticleRemarkManager.save(cmArticleRemark);
		} catch (Exception e) {
			log.error(e.getMessage());
			map.put("result", "Save Fail");
		}
		return map;
	}

	
	@Resource(name="cmArticleRemarkManagerImpl")
	private CmArticleRemarkManager cmArticleRemarkManager;
}