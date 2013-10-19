/**
 * $Id: AmUserRoleManagerImpl.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
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

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ganjp.jpw.am.model.AmUser;
import org.ganjp.jpw.am.model.AmUserRole;
import org.ganjp.jpw.am.service.AmRoleManager;
import org.ganjp.jpw.am.service.AmUserDetailManager;
import org.ganjp.jpw.am.service.AmUserManager;
import org.ganjp.jpw.am.service.AmUserRoleManager;
import org.ganjp.jpw.core.dao.BaseDao;
import org.ganjp.jpw.core.service.BaseManager;
import org.ganjp.jpw.core.util.CollectionUtil;
import org.ganjp.jpw.core.util.EncryptUtil;
import org.ganjp.jpw.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


/**
 * <p>AmUserRoleManagerImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service 
public class AmUserRoleManagerImpl extends BaseManager implements AmUserRoleManager {
	
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new amUserRole</p>
	 * 
	 * @param AmUserRole
	 */
	@Transactional
	public void save(AmUserRole amUserRole) {
		dao.save(amUserRole);
	}

	/**
	 * <p>update new amUserRole</p>
	 * 
	 * @param AmUserRole
	 */
	@Transactional
	public void update(AmUserRole amUserRole) {
		dao.update(amUserRole);
	}
	/**
	 * <p>save or update new amUserRole</p>
	 * 
	 * @param AmUserRole
	 */
	@Transactional
	public void saveOrUpdate(AmUserRole amUserRole) {
		dao.saveOrUpdate(amUserRole);
	}
		
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the amUserRole</p>
	 * 
	 * @param amUserRole the amUserRole must be from session or transient object that has primary key attribute
	 */
	@Transactional
	public void delete(AmUserRole amUserRole) {
		dao.delete(amUserRole);
	}
	/**
	 * <p>delete a amUserRole by primary key</p>
	 * 
	 * @param PK
	 */
	@Transactional
	public void delete(final String pk) {
		dao.delete(AmUserRole.class, pk);
	}
	/**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
	@Transactional
	public void batchDelete(final String pks) {
		String hql = "delete from AmUserRole where userRoleId in (" + StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
	}
	
	/**
	 * <p>delete AmUser and AmUserRole</p>
	 * 
	 * @param userId
	 */
	@Transactional
	public void deleteAmUserAndUserRole(String userId) {
		batchDeleteAmUserAndUserRole(userId);
	}
	
	/**
	 * <p>batchDeleteRelateRoles</p>
	 * 
	 * @param userIds
	 */
	@Transactional
	public void batchDeleteRelateRoles(final String roleIds) {
		List<String> userIds = this.getUserIds(roleIds);
		String hql = "delete from AmUserRole where userId in (" + StringUtil.getStrWithQuote(StringUtil.toStrWithSplit(userIds, ",")) + ") " +
				"and roleId in ("+ StringUtil.getStrWithQuote(roleIds) + ")" ;
		dao.batchExecute(hql);
		amRoleManager.batchDelete(roleIds);
	}
	
	/**
	 * <p>batchDeleteAmUserAndUserRole</p>
	 * 
	 * @param userIds
	 */
	@Transactional
	public void batchDeleteAmUserAndUserRole(final String userIds) {
		String hql = "delete from AmUserRole where userId in (" + StringUtil.getStrWithQuote(userIds) + ")";
		dao.batchExecute(hql);
		hql = "delete from AmUserDetail where userId in (" + StringUtil.getStrWithQuote(userIds) + ")";
		dao.batchExecute(hql);
		
		hql = "delete from CmArticleRemark a where a.operatorId in (" + StringUtil.getStrWithQuote(userIds) +
					") or a.articleId in (select b.articleId from CmArticle b where b.operatorId in (" + StringUtil.getStrWithQuote(userIds) + "))";
		dao.batchExecute(hql);
		hql = "delete from CmArticle where operatorId in (" + StringUtil.getStrWithQuote(userIds) + ")";
		dao.batchExecute(hql);
		
		hql = "delete from CmUserEvaluateTableResult where userId in (" + StringUtil.getStrWithQuote(userIds) + ")";
		dao.batchExecute(hql);
		
		hql = "delete from CmFeedback where operatorId in (" + StringUtil.getStrWithQuote(userIds) + ")";
		dao.batchExecute(hql);
		
		hql = "delete from AmUser where userId in (" + StringUtil.getStrWithQuote(userIds) + ")";
		dao.batchExecute(hql);
	}
	//-------------------------------------------   find   ------------------------------------------
	//------------return AmUserRole
	/**
	 * <p>get AmUserRole object by primary key</p>
	 * 
	 * @param PK
	 * @return AmUserRole
	 */
	@Transactional
	public AmUserRole getAmUserRoleById(final String id) {
		AmUserRole amUserRole = dao.findById(AmUserRole.class, id);
		return amUserRole;
	}
	
	/**
	 * <p>get all AmUserRole objects</p>
	 *
	 * @return List<AmUserRole>
	 */
	@Transactional
	public List<AmUserRole> getAmUserRoles() {
		return dao.findAllWithOrder(AmUserRole.class, "modifyTimestamp", false);
	}
	
	/**
	 * <p>get all AmUserRole objects and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<AmUserRole>
	 */
	@Transactional
	public List<AmUserRole> getAmUserRolesWithOrder(String orderBy, boolean isAsc)  {
		return dao.findAllWithOrder(AmUserRole.class, orderBy, isAsc);
	}
	
	/**
	 * <p>get AmUserRole objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<AmUserRole>
	 */
	@Transactional
	public List<AmUserRole> getAmUserRolesByField(final String fieldName, final Object value) {
		return dao.findByField(AmUserRole.class, fieldName, value, "modifyTimestamp", false);
	}

	/**
	 * <p>save amUser and userRole</p>
	 * 
	 * @param amUser
	 * @param roleIds
	 */
	@Transactional
	public void saveAmUserAndUserRole(AmUser amUser, String... roleIds) {
		List<AmUser> amUsers = amUserManager.getAmUsersByField("userCd", amUser.getUserCd()); 
		if (amUsers==null || amUsers.isEmpty()) {
			amUserManager.save(amUser);
			
			for (String roleId : roleIds) {
				AmUserRole amUserRole = new AmUserRole();
				amUserRole.setUserId(amUser.getUserId());
				amUserRole.setRoleId(roleId);
				dao.save(amUserRole);
			}
		}
	}

	/**
	 * <p>update AmUser and amUserRole</p>
	 * 
	 * @param userId
	 * @param roleIds
	 */
	@Transactional
	public void updateAmUserAndAmUserRole(AmUser amUser, String roleIds) {
		amUserManager.update(amUser);
		dao.batchExecute("delete from AmUserRole where userId = ?", amUser.getUserId());
		if (StringUtils.hasText(roleIds)) {
			List<String> roleIdList = CollectionUtil.getList(roleIds, ",");
			for (String roleId : roleIdList) {
				AmUserRole amUserRole = new AmUserRole();
				amUserRole.setUserId(amUser.getUserId());
				amUserRole.setRoleId(roleId);
				dao.save(amUserRole);
			}
		}
		
		Map<String,String> groupRoleIdAndNames = amRoleManager.getGroupRoleIdAndNames();
		String[] roleIdArr = roleIds.split(",");
		String groupRoleIds = "";
		for (String roleId : roleIdArr) {
			if (groupRoleIdAndNames.containsKey(roleId)) {
				if (StringUtil.isEmpty(groupRoleIds)) {
					groupRoleIds = roleId;
				} else {
					groupRoleIds += "," + roleId;
				}
			}
		}
		
		String hql = "update CmArticle set roleIds = ? where operatorId = ?";
		dao.batchExecute(hql, groupRoleIds, amUser.getUserId());
	}

	/**
	 * <p>get all role ids by userCd and password</p>
	 * 
	 * @param userCd
	 * @param password
	 * @return
	 */
	@Transactional
	public List<String> getRoleIds(final String userCdOrEmail, final String password) {
		List<String> roleIds = new ArrayList<String>();
		String hql = "select b.roleId from AmUser a, AmUserRole b where a.userId = b.userId and a.password = ? ";
		if (userCdOrEmail.indexOf("@") == -1) {
			hql += " and a.userCd = ?";
		} else {
			hql += " and a.email = ?";
		}
		
		List<Object[]> objectArrs = dao.findByHql(hql, password, userCdOrEmail);
		if (objectArrs==null || objectArrs.isEmpty()) {
			return null;
		}
		for (int i = 0; i < objectArrs.size(); i++) {
			Object value = objectArrs.get(i);
			roleIds.add(i, value.toString());
		}
		return roleIds;
	}
	
	/**
	 * <p>getRoleIdsNamesMap</p>
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional
	public Map<String,List<String>> getRoleIdsNamesMap(final String userId) {
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		List<String> roleIds = new ArrayList<String>();
		List<String> roleNames = new ArrayList<String>();
		String hql = "select c.roleId, c.roleName from AmUser a, AmUserRole b, AmRole c " +
				"where a.userId = b.userId and b.roleId = c.roleId and a.userId = ? order by c.roleCd desc";
		List<Object[]> objectArrs = dao.findByHql(hql, userId);
		if (objectArrs==null || objectArrs.isEmpty()) {
			return null;
		}
		for (int i = 0; i < objectArrs.size(); i++) {
			Object[] roleArr = objectArrs.get(i);
			roleIds.add(StringUtil.toString(roleArr[0]));
			roleNames.add(StringUtil.toString(roleArr[1]));
		}
		map.put("roleIds", roleIds);
		map.put("roleNames", roleNames);
		return map;
	}
	
	/**
	 * <p>getAmUsersWithRoleIdNames()</p>
	 * 
	 * @return
	 */
	@Transactional
	public List<AmUser> getAmUsersWithRoleIdNames() {
		List<AmUser> amUsers = amUserManager.getAmUsers();
		Map<String,String> roleIdAndNames = amRoleManager.getRoleIdAndNames();
		String hql = "select userId, roleId from AmUserRole";
		List<Object[]> objArrs = dao.findByHql(hql);
		Map<String,List<String>> userIdAndRoleNamesMap = new HashMap<String,List<String>>();
		Map<String,String> userIdAndRoleIdsMap = new HashMap<String,String>();
		for (Object[] objs : objArrs) {
			String userId = objs[0].toString();
			String roleId = objs[1].toString();
			String roleName = roleIdAndNames.get(roleId);
			
			if (userIdAndRoleNamesMap.containsKey(userId)) {
				userIdAndRoleNamesMap.get(userId).add(roleName);
			} else {
				List<String> list = new ArrayList<String>();
				list.add(roleName);
				userIdAndRoleNamesMap.put(userId, list);
			}
			if (userIdAndRoleIdsMap.containsKey(userId)) {
				userIdAndRoleIdsMap.put(userId, userIdAndRoleIdsMap.get(userId) + "," + roleId);
			} else {
				userIdAndRoleIdsMap.put(userId, roleId);
			}
		}
		for (int i = 0; i < amUsers.size(); i++) {
			AmUser amUser = amUsers.get(i);
			amUser.setRoleNames(userIdAndRoleNamesMap.get(amUser.getUserId()));
			amUser.setRoleIds(userIdAndRoleIdsMap.get(amUser.getUserId()));
		}
		return amUsers;
	}
	
	/**
	 * <p>getUserIds</p>
	 * 
	 * @param roleIds
	 * @return
	 */
	@Transactional
	public List<String> getUserIds(final String roleIds) {
		String hql = "select distinct(a.userId) from AmUserRole a where a.roleId in (" + StringUtil.getStrWithQuote(roleIds) + ") ";
		List<String> userIds = dao.findByHql(hql);
		return userIds;
	}	
	/**
	 * <p>getRoleIdAndUserMaps</p>
	 * 
	 * @param roleIds
	 * @return
	 */
	@Transactional
	public Map<String,List<String>> getRoleIdAndUserMaps(final String roleIds) {
		Map<String,List<String>> roleIdAndUsersMap = new HashMap<String,List<String>>();
		String hql = "select a.userId, a.userName, b.roleId, a.userAlias from AmUser a, AmUserRole b " +
				"where a.userId = b.userId and b.roleId in (" + StringUtil.getStrWithQuote(roleIds) + ") order by a.userName";
		List<Object[]> objectArrs = dao.findByHql(hql);
		for (int i = 0; i < objectArrs.size(); i++) {
			Object[] objArr = objectArrs.get(i);
			String roleId = StringUtil.toString(objArr[2]);
			String userIdName = StringUtil.toString(objArr[0]) + ":" + StringUtil.toString(objArr[1]) + ":" + StringUtil.toString(objArr[3]);
			if (roleIdAndUsersMap.containsKey(roleId)) {
				roleIdAndUsersMap.get(roleId).add(userIdName);
			} else {
				List<String> list = new ArrayList<String>();
				list.add(userIdName);
				roleIdAndUsersMap.put(roleId, list);
			}
		}
		return roleIdAndUsersMap;
	}

	/**
	 * <p>saveUsersByExcel</p>
	 * 
	 * @param applicantId
	 * @return
	 */
	@Transactional
	public String saveUsersByExcel(final String excelPath) {
		XSSFWorkbook xwb;
		try {
			xwb = new XSSFWorkbook(excelPath);
			XSSFSheet aSheet = xwb.getSheetAt(0); //xwb.getNumberOfSheets()
			int rows = aSheet.getPhysicalNumberOfRows();
			List<String> userCds = amUserManager.getUserCds();
			List<String> userEmails = amUserManager.getUserEmails();
			for (int j = 2; j <rows ; j++) {
				AmUser amUser = new AmUser();
				XSSFRow xRow = aSheet.getRow(j);
				String userCd = "";
				if (xRow.getCell(0)==null) {
					continue;
				}
				if (xRow.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
					userCd = xRow.getCell(0).getStringCellValue();
					if (StringUtil.isEmpty(userCd)) {
						continue;
					}
					userCd=userCd.trim();
				}
				String userName = "";
				if (xRow.getCell(1)!=null && xRow.getCell(1).getCellType() == HSSFCell.CELL_TYPE_STRING) {
					userName = xRow.getCell(1).getStringCellValue();
				}
				String email = "";
				if (xRow.getCell(2)!=null && xRow.getCell(2).getCellType() == HSSFCell.CELL_TYPE_STRING) {
					email = xRow.getCell(2).getStringCellValue();
				}
				String password = "";
				if (xRow.getCell(3)!=null && xRow.getCell(3).getCellType() == HSSFCell.CELL_TYPE_STRING) {
					password = xRow.getCell(3).getStringCellValue();
				} else if (xRow.getCell(3)!=null && xRow.getCell(3).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					password = StringUtil.toString(xRow.getCell(3).getNumericCellValue());
					if (password.length()>=3) {
						password = password.substring(0,password.length()-2);
					}
				}
				String roleIds = "";
				if (xRow.getCell(4)!=null && HSSFCell.CELL_TYPE_STRING == xRow.getCell(4).getCellType()) {
					roleIds = xRow.getCell(4).getStringCellValue();
				}
				String mobilePhone = "";
				if (xRow.getCell(5)!=null && xRow.getCell(5).getCellType() == HSSFCell.CELL_TYPE_STRING) {
					mobilePhone = xRow.getCell(5).getStringCellValue();
				}
				String userAlias = "";
				if (xRow.getCell(6)!=null && xRow.getCell(6).getCellType() == HSSFCell.CELL_TYPE_STRING) {
					userAlias = xRow.getCell(6).getStringCellValue();
				}
				
				amUser.setUserCd(userCd);
				amUser.setUserName(userName);
				amUser.setEmail(email);
				amUser.setPassword(EncryptUtil.encryptByBase64(password));
				amUser.setMobilePhone(mobilePhone);
				amUser.setUserAlias(userAlias);
				
				if (!userCds.contains(amUser.getUserCd()) && !userEmails.contains(amUser.getEmail())) {
					this.saveAmUserAndUserRole(amUser, roleIds.split(","));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "";
	}
	
	@Resource(name="baseDaoHibernate")
	private BaseDao<AmUserRole, String> dao;
	
	@Resource(name="amUserManagerImpl")
	private AmUserManager amUserManager;
	
	@Resource(name="amRoleManagerImpl")
	private AmRoleManager amRoleManager;
	
	@Resource(name="amUserDetailManagerImpl")
	private AmUserDetailManager amUserDetailManager;
	
}