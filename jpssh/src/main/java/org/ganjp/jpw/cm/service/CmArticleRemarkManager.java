/**
 * $Id: CmArticleRemarkManager.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.service;

import java.util.List;
import java.util.Map;

import org.ganjp.jpw.cm.model.CmArticleRemark;

/**
 * <p>CmArticleRemarkManager</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface CmArticleRemarkManager {
		//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmArticleRemark</p>
	 * 
	 * @param T
	 */
	public void save(CmArticleRemark cmArticleRemark);
	/**
	 * <p>update new cmArticleRemark</p>
	 * 
	 * @param T
	 */
	public void update(CmArticleRemark cmArticleRemark);
	/**
	 * <p>save or update new cmArticleRemark</p>
	 * 
	 * @param T
	 */
	public void saveOrUpdate(CmArticleRemark cmArticleRemark);
	
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmArticleRemark</p>
	 * 
	 * @param cmArticleRemark the cmArticleRemark must be from session or transient object that has primary key attribute
	 */
	public void delete(CmArticleRemark cmArticleRemark);
	/**
	 * <p>delete a cmArticleRemark by primary key</p>
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
	//------------return CmArticleRemark
/**
	 * <p>get CmArticleRemark object by primary key</p>
	 * 
	 * @param PK
	 * @return CmArticleRemark
	 */
	public CmArticleRemark getCmArticleRemarkById(final String id);
	
	/**
	 * <p>get all CmArticleRemark objects</p>
	 *
	 * @return List<CmArticleRemark>
	 */
	public List<CmArticleRemark> getCmArticleRemarks();
	
	/**
	 * <p>get all CmArticleRemark records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmArticleRemark>
	 */
	public List<CmArticleRemark> getCmArticleRemarksWithOrder(String orderBy, boolean isAsc);
	
	/**
	 * <p>get CmArticleRemark objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmArticleRemark>
	 */
	public List<CmArticleRemark> getCmArticleRemarksByField(final String fieldName, final Object value);
	
	/**
	 * <p>getArticleIdAndOperatorIdTimeRemarks</p>
	 * 
	 * @param articleIds
	 * @return
	 */
	public Map<String,List<String>> getArticleIdAndOperatorIdTimeRemarks(final String articleIds);
}