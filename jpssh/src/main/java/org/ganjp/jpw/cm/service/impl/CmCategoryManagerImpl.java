/**
 * $Id: CmCategoryManagerImpl.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
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
import java.util.ResourceBundle;

import javax.annotation.Resource;

import org.ganjp.jpw.cm.model.CmCategory;
import org.ganjp.jpw.cm.service.CmCategoryManager;
import org.ganjp.jpw.core.Config;
import org.ganjp.jpw.core.Const;
import org.ganjp.jpw.core.dao.BaseDao;
import org.ganjp.jpw.core.model.PropertyFilter;
import org.ganjp.jpw.core.service.BaseManager;
import org.ganjp.jpw.core.util.HtmlUtil;
import org.ganjp.jpw.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>CmCategoryManagerImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service 
public class CmCategoryManagerImpl extends BaseManager implements CmCategoryManager {
	
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmCategory</p>
	 * 
	 * @param CmCategory
	 */
	@Transactional
	public void save(CmCategory cmCategory) {
		dao.save(cmCategory);
	}

	/**
	 * <p>update new cmCategory</p>
	 * 
	 * @param CmCategory
	 */
	@Transactional
	public void update(CmCategory cmCategory) {
		dao.update(cmCategory);
	}
	/**
	 * <p>save or update new cmCategory</p>
	 * 
	 * @param CmCategory
	 */
	@Transactional
	public void saveOrUpdate(CmCategory cmCategory) {
		dao.saveOrUpdate(cmCategory);
	}
		
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmCategory</p>
	 * 
	 * @param cmCategory the cmCategory must be from session or transient object that has primary key attribute
	 */
	@Transactional
	public void delete(CmCategory cmCategory) {
		dao.delete(cmCategory);
	}
	/**
	 * <p>delete a cmCategory by primary key</p>
	 * 
	 * @param PK
	 */
	@Transactional
	public void delete(final String pk) {
		dao.delete(CmCategory.class, pk);
	}
	/**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
	@Transactional
	public void batchDelete(final String pks) {
		String hql = "delete from CmCategory where categoryId in (" + StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
	}	
	//-------------------------------------------   find   ------------------------------------------
	//------------return CmCategory
	/**
	 * <p>get CmCategory object by primary key</p>
	 * 
	 * @param PK
	 * @return CmCategory
	 */
	@Transactional
	public CmCategory getCmCategoryById(final String id) {
		CmCategory cmCategory = dao.findById(CmCategory.class, id);
		return cmCategory;
	}
	
	/**
	 * <p>get all CmCategory objects</p>
	 *
	 * @return List<CmCategory>
	 */
	@Transactional
	public List<CmCategory> getCmCategorys() {
		return dao.findAllWithOrder(CmCategory.class, "modifyTimestamp", false);
	}
	
	/**
	 * <p>get all CmCategory objects and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmCategory>
	 */
	@Transactional
	public List<CmCategory> getCmCategorysWithOrder(String orderBy, boolean isAsc)  {
		return dao.findAllWithOrder(CmCategory.class, orderBy, isAsc);
	}
	
	/**
	 * <p>get CmCategory objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmCategory>
	 */
	@Transactional
	public List<CmCategory> getCmCategorysByField(final String fieldName, final Object value) {
		return dao.findByField(CmCategory.class, fieldName, value, "displayNo", true);
	}

	/**
	 * <p>getAllCategoryHtml</p>
	 * 
	 * @param lang
	 * @param jqmTheme
	 * @return
	 */
	@Transactional
	public String getAllCategoryHtml(final String lang, final String jqmTheme) {
		ResourceBundle i18nMessage = ResourceBundle.getBundle(Const.MESSAGE_BUNDLE_NAME, Config.getLocale(lang));
		String hql = "select categoryId, categoryCd, categoryName from CmCategory " +
				"where lang=? and categoryPid is null order by displayNo";
		List<Object[]> categoryArrs = dao.findByHql(hql, lang);
		StringBuffer htmlSb = new StringBuffer("");
		htmlSb.append("<div data-role='collapsible-set'>");
		for (Object[] categoryArr : categoryArrs) {
			String categoryId = StringUtil.toString(categoryArr[0]);
			String categoryCd = StringUtil.toString(categoryArr[1]);
			String categoryName = StringUtil.toString(categoryArr[2]);
			
			List<CmCategory> cmCategorys1 = this.getCmCategorysByField("categoryPid", categoryId);
			int size = cmCategorys1.size() + 1;
			if (size<10) {
				categoryCd = categoryCd + "0" + size;
			} else {
				categoryCd = categoryCd + size;
			}
			String addUrl1 = "javascript:add(" + StringUtil.getStrWithQuote(categoryId,categoryCd) + ");";
			String editUrl1 = "javascript:edit('" + categoryId + "');";
			String deleteUrl1 = "javascript:confirmDelete('" + categoryId + "');";
			htmlSb.append("<div data-role='collapsible'>");
			htmlSb.append("  <h3>").append(categoryName).append("(").append(categoryArr[1]).append(")</h3>");
			htmlSb.append("  &nbsp;").append(HtmlUtil.getJqmAedBtn(addUrl1, editUrl1, deleteUrl1, jqmTheme, i18nMessage)).append("");
			htmlSb.append("  <div data-role='collapsible-set' style='margin-top:13px;'>");
			for (CmCategory cmCategory1 : cmCategorys1) {
				List<CmCategory> cmCategorys2 = this.getCmCategorysByField("categoryPid", cmCategory1.getCategoryId());
				size = cmCategorys2.size() + 1;
				if (size<10) {
					categoryCd = cmCategory1.getCategoryCd() + "0" + size;
				} else {
					categoryCd = cmCategory1.getCategoryCd() + size;
				}
				String addUrl2 = "javascript:add(" + StringUtil.getStrWithQuote(cmCategory1.getCategoryId(),categoryCd) + ");";
				String editUrl2 = "javascript:edit('" + cmCategory1.getCategoryId() + "');";
				String deleteUrl2 = "javascript:confirmDelete('" + cmCategory1.getCategoryId() + "');";
				
				htmlSb.append("<div data-role='collapsible'>");
				htmlSb.append("  <h3>").append(cmCategory1.getCategoryName()).append("(").append(cmCategory1.getCategoryCd()).append(")</h3>");
				htmlSb.append("  &nbsp;").append(HtmlUtil.getJqmAedBtn(addUrl2, editUrl2, deleteUrl2, jqmTheme, i18nMessage)).append("");
				htmlSb.append("  <div data-role='collapsible-set' style='margin-top:13px;'>");
				for (CmCategory cmCategory2 : cmCategorys2) {
					List<CmCategory> cmCategorys3 = this.getCmCategorysByField("categoryPid", cmCategory2.getCategoryId());
					size = cmCategorys3.size() + 1;
					if (size<10) {
						categoryCd = cmCategory2.getCategoryCd() + "0" + size;
					} else {
						categoryCd = cmCategory2.getCategoryCd() + size;
					}
					String addUrl3 = "javascript:add(" + StringUtil.getStrWithQuote(cmCategory2.getCategoryId(),categoryCd) + ");";
					String editUrl3 = "javascript:edit('" + cmCategory2.getCategoryId() + "');";
					String deleteUrl3 = "javascript:confirmDelete('" + cmCategory2.getCategoryId() + "');";
					
					htmlSb.append("<div data-role='collapsible'>");
					htmlSb.append("  <h3>").append(cmCategory2.getCategoryName()).append("(").append(cmCategory2.getCategoryCd()).append(")</h3>");
					htmlSb.append("  &nbsp;").append(HtmlUtil.getJqmAedBtn(addUrl3, editUrl3, deleteUrl3, jqmTheme, i18nMessage)).append("");
					htmlSb.append("  <div data-role='collapsible-set' style='margin-top:13px;'>");
					for (CmCategory cmCategory3 : cmCategorys3) {
						List<CmCategory> cmCategorys4 = this.getCmCategorysByField("categoryPid", cmCategory3.getCategoryId());
						size = cmCategorys4.size() + 1;
						if (size<10) {
							categoryCd = cmCategory3.getCategoryCd() + "0" + size;
						} else {
							categoryCd = cmCategory3.getCategoryCd() + size;
						}
						String addUrl4 = "javascript:add(" + StringUtil.getStrWithQuote(cmCategory3.getCategoryId(),categoryCd) + ");";
						String editUrl4 = "javascript:edit('" + cmCategory3.getCategoryId() + "');";
						String deleteUrl4 = "javascript:confirmDelete('" + cmCategory3.getCategoryId() + "');";
						
						htmlSb.append("<div data-role='collapsible'>");
						htmlSb.append("  <h3>").append(cmCategory3.getCategoryName()).append("(").append(cmCategory3.getCategoryCd()).append(")</h3>");
						htmlSb.append("  ").append(HtmlUtil.getJqmAedBtn(addUrl4, editUrl4, deleteUrl4, jqmTheme, i18nMessage)).append("");
						htmlSb.append("  <div data-role='collapsible-set' style='margin-top:13px;'>");
						for (CmCategory cmCategory4 : cmCategorys4) {
							List<CmCategory> cmCategorys5 = this.getCmCategorysByField("categoryPid", cmCategory4.getCategoryId());
							size = cmCategorys5.size() + 1;
							if (size<10) {
								categoryCd = cmCategory4.getCategoryCd() + "0" + size;
							} else {
								categoryCd = cmCategory4.getCategoryCd() + size;
							}
							String addUrl5 = "javascript:add(" + StringUtil.getStrWithQuote(cmCategory4.getCategoryId(),categoryCd) + ");";
							String editUrl5 = "javascript:edit('" + cmCategory4.getCategoryId() + "');";
							String deleteUrl5 = "javascript:confirmDelete('" + cmCategory4.getCategoryId() + "');";
							
							htmlSb.append("<div data-role='collapsible'>");
							htmlSb.append("  <h3>").append(cmCategory4.getCategoryName()).append("(").append(cmCategory4.getCategoryCd()).append(")</h3>");
							htmlSb.append("  &nbsp;").append(HtmlUtil.getJqmAedBtn(addUrl5, editUrl5, deleteUrl5, jqmTheme, i18nMessage)).append("");
							htmlSb.append("</div>");
						}
						htmlSb.append("  </div>");
						htmlSb.append("</div>");
					}
					htmlSb.append("  </div>");
					htmlSb.append("</div>");
				}
				htmlSb.append("  </div>");
				htmlSb.append("</div>");
			}
			htmlSb.append("  </div>");
			htmlSb.append("</div>");
		}
		htmlSb.append("</div>");
		return htmlSb.toString();
	}
	
	/**
	 * <p>getCmCategory</p>
	 * 
	 * @param categoryCd
	 * @param lang
	 * @return
	 */
	@Transactional
	public CmCategory getCmCategory(final String categoryCd, final String lang) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQS_categoryCd", categoryCd));
		filters.add(new PropertyFilter("EQS_lang", lang));
		List<CmCategory> cmCategorys = dao.findByPropertyFilterList(CmCategory.class, filters);
		if (cmCategorys!=null && !cmCategorys.isEmpty()) {
			return cmCategorys.get(0);
		} 
		return null;
	}
	
	/**
	 * <p>getCategoryId</p>
	 * 
	 * @param categoryCd
	 * @param lang
	 * @return
	 */
	@Transactional
	public String getCategoryId(final String categoryCd, final String lang) {
		CmCategory cmCategory = this.getCmCategory(categoryCd, lang);
		if (cmCategory==null) {
			return null;
		}
		return cmCategory.getCategoryId();
	}

	/**
	 * <p>getCategoryPidsBtnsMap</p>
	 * 
	 * @param categoryId
	 * @param baseUrl
	 * @return
	 */
	@Transactional
	public Map<String,String> getCategoryPidsBtnsMap(String categoryId, String baseUrl) {
		Map<String,String> map = new HashMap<String,String>();
		String categoryIds = "";
		if (StringUtil.isEmpty(categoryId)) {
			return map;
		}
		CmCategory cmCategory1 = this.getCmCategoryById(categoryId);
		CmCategory cmCategoryHome = cmCategory1;
		StringBuffer sb = new StringBuffer("<div data-role='controlgroup' data-type='horizontal' data-mini='true'>");
		if (StringUtil.isNotEmpty(cmCategory1.getCategoryPid())) {
			categoryIds = cmCategory1.getCategoryId();
			sb.append("<a href='").append(baseUrl).append(cmCategory1.getCategoryId())
				.append("' data-role='button' rel='external' data-icon='arrow-r' data-iconpos='right'>")
				.append(cmCategory1.getCategoryName()).append("</a>");
			CmCategory cmCategory2 = this.getCmCategoryById(cmCategory1.getCategoryPid());
			cmCategoryHome = cmCategory2;
			if (StringUtil.isNotEmpty(cmCategory2.getCategoryPid())) {
				categoryIds += "," + cmCategory2.getCategoryId();
				sb.append("<a href='").append(baseUrl).append(cmCategory2.getCategoryId())
				.append("' data-role='button' rel='external' data-icon='arrow-r' data-iconpos='right'>")
				.append(cmCategory2.getCategoryName()).append("</a>");
				CmCategory cmCategory3 = this.getCmCategoryById(cmCategory2.getCategoryPid());
				cmCategoryHome = cmCategory3;
				if (StringUtil.isNotEmpty(cmCategory3.getCategoryPid())) {
					categoryIds += "," + cmCategory3.getCategoryId();
					sb.append("<a href='").append(baseUrl).append(cmCategory3.getCategoryId())
					  .append("' data-role='button' rel='external' data-icon='arrow-r' data-iconpos='right'>")
					  .append(cmCategory3.getCategoryName()).append("</a>");
					CmCategory cmCategory4 = this.getCmCategoryById(cmCategory3.getCategoryPid());
					cmCategoryHome = cmCategory4;
					if (StringUtil.isNotEmpty(cmCategory4.getCategoryPid())) {
						categoryIds += "," + cmCategory4.getCategoryId();
						sb.append("<a href='").append(baseUrl).append(cmCategory4.getCategoryId())
						  .append("' data-role='button' rel='external' data-icon='arrow-r' data-iconpos='right'>")
						  .append(cmCategory4.getCategoryName()).append("</a>");
						CmCategory cmCategory5 = this.getCmCategoryById(cmCategory4.getCategoryPid());
						cmCategoryHome = cmCategory5;
						if (StringUtil.isNotEmpty(cmCategory5.getCategoryPid())) {
							categoryIds += "," + cmCategory5.getCategoryId();
							sb.append("<a href='").append(baseUrl).append(cmCategory5.getCategoryId())
							  .append("' data-role='button' rel='external' data-icon='arrow-r' data-iconpos='right'>")
							  .append(cmCategory5.getCategoryName()).append("</a>");
						}
					}
				}
			}
		}
		sb.append("<a href='").append(baseUrl).append(cmCategoryHome.getCategoryId())
		  .append("' data-role='button' rel='external' data-icon='home' data-iconpos='right'>")
		  .append(cmCategoryHome.getCategoryName()).append("</a>");
		sb.append("</div>");
		map.put("categoryIds", categoryIds);
		map.put("categoryBtns", sb.toString());
		return map;
	}
	
	/**
	 * <p>getCategoryMaps</p>
	 * 
	 * @param categoryPid
	 * @param ownRoleIds
	 * @return
	 */
	@Transactional
	public List<Map<String,String>> getCategoryMaps(String categoryPid, String ownRoleIds) {
		List<CmCategory> cmCategorys = this.getCmCategorysByField("categoryPid", categoryPid);
		if (cmCategorys!=null && !cmCategorys.isEmpty()) {
			List<Map<String,String>> list = new ArrayList<Map<String,String>>();
			for (CmCategory cmCategory : cmCategorys) {
				if (StringUtil.aCanAccessB(ownRoleIds, cmCategory.getRoleIds())) {
					Map<String,String> map = new HashMap<String,String>();
					map.put("categoryId", cmCategory.getCategoryId());
					map.put("categoryCd", cmCategory.getCategoryCd());
					map.put("categoryName", cmCategory.getCategoryName());
					map.put("endFlag", cmCategory.getEndFlag());
					list.add(map);
				}
			}
			return list;
		}
		return null;
	}
	
	
	/**
	 * <p>getCategorysForJqmCheck</p>
	 * 
	 * @param categoryCd
	 * @param lang
	 * @return
	 */
	@Transactional
	public String getCategorysForJqmCheck(String categoryCd, String lang) {
		StringBuffer sb = new StringBuffer();
		String hql = "select categoryId, categoryName from CmCategory where categoryCd like ? and lang=? order by categoryCd asc";
		List<Object[]> objArrs = dao.findByHql(hql, categoryCd+"%", lang);
		for (Object[] objectArr : objArrs) {
			sb.append(StringUtil.toString(objectArr[0])).append(":").append(StringUtil.toString(objectArr[1])).append(";");
		}
		return sb.toString();
	}

	/**
	 * <p>getCategoryJqmHtml</p>
	 * 
	 * @param activeCategoryId
	 * @param categoryCd
	 * @param url
	 * @param lang
	 * @param jqmTheme
	 * @param ownRoleIds
	 * @return
	 */
	@Transactional
	public String getCategoryJqmHtml(final String activeCategoryId, final String categoryCd, final String url, 
			final String lang, final String jqmTheme, String ownRoleIds) {
		StringBuffer htmlSb = new StringBuffer("");
		List<Map<String,String>> categoryFirstLevelMaps = this.getCategoryMaps(this.getCategoryId(categoryCd, lang), ownRoleIds);
		if (categoryFirstLevelMaps!=null && CmCategory.END_FLAG_YES.equals(
				StringUtil.toString(categoryFirstLevelMaps.get(0).get("endFlag")))) {
			htmlSb.append("  <ul data-role='listview' style='margin:5px 10px 0 10px;border-left:1px solid #b3b3b3; border-right: 1px solid #b3b3b3;'>");
			for (Map<String, String> categoryMap : categoryFirstLevelMaps) {
				String categoryId = categoryMap.get("categoryId");
				htmlSb.append("<li ");
				if (categoryId.equals(activeCategoryId)) {
					htmlSb.append("data-theme='").append(jqmTheme).append("'");
				}
				htmlSb.append("><a href=\"javascript:toPage('").append(url).append("&categoryCd=").append(categoryCd).
							append("&categoryId=").append(categoryId).append("');\">")
								.append(categoryMap.get("categoryName")).append("</a></li>");
			}
			htmlSb.append("  </ul>");
		} else {
			String activeCategoryPid = null;
			if (StringUtil.isNotEmpty(activeCategoryId)) {
				activeCategoryPid = this.getCmCategoryById(activeCategoryId).getCategoryPid();
			}
			
			Map<String,List<Map<String,String>>> categoryPidAndCategoryMap = this.getCategoryPidAndCategoryMaps(categoryCd, lang, ownRoleIds);
			htmlSb.append("<div data-role='collapsible-set' data-theme='").append("d").append("' data-content-theme='c'>");
			if (categoryFirstLevelMaps!=null) {
			for (Map<String, String> categoryFirstLevelMap : categoryFirstLevelMaps) {
				htmlSb.append("<div data-role='collapsible' ");
				if (categoryFirstLevelMap.get("categoryId").equals(activeCategoryPid)) {
					htmlSb.append("data-collapsed='false' ");
				}
				htmlSb.append(">  <h2>").append(categoryFirstLevelMap.get("categoryName")).append("</h2>");
				List<Map<String,String>> categoryMaps = categoryPidAndCategoryMap.get(categoryFirstLevelMap.get("categoryId"));
				if (categoryMaps!=null) {
					htmlSb.append("  <ul data-role='listview'>");
					for (Map<String, String> categoryMap : categoryMaps) {
						String categoryId = categoryMap.get("categoryId");
						htmlSb.append("<li ");
						if (categoryId.equals(activeCategoryId)) {
							htmlSb.append("data-theme='").append(jqmTheme).append("'");
						}
						htmlSb.append("><a href=\"javascript:toPage('").append(url).append("&categoryCd=").append(categoryCd).
									append("&categoryId=").append(categoryId).append("');\">")
										.append(categoryMap.get("categoryName")).append("</a></li>");
					}
					htmlSb.append("  </ul>");
				}
				htmlSb.append("</div>"); 
			}
			}
			htmlSb.append("</div>");
		}
		return htmlSb.toString();
	}

	/**
	 * <p>getCategoryPidAndCategoryMaps</p>
	 * 
	 * @param categoryPid
	 * @param ownRoleIds
	 * @return
	 */
	@Transactional
	public Map<String,List<Map<String,String>>> getCategoryPidAndCategoryMaps(String categoryCd, String lang, String ownRoleIds) {
		Map<String,List<Map<String,String>>> categoryPidAndCategoryMapss = new HashMap<String,List<Map<String,String>>>();
		String hql = "select categoryId, categoryPid, categoryCd, categoryName, roleIds from CmCategory " +
				"where categoryCd like ? and lang=? order by displayNo";
		List<Object[]> categoryArrs = dao.findByHql(hql, categoryCd+"%", lang);
		for (Object[] objArr : categoryArrs) {
			String roleIds = StringUtil.toString(objArr[4]);
			if (StringUtil.aCanAccessB(ownRoleIds, roleIds)) {
				String categoryPid = StringUtil.toString(objArr[1]);
				Map<String,String> map = new HashMap<String,String>();
				map.put("categoryId", StringUtil.toString(objArr[0]));
				map.put("categoryCd", StringUtil.toString(objArr[2]));
				map.put("categoryName", StringUtil.toString(objArr[3]));
				if (categoryPidAndCategoryMapss.containsKey(categoryPid)) {
					categoryPidAndCategoryMapss.get(categoryPid).add(map);
				} else {
					List<Map<String,String>> list = new ArrayList<Map<String,String>>();
					list.add(map);
					categoryPidAndCategoryMapss.put(categoryPid, list);
				}
			}
		}
		return categoryPidAndCategoryMapss;
	}
	
	/**
	 * <p>getCategoryCheckboxHtml</p>
	 * 
	 * @param categoryCd
	 * @param lang
	 * @param ownRoleIds
	 * @return
	 */
	@Transactional
	public String getCategoryCheckboxHtml(final String categoryCd, final String lang, String ownRoleIds) {
		StringBuffer htmlSb = new StringBuffer("");
		List<Map<String,String>> categoryFirstLevelMaps = this.getCategoryMaps(this.getCategoryId(categoryCd, lang), ownRoleIds);
		if (categoryFirstLevelMaps!=null) {
			Map<String,List<Map<String,String>>> categoryPidAndCategoryMap = this.getCategoryPidAndCategoryMaps(categoryCd, lang, ownRoleIds);
			htmlSb.append("<ul style=''>");
			for (Map<String, String> categoryFirstLevelMap : categoryFirstLevelMaps) {
				if (CmCategory.END_FLAG_YES.equals(StringUtil.toString(categoryFirstLevelMap.get("endFlag")))) {
					htmlSb.append("<li> <input type='checkbox' name='categories' id='").append(categoryFirstLevelMap.get("categoryId")).append("' value='")
						.append(categoryFirstLevelMap.get("categoryName")).append("' />").append("<label for='").append(categoryFirstLevelMap.get("categoryId")).append("'>")
						.append(categoryFirstLevelMap.get("categoryName")).append("</label>").append("</li>");
				} else {
					List<Map<String,String>> categorySecondLevelMaps = categoryPidAndCategoryMap.get(categoryFirstLevelMap.get("categoryId"));
					if (categorySecondLevelMaps==null) {
						htmlSb.append("<li> <input type='checkbox' name='categories' id='").append(categoryFirstLevelMap.get("categoryId")).append("' value='")
							.append(categoryFirstLevelMap.get("categoryName")).append("' />").append("<label for='").append(categoryFirstLevelMap.get("categoryId")).append("'>")
							.append(categoryFirstLevelMap.get("categoryName")).append("</label>").append("</li>");
					} else { 
						htmlSb.append("<input type='checkbox' name='categories' id='").append(categoryFirstLevelMap.get("categoryId")).append("' value='")
							.append(categoryFirstLevelMap.get("categoryName")).append("' />").append("<label for='").append(categoryFirstLevelMap.get("categoryId")).append("'>")
							.append(categoryFirstLevelMap.get("categoryName")).append("</label>");
						
						htmlSb.append("<input type='checkbox' id='expand").append(categoryFirstLevelMap.get("categoryId")).append("' /><label for='expand")
							.append(categoryFirstLevelMap.get("categoryId")).append("'>").append("-").append("</label>");
						htmlSb.append("<br/><ul style='display:none;' class='expand").append(categoryFirstLevelMap.get("categoryId")).append("'>");
						
						for (Map<String, String> categorySecondLevelMap : categorySecondLevelMaps) {
							if (CmCategory.END_FLAG_YES.equals(StringUtil.toString(categorySecondLevelMap.get("endFlag")))) {
								htmlSb.append("<li> <input type='checkbox' name='categories' id='").append(categorySecondLevelMap.get("categoryId")).append("' value='")
									.append(categorySecondLevelMap.get("categoryName")).append("' />").append("<label for='").append(categorySecondLevelMap.get("categoryId")).append("'>")
									.append(categorySecondLevelMap.get("categoryName")).append("</label>").append("</li>");
							} else {
								List<Map<String,String>> categoryThirdLevelMaps = categoryPidAndCategoryMap.get(categorySecondLevelMap.get("categoryId"));
								if (categoryThirdLevelMaps==null) {
									htmlSb.append("<li> <input type='checkbox' name='categories' id='").append(categorySecondLevelMap.get("categoryId")).append("' value='")
										.append(categorySecondLevelMap.get("categoryName")).append("' />").append("<label for='").append(categorySecondLevelMap.get("categoryId")).append("'>")
										.append(categorySecondLevelMap.get("categoryName")).append("</label>").append("</li>");
								} else {
									htmlSb.append("<input type='checkbox' name='categories' id='").append(categorySecondLevelMap.get("categoryId")).append("' value='")
										.append(categorySecondLevelMap.get("categoryName")).append("' />").append("<label for='").append(categorySecondLevelMap.get("categoryId")).append("'>")
										.append(categorySecondLevelMap.get("categoryName")).append("</label>");
									htmlSb.append("<ul>");
									for (Map<String, String> categoryThirdLevelMap : categoryThirdLevelMaps) {
										htmlSb.append("<li style='display:inline-block;'> <input type='checkbox' name='categories' id='")
											.append(categoryThirdLevelMap.get("categoryId")).append("' value='").append(categoryThirdLevelMap.get("categoryName")).append("' />")
											.append("<label for='").append(categoryThirdLevelMap.get("categoryId")).append("'>")
											.append(categoryThirdLevelMap.get("categoryName")).append("</label>").append("</li>");
									}
									htmlSb.append("</ul>");
								}
							}
						}
					    htmlSb.append("</ul>");
					}
				}
			}
			htmlSb.append("</ul>");
		}
		return htmlSb.toString();
	}

	@Resource(name="baseDaoHibernate")
	private BaseDao<CmCategory, String> dao;
}