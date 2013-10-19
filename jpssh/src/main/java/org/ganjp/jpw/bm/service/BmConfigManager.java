/**
 * $Id: BmConfigManager.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.bm.service;

import java.util.List;
import java.util.Map;

import org.ganjp.jpw.bm.model.BmConfig;

/**
 * <p>BmConfigManager</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface BmConfigManager {
		//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new bmConfig</p>
	 * 
	 * @param T
	 */
	public void save(BmConfig bmConfig);
	/**
	 * <p>update new bmConfig</p>
	 * 
	 * @param T
	 */
	public void update(BmConfig bmConfig);
	/**
	 * <p>save or update new bmConfig</p>
	 * 
	 * @param T
	 */
	public void saveOrUpdate(BmConfig bmConfig);
	
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the bmConfig</p>
	 * 
	 * @param bmConfig the bmConfig must be from session or transient object that has primary key attribute
	 */
	public void delete(BmConfig bmConfig);
	/**
	 * <p>delete a bmConfig by primary key</p>
	 * 
	 * @param PK
	 */
	public void delete(final String pk);
	
	/**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
	public void batchDelete(final String pks);
	
	//-------------------------------------------   find   ------------------------------------------
	//------------return BmConfig
/**
	 * <p>get BmConfig object by primary key</p>
	 * 
	 * @param PK
	 * @return BmConfig
	 */
	public BmConfig getBmConfigById(final String id);
	
	/**
	 * <p>get all BmConfig objects</p>
	 *
	 * @return List<BmConfig>
	 */
	public List<BmConfig> getBmConfigs();
	
	/**
	 * <p>get all BmConfig records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<BmConfig>
	 */
	public List<BmConfig> getBmConfigsWithOrder(String orderBy, boolean isAsc);
	
	/**
	 * <p>get BmConfig objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<BmConfig>
	 */
	public List<BmConfig> getBmConfigsByField(final String fieldName, final Object value);
	
	/**
	 * <p>getBmConfig</p>
	 * 
	 * @param configCd
	 * @param lang
	 * @return
	 */
	public BmConfig getBmConfig(final String configCd, final String lang);
	
	/**
	 * <p>getValue</p>
	 * 
	 * @param configCd
	 * @return
	 */
	public String getValue(final String configCd);
	
	/**
	 * <p>get config key and value Map</p>
	 * 
	 * @return
	 */
	public Map<String, String> getConfigKeyAndValues();
	
	/**
	 * <p>getDescription</p>
	 * 
	 * @param configCd
	 * @return
	 */
	public String getDescription(final String configCd);
	
	/**
	 * <p>getBmConfigs</p>
	 * 
	 * @param lastTime
	 * @param configCds
	 * @return
	 */
	public List<BmConfig> getBmConfigs(long lastTime, final String configCds);
}