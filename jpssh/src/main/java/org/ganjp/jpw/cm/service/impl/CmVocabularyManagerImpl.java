/**
 * $Id: CmVocabularyManagerImpl.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Coss Project
 *
 */
package org.ganjp.jpw.cm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.ganjp.jpw.cm.model.CmVocabulary;
import org.ganjp.jpw.cm.service.CmVocabularyManager;
import org.ganjp.jpw.core.dao.BaseDao;
import org.ganjp.jpw.core.service.BaseManager;
import org.ganjp.jpw.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>CmVocabularyManagerImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service 
public class CmVocabularyManagerImpl extends BaseManager implements CmVocabularyManager {
	
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmVocabulary</p>
	 * 
	 * @param CmVocabulary
	 */
	@Transactional
	public void save(CmVocabulary cmVocabulary) {
		dao.save(cmVocabulary);
	}

	/**
	 * <p>update new cmVocabulary</p>
	 * 
	 * @param CmVocabulary
	 */
	@Transactional
	public void update(CmVocabulary cmVocabulary) {
		dao.update(cmVocabulary);
	}
	/**
	 * <p>save or update new cmVocabulary</p>
	 * 
	 * @param CmVocabulary
	 */
	@Transactional
	public void saveOrUpdate(CmVocabulary cmVocabulary) {
		dao.saveOrUpdate(cmVocabulary);
	}
		
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmVocabulary</p>
	 * 
	 * @param cmVocabulary the cmVocabulary must be from session or transient object that has primary key attribute
	 */
	@Transactional
	public void delete(CmVocabulary cmVocabulary) {
		dao.delete(cmVocabulary);
	}
	/**
	 * <p>delete a cmVocabulary by primary key</p>
	 * 
	 * @param PK
	 */
	@Transactional
	public void delete(final String pk) {
		dao.delete(CmVocabulary.class, pk);
	}
	/**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
	@Transactional
	public void batchDelete(final String pks) {
		String hql = "delete from CmVocabularyCategory where vocabularyId in (" + StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
		hql = "delete from CmVocabulary where vocabularyId in (" + StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
	}	
	//-------------------------------------------   find   ------------------------------------------
	//------------return CmVocabulary
	/**
	 * <p>get CmVocabulary object by primary key</p>
	 * 
	 * @param PK
	 * @return CmVocabulary
	 */
	@Transactional
	public CmVocabulary getCmVocabularyById(final String id) {
		CmVocabulary cmVocabulary = dao.findById(CmVocabulary.class, id);
		return cmVocabulary;
	}
	
	/**
	 * <p>get all CmVocabulary objects</p>
	 *
	 * @return List<CmVocabulary>
	 */
	@Transactional
	public List<CmVocabulary> getCmVocabularys() {
		return dao.findAllWithOrder(CmVocabulary.class, "modifyTimestamp", false);
	}
	
	/**
	 * <p>get all CmVocabulary objects and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmVocabulary>
	 */
	@Transactional
	public List<CmVocabulary> getCmVocabularysWithOrder(String orderBy, boolean isAsc)  {
		return dao.findAllWithOrder(CmVocabulary.class, orderBy, isAsc);
	}
	
	/**
	 * <p>get CmVocabulary objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmVocabulary>
	 */
	@Transactional
	public List<CmVocabulary> getCmVocabularysByField(final String fieldName, final Object value) {
		return dao.findByField(CmVocabulary.class, fieldName, value, "modifyTimestamp", false);
	}

	/**
	 * <p>getCmVocabularys</p>
	 * 
	 * @param ownRoleIds
	 * @return
	 */
	@Transactional
	public List<CmVocabulary> getCmVocabularys(String ownRoleIds) {
		List<CmVocabulary> cmVocabularys = this.getCmVocabularys();
		for (int i = 0; i < cmVocabularys.size(); i++) {
			CmVocabulary cmVocabulary = cmVocabularys.get(i);
			if (!StringUtil.aCanAccessB(ownRoleIds, cmVocabulary.getRoleIds())) {
				cmVocabularys.remove(i);
				i--;
			}
		}
		return cmVocabularys;
	}
	
	@Resource(name="baseDaoHibernate")
	private BaseDao<CmVocabulary, String> dao;
}