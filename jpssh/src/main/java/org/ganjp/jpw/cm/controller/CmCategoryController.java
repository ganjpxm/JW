/**
 * $Id: CmCategoryController.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.ganjp.jpw.bm.model.BmMenu;
import org.ganjp.jpw.cm.model.CmCategory;
import org.ganjp.jpw.cm.service.CmCategoryManager;
import org.ganjp.jpw.core.util.StringUtil;
import org.ganjp.jpw.core.web.controller.BaseController;
import org.ganjp.jpw.core.web.util.ServletUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <p>CmCategoryController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
public class CmCategoryController extends BaseController {
	@RequestMapping(value = "/cmCategory", method = RequestMethod.GET)
	public String toCmCategoryJqmList(HttpServletRequest request) {
		String categoryHtml = cmCategoryManager.getAllCategoryHtml(ServletUtil.getLanguage(request), ServletUtil.getJqmTheme(request));
		request.setAttribute("categoryHtml", categoryHtml);
		super.setLeftMenu(request, BmMenu.MENU_TYPE_MANAGEMENT_CONSOLE);
        return "cm/cmCategory/cmCategoryJqmList";
	}
	
	@RequestMapping(value = "/cmCategoryJqmAdd", method = RequestMethod.GET)
	public String toCmCategoryJqmAdd(HttpServletRequest request) {
		request.setAttribute("categoryPid", request.getParameter("categoryPid"));
		request.setAttribute("categoryCd", request.getParameter("categoryCd"));
        return "cm/cmCategory/cmCategoryJqmAdd";
	}
	
	@RequestMapping(value = "/cmCategoryJqmEdit/{categoryId}", method = RequestMethod.GET)
	public String toCmCategoryJqmEdit(@PathVariable String categoryId, HttpServletRequest request) {
		CmCategory cmCategory = cmCategoryManager.getCmCategoryById(categoryId);
		request.setAttribute("cmCategory",cmCategory);
        return "cm/cmCategory/cmCategoryJqmEdit";
	}
	
	@RequestMapping(value = "/saveCmCategory", method = RequestMethod.POST)
	public String saveCmCategory(HttpServletRequest request, RedirectAttributes ra) {
		CmCategory cmCategory = new CmCategory();
		setValue(request, cmCategory);
		if (StringUtil.isEmpty(cmCategory.getCategoryPid())) {
			cmCategory.setCategoryPid(null);
		}
		cmCategoryManager.save(cmCategory);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmCategory";
	}
	
	@RequestMapping(value = "/updateCmCategory", method = RequestMethod.POST)
	public String updateCmCategory(HttpServletRequest request, RedirectAttributes ra) {
		String categoryId = request.getParameter("categoryId");
		CmCategory cmCategory = cmCategoryManager.getCmCategoryById(categoryId);
		setValue(request, cmCategory);
		cmCategoryManager.update(cmCategory);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmCategory"; 
	}
	
	@RequestMapping(value = "/deleteCmCategory/{categoryId}", method = RequestMethod.GET)
	public String deleteCmCategory(@PathVariable String categoryId, HttpServletRequest request, RedirectAttributes ra) {
		cmCategoryManager.delete(categoryId);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmCategory"; 
	}

	@RequestMapping(value = "/deleteCmCategorys", method = RequestMethod.POST)
	public String deleteCmCategorys(HttpServletRequest request, RedirectAttributes ra) {
		cmCategoryManager.batchDelete(request.getParameter("categoryIds"));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmCategory"; 
	}
	
	@Resource(name="cmCategoryManagerImpl")
	private CmCategoryManager cmCategoryManager;
}