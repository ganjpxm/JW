/**
 * $Id: AmUserDetailManagerImpl.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Coss Project
 *
 */
package org.ganjp.jpw.am.service.impl;

import org.ganjp.jpw.am.model.AmUserDetail;
import org.ganjp.jpw.am.service.AmUserDetailManager;
import org.ganjp.jpw.core.dao.BaseDao;
import org.ganjp.jpw.core.service.BaseManager;
import org.ganjp.jpw.core.util.StringUtil;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import javax.annotation.Resource;


/**
 * <p>AmUserDetailManagerImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service 
public class AmUserDetailManagerImpl extends BaseManager implements AmUserDetailManager {
	
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new amUserDetail</p>
	 * 
	 * @param AmUserDetail
	 */
	@Transactional
	public void save(AmUserDetail amUserDetail) {
		dao.save(amUserDetail);
	}

	/**
	 * <p>update new amUserDetail</p>
	 * 
	 * @param AmUserDetail
	 */
	@Transactional
	public void update(AmUserDetail amUserDetail) {
		dao.update(amUserDetail);
	}
	/**
	 * <p>save or update new amUserDetail</p>
	 * 
	 * @param AmUserDetail
	 */
	@Transactional
	public void saveOrUpdate(AmUserDetail amUserDetail) {
		dao.saveOrUpdate(amUserDetail);
	}
		
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the amUserDetail</p>
	 * 
	 * @param amUserDetail the amUserDetail must be from session or transient object that has primary key attribute
	 */
	@Transactional
	public void delete(AmUserDetail amUserDetail) {
		dao.delete(amUserDetail);
	}
	/**
	 * <p>delete a amUserDetail by primary key</p>
	 * 
	 * @param PK
	 */
	@Transactional
	public void delete(final String pk) {
		dao.delete(AmUserDetail.class, pk);
	}
	/**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
	@Transactional
	public void batchDelete(final String pks) {
		String hql = "delete from AmUserDetail where userDetailId in (" + StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
	}	
	//-------------------------------------------   find   ------------------------------------------
	//------------return AmUserDetail
	/**
	 * <p>get AmUserDetail object by primary key</p>
	 * 
	 * @param PK
	 * @return AmUserDetail
	 */
	@Transactional
	public AmUserDetail getAmUserDetailById(final String id) {
		AmUserDetail amUserDetail = dao.findById(AmUserDetail.class, id);
		return amUserDetail;
	}
	
	/**
	 * <p>get all AmUserDetail objects</p>
	 *
	 * @return List<AmUserDetail>
	 */
	@Transactional
	public List<AmUserDetail> getAmUserDetails() {
		return dao.findAllWithOrder(AmUserDetail.class, "modifyTimestamp", false);
	}
	
	/**
	 * <p>get all AmUserDetail objects and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<AmUserDetail>
	 */
	@Transactional
	public List<AmUserDetail> getAmUserDetailsWithOrder(String orderBy, boolean isAsc)  {
		return dao.findAllWithOrder(AmUserDetail.class, orderBy, isAsc);
	}
	
	/**
	 * <p>get AmUserDetail objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<AmUserDetail>
	 */
	@Transactional
	public List<AmUserDetail> getAmUserDetailsByField(final String fieldName, final Object value) {
		return dao.findByField(AmUserDetail.class, fieldName, value, "modifyTimestamp", false);
	}

	/**
	 * <p>get AmUserDetail object by userId</p>
	 * 
	 * @param userId
	 * @return AmUserDetail
	 */
	@Transactional
	public AmUserDetail getAmUserDetailByUserId(final String userId) {
		List<AmUserDetail> amUserDetails  = getAmUserDetailsByField("userId", userId);
		if (amUserDetails==null || amUserDetails.isEmpty()) {
			return null;
		}
		return amUserDetails.get(0);
	}
	
	/**
	 * <p>delete AmUserDetail by userId</p>
	 * 
	 * @param userId
	 */
	@Transactional
	public void deleteAmUserDetailByUserId(final String userId) {
		String hql = "delete from AmUserDetail where userId = ?";
		dao.batchExecute(hql, userId);
	}
	@Resource(name="baseDaoHibernate")
	private BaseDao<AmUserDetail, String> dao;
}