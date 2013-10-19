/**
 * $Id: CmUserEvaluateTableResultController.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.ganjp.jpw.bm.model.BmMenu;
import org.ganjp.jpw.cm.model.CmEvaluateTable;
import org.ganjp.jpw.cm.model.CmUserEvaluateTableResult;
import org.ganjp.jpw.cm.service.CmEvaluateItemManager;
import org.ganjp.jpw.cm.service.CmEvaluateTableManager;
import org.ganjp.jpw.cm.service.CmUserEvaluateTableResultManager;
import org.ganjp.jpw.core.util.DateUtil;
import org.ganjp.jpw.core.util.StringUtil;
import org.ganjp.jpw.core.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <p>CmUserEvaluateTableResultController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
public class CmUserEvaluateTableResultController extends BaseController {
	@RequestMapping(value = "/cmUserEvaluateTableResult", method = RequestMethod.GET)
	public String toCmUserEvaluateTableResultJqmList(HttpServletRequest request) {
		List<CmEvaluateTable> cmEvaluateTables = cmEvaluateTableManager.getCmEvaluateTablesByRoleIds(super.getLoginUserRoleIds(request));
		String evaluateTableId = request.getParameter("evaluateTableId");
		if (StringUtil.isEmpty(evaluateTableId)) {
			evaluateTableId = cmEvaluateTables.get(0).getEvaluateTableId();
		}
		List<CmUserEvaluateTableResult> cmUserEvaluateTableResults = cmUserEvaluateTableResultManager.getCmUserEvaluateTableResultsByField("evaluateTableId",evaluateTableId);
		request.setAttribute("cmUserEvaluateTableResults", cmUserEvaluateTableResults);
		super.setLeftMenu(request, BmMenu.MENU_TYPE_MANAGEMENT_CONSOLE);
		
		request.setAttribute("cmEvaluateTables", cmEvaluateTables);
		request.setAttribute("evaluateTableId", evaluateTableId);
        return "cm/cmUserEvaluateTableResult/cmUserEvaluateTableResultJqmList";
	}
	
	@RequestMapping(value = "/cmUserEvaluateTableResultJqmAdd", method = RequestMethod.GET)
	public String toCmUserEvaluateTableResultJqmAdd(HttpServletRequest request) {
        return "cm/cmUserEvaluateTableResult/cmUserEvaluateTableResultJqmAdd";
	}
	
	@RequestMapping(value = "/cmUserEvaluateTableResultJqmEdit/{userEvaluateTableResultId}", method = RequestMethod.GET)
	public String toCmUserEvaluateTableResultJqmEdit(@PathVariable String userEvaluateTableResultId, HttpServletRequest request) {
		CmUserEvaluateTableResult cmUserEvaluateTableResult = cmUserEvaluateTableResultManager.getCmUserEvaluateTableResultById(userEvaluateTableResultId);
		request.setAttribute("cmUserEvaluateTableResult",cmUserEvaluateTableResult);
        return "cm/cmUserEvaluateTableResult/cmUserEvaluateTableResultJqmEdit";
	}
	
	@RequestMapping(value = "/saveCmUserEvaluateTableResult", method = RequestMethod.POST)
	public String saveCmUserEvaluateTableResult(HttpServletRequest request, RedirectAttributes ra) {
		String userId = super.getLoginUserId(request);
		String evaluateTableId = request.getParameter("evaluateTableId");
		if (StringUtil.isNotEmpty(userId) && StringUtil.isNotEmpty(evaluateTableId)) {
			if (cmUserEvaluateTableResultManager.getUserEvaluateResultMap(userId, evaluateTableId)!=null) {
				ra.addAttribute("isSubmitAgain", "yes");
				return "redirect:" + request.getParameter("from");
			}
		}
		
		CmUserEvaluateTableResult cmUserEvaluateTableResult = new CmUserEvaluateTableResult();
		setValue(request, cmUserEvaluateTableResult);
		cmUserEvaluateTableResult.setEvaluateDate(DateUtil.getNowTimstamp());
		
		List<String> evaluateItemIds = cmEvaluateItemManager.getEvaluateItemIds(evaluateTableId);
		
		StringBuffer resultSb = new StringBuffer();
		int size = evaluateItemIds.size();
		BigDecimal score = new BigDecimal("0");
		for (int i = 0; i < size ; i++) {
			String evaluateItemId = evaluateItemIds.get(i);
			String indexScore = request.getParameter(evaluateItemId);
			score = score.add(new BigDecimal((indexScore.split(":")[1])));
			resultSb.append(evaluateItemId).append(":").append(indexScore).append(";");
		}
		score = score.divide(new BigDecimal(size), 2, BigDecimal.ROUND_FLOOR);
		cmUserEvaluateTableResult.setScore(score);
		cmUserEvaluateTableResult.setResult(resultSb.toString());
		
		cmUserEvaluateTableResult.setUserId(userId);
		cmUserEvaluateTableResult.setUserName(super.getLoginUserName(request));
		cmUserEvaluateTableResultManager.save(cmUserEvaluateTableResult);
		
		ra.addAttribute("evaluateTableId", evaluateTableId);
		ra.addAttribute("userEvaluateTableResultId", cmUserEvaluateTableResult.getUserEvaluateTableResultId());
		
		return "redirect:" + request.getParameter("from");
	}
	
	@RequestMapping(value = "/updateCmUserEvaluateTableResult", method = RequestMethod.POST)
	public String updateCmUserEvaluateTableResult(HttpServletRequest request, RedirectAttributes ra) {
		String userEvaluateTableResultId = request.getParameter("userEvaluateTableResultId");
		CmUserEvaluateTableResult cmUserEvaluateTableResult = cmUserEvaluateTableResultManager.getCmUserEvaluateTableResultById(userEvaluateTableResultId);
		setValue(request, cmUserEvaluateTableResult);
		cmUserEvaluateTableResultManager.update(cmUserEvaluateTableResult);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmUserEvaluateTableResult"; 
	}
	
	@RequestMapping(value = "/deleteCmUserEvaluateTableResult/{userEvaluateTableResultId}", method = RequestMethod.GET)
	public String deleteCmUserEvaluateTableResult(@PathVariable String userEvaluateTableResultId, HttpServletRequest request, RedirectAttributes ra) {
		cmUserEvaluateTableResultManager.batchDelete(userEvaluateTableResultId);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmUserEvaluateTableResult"; 
	}

	@RequestMapping(value = "/deleteCmUserEvaluateTableResult/{userId}/{evaluateTableId}", method = RequestMethod.GET)
	public String deleteCmUserEvaluateTableResult(@PathVariable String userId, @PathVariable String evaluateTableId, HttpServletRequest request, RedirectAttributes ra) {
		cmUserEvaluateTableResultManager.delete(userId, evaluateTableId);
		ra.addAttribute("evaluateTableId",evaluateTableId);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmUserEvaluateTableResult"; 
	}
	
	@RequestMapping(value = "/deleteCmUserEvaluateTableResults", method = RequestMethod.POST)
	public String deleteCmUserEvaluateTableResults(HttpServletRequest request, RedirectAttributes ra) {
		String userEvaluateTableResultIds = request.getParameter("userEvaluateTableResultIds");
		if (StringUtil.isNotEmpty(userEvaluateTableResultIds)) {
			cmUserEvaluateTableResultManager.batchDelete(userEvaluateTableResultIds);
			ra.addAttribute("userEvaluateTableResultIds",userEvaluateTableResultIds);
		}
		ra.addAttribute("evaluateTableId",request.getParameter("evaluateTableId"));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmUserEvaluateTableResult"; 
	}	
	
	@Resource(name="cmUserEvaluateTableResultManagerImpl")
	private CmUserEvaluateTableResultManager cmUserEvaluateTableResultManager;

	@Resource(name="cmEvaluateItemManagerImpl")
	private CmEvaluateItemManager cmEvaluateItemManager;
	
	@Resource(name="cmEvaluateTableManagerImpl")
	private CmEvaluateTableManager cmEvaluateTableManager;
}