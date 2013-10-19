/**
 * $Id: CmVocabularyCategoryManager.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.service;

import java.util.List;
import java.util.Map;

import org.ganjp.jpw.cm.model.CmVocabulary;
import org.ganjp.jpw.cm.model.CmVocabularyCategory;

/**
 * <p>CmVocabularyCategoryManager</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface CmVocabularyCategoryManager {
		//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmVocabularyCategory</p>
	 * 
	 * @param T
	 */
	public void save(CmVocabularyCategory cmVocabularyCategory);
	/**
	 * <p>update new cmVocabularyCategory</p>
	 * 
	 * @param T
	 */
	public void update(CmVocabularyCategory cmVocabularyCategory);
	/**
	 * <p>save or update new cmVocabularyCategory</p>
	 * 
	 * @param T
	 */
	public void saveOrUpdate(CmVocabularyCategory cmVocabularyCategory);
	
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmVocabularyCategory</p>
	 * 
	 * @param cmVocabularyCategory the cmVocabularyCategory must be from session or transient object that has primary key attribute
	 */
	public void delete(CmVocabularyCategory cmVocabularyCategory);
	/**
	 * <p>delete a cmVocabularyCategory by primary key</p>
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
	//------------return CmVocabularyCategory
/**
	 * <p>get CmVocabularyCategory object by primary key</p>
	 * 
	 * @param PK
	 * @return CmVocabularyCategory
	 */
	public CmVocabularyCategory getCmVocabularyCategoryById(final String id);
	
	/**
	 * <p>get all CmVocabularyCategory objects</p>
	 *
	 * @return List<CmVocabularyCategory>
	 */
	public List<CmVocabularyCategory> getCmVocabularyCategorys();
	
	/**
	 * <p>get all CmVocabularyCategory records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmVocabularyCategory>
	 */
	public List<CmVocabularyCategory> getCmVocabularyCategorysWithOrder(String orderBy, boolean isAsc);
	
	/**
	 * <p>get CmVocabularyCategory objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmVocabularyCategory>
	 */
	public List<CmVocabularyCategory> getCmVocabularyCategorysByField(final String fieldName, final Object value);
	
	/**
	 * <p>getVocabularyMaps</p>
	 * 
	 * @param categoryId
	 * @param ownRoleIds
	 * @return
	 */
	public List<Map<String,String>> getVocabularyMaps(String categoryId, String ownRoleIds);
	
	/**
	 * <p>saveCmVocabularyAndCategory</p>
	 * 
	 * @param cmVocabulary
	 * @param categoryIds
	 */
	public void saveCmVocabularyAndCategory(CmVocabulary cmVocabulary, String categoryIds);
	
	/**
	 * <p>getCategoryIds</p>
	 * 
	 * @param vocabularyId
	 * @return
	 */
	public String getCategoryIds(final String vocabularyId);

	/**
	 * <p>deleteByVocabularyId</p>
	 * 
	 * @param vocabularyId
	 */
	public void deleteByVocabularyId(String vocabularyId);
	
	/**
	 * <p>updateCmVocabularyAndCategory</p>
	 * 
	 * @param cmVocabulary
	 * @param categoryIds
	 */
	public void updateCmVocabularyAndCategory(CmVocabulary cmVocabulary, String categoryIds);
}