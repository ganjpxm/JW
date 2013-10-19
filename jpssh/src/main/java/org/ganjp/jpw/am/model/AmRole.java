/**
 * $Id: AmRole.java,v 1.0 2012/08/19 00:16:55 GanJianping Exp $
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
 * <p>AmRole</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Entity
@Table(name="am_role")
public class AmRole extends BaseModel{
	public static final String ROLE_ID_GUEST = "4028d98139636d9d013963756ccc0024";
	public static final String ROLE_ID_ADMIN = "4028d98139636d9d01396370224d0006";
	public static final String ROLE_ID_MANAGER = "4028d98139943adc0139943e5fa60040";
	public static final String ROLE_ID_EDIT = "402880e93d16f27d013d1714baf002c3";
	
	@Id
	@Column(name="role_id")
	private String roleId = UUIDHexGenerator.getUuid();
	
	@Column(name="role_cd")
	private String roleCd;
	@Column(name="role_name")
	private String roleName;
	@Column(name="description")
	private String description;
	@Column(name="create_date_time")
	private Timestamp createDateTime = DateUtil.getNowTimstamp();
	@Column(name="modify_timestamp")
	private Timestamp modifyTimestamp = DateUtil.getNowTimstamp();
	@Column(name="data_state")
	private String dataState = Const.DB_DATASTATE_NORMAL;
		
	//----------------------------------------------- default constructor --------------------------
    public AmRole() {
    	super();
    }
    
    //------------------------------------------------ Property accessors --------------------------
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
	 * @return String
	 */
	public String getRoleCd() {
        return this.roleCd;
    }
    
    /**
	 * @param String roleCd
	 */
    public void setRoleCd(String roleCd) {
		this.roleCd = roleCd;
    }
    /**
	 * @return String
	 */
	public String getRoleName() {
        return this.roleName;
    }
    
    /**
	 * @param String roleName
	 */
    public void setRoleName(String roleName) {
		this.roleName = roleName;
    }
    /**
	 * @return String
	 */
	public String getDescription() {
        return this.description;
    }
    
    /**
	 * @param String description
	 */
    public void setDescription(String description) {
		this.description = description;
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
     
}