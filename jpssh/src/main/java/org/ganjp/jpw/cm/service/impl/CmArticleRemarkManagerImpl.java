/**
 * $Id: CmArticleRemarkManagerImpl.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Coss Project
 *
 */
package org.ganjp.jpw.cm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.ganjp.jpw.cm.model.CmArticleRemark;
import org.ganjp.jpw.cm.service.CmArticleRemarkManager;
import org.ganjp.jpw.core.dao.BaseDao;
import org.ganjp.jpw.core.service.BaseManager;
import org.ganjp.jpw.core.util.DateUtil;
import org.ganjp.jpw.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>CmArticleRemarkManagerImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service 
public class CmArticleRemarkManagerImpl extends BaseManager implements CmArticleRemarkManager {
	
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmArticleRemark</p>
	 * 
	 * @param CmArticleRemark
	 */
	@Transactional
	public void save(CmArticleRemark cmArticleRemark) {
		dao.save(cmArticleRemark);
	}

	/**
	 * <p>update new cmArticleRemark</p>
	 * 
	 * @param CmArticleRemark
	 */
	@Transactional
	public void update(CmArticleRemark cmArticleRemark) {
		dao.update(cmArticleRemark);
	}
	/**
	 * <p>save or update new cmArticleRemark</p>
	 * 
	 * @param CmArticleRemark
	 */
	@Transactional
	public void saveOrUpdate(CmArticleRemark cmArticleRemark) {
		dao.saveOrUpdate(cmArticleRemark);
	}
		
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmArticleRemark</p>
	 * 
	 * @param cmArticleRemark the cmArticleRemark must be from session or transient object that has primary key attribute
	 */
	@Transactional
	public void delete(CmArticleRemark cmArticleRemark) {
		dao.delete(cmArticleRemark);
	}
	/**
	 * <p>delete a cmArticleRemark by primary key</p>
	 * 
	 * @param PK
	 */
	@Transactional
	public void delete(final String pk) {
		dao.delete(CmArticleRemark.class, pk);
	}
	/**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
	@Transactional
	public void batchDelete(final String pks) {
		String hql = "delete from CmArticleRemark where articleRemarkId in (" + StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
	}	
	//-------------------------------------------   find   ------------------------------------------
	//------------return CmArticleRemark
	/**
	 * <p>get CmArticleRemark object by primary key</p>
	 * 
	 * @param PK
	 * @return CmArticleRemark
	 */
	@Transactional
	public CmArticleRemark getCmArticleRemarkById(final String id) {
		CmArticleRemark cmArticleRemark = dao.findById(CmArticleRemark.class, id);
		return cmArticleRemark;
	}
	
	/**
	 * <p>get all CmArticleRemark objects</p>
	 *
	 * @return List<CmArticleRemark>
	 */
	@Transactional
	public List<CmArticleRemark> getCmArticleRemarks() {
		return dao.findAllWithOrder(CmArticleRemark.class, "modifyTimestamp", false);
	}
	
	/**
	 * <p>get all CmArticleRemark objects and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmArticleRemark>
	 */
	@Transactional
	public List<CmArticleRemark> getCmArticleRemarksWithOrder(String orderBy, boolean isAsc)  {
		return dao.findAllWithOrder(CmArticleRemark.class, orderBy, isAsc);
	}
	
	/**
	 * <p>get CmArticleRemark objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmArticleRemark>
	 */
	@Transactional
	public List<CmArticleRemark> getCmArticleRemarksByField(final String fieldName, final Object value) {
		return dao.findByField(CmArticleRemark.class, fieldName, value, "modifyTimestamp", false);
	}
	
	/**
	 * <p>getArticleIdAndOperatorIdTimeRemarks</p>
	 * 
	 * @param articleIds
	 * @return
	 */
	@Transactional
	public Map<String,List<String>> getArticleIdAndOperatorIdTimeRemarks(final String articleIds) {
		Map<String,List<String>> articleIdAndOperatorIdRemarks = new HashMap<String,List<String>>();
		String hql = "select a.articleId, a.remark, a.operatorId, a.modifyTimestamp from CmArticleRemark a where a.articleId in (" + StringUtil.getStrWithQuote(articleIds) + ") order by a.modifyTimestamp desc";
		List<Object[]> objArrs = dao.findByHql(hql);
		for (Object[] objArr : objArrs) {
			String articleId = StringUtil.toString(objArr[0]);
			String value = StringUtil.toString(objArr[2]) + ":::" + DateUtil.getTimeDateStr((Date)objArr[3]) + ":::" + StringUtil.toString(objArr[1]); 
			if (articleIdAndOperatorIdRemarks.containsKey(articleId)) {
				articleIdAndOperatorIdRemarks.get(articleId).add(value);
			} else {
				List<String> list = new ArrayList<String>();
				list.add(value);
				articleIdAndOperatorIdRemarks.put(articleId, list);
			}
		}
		return articleIdAndOperatorIdRemarks;
	}
	
	@Resource(name="baseDaoHibernate")
	private BaseDao<CmArticleRemark, String> dao;
}