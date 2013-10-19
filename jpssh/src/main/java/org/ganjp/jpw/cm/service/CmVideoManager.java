/**
 * $Id: CmVideoManager.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.service;

import java.util.List;
import java.util.Map;

import org.ganjp.jpw.cm.model.CmVideo;

/**
 * <p>CmVideoManager</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface CmVideoManager {
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmVideo</p>
	 * 
	 * @param T
	 */
	public void save(CmVideo cmVideo);
	/**
	 * <p>update new cmVideo</p>
	 * 
	 * @param T
	 */
	public void update(CmVideo cmVideo);
	/**
	 * <p>save or update new cmVideo</p>
	 * 
	 * @param T
	 */
	public void saveOrUpdate(CmVideo cmVideo);
	
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmVideo</p>
	 * 
	 * @param cmVideo the cmVideo must be from session or transient object that has primary key attribute
	 */
	public void delete(CmVideo cmVideo);
	/**
	 * <p>delete a cmVideo by primary key</p>
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
	//------------return CmVideo
/**
	 * <p>get CmVideo object by primary key</p>
	 * 
	 * @param PK
	 * @return CmVideo
	 */
	public CmVideo getCmVideoById(final String id);
	
	/**
	 * <p>get all CmVideo objects</p>
	 *
	 * @return List<CmVideo>
	 */
	public List<CmVideo> getCmVideos();
	
	/**
	 * <p>get all CmVideo records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmVideo>
	 */
	public List<CmVideo> getCmVideosWithOrder(String orderBy, boolean isAsc);
	
	/**
	 * <p>get CmVideo objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmVideo>
	 */
	public List<CmVideo> getCmVideosByField(final String fieldName, final Object value);
	
	/**
	 * <p>getVideoMaps</p>
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
	public List<Map<String,String>> getVideoMaps(final String tag, final String lang, final String startDate, final String endDate, 
			final int pageNo, final int pageSize, final String ownRoleIds);
	
	/**
	 * <p>getVideoMaps</p>
	 * 
	 * @param tag
	 * @param lang
	 * @param ownRoleIds
	 * @return
	 */
	public List<Map<String,String>> getVideoMaps(final String tag, final String lang, final String ownRoleIds);
}