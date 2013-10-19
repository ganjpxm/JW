/**
 * $Id: CmFile.java,v 1.0 2012/08/19 00:16:55 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.model;

import org.ganjp.jpw.core.model.BaseModel;
import org.ganjp.jpw.core.dao.uuid.UUIDHexGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Timestamp;

/**
 * <p>CmFile</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Entity
@Table(name="cm_file")
public class CmFile extends BaseModel{
	@Id
	@Column(name="file_id")
	private String fileId = UUIDHexGenerator.getUuid();
	
	@Column(name="file_name")
	private String fileName;
	@Column(name="title")
	private String title;
	@Column(name="thumb_url")
	private String thumbUrl;
	@Column(name="url")
	private String url;
	@Column(name="origin_url")
	private String originUrl;
	@Column(name="origin_website")
	private String originWebsite;
	@Column(name="tag")
	private String tag;
	@Column(name="display_no")
	private Integer displayNo;
	@Column(name="description")
	private String description;
	@Column(name="remark")
	private String remark;
	@Column(name="lang")
	private String lang;
	@Column(name="role_ids")
	private String roleIds;
	@Column(name="operator_id")
	private String operatorId;
	@Column(name="operator_name")
	private String operatorName;
	@Column(name="create_date_time")
	private Timestamp createDateTime;
	@Column(name="modify_timestamp")
	private Timestamp modifyTimestamp;
	@Column(name="data_state")
	private String dataState;
		
	//----------------------------------------------- default constructor --------------------------
    public CmFile() {
    	super();
    }
    
    //------------------------------------------------ Property accessors --------------------------
/**
	 * @return String
	 */
	public String getFileId() {
        return this.fileId;
    }
    
    /**
	 * @param String fileId
	 */
    public void setFileId(String fileId) {
		this.fileId = fileId;
    }
    /**
	 * @return String
	 */
	public String getFileName() {
        return this.fileName;
    }
    
    /**
	 * @param String fileName
	 */
    public void setFileName(String fileName) {
		this.fileName = fileName;
    }
    /**
	 * @return String
	 */
	public String getTitle() {
        return this.title;
    }
    
    /**
	 * @param String title
	 */
    public void setTitle(String title) {
		this.title = title;
    }
    /**
	 * @return String
	 */
	public String getThumbUrl() {
        return this.thumbUrl;
    }
    
    /**
	 * @param String thumbUrl
	 */
    public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
    }
    /**
	 * @return String
	 */
	public String getUrl() {
        return this.url;
    }
    
    /**
	 * @param String url
	 */
    public void setUrl(String url) {
		this.url = url;
    }
    /**
	 * @return String
	 */
	public String getOriginUrl() {
        return this.originUrl;
    }
    
    /**
	 * @param String originUrl
	 */
    public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
    }
    /**
	 * @return String
	 */
	public String getOriginWebsite() {
        return this.originWebsite;
    }
    
    /**
	 * @param String originWebsite
	 */
    public void setOriginWebsite(String originWebsite) {
		this.originWebsite = originWebsite;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
}