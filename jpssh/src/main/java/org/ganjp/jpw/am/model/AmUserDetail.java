/**
 * $Id: AmUserDetail.java,v 1.0 2012/08/19 00:16:55 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.am.model;

import org.ganjp.jpw.core.Const;
import org.ganjp.jpw.core.model.BaseModel;
import org.ganjp.jpw.core.util.DateUtil;
import org.ganjp.jpw.core.dao.uuid.UUIDHexGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * <p>AmUserDetail</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Entity
@Table(name="am_user_detail")
public class AmUserDetail extends BaseModel{
	@Id
	@Column(name="user_detail_id")
	private String userDetailId = UUIDHexGenerator.getUuid();
	
	@Column(name="user_id")
	private String userId;
	@Column(name="home_phone")
	private String homePhone;
	@Column(name="country_id")
	private String countryId;
	@Column(name="province_id")
	private String provinceId;
	@Column(name="city_id")
	private String cityId;
	@Column(name="birth_place")
	private String birthPlace;
	@Column(name="live_place")
	private String livePlace;
	@Column(name="annual_income")
	private BigDecimal annualIncome;
	@Column(name="job_category_pid")
	private String jobCategoryPid;
	@Column(name="job_category_id")
	private String jobCategoryId;
	@Column(name="job_position_id")
	private String jobPositionId;
	@Column(name="company_name")
	private String companyName;
	@Column(name="shool_name")
	private String shoolName;
	@Column(name="education_id")
	private String educationId;
	@Column(name="interest_ids")
	private String interestIds;
	@Column(name="password_tip_custom")
	private String passwordTipCustom;
	@Column(name="password_tip_id")
	private String passwordTipId;
	@Column(name="password_tip_answer")
	private String passwordTipAnswer;
	@Column(name="signature")
	private String signature;
	@Column(name="remark")
	private String remark;
	@Column(name="create_date_time")
	private Timestamp createDateTime = DateUtil.getNowTimstamp();
	@Column(name="modify_timestamp")
	private Timestamp modifyTimestamp = DateUtil.getNowTimstamp();
	@Column(name="data_state")
	private String dataState = Const.DB_DATASTATE_NORMAL;
		
	//----------------------------------------------- default constructor --------------------------
    public AmUserDetail() {
    	super();
    }
    
    //------------------------------------------------ Property accessors --------------------------
/**
	 * @return String
	 */
	public String getUserDetailId() {
        return this.userDetailId;
    }
    
    /**
	 * @param String userDetailId
	 */
    public void setUserDetailId(String userDetailId) {
		this.userDetailId = userDetailId;
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
	public String getHomePhone() {
        return this.homePhone;
    }
    
    /**
	 * @param String homePhone
	 */
    public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
    }
    /**
	 * @return String
	 */
	public String getCountryId() {
        return this.countryId;
    }
    
    /**
	 * @param String countryId
	 */
    public void setCountryId(String countryId) {
		this.countryId = countryId;
    }
    /**
	 * @return String
	 */
	public String getProvinceId() {
        return this.provinceId;
    }
    
    /**
	 * @param String provinceId
	 */
    public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
    }
    /**
	 * @return String
	 */
	public String getCityId() {
        return this.cityId;
    }
    
    /**
	 * @param String cityId
	 */
    public void setCityId(String cityId) {
		this.cityId = cityId;
    }
    /**
	 * @return String
	 */
	public String getBirthPlace() {
        return this.birthPlace;
    }
    
    /**
	 * @param String birthPlace
	 */
    public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
    }
    /**
	 * @return String
	 */
	public String getLivePlace() {
        return this.livePlace;
    }
    
    /**
	 * @param String livePlace
	 */
    public void setLivePlace(String livePlace) {
		this.livePlace = livePlace;
    }
    /**
	 * @return BigDecimal
	 */
	public BigDecimal getAnnualIncome() {
        return this.annualIncome;
    }
    
    /**
	 * @param BigDecimal annualIncome
	 */
    public void setAnnualIncome(BigDecimal annualIncome) {
		this.annualIncome = annualIncome;
    }
    /**
	 * @return String
	 */
	public String getJobCategoryPid() {
        return this.jobCategoryPid;
    }
    
    /**
	 * @param String jobCategoryPid
	 */
    public void setJobCategoryPid(String jobCategoryPid) {
		this.jobCategoryPid = jobCategoryPid;
    }
    /**
	 * @return String
	 */
	public String getJobCategoryId() {
        return this.jobCategoryId;
    }
    
    /**
	 * @param String jobCategoryId
	 */
    public void setJobCategoryId(String jobCategoryId) {
		this.jobCategoryId = jobCategoryId;
    }
    /**
	 * @return String
	 */
	public String getJobPositionId() {
        return this.jobPositionId;
    }
    
    /**
	 * @param String jobPositionId
	 */
    public void setJobPositionId(String jobPositionId) {
		this.jobPositionId = jobPositionId;
    }
    /**
	 * @return String
	 */
	public String getCompanyName() {
        return this.companyName;
    }
    
    /**
	 * @param String companyName
	 */
    public void setCompanyName(String companyName) {
		this.companyName = companyName;
    }
    /**
	 * @return String
	 */
	public String getShoolName() {
        return this.shoolName;
    }
    
    /**
	 * @param String shoolName
	 */
    public void setShoolName(String shoolName) {
		this.shoolName = shoolName;
    }
    /**
	 * @return String
	 */
	public String getEducationId() {
        return this.educationId;
    }
    
    /**
	 * @param String educationId
	 */
    public void setEducationId(String educationId) {
		this.educationId = educationId;
    }
    /**
	 * @return String
	 */
	public String getInterestIds() {
        return this.interestIds;
    }
    
    /**
	 * @param String interestIds
	 */
    public void setInterestIds(String interestIds) {
		this.interestIds = interestIds;
    }
    /**
	 * @return String
	 */
	public String getPasswordTipCustom() {
        return this.passwordTipCustom;
    }
    
    /**
	 * @param String passwordTipCustom
	 */
    public void setPasswordTipCustom(String passwordTipCustom) {
		this.passwordTipCustom = passwordTipCustom;
    }
    /**
	 * @return String
	 */
	public String getPasswordTipId() {
        return this.passwordTipId;
    }
    
    /**
	 * @param String passwordTipId
	 */
    public void setPasswordTipId(String passwordTipId) {
		this.passwordTipId = passwordTipId;
    }
    /**
	 * @return String
	 */
	public String getPasswordTipAnswer() {
        return this.passwordTipAnswer;
    }
    
    /**
	 * @param String passwordTipAnswer
	 */
    public void setPasswordTipAnswer(String passwordTipAnswer) {
		this.passwordTipAnswer = passwordTipAnswer;
    }
    /**
	 * @return String
	 */
	public String getSignature() {
        return this.signature;
    }
    
    /**
	 * @param String signature
	 */
    public void setSignature(String signature) {
		this.signature = signature;
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