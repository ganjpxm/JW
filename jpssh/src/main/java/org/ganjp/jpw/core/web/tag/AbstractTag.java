/**
 * $Id: AbstractTag.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.core.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.ganjp.jpw.core.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.util.ExpressionEvaluationUtils;

public abstract class AbstractTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(AbstractTag.class);
	
	protected String lang;
	
	protected String getMessage(String key, String lang) {
		RequestContext requestContext = null;
		try {
			requestContext = new RequestContext(request());
		}
		catch (Exception e) {
			log.error(e.getMessage());
		}
			
		String message = null;
		try {
			message = requestContext.getWebApplicationContext().getMessage(key, null, Config.getLocale(lang));
		} catch (NoSuchMessageException nsm) {
		    message = "???" + key + "???";
		}
		return message;
	}
	
	/**
	 * <p>get base path</p>
	 * 
	 * @param request
	 * @return
	 */
	public String getBasePath() {
		return request().getScheme()+"://"+request().getServerName()+":"+request().getServerPort() + request().getContextPath();
	}
	
	public HttpServletRequest request() {
		return (HttpServletRequest) this.pageContext.getRequest();
	}
	
	/**
	 * @jsp.attribute required="false" rtexprvalue="true"
	 */
	public void setLang(String lang) {
		try {
			this.lang = ExpressionEvaluationUtils.evaluateString("lang", lang, pageContext);
		} catch (JspException e) {
			this.lang = lang;
			log.error(e.getMessage());
		}
	}
}
