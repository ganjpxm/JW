/**
 * $Id: JqmListviewTag.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */

package org.ganjp.jpw.core.web.tag;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;

import org.ganjp.jpw.core.Config;
import org.ganjp.jpw.core.Const;

/**
 * @jsp.tag name="jqmListview" bodycontent="empty"
 */
public class JqmListviewTag extends MenuTag {
	private static final long serialVersionUID = 8077023501356593651L;
	
	public int doStartTag() throws JspException {
		StringBuffer htmlMenuSB = new StringBuffer();
		List menuIdNameUrls = (List)Config.getCashFromDb(Const.DB_MENU_ID_NAME_URL_LIST);
		Map roleIdAndMenuIdsMap = (Map)Config.getCashFromDb(Const.DB_ROLE_ID_MENUE_IDS_MAP);
		String menuIds = "";
		Object roleIdsObj = request().getSession().getAttribute("roleIds");
		if (roleIdsObj==null) {
			return (SKIP_BODY);
		}
		List roleIds = (List)roleIdsObj;
		for (Iterator iterator = roleIds.iterator(); iterator.hasNext();) {
			String roleId = (String) iterator.next();
			menuIds += roleIdAndMenuIdsMap.get(roleId) + ",";
		}
			
		htmlMenuSB.append("<ul data-role='listview' data-inset='true' data-theme='c'>");
		for (Iterator iterator = menuIdNameUrls.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			String menuId = objects[0].toString();
			String menuName = objects[1].toString();
			String url = objects[2].toString();
			if (menuIds.indexOf(menuId)!=-1) {
				htmlMenuSB.append("<li");
				if (menuId.equals(activeMenuId)) {
					htmlMenuSB.append(" data-theme='a'");
				}
				htmlMenuSB.append("><a href=\"javascript:toPage('").append(getBasePath()).append(url).append("')\">");
				htmlMenuSB.append(menuName);
				htmlMenuSB.append("</a></li>");
			}
		}
		htmlMenuSB.append("</ul>");
		try {
			pageContext.getOut().write(htmlMenuSB.toString());
		}
		catch (IOException io) {
			io.printStackTrace();
			throw new JspException("Error writing label: " + io.getMessage());
		}
		return (SKIP_BODY);
	}
}
