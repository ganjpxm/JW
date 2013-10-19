/**
 * $Id: AmUserRoleController.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.am.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.ganjp.jpw.am.model.AmRole;
import org.ganjp.jpw.am.model.AmUser;
import org.ganjp.jpw.am.model.AmUserRole;
import org.ganjp.jpw.am.service.AmUserManager;
import org.ganjp.jpw.am.service.AmUserRoleManager;
import org.ganjp.jpw.bm.model.BmConfig;
import org.ganjp.jpw.bm.model.BmMenu;
import org.ganjp.jpw.bm.model.BmUserLoginInfo;
import org.ganjp.jpw.bm.service.BmUserLoginInfoManager;
import org.ganjp.jpw.core.Config;
import org.ganjp.jpw.core.Const;
import org.ganjp.jpw.core.util.EncryptUtil;
import org.ganjp.jpw.core.util.StringUtil;
import org.ganjp.jpw.core.web.controller.BaseController;
import org.ganjp.jpw.core.web.listener.ActiveSessionsListener;
import org.ganjp.jpw.core.web.util.ServletUtil;
import org.ganjp.jpw.core.web.util.UserAgentUtil;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <p>AmUserRoleController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
public class AmUserRoleController extends BaseController {
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody Map<String,String> login(HttpServletRequest request) {
		if (request.getSession().getAttribute(Const.USER) != null) {  
			AmUser amUser = (AmUser)request.getSession().getAttribute(Const.USER);
			request.removeAttribute(Const.USER);
			Config.loginAmUserMap.remove(amUser.getUserId());
			request.getServletContext().setAttribute(Const.ACTIVE_SESSIONS, Config.loginAmUserMap.size());
		}
		Map<String,String> map = new HashMap<String,String>();
		map.put("result", "fail");
		String userCdOrEmail = request.getParameter("userCdOrEmail");
		String password = request.getParameter("userPassword");
		List<String> roleIds = null;
		try {
			String newPassword = EncryptUtil.encryptByBase64(password);
			roleIds = amUserRoleManager.getRoleIds(userCdOrEmail, newPassword);
			if (roleIds != null && !roleIds.isEmpty()) {
				AmUser amUser = amUserManager.getAmUser(userCdOrEmail, newPassword);
				Map<String,List<String>> roleIdsNamesMap = amUserRoleManager.getRoleIdsNamesMap(amUser.getUserId());
				amUser.setRoleIds(StringUtil.toStrWithSplit(roleIdsNamesMap.get("roleIds"),","));
				amUser.setRoleNames(roleIdsNamesMap.get("roleNames"));
					String singleSignOn = (String)request.getServletContext().getAttribute(BmConfig.SINGLE_SIGN_ON);
					if ("on".equalsIgnoreCase(singleSignOn) && Config.loginAmUserMap.containsKey(amUser.getUserId())) {
						//if(!request.getSession().getId().equalsIgnoreCase(Config.loginAmUserMap.get(amUser.getUserId()).getSessionId())) {
						map.put("result", "oneLogin");
						return map;
					}
					request.getSession().setAttribute(Const.USER, amUser);
					amUser.setSessionId(request.getSession().getId());
					Config.loginAmUserMap.put(amUser.getUserId(), amUser);
					if (roleIds.indexOf(AmRole.ROLE_ID_ADMIN) != -1) {
						request.getSession().setAttribute(Const.IS_ADMIN, "yes");
					}
					if (roleIds.indexOf(AmRole.ROLE_ID_MANAGER) != -1) {
						request.getSession().setAttribute(Const.IS_MANAGER, "yes");
					}
					String lang = ServletUtil.getLanguage(request);
					request.getSession().setAttribute(Const.LANGUAGE, Const.LANGUAGE_EN_SG);
					if (amUser.getLoginTimes()==null) {
						amUser.setLoginTimes(1);
					} else {
						int loginTimes = amUser.getLoginTimes().intValue();
						amUser.setLoginTimes(++loginTimes);
					}
					amUserManager.update(amUser);
					try {
						Device device = DeviceUtils.getCurrentDevice(request);
						BmUserLoginInfo bmUserLoginInfo = new BmUserLoginInfo(UserAgentUtil.getUserAgent(request.getHeader("user-agent")));
						if (device != null) {
							if (device.isMobile()) {
								bmUserLoginInfo.setDeviceType("Mobile");
							} else if (device.isTablet()) {
								bmUserLoginInfo.setDeviceType("Tablet");
							}
						}
						bmUserLoginInfo.setLoginUserId(amUser.getUserId());
						bmUserLoginInfo.setLoginUserName(amUser.getUserName());
						bmUserLoginInfo.setIpAddress(((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest().getRemoteAddr());
						bmUserLoginInfoManager.save(bmUserLoginInfo);
						request.getSession().setAttribute("bmUserLoginInfo", bmUserLoginInfo);
						ActiveSessionsListener.addTotalActiveSession();
						request.getServletContext().setAttribute(Const.ACTIVE_SESSIONS, Config.loginAmUserMap.size());
						log.info(amUser.getUserName() + " Sign In");
					} catch (Exception ex) {
						log.error(ex.getMessage());
					}
				map.put("result", "success");
				map.put("userId", amUser.getUserId());
				map.put("jSessionId", amUser.getSessionId());
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
			map.put("result", "error");
		}
		return map;
	}
	
	@RequestMapping(value = "/amUserRole", method = RequestMethod.GET)
	public String toAmUserRoleJqmList(HttpServletRequest request) {
		List<AmUserRole> amUserRoles = amUserRoleManager.getAmUserRoles();
		request.setAttribute("amUserRoles", amUserRoles);
		super.setLeftMenu(request, BmMenu.MENU_TYPE_MANAGEMENT_CONSOLE);
        return "am/amUserRole/amUserRoleJqmList";
	}
	
	@RequestMapping(value = "/amUserRoleJqmAdd", method = RequestMethod.GET)
	public String toAmUserRoleJqmAdd(HttpServletRequest request) {
        return "am/amUserRole/amUserRoleJqmAdd";
	}
	
	@RequestMapping(value = "/amUserRoleJqmEdit/{userRoleId}", method = RequestMethod.GET)
	public String toAmUserRoleJqmEdit(@PathVariable String userRoleId, HttpServletRequest request) {
		AmUserRole amUserRole = amUserRoleManager.getAmUserRoleById(userRoleId);
		request.setAttribute("amUserRole",amUserRole);
        return "am/amUserRole/amUserRoleJqmEdit";
	}
	
	@RequestMapping(value = "/saveAmUserRole", method = RequestMethod.POST)
	public String saveAmUserRole(HttpServletRequest request, RedirectAttributes ra) {
		AmUserRole amUserRole = new AmUserRole();
		setValue(request, amUserRole);
		amUserRoleManager.save(amUserRole);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/amUserRole";
	}
	
	@RequestMapping(value = "/updateAmUserRole", method = RequestMethod.POST)
	public String updateAmUserRole(HttpServletRequest request, RedirectAttributes ra) {
		String userRoleId = request.getParameter("userRoleId");
		AmUserRole amUserRole = amUserRoleManager.getAmUserRoleById(userRoleId);
		setValue(request, amUserRole);
		amUserRoleManager.update(amUserRole);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/amUserRole"; 
	}
	
	@RequestMapping(value = "/deleteAmUserRole/{userRoleId}", method = RequestMethod.GET)
	public String deleteAmUserRole(@PathVariable String userRoleId, HttpServletRequest request, RedirectAttributes ra) {
		amUserRoleManager.delete(userRoleId);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/amUserRole"; 
	}

	@RequestMapping(value = "/deleteAmUserRoles", method = RequestMethod.POST)
	public String deleteAmUserRoles(HttpServletRequest request, RedirectAttributes ra) {
		amUserRoleManager.batchDelete(request.getParameter("userRoleIds"));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/amUserRole"; 
	}	
	@Resource(name="amUserRoleManagerImpl")
	private AmUserRoleManager amUserRoleManager;
	
	@Resource(name="amUserManagerImpl")
	private AmUserManager amUserManager;
	
	@Resource(name="bmUserLoginInfoManagerImpl")
	private BmUserLoginInfoManager bmUserLoginInfoManager;

}