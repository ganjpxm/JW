/**
 * $Id: JqmCheckTag.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
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

public abstract class JqmCheckAbstractTag extends AbstractTag {

	private static final long serialVersionUID = 1L;

	protected String name;
	protected String id;
	protected String checkType; //checkbox,radio
	protected String lableClass; //lable
	protected String lableValue; //Type of Child Care Programme
	protected String dataType; //horizontal
	protected String dataMini; //true,false
	protected String checkedIds; //value1,value2 | {checkedIds}
	protected String checkedNames;
	protected String inputClass;
	protected String data; //male:Male;female:Female;
	protected String exprData; //${childPickupPersonIdAndNames}
	protected String onChange;
	
	/**
	 * <p><div style='float:left'>Type of Child Care Programme:</div>
	 * <fieldset data-role='controlgroup' data-type='horizontal'></p>
	 * 
	 * @param htmlSb
	 * @throws JspException
	 */
	protected void prefix(StringBuffer htmlSb) throws JspException {
		if (lableValue != null) {
			htmlSb.append("<div");
	    	if(lableClass != null) {
	    		htmlSb.append(" class='").append(lableClass).append("'");
	    	} else {
	    		htmlSb.append(" class='lable'");
	    	}
	    	htmlSb.append(">");
	    	if (inputClass!=null && inputClass.indexOf("required")!=-1) {
	    		htmlSb.append("<span class='red'>*</span>");
	    	}
	    	if (lableValue.indexOf(".") == -1) {
	    		htmlSb.append(lableValue);
	    	} else {
	    		htmlSb.append(getMessage(lableValue, lang));
	    	}
	    	htmlSb.append(" : </div>");
		}
    	htmlSb.append("  <fieldset data-role='controlgroup'");
    	if (dataType != null) {
    		htmlSb.append(" data-type='").append(dataType).append("'");
    	} else {
    		htmlSb.append(" data-type='horizontal'");
    	}
    	
    	if (dataMini != null) {
    		htmlSb.append(" data-mini='").append(dataMini).append("'");
    	}
    	htmlSb.append("'>");
	}

	protected void suffix(StringBuffer htmlSb) throws JspException {
		htmlSb.append("  </fieldset>");		
	}

	/**
	 * @jsp.attribute required="true" rtexprvalue="true"
	 */
	public void setName(String name) {
		try {
			this.name = ExpressionEvaluationUtils.evaluateString("name", name, pageContext);
		} catch (JspException e) {
			this.name = name;
			log.error(e.getMessage());
		}
	}
	
	/**
	 * @jsp.attribute required="false" rtexprvalue="false"
	 */
	public void setId(String id) {
		try {
			this.id = ExpressionEvaluationUtils.evaluateString("id", id, pageContext);
		} catch (JspException e) {
			this.id = id;
			log.error(e.getMessage());
		}
	}

	/**
	 * @jsp.attribute required="true" rtexprvalue="false"
	 */
	public void setCheckType(String checkType){
		this.checkType=checkType;
	}
	
	/**
	 * @jsp.attribute required="false" rtexprvalue="false"
	 */
	public void setLableClass(String lableClass) {
		this.lableClass = lableClass;
	}
	
	/**
	 * @jsp.attribute required="false" rtexprvalue="false"
	 */
	public void setLableValue(String lableValue) {
		this.lableValue = lableValue;
	}
	
	/**
	 * @jsp.attribute required="false" rtexprvalue="false"
	 */
	public void setDataType(String dataType){
		this.dataType = dataType;
	}
	
	/**
	 * @jsp.attribute required="false" rtexprvalue="false"
	 */
	public void setDataMini(String dataMini){
		this.dataMini = dataMini;
	}
	
	/**
	 * @jsp.attribute required="false" rtexprvalue="true"
	 */
	public void setCheckedIds(String checkedIds) {
		try {
			this.checkedIds = ExpressionEvaluationUtils.evaluateString("checkedIds", checkedIds, pageContext);
		}
		catch (JspException e) {
			this.checkedIds = checkedIds;
			log.error(e.getMessage());
		}
	}
	
	/**
	 * @jsp.attribute required="false" rtexprvalue="true"
	 */
	public void setCheckedNames(String checkedNames){
		try {
			this.checkedNames = ExpressionEvaluationUtils.evaluateString("checkedNames", checkedNames, pageContext);
		}
		catch (JspException e) {
			this.checkedNames = checkedNames;
		}
	}
	
	/**
	 * @jsp.attribute required="false" rtexprvalue="false"
	 */
	public void setInputClass(String inputClass){
		this.inputClass=inputClass;
	}

	/**
	 * @jsp.attribute required="false" rtexprvalue="false"
	 */
	public void setData(String data){
		this.data=data;
	}
	
	/**
	 * @jsp.attribute required="false" rtexprvalue="true"
	 */
	public void setExprData(String exprData) {
		try {
			this.exprData = ExpressionEvaluationUtils.evaluateString("exprData", exprData, pageContext);
		} catch (JspException e) {
			this.exprData = exprData;
			log.error(e.getMessage());
		}
	}
	
	/**
	 * @jsp.attribute required="false" rtexprvalue="false"
	 */
	public void setOnChange(String onChange){
		this.onChange=onChange;
	}
	
	protected static Logger log = LoggerFactory.getLogger(JqmCheckAbstractTag.class);
}
