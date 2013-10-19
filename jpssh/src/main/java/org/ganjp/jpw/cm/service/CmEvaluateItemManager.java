/**
 * $Id: CmEvaluateItemManager.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.service;

import java.util.List;
import java.util.Map;

import org.ganjp.jpw.cm.model.CmEvaluateItem;

/**
 * <p>CmEvaluateItemManager</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface CmEvaluateItemManager {
		//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmEvaluateItem</p>
	 * 
	 * @param T
	 */
	public void save(CmEvaluateItem cmEvaluateItem);
	/**
	 * <p>update new cmEvaluateItem</p>
	 * 
	 * @param T
	 */
	public void update(CmEvaluateItem cmEvaluateItem);
	/**
	 * <p>save or update new cmEvaluateItem</p>
	 * 
	 * @param T
	 */
	public void saveOrUpdate(CmEvaluateItem cmEvaluateItem);
	
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmEvaluateItem</p>
	 * 
	 * @param cmEvaluateItem the cmEvaluateItem must be from session or transient object that has primary key attribute
	 */
	public void delete(CmEvaluateItem cmEvaluateItem);
	/**
	 * <p>delete a cmEvaluateItem by primary key</p>
	 * 
	 * @param PK
	 */
	public void delete(final String pk);
	
	//-------------------------------------------   find   ------------------------------------------
	//------------return CmEvaluateItem
/**
	 * <p>get CmEvaluateItem object by primary key</p>
	 * 
	 * @param PK
	 * @return CmEvaluateItem
	 */
	public CmEvaluateItem getCmEvaluateItemById(final String id);
	
	/**
	 * <p>get all CmEvaluateItem objects</p>
	 *
	 * @return List<CmEvaluateItem>
	 */
	public List<CmEvaluateItem> getCmEvaluateItems();
	
	/**
	 * <p>get all CmEvaluateItem records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmEvaluateItem>
	 */
	public List<CmEvaluateItem> getCmEvaluateItemsWithOrder(String orderBy, boolean isAsc);
	
	/**
	 * <p>get CmEvaluateItem objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmEvaluateItem>
	 */
	public List<CmEvaluateItem> getCmEvaluateItemsByField(final String fieldName, final Object value);
	
	/**
	 * <p>getEvaluateTableIdAndItemMapss</p>
	 * 
	 * @return
	 */
	public Map<String,List<Map<String,String>>> getEvaluateTableIdAndItemMapss();
	
	/**
	 * <p>getEvaluateTableItemMaps</p>
	 * 
	 * @param evaluateTableId
	 * @return
	 */
	public List<Map<String,String>> getEvaluateTableItemMaps(String evaluateTableId);
	
	/**
	 * <p>deleteEvaluateItemsByTableId</p>
	 * 
	 * @param evaluateTableId
	 */
	public void deleteEvaluateItemsByTableId(final String evaluateTableId);
	
	/**
	 * <p>getItemPositionAndTableItemss</p>
	 * 
	 * @param evaluateTableId
	 * @return
	 */
	public Map<String, List<Map<String,String>>> getItemPositionAndTableItemss(String evaluateTableId);
	
	/**
	 * <p>getEvaluateTableItemHtml</p>
	 * 
	 * @param evaluateTableId
	 * @return
	 */
	public String getEvaluateTableItemHtml(String evaluateTableId);
	
	/**
	 * <p>getEvaluateTableItemHtml</p>
	 * 
	 * @param evaluateTableId
	 * @return
	 */
	public String getEvaluateTableItemRadioHtml(String evaluateTableId);
	
	/**
	 * <p>getEvaluateItemIds</p>
	 * 
	 * @param evaluateTableId
	 * @return
	 */
	public List<String> getEvaluateItemIds(String evaluateTableId);
	
	/**
	 * <p>getEvaluateTableItemRadioHtml</p>
	 * 
	 * @param evaluateTableId
	 * @return
	 */
	public String getEvaluateTableItemRadioHtml(String userId, String evaluateTableId);
	
	/**
	 * <p>getEvaluateItemMap</p>
	 * 
	 * @param evaluateTableId
	 * @return
	 */
	public Map<String, List<String>> getEvaluateItemMap(String evaluateTableId);
	
	/**
	 * <p>getEvaluateItemIdAndNameOptions</p>
	 * 
	 * @param evaluateTableIds
	 * @return
	 */
	public Map<String, List<String>> getEvaluateItemIdAndNameOptions(String evaluateTableIds);
}