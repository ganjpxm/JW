/**
 * $Id: Constants.java,v 1.0 2012/04/14 09:24:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Illume Technology. All rights reserved
 * Coss Project
 *
 */
package org.ganjp.jpw.core;

/**
 * <p>define constant</p>
 * 
 * @author ganjianping
 * @since 1.0
 */
public class Const {
	//------------------------------ java system constant ---------------------
    public static final String FILE_SEP = System.getProperty("file.separator");
    public static final String PATH_SEP = System.getProperty("path.separator");
    public static final String LINE_SEP = System.getProperty("line.separator");
    public static final String SYSTEM_USER_NAME = System.getProperty("user.name");
    public static final String SYSTEM_USER_HOME = System.getProperty("user.home");
    public static final String SYSTEM_USER_DIR = System.getProperty("user.dir");
    public static final String JAVA_VERSION = System.getProperty("java.version");
    public static final String JAVA_VENDOR = System.getProperty("java.vendor");
    public static final String JAVA_HOME = System.getProperty("java.home");
    public static final String JAVA_IO_TMP_DIR = System.getProperty("java.io.tmpdir");
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String OS_VERSION = System.getProperty("os.version");
    
	//------------------------------ define project constant ---------------------
    public static final String PROJECT_NAME = "jpw";
    public static final String PROJECT_VERSION = "1.0.0";
    public static final String MESSAGE_BUNDLE_NAME = "i18n" + FILE_SEP + "messages";
    public static final String JP_THEME = "jpTheme";
    public static final String JQM_THEME = "jqmTheme";
    public static final String LANGUAGE = "lang";
    public static final String LANGUAGE_ZH_CN = "zh_CN";
    public static final String LANGUAGE_EN_SG = "en_SG";
    public static final String CSS_SUFFIX = "cssSuffix";
    public static final String JS_SUFFIX = "jsSuffix";
    public static final String ENCODING = "encoding";
    public static final String HEAD_FILTER = "headers";//used by AddHeaderFilter.java
    public static final String EXCLUDE_PATTERNS = "excludePatterns";//used by AddHeaderFilter.java
    public static final String PROJECT_WEBSITE_NAVIGATE = "websiteNavigate";
    public static final String PROJECT_KNOWLEDGE = "knowledge";
    public static final String PROJECT_AUDIO = "audio";
    public static final String PROJECT_PHOTO = "photo";
    public static final String PROJECT_VIDEO = "video";
    public static final String PROJECT_ECOMMERCE = "ecommerce";
    public static final String PROJECT_DOWNLOAD = "download";
    public static final String PROJECT_DEMO = "demo";
    public static final String ROLE_NO_NEED = "noNeedRole";
    
    public static final String PROJECT_GROUPS = "groups";
    public static final String PROJECT_SURVEY = "survey";
    public static final String PROJECT_ABOUT_US = "aboutUs";
    public static final String PROJECT_PROFILE = "profile";
	
	
	// ------------------------ database information -------------------------------------------
	public static final String DB_DATASTATE_NORMAL = "0";
	public static final String DB_DATASTATE_DELETE = "1";
	public static final String DB_ORDER_TYPE_ASC = "asc";
	public static final String DB_ORDER_TYPE_DESC = "desc";
	public static final String HIBERNATE_DIALECT = "hibernate.dialect";
	public static final String DB_URL = "db.url";
	public static final String DB_DRIVER_CLASS = "db.driver_class";
	public static final String DB_USER_NAME = "db.username";
	public static final String DB_PASSWORD = "db.schema";
	public static final String DB_ROLE_ID_NAMES = "dbRoleIdNames";
	public static final String DB_GLOBLE_CONFIGS = "dbGlobleConfigs";
	public static final String DB_MENU_ID_NAME_URL_LIST = "dbMenuIdNameUrls";
	public static final String DB_ROLE_ID_MENUE_IDS_MAP = "dbRoleIdAndMenuIdss";
	public static final String ACTIVE_MENU_ID = "activeMenuId";
	public static final String ROLE_IDS = "roleIds";
	public static final String USER = "user";
	public static final String ACTIVE_SESSIONS = "activeSessions";
	public static final String IS_ADMIN = "isAdmin";
	public static final String IS_MANAGER = "isManager";
	
	public static final String PARAM_TYPE_EVALUATE_ITEM_VALUE_TYPE = "EvaluateItemValueType";
	
    //------------------------------ message constant defination ------------------------------
	public static final String MESSAGE_INFO = "info";
    public static final String MESSAGE_ERROR = "error";
    public static final String MESSAGE_WARNING = "warning";
    public static final String MESSAGE_SUCCESS = "success";
    public static final String MESSAGE_INFO_FOOTER = "infoFooter";
    public static final String MESSAGE_ERROR_FOOTER = "errorFooter";
    public static final String MESSAGE_WARNING_FOOTER = "warningFooter";
    public static final String MESSAGE_SUCCESS_FOOTER = "successFooter";
    
    public static void main(String arg[]) {
    	System.out.println(FILE_SEP);
    	System.out.println(PATH_SEP);
    	System.out.println(LINE_SEP);
        System.out.println(System.getProperty("file.separator"));
        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("java.vendor"));
        System.out.println(System.getProperty("java.home"));
        System.out.println(System.getProperty("java.io.tmpdir"));
        System.out.println(System.getProperty("java.home"));
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.version"));
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("user.dir"));
        System.out.println(MESSAGE_BUNDLE_NAME);
    }

}

