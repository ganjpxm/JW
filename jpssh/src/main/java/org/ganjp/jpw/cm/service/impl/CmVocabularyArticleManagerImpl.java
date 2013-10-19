/**
 * $Id: CmVocabularyArticleManagerImpl.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Coss Project
 *
 */
package org.ganjp.jpw.cm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.ganjp.jpw.cm.model.CmVocabularyArticle;
import org.ganjp.jpw.cm.service.CmVocabularyArticleManager;
import org.ganjp.jpw.core.dao.BaseDao;
import org.ganjp.jpw.core.service.BaseManager;
import org.ganjp.jpw.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>CmVocabularyArticleManagerImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service 
public class CmVocabularyArticleManagerImpl extends BaseManager implements CmVocabularyArticleManager {
	
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmVocabularyArticle</p>
	 * 
	 * @param CmVocabularyArticle
	 */
	@Transactional
	public void save(CmVocabularyArticle cmVocabularyArticle) {
		dao.save(cmVocabularyArticle);
	}

	/**
	 * <p>update new cmVocabularyArticle</p>
	 * 
	 * @param CmVocabularyArticle
	 */
	@Transactional
	public void update(CmVocabularyArticle cmVocabularyArticle) {
		dao.update(cmVocabularyArticle);
	}
	/**
	 * <p>save or update new cmVocabularyArticle</p>
	 * 
	 * @param CmVocabularyArticle
	 */
	@Transactional
	public void saveOrUpdate(CmVocabularyArticle cmVocabularyArticle) {
		dao.saveOrUpdate(cmVocabularyArticle);
	}
		
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmVocabularyArticle</p>
	 * 
	 * @param cmVocabularyArticle the cmVocabularyArticle must be from session or transient object that has primary key attribute
	 */
	@Transactional
	public void delete(CmVocabularyArticle cmVocabularyArticle) {
		dao.delete(cmVocabularyArticle);
	}
	/**
	 * <p>delete a cmVocabularyArticle by primary key</p>
	 * 
	 * @param PK
	 */
	@Transactional
	public void delete(final String pk) {
		dao.delete(CmVocabularyArticle.class, pk);
	}
	/**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
	@Transactional
	public void batchDelete(final String pks) {
		String hql = "delete from CmVocabularyArticle where vocabularyArticleId in (" + StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
	}	
	//-------------------------------------------   find   ------------------------------------------
	//------------return CmVocabularyArticle
	/**
	 * <p>get CmVocabularyArticle object by primary key</p>
	 * 
	 * @param PK
	 * @return CmVocabularyArticle
	 */
	@Transactional
	public CmVocabularyArticle getCmVocabularyArticleById(final String id) {
		CmVocabularyArticle cmVocabularyArticle = dao.findById(CmVocabularyArticle.class, id);
		return cmVocabularyArticle;
	}
	
	/**
	 * <p>get all CmVocabularyArticle objects</p>
	 *
	 * @return List<CmVocabularyArticle>
	 */
	@Transactional
	public List<CmVocabularyArticle> getCmVocabularyArticles() {
		return dao.findAllWithOrder(CmVocabularyArticle.class, "modifyTimestamp", false);
	}
	
	/**
	 * <p>get all CmVocabularyArticle objects and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmVocabularyArticle>
	 */
	@Transactional
	public List<CmVocabularyArticle> getCmVocabularyArticlesWithOrder(String orderBy, boolean isAsc)  {
		return dao.findAllWithOrder(CmVocabularyArticle.class, orderBy, isAsc);
	}
	
	/**
	 * <p>get CmVocabularyArticle objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmVocabularyArticle>
	 */
	@Transactional
	public List<CmVocabularyArticle> getCmVocabularyArticlesByField(final String fieldName, final Object value) {
		return dao.findByField(CmVocabularyArticle.class, fieldName, value, "modifyTimestamp", false);
	}

	public boolean isExist(final String articleId, final String vocabularyId) {
		String hql = "select vocabularyArticleId from CmVocabularyArticle where articleId=? and vocabularyId=?";
		List<Object> objs = dao.findByHql(hql, articleId, vocabularyId);
		if (objs==null || objs.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * <p>saveCmVocabularyArticles</p>
	 * 
	 * @param articleId
	 * @param vocabularyIds
	 * @return
	 */
	@Transactional
	public String saveCmVocabularyArticles(final String articleId, final String vocabularyIds) {
		if (StringUtil.isNotEmpty(vocabularyIds)) {
			if (vocabularyIds.indexOf(",")==-1) {
				if (isExist(articleId, vocabularyIds)) {
					return "exist";
				} else {
					CmVocabularyArticle cmVocabularyArticle = new CmVocabularyArticle();
					cmVocabularyArticle.setArticleId(articleId);
					cmVocabularyArticle.setVocabularyId(vocabularyIds);
					this.save(cmVocabularyArticle);
				}
			} else {
				String[] vocabularyIdArr = vocabularyIds.split(",");
				for (String vocabularyId : vocabularyIdArr) {
					if (isExist(articleId, vocabularyId)) {
						return "exist";
					} else {
						CmVocabularyArticle cmVocabularyArticle = new CmVocabularyArticle();
						cmVocabularyArticle.setArticleId(articleId);
						cmVocabularyArticle.setVocabularyId(vocabularyId);
						this.save(cmVocabularyArticle);
					}
				}
			}
		} else {
			return "null";
		}
		return "success";
	}

	/**
	 * <p>getVocabularyMaps</p>
	 * 
	 * @param articleId
	 * @return
	 */
	@Transactional
	public List<Map<String,String>> getVocabularyMaps(final String articleId) {
		String hql = "select b.vocabularyArticleId, a.vocabularyId, a.firstName, a.firstPhonogram, a.firstMean, a.firstDescription, " +
				"a.secondName,  a.secondDescription from CmVocabulary a, CmVocabularyArticle b " +
				"where a.vocabularyId=b.vocabularyId and b.articleId=?";
		List<Object[]> objArrs = dao.findByHql(hql, articleId);
		List<Map<String,String>> vocabularyMaps = new ArrayList<Map<String,String>>();
		for (Object[] objArr : objArrs) {
			Map<String,String> map = new HashMap<String,String>();
			map.put("vocabularyArticleId", StringUtil.toString(objArr[0]));
			map.put("vocabularyId", StringUtil.toString(objArr[1]));
			map.put("firstName", StringUtil.toString(objArr[2]));
			map.put("firstPhonogram", StringUtil.toString(objArr[3]));
			map.put("firstMean", StringUtil.toString(objArr[4]));
			map.put("firstDescription", StringUtil.toString(objArr[5]));
			map.put("secondName", StringUtil.toString(objArr[6]));
			map.put("secondDescription", StringUtil.toString(objArr[7]));
			vocabularyMaps.add(map);		
		}
		return vocabularyMaps; 
	}
	
	@Resource(name="baseDaoHibernate")
	private BaseDao<CmVocabularyArticle, String> dao;
}