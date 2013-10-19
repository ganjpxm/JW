/**
 * $Id: BaseModel.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.core.model;

import java.lang.reflect.Field;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Transient;

import org.ganjp.jpw.core.Const;
import org.ganjp.jpw.core.util.DateUtil;
import org.ganjp.jpw.core.util.JsonUtil;
import org.ganjp.jpw.core.util.ReflectUtil;
import org.ganjp.jpw.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>all model must extend BaseModel</p> 
 *
 * @author GanJianping
 * @since 1.0
 */
public abstract class BaseModel {

	protected static Logger log = LoggerFactory.getLogger(BaseModel.class);

	@Transient
	private String queryFilters = null;

	public String getQueryFilters() {
		return queryFilters;
	}
	public void setQueryFilters(String queryFilters) {
		this.queryFilters = queryFilters;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	/**
	 * <p>return all field's json data</p>
	 * 
	 * @return String
	 */
	public String toJsonStr() {
		return this.toJsonStr(null);
	}
	
	/**
	 * <p>return all field's json stringbuffer data</p>
	 * 
	 * @return String
	 */
	public StringBuffer toJsonStrBuffer() {
		return this.toJsonStrBuffer(null);
	}
	
	/**
	 * <p>return colluns's json data</p>
	 * 
	 * @return String
	 */
	public String toJsonStr(String columns) {
		return this.toJsonStrBuffer(columns).toString();
	}
	
	/**
	 * <p>return colluns's json StringBuffer data</p>
	 * 
	 * @return String
	 */
	public StringBuffer toJsonStrBuffer(String columns) {
		Class<? extends BaseModel> clas = this.getClass();
		Field[] fieldArr = clas.getDeclaredFields();
		StringBuffer jsonStrBuffer = new StringBuffer(JsonUtil.EMPTY_JSON_OBJECT_STR);
		try {
			for (int i = 0; i < fieldArr.length; i++) {
				Field field = fieldArr[i];
				String key = field.getName();
				if (StringUtil.hasText(columns)) {
					String replaceKey = StringUtil.getAfterColonValue(columns, field.getName());
					if (!StringUtil.hasText(replaceKey)) {
						continue;
					} else {
						key = replaceKey;
					}
				}
				field.setAccessible(true);
				Object value;
				try{
					value = ReflectUtil.getPropertityValue(this, field.getName());
				} catch (Exception e) {
					value = field.get(null);
					log.error("get "+ field.getName() + " mapping value!");
				}
				JsonUtil.addJsonObjectItem(jsonStrBuffer, "\"" + key + "\":" + JsonUtil.getPrimitiveValue(value));
			}
		} catch (Exception e) {
			log.error("get class " + clas.getName() + "'s json value error：" + e.getMessage());
		}
		return jsonStrBuffer;
	}
	
	/**
	 * <p>hashCode algorithm</p>
	 */
	public int hashCode() {
		int result = 17;
		Class<? extends BaseModel> c = this.getClass();
		Field[] fields = c.getDeclaredFields();
		try {
			for (int i = 0; i < fields.length; i++) {
				Field f = fields[i];
				f.setAccessible(true);
				result = 37 * result + (f.get(this) == null ? 0 : f.get(this).hashCode());
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}

	/**
	 * <p>to String</p> 
	 */
	public String toString() {
		Class<? extends BaseModel> c = this.getClass();
		Field[] fields = c.getDeclaredFields();
		StringBuffer buffer = new StringBuffer();
		buffer.append(c.getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		try {
			for (int i = 0; i < fields.length; i++) {
				Field f = fields[i];
				f.setAccessible(true);
				buffer.append(f.getName()).append("='").append(f.get(this)).append("' ");
			}
			buffer.append("]");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return buffer.toString();
	}
	
}
