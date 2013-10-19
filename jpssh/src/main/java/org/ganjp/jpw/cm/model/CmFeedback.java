/**
 * $Id: CmFeedback.java,v 1.0 2012/08/19 00:16:55 GanJianping Exp $
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
 * <p>CmFeedback</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Entity
@Table(name="cm_feedback")
public class CmFeedback extends BaseModel{
	@Id
	@Column(name="feedback_id")
	private String feedbackId = UUIDHexGenerator.getUuid();
	
	@Column(name="feedback_type")
	private String feedbackType;
	@Column(name="section")
	private String section;
	@Column(name="subject")
	private String subject;
	@Column(name="content")
	private String content;
	@Column(name="operator_id")
	private String operatorId;
	@Column(name="operator_name")
	private String operatorName;
	@Column(name="create_date_time")
	private Timestamp createDateTime = DateUtil.getNowTimstamp();
	@Column(name="modify_timestamp")
	private Timestamp modifyTimestamp;
	@Column(name="data_state")
	private String dataState = Const.DB_DATASTATE_NORMAL;
	
	//----------------------------------------------- default constructor --------------------------
    public CmFeedback() {
    	super();
    }
    
    //------------------------------------------------ Property accessors --------------------------
/**
	 * @return String
	 */
	public String getFeedbackId() {
        return this.feedbackId;
    }
    
    /**
	 * @param String feedbackId
	 */
    public void setFeedbackId(String feedbackId) {
		this.feedbackId = feedbackId;
    }
    /**
	 * @return String
	 */
	public String getFeedbackType() {
        return this.feedbackType;
    }
    
    /**
	 * @param String feedbackType
	 */
    public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
    }
    /**
	 * @return String
	 */
	public String getSection() {
        return this.section;
    }
    
    /**
	 * @param String section
	 */
    public void setSection(String section) {
		this.section = section;
    }
    /**
	 * @return String
	 */
	public String getSubject() {
        return this.subject;
    }
    
    /**
	 * @param String subject
	 */
    public void setSubject(String subject) {
		this.subject = subject;
    }
    /**
	 * @return String
	 */
	public String getContent() {
        return this.content;
    }
    
    /**
	 * @param String content
	 */
    public void setContent(String content) {
		this.content = content;
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
     
}