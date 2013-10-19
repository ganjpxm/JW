/**
 * $Id: CmPhoto.java,v 1.0 2012/08/19 00:16:55 GanJianping Exp $
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
 * <p>CmPhoto</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Entity
@Table(name="cm_photo")
public class CmPhoto extends BaseModel{
	public static final String SHOW_TYPE_MERGING_IMAGE_BOXES = "mergingImageBoxes";
	public static final String SHOW_TYPE_SLIDING_GALLERY = "slidingGallery";
	
	public static final String PHOTO_TAG_WEBSITE_THEME = "Website Theme";
	
	@Id
	@Column(name="photo_id")
	private String photoId = UUIDHexGenerator.getUuid();
	
	@Column(name="photo_name")
	private String photoName;
	@Column(name="title")
	private String title;
	@Column(name="thumb_url")
	private String thumbUrl;
	@Column(name="url")
	private String url;
	@Column(name="origin_url")
	private String originUrl;
	@Column(name="tag")
	private String tag;
	@Column(name="remark")
	private String remark;
	@Column(name="display_no")
	private Integer displayNo;
	@Column(name="lang")
	private String lang;
	@Column(name="role_ids")
	private String roleIds;
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
	
	@Column(name="ref_article_id")
	private String refArticleId;
	
	//----------------------------------------------- default constructor --------------------------
    public CmPhoto() {
    	super();
    }
    
    //------------------------------------------------ Property accessors --------------------------
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
	public String getPhotoName() {
        return this.photoName;
    }
    
    /**
	 * @param String photoName
	 */
    public void setPhotoName(String photoName) {
		this.photoName = photoName;
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

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public Integer getDisplayNo() {
		return displayNo;
	}

	public void setDisplayNo(Integer displayNo) {
		this.displayNo = displayNo;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getThumbUrl() {
		return thumbUrl;
	}

	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}

	public String getOriginUrl() {
		return originUrl;
	}

	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
	}

	public String getRefArticleId() {
		return refArticleId;
	}

	public void setRefArticleId(String refArticleId) {
		this.refArticleId = refArticleId;
	}
	
}