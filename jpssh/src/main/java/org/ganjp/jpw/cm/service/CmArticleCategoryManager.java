/**
 * $Id: CmArticleCategoryManager.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.service;

import java.util.List;
import java.util.Map;

import org.ganjp.jpw.cm.model.CmArticle;
import org.ganjp.jpw.cm.model.CmArticleCategory;

/**
 * <p>CmArticleCategoryManager</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface CmArticleCategoryManager {
		//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmArticleCategory</p>
	 * 
	 * @param T
	 */
	public void save(CmArticleCategory cmArticleCategory);
	/**
	 * <p>update new cmArticleCategory</p>
	 * 
	 * @param T
	 */
	public void update(CmArticleCategory cmArticleCategory);
	/**
	 * <p>save or update new cmArticleCategory</p>
	 * 
	 * @param T
	 */
	public void saveOrUpdate(CmArticleCategory cmArticleCategory);
	
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmArticleCategory</p>
	 * 
	 * @param cmArticleCategory the cmArticleCategory must be from session or transient object that has primary key attribute
	 */
	public void delete(CmArticleCategory cmArticleCategory);
	/**
	 * <p>delete a cmArticleCategory by primary key</p>
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
	//------------return CmArticleCategory
/**
	 * <p>get CmArticleCategory object by primary key</p>
	 * 
	 * @param PK
	 * @return CmArticleCategory
	 */
	public CmArticleCategory getCmArticleCategoryById(final String id);
	
	/**
	 * <p>get all CmArticleCategory objects</p>
	 *
	 * @return List<CmArticleCategory>
	 */
	public List<CmArticleCategory> getCmArticleCategorys();
	
	/**
	 * <p>get all CmArticleCategory records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmArticleCategory>
	 */
	public List<CmArticleCategory> getCmArticleCategorysWithOrder(String orderBy, boolean isAsc);
	
	/**
	 * <p>get CmArticleCategory objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmArticleCategory>
	 */
	public List<CmArticleCategory> getCmArticleCategorysByField(final String fieldName, final Object value);
	
	/**
	 * <p>getArticleMaps</p>
	 * 
	 * @param categoryId
	 * @param ownRoleIds
	 * @return
	 */
	public List<Map<String,String>> getArticleMaps(String categoryId, String ownRoleIds);
	
	/**
	 * <p>saveCmArticleAndCategory</p>
	 * 
	 * @param cmArticle
	 * @param categoryIds
	 */
	public void saveCmArticleAndCategory(CmArticle cmArticle, String categoryIds);
	
	/**
	 * <p>getCategoryIds</p>
	 * 
	 * @param articleId
	 * @return
	 */
	public String getCategoryIds(final String articleId);

	/**
	 * <p>deleteByArticleId</p>
	 * 
	 * @param articleId
	 */
	public void deleteByArticleId(String articleId);
	
	/**
	 * <p>updateCmArticleAndCategory</p>
	 * 
	 * @param cmArticle
	 * @param categoryIds
	 */
	public void updateCmArticleAndCategory(CmArticle cmArticle, String categoryIds);
}