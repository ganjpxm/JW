/**
 * $Id: CmPhotoCategory.java,v 1.0 2012/08/19 00:16:55 GanJianping Exp $
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
 * <p>CmPhotoCategory</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Entity
@Table(name="cm_photo_category")
public class CmPhotoCategory extends BaseModel{
	@Id
	@Column(name="photo_category_id")
	private String photoCategoryId = UUIDHexGenerator.getUuid();
	
	@Column(name="photo_id")
	private String photoId;
	@Column(name="category_id")
	private String categoryId;
	@Column(name="default_photo")
	private String defaultPhoto;
	@Column(name="create_date_time")
	private Timestamp createDateTime = DateUtil.getNowTimstamp();
	@Column(name="modify_timestamp")
	private Timestamp modifyTimestamp = DateUtil.getNowTimstamp();
	@Column(name="data_state")
	private String dataState = Const.DB_DATASTATE_NORMAL;
		
	//----------------------------------------------- default constructor --------------------------
    public CmPhotoCategory() {
    	super();
    }
    
    //------------------------------------------------ Property accessors --------------------------
/**
	 * @return String
	 */
	public String getPhotoCategoryId() {
        return this.photoCategoryId;
    }
    
    /**
	 * @param String photoCategoryId
	 */
    public void setPhotoCategoryId(String photoCategoryId) {
		this.photoCategoryId = photoCategoryId;
    }
    /**
	 * @return String
	 */
	public String getPhotoId() {
        return this.photoId;
    }
    
    /**
	 * @param String photoId
	 */
    public void setPhotoId(String photoId) {
		this.photoId = photoId;
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
	 * @return String
	 */
	public String getDefaultPhoto() {
        return this.defaultPhoto;
    }
    
    /**
	 * @param String defaultPhoto
	 */
    public void setDefaultPhoto(String defaultPhoto) {
		this.defaultPhoto = defaultPhoto;
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