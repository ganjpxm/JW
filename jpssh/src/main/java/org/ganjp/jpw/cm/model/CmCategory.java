/**
 * $Id: CmCategory.java,v 1.0 2012/08/19 00:16:55 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.model;

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
 * <p>CmCategory</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Entity
@Table(name="cm_category")
public class CmCategory extends BaseModel{
	public static final String CATEGORY_CD_PHOTO_CATEGORY = "01";
	public static final String CATEGORY_CD_HOME_PHOTO_SCROLLED = "0101";
	public static final String CATEGORY_CD_YANGJL_PHOTO = "0102";
	public static final String CATEGORY_CD_VOCABULARY_CATEGORY = "02";
	public static final String CATEGORY_CD_ARTICLE_CATEGORY = "03";
	
	public static final String CATEGORY_CD_AUDIO_CATEGORY = "04";
	public static final String CATEGORY_CD_VIDEO_CATEGORY = "05";
	public static final String CATEGORY_CD_FILE_CATEGORY = "06";
	
	public static final String END_FLAG_NO = "0";
	public static final String END_FLAG_YES = "1";
	@Id
	@Column(name="category_id")
	private String categoryId = UUIDHexGenerator.getUuid();
	
	@Column(name="category_pid")
	private String categoryPid;
	@Column(name="category_cd")
	private String categoryCd;
	@Column(name="category_name")
	private String categoryName;
	@Column(name="end_flag")
	private String endFlag;
	@Column(name="display_no")
	private Integer displayNo;
	@Column(name="lang")
	private String lang;
	@Column(name="role_ids")
	private String roleIds;
	@Column(name="operator_id")
	private String operatorId;
	@Column(name="operator_name")
	private String operatorName;
	@Column(name="create_date_time")
	private Timestamp createDateTime = DateUtil.getNowTimstamp();
	@Column(name="modify_timestamp")
	private Timestamp modifyTimestamp = DateUtil.getNowTimstamp();
	@Column(name="data_state")
	private String dataState = Const.DB_DATASTATE_NORMAL;
		
	//----------------------------------------------- default constructor --------------------------
    public CmCategory() {
    	super();
    }
    
    //------------------------------------------------ Property accessors --------------------------
/**
	 * @return String
	 */
	public String getCategoryId() {
        return this.categoryId;
    }
    
    /**
	 * @param String categoryId
	 */
    public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
    }
    /**
	 * @return String
	 */
	public String getCategoryPid() {
        return this.categoryPid;
    }
    
    /**
	 * @param String categoryPid
	 */
    public void setCategoryPid(String categoryPid) {
		this.categoryPid = categoryPid;
    }
    /**
	 * @return String
	 */
	public String getCategoryName() {
        return this.categoryName;
    }
    
    /**
	 * @param String categoryName
	 */
    public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
	public String getOperatorId() {
        return this.operatorId;
    }
    
    /**
	 * @param String operatorId
	 */
    public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
    }
    /**
	 * @return String
	 */
	public String getOperatorName() {
        return this.operatorName;
    }
    
    /**
	 * @param String operatorName
	 */
    public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
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

	public String getCategoryCd() {
		return categoryCd;
	}

	public void setCategoryCd(String categoryCd) {
		this.categoryCd = categoryCd;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public Integer getDisplayNo() {
		return displayNo;
	}

	public void setDisplayNo(Integer displayNo) {
		this.displayNo = displayNo;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	
}