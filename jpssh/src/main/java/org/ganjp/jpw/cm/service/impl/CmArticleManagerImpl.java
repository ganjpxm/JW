/**
 * $Id: CmArticleManagerImpl.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
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

import org.ganjp.jpw.am.service.AmRoleManager;
import org.ganjp.jpw.am.service.AmUserManager;
import org.ganjp.jpw.cm.model.CmArticle;
import org.ganjp.jpw.cm.service.CmArticleManager;
import org.ganjp.jpw.cm.service.CmArticleRemarkManager;
import org.ganjp.jpw.core.dao.BaseDao;
import org.ganjp.jpw.core.model.Page;
import org.ganjp.jpw.core.service.BaseManager;
import org.ganjp.jpw.core.util.DateUtil;
import org.ganjp.jpw.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * CmArticleManagerImpl
 * </p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service
public class CmArticleManagerImpl extends BaseManager implements CmArticleManager {

	// ------------------------------------------- Save Or Update
	// ------------------------------------------
	/**
	 * <p>
	 * save new cmArticle
	 * </p>
	 * 
	 * @param CmArticle
	 */
	@Transactional
	public void save(CmArticle cmArticle) {
		dao.save(cmArticle);
	}

	/**
	 * <p>
	 * update new cmArticle
	 * </p>
	 * 
	 * @param CmArticle
	 */
	@Transactional
	public void update(CmArticle cmArticle) {
		dao.update(cmArticle);
	}

	/**
	 * <p>
	 * save or update new cmArticle
	 * </p>
	 * 
	 * @param CmArticle
	 */
	@Transactional
	public void saveOrUpdate(CmArticle cmArticle) {
		dao.saveOrUpdate(cmArticle);
	}

	// ------------------------------------------- delete
	// ------------------------------------------
	/**
	 * <p>
	 * delete the cmArticle
	 * </p>
	 * 
	 * @param cmArticle
	 *            the cmArticle must be from session or transient object that
	 *            has primary key attribute
	 */
	@Transactional
	public void delete(CmArticle cmArticle) {
		dao.delete(cmArticle);
	}

	/**
	 * <p>
	 * delete a cmArticle by primary key
	 * </p>
	 * 
	 * @param PK
	 */
	@Transactional
	public void delete(final String pk) {
		dao.delete(CmArticle.class, pk);
	}

	/**
	 * <p>
	 * batchDelete
	 * </p>
	 * 
	 * @param pks
	 */
	@Transactional
	public void batchDelete(final String pks) {
		String hql = "delete from CmArticleCategory where articleId in ("
				+ StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
		hql = "delete from CmArticleRemark where articleId in ("
				+ StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
		hql = "delete from CmArticle where articleId in ("
				+ StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
	}

	// ------------------------------------------- find
	// ------------------------------------------
	// ------------return CmArticle
	/**
	 * <p>
	 * get CmArticle object by primary key
	 * </p>
	 * 
	 * @param PK
	 * @return CmArticle
	 */
	@Transactional
	public CmArticle getCmArticleById(final String id) {
		CmArticle cmArticle = dao.findById(CmArticle.class, id);
		return cmArticle;
	}

	/**
	 * <p>
	 * get all CmArticle objects
	 * </p>
	 * 
	 * @return List<CmArticle>
	 */
	@Transactional
	public List<CmArticle> getCmArticles() {
		return dao.findAllWithOrder(CmArticle.class, "modifyTimestamp", false);
	}

	/**
	 * <p>
	 * get all CmArticle objects and support order
	 * </p>
	 * 
	 * @param String
	 * @param boolean
	 * @return List<CmArticle>
	 */
	@Transactional
	public List<CmArticle> getCmArticlesWithOrder(String orderBy, boolean isAsc) {
		return dao.findAllWithOrder(CmArticle.class, orderBy, isAsc);
	}

	/**
	 * <p>
	 * get CmArticle objects by fieldName
	 * </p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmArticle>
	 */
	@Transactional
	public List<CmArticle> getCmArticlesByField(final String fieldName,
			final Object value) {
		return dao.findByField(CmArticle.class, fieldName, value,
				"modifyTimestamp", false);
	}

	/**
	 * <p>
	 * getCmArticles
	 * </p>
	 * 
	 * @param ownRoleIds
	 * @return
	 */
	@Transactional
	public List<CmArticle> getCmArticles(String ownRoleIds) {
		List<CmArticle> cmArticles = this.getCmArticles();
		for (int i = 0; i < cmArticles.size(); i++) {
			CmArticle cmArticle = cmArticles.get(i);
			if (!StringUtil.aCanAccessB(ownRoleIds, cmArticle.getRoleIds())) {
				cmArticles.remove(i);
				i--;
			}
		}
		return cmArticles;
	}

	/**
	 * <p>
	 * getArticleMaps
	 * </p>
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
	public List<Map<String, String>> getArticleMaps(final String tag,
			final String lang, final String startDate, final String endDate,
			final int pageNo, final int pageSize, String ownRoleIds) {
		return this.getArticleMapsWithPhoto(tag, lang, startDate, endDate,
				pageNo, pageSize, ownRoleIds, null);
	}

	/**
	 * <p>
	 * getArticleMaps
	 * </p>
	 * 
	 * @param tag
	 * @param lang
	 * @param startDate
	 *            dd/MM/yyyy
	 * @param endDate
	 *            dd/MM/yyyy
	 * @param pageNo
	 * @param pageSize
	 * @param ownRoleIds
	 * @param selGroupRoleIds
	 * @return
	 */
	@Transactional
	public List<Map<String, String>> getArticleMapsWithPhoto(final String tag,
			final String lang, final String startDate, final String endDate,
			final int pageNo, final int pageSize, String ownRoleIds,
			String selGroupRoleIds) {

		String hql = "select a.articleId, a.articleCd, a.title, a.summary, a.author, a.imageUrl, a.tag, a.modifyTimestamp, a.roleIds, b.userName, b.photoUrl, a.operatorId "
				+ "from CmArticle a, AmUser b where a.operatorId=b.userId ";
		List<Object> paramList = new ArrayList<Object>();
		if (StringUtil.isNotEmpty(lang)) {
			hql += " and a.lang = ? ";
			paramList.add(lang);
		}

		if (StringUtil.isEmpty(selGroupRoleIds)) {
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
		} else {
			if (StringUtil.isNotEmpty(ownRoleIds)) {
				if (selGroupRoleIds.indexOf("public") != -1) {
					hql += "and (a.roleIds = '' ";
				} else {
					hql += "and ( ";
				}

				String[] aArr = ownRoleIds.split(",");
				for (String tmp : aArr) {
					if (selGroupRoleIds.indexOf(tmp) != -1) {
						if (!hql.endsWith("( ")) {
							hql += " or ";
						}
						hql += " a.roleIds like '%" + tmp + "%'";
					}
				}
				hql += ") ";
				if (hql.endsWith("and ( )")) {
					hql = hql.replace("and ( )", " ");
				}
			} else {
				if (selGroupRoleIds.indexOf("public") != -1) {
					hql += " and a.roleIds =''  ";
				}
			}
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
				hql += " and (a.tag like '%" + tag + "%' or a.title like '%"
						+ tag + "%')";
			}
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
		Page<CmArticle> page = dao.fetchPageByHql(pageNo, pageSize, hql,
				paramList.toArray());
		List result = page.getResult();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> pageMap = new HashMap<String, String>();
		pageMap.put("totalCount", String.valueOf(page.getTotalCount()));
		pageMap.put("totalPages", String.valueOf(page.getTotalPages()));
		list.add(pageMap);
		StringBuffer articleIds = new StringBuffer("");
		for (int i = 0; i < result.size(); i++) {
			Object[] objArr = (Object[]) result.get(i);
			if (i == 0) {
				articleIds.append(StringUtil.toString(objArr[0]));
			} else {
				articleIds.append(",").append(StringUtil.toString(objArr[0]));
			}
		}
		Map<String, String> userIdAndNamePhotoUrlMap = amUserManager
				.getUserIdAndNamePhotoUrl();
		Map<String, List<String>> articleIdAndOperatorIdTimeRemarks = cmArticleRemarkManager
				.getArticleIdAndOperatorIdTimeRemarks(articleIds.toString());
		Map<String, String> roleIdAndNameMap = amRoleManager
				.getGroupRoleIdAndNames();
		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			Object[] objArr = (Object[]) iterator.next();
			String roleIds = StringUtil.toString(objArr[8]);
			String group = "Public";
			if (roleIds.length() == 32) {
				group = roleIdAndNameMap.get(roleIds);
			} else if (roleIds.length() > 32) {
				group = "";
				String[] roleIdArr = roleIds.split(",");
				for (String roleId : roleIdArr) {
					if (StringUtil.isEmpty(group)) {
						group = roleIdAndNameMap.get(roleId);
					} else {
						group += "," + roleIdAndNameMap.get(roleId);
					}
				}
			}

			String articleId = StringUtil.toString(objArr[0]);
			// if (StringUtil.aCanAccessB(ownRoleIds, roleIds)) {
			Map<String, String> map = new HashMap<String, String>();

			map.put("articleId", articleId);
			map.put("articleCd", StringUtil.toString(objArr[1]));
			map.put("title", StringUtil.toString(objArr[2]));
			map.put("summary", StringUtil.toString(objArr[3]));
			map.put("author", StringUtil.toString(objArr[4]));
			map.put("imageUrl", StringUtil.toString(objArr[5]));
			map.put("tag", StringUtil.toString(objArr[6]));
			map.put("updateDate", DateUtil.getTimeDateStr((Date) objArr[7]));
			map.put("operatorName", StringUtil.toString(objArr[9]));
			String operatorPhotoUrl = StringUtil.toString(objArr[10]);
			map.put("operatorPhotoUrl",
					StringUtil.isEmpty(operatorPhotoUrl) ? null
							: operatorPhotoUrl);
			map.put("operatorId", StringUtil.toString(objArr[11]));
			map.put("group", group);

			List<String> operatorIdTimeRemarks = articleIdAndOperatorIdTimeRemarks
					.get(articleId);
			if (operatorIdTimeRemarks != null) {
				StringBuffer sb = new StringBuffer();
				for (String operatorIdTimeRemark : operatorIdTimeRemarks) {
					String[] operatorIdTimeRemarkArr = operatorIdTimeRemark
							.split(":::");
					String userNamePhotoUrl = userIdAndNamePhotoUrlMap
							.get(operatorIdTimeRemarkArr[0]);
					String[] userNamePhotoUrlArr = null;
					if (userNamePhotoUrl != null) {
						userNamePhotoUrlArr = userNamePhotoUrl.split(":");
						sb.append(userNamePhotoUrlArr[0]).append("~")
								.append(userNamePhotoUrlArr[1]).append("~")
								.append(operatorIdTimeRemarkArr[1]).append("~")
								.append(operatorIdTimeRemarkArr[2])
								.append(";;;");
					}
				}
				map.put("remarks", sb.toString());
			}
			list.add(map);
		}

		return list;
	}

	/**
	 * <p>getCmArticles</p>
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
	public Page<CmArticle> getCmArticlePage(final String tag, final String lang, final String startDate, final String endDate, final int pageNo,
			final int pageSize, String ownRoleIds) {
		String hql = "select a from CmArticle a where 1=1 ";
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

	@Resource(name = "baseDaoHibernate")
	private BaseDao<CmArticle, String> dao;

	@Resource(name = "amUserManagerImpl")
	private AmUserManager amUserManager;

	@Resource(name = "amRoleManagerImpl")
	private AmRoleManager amRoleManager;

	@Resource(name = "cmArticleRemarkManagerImpl")
	private CmArticleRemarkManager cmArticleRemarkManager;

}