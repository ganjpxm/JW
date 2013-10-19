/**
 * $Id: CmFileManager.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.service;

import java.util.List;
import java.util.Map;

import org.ganjp.jpw.cm.model.CmFile;

/**
 * <p>CmFileManager</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface CmFileManager {
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmFile</p>
	 * 
	 * @param T
	 */
	public void save(CmFile cmFile);
	/**
	 * <p>update new cmFile</p>
	 * 
	 * @param T
	 */
	public void update(CmFile cmFile);
	/**
	 * <p>save or update new cmFile</p>
	 * 
	 * @param T
	 */
	public void saveOrUpdate(CmFile cmFile);
	
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmFile</p>
	 * 
	 * @param cmFile the cmFile must be from session or transient object that has primary key attribute
	 */
	public void delete(CmFile cmFile);
	/**
	 * <p>delete a cmFile by primary key</p>
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
	//------------return CmFile
/**
	 * <p>get CmFile object by primary key</p>
	 * 
	 * @param PK
	 * @return CmFile
	 */
	public CmFile getCmFileById(final String id);
	
	/**
	 * <p>get all CmFile objects</p>
	 *
	 * @return List<CmFile>
	 */
	public List<CmFile> getCmFiles();
	
	/**
	 * <p>get all CmFile records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmFile>
	 */
	public List<CmFile> getCmFilesWithOrder(String orderBy, boolean isAsc);
	
	/**
	 * <p>get CmFile objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmFile>
	 */
	public List<CmFile> getCmFilesByField(final String fieldName, final Object value);
	
	/**
	 * <p>getFileMaps</p>
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
	public List<Map<String,String>> getFileMaps(final String tag, final String lang, final String startDate, final String endDate, 
			final int pageNo, final int pageSize, final String ownRoleIds);
	
	/**
	 * <p>getFileMaps</p>
	 * 
	 * @param tag
	 * @param lang
	 * @param ownRoleIds
	 * @return
	 */
	public List<Map<String,String>> getFileMaps(final String tag, final String lang, final String ownRoleIds);
}