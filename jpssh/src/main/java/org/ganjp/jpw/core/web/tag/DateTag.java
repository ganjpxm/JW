/**
 * $Id: DateTag.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */

package org.ganjp.jpw.core.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;

/**
 * @jsp.tag name="date" bodycontent="empty"
 */
public class DateTag extends AbstractTag {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String lableValue; //Date of Birth:
	
	protected String dayName;
	
	protected String monthName;
	
	protected String yearName;
	
	public int doStartTag() throws JspException {
		StringBuffer htmlSb = new StringBuffer();
		htmlSb.append("<div data-role='fieldcontain'>");
		htmlSb.append("  <fieldset data-role='controlgroup' data-type='horizontal'>");
		htmlSb.append("    <legend>").append(lableValue).append(" : </legend>");
		htmlSb.append("    <label for='").append(dayName).append("'>Day</label>");	
		htmlSb.append("    <select name='").append(dayName).append("' id='").append(dayName).append("' data-native-menu='false'>");
		htmlSb.append("      <option>Day</option>");
		for (int i = 1; i < 32; i++) {
			htmlSb.append("  <option value='").append(i).append("'>").append(i).append("</option>");
		}	
		htmlSb.append("    </select>");		
		htmlSb.append("    <label for='").append(monthName).append("'>Month</label>");	
		htmlSb.append("    <select name='").append(monthName).append("' id='").append(monthName).append("' data-native-menu='false'>");
		htmlSb.append("      <option>Month</option>");
		htmlSb.append("      <option value='jan'>January</option>");
		htmlSb.append("      <option value='jan'>February</option>");
		htmlSb.append("      <option value='jan'>March</option>");
		htmlSb.append("      <option value='jan'>April</option>");
		htmlSb.append("      <option value='jan'>May</option>");
		htmlSb.append("      <option value='jun'>June</option>");
		htmlSb.append("      <option value='jul'>July</option>");
		htmlSb.append("      <option value='aug'>August</option>");
		htmlSb.append("      <option value='sep'>September</option>");
		htmlSb.append("      <option value='oct'>October</option>");
		htmlSb.append("      <option value='nov'>November</option>");
		htmlSb.append("      <option value='dec'>December</option>");
		htmlSb.append("    </select>");
		htmlSb.append("    <input id='").append(yearName).append("' name='").append(yearName).append("' style='width:100px'/>");
		htmlSb.append("  </fieldset>");
		htmlSb.append("</div>");
		try {
			pageContext.getOut().write(htmlSb.toString());
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
	public void setLableValue(String lableValue) {
		this.lableValue = lableValue;
	}
	
	/**
	 * @jsp.attribute required="true" rtexprvalue="false"
	 */
	public void setDayName(String dayName) {
		this.dayName = dayName;
	}
	
	/**
	 * @jsp.attribute required="true" rtexprvalue="false"
	 */
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	/**
	 * @jsp.attribute required="true" rtexprvalue="false"
	 */
	public void setYearName(String yearName) {
		this.yearName = yearName;
	}
}
