/**
 * $Id: BmUserLoginInfoManager.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.bm.service;

import org.ganjp.jpw.bm.model.BmUserLoginInfo;
import java.util.List;

/**
 * <p>BmUserLoginInfoManager</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface BmUserLoginInfoManager {
		//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new bmUserLoginInfo</p>
	 * 
	 * @param T
	 */
	public void save(BmUserLoginInfo bmUserLoginInfo);
	/**
	 * <p>update new bmUserLoginInfo</p>
	 * 
	 * @param T
	 */
	public void update(BmUserLoginInfo bmUserLoginInfo);
	/**
	 * <p>save or update new bmUserLoginInfo</p>
	 * 
	 * @param T
	 */
	public void saveOrUpdate(BmUserLoginInfo bmUserLoginInfo);
	
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the bmUserLoginInfo</p>
	 * 
	 * @param bmUserLoginInfo the bmUserLoginInfo must be from session or transient object that has primary key attribute
	 */
	public void delete(BmUserLoginInfo bmUserLoginInfo);
	/**
	 * <p>delete a bmUserLoginInfo by primary key</p>
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
	//------------return BmUserLoginInfo
/**
	 * <p>get BmUserLoginInfo object by primary key</p>
	 * 
	 * @param PK
	 * @return BmUserLoginInfo
	 */
	public BmUserLoginInfo getBmUserLoginInfoById(final String id);
	
	/**
	 * <p>get all BmUserLoginInfo objects</p>
	 *
	 * @return List<BmUserLoginInfo>
	 */
	public List<BmUserLoginInfo> getBmUserLoginInfos();
	
	/**
	 * <p>get all BmUserLoginInfo records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<BmUserLoginInfo>
	 */
	public List<BmUserLoginInfo> getBmUserLoginInfosWithOrder(String orderBy, boolean isAsc);
	
	/**
	 * <p>get BmUserLoginInfo objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<BmUserLoginInfo>
	 */
	public List<BmUserLoginInfo> getBmUserLoginInfosByField(final String fieldName, final Object value);
}