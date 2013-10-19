/**
 * $Id: AmUserManagerImpl.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Coss Project
 *
 */
package org.ganjp.jpw.am.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.ganjp.jpw.am.model.AmUser;
import org.ganjp.jpw.am.service.AmUserManager;
import org.ganjp.jpw.core.dao.BaseDao;
import org.ganjp.jpw.core.model.PropertyFilter;
import org.ganjp.jpw.core.service.BaseManager;
import org.ganjp.jpw.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>AmUserManagerImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service 
public class AmUserManagerImpl extends BaseManager implements AmUserManager {
	
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new amUser</p>
	 * 
	 * @param AmUser
	 */
	@Transactional
	public void save(AmUser amUser) {
		dao.save(amUser);
	}

	/**
	 * <p>update new amUser</p>
	 * 
	 * @param AmUser
	 */
	@Transactional
	public void update(AmUser amUser) {
		dao.update(amUser);
	}
	/**
	 * <p>save or update new amUser</p>
	 * 
	 * @param AmUser
	 */
	@Transactional
	public void saveOrUpdate(AmUser amUser) {
		dao.saveOrUpdate(amUser);
	}
		
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the amUser</p>
	 * 
	 * @param amUser the amUser must be from session or transient object that has primary key attribute
	 */
	@Transactional
	public void delete(AmUser amUser) {
		dao.delete(amUser);
	}
	/**
	 * <p>delete a amUser by primary key</p>
	 * 
	 * @param PK
	 */
	@Transactional
	public void delete(final String pk) {
		dao.delete(AmUser.class, pk);
	}
	/**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
	@Transactional
	public void batchDelete(final String pks) {
		String hql = "delete from AmUser where userId in (" + StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
	}	
	//-------------------------------------------   find   ------------------------------------------
	//------------return AmUser
	/**
	 * <p>get AmUser object by primary key</p>
	 * 
	 * @param PK
	 * @return AmUser
	 */
	@Transactional
	public AmUser getAmUserById(final String id) {
		AmUser amUser = dao.findById(AmUser.class, id);
		return amUser;
	}
	
	/**
	 * <p>get all AmUser objects</p>
	 *
	 * @return List<AmUser>
	 */
	@Transactional
	public List<AmUser> getAmUsers() {
		return dao.findAllWithOrder(AmUser.class, "modifyTimestamp", false);
	}
	
	/**
	 * <p>get all AmUser objects and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<AmUser>
	 */
	@Transactional
	public List<AmUser> getAmUsersWithOrder(String orderBy, boolean isAsc)  {
		return dao.findAllWithOrder(AmUser.class, orderBy, isAsc);
	}
	
	/**
	 * <p>get AmUser objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<AmUser>
	 */
	@Transactional
	public List<AmUser> getAmUsersByField(final String fieldName, final Object value) {
		return dao.findByField(AmUser.class, fieldName, value, "modifyTimestamp", false);
	}

	/**
	 * <p>get AmUser by userCdOrEmail and password</p>
	 * 
	 * @param userCdOrEmail
	 * @param password
	 * @return
	 */
	@Transactional
	public AmUser getAmUser(final String userCdOrEmail, final String password) {
		PropertyFilter eqFilter1 = null;
		if (userCdOrEmail.indexOf("@") == -1) {
			eqFilter1 = new PropertyFilter("EQS_userCd", userCdOrEmail);
		} else {
			eqFilter1 = new PropertyFilter("EQS_email", userCdOrEmail); 
		}
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(eqFilter1);
		filters.add(new PropertyFilter("EQS_password", password));
		List<AmUser> amUsers = dao.findByPropertyFilterList(AmUser.class, filters);
		
		if (amUsers==null || amUsers.isEmpty()) {
			return null;
		}
		return amUsers.get(0);
	}

	/**
	 * <p>getUserIdAndNamePhotoUrl</p>
	 * 
	 * @return
	 */
	@Transactional
	public Map<String,String> getUserIdAndNamePhotoUrl() {
		Map<String,String> userIdAndNamePhotoUrl = new HashMap<String,String>();
		String hql = "select userId, userName, photoUrl from AmUser";
		List<Object[]> objArrs = dao.findByHql(hql);
		for (Object[] objArr : objArrs) {
			String photoUrl = StringUtil.toString(objArr[2]);
			if (StringUtil.isEmpty(photoUrl)) {
				photoUrl = "/resources/style/default/image/jp/photo/default-head.png";
			}
			userIdAndNamePhotoUrl.put(StringUtil.toString(objArr[0]), StringUtil.toString(objArr[1]) + ":" + photoUrl);
		}
		return userIdAndNamePhotoUrl;
	}

	/**
	 * <p>getUserCds</p>
	 * 
	 * @return
	 */
	@Transactional
	public List<String> getUserCds() {
		String hql = "select userCd from AmUser";
		return dao.findByHql(hql);
	}
	
	/**
	 * <p>getUserEmails</p>
	 * 
	 * @return
	 */
	@Transactional
	public List<String> getUserEmails() {
		String hql = "select email from AmUser";
		return dao.findByHql(hql);
	}
	
	@Resource(name="baseDaoHibernate")
	private BaseDao<AmUser, String> dao;
}