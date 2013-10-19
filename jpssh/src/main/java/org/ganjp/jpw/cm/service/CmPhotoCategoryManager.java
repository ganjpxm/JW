/**
 * $Id: CmPhotoCategoryManager.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.service;

import java.util.List;
import java.util.Map;

import org.ganjp.jpw.cm.model.CmPhoto;
import org.ganjp.jpw.cm.model.CmPhotoCategory;

/**
 * <p>CmPhotoCategoryManager</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface CmPhotoCategoryManager {
		//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmPhotoCategory</p>
	 * 
	 * @param T
	 */
	public void save(CmPhotoCategory cmPhotoCategory);
	/**
	 * <p>update new cmPhotoCategory</p>
	 * 
	 * @param T
	 */
	public void update(CmPhotoCategory cmPhotoCategory);
	/**
	 * <p>save or update new cmPhotoCategory</p>
	 * 
	 * @param T
	 */
	public void saveOrUpdate(CmPhotoCategory cmPhotoCategory);
	
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmPhotoCategory</p>
	 * 
	 * @param cmPhotoCategory the cmPhotoCategory must be from session or transient object that has primary key attribute
	 */
	public void delete(CmPhotoCategory cmPhotoCategory);
	/**
	 * <p>delete a cmPhotoCategory by primary key</p>
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
	//------------return CmPhotoCategory
/**
	 * <p>get CmPhotoCategory object by primary key</p>
	 * 
	 * @param PK
	 * @return CmPhotoCategory
	 */
	public CmPhotoCategory getCmPhotoCategoryById(final String id);
	
	/**
	 * <p>get all CmPhotoCategory objects</p>
	 *
	 * @return List<CmPhotoCategory>
	 */
	public List<CmPhotoCategory> getCmPhotoCategorys();
	
	/**
	 * <p>get all CmPhotoCategory records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmPhotoCategory>
	 */
	public List<CmPhotoCategory> getCmPhotoCategorysWithOrder(String orderBy, boolean isAsc);
	
	/**
	 * <p>get CmPhotoCategory objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmPhotoCategory>
	 */
	public List<CmPhotoCategory> getCmPhotoCategorysByField(final String fieldName, final Object value);
	
	/**
	 * <p>getPhotoMaps</p>
	 * 
	 * @param categoryId
	 * @param ownRoleIds
	 * @return
	 */
	public List<Map<String,String>> getPhotoMaps(String categoryId, String ownRoleIds);

	/**
	 * <p>saveCmPhotoAndCategory</p>
	 * 
	 * @param cmPhoto
	 * @param categoryIds
	 */
	public void saveCmPhotoAndCategory(CmPhoto cmPhoto, String categoryIds);
	
	/**
	 * <p>deleteByPhotoId</p>
	 * 
	 * @param photoId
	 */
	public void deleteByPhotoId(String photoId);
	
	/**
	 * <p>deleteCmPhotoAndCmPhotoCategory</p>
	 * 
	 * @param photoId
	 */
	public void deleteCmPhotoAndCmPhotoCategory(String photoId);
	
	/**
	 * <p>getPhotoMaps</p>
	 * 
	 * @param categoryCd
	 * @param lang
	 * @param tag
	 * @param ownRoleIds
	 * @return
	 */
	public List<Map<String,String>> getPhotoMaps(String categoryCd, String lang, String tag, String ownRoleIds);
	
	/**
	 * <p>getCategoryIds</p>
	 * 
	 * @param photoId
	 * @return
	 */
	public String getCategoryIds(final String photoId);
	
	/**
	 * <p>updateCmPhotoAndCategory</p>
	 * 
	 * @param cmPhoto
	 * @param categoryIds
	 */
	public void updateCmPhotoAndCategory(CmPhoto cmPhoto, String categoryIds);
	
}