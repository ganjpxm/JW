/**
 * $Id: CmCategoryManager.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.service;

import java.util.List;
import java.util.Map;

import org.ganjp.jpw.cm.model.CmCategory;

/**
 * <p>CmCategoryManager</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface CmCategoryManager {
		//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmCategory</p>
	 * 
	 * @param T
	 */
	public void save(CmCategory cmCategory);
	/**
	 * <p>update new cmCategory</p>
	 * 
	 * @param T
	 */
	public void update(CmCategory cmCategory);
	/**
	 * <p>save or update new cmCategory</p>
	 * 
	 * @param T
	 */
	public void saveOrUpdate(CmCategory cmCategory);
	
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmCategory</p>
	 * 
	 * @param cmCategory the cmCategory must be from session or transient object that has primary key attribute
	 */
	public void delete(CmCategory cmCategory);
	/**
	 * <p>delete a cmCategory by primary key</p>
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
	//------------return CmCategory
/**
	 * <p>get CmCategory object by primary key</p>
	 * 
	 * @param PK
	 * @return CmCategory
	 */
	public CmCategory getCmCategoryById(final String id);
	
	/**
	 * <p>get all CmCategory objects</p>
	 *
	 * @return List<CmCategory>
	 */
	public List<CmCategory> getCmCategorys();
	
	/**
	 * <p>get all CmCategory records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmCategory>
	 */
	public List<CmCategory> getCmCategorysWithOrder(String orderBy, boolean isAsc);
	
	/**
	 * <p>get CmCategory objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmCategory>
	 */
	public List<CmCategory> getCmCategorysByField(final String fieldName, final Object value);
	
	/**
	 * <p>getAllCategoryHtml</p>
	 * 
	 * @param lang
	 * @param jqmTheme
	 * @return
	 */
	public String getAllCategoryHtml(final String lang, final String jqmTheme);
	
	/**
	 * <p>getCmCategory</p>
	 * 
	 * @param categoryCd
	 * @param lang
	 * @return
	 */
	public CmCategory getCmCategory(final String categoryCd, final String lang);
	
	/**
	 * <p>getCategoryId</p>
	 * 
	 * @param categoryCd
	 * @param lang
	 * @return
	 */
	public String getCategoryId(final String categoryCd, final String lang);
	
	/**
	 * <p>getCategoryPidsBtnsMap</p>
	 * 
	 * @param categoryId
	 * @param baseUrl
	 * @return
	 */
	public Map<String,String> getCategoryPidsBtnsMap(String categoryId, String baseUrl);
	
	/**
	 * <p>getCategoryMaps</p>
	 * 
	 * @param categoryPid
	 * @param ownRoleIds
	 * @return
	 */
	public List<Map<String,String>> getCategoryMaps(String categoryPid, String ownRoleIds);
	
	/**
	 * <p>getCategorysForJqmCheck</p>
	 * 
	 * @param categoryCd
	 * @param lang
	 * @return
	 */
	public String getCategorysForJqmCheck(String categoryCd, String lang);
	
	/**
	 * <p>getCategoryJqmHtml</p>
	 * 
	 * @param activeCategoryId
	 * @param categoryCd
	 * @param url
	 * @param lang
	 * @param jqmTheme
	 * @param ownRoleIds
	 * @return
	 */
	public String getCategoryJqmHtml(final String activeCategoryId, final String categoryCd, final String url, 
			final String lang, final String jqmTheme, String ownRoleIds); 
	
	/**
	 * <p>getCategoryCheckboxHtml</p>
	 * 
	 * @param categoryCd
	 * @param lang
	 * @param ownRoleIds
	 * @return
	 */
	public String getCategoryCheckboxHtml(final String categoryCd, final String lang, String ownRoleIds);
}