/**
 * $Id: AmUserRoleManager.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.am.service;

import java.util.List;
import java.util.Map;

import org.ganjp.jpw.am.model.AmUser;
import org.ganjp.jpw.am.model.AmUserRole;

/**
 * <p>AmUserRoleManager</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface AmUserRoleManager {
		//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new amUserRole</p>
	 * 
	 * @param T
	 */
	public void save(AmUserRole amUserRole);
	/**
	 * <p>update new amUserRole</p>
	 * 
	 * @param T
	 */
	public void update(AmUserRole amUserRole);
	/**
	 * <p>save or update new amUserRole</p>
	 * 
	 * @param T
	 */
	public void saveOrUpdate(AmUserRole amUserRole);
	
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the amUserRole</p>
	 * 
	 * @param amUserRole the amUserRole must be from session or transient object that has primary key attribute
	 */
	public void delete(AmUserRole amUserRole);
	/**
	 * <p>delete a amUserRole by primary key</p>
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
	//------------return AmUserRole
/**
	 * <p>get AmUserRole object by primary key</p>
	 * 
	 * @param PK
	 * @return AmUserRole
	 */
	public AmUserRole getAmUserRoleById(final String id);
	
	/**
	 * <p>get all AmUserRole objects</p>
	 *
	 * @return List<AmUserRole>
	 */
	public List<AmUserRole> getAmUserRoles();
	
	/**
	 * <p>get all AmUserRole records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<AmUserRole>
	 */
	public List<AmUserRole> getAmUserRolesWithOrder(String orderBy, boolean isAsc);
	
	/**
	 * <p>get AmUserRole objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<AmUserRole>
	 */
	public List<AmUserRole> getAmUserRolesByField(final String fieldName, final Object value);
	
	/**
	 * <p>save amUser and userRole</p>
	 * 
	 * @param amUser
	 * @param roleIds
	 */
	public void saveAmUserAndUserRole(AmUser amUser, String... roleIds);
	
	/**
	 * <p>delete AmUser and AmUserRole</p>
	 * 
	 * @param userId
	 */
	public void deleteAmUserAndUserRole(String userId);
	
	/**
	 * <p>update AmUser and amUserRole</p>
	 * 
	 * @param userId
	 * @param roleIds
	 */
	public void updateAmUserAndAmUserRole(AmUser amUser, String roleIds);
	
	/**
	 * <p>getRoleIdsNamesMap</p>
	 * 
	 * @param userId
	 * @return
	 */
	public Map<String,List<String>> getRoleIdsNamesMap(final String userId);
	
	/**
	 * <p>getAmUsersWithRoleIdNames()</p>
	 * 
	 * @return
	 */
	public List<AmUser> getAmUsersWithRoleIdNames();
	
	/**
	 * <p>batchDeleteAmUserAndUserRole</p>
	 * 
	 * @param userIds
	 */
	public void batchDeleteAmUserAndUserRole(final String userIds);
	
	/**
	 * <p>get all role ids by userCd and password</p>
	 * 
	 * @param userCd
	 * @param password
	 * @return
	 */
	public List<String> getRoleIds(final String userCdOrEmail, final String password);
	
	/**
	 * <p>getRoleIdAndUserMaps</p>
	 * 
	 * @param roleIds
	 * @return
	 */
	public Map<String,List<String>> getRoleIdAndUserMaps(final String roleIds);
	
	/**
	 * <p>getUserIds</p>
	 * 
	 * @param roleIds
	 * @return
	 */
	public List<String> getUserIds(final String roleIds);
	
	/**
	 * <p>saveUsersByExcel</p>
	 * 
	 * @param applicantId
	 * @return
	 */
	public String saveUsersByExcel(final String excelPath);
	
	/**
	 * <p>batchDeleteRelateRoles</p>
	 * 
	 * @param userIds
	 */
	public void batchDeleteRelateRoles(final String roleIds);
}