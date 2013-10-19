/**
 * $Id: AmUserDetailManager.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.am.service;

import java.util.List;

import org.ganjp.jpw.am.model.AmUserDetail;

/**
 * <p>AmUserDetailManager</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface AmUserDetailManager {
		//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new amUserDetail</p>
	 * 
	 * @param T
	 */
	public void save(AmUserDetail amUserDetail);
	/**
	 * <p>update new amUserDetail</p>
	 * 
	 * @param T
	 */
	public void update(AmUserDetail amUserDetail);
	/**
	 * <p>save or update new amUserDetail</p>
	 * 
	 * @param T
	 */
	public void saveOrUpdate(AmUserDetail amUserDetail);
	
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the amUserDetail</p>
	 * 
	 * @param amUserDetail the amUserDetail must be from session or transient object that has primary key attribute
	 */
	public void delete(AmUserDetail amUserDetail);
	/**
	 * <p>delete a amUserDetail by primary key</p>
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
	//------------return AmUserDetail
/**
	 * <p>get AmUserDetail object by primary key</p>
	 * 
	 * @param PK
	 * @return AmUserDetail
	 */
	public AmUserDetail getAmUserDetailById(final String id);
	
	/**
	 * <p>get all AmUserDetail objects</p>
	 *
	 * @return List<AmUserDetail>
	 */
	public List<AmUserDetail> getAmUserDetails();
	
	/**
	 * <p>get all AmUserDetail records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<AmUserDetail>
	 */
	public List<AmUserDetail> getAmUserDetailsWithOrder(String orderBy, boolean isAsc);
	
	/**
	 * <p>get AmUserDetail objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<AmUserDetail>
	 */
	public List<AmUserDetail> getAmUserDetailsByField(final String fieldName, final Object value);
	
	/**
	 * <p>get AmUserDetail object by userId</p>
	 * 
	 * @param userId
	 * @return AmUserDetail
	 */
	public AmUserDetail getAmUserDetailByUserId(final String userId);
	
	/**
	 * <p>delete AmUserDetail by userId</p>
	 * 
	 * @param userId
	 */
	public void deleteAmUserDetailByUserId(final String userId);
}