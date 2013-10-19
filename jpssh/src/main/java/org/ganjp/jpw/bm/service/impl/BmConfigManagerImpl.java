/**
 * $Id: BmConfigManagerImpl.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Coss Project
 *
 */
package org.ganjp.jpw.bm.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.ganjp.jpw.bm.model.BmConfig;
import org.ganjp.jpw.bm.service.BmConfigManager;
import org.ganjp.jpw.core.dao.BaseDao;
import org.ganjp.jpw.core.service.BaseManager;
import org.ganjp.jpw.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>BmConfigManagerImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service 
public class BmConfigManagerImpl extends BaseManager implements BmConfigManager {
	
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new bmConfig</p>
	 * 
	 * @param BmConfig
	 */
	@Transactional
	public void save(BmConfig bmConfig) {
		dao.save(bmConfig);
	}

	/**
	 * <p>update new bmConfig</p>
	 * 
	 * @param BmConfig
	 */
	@Transactional
	public void update(BmConfig bmConfig) {
		dao.update(bmConfig);
	}
	/**
	 * <p>save or update new bmConfig</p>
	 * 
	 * @param BmConfig
	 */
	@Transactional
	public void saveOrUpdate(BmConfig bmConfig) {
		dao.saveOrUpdate(bmConfig);
	}
		
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the bmConfig</p>
	 * 
	 * @param bmConfig the bmConfig must be from session or transient object that has primary key attribute
	 */
	@Transactional
	public void delete(BmConfig bmConfig) {
		dao.delete(bmConfig);
	}
	/**
	 * <p>delete a bmConfig by primary key</p>
	 * 
	 * @param PK
	 */
	@Transactional
	public void delete(final String pk) {
		dao.delete(BmConfig.class, pk);
	}
	
	/**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
	@Transactional
	public void batchDelete(final String pks) {
		String hql = "delete from BmConfig where configId in (" + StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
	}
	
	//-------------------------------------------   find   ------------------------------------------
	//------------return BmConfig
	/**
	 * <p>get BmConfig object by primary key</p>
	 * 
	 * @param PK
	 * @return BmConfig
	 */
	@Transactional
	public BmConfig getBmConfigById(final String id) {
		BmConfig bmConfig = dao.findById(BmConfig.class, id);
		return bmConfig;
	}
	
	/**
	 * <p>get all BmConfig objects</p>
	 *
	 * @return List<BmConfig>
	 */
	@Transactional
	public List<BmConfig> getBmConfigs() {
		return dao.findAllWithOrder(BmConfig.class, "modifyTimestamp", false);
	}
	
	/**
	 * <p>get all BmConfig objects and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<BmConfig>
	 */
	@Transactional
	public List<BmConfig> getBmConfigsWithOrder(String orderBy, boolean isAsc)  {
		return dao.findAllWithOrder(BmConfig.class, orderBy, isAsc);
	}
	
	/**
	 * <p>get BmConfig objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<BmConfig>
	 */
	@Transactional
	public List<BmConfig> getBmConfigsByField(final String fieldName, final Object value) {
		return dao.findByField(BmConfig.class, fieldName, value, "modifyTimestamp", false);
	}

	/**
	 * <p>getBmConfig</p>
	 * 
	 * @param configCd
	 * @param lang
	 * @return
	 */
	@Transactional
	public BmConfig getBmConfig(final String configCd, final String lang) {
		String hql = "select a.configName, a.configValue, a.description from BmConfig a where a.configCd = ? and a.lang = ? ";
		List<Object[]> objectArrs = dao.findByHql(hql, configCd, lang);
		if (objectArrs==null || objectArrs.isEmpty()) {
			return null;
		} else {
			Object[] objArr = objectArrs.get(0);
			BmConfig bmConfig = new BmConfig();
			bmConfig.setConfigName(StringUtil.toString(objArr[0]));
			bmConfig.setConfigValue(StringUtil.toString(objArr[1]));
			bmConfig.setDescription(StringUtil.toString(objArr[2]));
			return bmConfig;
		}
	}

	/**
	 * <p>getValue</p>
	 * 
	 * @param configCd
	 * @return
	 */
	@Transactional
	public String getValue(final String configCd) {
		String hql = "select a.configValue from BmConfig a where a.configCd = ?";
		List<Object> objectArrs = dao.findByHql(hql, configCd);
		if (objectArrs==null || objectArrs.isEmpty()) {
			return null;
		} else {
			Object value = objectArrs.get(0);
			return StringUtil.toString(value);
		}
	}
	
	/**
	 * <p>get config key and value Map</p>
	 * 
	 * @return
	 */
	@Transactional
	public Map<String, String> getConfigKeyAndValues() {
		List<BmConfig> bmConfigs = getBmConfigs();
		Map<String,String> configKeyAndValues = new HashMap<String,String>();
		for (BmConfig bmConfig : bmConfigs) {
			configKeyAndValues.put(bmConfig.getConfigCd(), bmConfig.getConfigValue());
		}
		return configKeyAndValues;
	}
	
	/**
	 * <p>getDescription</p>
	 * 
	 * @param configCd
	 * @return
	 */
	@Transactional
	public String getDescription(final String configCd) {
		String hql = "select a.description from BmConfig a where a.configCd = ?";
		List<Object> objectArrs = dao.findByHql(hql, configCd);
		if (objectArrs==null || objectArrs.isEmpty()) {
			return null;
		} else {
			Object value = objectArrs.get(0);
			return StringUtil.toString(value);
		}
	}
	
	@Transactional
	public List<BmConfig> getBmConfigs(long lastTime, final String configCds) {
		String hql = "select configId, configCd, configName, configValue, description, lang, createDateTime, modifyTimestamp, dataState " +
				" from BmConfig a where configCd in " + configCds;
		List<Object[]> objectArrs = null;
		if (lastTime!=0) {
			hql += " and modifyTimestamp > ?";
			objectArrs = dao.findByHql(hql, new Timestamp(lastTime));
		} else {
			objectArrs = dao.findByHql(hql);
		}
		
		List<BmConfig> bmConfigs = new ArrayList<BmConfig>(); 
		
		for (Object[] objArr : objectArrs) {
			BmConfig bmConfig = new BmConfig();
			bmConfig.setConfigId(StringUtil.toString(objArr[0]));
			bmConfig.setConfigCd(StringUtil.toString(objArr[1]));
			bmConfig.setConfigName(StringUtil.toString(objArr[2]));
			bmConfig.setConfigValue(StringUtil.toString(objArr[3]));
			bmConfig.setDescription(StringUtil.toString(objArr[4]));
			bmConfig.setLang(StringUtil.toString(objArr[5]));
			bmConfig.setCreateDateTime((Timestamp)objArr[6]);
			bmConfig.setModifyTimestamp((Timestamp)objArr[7]);
			bmConfig.setDataState(StringUtil.toString(objArr[8]));
			bmConfigs.add(bmConfig);
		}	
		return bmConfigs;
	}
	
	@Resource(name="baseDaoHibernate")
	private BaseDao<BmConfig, String> dao;
}