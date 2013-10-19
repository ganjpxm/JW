/**
 * $Id: BmMenuController.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.bm.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ganjp.jpw.bm.model.BmMenu;
import org.ganjp.jpw.core.Config;
import org.ganjp.jpw.core.util.FileUtil;
import org.ganjp.jpw.core.util.StringUtil;
import org.ganjp.jpw.core.web.controller.BaseController;
import org.ganjp.jpw.core.web.util.ServletUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <p>BmMenuController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
public class BmMenuController extends BaseController {
	@RequestMapping(value = "/bmMenu", method = RequestMethod.GET)
	public String toBmMenuJqmList(HttpServletRequest request) {
		String menuHtml = bmMenuManager.getAllMenuHtml(super.getBasePath(request), 
				ServletUtil.getLanguage(request), ServletUtil.getJqmTheme(request));
		request.setAttribute("menuHtml", menuHtml);
		super.setLeftMenu(request, BmMenu.MENU_TYPE_MANAGEMENT_CONSOLE);
        return "bm/bmMenu/bmMenuJqmList";
	}
	
	@RequestMapping(value = "/bmMenuJqmAdd", method = RequestMethod.GET)
	public String toBmMenuJqmAdd(HttpServletRequest request) {
		request.setAttribute("menuCategoryId", request.getParameter("menuCategoryId"));
		request.setAttribute("menuPid", request.getParameter("menuPid"));
		request.setAttribute("displayLevel", request.getParameter("displayLevel"));
		request.setAttribute("menuCd", request.getParameter("menuCd"));
		request.setAttribute("displayNo", request.getParameter("displayNo"));
		request.setAttribute("addMenuId", request.getParameter("addMenuId"));
		request.setAttribute("roleMaps", super.getRoleMapsForJqmCheck(request));
		request.setAttribute("menuId",request.getParameter("menuId"));
		request.setAttribute("from",request.getParameter("from"));
		request.setAttribute("simpleDisplay",request.getParameter("simpleDisplay"));
		
        return "bm/bmMenu/bmMenuJqmAdd";
	}
	
	@RequestMapping(value = "/bmMenuJqmEdit/{menuId}", method = RequestMethod.GET)
	public String toBmMenuJqmEdit(@PathVariable String menuId, HttpServletRequest request) {
		BmMenu bmMenu = bmMenuManager.getBmMenuById(menuId);
		request.setAttribute("bmMenu",bmMenu);
		request.setAttribute("activeMenuId",request.getParameter("menuId"));
		request.setAttribute("roleMaps", super.getRoleMapsForJqmCheck(request));
		request.setAttribute("from", request.getParameter("from"));
		request.setAttribute("addMenuId", request.getParameter("addMenuId"));
        return "bm/bmMenu/bmMenuJqmEdit";
	}
	
	@RequestMapping(value = "/saveBmMenu", method = RequestMethod.POST)
	public String saveBmMenu(HttpServletRequest request, RedirectAttributes ra) {
		BmMenu bmMenu = new BmMenu();
		setValue(request, bmMenu);
		String addMenuId = request.getParameter("addMenuId");
		if (StringUtil.isNotEmpty(bmMenu.getUrl()) && "1".equals(addMenuId)) {
			if (bmMenu.getUrl().indexOf("?")==-1) {
				bmMenu.setUrl(bmMenu.getUrl() + "?menuId=" + bmMenu.getMenuId());
			} else {
				bmMenu.setUrl(bmMenu.getUrl() + "&menuId=" + bmMenu.getMenuId());
			}
		}
		bmMenuManager.save(bmMenu);
		Map<String,String> map = new HashMap<String,String>();
		map.put("menuId", bmMenu.getMenuId());
		map.put("menuPid", bmMenu.getMenuPid());
		map.put("menuName", bmMenu.getMenuName());
		map.put("menuCd", bmMenu.getMenuCd());
		map.put("url", bmMenu.getUrl());
		map.put("imageUrl", bmMenu.getImageUrl());
		map.put("menuCategoryId", bmMenu.getMenuCategoryId());
		List menuMaps = Config.getMenuMaps(bmMenu.getMenuCategoryId(),ServletUtil.getLanguage(request));
		if (menuMaps!=null) {
		    menuMaps.add(map);
		}
		String from = request.getParameter("from");
		if (StringUtil.isNotEmpty(from)) {
			return "redirect:/" + from;
		} else {
			ra.addAttribute("menuId", request.getParameter("activeMenuId"));
			return "redirect:/bmMenu";
		}
	}
	
	@RequestMapping(value = "/updateBmMenu", method = RequestMethod.POST)
	public String updateBmMenu(HttpServletRequest request, RedirectAttributes ra) {
		String menuId = request.getParameter("menuId");
		BmMenu bmMenu = bmMenuManager.getBmMenuById(menuId);
		setValue(request, bmMenu);
		String addMenuId = request.getParameter("addMenuId");
		if (StringUtil.isNotEmpty(bmMenu.getUrl()) && "1".equals(addMenuId)) {
			if (bmMenu.getUrl().indexOf("?")==-1) {
				bmMenu.setUrl(bmMenu.getUrl() + "?menuId=" + bmMenu.getMenuId());
			} else if (bmMenu.getUrl().indexOf("menuId")==-1) {
				bmMenu.setUrl(bmMenu.getUrl() + "&menuId=" + bmMenu.getMenuId());
			}
		}
		bmMenuManager.update(bmMenu);
		
		List<Map> menuMaps = Config.getMenuMaps(bmMenu.getMenuCategoryId(),ServletUtil.getLanguage(request));
		for(Map map : menuMaps){
			String cashMenuId = (String)map.get("menuId");
			if (cashMenuId.equals(bmMenu.getMenuId())) {
				map.put("menuId", bmMenu.getMenuId());
				map.put("menuPid", bmMenu.getMenuPid());
				map.put("menuName", bmMenu.getMenuName());
				map.put("url", bmMenu.getUrl());
				map.put("imageUrl", bmMenu.getImageUrl());
				break;
			}
	    }
		
		String from = request.getParameter("from");
		if (StringUtil.isNotEmpty(from)) {
			return "redirect:/" + from;
		} else {
			ra.addAttribute("menuId", request.getParameter("activeMenuId"));
			return "redirect:/bmMenu";
		}
		
	}
	
	@RequestMapping(value = "/deleteBmMenu/{menuId}", method = RequestMethod.GET)
	public String deleteBmMenu(@PathVariable String menuId, HttpServletRequest request, RedirectAttributes ra) {
		BmMenu bmMenu = bmMenuManager.getBmMenuById(menuId);
		bmMenuManager.delete(menuId);
		List<Map> menuMaps = Config.getMenuMaps(bmMenu.getMenuCategoryId(),ServletUtil.getLanguage(request));
		for(Map map : menuMaps){
			String cashMenuId = (String)map.get("menuId");
			if (cashMenuId.equals(bmMenu.getMenuId())) {
				menuMaps.remove(map);
				break;
			}
	    }
		File menuImageFile = new File(FileUtil.getPath(request.getRealPath(""), bmMenu.getImageUrl()));
		if (StringUtil.isNotEmpty(bmMenu.getImageUrl()) && menuImageFile.exists()) {
			FileUtil.delete(menuImageFile);
		}
		
		String from = request.getParameter("from");
		if (StringUtil.isNotEmpty(from)) {
			return "redirect:/" + from;
		} else {
			ra.addAttribute("menuId", request.getParameter("menuId"));
			return "redirect:/bmMenu";
		}
	}

	@RequestMapping(value = "/deleteBmMenus", method = RequestMethod.POST)
	public String deleteBmMenus(HttpServletRequest request, RedirectAttributes ra) {
		bmMenuManager.batchDelete(request.getParameter("menuIds"));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/bmMenu"; 
	}	
	
}