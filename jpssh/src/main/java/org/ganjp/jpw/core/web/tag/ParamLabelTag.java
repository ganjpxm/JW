/**
 * $Id: ParamLabelTag.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.core.web.tag;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;

import org.ganjp.jpw.core.Config;

/**
 * @jsp.tag name="paramLabel" bodycontent="empty"
 */
public class ParamLabelTag extends AbstractTag {

	protected String paramId;
	protected String hiddenName;
	

	public int doStartTag() throws JspException {
		StringBuffer htmlSB = new StringBuffer();
		Map paramIdNameMap = Config.getAllCashParamIdNames();
		htmlSB.append("<lable>");
		htmlSB.append(paramIdNameMap.get(paramId));
		htmlSB.append("</lable>");
		try {
			pageContext.getOut().write(htmlSB.toString());
		}
		catch (IOException io) {
			io.printStackTrace();
			throw new JspException("Error writing label: " + io.getMessage());
		}
		return (SKIP_BODY);
	}
	
	/**
	 * @jsp.attribute required="true" rtexprvalue="false"
	 */
	public void setParamId(String paramId) {
		this.paramId = paramId;
	}
	
	/**
	 * @jsp.attribute required="false" rtexprvalue="false"
	 */
	public void setHiddenName(String hiddenName) {
		this.hiddenName = hiddenName;
	}
	
}
