/**
 * $Id: CmEvaluateTableManager.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.service;

import java.util.List;

import org.ganjp.jpw.cm.model.CmEvaluateItem;
import org.ganjp.jpw.cm.model.CmEvaluateTable;

/**
 * <p>CmEvaluateTableManager</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface CmEvaluateTableManager {
		//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmEvaluateTable</p>
	 * 
	 * @param T
	 */
	public void save(CmEvaluateTable cmEvaluateTable);
	/**
	 * <p>update new cmEvaluateTable</p>
	 * 
	 * @param T
	 */
	public void update(CmEvaluateTable cmEvaluateTable);
	/**
	 * <p>save or update new cmEvaluateTable</p>
	 * 
	 * @param T
	 */
	public void saveOrUpdate(CmEvaluateTable cmEvaluateTable);
	
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmEvaluateTable</p>
	 * 
	 * @param cmEvaluateTable the cmEvaluateTable must be from session or transient object that has primary key attribute
	 */
	public void delete(CmEvaluateTable cmEvaluateTable);
	/**
	 * <p>delete a cmEvaluateTable by primary key</p>
	 * 
	 * @param PK
	 */
	public void delete(final String pk);
	
	/**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
	public void batchDelete(final String pks);
	
	//-------------------------------------------   find   ------------------------------------------
	//------------return CmEvaluateTable
/**
	 * <p>get CmEvaluateTable object by primary key</p>
	 * 
	 * @param PK
	 * @return CmEvaluateTable
	 */
	public CmEvaluateTable getCmEvaluateTableById(final String id);
	
	/**
	 * <p>get all CmEvaluateTable objects</p>
	 *
	 * @return List<CmEvaluateTable>
	 */
	public List<CmEvaluateTable> getCmEvaluateTables();
	
	/**
	 * <p>get all CmEvaluateTable records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmEvaluateTable>
	 */
	public List<CmEvaluateTable> getCmEvaluateTablesWithOrder(String orderBy, boolean isAsc);
	
	/**
	 * <p>get CmEvaluateTable objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmEvaluateTable>
	 */
	public List<CmEvaluateTable> getCmEvaluateTablesByField(final String fieldName, final Object value);
	
	/**
	 * <p>saveCmEvaluateTableAndItem</p>
	 * 
	 * @param cmEvaluateTable
	 * @param cmEvaluateItems
	 */
	public void saveCmEvaluateTableAndItem(final CmEvaluateTable cmEvaluateTable, List<CmEvaluateItem> cmEvaluateItems);
	
	/**
	 * <p>updateCmEvaluateTableAndItem</p>
	 * 
	 * @param cmEvaluateTable
	 * @param cmEvaluateItems
	 */
	public void updateCmEvaluateTableAndItem(final CmEvaluateTable cmEvaluateTable, List<CmEvaluateItem> cmEvaluateItems);
	
	/**
	 * <p>getCmEvaluateTablesByRoleIds</p>
	 * 
	 * @param ownRoleIds
	 * @return
	 */
	public List<CmEvaluateTable> getCmEvaluateTablesByRoleIds(String ownRoleIds);
	
}