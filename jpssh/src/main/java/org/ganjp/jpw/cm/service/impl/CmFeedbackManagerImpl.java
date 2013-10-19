/**
 * $Id: CmFeedbackManagerImpl.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Coss Project
 *
 */
package org.ganjp.jpw.cm.service.impl;

import org.ganjp.jpw.cm.model.CmFeedback;
import org.ganjp.jpw.cm.service.CmFeedbackManager;
import org.ganjp.jpw.core.dao.BaseDao;
import org.ganjp.jpw.core.service.BaseManager;
import org.ganjp.jpw.core.util.StringUtil;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import javax.annotation.Resource;


/**
 * <p>CmFeedbackManagerImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service 
public class CmFeedbackManagerImpl extends BaseManager implements CmFeedbackManager {
	
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmFeedback</p>
	 * 
	 * @param CmFeedback
	 */
	@Transactional
	public void save(CmFeedback cmFeedback) {
		dao.save(cmFeedback);
	}

	/**
	 * <p>update new cmFeedback</p>
	 * 
	 * @param CmFeedback
	 */
	@Transactional
	public void update(CmFeedback cmFeedback) {
		dao.update(cmFeedback);
	}
	/**
	 * <p>save or update new cmFeedback</p>
	 * 
	 * @param CmFeedback
	 */
	@Transactional
	public void saveOrUpdate(CmFeedback cmFeedback) {
		dao.saveOrUpdate(cmFeedback);
	}
		
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmFeedback</p>
	 * 
	 * @param cmFeedback the cmFeedback must be from session or transient object that has primary key attribute
	 */
	@Transactional
	public void delete(CmFeedback cmFeedback) {
		dao.delete(cmFeedback);
	}
	/**
	 * <p>delete a cmFeedback by primary key</p>
	 * 
	 * @param PK
	 */
	@Transactional
	public void delete(final String pk) {
		dao.delete(CmFeedback.class, pk);
	}
	/**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
	@Transactional
	public void batchDelete(final String pks) {
		String hql = "delete from CmFeedback where feedbackId in (" + StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
	}	
	//-------------------------------------------   find   ------------------------------------------
	//------------return CmFeedback
	/**
	 * <p>get CmFeedback object by primary key</p>
	 * 
	 * @param PK
	 * @return CmFeedback
	 */
	@Transactional
	public CmFeedback getCmFeedbackById(final String id) {
		CmFeedback cmFeedback = dao.findById(CmFeedback.class, id);
		return cmFeedback;
	}
	
	/**
	 * <p>get all CmFeedback objects</p>
	 *
	 * @return List<CmFeedback>
	 */
	@Transactional
	public List<CmFeedback> getCmFeedbacks() {
		return dao.findAllWithOrder(CmFeedback.class, "modifyTimestamp", false);
	}
	
	/**
	 * <p>get all CmFeedback objects and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmFeedback>
	 */
	@Transactional
	public List<CmFeedback> getCmFeedbacksWithOrder(String orderBy, boolean isAsc)  {
		return dao.findAllWithOrder(CmFeedback.class, orderBy, isAsc);
	}
	
	/**
	 * <p>get CmFeedback objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmFeedback>
	 */
	@Transactional
	public List<CmFeedback> getCmFeedbacksByField(final String fieldName, final Object value) {
		return dao.findByField(CmFeedback.class, fieldName, value, "modifyTimestamp", false);
	}
	
	@Resource(name="baseDaoHibernate")
	private BaseDao<CmFeedback, String> dao;
}