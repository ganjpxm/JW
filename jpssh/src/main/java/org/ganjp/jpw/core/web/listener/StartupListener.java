/**
 * $Id: StartupListener.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.core.web.listener;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.ganjp.jpw.am.service.AmRoleManager;
import org.ganjp.jpw.bm.model.BmConfig;
import org.ganjp.jpw.bm.service.BmConfigManager;
import org.ganjp.jpw.bm.service.BmMenuManager;
import org.ganjp.jpw.bm.service.BmParamManager;
import org.ganjp.jpw.core.Config;
import org.ganjp.jpw.core.Const;
import org.ganjp.jpw.core.util.RackspaceCloudFilesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * <p>set globle param when web server start, extends spring's ContextLoaderListener</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public class StartupListener extends ContextLoaderListener implements ServletContextListener {
	/**
	 * <p>set default jqmTheme, jpTheme,  cssSuffix, jsSuffix and language to ServletContext</p>
	 * 
	 * @param ServletContext
	 */
	private void initValue(ServletContext servletContext) {
		servletContext.setAttribute(Const.CSS_SUFFIX, Config.getCssSuffix());
		servletContext.setAttribute(Const.JS_SUFFIX, Config.getJsSuffix());
		servletContext.setAttribute(Const.JP_THEME, Config.getDefaultJpTheme());
		log.debug("Set default jqmTheme, jpTheme, cssSuffix, jsSuffix and ln to ServletContext success");
	}
	
	public static void cashValue(ServletContext context) {
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
		
		AmRoleManager amRoleManager = (AmRoleManager) applicationContext.getBean("amRoleManagerImpl");
		Config.setCashFromDb(Const.DB_ROLE_ID_NAMES, amRoleManager.getRoleIdAndNames());
		
		BmParamManager bmParamManager = (BmParamManager) applicationContext.getBean("bmParamManagerImpl");
		Config.setCashParamMap(bmParamManager.getParamTypeCdLangAndParamMaps());
		BmMenuManager bmMenuManager = (BmMenuManager) applicationContext.getBean("bmMenuManagerImpl");
		Config.setCashMenuMap(bmMenuManager.getMenuCategoryIdLangAndMenuMaps());
		BmConfigManager bmConfigManager = (BmConfigManager) applicationContext.getBean("bmConfigManagerImpl");
		Map<String,String> configKeyAndValues = bmConfigManager.getConfigKeyAndValues();
		context.setAttribute(BmConfig.SHOW_USER_SURVEY_AVG_SCORE_FOR_LEADER, configKeyAndValues.get(BmConfig.SHOW_USER_SURVEY_AVG_SCORE_FOR_LEADER)==null?"off":configKeyAndValues.get(BmConfig.SHOW_USER_SURVEY_AVG_SCORE_FOR_LEADER));
		context.setAttribute(BmConfig.AUDIO_URL, configKeyAndValues.get(BmConfig.AUDIO_URL)==null?RackspaceCloudFilesUtil.URL_HTTPS_AUDIO:configKeyAndValues.get(BmConfig.AUDIO_URL));
		context.setAttribute(BmConfig.IMAGE_URL, configKeyAndValues.get(BmConfig.IMAGE_URL)==null?RackspaceCloudFilesUtil.URL_HTTPS_IMAGE:configKeyAndValues.get(BmConfig.IMAGE_URL));
		String videoUrl = configKeyAndValues.get(BmConfig.VIDEO_URL)==null?RackspaceCloudFilesUtil.URL_HTTPS_VIDEO:configKeyAndValues.get(BmConfig.VIDEO_URL);
		context.setAttribute(BmConfig.VIDEO_URL, videoUrl);
		context.setAttribute(BmConfig.FILE_URL, configKeyAndValues.get(BmConfig.FILE_URL)==null?RackspaceCloudFilesUtil.URL_HTTPS_FILE:configKeyAndValues.get(BmConfig.FILE_URL));
		context.setAttribute(BmConfig.SINGLE_SIGN_ON, configKeyAndValues.get(BmConfig.SINGLE_SIGN_ON)==null?"off":configKeyAndValues.get(BmConfig.SINGLE_SIGN_ON));
//		Email.HOST=configKeyAndValues.get("emailHost");
//		Email.PORT= new Integer(configKeyAndValues.get("emailHostPort"));
//		Email.USER_NAME=configKeyAndValues.get("emailUserName");
//		Email.PASSWORD=configKeyAndValues.get("emailUserPassword");
//		Config.setCashFromDb(Const.DB_GLOBLE_CONFIGS, configKeyAndValues);
		
//		AmRoleMenuManager amRoleMenuManager = (AmRoleMenuManager) applicationContext.getBean("amRoleMenuManagerImpl");
//		Config.setCashFromDb(Constant.DB_ROLE_ID_MENUE_IDS_MAP, amRoleMenuManager.getRoleIdAndMenuIdss());
		log.debug("Cash database data success");
	}

	/**
	 * <p>init all system param and load some information to memoery, call this method when web server start</p>
	 * 
	 * @param ServletContextEvent
	 */
	public void contextInitialized(ServletContextEvent event) {
		log.debug("Initializing start");
		ServletContext servletContext = event.getServletContext();
		super.contextInitialized(event);
		cashValue(servletContext);
		initValue(servletContext);
		log.debug("Initializing complete [OK]");
	}

	private static final Logger log = LoggerFactory.getLogger(StartupListener.class);
	
	private static ApplicationContext applicationContext;
}
