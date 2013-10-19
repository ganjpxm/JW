/**
 * $Id: BmMenuManager.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.bm.service;

import java.util.List;
import java.util.Map;

import org.ganjp.jpw.bm.model.BmMenu;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>BmMenuManager</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface BmMenuManager {
		//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new bmMenu</p>
	 * 
	 * @param T
	 */
	public void save(BmMenu bmMenu);
	/**
	 * <p>update new bmMenu</p>
	 * 
	 * @param T
	 */
	public void update(BmMenu bmMenu);
	/**
	 * <p>save or update new bmMenu</p>
	 * 
	 * @param T
	 */
	public void saveOrUpdate(BmMenu bmMenu);
	
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the bmMenu</p>
	 * 
	 * @param bmMenu the bmMenu must be from session or transient object that has primary key attribute
	 */
	public void delete(BmMenu bmMenu);
	/**
	 * <p>delete a bmMenu by primary key</p>
	 * 
	 * @param PK
	 */
	public void delete(final String pk);
	
	/**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
	public void batchDelete(final String pks);
	
	//-------------------------------------------   find   ------------------------------------------
	//------------return BmMenu
/**
	 * <p>get BmMenu object by primary key</p>
	 * 
	 * @param PK
	 * @return BmMenu
	 */
	public BmMenu getBmMenuById(final String id);
	
	/**
	 * <p>get all BmMenu objects</p>
	 *
	 * @return List<BmMenu>
	 */
	public List<BmMenu> getBmMenus();
	
	/**
	 * <p>get all BmMenu records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<BmMenu>
	 */
	public List<BmMenu> getBmMenusWithOrder(String orderBy, boolean isAsc);
	
	/**
	 * <p>get BmMenu objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<BmMenu>
	 */
	public List<BmMenu> getBmMenusByField(final String fieldName, final Object value);
	
	/**
	 * <p>get all data : Map<menuCategory+lang, List[map<string,string>]> </p>
	 * 
	 * @return 
	 */
	public Map<String, List<Map<String,String>>> getMenuCategoryIdLangAndMenuMaps();

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
	 * 
	 * @param baseUrl
	 * @param lang
	 * @param jqmTheme
	 * 
	 * @return
	 */
	public String getAllMenuHtml(final String baseUrl, final String lang, final String jqmTheme);
	
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
	public String getTwoLevelMenuHtml(final String activeMenuId, final String paramCd, final String baseUrl, 
			final String lang, final String jqmTheme);
}