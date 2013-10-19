/**
 * $Id: User.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */

package org.ganjp.jpw.tm.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.ganjp.jpw.core.dao.uuid.UUIDHexGenerator;
import org.ganjp.jpw.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * <p>User for test</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Entity
@Table(name="tm_type")
@XStreamAlias("message")
public class TmType extends BaseModel{
	@Id
	@Column(name="type_id")
	@XStreamAlias("id")
	@XStreamAsAttribute
	private String typeId = UUIDHexGenerator.getUuid();
	
	@Column(name="str_type")
	@XStreamAlias("str")
	private String strType;
	
	@Column(name="int_type")
	@XStreamAlias("int")
	private Integer intType;
	
	@Column(name="number_type")
	@XStreamAlias("number")
	private BigDecimal numberType;

	@Column(name="date_type")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateType;
	
	@Column(name="time_type")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date timeType;
	
	@Column(name="email")
	@XStreamAlias("email")
	private String email;
	
	@Column(name="phone")
	@XStreamAlias("phone")
	private String phone;
	
	//----------------------------------------------- default constructor --------------------------
    public TmType() {
    	super();
    }

   //------------------------------------------------ Property accessors --------------------------
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getStrType() {
		return strType;
	}

	public void setStrType(String strType) {
		this.strType = strType;
	}

	public Integer getIntType() {
		return intType;
	}

	public void setIntType(Integer intType) {
		this.intType = intType;
	}

	public BigDecimal getNumberType() {
		return numberType;
	}

	public void setNumberType(BigDecimal numberType) {
		this.numberType = numberType;
	}

	public Date getDateType() {
		return dateType;
	}

	public void setDateType(Date dateType) {
		this.dateType = dateType;
	}

	public Date getTimeType() {
		return timeType;
	}

	public void setTimeType(Date timeType) {
		this.timeType = timeType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
    
}