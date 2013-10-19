/**
 * $Id: CmVocabularyCategoryManagerImpl.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
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

import org.ganjp.jpw.cm.model.CmVocabulary;
import org.ganjp.jpw.cm.model.CmVocabularyCategory;
import org.ganjp.jpw.cm.service.CmVocabularyCategoryManager;
import org.ganjp.jpw.cm.service.CmVocabularyManager;
import org.ganjp.jpw.core.dao.BaseDao;
import org.ganjp.jpw.core.service.BaseManager;
import org.ganjp.jpw.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>CmVocabularyCategoryManagerImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service 
public class CmVocabularyCategoryManagerImpl extends BaseManager implements CmVocabularyCategoryManager {
	
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmVocabularyCategory</p>
	 * 
	 * @param CmVocabularyCategory
	 */
	@Transactional
	public void save(CmVocabularyCategory cmVocabularyCategory) {
		dao.save(cmVocabularyCategory);
	}

	/**
	 * <p>update new cmVocabularyCategory</p>
	 * 
	 * @param CmVocabularyCategory
	 */
	@Transactional
	public void update(CmVocabularyCategory cmVocabularyCategory) {
		dao.update(cmVocabularyCategory);
	}
	/**
	 * <p>save or update new cmVocabularyCategory</p>
	 * 
	 * @param CmVocabularyCategory
	 */
	@Transactional
	public void saveOrUpdate(CmVocabularyCategory cmVocabularyCategory) {
		dao.saveOrUpdate(cmVocabularyCategory);
	}
		
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmVocabularyCategory</p>
	 * 
	 * @param cmVocabularyCategory the cmVocabularyCategory must be from session or transient object that has primary key attribute
	 */
	@Transactional
	public void delete(CmVocabularyCategory cmVocabularyCategory) {
		dao.delete(cmVocabularyCategory);
	}
	/**
	 * <p>delete a cmVocabularyCategory by primary key</p>
	 * 
	 * @param PK
	 */
	@Transactional
	public void delete(final String pk) {
		dao.delete(CmVocabularyCategory.class, pk);
	}
	/**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
	@Transactional
	public void batchDelete(final String pks) {
		String hql = "delete from CmVocabularyCategory where vocabularyCategoryId in (" + StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
	}	
	
	/**
	 * <p>deleteByVocabularyId</p>
	 * 
	 * @param vocabularyId
	 */
	@Transactional
	public void deleteByVocabularyId(String vocabularyId) {
		String hql = "delete from CmVocabularyCategory where vocabularyId=?";
		dao.batchExecute(hql,vocabularyId);
	}
	
	//-------------------------------------------   find   ------------------------------------------
	//------------return CmVocabularyCategory
	/**
	 * <p>get CmVocabularyCategory object by primary key</p>
	 * 
	 * @param PK
	 * @return CmVocabularyCategory
	 */
	@Transactional
	public CmVocabularyCategory getCmVocabularyCategoryById(final String id) {
		CmVocabularyCategory cmVocabularyCategory = dao.findById(CmVocabularyCategory.class, id);
		return cmVocabularyCategory;
	}
	
	/**
	 * <p>get all CmVocabularyCategory objects</p>
	 *
	 * @return List<CmVocabularyCategory>
	 */
	@Transactional
	public List<CmVocabularyCategory> getCmVocabularyCategorys() {
		return dao.findAllWithOrder(CmVocabularyCategory.class, "modifyTimestamp", false);
	}
	
	/**
	 * <p>get all CmVocabularyCategory objects and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmVocabularyCategory>
	 */
	@Transactional
	public List<CmVocabularyCategory> getCmVocabularyCategorysWithOrder(String orderBy, boolean isAsc)  {
		return dao.findAllWithOrder(CmVocabularyCategory.class, orderBy, isAsc);
	}
	
	/**
	 * <p>get CmVocabularyCategory objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmVocabularyCategory>
	 */
	@Transactional
	public List<CmVocabularyCategory> getCmVocabularyCategorysByField(final String fieldName, final Object value) {
		return dao.findByField(CmVocabularyCategory.class, fieldName, value, "modifyTimestamp", false);
	}

	/**
	 * <p>getVocabularyMaps</p>
	 * 
	 * @param categoryId
	 * @param ownRoleIds
	 * @return
	 */
	@Transactional
	public List<Map<String,String>> getVocabularyMaps(String categoryId, String ownRoleIds) {
		String hql = "select distinct(a.vocabularyId), a.roleIds, a.firstName, a.secondName, a.imageUrl, a.firstMean, " +
				"a.firstPhonogram, a.firstDescription, a.secondDescription, a.displayNo " +
				"from CmVocabulary a, CmVocabularyCategory b " +
				"where a.vocabularyId=b.vocabularyId and b.categoryId = ? order by a.displayNo";
		List<Object[]> objArrs =  dao.findByHql(hql, categoryId);
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		for (Object[] objArr : objArrs) {
			String roleIds = StringUtil.toString(objArr[1]);
			if (StringUtil.aCanAccessB(ownRoleIds, roleIds)) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("vocabularyId", StringUtil.toString(objArr[0]));
				map.put("firstName", StringUtil.toString(objArr[2]));
				map.put("secondName", StringUtil.toString(objArr[3]));
				map.put("imageUrl", StringUtil.toString(objArr[4]));
				map.put("firstMean", StringUtil.toString(objArr[5]));
				map.put("firstPhonogram", StringUtil.toString(objArr[6]));
				map.put("firstDescription", StringUtil.toString(objArr[7]));
				map.put("secondDescription", StringUtil.toString(objArr[8]));
				map.put("displayNo", StringUtil.toString(objArr[9]));
				map.put("categoryId", categoryId);
				list.add(map);
			}
		}
		return list;
	}

	/**
	 * <p>saveCmVocabularyAndCategory</p>
	 * 
	 * @param cmVocabulary
	 * @param categoryIds
	 */
	@Transactional
	public void saveCmVocabularyAndCategory(CmVocabulary cmVocabulary, String categoryIds) {
		cmVocabularyManager.save(cmVocabulary);
		if (StringUtil.isNotEmpty(categoryIds)) {
			String[] categoryIdArr = categoryIds.split(",");
			for (String categoryId : categoryIdArr) {
				CmVocabularyCategory cmVocabularyCategory = new CmVocabularyCategory();
				cmVocabularyCategory.setCategoryId(categoryId);
				cmVocabularyCategory.setVocabularyId(cmVocabulary.getVocabularyId());
				this.save(cmVocabularyCategory);
			}
		}
	}

	/**
	 * <p>updateCmVocabularyAndCategory</p>
	 * 
	 * @param cmVocabulary
	 * @param categoryIds
	 */
	@Transactional
	public void updateCmVocabularyAndCategory(CmVocabulary cmVocabulary, String categoryIds) {
		cmVocabularyManager.update(cmVocabulary);
		if (StringUtil.isNotEmpty(categoryIds)) {
			this.deleteByVocabularyId(cmVocabulary.getVocabularyId());
			String[] categoryIdArr = categoryIds.split(",");
			for (String categoryId : categoryIdArr) {
				CmVocabularyCategory cmVocabularyCategory = new CmVocabularyCategory();
				cmVocabularyCategory.setCategoryId(categoryId);
				cmVocabularyCategory.setVocabularyId(cmVocabulary.getVocabularyId());
				this.save(cmVocabularyCategory);
			}
		}
	}
	
	/**
	 * <p>getCategoryIds</p>
	 * 
	 * @param vocabularyId
	 * @return
	 */
	@Transactional
	public String getCategoryIds(final String vocabularyId) {
		String categoryIds = "";
		List<CmVocabularyCategory> cmVocabularyCategorys = dao.findByField(CmVocabularyCategory.class, "vocabularyId", vocabularyId);
		for (CmVocabularyCategory cmVocabularyCategory : cmVocabularyCategorys) {
			if (StringUtil.isEmpty(categoryIds)) {
				categoryIds = cmVocabularyCategory.getCategoryId();
			} else {
				categoryIds += "," + cmVocabularyCategory.getCategoryId();
			}
		}
		return categoryIds;
	}
	
	@Resource(name="baseDaoHibernate")
	private BaseDao<CmVocabularyCategory, String> dao;
	
	@Resource(name="cmVocabularyManagerImpl")
	private CmVocabularyManager cmVocabularyManager;
}