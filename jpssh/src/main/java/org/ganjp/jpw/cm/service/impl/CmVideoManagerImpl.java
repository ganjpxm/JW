/**
 * $Id: CmVideoManagerImpl.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
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

import org.ganjp.jpw.cm.model.CmVideo;
import org.ganjp.jpw.cm.service.CmVideoManager;
import org.ganjp.jpw.core.dao.BaseDao;
import org.ganjp.jpw.core.model.Page;
import org.ganjp.jpw.core.service.BaseManager;
import org.ganjp.jpw.core.util.DateUtil;
import org.ganjp.jpw.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>CmVideoManagerImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service 
public class CmVideoManagerImpl extends BaseManager implements CmVideoManager {
	
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmVideo</p>
	 * 
	 * @param CmVideo
	 */
	@Transactional
	public void save(CmVideo cmVideo) {
		dao.save(cmVideo);
	}

	/**
	 * <p>update new cmVideo</p>
	 * 
	 * @param CmVideo
	 */
	@Transactional
	public void update(CmVideo cmVideo) {
		dao.update(cmVideo);
	}
	/**
	 * <p>save or update new cmVideo</p>
	 * 
	 * @param CmVideo
	 */
	@Transactional
	public void saveOrUpdate(CmVideo cmVideo) {
		dao.saveOrUpdate(cmVideo);
	}
		
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmVideo</p>
	 * 
	 * @param cmVideo the cmVideo must be from session or transient object that has primary key attribute
	 */
	@Transactional
	public void delete(CmVideo cmVideo) {
		dao.delete(cmVideo);
	}
	/**
	 * <p>delete a cmVideo by primary key</p>
	 * 
	 * @param PK
	 */
	@Transactional
	public void delete(final String pk) {
		dao.delete(CmVideo.class, pk);
	}
	/**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
	@Transactional
	public void batchDelete(final String pks) {
		String hql = "delete from CmVideo where videoId in (" + StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
	}	
	//-------------------------------------------   find   ------------------------------------------
	//------------return CmVideo
	/**
	 * <p>get CmVideo object by primary key</p>
	 * 
	 * @param PK
	 * @return CmVideo
	 */
	@Transactional
	public CmVideo getCmVideoById(final String id) {
		CmVideo cmVideo = dao.findById(CmVideo.class, id);
		return cmVideo;
	}
	
	/**
	 * <p>get all CmVideo objects</p>
	 *
	 * @return List<CmVideo>
	 */
	@Transactional
	public List<CmVideo> getCmVideos() {
		return dao.findAllWithOrder(CmVideo.class, "modifyTimestamp", false);
	}
	
	/**
	 * <p>get all CmVideo objects and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmVideo>
	 */
	@Transactional
	public List<CmVideo> getCmVideosWithOrder(String orderBy, boolean isAsc)  {
		return dao.findAllWithOrder(CmVideo.class, orderBy, isAsc);
	}
	
	/**
	 * <p>get CmVideo objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmVideo>
	 */
	@Transactional
	public List<CmVideo> getCmVideosByField(final String fieldName, final Object value) {
		return dao.findByField(CmVideo.class, fieldName, value, "modifyTimestamp", false);
	}

	/**
	 * <p>getVideoMaps</p>
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
	public List<Map<String,String>> getVideoMaps(final String tag, final String lang, final String startDate, final String endDate, 
			final int pageNo, final int pageSize, String ownRoleIds) {
		String hql = "select a.videoId, a.videoName, a.videoFormat, a.title, a.thumbUrl, a.url, a.originUrl, a.originWebsite, a.tag, a.remark, a.modifyTimestamp, a.roleIds " +
				"from CmVideo a where a.lang = ? ";
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
			paramList.add(DateUtil.parseDate(startDate));
		}
		if (StringUtil.isNotEmpty(endDate)) {
			hql += " and a.modifyTimestamp<=? "; 
			paramList.add(DateUtil.parseDate(endDate));
		}
		hql += " order by a.modifyTimestamp desc";
		Page<CmVideo> page = dao.fetchPageByHql(pageNo, pageSize, hql, paramList.toArray());
		List result = page.getResult();
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Map<String,String> pageMap = new HashMap<String,String>();
		pageMap.put("totalCount", String.valueOf(page.getTotalCount()));
		pageMap.put("totalPages", String.valueOf(page.getTotalPages()));
		list.add(pageMap);
		
		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			Object[] objArr = (Object[]) iterator.next();
			String roleIds = StringUtil.toString(objArr[11]);
			if (StringUtil.aCanAccessB(ownRoleIds, roleIds)) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("videoId", StringUtil.toString(objArr[0]));
				map.put("videoName", StringUtil.toString(objArr[1]));
				map.put("videoFormat", StringUtil.toString(objArr[2]));
				map.put("title", StringUtil.toString(objArr[3]));
				map.put("thumbUrl", StringUtil.toString(objArr[4]));
				map.put("url", StringUtil.toString(objArr[5]));
				map.put("originUrl", StringUtil.toString(objArr[6]));
				map.put("originWebsite", StringUtil.toString(objArr[7]));
				map.put("tag", StringUtil.toString(objArr[8]));
				map.put("remark", StringUtil.toString(objArr[9]));
				map.put("updateDate", DateUtil.getDateString((Date)objArr[10]));
				list.add(map);
			}
		}
		
		return list;
	}
	
	/**
	 * <p>getVideoMaps</p>
	 * 
	 * @param tag
	 * @param lang
	 * @param ownRoleIds
	 * @return
	 */ 
	@Transactional
	public List<Map<String,String>> getVideoMaps(String tag, String lang, String ownRoleIds) {
		String hql = "select a.videoId, a.videoName, a.videoFormat, a.title, a.url, a.originUrl, a.originWebsite, a.tag, a.remark, a.roleIds " +
				"from CmVideo a where a.tag like ? and a.lang = ? order by a.displayNo";
		List<Object[]> objArrs =  dao.findByHql(hql, "%"+tag+"%", lang);
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		for (Object[] objArr : objArrs) {
			String roleIds = StringUtil.toString(objArr[9]);
			if (StringUtil.aCanAccessB(ownRoleIds, roleIds)) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("videoId", StringUtil.toString(objArr[0]));
				map.put("videoName", StringUtil.toString(objArr[1]));
				map.put("videoFormat", StringUtil.toString(objArr[2]));
				map.put("title", StringUtil.toString(objArr[3]));
				map.put("url", StringUtil.toString(objArr[4]));
				map.put("originUrl", StringUtil.toString(objArr[5]));
				map.put("originWebsite", StringUtil.toString(objArr[6]));
				map.put("tag", StringUtil.toString(objArr[7]));
				map.put("remark", StringUtil.toString(objArr[8]));
				list.add(map);
			}
		}
		return list;
	}

	@Resource(name="baseDaoHibernate")
	private BaseDao<CmVideo, String> dao;
}