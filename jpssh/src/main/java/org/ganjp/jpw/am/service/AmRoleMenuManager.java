/**
 * $Id: AmRoleMenuManager.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.am.service;

import org.ganjp.jpw.am.model.AmRoleMenu;
import java.util.List;

/**
 * <p>AmRoleMenuManager</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface AmRoleMenuManager {
		//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new amRoleMenu</p>
	 * 
	 * @param T
	 */
	public void save(AmRoleMenu amRoleMenu);
	/**
	 * <p>update new amRoleMenu</p>
	 * 
	 * @param T
	 */
	public void update(AmRoleMenu amRoleMenu);
	/**
	 * <p>save or update new amRoleMenu</p>
	 * 
	 * @param T
	 */
	public void saveOrUpdate(AmRoleMenu amRoleMenu);
	
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the amRoleMenu</p>
	 * 
	 * @param amRoleMenu the amRoleMenu must be from session or transient object that has primary key attribute
	 */
	public void delete(AmRoleMenu amRoleMenu);
	/**
	 * <p>delete a amRoleMenu by primary key</p>
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
	//------------return AmRoleMenu
/**
	 * <p>get AmRoleMenu object by primary key</p>
	 * 
	 * @param PK
	 * @return AmRoleMenu
	 */
	public AmRoleMenu getAmRoleMenuById(final String id);
	
	/**
	 * <p>get all AmRoleMenu objects</p>
	 *
	 * @return List<AmRoleMenu>
	 */
	public List<AmRoleMenu> getAmRoleMenus();
	
	/**
	 * <p>get all AmRoleMenu records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<AmRoleMenu>
	 */
	public List<AmRoleMenu> getAmRoleMenusWithOrder(String orderBy, boolean isAsc);
	
	/**
	 * <p>get AmRoleMenu objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<AmRoleMenu>
	 */
	public List<AmRoleMenu> getAmRoleMenusByField(final String fieldName, final Object value);
}