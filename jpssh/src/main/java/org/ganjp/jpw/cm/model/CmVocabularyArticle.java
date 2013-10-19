/**
 * $Id: CmVocabularyArticle.java,v 1.0 2012/08/19 00:16:55 GanJianping Exp $
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
 * <p>CmVocabularyArticle</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Entity
@Table(name="cm_vocabulary_article")
public class CmVocabularyArticle extends BaseModel{
	@Id
	@Column(name="vocabulary_article_id")
	private String vocabularyArticleId = UUIDHexGenerator.getUuid();
	
	@Column(name="article_id")
	private String articleId;
	@Column(name="vocabulary_id")
	private String vocabularyId;
	@Column(name="create_date_time")
	private Timestamp createDateTime = DateUtil.getNowTimstamp();
	@Column(name="modify_timestamp")
	private Timestamp modifyTimestamp = DateUtil.getNowTimstamp();
	@Column(name="data_state")
	private String dataState = Const.DB_DATASTATE_NORMAL;
		
	//----------------------------------------------- default constructor --------------------------
    public CmVocabularyArticle() {
    	super();
    }
    
    //------------------------------------------------ Property accessors --------------------------
/**
	 * @return String
	 */
	public String getVocabularyArticleId() {
        return this.vocabularyArticleId;
    }
    
    /**
	 * @param String vocabularyArticleId
	 */
    public void setVocabularyArticleId(String vocabularyArticleId) {
		this.vocabularyArticleId = vocabularyArticleId;
    }
    /**
	 * @return String
	 */
	public String getArticleId() {
        return this.articleId;
    }
    
    /**
	 * @param String articleId
	 */
    public void setArticleId(String articleId) {
		this.articleId = articleId;
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