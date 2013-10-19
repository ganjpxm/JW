/**
 * $Id: BmParamController.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.bm.controller;

import org.ganjp.jpw.bm.model.BmMenu;
import org.ganjp.jpw.bm.model.BmParam;
import org.ganjp.jpw.bm.service.BmParamManager;
import org.ganjp.jpw.core.web.controller.BaseController;
import org.ganjp.jpw.core.web.util.ServletUtil;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>BmParamController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
public class BmParamController extends BaseController {
	@RequestMapping(value = "/bmParam", method = RequestMethod.GET)
	public String toBmParamJqmList(HttpServletRequest request) {
		Map<String,List<BmParam>> paramTypeAndBmParamss = bmParamManager.getParamTypeAndBmParamss(ServletUtil.getLanguage(request));
		request.setAttribute("paramTypeAndBmParamss", paramTypeAndBmParamss);
		super.setLeftMenu(request, BmMenu.MENU_TYPE_MANAGEMENT_CONSOLE);
        return "bm/bmParam/bmParamJqmList";
	}
	
	@RequestMapping(value = "/bmParamJqmAdd", method = RequestMethod.GET)
	public String toBmParamJqmAdd(HttpServletRequest request) {
        return "bm/bmParam/bmParamJqmAdd";
	}
	
	@RequestMapping(value = "/bmParamJqmAdd", method = RequestMethod.POST)
	public String toBmParamJqmAddWithParamType(HttpServletRequest request) {
		request.setAttribute("paramTypeCd", request.getParameter("paramTypeCd"));
		request.setAttribute("paramTypeName", request.getParameter("paramTypeName"));
        return "bm/bmParam/bmParamJqmAdd";
	}
	
	@RequestMapping(value = "/bmParamJqmEdit/{paramId}", method = RequestMethod.GET)
	public String toBmParamJqmEdit(@PathVariable String paramId, HttpServletRequest request) {
		BmParam bmParam = bmParamManager.getBmParamById(paramId);
		request.setAttribute("bmParam",bmParam);
        return "bm/bmParam/bmParamJqmEdit";
	}
	
	@RequestMapping(value = "/saveBmParam", method = RequestMethod.POST)
	public String saveBmParam(HttpServletRequest request, RedirectAttributes ra) {
		BmParam bmParam = new BmParam();
		setValue(request, bmParam);
		bmParamManager.save(bmParam);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/bmParam";
	}
	
	@RequestMapping(value = "/updateBmParam", method = RequestMethod.POST)
	public String updateBmParam(HttpServletRequest request, RedirectAttributes ra) {
		String paramId = request.getParameter("paramId");
		BmParam bmParam = bmParamManager.getBmParamById(paramId);
		setValue(request, bmParam);
		bmParamManager.update(bmParam);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/bmParam"; 
	}
	
	@RequestMapping(value = "/deleteBmParam/{paramId}", method = RequestMethod.GET)
	public String deleteBmParam(@PathVariable String paramId, HttpServletRequest request, RedirectAttributes ra) {
		bmParamManager.delete(paramId);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/bmParam"; 
	}

	@RequestMapping(value = "/deleteBmParams", method = RequestMethod.POST)
	public String deleteBmParams(HttpServletRequest request, RedirectAttributes ra) {
		bmParamManager.batchDelete(request.getParameter("paramIds"));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/bmParam"; 
	}	
	@Resource(name="bmParamManagerImpl")
	private BmParamManager bmParamManager;
}