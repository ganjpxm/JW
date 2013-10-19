/**
 * $Id: JqmSelectTag.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */

package org.ganjp.jpw.core.web.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.ExpressionEvaluationUtils;

/**
 * @jsp.tag name="jqmSelect" bodycontent="empty"
 */
public class JqmSelectTag extends AbstractTag {
	protected String name;
	protected String title;
	protected String selectedValue;
	protected String data;
	protected String onChange;
	protected String selectClass;
	protected String dataTheme;
	protected String dataMini;
	
	public int doStartTag() throws JspException {
		StringBuffer htmlSb = new StringBuffer();
		if (selectClass!=null && selectClass.indexOf("required")!=-1) {
    		htmlSb.append("<span class='red'>*</span>");
    	}
		htmlSb.append("<select name='").append(name).append("' id='").append(name).append("' ");
		if(onChange != null) {
        	htmlSb.append(" onchange='").append(onChange).append("' ");
    	} 
		if (selectClass != null) {
			htmlSb.append(" class='").append(selectClass).append("' ");
		}
		htmlSb.append("data-native-menu='false' ");
		if (dataTheme==null) {
			htmlSb.append("data-theme='d'");
		} else {
			htmlSb.append("data-theme='").append(dataTheme).append("'");
		}
		
		if (dataMini==null) {
			htmlSb.append("data-mini='true'");
		} else {
			htmlSb.append("data-mini='").append(dataMini).append("'");
		}
		
		htmlSb.append(">");
		if (title!=null) {
			htmlSb.append("<option data-placeholder='true'>").append(title).append("</option>");
		}
				
		List valueLabelArrs = new ArrayList();
		if (data!=null && data.indexOf(":")!=-1) {
			String[] dataArr = data.split(";");
			for (int i = 0; i < dataArr.length; i++) {
				String valueLabels = dataArr[i];
				valueLabelArrs.add(valueLabels.split(":"));
			}
		}
		for (int i = 0; i < valueLabelArrs.size(); i++) {
			String[] valueLabelArr = (String[])valueLabelArrs.get(i);
			String value = valueLabelArr[0];
			String lable = valueLabelArr[1];
			htmlSb.append("<option value='").append(value).append("' ");
			if (value.equals(selectedValue)) {
				htmlSb.append("selected='selected' ");	
			}
			htmlSb.append(">").append(lable).append("</option>");
		}
		
		htmlSb.append("</select>");
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
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @jsp.attribute required="false" rtexprvalue="false"
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @jsp.attribute required="false" rtexprvalue="true"
	 */
	public void setSelectedValue(String selectedValue) {
		try {
			this.selectedValue = ExpressionEvaluationUtils.evaluateString("selectedValue", selectedValue, pageContext);
		} catch (JspException e) {
			this.selectedValue = selectedValue;
			log.error(e.getMessage());
		}
	}
	
	/**
	 * @jsp.attribute required="true" rtexprvalue="true"
	 */
	public void setData(String data) {
		try {
			this.data = ExpressionEvaluationUtils.evaluateString("data", data, pageContext);
		} catch (JspException e) {
			this.data = data;
			log.error(e.getMessage());
		}
	}
	
	/**
	 * @jsp.attribute required="false" rtexprvalue="false"
	 */
	public void setOnChange(String onChange){
		this.onChange=onChange;
	}
	
	/**
	 * @jsp.attribute required="false" rtexprvalue="false"
	 */
	public void setSelectClass(String selectClass){
		this.selectClass=selectClass;
	}
	
	/**
	 * @jsp.attribute required="false" rtexprvalue="false"
	 */
	public void setDataTheme(String dataTheme){
		this.dataTheme=dataTheme;
	}
	
	/**
	 * @jsp.attribute required="false" rtexprvalue="false"
	 */
	public void setDataMini(String dataMini){
		this.dataMini=dataMini;
	}
	
	private static Logger log = LoggerFactory.getLogger(JqmSelectTag.class);
}
