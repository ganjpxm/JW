/**
 * $Id: BmConfig.java,v 1.0 2012/08/19 00:16:55 GanJianping Exp $
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
 * <p>BmConfig</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Entity
@Table(name="bm_config")
public class BmConfig extends BaseModel{
	public static final String CONFIG_CD_DEFAULT_AUDIO = "defaultAudio";
	public static final String SHOW_USER_SURVEY_AVG_SCORE_FOR_LEADER = "showUserSurveyAvgScoreForLeader";
	public static final String AUDIO_URL = "AudioUrl";
	public static final String IMAGE_URL = "ImageUrl";
	public static final String VIDEO_URL = "VideoUrl";
	public static final String FILE_URL = "FileUrl";

	public static final String SINGLE_SIGN_ON = "singleSignOn";
	
	public static final String FAQ = "faq";
	public static final String ABOUT_US = "aboutus";

	@Id
	@Column(name="config_id")
	private String configId = UUIDHexGenerator.getUuid();
	
	@Column(name="config_cd")
	private String configCd;
	@Column(name="config_name")
	private String configName;
	@Column(name="config_value")
	private String configValue;
	@Column(name="description")
	private String description;
	@Column(name="lang")
	private String lang;
	@Column(name="create_date_time")
	private Timestamp createDateTime = DateUtil.getNowTimstamp();
	@Column(name="modify_timestamp")
	private Timestamp modifyTimestamp = DateUtil.getNowTimstamp();
	@Column(name="data_state")
	private String dataState = Const.DB_DATASTATE_NORMAL;
		
	//----------------------------------------------- default constructor --------------------------
    public BmConfig() {
    	super();
    }
    
    //------------------------------------------------ Property accessors --------------------------
/**
	 * @return String
	 */
	public String getConfigId() {
        return this.configId;
    }
    
    /**
	 * @param String configId
	 */
    public void setConfigId(String configId) {
		this.configId = configId;
    }
    /**
	 * @return String
	 */
	public String getConfigCd() {
        return this.configCd;
    }
    
    /**
	 * @param String configCd
	 */
    public void setConfigCd(String configCd) {
		this.configCd = configCd;
    }
    /**
	 * @return String
	 */
	public String getConfigName() {
        return this.configName;
    }
    
    /**
	 * @param String configName
	 */
    public void setConfigName(String configName) {
		this.configName = configName;
    }
    /**
	 * @return String
	 */
	public String getConfigValue() {
        return this.configValue;
    }
    
    /**
	 * @param String configValue
	 */
    public void setConfigValue(String configValue) {
		this.configValue = configValue;
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

	public Timestamp getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Timestamp createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Timestamp getModifyTimestamp() {
		return modifyTimestamp;
	}

	public void setModifyTimestamp(Timestamp modifyTimestamp) {
		this.modifyTimestamp = modifyTimestamp;
	}

	public String getDataState() {
		return dataState;
	}

	public void setDataState(String dataState) {
		this.dataState = dataState;
	}
    
}