/**
 * $Id: CmEvaluateResultManager.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.service;

import org.ganjp.jpw.cm.model.CmEvaluateResult;
import java.util.List;

/**
 * <p>CmEvaluateResultManager</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface CmEvaluateResultManager {
		//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmEvaluateResult</p>
	 * 
	 * @param T
	 */
	public void save(CmEvaluateResult cmEvaluateResult);
	/**
	 * <p>update new cmEvaluateResult</p>
	 * 
	 * @param T
	 */
	public void update(CmEvaluateResult cmEvaluateResult);
	/**
	 * <p>save or update new cmEvaluateResult</p>
	 * 
	 * @param T
	 */
	public void saveOrUpdate(CmEvaluateResult cmEvaluateResult);
	
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmEvaluateResult</p>
	 * 
	 * @param cmEvaluateResult the cmEvaluateResult must be from session or transient object that has primary key attribute
	 */
	public void delete(CmEvaluateResult cmEvaluateResult);
	/**
	 * <p>delete a cmEvaluateResult by primary key</p>
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
	//------------return CmEvaluateResult
/**
	 * <p>get CmEvaluateResult object by primary key</p>
	 * 
	 * @param PK
	 * @return CmEvaluateResult
	 */
	public CmEvaluateResult getCmEvaluateResultById(final String id);
	
	/**
	 * <p>get all CmEvaluateResult objects</p>
	 *
	 * @return List<CmEvaluateResult>
	 */
	public List<CmEvaluateResult> getCmEvaluateResults();
	
	/**
	 * <p>get all CmEvaluateResult records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmEvaluateResult>
	 */
	public List<CmEvaluateResult> getCmEvaluateResultsWithOrder(String orderBy, boolean isAsc);
	
	/**
	 * <p>get CmEvaluateResult objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmEvaluateResult>
	 */
	public List<CmEvaluateResult> getCmEvaluateResultsByField(final String fieldName, final Object value);
}