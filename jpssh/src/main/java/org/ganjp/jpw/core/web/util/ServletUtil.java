/**
 * $Id: ServletUtil.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.core.web.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ganjp.jpw.am.model.AmUser;
import org.ganjp.jpw.core.Config;
import org.ganjp.jpw.core.Const;
import org.ganjp.jpw.core.model.BaseModel;
import org.ganjp.jpw.core.util.DateUtil;
import org.ganjp.jpw.core.util.ReflectUtil;
import org.ganjp.jpw.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Servlet Util</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public class ServletUtil {
	public static void setValue(HttpServletRequest request, BaseModel baseModel, String suffix) {
		Field[] fields = baseModel.getClass().getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			if ("modifyTimestamp".equalsIgnoreCase(fieldName)) {
				try {
					Method method = ReflectUtil.getSetMethod(baseModel.getClass(), fieldName);
					method.invoke(baseModel, DateUtil.getNowTimstamp());
					continue;
				} catch (Exception ex) {
					log.error("set method " + fieldName + " value fail:" + ex.getMessage());
				}
			} else if ("operatorId".equalsIgnoreCase(fieldName)) {
				AmUser amUser = (AmUser)request.getSession().getAttribute(Const.USER);
				try {
					if (amUser!=null) {
						Method method = ReflectUtil.getSetMethod(baseModel.getClass(), fieldName);
						method.invoke(baseModel, amUser.getUserId());
					}
					continue;
				} catch (Exception ex) {
					log.error("set operatorId value fail:" + ex.getMessage());
				}
			} else if ("operatorName".equalsIgnoreCase(fieldName)) {
				AmUser amUser = (AmUser)request.getSession().getAttribute(Const.USER);
				try {
					if (amUser!=null) {
						Method method = ReflectUtil.getSetMethod(baseModel.getClass(), fieldName);
						method.invoke(baseModel, amUser.getUserName());
					}
					continue;
				} catch (Exception ex) {
					log.error("set operatorName value fail:" + ex.getMessage());
				}
			} else if ("lang".equalsIgnoreCase(fieldName)) {
				try {
					Method method = ReflectUtil.getSetMethod(baseModel.getClass(), fieldName);
					method.invoke(baseModel, ServletUtil.getLanguage(request));
					continue;
				} catch (Exception ex) {
					log.error("set operatorId value fail:" + ex.getMessage());
				}
			} 
			String value = "" ;
			if (StringUtil.hasLength(suffix)) {
				value = request.getParameter(fieldName + suffix);
			} else {
				value = request.getParameter(fieldName);
			}
			if (value != null) {
				try {
					Method method = ReflectUtil.getSetMethod(baseModel.getClass(), fieldName);
					Class<?>[] types = method.getParameterTypes();
					if (types[0].getSimpleName().equalsIgnoreCase("Integer")) {
						method.invoke(baseModel, new Integer(value));
					} else if(types[0].getSimpleName().equalsIgnoreCase("BigDecimal")) {
						method.invoke(baseModel, new BigDecimal(value));
					} else if(types[0].getSimpleName().equalsIgnoreCase("Date")) {
						method.invoke(baseModel, DateUtil.parseDate(value, "dd/MM/yyyy"));
					} else if(types[0].getSimpleName().equalsIgnoreCase("Time")) {
						method.invoke(baseModel, DateUtil.parseTime(value));
					} else if(types[0].getSimpleName().equalsIgnoreCase("Timestamp")) {
						Date date = DateUtil.parseDate(value, "dd/MM/yyyy hh:mm");
						method.invoke(baseModel, DateUtil.convertDateToTimestamp(date));
					} else {
						method.invoke(baseModel, value);
					}
				} catch (Exception ex) {
					log.error("set method " + fieldName + " value fail:" + ex.getMessage());
				}
			}
		}
	}

	public static String getCookieValue(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if (cookies!=null) {
			for(int i=0; i<cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookieName.equals(cookie.getName())) {
					return(cookie.getValue());
				}
			}
		}
		return null;
	}
	
	public static String getLanguage(HttpServletRequest request) {
		String lang = null;
		if (request.getParameter(Const.LANGUAGE)!=null) {
			lang = request.getParameter(Const.LANGUAGE);
		} else if (request.getSession().getAttribute(Const.LANGUAGE) != null) {
			lang = StringUtil.toString(request.getSession().getAttribute(Const.LANGUAGE));
		} else if (ServletUtil.getCookieValue(request, Const.LANGUAGE)!=null) {
			lang = ServletUtil.getCookieValue(request, Const.LANGUAGE);
		} else {
			lang = Config.getDefaultLanguage();
		}
		return lang;
	}
	
	public static String getClientIpAddr(HttpServletRequest request) {  
        String ip = request.getHeader("X-Forwarded-For");  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_CLIENT_IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        }  
        return ip;  
    }  
	
	public static String getJqmTheme(HttpServletRequest request) {
		String jqmTheme = null;
		if (request.getParameter(Const.JQM_THEME)!=null) {
			jqmTheme = request.getParameter(Const.JQM_THEME);
		} else if (request.getSession().getAttribute(Const.JQM_THEME) != null) {
			jqmTheme = StringUtil.toString(request.getSession().getAttribute(Const.JQM_THEME));
		} else if (ServletUtil.getCookieValue(request, Const.JQM_THEME)!=null) {
			jqmTheme = ServletUtil.getCookieValue(request, Const.JQM_THEME);
		} else {
			jqmTheme = Config.getDefaultJqmTheme();
		}
		return jqmTheme;
	}
	
	public static void loginOut(ServletContext context, HttpSession session) {  
        if (session.getAttribute(Const.USER) == null) {  
            return;  
        } else {
        	session.invalidate();
        }
  
        /**
        ConcurrentHashMap<String, Object> map = (ConcurrentHashMap<String, Object>) context.getAttribute(Const.ACTIVE_SESSIONS);  
  
        if (null != map) {  
              
            if (map.containsKey(session.getId())) {  
                map.remove(session.getId());  
                context.setAttribute(Const.ACTIVE_SESSIONS, map);  
            }  
        }  
        */
    }  
	private static Logger log = LoggerFactory.getLogger(ServletUtil.class);
		
}
