/**
 * $Id: CmFeedbackManager.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.service;

import org.ganjp.jpw.cm.model.CmFeedback;
import java.util.List;

/**
 * <p>CmFeedbackManager</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface CmFeedbackManager {
		//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmFeedback</p>
	 * 
	 * @param T
	 */
	public void save(CmFeedback cmFeedback);
	/**
	 * <p>update new cmFeedback</p>
	 * 
	 * @param T
	 */
	public void update(CmFeedback cmFeedback);
	/**
	 * <p>save or update new cmFeedback</p>
	 * 
	 * @param T
	 */
	public void saveOrUpdate(CmFeedback cmFeedback);
	
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmFeedback</p>
	 * 
	 * @param cmFeedback the cmFeedback must be from session or transient object that has primary key attribute
	 */
	public void delete(CmFeedback cmFeedback);
	/**
	 * <p>delete a cmFeedback by primary key</p>
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
	//------------return CmFeedback
/**
	 * <p>get CmFeedback object by primary key</p>
	 * 
	 * @param PK
	 * @return CmFeedback
	 */
	public CmFeedback getCmFeedbackById(final String id);
	
	/**
	 * <p>get all CmFeedback objects</p>
	 *
	 * @return List<CmFeedback>
	 */
	public List<CmFeedback> getCmFeedbacks();
	
	/**
	 * <p>get all CmFeedback records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmFeedback>
	 */
	public List<CmFeedback> getCmFeedbacksWithOrder(String orderBy, boolean isAsc);
	
	/**
	 * <p>get CmFeedback objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmFeedback>
	 */
	public List<CmFeedback> getCmFeedbacksByField(final String fieldName, final Object value);
}