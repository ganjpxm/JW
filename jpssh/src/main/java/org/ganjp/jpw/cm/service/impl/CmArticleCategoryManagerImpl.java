/**
 * $Id: CmArticleCategoryManagerImpl.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
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

import org.ganjp.jpw.cm.model.CmArticle;
import org.ganjp.jpw.cm.model.CmArticleCategory;
import org.ganjp.jpw.cm.service.CmArticleCategoryManager;
import org.ganjp.jpw.cm.service.CmArticleManager;
import org.ganjp.jpw.core.dao.BaseDao;
import org.ganjp.jpw.core.service.BaseManager;
import org.ganjp.jpw.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>CmArticleCategoryManagerImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service 
public class CmArticleCategoryManagerImpl extends BaseManager implements CmArticleCategoryManager {
	
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmArticleCategory</p>
	 * 
	 * @param CmArticleCategory
	 */
	@Transactional
	public void save(CmArticleCategory cmArticleCategory) {
		dao.save(cmArticleCategory);
	}

	/**
	 * <p>update new cmArticleCategory</p>
	 * 
	 * @param CmArticleCategory
	 */
	@Transactional
	public void update(CmArticleCategory cmArticleCategory) {
		dao.update(cmArticleCategory);
	}
	/**
	 * <p>save or update new cmArticleCategory</p>
	 * 
	 * @param CmArticleCategory
	 */
	@Transactional
	public void saveOrUpdate(CmArticleCategory cmArticleCategory) {
		dao.saveOrUpdate(cmArticleCategory);
	}
		
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmArticleCategory</p>
	 * 
	 * @param cmArticleCategory the cmArticleCategory must be from session or transient object that has primary key attribute
	 */
	@Transactional
	public void delete(CmArticleCategory cmArticleCategory) {
		dao.delete(cmArticleCategory);
	}
	/**
	 * <p>delete a cmArticleCategory by primary key</p>
	 * 
	 * @param PK
	 */
	@Transactional
	public void delete(final String pk) {
		dao.delete(CmArticleCategory.class, pk);
	}
	/**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
	@Transactional
	public void batchDelete(final String pks) {
		String hql = "delete from CmArticleCategory where articleCategoryId in (" + StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
	}	
	
	/**
	 * <p>deleteByArticleId</p>
	 * 
	 * @param articleId
	 */
	@Transactional
	public void deleteByArticleId(String articleId) {
		String hql = "delete from CmArticleCategory where articleId=?";
		dao.batchExecute(hql,articleId);
	}
	
	//-------------------------------------------   find   ------------------------------------------
	//------------return CmArticleCategory
	/**
	 * <p>get CmArticleCategory object by primary key</p>
	 * 
	 * @param PK
	 * @return CmArticleCategory
	 */
	@Transactional
	public CmArticleCategory getCmArticleCategoryById(final String id) {
		CmArticleCategory cmArticleCategory = dao.findById(CmArticleCategory.class, id);
		return cmArticleCategory;
	}
	
	/**
	 * <p>get all CmArticleCategory objects</p>
	 *
	 * @return List<CmArticleCategory>
	 */
	@Transactional
	public List<CmArticleCategory> getCmArticleCategorys() {
		return dao.findAllWithOrder(CmArticleCategory.class, "modifyTimestamp", false);
	}
	
	/**
	 * <p>get all CmArticleCategory objects and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmArticleCategory>
	 */
	@Transactional
	public List<CmArticleCategory> getCmArticleCategorysWithOrder(String orderBy, boolean isAsc)  {
		return dao.findAllWithOrder(CmArticleCategory.class, orderBy, isAsc);
	}
	
	/**
	 * <p>get CmArticleCategory objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmArticleCategory>
	 */
	@Transactional
	public List<CmArticleCategory> getCmArticleCategorysByField(final String fieldName, final Object value) {
		return dao.findByField(CmArticleCategory.class, fieldName, value, "modifyTimestamp", false);
	}

	/**
	 * <p>getArticleMaps</p>
	 * 
	 * @param categoryId
	 * @param ownRoleIds
	 * @return
	 */
	@Transactional
	public List<Map<String,String>> getArticleMaps(String categoryId, String ownRoleIds) {
		String hql = "select distinct(a.articleId), a.roleIds, a.articleCd, a.title " +
				"from CmArticle a, CmArticleCategory b " +
				"where a.articleId=b.articleId and b.categoryId = ? order by a.displayNo";
		List<Object[]> objArrs =  dao.findByHql(hql, categoryId);
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		for (Object[] objArr : objArrs) {
			String roleIds = StringUtil.toString(objArr[1]);
			if (StringUtil.aCanAccessB(ownRoleIds, roleIds)) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("articleId", StringUtil.toString(objArr[0]));
				map.put("articleCd", StringUtil.toString(objArr[2]));
				map.put("title", StringUtil.toString(objArr[3]));
				map.put("categoryId", categoryId);
				list.add(map);
			}
		}
		return list;
	}

	/**
	 * <p>saveCmArticleAndCategory</p>
	 * 
	 * @param cmArticle
	 * @param categoryIds
	 */
	@Transactional
	public void saveCmArticleAndCategory(CmArticle cmArticle, String categoryIds) {
		cmArticleManager.save(cmArticle);
		if (StringUtil.isNotEmpty(categoryIds)) {
			String[] categoryIdArr = categoryIds.split(",");
			for (String categoryId : categoryIdArr) {
				CmArticleCategory cmArticleCategory = new CmArticleCategory();
				cmArticleCategory.setCategoryId(categoryId);
				cmArticleCategory.setArticleId(cmArticle.getArticleId());
				this.save(cmArticleCategory);
			}
		}
	}

	/**
	 * <p>updateCmArticleAndCategory</p>
	 * 
	 * @param cmArticle
	 * @param categoryIds
	 */
	@Transactional
	public void updateCmArticleAndCategory(CmArticle cmArticle, String categoryIds) {
		cmArticleManager.update(cmArticle);
		if (StringUtil.isNotEmpty(categoryIds)) {
			this.deleteByArticleId(cmArticle.getArticleId());
			String[] categoryIdArr = categoryIds.split(",");
			for (String categoryId : categoryIdArr) {
				CmArticleCategory cmArticleCategory = new CmArticleCategory();
				cmArticleCategory.setCategoryId(categoryId);
				cmArticleCategory.setArticleId(cmArticle.getArticleId());
				this.save(cmArticleCategory);
			}
		}
	}
	
	/**
	 * <p>getCategoryIds</p>
	 * 
	 * @param articleId
	 * @return
	 */
	@Transactional
	public String getCategoryIds(final String articleId) {
		String categoryIds = "";
		List<CmArticleCategory> cmArticleCategorys = dao.findByField(CmArticleCategory.class, "articleId", articleId);
		for (CmArticleCategory cmArticleCategory : cmArticleCategorys) {
			if (StringUtil.isEmpty(categoryIds)) {
				categoryIds = cmArticleCategory.getCategoryId();
			} else {
				categoryIds += "," + cmArticleCategory.getCategoryId();
			}
		}
		return categoryIds;
	}
	
	@Resource(name="baseDaoHibernate")
	private BaseDao<CmArticleCategory, String> dao;
	
	@Resource(name="cmArticleManagerImpl")
	private CmArticleManager cmArticleManager;
}