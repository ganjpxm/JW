/**
 * $Id: BmParam.java,v 1.0 2012/08/19 00:16:55 GanJianping Exp $
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
 * <p>BmParam</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Entity
@Table(name="bm_param")
public class BmParam extends BaseModel{
	public static final String PARAM_TYPE_CD_MENU_CATEGORY = "00";
	public static final String PARAM_CD_MANAGEMENT_CONSOLE = "0001";
	public static final String PARAM_CD_HOME_MAIN_MENU = "0002";
	public static final String PARAM_CD_HOME_LINK_SET = "0003";
	public static final String PARAM_CD_ARTICLE_TAG = "02";
	public static final String PARAM_CD_PHOTO_TAG = "03";
	public static final String PARAM_CD_AUDIO_TAG = "04";
	public static final String PARAM_CD_VIDEO_TAG = "05";
	public static final String PARAM_CD_FILE_TAG = "06";
	
	@Id
	@Column(name="param_id")
	private String paramId = UUIDHexGenerator.getUuid();
	
	@Column(name="param_cd")
	private String paramCd;
	@Column(name="param_name")
	private String paramName;
	@Column(name="param_type_cd")
	private String paramTypeCd;
	@Column(name="param_type_name")
	private String paramTypeName;
	@Column(name="display_no")
	private Integer displayNo;
	@Column(name="lang")
	private String lang;
	@Column(name="create_date_time")
	private Timestamp createDateTime = DateUtil.getNowTimstamp();
	@Column(name="modify_timestamp")
	private Timestamp modifyTimestamp = DateUtil.getNowTimstamp();
	@Column(name="data_state")
	private String dataState = Const.DB_DATASTATE_NORMAL;
		
	//----------------------------------------------- default constructor --------------------------
    public BmParam() {
    	super();
    }
    
    //------------------------------------------------ Property accessors --------------------------
/**
	 * @return String
	 */
	public String getParamId() {
        return this.paramId;
    }
    
    /**
	 * @param String paramId
	 */
    public void setParamId(String paramId) {
		this.paramId = paramId;
    }
    /**
	 * @return String
	 */
	public String getParamCd() {
        return this.paramCd;
    }
    
    /**
	 * @param String paramCd
	 */
    public void setParamCd(String paramCd) {
		this.paramCd = paramCd;
    }
    /**
	 * @return String
	 */
	public String getParamName() {
        return this.paramName;
    }
    
    /**
	 * @param String paramName
	 */
    public void setParamName(String paramName) {
		this.paramName = paramName;
    }
    /**
	 * @return String
	 */
	public String getParamTypeCd() {
        return this.paramTypeCd;
    }
    
    /**
	 * @param String paramTypeCd
	 */
    public void setParamTypeCd(String paramTypeCd) {
		this.paramTypeCd = paramTypeCd;
    }
    /**
	 * @return String
	 */
	public String getParamTypeName() {
        return this.paramTypeName;
    }
    
    /**
	 * @param String paramTypeName
	 */
    public void setParamTypeName(String paramTypeName) {
		this.paramTypeName = paramTypeName;
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