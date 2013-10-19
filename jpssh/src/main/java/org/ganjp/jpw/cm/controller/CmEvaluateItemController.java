/**
 * $Id: CmEvaluateItemController.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.ganjp.jpw.cm.model.CmEvaluateItem;
import org.ganjp.jpw.cm.service.CmEvaluateItemManager;
import org.ganjp.jpw.core.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <p>CmEvaluateItemController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
public class CmEvaluateItemController extends BaseController {
	@RequestMapping(value = "/cmEvaluateItem", method = RequestMethod.GET)
	public String toCmEvaluateItemMobileList(HttpServletRequest request) {
		List<CmEvaluateItem> cmEvaluateItems = cmEvaluateItemManager.getCmEvaluateItems();
		request.setAttribute("cmEvaluateItems", cmEvaluateItems);
        return "cm/cmEvaluateItem/cmEvaluateItemMobileList";
	}
	
	@RequestMapping(value = "/cmEvaluateItemAddDialog/{menuId}", method = RequestMethod.GET)
	public String toCmEvaluateItemAddDialog(@PathVariable String menuId, HttpServletRequest request) {
		request.setAttribute("menuId", menuId);
        return "cm/cmEvaluateItem/cmEvaluateItemAddDialog";
	}
	
	@RequestMapping(value = "/cmEvaluateItemEditDialog/{menuId}", method = RequestMethod.GET)
	public String toCmEvaluateItemEditDialog(@PathVariable String menuId, HttpServletRequest request) {
		String evaluateItemId = request.getParameter("evaluateItemId");
		CmEvaluateItem cmEvaluateItem = cmEvaluateItemManager.getCmEvaluateItemById(evaluateItemId);
		request.setAttribute("cmEvaluateItem",cmEvaluateItem);
		request.setAttribute("menuId", menuId);
        return "cm/cmEvaluateItem/cmEvaluateItemEditDialog";
	}
	
	@RequestMapping(value = "/saveCmEvaluateItem", method = RequestMethod.POST)
	public String saveCmEvaluateItem(HttpServletRequest request, RedirectAttributes ra) {
		CmEvaluateItem cmEvaluateItem = new CmEvaluateItem();
		setValue(request, cmEvaluateItem);
		cmEvaluateItemManager.save(cmEvaluateItem);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmEvaluateItem";
	}
	
	@RequestMapping(value = "/updateCmEvaluateItem", method = RequestMethod.POST)
	public String updateCmEvaluateItem(HttpServletRequest request, RedirectAttributes ra) {
		String evaluateItemId = request.getParameter("evaluateItemId");
		CmEvaluateItem cmEvaluateItem = cmEvaluateItemManager.getCmEvaluateItemById(evaluateItemId);
		setValue(request, cmEvaluateItem);
		cmEvaluateItemManager.update(cmEvaluateItem);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmEvaluateItem"; 
	}
	
	@RequestMapping(value = "/deleteCmEvaluateItem", method = RequestMethod.GET)
	public String deleteCmEvaluateItem(HttpServletRequest request, RedirectAttributes ra) {
		cmEvaluateItemManager.delete(request.getParameter("evaluateItemId"));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmEvaluateItem"; 
	}
	
	@Resource(name="cmEvaluateItemManagerImpl")
	private CmEvaluateItemManager cmEvaluateItemManager;
}