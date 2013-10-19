/**
 * $Id: CmEvaluateResultManagerImpl.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Coss Project
 *
 */
package org.ganjp.jpw.cm.service.impl;

import org.ganjp.jpw.cm.model.CmEvaluateResult;
import org.ganjp.jpw.cm.service.CmEvaluateResultManager;
import org.ganjp.jpw.core.dao.BaseDao;
import org.ganjp.jpw.core.service.BaseManager;
import org.ganjp.jpw.core.util.StringUtil;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import javax.annotation.Resource;


/**
 * <p>CmEvaluateResultManagerImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service 
public class CmEvaluateResultManagerImpl extends BaseManager implements CmEvaluateResultManager {
	
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmEvaluateResult</p>
	 * 
	 * @param CmEvaluateResult
	 */
	@Transactional
	public void save(CmEvaluateResult cmEvaluateResult) {
		dao.save(cmEvaluateResult);
	}

	/**
	 * <p>update new cmEvaluateResult</p>
	 * 
	 * @param CmEvaluateResult
	 */
	@Transactional
	public void update(CmEvaluateResult cmEvaluateResult) {
		dao.update(cmEvaluateResult);
	}
	/**
	 * <p>save or update new cmEvaluateResult</p>
	 * 
	 * @param CmEvaluateResult
	 */
	@Transactional
	public void saveOrUpdate(CmEvaluateResult cmEvaluateResult) {
		dao.saveOrUpdate(cmEvaluateResult);
	}
		
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmEvaluateResult</p>
	 * 
	 * @param cmEvaluateResult the cmEvaluateResult must be from session or transient object that has primary key attribute
	 */
	@Transactional
	public void delete(CmEvaluateResult cmEvaluateResult) {
		dao.delete(cmEvaluateResult);
	}
	/**
	 * <p>delete a cmEvaluateResult by primary key</p>
	 * 
	 * @param PK
	 */
	@Transactional
	public void delete(final String pk) {
		dao.delete(CmEvaluateResult.class, pk);
	}
	/**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
	@Transactional
	public void batchDelete(final String pks) {
		String hql = "delete from CmEvaluateResult where evaluateResultId in (" + StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
	}	
	//-------------------------------------------   find   ------------------------------------------
	//------------return CmEvaluateResult
	/**
	 * <p>get CmEvaluateResult object by primary key</p>
	 * 
	 * @param PK
	 * @return CmEvaluateResult
	 */
	@Transactional
	public CmEvaluateResult getCmEvaluateResultById(final String id) {
		CmEvaluateResult cmEvaluateResult = dao.findById(CmEvaluateResult.class, id);
		return cmEvaluateResult;
	}
	
	/**
	 * <p>get all CmEvaluateResult objects</p>
	 *
	 * @return List<CmEvaluateResult>
	 */
	@Transactional
	public List<CmEvaluateResult> getCmEvaluateResults() {
		return dao.findAllWithOrder(CmEvaluateResult.class, "modifyTimestamp", false);
	}
	
	/**
	 * <p>get all CmEvaluateResult objects and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmEvaluateResult>
	 */
	@Transactional
	public List<CmEvaluateResult> getCmEvaluateResultsWithOrder(String orderBy, boolean isAsc)  {
		return dao.findAllWithOrder(CmEvaluateResult.class, orderBy, isAsc);
	}
	
	/**
	 * <p>get CmEvaluateResult objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmEvaluateResult>
	 */
	@Transactional
	public List<CmEvaluateResult> getCmEvaluateResultsByField(final String fieldName, final Object value) {
		return dao.findByField(CmEvaluateResult.class, fieldName, value, "modifyTimestamp", false);
	}
	
	@Resource(name="baseDaoHibernate")
	private BaseDao<CmEvaluateResult, String> dao;
}