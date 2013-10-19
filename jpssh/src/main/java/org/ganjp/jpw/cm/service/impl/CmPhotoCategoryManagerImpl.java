/**
 * $Id: CmPhotoCategoryManagerImpl.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
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

import org.ganjp.jpw.cm.model.CmPhoto;
import org.ganjp.jpw.cm.model.CmPhotoCategory;
import org.ganjp.jpw.cm.service.CmPhotoCategoryManager;
import org.ganjp.jpw.cm.service.CmPhotoManager;
import org.ganjp.jpw.core.dao.BaseDao;
import org.ganjp.jpw.core.service.BaseManager;
import org.ganjp.jpw.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>CmPhotoCategoryManagerImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service 
public class CmPhotoCategoryManagerImpl extends BaseManager implements CmPhotoCategoryManager {
	
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmPhotoCategory</p>
	 * 
	 * @param CmPhotoCategory
	 */
	@Transactional
	public void save(CmPhotoCategory cmPhotoCategory) {
		dao.save(cmPhotoCategory);
	}

	/**
	 * <p>update new cmPhotoCategory</p>
	 * 
	 * @param CmPhotoCategory
	 */
	@Transactional
	public void update(CmPhotoCategory cmPhotoCategory) {
		dao.update(cmPhotoCategory);
	}
	/**
	 * <p>save or update new cmPhotoCategory</p>
	 * 
	 * @param CmPhotoCategory
	 */
	@Transactional
	public void saveOrUpdate(CmPhotoCategory cmPhotoCategory) {
		dao.saveOrUpdate(cmPhotoCategory);
	}
		
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmPhotoCategory</p>
	 * 
	 * @param cmPhotoCategory the cmPhotoCategory must be from session or transient object that has primary key attribute
	 */
	@Transactional
	public void delete(CmPhotoCategory cmPhotoCategory) {
		dao.delete(cmPhotoCategory);
	}
	/**
	 * <p>delete a cmPhotoCategory by primary key</p>
	 * 
	 * @param PK
	 */
	@Transactional
	public void delete(final String pk) {
		dao.delete(CmPhotoCategory.class, pk);
	}
	/**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
	@Transactional
	public void batchDelete(final String pks) {
		String hql = "delete from CmPhotoCategory where photoCategoryId in (" + StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
	}
	
	/**
	 * <p>deleteByPhotoId</p>
	 * 
	 * @param photoId
	 */
	@Transactional
	public void deleteByPhotoId(String photoId) {
		String hql = "delete from CmPhotoCategory where photoId=?";
		dao.batchExecute(hql,photoId);
	}
	
	/**
	 * <p>deleteCmPhotoAndCmPhotoCategory</p>
	 * 
	 * @param photoId
	 */
	@Transactional
	public void deleteCmPhotoAndCmPhotoCategory(String photoId) {
		this.deleteByPhotoId(photoId);
		cmPhotoManager.delete(photoId);
	}
	//-------------------------------------------   find   ------------------------------------------
	//------------return CmPhotoCategory
	/**
	 * <p>get CmPhotoCategory object by primary key</p>
	 * 
	 * @param PK
	 * @return CmPhotoCategory
	 */
	@Transactional
	public CmPhotoCategory getCmPhotoCategoryById(final String id) {
		CmPhotoCategory cmPhotoCategory = dao.findById(CmPhotoCategory.class, id);
		return cmPhotoCategory;
	}
	
	/**
	 * <p>get all CmPhotoCategory objects</p>
	 *
	 * @return List<CmPhotoCategory>
	 */
	@Transactional
	public List<CmPhotoCategory> getCmPhotoCategorys() {
		return dao.findAllWithOrder(CmPhotoCategory.class, "modifyTimestamp", false);
	}
	
	/**
	 * <p>get all CmPhotoCategory objects and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmPhotoCategory>
	 */
	@Transactional
	public List<CmPhotoCategory> getCmPhotoCategorysWithOrder(String orderBy, boolean isAsc)  {
		return dao.findAllWithOrder(CmPhotoCategory.class, orderBy, isAsc);
	}
	
	/**
	 * <p>get CmPhotoCategory objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmPhotoCategory>
	 */
	@Transactional
	public List<CmPhotoCategory> getCmPhotoCategorysByField(final String fieldName, final Object value) {
		return dao.findByField(CmPhotoCategory.class, fieldName, value, "modifyTimestamp", false);
	}

	/**
	 * <p>getPhotoMaps</p>
	 * 
	 * @param categoryId
	 * @param ownRoleIds
	 * @return
	 */
	@Transactional
	public List<Map<String,String>> getPhotoMaps(String categoryId, String ownRoleIds) {
		String hql = "select distinct(a.photoId), a.photoName, a.title, a.url, a.tag, a.remark, a.roleIds " +
				"from CmPhoto a, CmPhotoCategory b " +
				"where a.photoId=b.photoId and b.categoryId = ? order by a.displayNo";
		List<Object[]> objArrs =  dao.findByHql(hql, categoryId);
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		for (Object[] objArr : objArrs) {
			String roleIds = StringUtil.toString(objArr[6]);
			if (StringUtil.aCanAccessB(ownRoleIds, roleIds)) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("photoId", StringUtil.toString(objArr[0]));
				map.put("photoName", StringUtil.toString(objArr[1]));
				map.put("title", StringUtil.toString(objArr[2]));
				map.put("url", StringUtil.toString(objArr[3]));
				map.put("tag", StringUtil.toString(objArr[4]));
				map.put("remark", StringUtil.toString(objArr[5]));
				map.put("categoryId", categoryId);
				list.add(map);
			}
		}
		return list;
	}
	
	/**
	 * <p>saveCmPhotoAndCategory</p>
	 * 
	 * @param cmPhoto
	 * @param categoryIds
	 */
	@Transactional
	public void saveCmPhotoAndCategory(CmPhoto cmPhoto, String categoryIds) {
		cmPhotoManager.save(cmPhoto);
		if (StringUtil.isNotEmpty(categoryIds)) {
			String[] categoryIdArr = categoryIds.split(",");
			for (String categoryId : categoryIdArr) {
				CmPhotoCategory cmPhotoCategory = new CmPhotoCategory();
				cmPhotoCategory.setCategoryId(categoryId);
				cmPhotoCategory.setPhotoId(cmPhoto.getPhotoId());
				this.save(cmPhotoCategory);
			}
		}
	}

	/**
	 * <p>updateCmPhotoAndCategory</p>
	 * 
	 * @param cmPhoto
	 * @param categoryIds
	 */
	@Transactional
	public void updateCmPhotoAndCategory(CmPhoto cmPhoto, String categoryIds) {
		cmPhotoManager.update(cmPhoto);
		if (StringUtil.isNotEmpty(categoryIds)) {
			this.deleteByPhotoId(cmPhoto.getPhotoId());
			String[] categoryIdArr = categoryIds.split(",");
			for (String categoryId : categoryIdArr) {
				CmPhotoCategory cmPhotoCategory = new CmPhotoCategory();
				cmPhotoCategory.setCategoryId(categoryId);
				cmPhotoCategory.setPhotoId(cmPhoto.getPhotoId());
				this.save(cmPhotoCategory);
			}
		}
	}
	
	/**
	 * <p>getPhotoMaps</p>
	 * 
	 * @param categoryCd
	 * @param lang
	 * @param tag
	 * @param ownRoleIds
	 * @return
	 */
	@Transactional
	public List<Map<String,String>> getPhotoMaps(String categoryCd, String lang, String tag, String ownRoleIds) {
		String hql = "select distinct(a.photoId), a.photoName, a.title, a.thumbUrl, a.url, a.roleIds " +
				"from CmPhoto a, CmPhotoCategory b, CmCategory c " +
				"where a.photoId=b.photoId and b.categoryId=c.categoryId and " +
				"c.categoryCd = ? and a.lang=? ";
		if (StringUtil.isNotEmpty(tag)) {
			hql += "and a.tag like ? ";
		}
		hql += "order by a.displayNo";
		List<Object[]> objArrs =  dao.findByHql(hql, categoryCd, lang, "%"+tag+"%");
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		for (Object[] objArr : objArrs) {
			String roleIds = StringUtil.toString(objArr[5]);
			if (StringUtil.aCanAccessB(ownRoleIds, roleIds)) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("title", StringUtil.toString(objArr[2]));
				map.put("thumbUrl", StringUtil.toString(objArr[3]));
				map.put("url", StringUtil.toString(objArr[4]));
				list.add(map);
			}
		}
		return list;
	}

	/**
	 * <p>getCategoryIds</p>
	 * 
	 * @param photoId
	 * @return
	 */
	@Transactional
	public String getCategoryIds(final String photoId) {
		String categoryIds = "";
		List<CmPhotoCategory> cmPhotoCategorys = dao.findByField(CmPhotoCategory.class, "photoId", photoId);
		for (CmPhotoCategory cmPhotoCategory : cmPhotoCategorys) {
			if (StringUtil.isEmpty(categoryIds)) {
				categoryIds = cmPhotoCategory.getCategoryId();
			} else {
				categoryIds += "," + cmPhotoCategory.getCategoryId();
			}
		}
		return categoryIds;
	}
	
	@Resource(name="baseDaoHibernate")
	private BaseDao<CmPhotoCategory, String> dao;
	
	@Resource(name="cmPhotoManagerImpl")
	private CmPhotoManager cmPhotoManager;
}