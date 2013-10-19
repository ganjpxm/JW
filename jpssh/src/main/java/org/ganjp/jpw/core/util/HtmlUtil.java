/**
 * $Id: StringUtil.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.core.util;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>String Utility for internal use.</p>
 *
 * @author GanJianping
 * @since 1.0
 */
public class HtmlUtil {
	/**
	 * <p>getJqmAddButton</p>
	 * 
	 * @param url
	 * @return
	 */
    public static String getJqmAddBtn(String url, String jqmTheme, ResourceBundle i18nMessage) {
    	//data-iconpos='notext'
    	return "<a href=\"" + url + "\" data-role='button' data-icon='jp-add' rel='external' data-mini='true' " +
    			"data-inline='true' style='float:right;margin-top:-6px;'>" + i18nMessage.getString("button.add") + "</a>";
    }

    public static String getJqmAedBtn(String addUrl, String editUrl, String deleteUrl, String jqmTheme,
    		ResourceBundle i18nMessage) {
    	StringBuffer sb = new StringBuffer();
//    	sb.append("<div data-role='controlgroup' data-type='horizontal'>");
    	sb.append("<a href=\"").append(deleteUrl).append("\" data-role='button' data-icon='jp-delete' rel='external' ")
		.append("data-mini='true' data-inline='true' data-mini='true' style='float:right;margin-top:-6px;'>" + "&nbsp;" + "</a>");//i18nMessage.getString("button.delete")
    	sb.append("<a href=\"").append(editUrl).append("\" data-role='button' data-icon='jp-edit' rel='external' ")
				.append("data-mini='true' data-inline='true' data-mini='true' style='float:right;margin-top:-6px;'>" + "&nbsp;" + "</a>");//i18nMessage.getString("button.edit")
    	sb.append("<a href=\"").append(addUrl).append("\" data-role='button' data-icon='jp-add' rel='external' ")
		.append("data-mini='true' data-inline='true' style='float:right;margin-top:-6px;'>" + "&nbsp;" + "</a>");//i18nMessage.getString("button.add")
    	
//    	sb.append("</div>");
    	return sb.toString();
    }

	private static Logger log = LoggerFactory.getLogger(StringUtil.class);
	public static void main(String[] args) {
	
	}
}
