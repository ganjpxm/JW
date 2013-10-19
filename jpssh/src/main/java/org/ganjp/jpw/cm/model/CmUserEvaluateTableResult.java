/**
 * $Id: CmUserEvaluateTableResult.java,v 1.0 2012/08/19 00:16:55 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.ganjp.jpw.core.Const;
import org.ganjp.jpw.core.dao.uuid.UUIDHexGenerator;
import org.ganjp.jpw.core.model.BaseModel;
import org.ganjp.jpw.core.util.DateUtil;

/**
 * <p>CmUserEvaluateTableResult</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Entity
@Table(name="cm_user_evaluate_table_result")
public class CmUserEvaluateTableResult extends BaseModel{
	@Id
	@Column(name="user_evaluate_table_result_id")
	private String userEvaluateTableResultId = UUIDHexGenerator.getUuid();
	
	@Column(name="user_id")
	private String userId;
	@Column(name="evaluate_table_id")
	private String evaluateTableId;
	@Column(name="result")
	private String result;
	@Column(name="score")
	private BigDecimal score;
	@Column(name="remark")
	private String remark;
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
	
	@Column(name="evaluate_date")
	private Date evaluateDate;
	@Column(name="user_name")
	private String userName;
		
	//----------------------------------------------- default constructor --------------------------
    public CmUserEvaluateTableResult() {
    	super();
    }
    
    //------------------------------------------------ Property accessors --------------------------
/**
	 * @return String
	 */
	public String getUserEvaluateTableResultId() {
        return this.userEvaluateTableResultId;
    }
    
    /**
	 * @param String userEvaluateTableResultId
	 */
    public void setUserEvaluateTableResultId(String userEvaluateTableResultId) {
		this.userEvaluateTableResultId = userEvaluateTableResultId;
    }
    /**
	 * @return String
	 */
	public String getUserId() {
        return this.userId;
    }
    
    /**
	 * @param String userId
	 */
    public void setUserId(String userId) {
		this.userId = userId;
    }
    /**
	 * @return String
	 */
	public String getEvaluateTableId() {
        return this.evaluateTableId;
    }
    
    /**
	 * @param String evaluateTableId
	 */
    public void setEvaluateTableId(String evaluateTableId) {
		this.evaluateTableId = evaluateTableId;
    }
    /**
	 * @return String
	 */
	public String getResult() {
        return this.result;
    }
    
    /**
	 * @param String result
	 */
    public void setResult(String result) {
		this.result = result;
    }
    /**
	 * @return BigDecimal
	 */
	public BigDecimal getScore() {
        return this.score;
    }
    
    /**
	 * @param BigDecimal score
	 */
    public void setScore(BigDecimal score) {
		this.score = score;
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

	public Date getEvaluateDate() {
		return evaluateDate;
	}

	public void setEvaluateDate(Date evaluateDate) {
		this.evaluateDate = evaluateDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}