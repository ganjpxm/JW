/**
 * $Id: AmUserManager.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.am.service;

import java.util.List;
import java.util.Map;

import org.ganjp.jpw.am.model.AmUser;

/**
 * <p>AmUserManager</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface AmUserManager {
		//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new amUser</p>
	 * 
	 * @param T
	 */
	public void save(AmUser amUser);
	/**
	 * <p>update new amUser</p>
	 * 
	 * @param T
	 */
	public void update(AmUser amUser);
	/**
	 * <p>save or update new amUser</p>
	 * 
	 * @param T
	 */
	public void saveOrUpdate(AmUser amUser);
	
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the amUser</p>
	 * 
	 * @param amUser the amUser must be from session or transient object that has primary key attribute
	 */
	public void delete(AmUser amUser);
	/**
	 * <p>delete a amUser by primary key</p>
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
	//------------return AmUser
/**
	 * <p>get AmUser object by primary key</p>
	 * 
	 * @param PK
	 * @return AmUser
	 */
	public AmUser getAmUserById(final String id);
	
	/**
	 * <p>get all AmUser objects</p>
	 *
	 * @return List<AmUser>
	 */
	public List<AmUser> getAmUsers();
	
	/**
	 * <p>get all AmUser records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<AmUser>
	 */
	public List<AmUser> getAmUsersWithOrder(String orderBy, boolean isAsc);
	
	/**
	 * <p>get AmUser objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<AmUser>
	 */
	public List<AmUser> getAmUsersByField(final String fieldName, final Object value);
	
	/**
	 * <p>get AmUser by userCdOrEmail and password</p>
	 * 
	 * @param userCdOrEmail
	 * @param password
	 * @return
	 */
	public AmUser getAmUser(final String userCdOrEmail, final String password);
	
	/**
	 * <p>getUserIdAndNamePhotoUrl</p>
	 * 
	 * @return
	 */
	public Map<String,String> getUserIdAndNamePhotoUrl();
	
	/**
	 * <p>getUserCds</p>
	 * 
	 * @return
	 */
	public List<String> getUserCds();
	
	/**
	 * <p>getUserEmails</p>
	 * 
	 * @return
	 */
	public List<String> getUserEmails();
}