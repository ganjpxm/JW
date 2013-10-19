/**
 * $Id: CmAudioManagerImpl.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
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

import org.ganjp.jpw.cm.model.CmAudio;
import org.ganjp.jpw.cm.service.CmAudioManager;
import org.ganjp.jpw.core.dao.BaseDao;
import org.ganjp.jpw.core.model.Page;
import org.ganjp.jpw.core.service.BaseManager;
import org.ganjp.jpw.core.util.DateUtil;
import org.ganjp.jpw.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>CmAudioManagerImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service 
public class CmAudioManagerImpl extends BaseManager implements CmAudioManager {
	
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmAudio</p>
	 * 
	 * @param CmAudio
	 */
	@Transactional
	public void save(CmAudio cmAudio) {
		dao.save(cmAudio);
	}

	/**
	 * <p>update new cmAudio</p>
	 * 
	 * @param CmAudio
	 */
	@Transactional
	public void update(CmAudio cmAudio) {
		dao.update(cmAudio);
	}
	/**
	 * <p>save or update new cmAudio</p>
	 * 
	 * @param CmAudio
	 */
	@Transactional
	public void saveOrUpdate(CmAudio cmAudio) {
		dao.saveOrUpdate(cmAudio);
	}
		
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmAudio</p>
	 * 
	 * @param cmAudio the cmAudio must be from session or transient object that has primary key attribute
	 */
	@Transactional
	public void delete(CmAudio cmAudio) {
		dao.delete(cmAudio);
	}
	/**
	 * <p>delete a cmAudio by primary key</p>
	 * 
	 * @param PK
	 */
	@Transactional
	public void delete(final String pk) {
		dao.delete(CmAudio.class, pk);
	}
	/**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
	@Transactional
	public void batchDelete(final String pks) {
		String hql = "delete from CmAudio where audioId in (" + StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
	}	
	//-------------------------------------------   find   ------------------------------------------
	//------------return CmAudio
	/**
	 * <p>get CmAudio object by primary key</p>
	 * 
	 * @param PK
	 * @return CmAudio
	 */
	@Transactional
	public CmAudio getCmAudioById(final String id) {
		CmAudio cmAudio = dao.findById(CmAudio.class, id);
		return cmAudio;
	}
	
	/**
	 * <p>get all CmAudio objects</p>
	 *
	 * @return List<CmAudio>
	 */
	@Transactional
	public List<CmAudio> getCmAudios() {
		return dao.findAllWithOrder(CmAudio.class, "modifyTimestamp", false);
	}
	
	/**
	 * <p>get all CmAudio objects and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmAudio>
	 */
	@Transactional
	public List<CmAudio> getCmAudiosWithOrder(String orderBy, boolean isAsc)  {
		return dao.findAllWithOrder(CmAudio.class, orderBy, isAsc);
	}
	
	/**
	 * <p>get CmAudio objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmAudio>
	 */
	@Transactional
	public List<CmAudio> getCmAudiosByField(final String fieldName, final Object value) {
		return dao.findByField(CmAudio.class, fieldName, value, "modifyTimestamp", false);
	}

	/**
	 * <p>getAudioMaps</p>
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
	public List<Map<String,String>> getAudioMaps(final String tag, final String lang, final String startDate, final String endDate, 
			final int pageNo, final int pageSize, String ownRoleIds) {
		String hql = "select a.audioId, a.audioName, a.audioFormat, a.title, a.thumbUrl, a.url, a.originUrl, a.originWebsite, a.tag, a.remark, " +
				"a.modifyTimestamp, a.roleIds, a.description " +
				"from CmAudio a where a.lang = ? ";
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
		Page<CmAudio> page = dao.fetchPageByHql(pageNo, pageSize, hql, paramList.toArray());
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
				map.put("audioId", StringUtil.toString(objArr[0]));
				map.put("audioName", StringUtil.toString(objArr[1]));
				map.put("audioFormat", StringUtil.toString(objArr[2]));
				map.put("title", StringUtil.toString(objArr[3]));
				map.put("thumbUrl", StringUtil.toString(objArr[4]));
				map.put("url", StringUtil.toString(objArr[5]));
				map.put("originUrl", StringUtil.toString(objArr[6]));
				map.put("originWebsite", StringUtil.toString(objArr[7]));
				map.put("tag", StringUtil.toString(objArr[8]));
				map.put("remark", StringUtil.convertForHtml(StringUtil.toString(objArr[9])));
				map.put("updateDate", DateUtil.getDateString((Date)objArr[10]));
				map.put("description", StringUtil.convertForHtml(StringUtil.toString(objArr[12])));
				list.add(map);
			}
		}
		
		return list;
	}
	
	/**
	 * <p>getAudioMaps</p>
	 * 
	 * @param tag
	 * @param lang
	 * @param ownRoleIds
	 * @return
	 */ 
	@Transactional
	public List<Map<String,String>> getAudioMaps(String tag, String lang, String ownRoleIds) {
		String hql = "select a.audioId, a.audioName, a.audioFormat, a.title, a.url, a.originUrl, a.originWebsite, a.tag, a.remark, a.roleIds " +
				"from CmAudio a where a.tag like ? and a.lang = ? order by a.displayNo";
		List<Object[]> objArrs =  dao.findByHql(hql, "%"+tag+"%", lang);
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		for (Object[] objArr : objArrs) {
			String roleIds = StringUtil.toString(objArr[9]);
			if (StringUtil.aCanAccessB(ownRoleIds, roleIds)) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("audioId", StringUtil.toString(objArr[0]));
				map.put("audioName", StringUtil.toString(objArr[1]));
				map.put("audioFormat", StringUtil.toString(objArr[2]));
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
	private BaseDao<CmAudio, String> dao;
}