/**
 * $Id: CmArticleRemark.java,v 1.0 2012/08/19 00:16:55 GanJianping Exp $
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
 * <p>CmArticleRemark</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Entity
@Table(name="cm_article_remark")
public class CmArticleRemark extends BaseModel{
	@Id
	@Column(name="article_remark_id")
	private String articleRemarkId = UUIDHexGenerator.getUuid();
	
	@Column(name="article_id")
	private String articleId;
	@Column(name="remark")
	private String remark;
	@Column(name="display_no")
	private Integer displayNo;
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
    public CmArticleRemark() {
    	super();
    }
    
    //------------------------------------------------ Property accessors --------------------------
/**
	 * @return String
	 */
	public String getArticleRemarkId() {
        return this.articleRemarkId;
    }
    
    /**
	 * @param String articleRemarkId
	 */
    public void setArticleRemarkId(String articleRemarkId) {
		this.articleRemarkId = articleRemarkId;
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
	public String getRemark() {
        return this.remark;
    }
    
    /**
	 * @param String remark
	 */
    public void setRemark(String remark) {
		this.remark = remark;
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