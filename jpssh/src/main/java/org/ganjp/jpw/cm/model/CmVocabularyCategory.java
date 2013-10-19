/**
 * $Id: CmVocabularyCategory.java,v 1.0 2012/08/19 00:16:55 GanJianping Exp $
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
 * <p>CmVocabularyCategory</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Entity
@Table(name="cm_vocabulary_category")
public class CmVocabularyCategory extends BaseModel{
	@Id
	@Column(name="vocabulary_category_id")
	private String vocabularyCategoryId = UUIDHexGenerator.getUuid();
	
	@Column(name="vocabulary_id")
	private String vocabularyId;
	@Column(name="category_id")
	private String categoryId;
	@Column(name="create_date_time")
	private Timestamp createDateTime = DateUtil.getNowTimstamp();
	@Column(name="modify_timestamp")
	private Timestamp modifyTimestamp = DateUtil.getNowTimstamp();
	@Column(name="data_state")
	private String dataState = Const.DB_DATASTATE_NORMAL;
		
	//----------------------------------------------- default constructor --------------------------
    public CmVocabularyCategory() {
    	super();
    }
    
    //------------------------------------------------ Property accessors --------------------------
/**
	 * @return String
	 */
	public String getVocabularyCategoryId() {
        return this.vocabularyCategoryId;
    }
    
    /**
	 * @param String vocabularyCategoryId
	 */
    public void setVocabularyCategoryId(String vocabularyCategoryId) {
		this.vocabularyCategoryId = vocabularyCategoryId;
    }
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