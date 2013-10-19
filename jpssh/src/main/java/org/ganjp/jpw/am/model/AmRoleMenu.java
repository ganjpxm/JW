/**
 * $Id: AmRoleMenu.java,v 1.0 2012/08/19 00:16:55 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.am.model;

import org.ganjp.jpw.core.Const;
import org.ganjp.jpw.core.model.BaseModel;
import org.ganjp.jpw.core.util.DateUtil;
import org.ganjp.jpw.core.dao.uuid.UUIDHexGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Timestamp;

/**
 * <p>AmRoleMenu</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Entity
@Table(name="am_role_menu")
public class AmRoleMenu extends BaseModel{
	@Id
	@Column(name="role_menu_id")
	private String roleMenuId = UUIDHexGenerator.getUuid();
	
	@Column(name="menu_id")
	private String menuId;
	@Column(name="role_id")
	private String roleId;
	@Column(name="create_date_time")
	private Timestamp createDateTime = DateUtil.getNowTimstamp();
	@Column(name="modify_timestamp")
	private Timestamp modifyTimestamp = DateUtil.getNowTimstamp();
	@Column(name="data_state")
	private String dataState = Const.DB_DATASTATE_NORMAL;
		
	//----------------------------------------------- default constructor --------------------------
    public AmRoleMenu() {
    	super();
    }
    
    //------------------------------------------------ Property accessors --------------------------
/**
	 * @return String
	 */
	public String getRoleMenuId() {
        return this.roleMenuId;
    }
    
    /**
	 * @param String roleMenuId
	 */
    public void setRoleMenuId(String roleMenuId) {
		this.roleMenuId = roleMenuId;
    }
    /**
	 * @return String
	 */
	public String getMenuId() {
        return this.menuId;
    }
    
    /**
	 * @param String menuId
	 */
    public void setMenuId(String menuId) {
		this.menuId = menuId;
    }
    /**
	 * @return String
	 */
	public String getRoleId() {
        return this.roleId;
    }
    
    /**
	 * @param String roleId
	 */
    public void setRoleId(String roleId) {
		this.roleId = roleId;
    }
    /**
	 * @return Timestamp
	 */
	public Timestamp getCreateDateTime() {
        return this.createDateTime;
    }
    
    /**
	 * @param Timestamp createDateTime
	 */
    public void setCreateDateTime(Timestamp createDateTime) {
		this.createDateTime = createDateTime;
    }
    /**
	 * @return String
	 */
	public String getDataState() {
        return this.dataState;
    }
    
    /**
	 * @param String dataState
	 */
    public void setDataState(String dataState) {
		this.dataState = dataState;
    }
    /**
	 * @return Timestamp
	 */
	public Timestamp getModifyTimestamp() {
        return this.modifyTimestamp;
    }
    
    /**
	 * @param Timestamp modifyTimestamp
	 */
    public void setModifyTimestamp(Timestamp modifyTimestamp) {
		this.modifyTimestamp = modifyTimestamp;
    }
     
}