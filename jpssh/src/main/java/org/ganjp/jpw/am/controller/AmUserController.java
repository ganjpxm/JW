/**
 * $Id: AmUserController.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.am.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.ganjp.jpw.am.model.AmRole;
import org.ganjp.jpw.am.model.AmUser;
import org.ganjp.jpw.am.service.AmRoleManager;
import org.ganjp.jpw.am.service.AmUserManager;
import org.ganjp.jpw.am.service.AmUserRoleManager;
import org.ganjp.jpw.bm.model.BmMenu;
import org.ganjp.jpw.core.Const;
import org.ganjp.jpw.core.util.DateUtil;
import org.ganjp.jpw.core.util.EncryptUtil;
import org.ganjp.jpw.core.util.FileUtil;
import org.ganjp.jpw.core.util.StringUtil;
import org.ganjp.jpw.core.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <p>AmUserController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
public class AmUserController extends BaseController {
	@RequestMapping(value = "/amUser", method = RequestMethod.GET)
	public String toAmUserJqmList(HttpServletRequest request) {
		List<AmUser> amUsers = amUserRoleManager.getAmUsersWithRoleIdNames();
		request.setAttribute("amUsers", amUsers);
		super.setLeftMenu(request, BmMenu.MENU_TYPE_MANAGEMENT_CONSOLE);
        return "am/amUser/amUserJqmList";
	}
	
	@RequestMapping(value = "/amUserJqmAdd", method = RequestMethod.GET)
	public String toAmUserJqmAdd(HttpServletRequest request) {
		request.setAttribute("roleMaps", amRoleManager.getRolesForJqmCheck());
        return "am/amUser/amUserJqmAdd";
	}
	
	@RequestMapping(value = "/amUserJqmEdit/{userId}", method = RequestMethod.GET)
	public String toAmUserJqmEdit(@PathVariable String userId, HttpServletRequest request) {
		AmUser amUser = amUserManager.getAmUserById(userId);
		try {
			amUser.setPassword(EncryptUtil.decryptByBase64(amUser.getPassword()));
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		amUser.setRoleIds(request.getParameter("roleIds"));
		request.setAttribute("amUser",amUser);
		request.setAttribute("roleMaps", amRoleManager.getRolesForJqmCheck());
        return "am/amUser/amUserJqmEdit";
	}
	
	@RequestMapping(value = "/loginUserJqmEdit/{userId}", method = RequestMethod.GET)
	public String toAmLoginUserJqmEdit(@PathVariable String userId, HttpServletRequest request) {
		AmUser amUser = amUserManager.getAmUserById(userId);
		try {
			amUser.setPassword(EncryptUtil.decryptByBase64(amUser.getPassword()));
		} catch(Exception ex) {
			log.error(ex.getMessage());
		}
		request.setAttribute("amUser", amUser);
		request.setAttribute("from", request.getParameter("from"));
        return "am/amUser/amLoginUserJqmEdit";
	}
	
	@RequestMapping(value = "/saveAmUser", method = RequestMethod.POST)
	public String saveAmUser(HttpServletRequest request, RedirectAttributes ra) {
		AmUser amUser = new AmUser();
		setValue(request, amUser);
		try {
			amUser.setPassword(EncryptUtil.encryptByBase64(amUser.getPassword()));
		} catch(Exception ex) {
			log.error(ex.getMessage());
		}
		amUserRoleManager.saveAmUserAndUserRole(amUser, request.getParameter("roleIds").split(","));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/amUser";
	}
	
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public @ResponseBody Map<String,String> signUp(HttpServletRequest request, RedirectAttributes ra) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("result", "success");
		try {
			AmUser amUser = new AmUser();
			setValue(request, amUser);
			List<AmUser> amUsers= amUserManager.getAmUsersByField("userCd", amUser.getUserCd());
			if (amUsers!=null && !amUsers.isEmpty()) {
				map.put("result", "userCd");
			} else {
				amUsers = amUserManager.getAmUsersByField("email", amUser.getEmail());
				if (amUsers!=null && !amUsers.isEmpty()) {
					map.put("result", "email");
				} else {
					amUser.setUserName(amUser.getUserCd());
					amUserRoleManager.saveAmUserAndUserRole(amUser, AmRole.ROLE_ID_GUEST);
				}
			}
		} catch (Exception e) {
			map.put("result", "error");
		}
		return map;
	}
	
	@RequestMapping(value = "/updateAmUser", method = RequestMethod.POST)
	public String updateAmUser(HttpServletRequest request, RedirectAttributes ra) {
		String userId = request.getParameter("userId");
		AmUser amUser = amUserManager.getAmUserById(userId);
		String password = amUser.getPassword();
		setValue(request, amUser);
		try {
			amUser.setPassword(EncryptUtil.encryptByBase64(amUser.getPassword()));
		} catch(Exception ex) {
			amUser.setPassword(password);
			log.error(ex.getMessage());
		}
		amUserRoleManager.updateAmUserAndAmUserRole(amUser, request.getParameter("roleIds"));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/amUser";
	}
	
	@RequestMapping(value = "/updateAmLoginUser", method = RequestMethod.POST)
	public String updateAmLoginUser(HttpServletRequest request, RedirectAttributes ra) {
		AmUser amUser = super.getLoginUser(request);
		String password = amUser.getPassword();
		setValue(request, amUser);
		try {
			amUser.setPassword(EncryptUtil.encryptByBase64(amUser.getPassword()));
		} catch(Exception ex) {
			amUser.setPassword(password);
			log.error(ex.getMessage());
		}
		amUserManager.update(amUser);
		request.getSession().setAttribute(Const.USER, amUser);
		String from = StringUtil.isEmpty(request.getParameter("from"))?"/":request.getParameter("from");
		ra.addAttribute("profile", "yes");
		return "redirect:"+from;
	}
	
	@RequestMapping(value = "/deleteAmUser/{userId}", method = RequestMethod.GET)
	public String deleteAmUser(@PathVariable String userId, HttpServletRequest request, RedirectAttributes ra) {
		amUserRoleManager.deleteAmUserAndUserRole(userId);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/amUser"; 
	}

	@RequestMapping(value = "/deleteAmUserByUserCd", method = RequestMethod.GET)
	public String deleteAmUserByUserCd(HttpServletRequest request, RedirectAttributes ra) {
		String userCd = request.getParameter("userCd");
		List<AmUser> amUsers = amUserManager.getAmUsersByField("userCd", userCd);
		if (amUsers!=null && !amUsers.isEmpty()) {
			amUserRoleManager.deleteAmUserAndUserRole(amUsers.get(0).getUserId());
		}
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/amUser"; 
	}
	
	@RequestMapping(value = "/deleteAmUsers", method = RequestMethod.POST)
	public String deleteAmUsers(HttpServletRequest request, RedirectAttributes ra) {
		amUserRoleManager.batchDeleteAmUserAndUserRole(request.getParameter("userIds"));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/amUser"; 
	}	
	
	@RequestMapping(value="/importUserExcel", method=RequestMethod.POST)
	public @ResponseBody Map<String,String> importUserExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		String fileName = "";
		Map<String,String> map = new HashMap<String,String>();
		map.put("result", "success");
		String errorInfo = "";
		try {
			fileName = DateUtil.getNowYyyyMmDmHhMmSs() + new String(file.getOriginalFilename().getBytes("ISO-8859-1"),"UTF-8");
			String subFix = fileName.substring(fileName.lastIndexOf(".")+1);
			if (!"xlsx".equalsIgnoreCase(subFix) && !"xls".equalsIgnoreCase(subFix)) {
				map.put("result", "Please import excel.");
				return map;
			}
			String saveFullPath = FileUtil.getPath(request.getRealPath(""), "resources", "upload", "excel", fileName);
			if (file.getSize() > 20000000) {
				map.put("result", fileName + " size is more than 20M");
			} else {
				FileUtil.copy(file.getInputStream(), new File(saveFullPath));
				errorInfo = amUserRoleManager.saveUsersByExcel(saveFullPath);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			map.put("result", fileName + " import fail" + e.getMessage());
			return map;
		}
		if (StringUtil.isNotEmpty(errorInfo)) {
			map.put("result", fileName + " import fail : " + errorInfo);
		}
		return map;
	}

	
	@Resource(name="amUserManagerImpl")
	private AmUserManager amUserManager;
	
	@Resource(name="amRoleManagerImpl")
	private AmRoleManager amRoleManager;
	
	@Resource(name="amUserRoleManagerImpl")
	private AmUserRoleManager amUserRoleManager;
}