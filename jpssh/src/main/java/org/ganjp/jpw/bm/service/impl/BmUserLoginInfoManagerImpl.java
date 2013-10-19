/**
 * $Id: BmUserLoginInfoManagerImpl.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Coss Project
 *
 */
package org.ganjp.jpw.bm.service.impl;

import org.ganjp.jpw.bm.model.BmUserLoginInfo;
import org.ganjp.jpw.bm.service.BmUserLoginInfoManager;
import org.ganjp.jpw.core.dao.BaseDao;
import org.ganjp.jpw.core.service.BaseManager;
import org.ganjp.jpw.core.util.StringUtil;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import javax.annotation.Resource;


/**
 * <p>BmUserLoginInfoManagerImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service 
public class BmUserLoginInfoManagerImpl extends BaseManager implements BmUserLoginInfoManager {
	
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new bmUserLoginInfo</p>
	 * 
	 * @param BmUserLoginInfo
	 */
	@Transactional
	public void save(BmUserLoginInfo bmUserLoginInfo) {
		dao.save(bmUserLoginInfo);
	}

	/**
	 * <p>update new bmUserLoginInfo</p>
	 * 
	 * @param BmUserLoginInfo
	 */
	@Transactional
	public void update(BmUserLoginInfo bmUserLoginInfo) {
		dao.update(bmUserLoginInfo);
	}
	/**
	 * <p>save or update new bmUserLoginInfo</p>
	 * 
	 * @param BmUserLoginInfo
	 */
	@Transactional
	public void saveOrUpdate(BmUserLoginInfo bmUserLoginInfo) {
		dao.saveOrUpdate(bmUserLoginInfo);
	}
		
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the bmUserLoginInfo</p>
	 * 
	 * @param bmUserLoginInfo the bmUserLoginInfo must be from session or transient object that has primary key attribute
	 */
	@Transactional
	public void delete(BmUserLoginInfo bmUserLoginInfo) {
		dao.delete(bmUserLoginInfo);
	}
	/**
	 * <p>delete a bmUserLoginInfo by primary key</p>
	 * 
	 * @param PK
	 */
	@Transactional
	public void delete(final String pk) {
		dao.delete(BmUserLoginInfo.class, pk);
	}
	/**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
	@Transactional
	public void batchDelete(final String pks) {
		String hql = "delete from BmUserLoginInfo where userLoginInfoId in (" + StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
	}	
	//-------------------------------------------   find   ------------------------------------------
	//------------return BmUserLoginInfo
	/**
	 * <p>get BmUserLoginInfo object by primary key</p>
	 * 
	 * @param PK
	 * @return BmUserLoginInfo
	 */
	@Transactional
	public BmUserLoginInfo getBmUserLoginInfoById(final String id) {
		BmUserLoginInfo bmUserLoginInfo = dao.findById(BmUserLoginInfo.class, id);
		return bmUserLoginInfo;
	}
	
	/**
	 * <p>get all BmUserLoginInfo objects</p>
	 *
	 * @return List<BmUserLoginInfo>
	 */
	@Transactional
	public List<BmUserLoginInfo> getBmUserLoginInfos() {
		return dao.findAllWithOrder(BmUserLoginInfo.class, "modifyTimestamp", false);
	}
	
	/**
	 * <p>get all BmUserLoginInfo objects and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<BmUserLoginInfo>
	 */
	@Transactional
	public List<BmUserLoginInfo> getBmUserLoginInfosWithOrder(String orderBy, boolean isAsc)  {
		return dao.findAllWithOrder(BmUserLoginInfo.class, orderBy, isAsc);
	}
	
	/**
	 * <p>get BmUserLoginInfo objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<BmUserLoginInfo>
	 */
	@Transactional
	public List<BmUserLoginInfo> getBmUserLoginInfosByField(final String fieldName, final Object value) {
		return dao.findByField(BmUserLoginInfo.class, fieldName, value, "modifyTimestamp", false);
	}
	
	@Resource(name="baseDaoHibernate")
	private BaseDao<BmUserLoginInfo, String> dao;
}