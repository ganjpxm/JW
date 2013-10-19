/**
 * $Id: BmParamManager.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.bm.service;

import java.util.List;
import java.util.Map;

import org.ganjp.jpw.bm.model.BmParam;

/**
 * <p>BmParamManager</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface BmParamManager {
		//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new bmParam</p>
	 * 
	 * @param T
	 */
	public void save(BmParam bmParam);
	/**
	 * <p>update new bmParam</p>
	 * 
	 * @param T
	 */
	public void update(BmParam bmParam);
	/**
	 * <p>save or update new bmParam</p>
	 * 
	 * @param T
	 */
	public void saveOrUpdate(BmParam bmParam);
	
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the bmParam</p>
	 * 
	 * @param bmParam the bmParam must be from session or transient object that has primary key attribute
	 */
	public void delete(BmParam bmParam);
	/**
	 * <p>delete a bmParam by primary key</p>
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
	//------------return BmParam
/**
	 * <p>get BmParam object by primary key</p>
	 * 
	 * @param PK
	 * @return BmParam
	 */
	public BmParam getBmParamById(final String id);
	
	/**
	 * <p>get all BmParam objects</p>
	 *
	 * @return List<BmParam>
	 */
	public List<BmParam> getBmParams();
	
	/**
	 * <p>get all BmParam records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<BmParam>
	 */
	public List<BmParam> getBmParamsWithOrder(String orderBy, boolean isAsc);
	
	/**
	 * <p>get BmParam objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<BmParam>
	 */
	public List<BmParam> getBmParamsByField(final String fieldName, final Object value);
	
	/**
	 * <p>get Map<paramTypeCdName, ListBmParam>></p>
	 * 
	 * @return Map<String,List<BmParam>>
	 */
	public Map<String,List<BmParam>> getParamTypeAndBmParamss(String lang);
	
	/**
	 * <p>getParamTypeCdLangAndParamMaps</p>
	 * 
	 * @return
	 */
	public Map<String,List<Map<String,String>>> getParamTypeCdLangAndParamMaps();
	
	/**
	 * <p>getBmParams</p>
	 * 
	 * @param paramTypeCd
	 * @param lang
	 * @return
	 */
	public List<BmParam> getBmParams(String paramTypeCd, String lang);
	
	/**
	 * <p>get all paramId and paramName Map</p>
	 * 
	 * @return Map<paramId,paramCd>
	 */
	public Map<String,String> getParamIdAndCds();
	
	/**
	 * <p>getParamIdAndCds</p>
	 * 
	 * @param paramType
	 * @return
	 */
	public Map<String,String> getParamIdAndCds(String paramType);
}