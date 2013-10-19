/**
 * $Id: Config.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

import org.ganjp.jpw.am.model.AmUser;
import org.ganjp.jpw.bm.model.BmParam;
import org.ganjp.jpw.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>store sytem's configuration</p> 
 *
 * @author GanJianping
 * @since 1.0
 */
public class Config {
	/**
	 * <p>get value by key from system config file</p>
	 * 
	 * @param key eg:"timestamp.format"
	 * @return String
	 */
	public static String getValue(String key) {
		try {
			String value = RESOURCE_BUNDLE.getString(key).trim();
			log.debug("get " + key + " = " + value + " from " + Const.PROJECT_NAME + ".properties ");
			return value;
		} catch (MissingResourceException e) {
			log.error(Const.PROJECT_NAME + ".properties doesn't exist key = " + key );
			return null;
		}
	}

    public static String getJsSuffix() {
        return getValue(Const.JS_SUFFIX);
    }
    
    public static String getCssSuffix() {
        return getValue(Const.CSS_SUFFIX);
    }
    
    public static String getDefaultJpTheme() {
        return getValue(Const.JP_THEME);
    }

    public static String getDefaultJqmTheme() {
        return getValue(Const.JQM_THEME);
    }
    
    public static String getDefaultLanguage() {
        return getValue(Const.LANGUAGE);
    }

    public static void setCashFromDb(final String key, final Object value) {
		cashFromDbMap.put(key, value);
	}
    
	public static Object getCashFromDb(final String key) {
		return cashFromDbMap.get(key);
	}
	
	public static List getCashParamMaps(String paramType, String lang) {
		return (List)cashParamMap.get(paramType+lang);
	}

	public static void setCashParamMap(Map cashParamMap) {
		Config.cashParamMap = cashParamMap;
	}

	public static void setCashMenuMap(Map cashMenuMap) {
		Config.cashMenuMap = cashMenuMap;
	}
	
	public static Map getCashMenuMap() {
		return Config.cashMenuMap;
	}
	
	public static String getCashValue(final String key1, final String key2) {
		return ((Map)cashFromDbMap.get(key1)).get(key2).toString();
	}

	public static Map getAllCashParamIdNames() {
		Map map = new HashMap();
		if (cashParamMap != null) {
			Collection collect = cashParamMap.values();
			for (Iterator iterator = collect.iterator(); iterator.hasNext();) {
				List paramIdNameArrs = (List) iterator.next();
				for (Iterator iterator2 = paramIdNameArrs.iterator(); iterator2.hasNext();) {
					String[] paramIdNameArr = (String[]) iterator2.next();
					map.put(paramIdNameArr[0], paramIdNameArr[1]);
				}
			}
		}
		return map;
	}
	
	public static Locale getLocale(String lang) {
		Locale locale = null;
		if (Const.LANGUAGE_ZH_CN.equalsIgnoreCase(lang)) {
			locale = Locale.SIMPLIFIED_CHINESE;
		} else {
			locale = Locale.ENGLISH;
		}
		return locale;
	}

	public static Map getParamMap(String paramTypeCd, String lang, String paramCd) {
		List paramMaps = getCashParamMaps(paramTypeCd, lang);
		for (Iterator iterator = paramMaps.iterator(); iterator.hasNext();) {
			Map map = (Map) iterator.next();
			if (paramCd.equals(map.get("paramCd").toString())) {
				return map;
			}
		}
		return new HashMap(); 
	}

	public static List getMenuMaps(String menuCategoryId, String lang) {
		return (List)cashMenuMap.get(menuCategoryId+lang);
	}
	
	public static List getMenuMaps(String menuCategoryId, String lang, String menuPid) {
		List menuMaps = new ArrayList();
		List cashMenuMaps = (List)cashMenuMap.get(menuCategoryId+lang);
		for (Iterator iterator = cashMenuMaps.iterator(); iterator.hasNext();) {
			Map map = (Map) iterator.next();
			String cashMenuPid = StringUtil.toString(map.get("menuPid"));
			if (menuPid==null) {
				if (!StringUtil.hasText(cashMenuPid)) {
					menuMaps.add(map);
				}
			} else {
				if (menuPid.equals(cashMenuPid)) {
					menuMaps.add(map);
				}
			}
		}
		return menuMaps;
	}
	
	public static String getMenuPid(String menuCategoryId, String lang, String menuId) {
		List cashMenuMaps = (List)cashMenuMap.get(menuCategoryId+lang);
		for (Iterator iterator = cashMenuMaps.iterator(); iterator.hasNext();) {
			Map map = (Map) iterator.next();
			String cashMenuId = StringUtil.toString(map.get("menuId"));
			if (cashMenuId.equals(menuId)) {
				return StringUtil.toString(map.get("menuPid"));
			}
		}
		return null;
	}

	public static List getMenuMapsByParamCd(String paramCd, String lang) {
		Map<String,String> map = Config.getParamMap(BmParam.PARAM_TYPE_CD_MENU_CATEGORY, lang, paramCd);
		String menuCategoryId = StringUtil.toString(map.get("paramId")); 
		return getMenuMaps(menuCategoryId, lang);
	}
	
	public static List getPmenuMapAndMenuMapss(String paramCd, String lang, String ownRoleIds) {
		List menuMaps = getMenuMapsByParamCd(paramCd, lang);
		List menuMapAndMenuMaps = new ArrayList();
		List firstLevelMenuMaps = new ArrayList();
		Map menuPidAndMenuMap = new HashMap();
		String nextLinkCategoryMenuCd = "";
		String menuCategoryId = "";
		if (menuMaps!=null) {
			for (Iterator iterator = menuMaps.iterator(); iterator.hasNext();) {
				Map menuMap = (Map) iterator.next();
				String menuPid = StringUtil.toString(menuMap.get("menuPid"));
				String roleIds = StringUtil.toString(menuMap.get("roleIds"));
				if (StringUtil.isEmpty(nextLinkCategoryMenuCd) && menuMap.get("menuCd")!=null) {
					nextLinkCategoryMenuCd = StringUtil.toString(menuMap.get("menuCd"));
				}
				if (StringUtil.isEmpty(menuCategoryId) && menuMap.get("menuCategoryId")!=null) {
					menuCategoryId = StringUtil.toString(menuMap.get("menuCategoryId"));
				}
				if (!StringUtil.aCanAccessB(ownRoleIds, roleIds)) {
					continue;
				}
				if (StringUtil.isEmpty(menuPid)) {
					firstLevelMenuMaps.add(menuMap);
				} else {
					if (menuPidAndMenuMap.containsKey(menuPid)) {
						((List)menuPidAndMenuMap.get(menuPid)).add(menuMap);
					} else {
						List list = new ArrayList();
						list.add(menuMap);
						menuPidAndMenuMap.put(menuPid, list);
					}
				}
			}
			for (Iterator iterator = firstLevelMenuMaps.iterator(); iterator.hasNext();) {
				Map firstLevelMenuMap = (Map) iterator.next();
				String menuId = StringUtil.toString(firstLevelMenuMap.get("menuId"));
				String menuName = StringUtil.toString(firstLevelMenuMap.get("menuName"));
				Map map = new HashMap();
				List list = (List)menuPidAndMenuMap.get(menuId);
				map.put(firstLevelMenuMap, list);
				if (list==null) {
					firstLevelMenuMap.put("nextDisplayNo", 1);
					firstLevelMenuMap.put("nextMenuCd", (String)firstLevelMenuMap.get("menuCd")+"01");
				} else {
					int size = list.size();
					String nextMenuCd = (String)firstLevelMenuMap.get("menuCd");
					firstLevelMenuMap.put("nextDisplayNo", size+1);
					if (size++<10) nextMenuCd += "0" + size; else nextMenuCd += size;
					firstLevelMenuMap.put("nextMenuCd", nextMenuCd);
				}
				
				menuMapAndMenuMaps.add(map);
			}
			
			Map map = new HashMap();
			int size = menuMapAndMenuMaps.size();
			if (size++<10) {
				nextLinkCategoryMenuCd = nextLinkCategoryMenuCd.substring(0, 2) + "0" + size;
			} else {
				nextLinkCategoryMenuCd = nextLinkCategoryMenuCd.substring(0, 2) + size;
			}
			map.put("nextLinkCategoryMenuCd", nextLinkCategoryMenuCd);
			map.put("nextLinkCategoryMenuSize", size);
			map.put("menuCategoryId", menuCategoryId);
			menuMapAndMenuMaps.add(map);
		}
		return menuMapAndMenuMaps;
	}
	
	public static Map getLevelAndMenuMapss(String paramCd, String lang, String ownRoleIds, String activeMenuId) {
		List menuMaps = getMenuMapsByParamCd(paramCd, lang);
		List firstLevelMenuMaps = new ArrayList();
		List secondLevelMenuMaps = new ArrayList();
		if (menuMaps!=null) {
			for (Iterator iterator = menuMaps.iterator(); iterator.hasNext();) {
				Map menuMap = (Map) iterator.next();
				String menuPid = StringUtil.toString(menuMap.get("menuPid"));
				String roleIds = StringUtil.toString(menuMap.get("roleIds"));
				if (!StringUtil.aCanAccessB(ownRoleIds, roleIds)) {
					continue;
				}
				if (StringUtil.isEmpty(menuPid)) {
					firstLevelMenuMaps.add(menuMap);
				} else if (menuPid.equals(activeMenuId)) {
					secondLevelMenuMaps.add(menuMap);	
				}
			}
		}
		Map levelAndMenuMapss = new HashMap();
		levelAndMenuMapss.put("first", firstLevelMenuMaps);
		if (!secondLevelMenuMaps.isEmpty()) {
			levelAndMenuMapss.put("second", secondLevelMenuMaps);
		}
		return levelAndMenuMapss;
	}
	
	
    private static Logger log = LoggerFactory.getLogger(Config.class);
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(Const.PROJECT_NAME);
	private static Map cashFromDbMap = new HashMap();
	private static Map cashParamMap = null;
	private static Map cashMenuMap = null;
	public static ConcurrentHashMap<String, AmUser> loginAmUserMap = new ConcurrentHashMap<String, AmUser>();
}