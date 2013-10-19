/**
 * $Id: BmMenu.java,v 1.0 2012/08/19 00:16:55 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.bm.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.ganjp.jpw.core.Const;
import org.ganjp.jpw.core.dao.uuid.UUIDHexGenerator;
import org.ganjp.jpw.core.model.BaseModel;
import org.ganjp.jpw.core.util.DateUtil;

/**
 * <p>BmMenu</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Entity
@Table(name="bm_menu")
public class BmMenu extends BaseModel{
	public static final String MENU_TYPE_MANAGEMENT_CONSOLE = "managementConsole";
	public static final String MENU_CD_HOME = "02";
	
	public static final String END_FLAG_NO = "0";
	public static final String END_FLAG_YES = "1";
	
	@Id
	@Column(name="menu_id")
	private String menuId = UUIDHexGenerator.getUuid();
	
	@Column(name="menu_pid")
	private String menuPid;
	@Column(name="menu_cd")
	private String menuCd;
	@Column(name="menu_name")
	private String menuName;
	@Column(name="image_url")
	private String imageUrl;
	@Column(name="url")
	private String url;
	@Column(name="display_level")
	private Integer displayLevel;
	@Column(name="display_no")
	private Integer displayNo;
	@Column(name="end_flag")
	private String endFlag;
	@Column(name="menu_category_id")
	private String menuCategoryId;
	@Column(name="lang")
	private String lang;
	@Column(name="role_ids")
	private String roleIds;
	@Column(name="create_date_time")
	private Timestamp createDateTime = DateUtil.getNowTimstamp();
	@Column(name="modify_timestamp")
	private Timestamp modifyTimestamp = DateUtil.getNowTimstamp();
	@Column(name="data_state")
	private String dataState = Const.DB_DATASTATE_NORMAL;
		
	//----------------------------------------------- default constructor --------------------------
    public BmMenu() {
    	super();
    }
    
    //------------------------------------------------ Property accessors --------------------------
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
	public String getMenuPid() {
        return this.menuPid;
    }
    
    /**
	 * @param String menuPid
	 */
    public void setMenuPid(String menuPid) {
		this.menuPid = menuPid;
    }
    /**
	 * @return String
	 */
	public String getMenuCd() {
        return this.menuCd;
    }
    
    /**
	 * @param String menuCd
	 */
    public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
    }
    /**
	 * @return String
	 */
	public String getMenuName() {
        return this.menuName;
    }
    
    /**
	 * @param String menuName
	 */
    public void setMenuName(String menuName) {
		this.menuName = menuName;
    }
    /**
	 * @return String
	 */
	public String getImageUrl() {
        return this.imageUrl;
    }
    
    /**
	 * @param String imageUrl
	 */
    public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
    }
    /**
	 * @return String
	 */
	public String getUrl() {
        return this.url;
    }
    
    /**
	 * @param String url
	 */
    public void setUrl(String url) {
		this.url = url;
    }
    /**
	 * @return Integer
	 */
	public Integer getDisplayLevel() {
        return this.displayLevel;
    }
    
    /**
	 * @param Integer displayLevel
	 */
    public void setDisplayLevel(Integer displayLevel) {
		this.displayLevel = displayLevel;
    }
    /**
	 * @return Integer
	 */
	public Integer getDisplayNo() {
        return this.displayNo;
    }
    
    /**
	 * @param Integer displayNo
	 */
    public void setDisplayNo(Integer displayNo) {
		this.displayNo = displayNo;
    }
    /**
	 * @return String
	 */
	public String getEndFlag() {
        return this.endFlag;
    }
    
    /**
	 * @param String endFlag
	 */
    public void setEndFlag(String endFlag) {
		this.endFlag = endFlag;
    }
    /**
	 * @return String
	 */
	public String getMenuCategoryId() {
        return this.menuCategoryId;
    }
    
    /**
	 * @param String menuCategoryId
	 */
    public void setMenuCategoryId(String menuCategoryId) {
		this.menuCategoryId = menuCategoryId;
    }
    /**
	 * @return String
	 */
	public String getLang() {
        return this.lang;
    }
    
    /**
	 * @param String lang
	 */
    public void setLang(String lang) {
		this.lang = lang;
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

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
}