/**
 * $Id: PropertyFilter.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */

package org.ganjp.jpw.core.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.util.Assert;

import org.ganjp.jpw.core.util.DateUtil;
import org.ganjp.jpw.core.util.ReflectUtil;
import org.ganjp.jpw.core.util.StringUtil;

/**
 * <p></p> 
 * 
 * @author ganjianping
 */
public class PropertyFilter {

	public static final String OR_SEPARATOR = "_OR_";

	public enum MatchType {
		EQ, LIKE, LT, GT, LE, GE;
	}

	public enum PropertyType {
		S(String.class), I(Integer.class), L(Long.class), N(Double.class), D(Date.class), B(Boolean.class), 
		K(BigDecimal.class), T(Timestamp.class);

		private Class<?> clazz;

		PropertyType(Class<?> clazz) {
			this.clazz = clazz;
		}

		public Class<?> getValue() {
			return clazz;
		}
	}

	private String[] propertyNames = null;
	private Class<?> propertyType = null;
	private Object propertyValue = null;
	private MatchType matchType = null;

	public PropertyFilter() {
	}

	public PropertyFilter(final String filterName, final String value) {

		String matchTypeStr = StringUtil.substringBefore(filterName, "_");
		String matchTypeCode = StringUtil.substring(matchTypeStr, 0, matchTypeStr.length() - 1);
		String propertyTypeCode = StringUtil.substring(matchTypeStr, matchTypeStr.length() - 1, matchTypeStr.length());
		try {
			matchType = Enum.valueOf(MatchType.class, matchTypeCode);
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("filter名称" + filterName + "没有按规则编写,无法得到属性比较类型.", e);
		}

		try {
			propertyType = Enum.valueOf(PropertyType.class, propertyTypeCode).getValue();
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("filter名称" + filterName + "没有按规则编写,无法得到属性值类型.", e);
		}

		String propertyNameStr = StringUtil.substringAfter(filterName, "_");
		propertyNames = StringUtil.split(propertyNameStr, PropertyFilter.OR_SEPARATOR);

		Assert.isTrue(propertyNames.length > 0, "filter名称" + filterName + "没有按规则编写,无法得到属性名称.");
		
		if(propertyType.getSimpleName().equalsIgnoreCase("Date")) {
			this.propertyValue = DateUtil.parseDate(value, "dd/MM/yyyy");
		} else if(propertyType.getSimpleName().equalsIgnoreCase("TimeStamp")) {
			this.propertyValue = DateUtil.convertDateToTimestamp(DateUtil.parseDate(value, "dd/MM/yyyy hh:mm"));
		} else {
			this.propertyValue = ReflectUtil.convertStringToObject(value, propertyType);
		}
	}

	public boolean isMultiProperty() {
		return (propertyNames.length > 1);
	}

	public String[] getPropertyNames() {
		return propertyNames;
	}

	public String getPropertyName() {
		if (propertyNames.length > 1) {
			throw new IllegalArgumentException("There are not only one property");
		}
		return propertyNames[0];
	}

	public Object getPropertyValue() {
		return propertyValue;
	}

	public Class<?> getPropertyType() {
		return propertyType;
	}

	public MatchType getMatchType() {
		return matchType;
	}
}
