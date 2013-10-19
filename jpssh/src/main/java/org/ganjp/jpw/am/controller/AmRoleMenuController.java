/**
 * $Id: AmRoleMenuController.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.am.controller;

import org.ganjp.jpw.am.model.AmRoleMenu;
import org.ganjp.jpw.am.service.AmRoleMenuManager;
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
 * <p>AmRoleMenuController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
public class AmRoleMenuController extends BaseController {
	@RequestMapping(value = "/amRoleMenu", method = RequestMethod.GET)
	public String toAmRoleMenuJqmList(HttpServletRequest request) {
		List<AmRoleMenu> amRoleMenus = amRoleMenuManager.getAmRoleMenus();
		request.setAttribute("amRoleMenus", amRoleMenus);
		super.setLeftMenu(request, BmMenu.MENU_TYPE_MANAGEMENT_CONSOLE);
        return "am/amRoleMenu/amRoleMenuJqmList";
	}
	
	@RequestMapping(value = "/amRoleMenuJqmAdd", method = RequestMethod.GET)
	public String toAmRoleMenuJqmAdd(HttpServletRequest request) {
        return "am/amRoleMenu/amRoleMenuJqmAdd";
	}
	
	@RequestMapping(value = "/amRoleMenuJqmEdit/{roleMenuId}", method = RequestMethod.GET)
	public String toAmRoleMenuJqmEdit(@PathVariable String roleMenuId, HttpServletRequest request) {
		AmRoleMenu amRoleMenu = amRoleMenuManager.getAmRoleMenuById(roleMenuId);
		request.setAttribute("amRoleMenu",amRoleMenu);
        return "am/amRoleMenu/amRoleMenuJqmEdit";
	}
	
	@RequestMapping(value = "/saveAmRoleMenu", method = RequestMethod.POST)
	public String saveAmRoleMenu(HttpServletRequest request, RedirectAttributes ra) {
		AmRoleMenu amRoleMenu = new AmRoleMenu();
		setValue(request, amRoleMenu);
		amRoleMenuManager.save(amRoleMenu);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/amRoleMenu";
	}
	
	@RequestMapping(value = "/updateAmRoleMenu", method = RequestMethod.POST)
	public String updateAmRoleMenu(HttpServletRequest request, RedirectAttributes ra) {
		String roleMenuId = request.getParameter("roleMenuId");
		AmRoleMenu amRoleMenu = amRoleMenuManager.getAmRoleMenuById(roleMenuId);
		setValue(request, amRoleMenu);
		amRoleMenuManager.update(amRoleMenu);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/amRoleMenu"; 
	}
	
	@RequestMapping(value = "/deleteAmRoleMenu/{roleMenuId}", method = RequestMethod.GET)
	public String deleteAmRoleMenu(@PathVariable String roleMenuId, HttpServletRequest request, RedirectAttributes ra) {
		amRoleMenuManager.delete(roleMenuId);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/amRoleMenu"; 
	}

	@RequestMapping(value = "/deleteAmRoleMenus", method = RequestMethod.POST)
	public String deleteAmRoleMenus(HttpServletRequest request, RedirectAttributes ra) {
		amRoleMenuManager.batchDelete(request.getParameter("roleMenuIds"));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/amRoleMenu"; 
	}	
	@Resource(name="amRoleMenuManagerImpl")
	private AmRoleMenuManager amRoleMenuManager;
}