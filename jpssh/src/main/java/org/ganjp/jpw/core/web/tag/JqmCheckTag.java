package org.ganjp.jpw.core.web.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;

import org.ganjp.jpw.core.Config;
import org.springframework.web.util.ExpressionEvaluationUtils;

/**
 * @jsp.tag name="jqmCheck" bodycontent="empty"
 */
public class JqmCheckTag extends JqmCheckAbstractTag {
	protected String paramTypeCd; //careProgramme
	protected String hiddenName;
	protected String filterCd;
	protected String needInput; //yes
	protected String inputValue; //yes
	
	public int doStartTag() throws JspException {
		StringBuffer htmlSb = new StringBuffer();
		List paramMaps = null;
		String checkedName = "";
		if (data==null && exprData==null) { 
			paramMaps = Config.getCashParamMaps(paramTypeCd,lang);
			if (paramMaps==null || paramMaps.isEmpty()) {
				log.error("BmParam's type(" + paramTypeCd +") is not exist!");
			}
		} else {
			if (exprData!=null) data=exprData;
			if (data!=null && data.indexOf(":")!=-1) {
				paramMaps = new ArrayList();
				String[] datas = data.split(";");
				for (int i = 0; i < datas.length; i++) {
					String paramIdNames = datas[i];
					String[] paramIdNameArr = paramIdNames.split(":");
					Map map = new HashMap();
					map.put("paramId", paramIdNameArr[0]);
					map.put("paramCd", paramIdNameArr[0]);
					map.put("paramName", paramIdNameArr[1]);
					paramMaps.add(map);
				}
			}
		}
		if (paramMaps!=null && paramMaps.size()>0) {
			this.prefix(htmlSb);
			for (int i = 0; i < paramMaps.size(); i++) {
				Map paramMap = (Map)paramMaps.get(i);
				String paramId = String.valueOf(paramMap.get("paramId"));
				String paramCd = String.valueOf(paramMap.get("paramCd"));
				String paramName = String.valueOf(paramMap.get("paramName"));
				if (filterCd!=null && paramCd.indexOf(filterCd)==-1) {
					 continue;
				}
				htmlSb.append("  <label><input ");
				htmlSb.append(" type='").append(checkType).append("'");
				htmlSb.append(" id='").append(paramCd).append("'");
				htmlSb.append(" paramName='").append(paramName).append("'");
				htmlSb.append(" name='").append(name).append("'");
				if (inputClass != null) {
					htmlSb.append(" class='").append(inputClass).append("'");
				}
				if ((checkedIds!=null && checkedIds.indexOf(paramId)!=-1) || 
					(checkedNames!=null && checkedNames.indexOf(paramName)!=-1)) {
					checkedName = paramName;
					htmlSb.append("checked='checked'");
				}
				if(onChange != null) {
		        	htmlSb.append(" onchange='").append(onChange).append("'");
		    	} else {
		    		if(needInput != null && needInput.equalsIgnoreCase("yes")) {
		    			htmlSb.append(" onchange=\"showInput('").append(name).append("','").append(hiddenName).append("')\"");
					}
		    	}
				
				htmlSb.append(" value='").append(paramId).append("'>").append(paramName).append("</label>");
			}
			this.suffix(htmlSb);
			if (hiddenName != null) {
				htmlSb.append("<input type='text'");
				if (checkedName.equals("Others (please specify)")) {
					htmlSb.append(" value='").append(inputValue).append("' ");
				} else {
					htmlSb.append(" style='display:none' ");
				}
				htmlSb.append("id='").append(hiddenName).append("' name='").append(hiddenName).append("'");
				
				htmlSb.append("/>");
			}
		} else {
			htmlSb = new StringBuffer("");
		}
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
	 * @jsp.attribute required="false" rtexprvalue="true"
	 */
	public void setParamTypeCd(String paramTypeCd){
		try {
			this.paramTypeCd = ExpressionEvaluationUtils.evaluateString("paramTypeCd", paramTypeCd, pageContext);
		} catch (JspException e) {
			this.paramTypeCd = paramTypeCd;
			log.error(e.getMessage());
		}
	}
	
	/**
	 * @jsp.attribute required="false" rtexprvalue="true"
	 */
	public void setHiddenName(String hiddenName){
		try {
			this.hiddenName = ExpressionEvaluationUtils.evaluateString("hiddenName", hiddenName, pageContext);
		} catch (JspException e) {
			this.hiddenName = hiddenName;
		}
	}

	/**
	 * @jsp.attribute required="false" rtexprvalue="false"
	 */
	public void setNeedInput(String needInput) {
		this.needInput = needInput;
	}

	/**
	 * @jsp.attribute required="false" rtexprvalue="false"
	 */
	public void setFilterCd(String filterCd) {
		this.filterCd = filterCd;
	}
	
	/**
	 * @jsp.attribute required="false" rtexprvalue="true"
	 */
	public void setInputValue(String inputValue) {
		try {
			this.inputValue = ExpressionEvaluationUtils.evaluateString("inputValue", inputValue, pageContext);
		} catch (JspException e) {
			this.inputValue = inputValue;
		}
	}

}
