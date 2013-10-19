/**
 * $Id: AbstractSelectTag.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
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

public abstract class AbstractSelectTag extends AbstractTag {

	private static final long serialVersionUID = 1L;

	protected String name;
	
	protected String selected;

	protected String cssClass;
	
	protected String cssStyle;
	
	protected String titleKey;
	
	protected String titleValue;
	
	protected String onChange;
	
	protected void prefix(StringBuffer html) throws JspException {
    	html.append("<select id=\"").append(name).append("\" name=\"").append(name).append("\"");
    	if(cssClass != null) {
    		html.append(" class=\"").append(cssClass).append("\"");
    	}
    	if(cssStyle != null) {
    		html.append(" style=\"").append(cssStyle).append("\"");
    	}
    	if(onChange != null) {
        	html.append(" onchange=\"").append(onChange).append("\"");
    	}
    	html.append(">");
    	if(titleKey != null){
    		String message = getMessage(titleKey, lang);
    		html.append("<option value=\"").append(titleValue != null ? titleValue : "").append("\">").append(message).append("</option>");
    	}
	}

	protected void suffix(StringBuffer html) throws JspException {
    	html.append("</select>");
	}

	/**
	 * @jsp.attribute required="true" rtexprvalue="false"
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @jsp.attribute required="false" rtexprvalue="false"
	 */
	public void setSelected(String selValue) {
		try {
			this.selected = ExpressionEvaluationUtils.evaluateString("selected", selValue, pageContext);
		}
		catch (JspException e) {
			this.selected = selValue;
			log.error(e.getMessage());
		}
	}

	/**
	 * @jsp.attribute required="false" rtexprvalue="false"
	 */
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	
	/**
	 * @jsp.attribute required="false" rtexprvalue="false"
	 */
	public void setCssStyle(String cssStyle) {
		this.cssStyle = cssStyle;
	}
	
	/**
	 * @jsp.attribute required="false" rtexprvalue="false"
	 */
	public void setTitleKey(String titleKey){
		this.titleKey = titleKey;
	}
	
	/**
	 * @jsp.attribute required="false" rtexprvalue="false"
	 */
	public void setTitleValue(String titleValue) {
		this.titleValue = titleValue;
	}

	/**
	 * @jsp.attribute required="false" rtexprvalue="false"
	 */
	public void setOnChange(String onChange){
		this.onChange=onChange;
	}

	public void release() {
		super.release();
		name = null;
		selected = null;
		cssClass = null;
		cssStyle = null;
		titleKey = null;
		titleValue = null;
		onChange = null;
	}
	
	private static Logger log = LoggerFactory.getLogger(AbstractSelectTag.class);
}
