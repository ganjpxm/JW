/**
 * $Id: CmArticleManager.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.service;

import java.util.List;
import java.util.Map;

import org.ganjp.jpw.cm.model.CmArticle;
import org.ganjp.jpw.core.model.Page;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>CmArticleManager</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface CmArticleManager {
		//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmArticle</p>
	 * 
	 * @param T
	 */
	public void save(CmArticle cmArticle);
	/**
	 * <p>update new cmArticle</p>
	 * 
	 * @param T
	 */
	public void update(CmArticle cmArticle);
	/**
	 * <p>save or update new cmArticle</p>
	 * 
	 * @param T
	 */
	public void saveOrUpdate(CmArticle cmArticle);
	
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmArticle</p>
	 * 
	 * @param cmArticle the cmArticle must be from session or transient object that has primary key attribute
	 */
	public void delete(CmArticle cmArticle);
	/**
	 * <p>delete a cmArticle by primary key</p>
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
	//------------return CmArticle
/**
	 * <p>get CmArticle object by primary key</p>
	 * 
	 * @param PK
	 * @return CmArticle
	 */
	public CmArticle getCmArticleById(final String id);
	
	/**
	 * <p>get all CmArticle objects</p>
	 *
	 * @return List<CmArticle>
	 */
	public List<CmArticle> getCmArticles();
	
	/**
	 * <p>get all CmArticle records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmArticle>
	 */
	public List<CmArticle> getCmArticlesWithOrder(String orderBy, boolean isAsc);
	
	/**
	 * <p>get CmArticle objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmArticle>
	 */
	public List<CmArticle> getCmArticlesByField(final String fieldName, final Object value);
	
	/**
	 * <p>getCmArticles</p>
	 * 
	 * @param ownRoleIds
	 * @return
	 */
	public List<CmArticle> getCmArticles(String ownRoleIds);
	
	/**
	 * <p>getArticleMaps</p>
	 * 
	 * @param tag
	 * @param lang
	 * @param startDate
	 * @param endDate
	 * @param pageNo
	 * @param pageSize
	 * @param ownRoleIds
	 * @return
	 */
	@Transactional
	public List<Map<String,String>> getArticleMaps(final String tag, final String lang, final String startDate, final String endDate, 
			final int pageNo, final int pageSize, String ownRoleIds);
	
	/**
	 * <p>getArticleMaps</p>
	 * 
	 * @param tag
	 * @param lang
	 * @param startDate
	 * @param endDate
	 * @param pageNo
	 * @param pageSize
	 * @param ownRoleIds
	 * @param selGroupRoleIds
	 * @return
	 */
	public List<Map<String,String>> getArticleMapsWithPhoto(final String tag, final String lang, final String startDate, final String endDate, 
			final int pageNo, final int pageSize, String ownRoleIds, String selGroupRoleIds);
	
	/**
	 * <p>getCmArticles</p>
	 * 
	 * @param tag
	 * @param lang
	 * @param startDate
	 * @param endDate
	 * @param pageNo
	 * @param pageSize
	 * @param ownRoleIds
	 * @return
	 */
	public Page<CmArticle> getCmArticlePage(final String tag, final String lang, final String startDate, final String endDate, final int pageNo,
			final int pageSize, String ownRoleIds);
}