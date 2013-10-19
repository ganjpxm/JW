/**
 * $Id: AmUser.java,v 1.0 2012/08/19 00:16:55 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.am.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.ganjp.jpw.core.Const;
import org.ganjp.jpw.core.dao.uuid.UUIDHexGenerator;
import org.ganjp.jpw.core.model.BaseModel;
import org.ganjp.jpw.core.util.DateUtil;

/**
 * <p>AmUser</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Entity
@Table(name="am_user")
public class AmUser extends BaseModel{
	public static final String USER_ALIAS_DEPARTMENT_LEADER = "departmentLeader";
	public static final String USER_ALIAS_ADMIN = "admin";
	
	
	@Id
	@Column(name="user_id")
	private String userId = UUIDHexGenerator.getUuid();
	
	@Column(name="user_cd")
	private String userCd;
	@Column(name="user_name")
	private String userName;
	@Column(name="user_alias")
	private String userAlias;
	@Column(name="user_level")
	private Integer userLevel;
	@Column(name="user_score")
	private Integer userScore;
	@Column(name="login_times")
	private Integer loginTimes;
	@Column(name="password")
	private String password;
	@Column(name="mobile_phone")
	private String mobilePhone;
	@Column(name="qq")
	private String qq;
	@Column(name="email")
	private String email;
	@Column(name="birthday")
	private Date birthday;
	@Column(name="photo_url")
	private String photoUrl;
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
	@Column(name="life_motto")
	private String lifeMotto;
	
	@Transient
	private String roleIds;
	@Transient
	private List<String> roleNames;
	@Transient
	private String sessionId;
	
	//----------------------------------------------- default constructor --------------------------
    public AmUser() {
    	super();
    }
    
    //------------------------------------------------ Property accessors --------------------------
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
	public String getUserCd() {
        return this.userCd;
    }
    
    /**
	 * @param String userCd
	 */
    public void setUserCd(String userCd) {
		this.userCd = userCd;
    }
    /**
	 * @return String
	 */
	public String getUserName() {
        return this.userName;
    }
    
    /**
	 * @param String userName
	 */
    public void setUserName(String userName) {
		this.userName = userName;
    }
    /**
	 * @return String
	 */
	public String getUserAlias() {
        return this.userAlias;
    }
    
    /**
	 * @param String userAlias
	 */
    public void setUserAlias(String userAlias) {
		this.userAlias = userAlias;
    }
    /**
	 * @return Integer
	 */
	public Integer getUserLevel() {
        return this.userLevel;
    }
    
    /**
	 * @param Integer userLevel
	 */
    public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
    }
    /**
	 * @return Integer
	 */
	public Integer getUserScore() {
        return this.userScore;
    }
    
    /**
	 * @param Integer userScore
	 */
    public void setUserScore(Integer userScore) {
		this.userScore = userScore;
    }
    /**
	 * @return Integer
	 */
	public Integer getLoginTimes() {
        return this.loginTimes;
    }
    
    /**
	 * @param Integer loginTimes
	 */
    public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
    }
    /**
	 * @return String
	 */
	public String getPassword() {
        return this.password;
    }
    
    /**
	 * @param String password
	 */
    public void setPassword(String password) {
		this.password = password;
    }
    /**
	 * @return String
	 */
	public String getMobilePhone() {
        return this.mobilePhone;
    }
    
    /**
	 * @param String mobilePhone
	 */
    public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
    }
    /**
	 * @return String
	 */
	public String getQq() {
        return this.qq;
    }
    
    /**
	 * @param String qq
	 */
    public void setQq(String qq) {
		this.qq = qq;
    }
    /**
	 * @return String
	 */
	public String getEmail() {
        return this.email;
    }
    
    /**
	 * @param String email
	 */
    public void setEmail(String email) {
		this.email = email;
    }
    /**
	 * @return Date
	 */
	public Date getBirthday() {
        return this.birthday;
    }
    
    /**
	 * @param Date birthday
	 */
    public void setBirthday(Date birthday) {
		this.birthday = birthday;
    }
    /**
	 * @return String
	 */
	public String getPhotoUrl() {
        return this.photoUrl;
    }
    
    /**
	 * @param String photoUrl
	 */
    public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
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

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public List<String> getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(List<String> roleNames) {
		this.roleNames = roleNames;
	}

	public String getLifeMotto() {
		return lifeMotto;
	}

	public void setLifeMotto(String lifeMotto) {
		this.lifeMotto = lifeMotto;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
}