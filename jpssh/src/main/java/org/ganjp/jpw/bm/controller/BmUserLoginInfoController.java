/**
 * $Id: BmUserLoginInfoController.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.bm.controller;

import org.ganjp.jpw.bm.model.BmUserLoginInfo;
import org.ganjp.jpw.bm.service.BmUserLoginInfoManager;
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
 * <p>BmUserLoginInfoController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
public class BmUserLoginInfoController extends BaseController {
	@RequestMapping(value = "/bmUserLoginInfo", method = RequestMethod.GET)
	public String toBmUserLoginInfoJqmList(HttpServletRequest request) {
		List<BmUserLoginInfo> bmUserLoginInfos = bmUserLoginInfoManager.getBmUserLoginInfos();
		request.setAttribute("bmUserLoginInfos", bmUserLoginInfos);
		super.setLeftMenu(request, BmMenu.MENU_TYPE_MANAGEMENT_CONSOLE);
        return "bm/bmUserLoginInfo/bmUserLoginInfoJqmList";
	}
	
	@RequestMapping(value = "/bmUserLoginInfoJqmAdd", method = RequestMethod.GET)
	public String toBmUserLoginInfoJqmAdd(HttpServletRequest request) {
        return "bm/bmUserLoginInfo/bmUserLoginInfoJqmAdd";
	}
	
	@RequestMapping(value = "/bmUserLoginInfoJqmEdit/{userLoginInfoId}", method = RequestMethod.GET)
	public String toBmUserLoginInfoJqmEdit(@PathVariable String userLoginInfoId, HttpServletRequest request) {
		BmUserLoginInfo bmUserLoginInfo = bmUserLoginInfoManager.getBmUserLoginInfoById(userLoginInfoId);
		request.setAttribute("bmUserLoginInfo",bmUserLoginInfo);
        return "bm/bmUserLoginInfo/bmUserLoginInfoJqmEdit";
	}
	
	@RequestMapping(value = "/saveBmUserLoginInfo", method = RequestMethod.POST)
	public String saveBmUserLoginInfo(HttpServletRequest request, RedirectAttributes ra) {
		BmUserLoginInfo bmUserLoginInfo = new BmUserLoginInfo();
		setValue(request, bmUserLoginInfo);
		bmUserLoginInfoManager.save(bmUserLoginInfo);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/bmUserLoginInfo";
	}
	
	@RequestMapping(value = "/updateBmUserLoginInfo", method = RequestMethod.POST)
	public String updateBmUserLoginInfo(HttpServletRequest request, RedirectAttributes ra) {
		String userLoginInfoId = request.getParameter("userLoginInfoId");
		BmUserLoginInfo bmUserLoginInfo = bmUserLoginInfoManager.getBmUserLoginInfoById(userLoginInfoId);
		setValue(request, bmUserLoginInfo);
		bmUserLoginInfoManager.update(bmUserLoginInfo);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/bmUserLoginInfo"; 
	}
	
	@RequestMapping(value = "/deleteBmUserLoginInfo/{userLoginInfoId}", method = RequestMethod.GET)
	public String deleteBmUserLoginInfo(@PathVariable String userLoginInfoId, HttpServletRequest request, RedirectAttributes ra) {
		bmUserLoginInfoManager.delete(userLoginInfoId);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/bmUserLoginInfo"; 
	}

	@RequestMapping(value = "/deleteBmUserLoginInfos", method = RequestMethod.POST)
	public String deleteBmUserLoginInfos(HttpServletRequest request, RedirectAttributes ra) {
		bmUserLoginInfoManager.batchDelete(request.getParameter("userLoginInfoIds"));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/bmUserLoginInfo"; 
	}	
	@Resource(name="bmUserLoginInfoManagerImpl")
	private BmUserLoginInfoManager bmUserLoginInfoManager;
}