/**
 * $Id: CmFileManagerImpl.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
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

import org.ganjp.jpw.cm.model.CmFile;
import org.ganjp.jpw.cm.service.CmFileManager;
import org.ganjp.jpw.core.dao.BaseDao;
import org.ganjp.jpw.core.model.Page;
import org.ganjp.jpw.core.service.BaseManager;
import org.ganjp.jpw.core.util.DateUtil;
import org.ganjp.jpw.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>CmFileManagerImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service 
public class CmFileManagerImpl extends BaseManager implements CmFileManager {
	
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmFile</p>
	 * 
	 * @param CmFile
	 */
	@Transactional
	public void save(CmFile cmFile) {
		dao.save(cmFile);
	}

	/**
	 * <p>update new cmFile</p>
	 * 
	 * @param CmFile
	 */
	@Transactional
	public void update(CmFile cmFile) {
		dao.update(cmFile);
	}
	/**
	 * <p>save or update new cmFile</p>
	 * 
	 * @param CmFile
	 */
	@Transactional
	public void saveOrUpdate(CmFile cmFile) {
		dao.saveOrUpdate(cmFile);
	}
		
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmFile</p>
	 * 
	 * @param cmFile the cmFile must be from session or transient object that has primary key attribute
	 */
	@Transactional
	public void delete(CmFile cmFile) {
		dao.delete(cmFile);
	}
	/**
	 * <p>delete a cmFile by primary key</p>
	 * 
	 * @param PK
	 */
	@Transactional
	public void delete(final String pk) {
		dao.delete(CmFile.class, pk);
	}
	/**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
	@Transactional
	public void batchDelete(final String pks) {
		String hql = "delete from CmFile where fileId in (" + StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
	}	
	//-------------------------------------------   find   ------------------------------------------
	//------------return CmFile
	/**
	 * <p>get CmFile object by primary key</p>
	 * 
	 * @param PK
	 * @return CmFile
	 */
	@Transactional
	public CmFile getCmFileById(final String id) {
		CmFile cmFile = dao.findById(CmFile.class, id);
		return cmFile;
	}
	
	/**
	 * <p>get all CmFile objects</p>
	 *
	 * @return List<CmFile>
	 */
	@Transactional
	public List<CmFile> getCmFiles() {
		return dao.findAllWithOrder(CmFile.class, "modifyTimestamp", false);
	}
	
	/**
	 * <p>get all CmFile objects and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmFile>
	 */
	@Transactional
	public List<CmFile> getCmFilesWithOrder(String orderBy, boolean isAsc)  {
		return dao.findAllWithOrder(CmFile.class, orderBy, isAsc);
	}
	
	/**
	 * <p>get CmFile objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmFile>
	 */
	@Transactional
	public List<CmFile> getCmFilesByField(final String fieldName, final Object value) {
		return dao.findByField(CmFile.class, fieldName, value, "modifyTimestamp", false);
	}

	/**
	 * <p>getFileMaps</p>
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
	public List<Map<String,String>> getFileMaps(final String tag, final String lang, final String startDate, final String endDate, 
			final int pageNo, final int pageSize, String ownRoleIds) {
		String hql = "select a.fileId, a.fileName, a.title, a.thumbUrl, a.url, a.originUrl, a.originWebsite, a.tag, a.remark, a.modifyTimestamp, a.roleIds " +
				"from CmFile a where a.lang = ? ";
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
		Page<CmFile> page = dao.fetchPageByHql(pageNo, pageSize, hql, paramList.toArray());
		List result = page.getResult();
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Map<String,String> pageMap = new HashMap<String,String>();
		pageMap.put("totalCount", String.valueOf(page.getTotalCount()));
		pageMap.put("totalPages", String.valueOf(page.getTotalPages()));
		list.add(pageMap);
		
		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			Object[] objArr = (Object[]) iterator.next();
			String roleIds = StringUtil.toString(objArr[10]);
			if (StringUtil.aCanAccessB(ownRoleIds, roleIds)) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("fileId", StringUtil.toString(objArr[0]));
				map.put("fileName", StringUtil.toString(objArr[1]));
				map.put("title", StringUtil.toString(objArr[2]));
				map.put("thumbUrl", StringUtil.toString(objArr[3]));
				map.put("url", StringUtil.toString(objArr[4]));
				map.put("originUrl", StringUtil.toString(objArr[5]));
				map.put("originWebsite", StringUtil.toString(objArr[6]));
				map.put("tag", StringUtil.toString(objArr[7]));
				map.put("remark", StringUtil.toString(objArr[8]));
				map.put("updateDate", DateUtil.getDateString((Date)objArr[9]));
				list.add(map);
			}
		}
		
		return list;
	}
	
	/**
	 * <p>getFileMaps</p>
	 * 
	 * @param tag
	 * @param lang
	 * @param ownRoleIds
	 * @return
	 */
	@Transactional
	public List<Map<String,String>> getFileMaps(String tag, String lang, String ownRoleIds) {
		String hql = "select a.fileId, a.fileName, a.title, a.url, a.originUrl, a.originWebsite, a.tag, a.remark, a.roleIds " +
				"from CmFile a where a.tag like ? and a.lang = ? order by a.displayNo";
		List<Object[]> objArrs =  dao.findByHql(hql, "%"+tag+"%", lang);
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		for (Object[] objArr : objArrs) {
			String roleIds = StringUtil.toString(objArr[8]);
			if (StringUtil.aCanAccessB(ownRoleIds, roleIds)) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("fileId", StringUtil.toString(objArr[0]));
				map.put("fileName", StringUtil.toString(objArr[1]));
				map.put("title", StringUtil.toString(objArr[2]));
				map.put("url", StringUtil.toString(objArr[3]));
				map.put("originUrl", StringUtil.toString(objArr[4]));
				map.put("originWebsite", StringUtil.toString(objArr[5]));
				map.put("tag", StringUtil.toString(objArr[6]));
				map.put("remark", StringUtil.toString(objArr[7]));
				list.add(map);
			}
		}
		return list;
	}

	@Resource(name="baseDaoHibernate")
	private BaseDao<CmFile, String> dao;
}