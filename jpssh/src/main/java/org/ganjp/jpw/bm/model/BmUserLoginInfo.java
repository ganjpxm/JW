/**
 * $Id: BmUserLoginInfo.java,v 1.0 2012/08/19 00:16:55 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.bm.model;

import java.sql.Timestamp;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.ganjp.jpw.core.dao.uuid.UUIDHexGenerator;
import org.ganjp.jpw.core.model.BaseModel;
import org.ganjp.jpw.core.util.DateUtil;
import org.ganjp.jpw.core.web.util.UserAgentUtil;

/**
 * <p>BmUserLoginInfo</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Entity
@Table(name="bm_user_login_info")
public class BmUserLoginInfo extends BaseModel{
	@Id
	@Column(name="user_login_info_id")
	private String userLoginInfoId = UUIDHexGenerator.getUuid();
	
	@Column(name="login_user_id")
	private String loginUserId;
	@Column(name="login_user_name")
	private String loginUserName;
	@Column(name="login_time")
	 private Timestamp loginTime = DateUtil.getNowTimstamp();
	@Column(name="logout_time")
	private Timestamp logoutTime;
	@Column(name="device_type")
	private String deviceType = "Normal";
	@Column(name="device_vender")
	private String deviceVender;
	@Column(name="device_series")
	private String deviceSeries;
	@Column(name="device_version")
	private String deviceVersion;
	@Column(name="browser_type")
	private String browserType;
	@Column(name="browser_version")
	private String browserVersion;
	@Column(name="platform_type")
	private String platformType;
	@Column(name="platform_series")
	private String platformSeries;
	@Column(name="platform_version")
	private String platformVersion;
	@Column(name="ip_address")
	private String ipAddress;
		
	//----------------------------------------------- default constructor --------------------------
    public BmUserLoginInfo() {
    	super();
    }
    
    //------------------------------------------------ Property accessors --------------------------
/**
	 * @return String
	 */
	public String getUserLoginInfoId() {
        return this.userLoginInfoId;
    }
    
	
    public BmUserLoginInfo(Map<String,String> userAgent) {
    	this.deviceVender = userAgent.get(UserAgentUtil.DEVICE_VENDER);
    	this.deviceSeries = userAgent.get(UserAgentUtil.DEVICE_SERIES);
    	this.deviceVersion = userAgent.get(UserAgentUtil.DEVICE_VERSION);
    	
    	this.browserType = userAgent.get(UserAgentUtil.BROWSER_TYPE);
    	this.browserVersion = userAgent.get(UserAgentUtil.BROWSER_VERSION);
    	this.platformType = userAgent.get(UserAgentUtil.PLATFORM_TYPE);
    	this.platformSeries = userAgent.get(UserAgentUtil.PLATFORM_SERIES);
    	this.platformVersion = userAgent.get(UserAgentUtil.PLATFORM_VERSION);
    }
	    
    /**
	 * @param String userLoginInfoId
	 */
    public void setUserLoginInfoId(String userLoginInfoId) {
		this.userLoginInfoId = userLoginInfoId;
    }
    /**
	 * @return String
	 */
	public String getLoginUserId() {
        return this.loginUserId;
    }
    
    /**
	 * @param String loginUserId
	 */
    public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
    }
    /**
	 * @return String
	 */
	public String getLoginUserName() {
        return this.loginUserName;
    }
    
    /**
	 * @param String loginUserName
	 */
    public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
    }
    /**
	 * @return Timestamp
	 */
	public Timestamp getLoginTime() {
        return this.loginTime;
    }
    
    /**
	 * @param Timestamp loginTime
	 */
    public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
    }
    /**
	 * @return Timestamp
	 */
	public Timestamp getLogoutTime() {
        return this.logoutTime;
    }
    
    /**
	 * @param Timestamp logoutTime
	 */
    public void setLogoutTime(Timestamp logoutTime) {
		this.logoutTime = logoutTime;
    }
    /**
	 * @return String
	 */
	public String getDeviceType() {
        return this.deviceType;
    }
    
    /**
	 * @param String deviceType
	 */
    public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
    }
    /**
	 * @return String
	 */
	public String getDeviceVender() {
        return this.deviceVender;
    }
    
    /**
	 * @param String deviceVender
	 */
    public void setDeviceVender(String deviceVender) {
		this.deviceVender = deviceVender;
    }
    /**
	 * @return String
	 */
	public String getDeviceSeries() {
        return this.deviceSeries;
    }
    
    /**
	 * @param String deviceSeries
	 */
    public void setDeviceSeries(String deviceSeries) {
		this.deviceSeries = deviceSeries;
    }
    /**
	 * @return String
	 */
	public String getDeviceVersion() {
        return this.deviceVersion;
    }
    
    /**
	 * @param String deviceVersion
	 */
    public void setDeviceVersion(String deviceVersion) {
		this.deviceVersion = deviceVersion;
    }
    /**
	 * @return String
	 */
	public String getBrowserType() {
        return this.browserType;
    }
    
    /**
	 * @param String browserType
	 */
    public void setBrowserType(String browserType) {
		this.browserType = browserType;
    }
    /**
	 * @return String
	 */
	public String getBrowserVersion() {
        return this.browserVersion;
    }
    
    /**
	 * @param String browserVersion
	 */
    public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
    }
    /**
	 * @return String
	 */
	public String getPlatformType() {
        return this.platformType;
    }
    
    /**
	 * @param String platformType
	 */
    public void setPlatformType(String platformType) {
		this.platformType = platformType;
    }
    /**
	 * @return String
	 */
	public String getPlatformSeries() {
        return this.platformSeries;
    }
    
    /**
	 * @param String platformSeries
	 */
    public void setPlatformSeries(String platformSeries) {
		this.platformSeries = platformSeries;
    }
    /**
	 * @return String
	 */
	public String getPlatformVersion() {
        return this.platformVersion;
    }
    
    /**
	 * @param String platformVersion
	 */
    public void setPlatformVersion(String platformVersion) {
		this.platformVersion = platformVersion;
    }
    /**
	 * @return String
	 */
	public String getIpAddress() {
        return this.ipAddress;
    }
    
    /**
	 * @param String ipAddress
	 */
    public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
    }
     
}