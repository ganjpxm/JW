/**
 * $Id: CmEvaluateItemManagerImpl.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
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

import org.ganjp.jpw.bm.service.BmParamManager;
import org.ganjp.jpw.cm.model.CmEvaluateItem;
import org.ganjp.jpw.cm.model.CmEvaluateTable;
import org.ganjp.jpw.cm.service.CmEvaluateItemManager;
import org.ganjp.jpw.cm.service.CmEvaluateTableManager;
import org.ganjp.jpw.cm.service.CmUserEvaluateTableResultManager;
import org.ganjp.jpw.core.Const;
import org.ganjp.jpw.core.dao.BaseDao;
import org.ganjp.jpw.core.service.BaseManager;
import org.ganjp.jpw.core.util.DateUtil;
import org.ganjp.jpw.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service 
public class CmEvaluateItemManagerImpl extends BaseManager implements CmEvaluateItemManager {
	
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmEvaluateItem</p>
	 * 
	 * @param CmEvaluateItem
	 */
	@Transactional
	public void save(CmEvaluateItem cmEvaluateItem) {
		dao.save(cmEvaluateItem);
	}

	/**
	 * <p>update new cmEvaluateItem</p>
	 * 
	 * @param CmEvaluateItem
	 */
	@Transactional
	public void update(CmEvaluateItem cmEvaluateItem) {
		dao.update(cmEvaluateItem);
	}
	/**
	 * <p>save or update new cmEvaluateItem</p>
	 * 
	 * @param CmEvaluateItem
	 */
	@Transactional
	public void saveOrUpdate(CmEvaluateItem cmEvaluateItem) {
		dao.saveOrUpdate(cmEvaluateItem);
	}
		
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmEvaluateItem</p>
	 * 
	 * @param cmEvaluateItem the cmEvaluateItem must be from session or transient object that has primary key attribute
	 */
	@Transactional
	public void delete(CmEvaluateItem cmEvaluateItem) {
		dao.delete(cmEvaluateItem);
	}
	/**
	 * <p>delete a cmEvaluateItem by primary key</p>
	 * 
	 * @param PK
	 */
	@Transactional
	public void delete(final String pk) {
		dao.delete(CmEvaluateItem.class, pk);
	}
	
	//-------------------------------------------   find   ------------------------------------------
	//------------return CmEvaluateItem
	/**
	 * <p>get CmEvaluateItem object by primary key</p>
	 * 
	 * @param PK
	 * @return CmEvaluateItem
	 */
	@Transactional
	public CmEvaluateItem getCmEvaluateItemById(final String id) {
		CmEvaluateItem cmEvaluateItem = dao.findById(CmEvaluateItem.class, id);
		return cmEvaluateItem;
	}
	
	/**
	 * <p>get all CmEvaluateItem objects</p>
	 *
	 * @return List<CmEvaluateItem>
	 */
	@Transactional
	public List<CmEvaluateItem> getCmEvaluateItems() {
		return dao.findAll(CmEvaluateItem.class);
	}
	
	/**
	 * <p>get all CmEvaluateItem objects and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmEvaluateItem>
	 */
	@Transactional
	public List<CmEvaluateItem> getCmEvaluateItemsWithOrder(String orderBy, boolean isAsc)  {
		return dao.findAllWithOrder(CmEvaluateItem.class, orderBy, isAsc);
	}
	
	/**
	 * <p>get CmEvaluateItem objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmEvaluateItem>
	 */
	@Transactional
	public List<CmEvaluateItem> getCmEvaluateItemsByField(final String fieldName, final Object value) {
		return dao.findByField(CmEvaluateItem.class, fieldName, value);
	}

	/**
	 * <p>getEvaluateTableIdAndItemMapss</p>
	 * 
	 * @return
	 */
	@Transactional
	public Map<String,List<Map<String,String>>> getEvaluateTableIdAndItemMapss() {
		Map<String,List<Map<String,String>>> map = new HashMap<String,List<Map<String,String>>>();
		List<CmEvaluateItem> cmEvaluateItems = this.getCmEvaluateItems();
		for (CmEvaluateItem cmEvaluateItem2 : cmEvaluateItems) {
			if (map.containsKey(cmEvaluateItem2.getEvaluateTableId())) {
				List<Map<String,String>> evaluateItemMaps = map.get(cmEvaluateItem2.getEvaluateTableId());
				Map<String,String> evaluateItemMap = new HashMap<String,String>();
				evaluateItemMap.put("itemName", cmEvaluateItem2.getItemName());
				evaluateItemMap.put("itemValueType", cmEvaluateItem2.getItemValueType());
				evaluateItemMaps.add(evaluateItemMap);
			} else {
				List<Map<String,String>> evaluateItemMaps = new ArrayList<Map<String,String>>();
				Map<String,String> evaluateItemMap = new HashMap<String,String>();
				evaluateItemMap.put("itemName", cmEvaluateItem2.getItemName());
				evaluateItemMap.put("itemValueType", cmEvaluateItem2.getItemValueType());
				evaluateItemMaps.add(evaluateItemMap);
				map.put(cmEvaluateItem2.getEvaluateTableId(), evaluateItemMaps);
			}
		}
		return map;
	}
	
	/**
	 * <p>getEvaluateTableItemMaps</p>
	 * 
	 * @param evaluateTableId
	 * @return
	 */
	@Transactional
	public List<Map<String,String>> getEvaluateTableItemMaps(String evaluateTableId) {
		List<Map<String,String>> evaluateItemMaps = new ArrayList<Map<String,String>>();
		List<CmEvaluateItem> cmEvaluateItems = dao.findByField(CmEvaluateItem.class, "evaluateTableId", evaluateTableId, "itemOrder", true);
		for (CmEvaluateItem cmEvaluateItem2 : cmEvaluateItems) {
			Map<String,String> evaluateItemMap = new HashMap<String,String>();
			evaluateItemMap.put("evaluateItemId", cmEvaluateItem2.getEvaluateItemId());
			evaluateItemMap.put("itemName", cmEvaluateItem2.getItemName());
			evaluateItemMap.put("itemOptions", cmEvaluateItem2.getItemOptions());
			evaluateItemMaps.add(evaluateItemMap);
		}
		return evaluateItemMaps;
	}

	/**
	 * <p>getItemPositionAndTableItemss</p>
	 * 
	 * @param evaluateTableId
	 * @return
	 */
	@Transactional
	public Map<String, List<Map<String,String>>> getItemPositionAndTableItemss(String evaluateTableId) {
		Map<String, List<Map<String,String>>> map = new HashMap<String, List<Map<String,String>>>();
		Map<String,String> paramIdAndCds = bmParamManager.getParamIdAndCds(Const.PARAM_TYPE_EVALUATE_ITEM_VALUE_TYPE);
		List<CmEvaluateItem> cmEvaluateItems = dao.findByField(CmEvaluateItem.class, "evaluateTableId", evaluateTableId, "itemOrder", true);
		for (CmEvaluateItem cmEvaluateItem2 : cmEvaluateItems) {
			Map<String,String> evaluateItemMap = new HashMap<String,String>();
			evaluateItemMap.put("evaluateItemId", cmEvaluateItem2.getEvaluateItemId());
			evaluateItemMap.put("itemName", cmEvaluateItem2.getItemName());
			evaluateItemMap.put("itemValueType", cmEvaluateItem2.getItemValueType());
			evaluateItemMap.put("itemValueTypeId", cmEvaluateItem2.getItemValueTypeId());
			evaluateItemMap.put("itemValueTypeCd", paramIdAndCds.get(cmEvaluateItem2.getItemValueTypeId()));
			evaluateItemMap.put("itemWidth", cmEvaluateItem2.getItemWidth());
			evaluateItemMap.put("itemOrder", cmEvaluateItem2.getItemOrder().toString());
			if (map.containsKey(cmEvaluateItem2.getItemPosition())) {
				map.get(cmEvaluateItem2.getItemPosition()).add(evaluateItemMap);
			} else {
				List<Map<String,String>> evaluateItemMaps = new ArrayList<Map<String,String>>();
				evaluateItemMaps.add(evaluateItemMap);
				map.put(cmEvaluateItem2.getItemPosition(), evaluateItemMaps);
			}
		}
		return map;
	}
	
	/**
	 * <p>deleteEvaluateItemsByTableId</p>
	 * 
	 * @param evaluateTableId
	 */
	@Transactional
	public void deleteEvaluateItemsByTableId(final String evaluateTableId) {
		String hql = "delete from CmEvaluateItem where evaluateTableId=?";
		dao.batchExecute(hql, evaluateTableId);
	}

	/**
	 * <p>getEvaluateTableItemHtml</p>
	 * 
	 * @param evaluateTableId
	 * @return
	 */
	@Transactional
	public String getEvaluateTableItemHtml(String evaluateTableId) {
		StringBuffer htmlSb = new StringBuffer("<div>"); 
		List<Map<String,String>> itemMaps = this.getEvaluateTableItemMaps(evaluateTableId);
		htmlSb.append("<ul style='font-size:16px;margin-top:20px;'>");
		for (int i=0;i<itemMaps.size();i++) {
			Map<String,String> map = itemMaps.get(i);
			htmlSb.append(i+1).append(".").append(map.get("itemName")).append("<ul>");
			String itemOptions = map.get("itemOptions");
			if (StringUtil.isNotEmpty(itemOptions)) {
				String[] itemOptionArr = itemOptions.split(";");
				for (int j=0; j<itemOptionArr.length; j++) {
					String[] valueArr = itemOptionArr[j].split(":");
					if (valueArr.length==2) {
						htmlSb.append("<li style='line-height:30px;font-size:14px;'>").append(valueArr[0]).append("(").append(valueArr[1]).append(")</li>");//.append(StringUtil.getABC(j+1)).append(".")
					}
				}
				
			}
			htmlSb.append("</ul>");
		}
		htmlSb.append("</ul>");
		htmlSb.append("</div>");
		return htmlSb.toString();
	}

	/**
	 * <p>getEvaluateTableItemRadioHtml</p>
	 * 
	 * @param evaluateTableId
	 * @return
	 */
	@Transactional
	public String getEvaluateTableItemRadioHtml(String userId, String evaluateTableId) {
		String html = "";
		if (StringUtil.isNotEmpty(userId)) {
			Map<String,String> userEvaluateResultMap = cmUserEvaluateTableResultManager.getUserEvaluateResultMap(userId, evaluateTableId);
			if (userEvaluateResultMap==null) {
				html = this.getEvaluateTableItemRadioHtml(evaluateTableId);
			} else {
				html = "You have filled up the survey in <span style='color:red'>" + userEvaluateResultMap.get("evaluateDate") + 
						"</span>, Your avg score is <span style='color:red'>" + userEvaluateResultMap.get("score") + "<span>";
			}
		} else {
			html = this.getEvaluateTableItemRadioHtml(evaluateTableId);
		}
		
		return html;
	}
	
	/**
	 * <p>getEvaluateTableItemRadioHtml</p>
	 * 
	 * @param evaluateTableId
	 * @return
	 */
	@Transactional
	public String getEvaluateTableItemRadioHtml(String evaluateTableId) {
		StringBuffer htmlSb = new StringBuffer("<div>"); 
		CmEvaluateTable cmEvaluateTable = cmEvaluateTableManager.getCmEvaluateTableById(evaluateTableId);
		List<Map<String,String>> itemMaps = this.getEvaluateTableItemMaps(evaluateTableId);
		htmlSb.append("<div style='text-align:center;font-size:18px;font-weight:bold;'>")
		.append(cmEvaluateTable.getTableName()).append("<br/> <span class='fs1'> (").append(DateUtil.getDateString(cmEvaluateTable.getStartDate()))
		.append("-").append(DateUtil.getDateString(cmEvaluateTable.getEndDate())).append(")").append("</span></div>");
		for (int i=0;i<itemMaps.size();i++) {
			Map<String,String> map = itemMaps.get(i);
			htmlSb.append("<div style='margin:20px 0px;'>").append(i+1).append(".").append(map.get("itemName")).append("</div>");
			String itemOptions = map.get("itemOptions");
			if (StringUtil.isNotEmpty(itemOptions)) {
				String[] itemOptionArr = itemOptions.split(";");
				htmlSb.append("<div style='margin:10px;'>");
				for (int j=0; j<itemOptionArr.length; j++) {
					String[] valueArr = itemOptionArr[j].split(":");
					if (valueArr.length==2) {
						String id = map.get("evaluateItemId")+j;
						  
						htmlSb.append("<label style='float:left;margin-left:10px;'><input type='radio' style='float:left;' onclick='clickRadio(&#34;").append(map.get("evaluateItemId")).append("&#34;);' id='").append(id)
							.append("' name='").append(map.get("evaluateItemId")).append("' value='").append(j).append(":")
							.append(valueArr[1]).append("'/> &nbsp;&nbsp;").append(valueArr[0]).append("</label>");//.append(StringUtil.getABC(j+1)).append(". ")
					}
				}
				htmlSb.append("</div>");
				htmlSb.append("<div class='clearfix'></div>");
			}
		}
		htmlSb.append("</div>");
		return htmlSb.toString();
	}
	
	/**
	 * <p>getEvaluateItemIds</p>
	 * 
	 * @param evaluateTableId
	 * @return
	 */
	@Transactional
	public List<String> getEvaluateItemIds(String evaluateTableId) {
		String hql = "select evaluateItemId from CmEvaluateItem where evaluateTableId=? order by itemOrder asc";
		List<String> evaluateItemIds = dao.findByHql(hql, evaluateTableId);
		return evaluateItemIds;
	}
	
	/**
	 * <p>getEvaluateItemMap</p>
	 * 
	 * @param evaluateTableId
	 * @return
	 */
	@Transactional
	public Map<String, List<String>> getEvaluateItemMap(String evaluateTableId) {
		String hql = "select evaluateItemId, itemName, itemOptions from CmEvaluateItem where evaluateTableId=? order by itemOrder asc";
		List<Object[]> objArrs = dao.findByHql(hql, evaluateTableId);
		List<String> itemIds = new ArrayList<String>();
		List<String> itemNames = new ArrayList<String>();
		List<String> itemOptions = new ArrayList<String>();
		for (Object[] objArr : objArrs) {
			itemIds.add(StringUtil.toString(objArr[0]));
			itemNames.add(StringUtil.toString(objArr[1]));
			itemOptions.add(StringUtil.toString(objArr[2]));
		}
		Map<String, List<String>> map = new HashMap<String,List<String>>();
		map.put("itemIds", itemIds);
		map.put("itemNames", itemNames);
		map.put("itemOptions", itemOptions);
		return map;
	}
	
	/**
	 * <p>getEvaluateItemIdAndNameOptions</p>
	 * 
	 * @param evaluateTableIds
	 * @return
	 */
	@Transactional
	public Map<String, List<String>> getEvaluateItemIdAndNameOptions(String evaluateTableIds) {
		Map<String, List<String>> map = new HashMap<String,List<String>>();
		String hql = "select evaluateItemId, itemName, itemOptions from CmEvaluateItem where evaluateTableId in (" + StringUtil.getStrWithQuote(evaluateTableIds) + ")";
		List<Object[]> objArrs = dao.findByHql(hql);
		for (Object[] objArr : objArrs) {
			List<String> list = new ArrayList<String>();
			list.add(StringUtil.toString(objArr[1]));
			list.add(StringUtil.toString(objArr[2]));
			map.put(StringUtil.toString(objArr[0]), list);
		}
		return map;
	}
	
	@Resource(name="baseDaoHibernate")
	private BaseDao<CmEvaluateItem, String> dao;
	
	@Resource(name="bmParamManagerImpl")
	private BmParamManager bmParamManager;
	
	@Resource(name="cmUserEvaluateTableResultManagerImpl")
	private CmUserEvaluateTableResultManager cmUserEvaluateTableResultManager;

	@Resource(name="cmEvaluateTableManagerImpl")
	private CmEvaluateTableManager cmEvaluateTableManager;
}
