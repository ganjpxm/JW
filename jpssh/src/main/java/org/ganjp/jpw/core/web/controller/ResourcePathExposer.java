/**
 * $Id: ResourcePathExposer.java,v 1.0 2012/04/14 09:24:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Illume Technology. All rights reserved
 * Coss Project
 *
 */
package org.ganjp.jpw.core.web.controller;

import javax.servlet.ServletContext;

import org.ganjp.jpw.core.Const;
import org.springframework.web.context.ServletContextAware;

public class ResourcePathExposer implements ServletContextAware {
	private ServletContext servletContext;
	private String resourceRoot;
	
	
	public void init() {
		resourceRoot = "/resources-" + Const.PROJECT_VERSION;
		getServletContext().setAttribute("resourceRoot", getServletContext().getContextPath() + resourceRoot);
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public String getResourceRoot() {
		return resourceRoot;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}	
}
