/**
 * $Id: AmRoleMenuManagerImpl.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Coss Project
 *
 */
package org.ganjp.jpw.am.service.impl;

import org.ganjp.jpw.am.model.AmRoleMenu;
import org.ganjp.jpw.am.service.AmRoleMenuManager;
import org.ganjp.jpw.core.dao.BaseDao;
import org.ganjp.jpw.core.service.BaseManager;
import org.ganjp.jpw.core.util.StringUtil;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import javax.annotation.Resource;


/**
 * <p>AmRoleMenuManagerImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service 
public class AmRoleMenuManagerImpl extends BaseManager implements AmRoleMenuManager {
	
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new amRoleMenu</p>
	 * 
	 * @param AmRoleMenu
	 */
	@Transactional
	public void save(AmRoleMenu amRoleMenu) {
		dao.save(amRoleMenu);
	}

	/**
	 * <p>update new amRoleMenu</p>
	 * 
	 * @param AmRoleMenu
	 */
	@Transactional
	public void update(AmRoleMenu amRoleMenu) {
		dao.update(amRoleMenu);
	}
	/**
	 * <p>save or update new amRoleMenu</p>
	 * 
	 * @param AmRoleMenu
	 */
	@Transactional
	public void saveOrUpdate(AmRoleMenu amRoleMenu) {
		dao.saveOrUpdate(amRoleMenu);
	}
		
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the amRoleMenu</p>
	 * 
	 * @param amRoleMenu the amRoleMenu must be from session or transient object that has primary key attribute
	 */
	@Transactional
	public void delete(AmRoleMenu amRoleMenu) {
		dao.delete(amRoleMenu);
	}
	/**
	 * <p>delete a amRoleMenu by primary key</p>
	 * 
	 * @param PK
	 */
	@Transactional
	public void delete(final String pk) {
		dao.delete(AmRoleMenu.class, pk);
	}
	/**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
	@Transactional
	public void batchDelete(final String pks) {
		String hql = "delete from AmRoleMenu where roleMenuId in (" + StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
	}	
	//-------------------------------------------   find   ------------------------------------------
	//------------return AmRoleMenu
	/**
	 * <p>get AmRoleMenu object by primary key</p>
	 * 
	 * @param PK
	 * @return AmRoleMenu
	 */
	@Transactional
	public AmRoleMenu getAmRoleMenuById(final String id) {
		AmRoleMenu amRoleMenu = dao.findById(AmRoleMenu.class, id);
		return amRoleMenu;
	}
	
	/**
	 * <p>get all AmRoleMenu objects</p>
	 *
	 * @return List<AmRoleMenu>
	 */
	@Transactional
	public List<AmRoleMenu> getAmRoleMenus() {
		return dao.findAllWithOrder(AmRoleMenu.class, "modifyTimestamp", false);
	}
	
	/**
	 * <p>get all AmRoleMenu objects and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<AmRoleMenu>
	 */
	@Transactional
	public List<AmRoleMenu> getAmRoleMenusWithOrder(String orderBy, boolean isAsc)  {
		return dao.findAllWithOrder(AmRoleMenu.class, orderBy, isAsc);
	}
	
	/**
	 * <p>get AmRoleMenu objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<AmRoleMenu>
	 */
	@Transactional
	public List<AmRoleMenu> getAmRoleMenusByField(final String fieldName, final Object value) {
		return dao.findByField(AmRoleMenu.class, fieldName, value, "modifyTimestamp", false);
	}
	
	@Resource(name="baseDaoHibernate")
	private BaseDao<AmRoleMenu, String> dao;
}