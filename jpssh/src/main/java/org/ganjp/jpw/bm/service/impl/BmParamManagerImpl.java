/**
 * $Id: BmParamManagerImpl.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Coss Project
 *
 */
package org.ganjp.jpw.bm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.ganjp.jpw.bm.model.BmParam;
import org.ganjp.jpw.bm.service.BmParamManager;
import org.ganjp.jpw.core.Config;
import org.ganjp.jpw.core.Const;
import org.ganjp.jpw.core.dao.BaseDao;
import org.ganjp.jpw.core.model.PropertyFilter;
import org.ganjp.jpw.core.service.BaseManager;
import org.ganjp.jpw.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>BmParamManagerImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service 
public class BmParamManagerImpl extends BaseManager implements BmParamManager {
	
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new bmParam</p>
	 * 
	 * @param BmParam
	 */
	@Transactional
	public void save(BmParam bmParam) {
		dao.save(bmParam);
	}

	/**
	 * <p>update new bmParam</p>
	 * 
	 * @param BmParam
	 */
	@Transactional
	public void update(BmParam bmParam) {
		dao.update(bmParam);
	}
	/**
	 * <p>save or update new bmParam</p>
	 * 
	 * @param BmParam
	 */
	@Transactional
	public void saveOrUpdate(BmParam bmParam) {
		dao.saveOrUpdate(bmParam);
	}
		
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the bmParam</p>
	 * 
	 * @param bmParam the bmParam must be from session or transient object that has primary key attribute
	 */
	@Transactional
	public void delete(BmParam bmParam) {
		dao.delete(bmParam);
	}
	/**
	 * <p>delete a bmParam by primary key</p>
	 * 
	 * @param PK
	 */
	@Transactional
	public void delete(final String pk) {
		dao.delete(BmParam.class, pk);
	}
	/**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
	@Transactional
	public void batchDelete(final String pks) {
		String hql = "delete from BmParam where paramId in (" + StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
	}	
	//-------------------------------------------   find   ------------------------------------------
	//------------return BmParam
	/**
	 * <p>get BmParam object by primary key</p>
	 * 
	 * @param PK
	 * @return BmParam
	 */
	@Transactional
	public BmParam getBmParamById(final String id) {
		BmParam bmParam = dao.findById(BmParam.class, id);
		return bmParam;
	}
	
	/**
	 * <p>get all BmParam objects</p>
	 *
	 * @return List<BmParam>
	 */
	@Transactional
	public List<BmParam> getBmParams() {
		return dao.findAllWithOrder(BmParam.class, "modifyTimestamp", false);
	}
	
	/**
	 * <p>get all BmParam objects and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<BmParam>
	 */
	@Transactional
	public List<BmParam> getBmParamsWithOrder(String orderBy, boolean isAsc)  {
		return dao.findAllWithOrder(BmParam.class, orderBy, isAsc);
	}
	
	/**
	 * <p>get BmParam objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<BmParam>
	 */
	@Transactional
	public List<BmParam> getBmParamsByField(final String fieldName, final Object value) {
		return dao.findByField(BmParam.class, fieldName, value, "modifyTimestamp", false);
	}

	/**
	 * <p>get Map<paramTypeCdName, ListBmParam>></p>
	 * 
	 * @return Map<String,List<BmParam>>
	 */
	@Transactional
	public Map<String,List<BmParam>> getParamTypeAndBmParamss(String lang) {
		Map<String,List<BmParam>> map = new HashMap<String,List<BmParam>>();
		List<BmParam> bmParams = dao.findByField(BmParam.class, "lang", lang, "displayNo", true);
		for (BmParam bmParam : bmParams) {
			String key = bmParam.getParamTypeCd() + "," + bmParam.getParamTypeName();
			if (map.containsKey(key)) {
				map.get(key).add(bmParam);
			} else {
				List<BmParam> list = new ArrayList<BmParam>();
				list.add(bmParam);
				map.put(key, list);
			}
		}
		return map;
	}
	
	/**
	 * <p>getBmParams</p>
	 * 
	 * @param paramTypeCd
	 * @param lang
	 * @return
	 */
	@Transactional
	public List<BmParam> getBmParams(String paramTypeCd, String lang) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQS_lang", lang));
		filters.add(new PropertyFilter("EQS_paramTypeCd", paramTypeCd));
		return dao.findByPropertyFilterList(BmParam.class, filters, "displayNo", true);
	}
	/**
	 * <p>getParamTypeCdLangAndParamMaps</p>
	 * 
	 * @return
	 */
	@Transactional
	public Map<String,List<Map<String,String>>> getParamTypeCdLangAndParamMaps() {
		List<BmParam> bmParams = getBmParamsWithOrder("displayNo",true);
		Map<String,List<Map<String,String>>> paramTypeCdLangAndParamMaps = new HashMap<String,List<Map<String,String>>>();
		for (BmParam bmParam : bmParams) {
			Map<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("paramId", bmParam.getParamId());
			paramMap.put("paramCd", bmParam.getParamCd());
			paramMap.put("paramName", bmParam.getParamName());
			String key = bmParam.getParamTypeCd() + bmParam.getLang();
			if (paramTypeCdLangAndParamMaps.containsKey(key)) {
				paramTypeCdLangAndParamMaps.get(key).add(paramMap);
			} else {
				List<Map<String,String>> list = new ArrayList<Map<String,String>>();
				list.add(paramMap);
				paramTypeCdLangAndParamMaps.put(key, list);
			}
		}
		return paramTypeCdLangAndParamMaps;
	}

	/**
	 * <p>get all paramId and paramName Map</p>
	 * 
	 * @return Map<paramId,paramCd>
	 */
	@Transactional
	public Map<String,String> getParamIdAndCds() {
		List<BmParam> bmParams = getBmParams();
		Map<String,String> map = new HashMap<String,String>();
		for (BmParam bmParam : bmParams) {
			map.put(bmParam.getParamId(), bmParam.getParamCd());
		}
		return map;
	}
	
	/**
	 * <p>getParamIdAndCds</p>
	 * 
	 * @param paramType
	 * @return
	 */
	@Transactional
	public Map<String,String> getParamIdAndCds(String paramType) {
		Map<String,String> map = new HashMap<String,String>();
		List paramIdNameArrs = Config.getCashParamMaps(paramType, Const.LANGUAGE_EN_SG);
		for (int i = 0; i < paramIdNameArrs.size(); i++) {
			String[] idNameArr = (String[])paramIdNameArrs.get(i);
			map.put(idNameArr[0], idNameArr[2]);
		}	
		return map;
	}

	@Resource(name="baseDaoHibernate")
	private BaseDao<BmParam, String> dao;
}