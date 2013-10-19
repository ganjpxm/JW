/**
 * $Id: BmMenuManagerImpl.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Coss Project
 *
 */
package org.ganjp.jpw.bm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.Resource;

import org.ganjp.jpw.bm.model.BmMenu;
import org.ganjp.jpw.bm.model.BmParam;
import org.ganjp.jpw.bm.service.BmMenuManager;
import org.ganjp.jpw.bm.service.BmParamManager;
import org.ganjp.jpw.core.Config;
import org.ganjp.jpw.core.Const;
import org.ganjp.jpw.core.dao.BaseDao;
import org.ganjp.jpw.core.model.PropertyFilter;
import org.ganjp.jpw.core.service.BaseManager;
import org.ganjp.jpw.core.util.HtmlUtil;
import org.ganjp.jpw.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>BmMenuManagerImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service 
public class BmMenuManagerImpl extends BaseManager implements BmMenuManager {
	
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new bmMenu</p>
	 * 
	 * @param BmMenu
	 */
	@Transactional
	public void save(BmMenu bmMenu) {
		dao.save(bmMenu);
	}

	/**
	 * <p>update new bmMenu</p>
	 * 
	 * @param BmMenu
	 */
	@Transactional
	public void update(BmMenu bmMenu) {
		dao.update(bmMenu);
	}
	/**
	 * <p>save or update new bmMenu</p>
	 * 
	 * @param BmMenu
	 */
	@Transactional
	public void saveOrUpdate(BmMenu bmMenu) {
		dao.saveOrUpdate(bmMenu);
	}
		
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the bmMenu</p>
	 * 
	 * @param bmMenu the bmMenu must be from session or transient object that has primary key attribute
	 */
	@Transactional
	public void delete(BmMenu bmMenu) {
		dao.delete(bmMenu);
	}
	/**
	 * <p>delete a bmMenu by primary key</p>
	 * 
	 * @param PK
	 */
	@Transactional
	public void delete(final String pk) {
		dao.delete(BmMenu.class, pk);
	}
	/**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
	@Transactional
	public void batchDelete(final String pks) {
		String hql = "delete from BmMenu where menuId in (" + StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
	}	
	//-------------------------------------------   find   ------------------------------------------
	//------------return BmMenu
	/**
	 * <p>get BmMenu object by primary key</p>
	 * 
	 * @param PK
	 * @return BmMenu
	 */
	@Transactional
	public BmMenu getBmMenuById(final String id) {
		BmMenu bmMenu = dao.findById(BmMenu.class, id);
		return bmMenu;
	}
	
	/**
	 * <p>get all BmMenu objects</p>
	 *
	 * @return List<BmMenu>
	 */
	@Transactional
	public List<BmMenu> getBmMenus() {
		return dao.findAllWithOrder(BmMenu.class, "modifyTimestamp", false);
	}
	
	/**
	 * <p>get all BmMenu objects and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<BmMenu>
	 */
	@Transactional
	public List<BmMenu> getBmMenusWithOrder(String orderBy, boolean isAsc)  {
		return dao.findAllWithOrder(BmMenu.class, orderBy, isAsc);
	}
	
	/**
	 * <p>get BmMenu objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<BmMenu>
	 */
	@Transactional
	public List<BmMenu> getBmMenusByField(final String fieldName, final Object value) {
		return dao.findByField(BmMenu.class, fieldName, value, "modifyTimestamp", false);
	}

	/**
	 * <p>get all data : Map<menuCategory+lang, List[map<string,string>]> </p>
	 * 
	 * @return 
	 */
	@Transactional
	public Map<String, List<Map<String,String>>> getMenuCategoryIdLangAndMenuMaps() {
		Map<String, List<Map<String,String>>>  menuCategoryIdAndMenuMaps = new HashMap<String, List<Map<String,String>>>();
		String hql = "select menuId, menuPid, menuName, url, imageUrl, menuCategoryId, lang, roleIds, menuCd from BmMenu order by displayNo";
		List<Object[]> objArrs = dao.findByHql(hql);
		for (Object[] objArr : objArrs) {
			String key = StringUtil.toString(objArr[5]) + StringUtil.toString(objArr[6]); 
			Map<String,String> map = new HashMap<String,String>();
			map.put("menuId", StringUtil.toString(objArr[0]));
			map.put("menuPid", StringUtil.toString(objArr[1]));
			map.put("menuName", StringUtil.toString(objArr[2]));
			map.put("url", StringUtil.toString(objArr[3]));
			map.put("imageUrl", StringUtil.toString(objArr[4]));
			map.put("menuCategoryId", StringUtil.toString(objArr[5]));
			map.put("roleIds", StringUtil.toString(objArr[7]));
			map.put("menuCd", StringUtil.toString(objArr[8]));
			if (menuCategoryIdAndMenuMaps.containsKey(key)) {
				menuCategoryIdAndMenuMaps.get(key).add(map);
			} else {
				List<Map<String,String>> menuMaps = new ArrayList<Map<String,String>>();
				menuMaps.add(map);
				menuCategoryIdAndMenuMaps.put(key, menuMaps);
			}
			
		}
		return menuCategoryIdAndMenuMaps;
	}
	
	/**
	 * <p>getMenuHtml</p>
	 * <pre>
	 *   <div data-role='collapsible-set'>
	 *     <div data-role='collapsible'>
	 *       <h3>title1</h3><p></p>
	 *     </div>
	 *     <div data-role='collapsible'>
	 *       <h3>title2</h3><p></p>
	 *       <div data-role='collapsible-set'>
	 *         <div data-role='collapsible'>
	 *           <h3>title2</h3>
	 *         </div>
	 *       </div>
	 *     </div>
	 *   </div>     
	 * </pre>
	 * @param baseUrl
	 * @param lang
	 * @param jqmTheme
	 * 
	 * @return
	 */
	@Transactional
	public String getAllMenuHtml(final String baseUrl, final String lang, final String jqmTheme) {
		List<BmParam> bmParams = bmParamManager.getBmParams(BmParam.PARAM_TYPE_CD_MENU_CATEGORY, lang);
		ResourceBundle i18nMessage = ResourceBundle.getBundle(Const.MESSAGE_BUNDLE_NAME, Config.getLocale(lang));
		StringBuffer htmlSb = new StringBuffer("");
		htmlSb.append("<div data-role='collapsible-set'>");
		int index = 0;
		for (BmParam bmParam : bmParams) {
			String menuCategoryId = bmParam.getParamId();
			List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
			filters.add(new PropertyFilter("EQS_lang", lang));
			filters.add(new PropertyFilter("EQS_menuCategoryId", bmParam.getParamId()));
			filters.add(new PropertyFilter("EQI_displayLevel", "1"));
			List<BmMenu> bmMenus1 = dao.findByPropertyFilterList(BmMenu.class, filters, "displayNo", true);
			//add(menuPid, menuCategoryId, displayLevel, menuCd)
			String menuCd = index + "" + (bmMenus1.size()+1) + "";
			String addUrl = "javascript:add(" + StringUtil.getStrWithQuote("",menuCategoryId,"1", menuCd) + ");";
			htmlSb.append("<div data-role='collapsible'>");
			htmlSb.append("  <h3>").append(++index).append(".").append(bmParam.getParamName()).append("</h3>");
			htmlSb.append("  &nbsp;").append(HtmlUtil.getJqmAddBtn(addUrl, jqmTheme, i18nMessage)).append("");
			htmlSb.append("  <div data-role='collapsible-set' style='margin-top:13px;'>");
			for (BmMenu bmMenu1 : bmMenus1) {
				List<BmMenu> bmMenus2 = dao.findByField(BmMenu.class, "menuPid", bmMenu1.getMenuId(), "displayNo", true);
				menuCd = bmMenu1.getMenuCd() + (bmMenus2.size()+1) + "";
				String addUrl1 = "javascript:add(" + StringUtil.getStrWithQuote(bmMenu1.getMenuId(),menuCategoryId,"2", menuCd) + ");";
				String editUrl1 = "javascript:edit('" + bmMenu1.getMenuId() + "');";
				String deleteUrl1 = "javascript:confirmDelete('" + bmMenu1.getMenuId() + "');";
				htmlSb.append("<div data-role='collapsible'>");
				htmlSb.append("  <h3>").append(bmMenu1.getMenuName()).append("(").append(bmMenu1.getMenuCd()).append(")");
				if (StringUtil.isNotEmpty(bmMenu1.getImageUrl())) {
					htmlSb.append("<img src='").append(baseUrl + bmMenu1.getImageUrl()).append("'  height='20'/>");
				}
				htmlSb.append("</h3>");
				htmlSb.append("  &nbsp;").append(HtmlUtil.getJqmAedBtn(addUrl1, editUrl1, deleteUrl1, jqmTheme, i18nMessage)).append("");
				if (StringUtil.isNotEmpty(bmMenu1.getUrl())) {
					htmlSb.append(i18nMessage.getString("bmMenu.url")).append(bmMenu1.getUrl());
				}
						
				htmlSb.append("  <div data-role='collapsible-set' style='margin-top:13px;'>");
				for (BmMenu bmMenu2 : bmMenus2) {
					List<BmMenu> bmMenus3 = dao.findByField(BmMenu.class, "menuPid", bmMenu2.getMenuId(), "displayNo", true);
					menuCd = bmMenu1.getMenuCd() + bmMenus3.size() + "";
					String addUrl2 = "javascript:add(" + 
							 StringUtil.getStrWithQuote(bmMenu2.getMenuId(), menuCategoryId, "3", menuCd) + ");";
					String editUrl2 = "javascript:edit('" + bmMenu2.getMenuId() + "');";
					String deleteUrl2 = "javascript:confirmDelete('" + bmMenu2.getMenuId() + "');";
					
					htmlSb.append("<div data-role='collapsible'>");
					htmlSb.append("  <h3>").append(bmMenu2.getMenuName()).append("(").append(bmMenu2.getMenuCd()).append(")");
					if (StringUtil.isNotEmpty(bmMenu1.getImageUrl())) {
						htmlSb.append("<img src='").append(baseUrl + bmMenu2.getImageUrl()).append("'  height='20'/>");
					}
					htmlSb.append("</h3>");
					htmlSb.append("  ").append(HtmlUtil.getJqmAedBtn(addUrl2, editUrl2, deleteUrl2, jqmTheme, i18nMessage)).append("");
					if (StringUtil.isNotEmpty(bmMenu2.getUrl())) {
						htmlSb.append(i18nMessage.getString("bmMenu.url") + " : ").append(bmMenu2.getUrl());
					}
					htmlSb.append("</div>");
				}
				htmlSb.append("  </div>");
				htmlSb.append("</div>");
			}
			htmlSb.append("  </div>");
			htmlSb.append("</div>");
		}
		htmlSb.append("</div>");
		return htmlSb.toString();
	}
	
	/**
	 * <p>getMenuHtml</p>
	 * <pre>
	 *   <div data-role='collapsible-set' data-theme='b' data-content-theme='d'>
	 *     <div data-role='collapsible'>
	 *       <h2>Filtered list</h2>
	 *       <ul data-role='listview' data-filter='true' data-filter-theme='c' data-divider-theme='d'>
	 *		   <li><a href='index.html'>Adam Kinkaid</a></li>
	 *		   <li><a href='index.html'>Alex Wickerham</a></li>
	 *		 </ul>
	 *     </div>
	 *   </div>     
	 * </pre>
	 * 
	 * @param lang activeMenuId BmParam.PARAM_CD_MANAGEMENT_CONSOLE
	 * @param lang
	 * @param jqmTheme
	 * 
	 * @return
	 */
	@Transactional
	public String getTwoLevelMenuHtml(final String activeMenuId, final String paramCd, final String baseUrl, 
			final String lang, final String jqmTheme) {
		Map paramMap = Config.getParamMap(BmParam.PARAM_TYPE_CD_MENU_CATEGORY, lang, paramCd);
		String menuCategoryId = (String)paramMap.get("paramId");
		String activeMenuPid = Config.getMenuPid(menuCategoryId, lang, activeMenuId);
		
		StringBuffer htmlSb = new StringBuffer("");
		htmlSb.append("<div data-role='collapsible-set' data-theme='").append("d").append("' data-content-theme='c'>");
		
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQS_lang", lang));
		filters.add(new PropertyFilter("EQS_menuCategoryId", menuCategoryId));
		List<Map<String,String>> menuMaps1 = Config.getMenuMaps(menuCategoryId, lang, null);
		for (Map<String,String> menuMap1 : menuMaps1) {
			htmlSb.append("<div data-role='collapsible' ");
			if (menuMap1.get("menuId").equals(activeMenuPid)) {
				htmlSb.append("data-collapsed='false' ");
			}
			htmlSb.append(">  <h2>").append(menuMap1.get("menuName")).append("</h2>");
			htmlSb.append("  <ul data-role='listview'>");
			List<Map<String,String>> menuMaps2 = Config.getMenuMaps(menuCategoryId, lang, menuMap1.get("menuId"));
			for (Map<String,String> menuMap2 : menuMaps2) {
				htmlSb.append("<li ");
				if (menuMap2.get("menuId").equals(activeMenuId)) {
					htmlSb.append("data-theme='").append(jqmTheme).append("'");
				}
				htmlSb.append("><a href=\"javascript:toPage('").append(baseUrl).append(menuMap2.get("url")).append("');\">")
								.append(menuMap2.get("menuName")).append("</a></li>");
			}
			htmlSb.append("  </ul>"); 
			htmlSb.append("</div>"); 
		}
		htmlSb.append("</div>");
		return htmlSb.toString();
	}

	/**
	 * <p>getBmMenus</p>
	 * 
	 * @param menuCd
	 * @param lang
	 * @return
	 */
	@Transactional
	public List<BmMenu> getBmMenus(final String menuCd, final String lang) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("LIKES_menuCd", menuCd + "%"));
		filters.add(new PropertyFilter("EQS_lang", lang));
		return dao.findByPropertyFilterList(BmMenu.class, filters);
	}

	@Resource(name="baseDaoHibernate")
	private BaseDao<BmMenu, String> dao;
	
	@Resource(name="bmParamManagerImpl")
	private BmParamManager bmParamManager;
}