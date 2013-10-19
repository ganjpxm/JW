/**
 * $Id: BaseController.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.core.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.ganjp.jpw.am.model.AmUser;
import org.ganjp.jpw.bm.model.BmMenu;
import org.ganjp.jpw.bm.model.BmParam;
import org.ganjp.jpw.bm.service.BmMenuManager;
import org.ganjp.jpw.core.Const;
import org.ganjp.jpw.core.model.BaseModel;
import org.ganjp.jpw.core.util.StringUtil;
import org.ganjp.jpw.core.web.util.ServletUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Base Controller</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public class BaseController {
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * <p>set baseModel object's field value from request</p>
	 * 
	 * @param request
	 * @param baseModel
	 */
	protected void setValue(HttpServletRequest request, BaseModel baseModel) {
		ServletUtil.setValue(request, baseModel, null);
	}
	
	/**
	 * <p>set baseModel object's field value from request</p>
	 * 
	 * @param request
	 * @param baseModel
	 */
	protected void setValue(HttpServletRequest request, BaseModel baseModel, String suffix) {
		ServletUtil.setValue(request, baseModel, suffix);
	}
	/**
	 * <p>get base path</p>
	 * 
	 * @param request
	 * @return
	 */
	public String getBasePath(HttpServletRequest request) {
		return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + request.getContextPath();
	}

	protected AmUser getLoginUser(HttpServletRequest request) {
		return (AmUser)request.getSession().getAttribute(Const.USER);
	}
	
	protected boolean isAdmin(HttpServletRequest request) {
		return "yes".equalsIgnoreCase(StringUtil.toString(request.getSession().getAttribute(Const.IS_ADMIN)))?true:false;
	}

	protected boolean isManager(HttpServletRequest request) {
		return "yes".equalsIgnoreCase(StringUtil.toString(request.getSession().getAttribute(Const.IS_MANAGER)))?true:false;
	}

	protected String getLoginUserId(HttpServletRequest request) {
		AmUser amUser = this.getLoginUser(request);
		if (amUser!=null) {
			return amUser.getUserId();
		} else {
			return null;
		}
		
	}
	
	protected String getLoginUserName(HttpServletRequest request) {
		AmUser amUser = this.getLoginUser(request);
		if (amUser!=null) { 
			return amUser.getUserName();
		} else {
		    return "";
		}
		
	}
	
	protected String getLoginUserRoleIds(HttpServletRequest request) {
		AmUser amUser = this.getLoginUser(request);
		if (amUser==null) {
			return null;
		} else {
			return amUser.getRoleIds();
		}
	}
	
	public void setLeftMenu(HttpServletRequest request, String menuType) {
		if (BmMenu.MENU_TYPE_MANAGEMENT_CONSOLE.equals(menuType)) {
			String managementConsoleMenuHtml = bmMenuManager.getTwoLevelMenuHtml(request.getParameter("menuId"), 
					BmParam.PARAM_CD_MANAGEMENT_CONSOLE, getBasePath(request), ServletUtil.getLanguage(request), 
					ServletUtil.getJqmTheme(request));
			request.setAttribute("leftMenuHtml", managementConsoleMenuHtml);
		}
	}

	public String getRoleMapsForJqmCheck(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		AmUser amUser = getLoginUser(request);
		if (amUser!=null) {
			String[] roleIdArr = amUser.getRoleIds().split(",");
			List<String> roleNames = amUser.getRoleNames();
			for (int i = 0; i < roleIdArr.length; i++) {
				sb.append(roleIdArr[i]).append(":").append(roleNames.get(i)).append(";");
			}
		}
		return sb.toString();
	}
	
	protected void accessProject(HttpServletRequest request, String project) {
		request.getSession().setAttribute("project", project);
	}
	@Resource(name="bmMenuManagerImpl")
	protected BmMenuManager bmMenuManager;
}
