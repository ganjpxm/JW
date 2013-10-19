/**
 * $Id: AmUserDetailController.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.am.controller;

import org.ganjp.jpw.am.model.AmUserDetail;
import org.ganjp.jpw.am.service.AmUserDetailManager;
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
 * <p>AmUserDetailController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
public class AmUserDetailController extends BaseController {
	@RequestMapping(value = "/amUserDetail", method = RequestMethod.GET)
	public String toAmUserDetailJqmList(HttpServletRequest request) {
		List<AmUserDetail> amUserDetails = amUserDetailManager.getAmUserDetails();
		request.setAttribute("amUserDetails", amUserDetails);
		super.setLeftMenu(request, BmMenu.MENU_TYPE_MANAGEMENT_CONSOLE);
        return "am/amUserDetail/amUserDetailJqmList";
	}
	
	@RequestMapping(value = "/amUserDetailJqmAdd", method = RequestMethod.GET)
	public String toAmUserDetailJqmAdd(HttpServletRequest request) {
        return "am/amUserDetail/amUserDetailJqmAdd";
	}
	
	@RequestMapping(value = "/amUserDetailJqmEdit/{userDetailId}", method = RequestMethod.GET)
	public String toAmUserDetailJqmEdit(@PathVariable String userDetailId, HttpServletRequest request) {
		AmUserDetail amUserDetail = amUserDetailManager.getAmUserDetailById(userDetailId);
		request.setAttribute("amUserDetail",amUserDetail);
        return "am/amUserDetail/amUserDetailJqmEdit";
	}
	
	@RequestMapping(value = "/saveAmUserDetail", method = RequestMethod.POST)
	public String saveAmUserDetail(HttpServletRequest request, RedirectAttributes ra) {
		AmUserDetail amUserDetail = new AmUserDetail();
		setValue(request, amUserDetail);
		amUserDetailManager.save(amUserDetail);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/amUserDetail";
	}
	
	@RequestMapping(value = "/updateAmUserDetail", method = RequestMethod.POST)
	public String updateAmUserDetail(HttpServletRequest request, RedirectAttributes ra) {
		String userDetailId = request.getParameter("userDetailId");
		AmUserDetail amUserDetail = amUserDetailManager.getAmUserDetailById(userDetailId);
		setValue(request, amUserDetail);
		amUserDetailManager.update(amUserDetail);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/amUserDetail"; 
	}
	
	@RequestMapping(value = "/deleteAmUserDetail/{userDetailId}", method = RequestMethod.GET)
	public String deleteAmUserDetail(@PathVariable String userDetailId, HttpServletRequest request, RedirectAttributes ra) {
		amUserDetailManager.delete(userDetailId);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/amUserDetail"; 
	}

	@RequestMapping(value = "/deleteAmUserDetails", method = RequestMethod.POST)
	public String deleteAmUserDetails(HttpServletRequest request, RedirectAttributes ra) {
		amUserDetailManager.batchDelete(request.getParameter("userDetailIds"));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/amUserDetail"; 
	}	
	@Resource(name="amUserDetailManagerImpl")
	private AmUserDetailManager amUserDetailManager;
}