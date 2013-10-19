/**
 * $Id: CommonController.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.core.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ganjp.jpw.am.model.AmUser;
import org.ganjp.jpw.core.Config;
import org.ganjp.jpw.core.Const;
import org.ganjp.jpw.core.util.StringUtil;
import org.ganjp.jpw.core.web.util.ServletUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class JpInteceptor implements HandlerInterceptor {
	@Override  
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding(Config.getValue(Const.ENCODING));
		String url = request.getRequestURL().toString();
		String contextPath = request.getContextPath();
		HttpSession session = request.getSession();
		AmUser user = (AmUser) session.getAttribute(Const.USER);
		if (user!=null && StringUtil.isNotEmpty(request.getParameter("changeLange"))) {
        	session.setAttribute(Const.LANGUAGE, request.getParameter("changeLange"));
        }
		if (url.indexOf("/mobile")!=-1) {
			if (user == null) {
				response.sendRedirect(contextPath + "/vtwo/sessionTimeout");
				return false;
			} else {
				return true;
			}
		} else {
			/**
	        if (url.indexOf("/am")==-1 && url.indexOf("/bm")==-1 && url.indexOf("/cm")==-1 && url.indexOf("/console")==-1 
	        		&& url.indexOf("/groups")==-1 && url.indexOf("/survey")==-1 && url.indexOf("Jqm")==-1 && url.indexOf("/aboutUs")==-1) { 
	        	return true;  
	        }
	        */
			String root = Config.getValue("root");
	
			if (user == null && !url.endsWith("www.ganjianping.com/") && !url.endsWith("198.101.207.178/") && !url.endsWith(root) && !url.endsWith(root+"/") && url.indexOf("/login")==-1 && url.indexOf("/resources")==-1 &&
	        		url.indexOf("/uploadFile")==-1 && url.indexOf("ByAjax")==-1 && url.indexOf("/sessionTimeout")==-1 && url.indexOf("/logout")==-1 
	        		&& url.indexOf("vtwo/")==-1) {  
	        	response.sendRedirect(contextPath + "/vtwo/sessionTimeout");//Config.getValue("homePage")  
	        	return false;
	        } else {
	        	if (user == null && url.indexOf("/login")!=-1 && url.indexOf("/loginUser")!=-1) {
	        		response.sendRedirect(contextPath + "/vtwo/sessionTimeout");  
	            	return false;
	        	}
	        	return true;
	        }
		}
    }

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3) throws Exception {
		if (!"application/json".equals(response.getContentType()) && !"application/xml".equals(response.getContentType())) {
			request.setAttribute(Const.JQM_THEME, ServletUtil.getJqmTheme(request));
			request.setAttribute(Const.LANGUAGE, ServletUtil.getLanguage(request));
			request.setAttribute("menuId", request.getParameter("menuId"));
		}
	}  
}
