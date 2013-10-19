/**
 * $Id: CmEvaluateTableController.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.ganjp.jpw.am.service.AmRoleManager;
import org.ganjp.jpw.bm.model.BmMenu;
import org.ganjp.jpw.cm.model.CmEvaluateItem;
import org.ganjp.jpw.cm.model.CmEvaluateTable;
import org.ganjp.jpw.cm.service.CmEvaluateItemManager;
import org.ganjp.jpw.cm.service.CmEvaluateTableManager;
import org.ganjp.jpw.core.util.StringUtil;
import org.ganjp.jpw.core.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <p>CmEvaluateTableController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
public class CmEvaluateTableController extends BaseController {
	@RequestMapping(value = "/cmEvaluateTable", method = RequestMethod.GET)
	public String toCmEvaluateTableJqmList(HttpServletRequest request) {
		List<CmEvaluateTable> cmEvaluateTables = cmEvaluateTableManager.getCmEvaluateTables();
		request.setAttribute("cmEvaluateTables", cmEvaluateTables);
		super.setLeftMenu(request, BmMenu.MENU_TYPE_MANAGEMENT_CONSOLE);
        return "cm/cmEvaluateTable/cmEvaluateTableJqmList";
	}
	
	@RequestMapping(value = "/cmEvaluateTableJqmAdd", method = RequestMethod.GET)
	public String toCmEvaluateTableJqmAdd(HttpServletRequest request) {
		request.setAttribute("roleMaps", amRoleManager.getRolesForJqmCheck());
        return "cm/cmEvaluateTable/cmEvaluateTableJqmAdd";
	}
	
	@RequestMapping(value = "/cmEvaluateTableJqmEdit/{evaluateTableId}", method = RequestMethod.GET)
	public String toCmEvaluateTableJqmEdit(@PathVariable String evaluateTableId, HttpServletRequest request) {
		request.setAttribute("roleMaps", amRoleManager.getRolesForJqmCheck());
		
		CmEvaluateTable cmEvaluateTable = cmEvaluateTableManager.getCmEvaluateTableById(evaluateTableId);
		request.setAttribute("cmEvaluateTable",cmEvaluateTable);
		
		List<Map<String,String>> evaluateItems = cmEvaluateItemManager.getEvaluateTableItemMaps(evaluateTableId);
		request.setAttribute("evaluateItems", evaluateItems);
		request.setAttribute("evaluateItemsSize", evaluateItems.size());
        return "cm/cmEvaluateTable/cmEvaluateTableJqmEdit";
	}
	
	@RequestMapping(value = "/cmEvaluateTableJqmDetail/{evaluateTableId}", method = RequestMethod.GET)
	public String toCmEvaluateTableJqmDetail(@PathVariable String evaluateTableId, HttpServletRequest request) {
		request.setAttribute("evaluateItemHtml", cmEvaluateItemManager.getEvaluateTableItemHtml(evaluateTableId));
		CmEvaluateTable cmEvaluateTable = cmEvaluateTableManager.getCmEvaluateTableById(evaluateTableId);
		request.setAttribute("cmEvaluateTable",cmEvaluateTable);
        return "cm/cmEvaluateTable/cmEvaluateTableJqmDetail";
	}

	
	
	@RequestMapping(value = "/saveCmEvaluateTable", method = RequestMethod.POST)
	public String saveCmEvaluateTable(HttpServletRequest request, RedirectAttributes ra) {
		CmEvaluateTable cmEvaluateTable = new CmEvaluateTable();
		setValue(request, cmEvaluateTable);
		
		List<CmEvaluateItem> cmEvaluateItems = new ArrayList<CmEvaluateItem>();
		int itemIndex = Integer.parseInt(StringUtil.isEmpty(request.getParameter("itemIndex"))?"0":request.getParameter("itemIndex"));
		for (int i = 1; i <= itemIndex; i++) {
			String itemName = request.getParameter("itemName" + i);
			String itemOption = request.getParameter("itemOption" + i);
			if ("delete".equals(itemName)) {
				continue;
			}
			cmEvaluateItems.add(new CmEvaluateItem(cmEvaluateTable.getEvaluateTableId(), itemName, itemOption, i));
		}
		cmEvaluateTableManager.saveCmEvaluateTableAndItem(cmEvaluateTable, cmEvaluateItems);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmEvaluateTable";
	}
	
	@RequestMapping(value = "/updateCmEvaluateTable", method = RequestMethod.POST)
	public String updateCmEvaluateTable(HttpServletRequest request, RedirectAttributes ra) {
		String evaluateTableId = request.getParameter("evaluateTableId");
		CmEvaluateTable cmEvaluateTable = cmEvaluateTableManager.getCmEvaluateTableById(evaluateTableId);
		setValue(request, cmEvaluateTable);

		List<CmEvaluateItem> cmEvaluateItems = new ArrayList<CmEvaluateItem>();
		int itemIndex = Integer.parseInt(StringUtil.isEmpty(request.getParameter("itemIndex"))?"0":request.getParameter("itemIndex"));
		for (int i = 1; i <= itemIndex; i++) {
			String itemName = request.getParameter("itemName" + i);
			String itemOption = request.getParameter("itemOption" + i);
			if ("delete".equals(itemName)) {
				continue;
			}
			cmEvaluateItems.add(new CmEvaluateItem(cmEvaluateTable.getEvaluateTableId(), itemName, itemOption, i));
		}
		cmEvaluateTableManager.updateCmEvaluateTableAndItem(cmEvaluateTable, cmEvaluateItems);

		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmEvaluateTable"; 
	}
	
	@RequestMapping(value = "/deleteCmEvaluateTable/{evaluateTableId}", method = RequestMethod.GET)
	public String deleteCmEvaluateTable(@PathVariable String evaluateTableId, HttpServletRequest request, RedirectAttributes ra) {
		cmEvaluateTableManager.batchDelete(evaluateTableId);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmEvaluateTable"; 
	}

	@RequestMapping(value = "/deleteCmEvaluateTables", method = RequestMethod.POST)
	public String deleteCmEvaluateTables(HttpServletRequest request, RedirectAttributes ra) {
		cmEvaluateTableManager.batchDelete(request.getParameter("evaluateTableIds"));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmEvaluateTable"; 
	}	
	
	@Resource(name="cmEvaluateTableManagerImpl")
	private CmEvaluateTableManager cmEvaluateTableManager;
	
	@Resource(name="cmEvaluateItemManagerImpl")
	private CmEvaluateItemManager cmEvaluateItemManager;
	
	@Resource(name="amRoleManagerImpl")
	private AmRoleManager amRoleManager;
}