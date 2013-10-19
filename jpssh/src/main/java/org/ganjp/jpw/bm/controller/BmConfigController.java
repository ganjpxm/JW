/**
 * $Id: BmConfigController.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.bm.controller;

import org.ganjp.jpw.bm.model.BmConfig;
import org.ganjp.jpw.bm.model.BmMenu;
import org.ganjp.jpw.bm.service.BmConfigManager;
import org.ganjp.jpw.core.web.controller.BaseController;
import org.ganjp.jpw.core.web.util.ServletUtil;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <p>BmConfigController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
public class BmConfigController extends BaseController {
	@RequestMapping(value = "/bmConfig", method = RequestMethod.GET)
	public String toBmConfigJqmList(HttpServletRequest request) {
		List<BmConfig> bmConfigs = bmConfigManager.getBmConfigsByField("lang", ServletUtil.getLanguage(request));
		request.setAttribute("bmConfigs", bmConfigs);
		super.setLeftMenu(request, BmMenu.MENU_TYPE_MANAGEMENT_CONSOLE);
        return "bm/bmConfig/bmConfigJqmList";
	}
	
	@RequestMapping(value = "/bmConfigJqmAdd", method = RequestMethod.GET)
	public String toBmConfigJqmAdd(HttpServletRequest request) {
        return "bm/bmConfig/bmConfigJqmAdd";
	}
	
	@RequestMapping(value = "/bmConfigJqmEdit/{configId}", method = RequestMethod.GET)
	public String toBmConfigJqmEdit(@PathVariable String configId, HttpServletRequest request) {
		BmConfig bmConfig = bmConfigManager.getBmConfigById(configId);
		request.setAttribute("bmConfig",bmConfig);
        return "bm/bmConfig/bmConfigJqmEdit";
	}
	
	@RequestMapping(value = "/saveBmConfig", method = RequestMethod.POST)
	public String saveBmConfig(HttpServletRequest request, RedirectAttributes ra) {
		BmConfig bmConfig = new BmConfig();
		setValue(request, bmConfig);
		bmConfigManager.save(bmConfig);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		request.getServletContext().setAttribute(bmConfig.getConfigCd(), bmConfig.getConfigValue());
		return "redirect:/bmConfig";
	}
	
	@RequestMapping(value = "/updateBmConfig", method = RequestMethod.POST)
	public String updateBmConfig(HttpServletRequest request, RedirectAttributes ra) {
		String configId = request.getParameter("configId");
		BmConfig bmConfig = bmConfigManager.getBmConfigById(configId);
		setValue(request, bmConfig);
		bmConfigManager.update(bmConfig);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		request.getServletContext().setAttribute(bmConfig.getConfigCd(), bmConfig.getConfigValue());
		return "redirect:/bmConfig"; 
	}
	
	@RequestMapping(value = "/deleteBmConfig/{configId}", method = RequestMethod.GET)
	public String deleteBmConfig(@PathVariable String configId, HttpServletRequest request, RedirectAttributes ra) {
		bmConfigManager.delete(configId);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/bmConfig"; 
	}
	
	@RequestMapping(value = "/deleteBmConfigs", method = RequestMethod.POST)
	public String deleteBmConfigs(HttpServletRequest request, RedirectAttributes ra) {
		bmConfigManager.batchDelete(request.getParameter("configIds"));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/bmConfig"; 
	}
	
	@Resource(name="bmConfigManagerImpl")
	private BmConfigManager bmConfigManager;
}