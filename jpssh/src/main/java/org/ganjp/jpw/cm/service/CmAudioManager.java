/**
 * $Id: CmAudioManager.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.service;

import java.util.List;
import java.util.Map;

import org.ganjp.jpw.cm.model.CmAudio;

/**
 * <p>CmAudioManager</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface CmAudioManager {
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmAudio</p>
	 * 
	 * @param T
	 */
	public void save(CmAudio cmAudio);
	/**
	 * <p>update new cmAudio</p>
	 * 
	 * @param T
	 */
	public void update(CmAudio cmAudio);
	/**
	 * <p>save or update new cmAudio</p>
	 * 
	 * @param T
	 */
	public void saveOrUpdate(CmAudio cmAudio);
	
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmAudio</p>
	 * 
	 * @param cmAudio the cmAudio must be from session or transient object that has primary key attribute
	 */
	public void delete(CmAudio cmAudio);
	/**
	 * <p>delete a cmAudio by primary key</p>
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
	//------------return CmAudio
/**
	 * <p>get CmAudio object by primary key</p>
	 * 
	 * @param PK
	 * @return CmAudio
	 */
	public CmAudio getCmAudioById(final String id);
	
	/**
	 * <p>get all CmAudio objects</p>
	 *
	 * @return List<CmAudio>
	 */
	public List<CmAudio> getCmAudios();
	
	/**
	 * <p>get all CmAudio records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmAudio>
	 */
	public List<CmAudio> getCmAudiosWithOrder(String orderBy, boolean isAsc);
	
	/**
	 * <p>get CmAudio objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmAudio>
	 */
	public List<CmAudio> getCmAudiosByField(final String fieldName, final Object value);
	
	/**
	 * <p>getAudioMaps</p>
	 * 
	 * @param tag
	 * @param lang
	 * @param startDate
	 * @param endDate
	 * @param pageNo
	 * @param pageSize
	 * @param ownRoleIds
	 * @return
	 */
	public List<Map<String,String>> getAudioMaps(final String tag, final String lang, final String startDate, final String endDate, 
			final int pageNo, final int pageSize, final String ownRoleIds);
	
	/**
	 * <p>getAudioMaps</p>
	 * 
	 * @param tag
	 * @param lang
	 * @param ownRoleIds
	 * @return
	 */
	public List<Map<String,String>> getAudioMaps(final String tag, final String lang, final String ownRoleIds);
}