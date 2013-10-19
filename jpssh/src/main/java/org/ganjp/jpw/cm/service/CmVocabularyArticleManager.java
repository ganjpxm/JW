/**
 * $Id: CmVocabularyArticleManager.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.service;

import java.util.List;
import java.util.Map;

import org.ganjp.jpw.cm.model.CmVocabularyArticle;

/**
 * <p>CmVocabularyArticleManager</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface CmVocabularyArticleManager {
		//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmVocabularyArticle</p>
	 * 
	 * @param T
	 */
	public void save(CmVocabularyArticle cmVocabularyArticle);
	/**
	 * <p>update new cmVocabularyArticle</p>
	 * 
	 * @param T
	 */
	public void update(CmVocabularyArticle cmVocabularyArticle);
	/**
	 * <p>save or update new cmVocabularyArticle</p>
	 * 
	 * @param T
	 */
	public void saveOrUpdate(CmVocabularyArticle cmVocabularyArticle);
	
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmVocabularyArticle</p>
	 * 
	 * @param cmVocabularyArticle the cmVocabularyArticle must be from session or transient object that has primary key attribute
	 */
	public void delete(CmVocabularyArticle cmVocabularyArticle);
	/**
	 * <p>delete a cmVocabularyArticle by primary key</p>
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
	//------------return CmVocabularyArticle
/**
	 * <p>get CmVocabularyArticle object by primary key</p>
	 * 
	 * @param PK
	 * @return CmVocabularyArticle
	 */
	public CmVocabularyArticle getCmVocabularyArticleById(final String id);
	
	/**
	 * <p>get all CmVocabularyArticle objects</p>
	 *
	 * @return List<CmVocabularyArticle>
	 */
	public List<CmVocabularyArticle> getCmVocabularyArticles();
	
	/**
	 * <p>get all CmVocabularyArticle records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmVocabularyArticle>
	 */
	public List<CmVocabularyArticle> getCmVocabularyArticlesWithOrder(String orderBy, boolean isAsc);
	
	/**
	 * <p>get CmVocabularyArticle objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmVocabularyArticle>
	 */
	public List<CmVocabularyArticle> getCmVocabularyArticlesByField(final String fieldName, final Object value);
	
	/**
	 * <p>saveCmVocabularyArticles</p>
	 * 
	 * @param articleId
	 * @param vocabularyIds
	 * @return
	 */
	public String saveCmVocabularyArticles(final String articleId, final String vocabularyIds);
	
	/**
	 * <p>getVocabularyMaps</p>
	 * 
	 * @param articleId
	 * @return
	 */
	public List<Map<String,String>> getVocabularyMaps(final String articleId);
}