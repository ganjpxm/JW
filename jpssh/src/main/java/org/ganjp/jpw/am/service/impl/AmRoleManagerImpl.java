/**
 * $Id: AmRoleManagerImpl.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
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

import org.ganjp.jpw.am.model.AmRole;
import org.ganjp.jpw.am.service.AmRoleManager;
import org.ganjp.jpw.core.dao.BaseDao;
import org.ganjp.jpw.core.service.BaseManager;
import org.ganjp.jpw.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>AmRoleManagerImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service 
public class AmRoleManagerImpl extends BaseManager implements AmRoleManager {
	
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new amRole</p>
	 * 
	 * @param AmRole
	 */
	@Transactional
	public void save(AmRole amRole) {
		dao.save(amRole);
	}

	/**
	 * <p>update new amRole</p>
	 * 
	 * @param AmRole
	 */
	@Transactional
	public void update(AmRole amRole) {
		dao.update(amRole);
	}
	/**
	 * <p>save or update new amRole</p>
	 * 
	 * @param AmRole
	 */
	@Transactional
	public void saveOrUpdate(AmRole amRole) {
		dao.saveOrUpdate(amRole);
	}
		
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the amRole</p>
	 * 
	 * @param amRole the amRole must be from session or transient object that has primary key attribute
	 */
	@Transactional
	public void delete(AmRole amRole) {
		dao.delete(amRole);
	}
	/**
	 * <p>delete a amRole by primary key</p>
	 * 
	 * @param PK
	 */
	@Transactional
	public void delete(final String pk) {
		dao.delete(AmRole.class, pk);
	}
	/**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
	@Transactional
	public void batchDelete(final String pks) {
		String hql = "delete from AmRole where roleId in (" + StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
	}	
	//-------------------------------------------   find   ------------------------------------------
	//------------return AmRole
	/**
	 * <p>get AmRole object by primary key</p>
	 * 
	 * @param PK
	 * @return AmRole
	 */
	@Transactional
	public AmRole getAmRoleById(final String id) {
		AmRole amRole = dao.findById(AmRole.class, id);
		return amRole;
	}
	
	/**
	 * <p>get all AmRole objects</p>
	 *
	 * @return List<AmRole>
	 */
	@Transactional
	public List<AmRole> getAmRoles() {
		return dao.findAllWithOrder(AmRole.class, "modifyTimestamp", false);
	}
	
	/**
	 * <p>get all AmRole objects and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<AmRole>
	 */
	@Transactional
	public List<AmRole> getAmRolesWithOrder(String orderBy, boolean isAsc)  {
		return dao.findAllWithOrder(AmRole.class, orderBy, isAsc);
	}
	
	/**
	 * <p>get AmRole objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<AmRole>
	 */
	@Transactional
	public List<AmRole> getAmRolesByField(final String fieldName, final Object value) {
		return dao.findByField(AmRole.class, fieldName, value, "modifyTimestamp", false);
	}

	/**
	 * <p>get all roleId and roleName Map</p>
	 * 
	 * @return Map<String,String>
	 */
	@Transactional
	public Map<String,String> getRoleIdAndNames() {
		List<AmRole> amRoles = getAmRoles();
		Map<String,String> map = new HashMap<String,String>();
		for (AmRole amRole : amRoles) {
			map.put(amRole.getRoleId(), amRole.getRoleName());
		}
		return map;
	}
	
	/**
	 * <p>getRolesForJqmCheck</p>
	 * 
	 * @return
	 */
	@Transactional
	public String getRolesForJqmCheck() {
		StringBuffer sb = new StringBuffer();
		List<AmRole> amRoles = this.getAmRolesWithOrder("roleCd", true);
		for (AmRole amRole : amRoles) {
			sb.append(amRole.getRoleId()).append(":").append(amRole.getRoleName()).append(";");
		}
		return sb.toString();
	}
	
	/**
	 * getGroupRoleIdAndNames
	 * 
	 * @return
	 */
	@Transactional
	public Map<String,String> getGroupRoleIdAndNames() {
		Map<String,String> map = new HashMap();
		List<AmRole> amRoles = dao.findByField(AmRole.class, "roleCd", "group", "roleName", true);
		for (AmRole amRole : amRoles) {
			map.put(amRole.getRoleId(), amRole.getRoleName());
		}
		return map;
	}
	
	/**
	 * <p>getGroupRoleIdAndNames</p>
	 * 
	 * @param ownRoleIds
	 * @return
	 */
	@Transactional
	public List<String> getGroupRoleIdNames(String ownRoleIds) {
		List<String> list = new ArrayList<String>();
		if (StringUtil.isNotEmpty(ownRoleIds)) {
			List<AmRole> amRoles = dao.findByField(AmRole.class, "roleCd", "group", "roleName", true);
			for (AmRole amRole : amRoles) {
				if (ownRoleIds.indexOf(amRole.getRoleId())==-1) {
					continue;
				} else {
					list.add(amRole.getRoleId()+":"+amRole.getRoleName());
				}
			}
		}
		return list;
	}
	
	/**
	 * <p>getRoleIdAndNameMap</p>
	 * 
	 * @return
	 */
	@Transactional
	public Map<String,String> getRoleIdAndNameMap() {
		Map<String,String> map = new HashMap<String,String>();
		List<AmRole> amRoles = this.getAmRoles();
		for (AmRole amRole : amRoles) {
			map.put(amRole.getRoleId(), amRole.getRoleName());
		}
		return map;
	}
	
	@Resource(name="baseDaoHibernate")
	private BaseDao<AmRole, String> dao;
}