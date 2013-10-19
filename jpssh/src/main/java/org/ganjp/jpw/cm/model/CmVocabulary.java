/**
 * $Id: CmVocabulary.java,v 1.0 2012/08/19 00:16:55 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.model;

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
 * <p>CmVocabulary</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Entity
@Table(name="cm_vocabulary")
public class CmVocabulary extends BaseModel{
	@Id
	@Column(name="vocabulary_id")
	private String vocabularyId = UUIDHexGenerator.getUuid();
	
	@Column(name="first_name")
	private String firstName;
	@Column(name="first_phonogram")
	private String firstPhonogram;
	@Column(name="first_pronounce_url")
	private String firstPronounceUrl;
	@Column(name="first_mean")
	private String firstMean;
	@Column(name="first_description")
	private String firstDescription;
	@Column(name="second_name")
	private String secondName;
	@Column(name="second_phonogram")
	private String secondPhonogram;
	@Column(name="second_pronounce_url")
	private String secondPronounceUrl;
	@Column(name="second_mean")
	private String secondMean;
	@Column(name="second_description")
	private String secondDescription;
	@Column(name="image_url")
	private String imageUrl;
	@Column(name="tag")
	private String tag;
	@Column(name="level_id")
	private String levelId;
	@Column(name="level_name")
	private String levelName;
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
    public CmVocabulary() {
    	super();
    }
    
    //------------------------------------------------ Property accessors --------------------------
/**
	 * @return String
	 */
	public String getVocabularyId() {
        return this.vocabularyId;
    }
    
    /**
	 * @param String vocabularyId
	 */
    public void setVocabularyId(String vocabularyId) {
		this.vocabularyId = vocabularyId;
    }
    /**
	 * @return String
	 */
	public String getFirstName() {
        return this.firstName;
    }
    
    /**
	 * @param String firstName
	 */
    public void setFirstName(String firstName) {
		this.firstName = firstName;
    }
    
    public String getFirstPhonogram() {
		return firstPhonogram;
	}

	public void setFirstPhonogram(String firstPhonogram) {
		this.firstPhonogram = firstPhonogram;
	}

	public String getFirstPronounceUrl() {
		return firstPronounceUrl;
	}

	public void setFirstPronounceUrl(String firstPronounceUrl) {
		this.firstPronounceUrl = firstPronounceUrl;
	}

	/**
	 * @return String
	 */
	public String getSecondName() {
        return this.secondName;
    }
    
    /**
	 * @param String secondName
	 */
    public void setSecondName(String secondName) {
		this.secondName = secondName;
    }
    /**
	 * @return String
	 */
	public String getSecondPhonogram() {
        return this.secondPhonogram;
    }
    
    /**
	 * @param String secondPhonogram
	 */
    public void setSecondPhonogram(String secondPhonogram) {
		this.secondPhonogram = secondPhonogram;
    }
    /**
	 * @return String
	 */
	public String getSecondPronounceUrl() {
        return this.secondPronounceUrl;
    }
    
    /**
	 * @param String secondPronounceUrl
	 */
    public void setSecondPronounceUrl(String secondPronounceUrl) {
		this.secondPronounceUrl = secondPronounceUrl;
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
	public String getTag() {
        return this.tag;
    }
    
    /**
	 * @param String tag
	 */
    public void setTag(String tag) {
		this.tag = tag;
    }
    /**
	 * @return String
	 */
	public String getLevelId() {
        return this.levelId;
    }
    
    /**
	 * @param String levelId
	 */
    public void setLevelId(String levelId) {
		this.levelId = levelId;
    }
    /**
	 * @return String
	 */
	public String getLevelName() {
        return this.levelName;
    }
    
    /**
	 * @param String levelName
	 */
    public void setLevelName(String levelName) {
		this.levelName = levelName;
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
	 * @return String
	 */
	public String getRoleIds() {
        return this.roleIds;
    }
    
    /**
	 * @param String roleIds
	 */
    public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
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

	public String getFirstMean() {
		return firstMean;
	}

	public void setFirstMean(String firstMean) {
		this.firstMean = firstMean;
	}

	public String getFirstDescription() {
		return firstDescription;
	}

	public void setFirstDescription(String firstDescription) {
		this.firstDescription = firstDescription;
	}

	public String getSecondMean() {
		return secondMean;
	}

	public void setSecondMean(String secondMean) {
		this.secondMean = secondMean;
	}

	public String getSecondDescription() {
		return secondDescription;
	}

	public void setSecondDescription(String secondDescription) {
		this.secondDescription = secondDescription;
	}
    
}