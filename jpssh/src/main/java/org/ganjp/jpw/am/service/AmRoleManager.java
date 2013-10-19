/**
 * $Id: AmRoleManager.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.am.service;

import java.util.List;
import java.util.Map;

import org.ganjp.jpw.am.model.AmRole;

/**
 * <p>AmRoleManager</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface AmRoleManager {
		//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new amRole</p>
	 * 
	 * @param T
	 */
	public void save(AmRole amRole);
	/**
	 * <p>update new amRole</p>
	 * 
	 * @param T
	 */
	public void update(AmRole amRole);
	/**
	 * <p>save or update new amRole</p>
	 * 
	 * @param T
	 */
	public void saveOrUpdate(AmRole amRole);
	
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the amRole</p>
	 * 
	 * @param amRole the amRole must be from session or transient object that has primary key attribute
	 */
	public void delete(AmRole amRole);
	/**
	 * <p>delete a amRole by primary key</p>
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
	//------------return AmRole
/**
	 * <p>get AmRole object by primary key</p>
	 * 
	 * @param PK
	 * @return AmRole
	 */
	public AmRole getAmRoleById(final String id);
	
	/**
	 * <p>get all AmRole objects</p>
	 *
	 * @return List<AmRole>
	 */
	public List<AmRole> getAmRoles();
	
	/**
	 * <p>get all AmRole records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<AmRole>
	 */
	public List<AmRole> getAmRolesWithOrder(String orderBy, boolean isAsc);
	
	/**
	 * <p>get AmRole objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<AmRole>
	 */
	public List<AmRole> getAmRolesByField(final String fieldName, final Object value);
	
	/**
	 * <p>get all roleId and roleName Map</p>
	 * 
	 * @return Map<String,String>
	 */
	public Map<String,String> getRoleIdAndNames();
	
	/**
	 * <p>getRolesForJqmCheck</p>
	 * 
	 * @return
	 */
	public String getRolesForJqmCheck();
	
	/**
	 * getGroupRoleIdAndNames
	 * 
	 * @return
	 */
	public Map<String,String> getGroupRoleIdAndNames();
	
	/**
	 * <p>getGroupRoleIdAndNames</p>
	 * 
	 * @param ownRoleIds
	 * @return
	 */
	public List<String> getGroupRoleIdNames(String ownRoleIds);
	
	/**
	 * <p>getRoleIdAndNameMap</p>
	 * 
	 * @return
	 */
	public Map<String,String> getRoleIdAndNameMap();
}