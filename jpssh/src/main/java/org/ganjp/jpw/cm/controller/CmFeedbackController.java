/**
 * $Id: CmFeedbackController.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
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
import org.ganjp.jpw.cm.model.CmFeedback;
import org.ganjp.jpw.cm.service.CmFeedbackManager;
import org.ganjp.jpw.core.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <p>CmFeedbackController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
public class CmFeedbackController extends BaseController {
	@RequestMapping(value = "/cmFeedback", method = RequestMethod.GET)
	public String toCmFeedbackJqmList(HttpServletRequest request) {
		List<CmFeedback> cmFeedbacks = cmFeedbackManager.getCmFeedbacks();
		request.setAttribute("cmFeedbacks", cmFeedbacks);
		super.setLeftMenu(request, BmMenu.MENU_TYPE_MANAGEMENT_CONSOLE);
        return "cm/cmFeedback/cmFeedbackJqmList";
	}
	
	@RequestMapping(value = "/cmFeedbackJqmAdd", method = RequestMethod.GET)
	public String toCmFeedbackJqmAdd(HttpServletRequest request) {
        return "cm/cmFeedback/cmFeedbackJqmAdd";
	}
	
	@RequestMapping(value = "/cmFeedbackJqmEdit/{feedbackId}", method = RequestMethod.GET)
	public String toCmFeedbackJqmEdit(@PathVariable String feedbackId, HttpServletRequest request) {
		CmFeedback cmFeedback = cmFeedbackManager.getCmFeedbackById(feedbackId);
		request.setAttribute("cmFeedback",cmFeedback);
        return "cm/cmFeedback/cmFeedbackJqmEdit";
	}
	
	@RequestMapping(value = "/saveCmFeedback", method = RequestMethod.POST)
	public String saveCmFeedback(HttpServletRequest request, RedirectAttributes ra) {
		CmFeedback cmFeedback = new CmFeedback();
		setValue(request, cmFeedback);
		cmFeedbackManager.save(cmFeedback);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmFeedback";
	}
	
	@RequestMapping(value = "/updateCmFeedback", method = RequestMethod.POST)
	public String updateCmFeedback(HttpServletRequest request, RedirectAttributes ra) {
		String feedbackId = request.getParameter("feedbackId");
		CmFeedback cmFeedback = cmFeedbackManager.getCmFeedbackById(feedbackId);
		setValue(request, cmFeedback);
		cmFeedbackManager.update(cmFeedback);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmFeedback"; 
	}
	
	@RequestMapping(value = "/deleteCmFeedback/{feedbackId}", method = RequestMethod.GET)
	public String deleteCmFeedback(@PathVariable String feedbackId, HttpServletRequest request, RedirectAttributes ra) {
		cmFeedbackManager.delete(feedbackId);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmFeedback"; 
	}

	@RequestMapping(value = "/deleteCmFeedbacks", method = RequestMethod.POST)
	public String deleteCmFeedbacks(HttpServletRequest request, RedirectAttributes ra) {
		cmFeedbackManager.batchDelete(request.getParameter("feedbackIds"));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmFeedback"; 
	}	
	
	@RequestMapping(value = "/saveCmFeedbackByAjax", method = RequestMethod.POST, produces="application/json")
	public @ResponseBody Map<String,String> saveCmFeedbackByAjax(HttpServletRequest request) {
		Map<String,String> map = new HashMap<String,String>();
		if (this.getLoginUser(request)==null) {
			map.put("result", "sessionOut");
			return map;
		}
		map.put("result", "success");
		try {
			CmFeedback cmFeedback = new CmFeedback();
			setValue(request, cmFeedback);
			cmFeedbackManager.save(cmFeedback);
		} catch (Exception e) {
			map.put("result", "fail");
		}
		return map;
	}

	@Resource(name="cmFeedbackManagerImpl")
	private CmFeedbackManager cmFeedbackManager;
}