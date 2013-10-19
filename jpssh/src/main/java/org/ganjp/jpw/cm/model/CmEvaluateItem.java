/**
 * $Id: CmEvaluateItem.java,v 1.0 2012/08/19 00:16:55 GanJianping Exp $
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
 * <p>CmEvaluateItem</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Entity
@Table(name="cm_evaluate_item")
public class CmEvaluateItem extends BaseModel{
	public static final String ITEM_POSTION_TABLE_TOP = "tableTop";
	public static final String ITEM_POSTION_TABLE_BOTTOM = "tableBottom";
	public static final String ITEM_POSTION_TABLE_ITEM = "tableItem";
	
	public static final String ITEM_POSTION_ORDER = "order";
	
	public static final String ITEM_VALUE_TYPE_INPUT = "input";
	public static final String ITEM_VALUE_TYPE_DATE = "date";
	public static final String ITEM_VALUE_TYPE_PICTURE = "picture";
	public static final String ITEM_VALUE_TYPE_TEXTAREA = "textArea";

	@Id
	@Column(name="evaluate_item_id")
	private String evaluateItemId = UUIDHexGenerator.getUuid();
	
	@Column(name="evaluate_table_id")
	private String evaluateTableId;
	@Column(name="item_name")
	private String itemName;
	@Column(name="item_position")
	private String itemPosition;
	@Column(name="item_value_type")
	private String itemValueType;
	@Column(name="item_value_type_id")
	private String itemValueTypeId;
	@Column(name="item_options")
	private String itemOptions;
	@Column(name="item_order")
	private Integer itemOrder;
	@Column(name="item_width")
	private String itemWidth;
	@Column(name="create_date_time")
	private Timestamp createDateTime = DateUtil.getNowTimstamp();
	@Column(name="modify_timestamp")
	private Timestamp modifyTimestamp;
	@Column(name="data_state")
	private String dataState = Const.DB_DATASTATE_NORMAL;
		
	//----------------------------------------------- default constructor --------------------------
    public CmEvaluateItem() {
    	super();
    }

    public CmEvaluateItem(String evaluateTableId, String itemName, String itemOption, Integer itemOrder) {
    	this.setEvaluateTableId(evaluateTableId);
    	this.setItemName(itemName);
    	this.setItemOptions(itemOption);
    	this.setItemOrder(itemOrder);
    }

    //------------------------------------------------ Property accessors --------------------------
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
	public String getItemValueTypeId() {
        return this.itemValueTypeId;
    }
    
    /**
	 * @param String itemValueTypeId
	 */
    public void setItemValueTypeId(String itemValueTypeId) {
		this.itemValueTypeId = itemValueTypeId;
    }
    /**
	 * @return String
	 */
	public String getItemOptions() {
        return this.itemOptions;
    }
    
    /**
	 * @param String itemOptions
	 */
    public void setItemOptions(String itemOptions) {
		this.itemOptions = itemOptions;
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