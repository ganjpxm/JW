/**
 * $Id: CmEvaluateTable.java,v 1.0 2012/08/19 00:16:55 GanJianping Exp $
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

import java.util.Date;
import java.sql.Timestamp;

/**
 * <p>CmEvaluateTable</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Entity
@Table(name="cm_evaluate_table")
public class CmEvaluateTable extends BaseModel{
	public static final String TABLE_TYPE_AVG_SCORE = "0";
	public static final String TABLE_TYPE_SEL_NUMBER = "1";
	
	@Id
	@Column(name="evaluate_table_id")
	private String evaluateTableId = UUIDHexGenerator.getUuid();
	
	@Column(name="table_cd")
	private String tableCd;
	@Column(name="table_name")
	private String tableName;
	@Column(name="table_type")
	private String tableType;
	@Column(name="start_date")
	private Date startDate;
	@Column(name="end_date")
	private Date endDate;
	@Column(name="role_ids")
	private String roleIds;
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
    public CmEvaluateTable() {
    	super();
    }
    
    //------------------------------------------------ Property accessors --------------------------
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
	public String getTableCd() {
        return this.tableCd;
    }
    
    /**
	 * @param String tableCd
	 */
    public void setTableCd(String tableCd) {
		this.tableCd = tableCd;
    }
    /**
	 * @return String
	 */
	public String getTableName() {
        return this.tableName;
    }
    
    /**
	 * @param String tableName
	 */
    public void setTableName(String tableName) {
		this.tableName = tableName;
    }
    /**
	 * @return String
	 */
	public String getTableType() {
        return this.tableType;
    }
    
    /**
	 * @param String tableType
	 */
    public void setTableType(String tableType) {
		this.tableType = tableType;
    }
    /**
	 * @return Date
	 */
	public Date getStartDate() {
        return this.startDate;
    }
    
    /**
	 * @param Date startDate
	 */
    public void setStartDate(Date startDate) {
		this.startDate = startDate;
    }
    /**
	 * @return Date
	 */
	public Date getEndDate() {
        return this.endDate;
    }
    
    /**
	 * @param Date endDate
	 */
    public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
     
}