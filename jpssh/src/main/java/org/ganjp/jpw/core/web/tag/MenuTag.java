/**
 * $Id: MenuTag.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */

package org.ganjp.jpw.core.web.tag;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.ExpressionEvaluationUtils;

public abstract class MenuTag extends AbstractTag {

	private static final long serialVersionUID = 1L;

	protected String activeMenuId;
	
	protected void prefix(StringBuffer html) throws JspException {
    	
	}

	protected void suffix(StringBuffer html) throws JspException {
    	html.append("</select>");
	}

	/**
	 * @jsp.attribute required="true" rtexprvalue="true"
	 */
	public void setActiveMenuId(String activeMenuId) {
		try {
			this.activeMenuId = ExpressionEvaluationUtils.evaluateString("activeMenuId", activeMenuId, pageContext);
		} catch (JspException e) {
			this.activeMenuId = activeMenuId;
			log.error(e.getMessage());
		}
	}
	
	private static Logger log = LoggerFactory.getLogger(MenuTag.class);
}
