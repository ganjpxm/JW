/**
 * $Id: CmEvaluateResult.java,v 1.0 2012/08/19 00:16:55 GanJianping Exp $
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
 * <p>CmEvaluateResult</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Entity
@Table(name="cm_evaluate_result")
public class CmEvaluateResult extends BaseModel{
	@Id
	@Column(name="evaluate_result_id")
	private String evaluateResultId = UUIDHexGenerator.getUuid();
	
	@Column(name="evaluate_item_id")
	private String evaluateItemId;
	@Column(name="table_id")
	private String tableId;
	@Column(name="table_type")
	private String tableType;
	@Column(name="item_name")
	private String itemName;
	@Column(name="item_position")
	private String itemPosition;
	@Column(name="item_value_type")
	private String itemValueType;
	@Column(name="item_value_id")
	private String itemValueId;
	@Column(name="item_value")
	private String itemValue;
	@Column(name="item_order")
	private Integer itemOrder;
	@Column(name="item_width")
	private String itemWidth;
	@Column(name="person_id")
	private String personId;
	@Column(name="kinsfolk_name")
	private String kinsfolkName;
	@Column(name="evaluate_date")
	private Date evaluateDate;
	@Column(name="operator_id")
	private String operatorId;
	@Column(name="operator_name")
	private String operatorName;
	@Column(name="remark")
	private String remark;
	@Column(name="create_date_time")
	private Timestamp createDateTime = DateUtil.getNowTimstamp();
	@Column(name="modify_timestamp")
	private Timestamp modifyTimestamp;
	@Column(name="data_state")
	private String dataState = Const.DB_DATASTATE_NORMAL;
		
	//----------------------------------------------- default constructor --------------------------
    public CmEvaluateResult() {
    	super();
    }
    
    //------------------------------------------------ Property accessors --------------------------
/**
	 * @return String
	 */
	public String getEvaluateResultId() {
        return this.evaluateResultId;
    }
    
    /**
	 * @param String evaluateResultId
	 */
    public void setEvaluateResultId(String evaluateResultId) {
		this.evaluateResultId = evaluateResultId;
    }
    /**
	 * @return String
	 */
	public String getEvaluateItemId() {
        return this.evaluateItemId;
    }
    
    /**
	 * @param String evaluateItemId
	 */
    public void setEvaluateItemId(String evaluateItemId) {
		this.evaluateItemId = evaluateItemId;
    }
    /**
	 * @return String
	 */
	public String getTableId() {
        return this.tableId;
    }
    
    /**
	 * @param String tableId
	 */
    public void setTableId(String tableId) {
		this.tableId = tableId;
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
	 * @return String
	 */
	public String getItemName() {
        return this.itemName;
    }
    
    /**
	 * @param String itemName
	 */
    public void setItemName(String itemName) {
		this.itemName = itemName;
    }
    /**
	 * @return String
	 */
	public String getItemPosition() {
        return this.itemPosition;
    }
    
    /**
	 * @param String itemPosition
	 */
    public void setItemPosition(String itemPosition) {
		this.itemPosition = itemPosition;
    }
    /**
	 * @return String
	 */
	public String getItemValueType() {
        return this.itemValueType;
    }
    
    /**
	 * @param String itemValueType
	 */
    public void setItemValueType(String itemValueType) {
		this.itemValueType = itemValueType;
    }
    /**
	 * @return String
	 */
	public String getItemValueId() {
        return this.itemValueId;
    }
    
    /**
	 * @param String itemValueId
	 */
    public void setItemValueId(String itemValueId) {
		this.itemValueId = itemValueId;
    }
    /**
	 * @return String
	 */
	public String getItemValue() {
        return this.itemValue;
    }
    
    /**
	 * @param String itemValue
	 */
    public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
    }
    /**
	 * @return Integer
	 */
	public Integer getItemOrder() {
        return this.itemOrder;
    }
    
    /**
	 * @param Integer itemOrder
	 */
    public void setItemOrder(Integer itemOrder) {
		this.itemOrder = itemOrder;
    }
    /**
	 * @return String
	 */
	public String getItemWidth() {
        return this.itemWidth;
    }
    
    /**
	 * @param String itemWidth
	 */
    public void setItemWidth(String itemWidth) {
		this.itemWidth = itemWidth;
    }
    /**
	 * @return String
	 */
	public String getPersonId() {
        return this.personId;
    }
    
    /**
	 * @param String personId
	 */
    public void setPersonId(String personId) {
		this.personId = personId;
    }
    /**
	 * @return String
	 */
	public String getKinsfolkName() {
        return this.kinsfolkName;
    }
    
    /**
	 * @param String kinsfolkName
	 */
    public void setKinsfolkName(String kinsfolkName) {
		this.kinsfolkName = kinsfolkName;
    }
    /**
	 * @return Date
	 */
	public Date getEvaluateDate() {
        return this.evaluateDate;
    }
    
    /**
	 * @param Date evaluateDate
	 */
    public void setEvaluateDate(Date evaluateDate) {
		this.evaluateDate = evaluateDate;
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