/**
 * $Id: CmVocabularyManager.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.service;

import java.util.List;

import org.ganjp.jpw.cm.model.CmVocabulary;

/**
 * <p>CmVocabularyManager</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface CmVocabularyManager {
		//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmVocabulary</p>
	 * 
	 * @param T
	 */
	public void save(CmVocabulary cmVocabulary);
	/**
	 * <p>update new cmVocabulary</p>
	 * 
	 * @param T
	 */
	public void update(CmVocabulary cmVocabulary);
	/**
	 * <p>save or update new cmVocabulary</p>
	 * 
	 * @param T
	 */
	public void saveOrUpdate(CmVocabulary cmVocabulary);
	
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmVocabulary</p>
	 * 
	 * @param cmVocabulary the cmVocabulary must be from session or transient object that has primary key attribute
	 */
	public void delete(CmVocabulary cmVocabulary);
	/**
	 * <p>delete a cmVocabulary by primary key</p>
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
	//------------return CmVocabulary
/**
	 * <p>get CmVocabulary object by primary key</p>
	 * 
	 * @param PK
	 * @return CmVocabulary
	 */
	public CmVocabulary getCmVocabularyById(final String id);
	
	/**
	 * <p>get all CmVocabulary objects</p>
	 *
	 * @return List<CmVocabulary>
	 */
	public List<CmVocabulary> getCmVocabularys();
	
	/**
	 * <p>get all CmVocabulary records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmVocabulary>
	 */
	public List<CmVocabulary> getCmVocabularysWithOrder(String orderBy, boolean isAsc);
	
	/**
	 * <p>get CmVocabulary objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmVocabulary>
	 */
	public List<CmVocabulary> getCmVocabularysByField(final String fieldName, final Object value);

	/**
	 * <p>getCmVocabularys</p>
	 * 
	 * @param ownRoleIds
	 * @return
	 */
	public List<CmVocabulary> getCmVocabularys(String ownRoleIds);
}