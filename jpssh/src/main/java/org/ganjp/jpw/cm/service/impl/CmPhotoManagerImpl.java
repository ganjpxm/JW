/**
 * $Id: CmPhotoManagerImpl.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Coss Project
 *
 */
package org.ganjp.jpw.cm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.ganjp.jpw.cm.model.CmPhoto;
import org.ganjp.jpw.cm.service.CmPhotoManager;
import org.ganjp.jpw.core.Const;
import org.ganjp.jpw.core.dao.BaseDao;
import org.ganjp.jpw.core.model.Page;
import org.ganjp.jpw.core.service.BaseManager;
import org.ganjp.jpw.core.util.DateUtil;
import org.ganjp.jpw.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>CmPhotoManagerImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service 
public class CmPhotoManagerImpl extends BaseManager implements CmPhotoManager {
	
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmPhoto</p>
	 * 
	 * @param CmPhoto
	 */
	@Transactional
	public void save(CmPhoto cmPhoto) {
		dao.save(cmPhoto);
	}

	/**
	 * <p>update new cmPhoto</p>
	 * 
	 * @param CmPhoto
	 */
	@Transactional
	public void update(CmPhoto cmPhoto) {
		dao.update(cmPhoto);
	}
	/**
	 * <p>save or update new cmPhoto</p>
	 * 
	 * @param CmPhoto
	 */
	@Transactional
	public void saveOrUpdate(CmPhoto cmPhoto) {
		dao.saveOrUpdate(cmPhoto);
	}
		
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmPhoto</p>
	 * 
	 * @param cmPhoto the cmPhoto must be from session or transient object that has primary key attribute
	 */
	@Transactional
	public void delete(CmPhoto cmPhoto) {
		dao.delete(cmPhoto);
	}
	/**
	 * <p>delete a cmPhoto by primary key</p>
	 * 
	 * @param PK
	 */
	@Transactional
	public void delete(final String pk) {
		dao.delete(CmPhoto.class, pk);
	}
	/**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
	@Transactional
	public void batchDelete(final String pks) {
		String hql = "delete from CmPhotoCategory where photoId in (" + StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
		hql = "delete from CmPhoto where photoId in (" + StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
	}	
	//-------------------------------------------   find   ------------------------------------------
	//------------return CmPhoto
	/**
	 * <p>get CmPhoto object by primary key</p>
	 * 
	 * @param PK
	 * @return CmPhoto
	 */
	@Transactional
	public CmPhoto getCmPhotoById(final String id) {
		CmPhoto cmPhoto = dao.findById(CmPhoto.class, id);
		return cmPhoto;
	}
	
	/**
	 * <p>get all CmPhoto objects</p>
	 *
	 * @return List<CmPhoto>
	 */
	@Transactional
	public List<CmPhoto> getCmPhotos() {
		return dao.findAllWithOrder(CmPhoto.class, "modifyTimestamp", false);
	}
	
	/**
	 * <p>get all CmPhoto objects and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmPhoto>
	 */
	@Transactional
	public List<CmPhoto> getCmPhotosWithOrder(String orderBy, boolean isAsc)  {
		return dao.findAllWithOrder(CmPhoto.class, orderBy, isAsc);
	}
	
	/**
	 * <p>get CmPhoto objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmPhoto>
	 */
	@Transactional
	public List<CmPhoto> getCmPhotosByField(final String fieldName, final Object value) {
		return dao.findByField(CmPhoto.class, fieldName, value, "modifyTimestamp", false);
	}

	/**
	 * <p>getPhotoMaps</p>
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
	@Transactional
	public List<Map<String,String>> getPhotoMaps(final String tag, final String lang, final String startDate, final String endDate, 
			final int pageNo, final int pageSize, String ownRoleIds) {
		String hql = "select a.photoId, a.photoName, a.title, a.thumbUrl, a.url, a.originUrl, a.tag, a.remark, a.modifyTimestamp, a.roleIds " +
				"from CmPhoto a where a.lang = ? ";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(lang);
		
		if (StringUtil.isNotEmpty(ownRoleIds)) {
			hql += "and (a.roleIds = '' "; 
			String[] aArr = ownRoleIds.split(",");
			for (String tmp : aArr) {
				hql += " or a.roleIds like '%" + tmp + "%'";  
			}
			hql += ") ";
		} else {
			hql += " and a.roleIds =''  ";
		}
		
		if (StringUtil.isNotEmpty(tag)) {
			if (tag.indexOf(",")!=-1) {
				String[] tagArr = tag.split(",");
				hql += " and ( ";
				for (int i=0; i<tagArr.length; i++) {
					if (i==0) {
						hql += " a.tag like '%" + tagArr[i] + "%'"; 
					} else {
						hql += " or a.tag like '%" + tagArr[i] + "%'";
					}
				}
				hql += " or a.title like '%" + tag + "%'";
				hql += " ) ";
			} else if (tag.indexOf(";")!=-1) {
				String[] tagArr = tag.split(";");
				for (int i=0; i<tagArr.length; i++) {
					hql += " and a.tag like '%" + tagArr[i] + "%'"; 
				}
				hql += " or a.title like '%" + tag + "%'";
			} else if ("null".equalsIgnoreCase(tag)) {
				hql += " and (a.tag='' or a.tag is null) ";
			} else {
				hql += " and (a.tag like '%" + tag + "%' or a.title like '%" + tag + "%')";
			}
		} else {
			hql += " and ((a.tag not like '%yjl%' and a.tag not like '%gjp%') or a.tag is null) ";
		}
		
		if (StringUtil.isNotEmpty(startDate)) {
			hql += " and a.modifyTimestamp>=? ";
			paramList.add(DateUtil.parseDateOrDateTime(startDate));
		}
		if (StringUtil.isNotEmpty(endDate)) {
			hql += " and a.modifyTimestamp<=? "; 
			paramList.add(DateUtil.parseDateOrDateTime(endDate));
		}
		hql += " order by a.modifyTimestamp desc";
		Page<CmPhoto> page = dao.fetchPageByHql(pageNo, pageSize, hql, paramList.toArray());
		List result = page.getResult();
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Map<String,String> pageMap = new HashMap<String,String>();
		pageMap.put("totalCount", String.valueOf(page.getTotalCount()));
		pageMap.put("totalPages", String.valueOf(page.getTotalPages()));
		list.add(pageMap);
		
		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			Object[] objArr = (Object[]) iterator.next();
			String roleIds = StringUtil.toString(objArr[9]);
			if (StringUtil.aCanAccessB(ownRoleIds, roleIds)) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("photoId", StringUtil.toString(objArr[0]));
				map.put("photoName", StringUtil.toString(objArr[1]));
				map.put("title", StringUtil.toString(objArr[2]));
				map.put("thumbUrl", StringUtil.toString(objArr[3]));
				map.put("url", StringUtil.toString(objArr[4]));
				map.put("originUrl", StringUtil.toString(objArr[5]));
				map.put("tag", StringUtil.toString(objArr[6]));
				map.put("remark", StringUtil.toString(objArr[7]));
				map.put("updateDate", DateUtil.getDateString((Date)objArr[8]));
				list.add(map);
			}
		}
		
		return list;
	}
	
	/**
	 * <p>getPhotoMaps</p>
	 * 
	 * @param tag
	 * @param lang
	 * @param ownRoleIds
	 * @return
	 */
	@Transactional
	public List<Map<String,String>> getPhotoMaps(String tag, String lang, String ownRoleIds) {
		String hql = "select a.photoId, a.photoName, a.title, a.url, a.tag, a.remark, a.roleIds " +
				"from CmPhoto a where a.tag like ? and a.lang = ? order by a.displayNo";
		List<Object[]> objArrs =  dao.findByHql(hql, "%"+tag+"%", lang);
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		for (Object[] objArr : objArrs) {
			String roleIds = StringUtil.toString(objArr[6]);
			if (Const.ROLE_NO_NEED.equals(ownRoleIds) || StringUtil.aCanAccessB(ownRoleIds, roleIds)) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("photoId", StringUtil.toString(objArr[0]));
				map.put("photoName", StringUtil.toString(objArr[1]));
				map.put("title", StringUtil.toString(objArr[2]));
				map.put("url", StringUtil.toString(objArr[3]));
				map.put("tag", StringUtil.toString(objArr[4]));
				map.put("remark", StringUtil.toString(objArr[5]));
				list.add(map);
			}
		}
		return list;
	}

	/**
	 * <p>get CmPhotos bPhotoleId</p>
	 * 
	 * @param articleId
	 * @return
	 */
	@Transactional
	public List<CmPhoto> getCmPhotosByArticleId(final String articleId) {
		return dao.findByField(CmPhoto.class, "refArticleId", articleId, "displayNo", true);
	}
	
	/**
	 * <p>getCmPhotos</p>
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
	@Transactional
	public Page<CmPhoto> getCmPhotoPage(final String tag, final String lang, final String startDate, final String endDate, final int pageNo,
			final int pageSize, String ownRoleIds) {
		String hql = "select a from CmPhoto a where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();
		if (StringUtil.isNotEmpty(lang)) {
			hql += " and a.lang = ? ";
			paramList.add(lang);
		}
		if (StringUtil.isNotEmpty(ownRoleIds)) {
			hql += "and (a.roleIds = '' ";
			String[] aArr = ownRoleIds.split(",");
			for (String tmp : aArr) {
				hql += " or a.roleIds like '%" + tmp + "%'";
			}
			hql += ") ";
		} else {
			hql += " and a.roleIds =''  ";
		}
		if (StringUtil.isNotEmpty(tag)) {
			if (tag.indexOf(",") != -1) {
				String[] tagArr = tag.split(",");
				hql += " and ( ";
				for (int i = 0; i < tagArr.length; i++) {
					if (i == 0) {
						hql += " a.tag like '%" + tagArr[i] + "%'";
					} else {
						hql += " or a.tag like '%" + tagArr[i] + "%'";
					}
				}
				hql += " or a.title like '%" + tag + "%'";
				hql += " ) ";
			} else if (tag.indexOf(";") != -1) {
				String[] tagArr = tag.split(";");
				for (int i = 0; i < tagArr.length; i++) {
					hql += " and a.tag like '%" + tagArr[i] + "%'";
				}
				hql += " or a.title like '%" + tag + "%'";
			} else if ("null".equalsIgnoreCase(tag)) {
				hql += " and (a.tag='' or a.tag is null) ";
			} else {
				hql += " and (a.tag like '%" + tag + "%' or a.title like '%" + tag + "%')";
			}
		}
		if (StringUtil.isNotEmpty(startDate)) {
			hql += " and a.modifyTimestamp>? ";
			paramList.add(DateUtil.parseDateOrDateTime(startDate));
		}
		if (StringUtil.isNotEmpty(endDate)) {
			hql += " and a.modifyTimestamp<? ";
			paramList.add(DateUtil.parseDateOrDateTime(endDate));
		}
		hql += " order by a.modifyTimestamp desc";

		return dao.fetchPageByHql(pageNo, pageSize, hql, paramList.toArray());
	}
	@Resource(name="baseDaoHibernate")
	private BaseDao<CmPhoto, String> dao;
}