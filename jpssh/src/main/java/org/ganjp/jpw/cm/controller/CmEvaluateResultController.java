/**
 * $Id: CmEvaluateResultController.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.controller;

import org.ganjp.jpw.cm.model.CmEvaluateResult;
import org.ganjp.jpw.cm.service.CmEvaluateResultManager;
import org.ganjp.jpw.core.web.controller.BaseController;
import org.ganjp.jpw.bm.model.BmMenu;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>CmEvaluateResultController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
public class CmEvaluateResultController extends BaseController {
	@RequestMapping(value = "/cmEvaluateResult", method = RequestMethod.GET)
	public String toCmEvaluateResultJqmList(HttpServletRequest request) {
		List<CmEvaluateResult> cmEvaluateResults = cmEvaluateResultManager.getCmEvaluateResults();
		request.setAttribute("cmEvaluateResults", cmEvaluateResults);
		super.setLeftMenu(request, BmMenu.MENU_TYPE_MANAGEMENT_CONSOLE);
        return "cm/cmEvaluateResult/cmEvaluateResultJqmList";
	}
	
	@RequestMapping(value = "/cmEvaluateResultJqmAdd", method = RequestMethod.GET)
	public String toCmEvaluateResultJqmAdd(HttpServletRequest request) {
        return "cm/cmEvaluateResult/cmEvaluateResultJqmAdd";
	}
	
	@RequestMapping(value = "/cmEvaluateResultJqmEdit/{evaluateResultId}", method = RequestMethod.GET)
	public String toCmEvaluateResultJqmEdit(@PathVariable String evaluateResultId, HttpServletRequest request) {
		CmEvaluateResult cmEvaluateResult = cmEvaluateResultManager.getCmEvaluateResultById(evaluateResultId);
		request.setAttribute("cmEvaluateResult",cmEvaluateResult);
        return "cm/cmEvaluateResult/cmEvaluateResultJqmEdit";
	}
	
	@RequestMapping(value = "/saveCmEvaluateResult", method = RequestMethod.POST)
	public String saveCmEvaluateResult(HttpServletRequest request, RedirectAttributes ra) {
		CmEvaluateResult cmEvaluateResult = new CmEvaluateResult();
		setValue(request, cmEvaluateResult);
		cmEvaluateResultManager.save(cmEvaluateResult);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmEvaluateResult";
	}
	
	@RequestMapping(value = "/updateCmEvaluateResult", method = RequestMethod.POST)
	public String updateCmEvaluateResult(HttpServletRequest request, RedirectAttributes ra) {
		String evaluateResultId = request.getParameter("evaluateResultId");
		CmEvaluateResult cmEvaluateResult = cmEvaluateResultManager.getCmEvaluateResultById(evaluateResultId);
		setValue(request, cmEvaluateResult);
		cmEvaluateResultManager.update(cmEvaluateResult);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmEvaluateResult"; 
	}
	
	@RequestMapping(value = "/deleteCmEvaluateResult/{evaluateResultId}", method = RequestMethod.GET)
	public String deleteCmEvaluateResult(@PathVariable String evaluateResultId, HttpServletRequest request, RedirectAttributes ra) {
		cmEvaluateResultManager.delete(evaluateResultId);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmEvaluateResult"; 
	}

	@RequestMapping(value = "/deleteCmEvaluateResults", method = RequestMethod.POST)
	public String deleteCmEvaluateResults(HttpServletRequest request, RedirectAttributes ra) {
		cmEvaluateResultManager.batchDelete(request.getParameter("evaluateResultIds"));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmEvaluateResult"; 
	}	
	@Resource(name="cmEvaluateResultManagerImpl")
	private CmEvaluateResultManager cmEvaluateResultManager;
}