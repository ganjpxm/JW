/**
 * $Id: CmPhotoManager.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.service;

import java.util.List;
import java.util.Map;

import org.ganjp.jpw.cm.model.CmPhoto;
import org.ganjp.jpw.core.model.Page;

/**
 * <p>CmPhotoManager</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface CmPhotoManager {
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmPhoto</p>
	 * 
	 * @param T
	 */
	public void save(CmPhoto cmPhoto);
	/**
	 * <p>update new cmPhoto</p>
	 * 
	 * @param T
	 */
	public void update(CmPhoto cmPhoto);
	/**
	 * <p>save or update new cmPhoto</p>
	 * 
	 * @param T
	 */
	public void saveOrUpdate(CmPhoto cmPhoto);
	
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmPhoto</p>
	 * 
	 * @param cmPhoto the cmPhoto must be from session or transient object that has primary key attribute
	 */
	public void delete(CmPhoto cmPhoto);
	/**
	 * <p>delete a cmPhoto by primary key</p>
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
	//------------return CmPhoto
/**
	 * <p>get CmPhoto object by primary key</p>
	 * 
	 * @param PK
	 * @return CmPhoto
	 */
	public CmPhoto getCmPhotoById(final String id);
	
	/**
	 * <p>get all CmPhoto objects</p>
	 *
	 * @return List<CmPhoto>
	 */
	public List<CmPhoto> getCmPhotos();
	
	/**
	 * <p>get all CmPhoto records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmPhoto>
	 */
	public List<CmPhoto> getCmPhotosWithOrder(String orderBy, boolean isAsc);
	
	/**
	 * <p>get CmPhoto objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmPhoto>
	 */
	public List<CmPhoto> getCmPhotosByField(final String fieldName, final Object value);
	
	/**
	 * <p>getPhotoMaps</p>
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
	public List<Map<String,String>> getPhotoMaps(final String tag, final String lang, final String startDate, final String endDate, 
			final int pageNo, final int pageSize, final String ownRoleIds);
	
	/**
	 * <p>getPhotoMaps</p>
	 * 
	 * @param tag
	 * @param lang
	 * @param ownRoleIds
	 * @return
	 */
	public List<Map<String,String>> getPhotoMaps(final String tag, final String lang, final String ownRoleIds);
	
	/**
	 * <p>get CmPhotos by articleId</p>
	 * 
	 * @param articleId
	 * @return
	 */
	public List<CmPhoto> getCmPhotosByArticleId(final String articleId);
	
	/**
	 * <p>Get CmPhoto Page</p>
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
	public Page<CmPhoto> getCmPhotoPage(final String tag, final String lang, final String startDate, final String endDate, final int pageNo,
			final int pageSize, String ownRoleIds);
}