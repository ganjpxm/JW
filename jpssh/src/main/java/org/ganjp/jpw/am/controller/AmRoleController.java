/**
 * $Id: AmRoleController.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.am.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.ganjp.jpw.am.model.AmRole;
import org.ganjp.jpw.am.service.AmRoleManager;
import org.ganjp.jpw.am.service.AmUserRoleManager;
import org.ganjp.jpw.bm.model.BmMenu;
import org.ganjp.jpw.core.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <p>AmRoleController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
public class AmRoleController extends BaseController {
	@RequestMapping(value = "/amRole", method = RequestMethod.GET)
	public String toAmRoleJqmList(HttpServletRequest request) {
		List<AmRole> amRoles = amRoleManager.getAmRoles();
		request.setAttribute("amRoles", amRoles);
		super.setLeftMenu(request, BmMenu.MENU_TYPE_MANAGEMENT_CONSOLE);
        return "am/amRole/amRoleJqmList";
	}
	
	@RequestMapping(value = "/amRoleJqmAdd", method = RequestMethod.GET)
	public String toAmRoleJqmAdd(HttpServletRequest request) {
        return "am/amRole/amRoleJqmAdd";
	}
	
	@RequestMapping(value = "/amRoleJqmEdit/{roleId}", method = RequestMethod.GET)
	public String toAmRoleJqmEdit(@PathVariable String roleId, HttpServletRequest request) {
		AmRole amRole = amRoleManager.getAmRoleById(roleId);
		request.setAttribute("amRole",amRole);
        return "am/amRole/amRoleJqmEdit";
	}
	
	@RequestMapping(value = "/saveAmRole", method = RequestMethod.POST)
	public String saveAmRole(HttpServletRequest request, RedirectAttributes ra) {
		AmRole amRole = new AmRole();
		setValue(request, amRole);
		amRoleManager.save(amRole);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/amRole";
	}
	
	@RequestMapping(value = "/updateAmRole", method = RequestMethod.POST)
	public String updateAmRole(HttpServletRequest request, RedirectAttributes ra) {
		String roleId = request.getParameter("roleId");
		AmRole amRole = amRoleManager.getAmRoleById(roleId);
		setValue(request, amRole);
		amRoleManager.update(amRole);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/amRole"; 
	}
	
	@RequestMapping(value = "/deleteAmRole/{roleId}", method = RequestMethod.GET)
	public String deleteAmRole(@PathVariable String roleId, HttpServletRequest request, RedirectAttributes ra) {
		amUserRoleManager.batchDeleteRelateRoles(roleId);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/amRole"; 
	}

	@RequestMapping(value = "/deleteAmRoles", method = RequestMethod.POST)
	public String deleteAmRoles(HttpServletRequest request, RedirectAttributes ra) {
		amUserRoleManager.batchDeleteRelateRoles(request.getParameter("roleIds"));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/amRole"; 
	}	
	
	@Resource(name="amRoleManagerImpl")
	private AmRoleManager amRoleManager;
	
	@Resource(name="amUserRoleManagerImpl")
	private AmUserRoleManager amUserRoleManager;
}