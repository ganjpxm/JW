/**
 * $Id: CmEvaluateTableManagerImpl.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Coss Project
 *
 */
package org.ganjp.jpw.cm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.ganjp.jpw.cm.model.CmEvaluateItem;
import org.ganjp.jpw.cm.model.CmEvaluateTable;
import org.ganjp.jpw.cm.model.CmUserEvaluateTableResult;
import org.ganjp.jpw.cm.service.CmEvaluateItemManager;
import org.ganjp.jpw.cm.service.CmEvaluateTableManager;
import org.ganjp.jpw.cm.service.CmUserEvaluateTableResultManager;
import org.ganjp.jpw.core.dao.BaseDao;
import org.ganjp.jpw.core.service.BaseManager;
import org.ganjp.jpw.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>CmEvaluateTableManagerImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service 
public class CmEvaluateTableManagerImpl extends BaseManager implements CmEvaluateTableManager {
	
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmEvaluateTable</p>
	 * 
	 * @param CmEvaluateTable
	 */
	@Transactional
	public void save(CmEvaluateTable cmEvaluateTable) {
		dao.save(cmEvaluateTable);
	}

	/**
	 * <p>update new cmEvaluateTable</p>
	 * 
	 * @param CmEvaluateTable
	 */
	@Transactional
	public void update(CmEvaluateTable cmEvaluateTable) {
		dao.update(cmEvaluateTable);
	}
	/**
	 * <p>save or update new cmEvaluateTable</p>
	 * 
	 * @param CmEvaluateTable
	 */
	@Transactional
	public void saveOrUpdate(CmEvaluateTable cmEvaluateTable) {
		dao.saveOrUpdate(cmEvaluateTable);
	}
		
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmEvaluateTable</p>
	 * 
	 * @param cmEvaluateTable the cmEvaluateTable must be from session or transient object that has primary key attribute
	 */
	@Transactional
	public void delete(CmEvaluateTable cmEvaluateTable) {
		dao.delete(cmEvaluateTable);
	}
	/**
	 * <p>delete a cmEvaluateTable by primary key</p>
	 * 
	 * @param PK
	 */
	@Transactional
	public void delete(final String pk) {
		dao.delete(CmEvaluateTable.class, pk);
	}
	/**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
	@Transactional
	public void batchDelete(final String pks) {
		String hql = "delete from CmEvaluateItem where evaluateTableId in (" + StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
		hql = "delete from CmEvaluateTable where evaluateTableId in (" + StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
	}	
	//-------------------------------------------   find   ------------------------------------------
	//------------return CmEvaluateTable
	/**
	 * <p>get CmEvaluateTable object by primary key</p>
	 * 
	 * @param PK
	 * @return CmEvaluateTable
	 */
	@Transactional
	public CmEvaluateTable getCmEvaluateTableById(final String id) {
		CmEvaluateTable cmEvaluateTable = dao.findById(CmEvaluateTable.class, id);
		return cmEvaluateTable;
	}
	
	/**
	 * <p>get all CmEvaluateTable objects</p>
	 *
	 * @return List<CmEvaluateTable>
	 */
	@Transactional
	public List<CmEvaluateTable> getCmEvaluateTables() {
		return dao.findAllWithOrder(CmEvaluateTable.class, "modifyTimestamp", false);
	}
	
	/**
	 * <p>get all CmEvaluateTable objects and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmEvaluateTable>
	 */
	@Transactional
	public List<CmEvaluateTable> getCmEvaluateTablesWithOrder(String orderBy, boolean isAsc)  {
		return dao.findAllWithOrder(CmEvaluateTable.class, orderBy, isAsc);
	}
	
	/**
	 * <p>get CmEvaluateTable objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmEvaluateTable>
	 */
	@Transactional
	public List<CmEvaluateTable> getCmEvaluateTablesByField(final String fieldName, final Object value) {
		return dao.findByField(CmEvaluateTable.class, fieldName, value, "modifyTimestamp", false);
	}
	
	/**
	 * <p>saveCmEvaluateTableAndItem</p>
	 * 
	 * @param cmEvaluateTable
	 * @param cmEvaluateItems
	 */
	@Transactional
	public void saveCmEvaluateTableAndItem(final CmEvaluateTable cmEvaluateTable, List<CmEvaluateItem> cmEvaluateItems) {
		dao.save(cmEvaluateTable);
		for (CmEvaluateItem cmEvaluateItem : cmEvaluateItems) {
			cmEvaluateItemManager.save(cmEvaluateItem);
		}
	}
	
	/**
	 * <p>updateCmEvaluateTableAndItem</p>
	 * 
	 * @param cmEvaluateTable
	 * @param cmEvaluateItems
	 */
	@Transactional
	public void updateCmEvaluateTableAndItem(final CmEvaluateTable cmEvaluateTable, List<CmEvaluateItem> cmEvaluateItems) {
		dao.update(cmEvaluateTable);
		List<CmUserEvaluateTableResult> userEvaluateTableResults = cmUserEvaluateTableResultManager.getCmUserEvaluateTableResultsByField("evaluateTableId", cmEvaluateTable.getEvaluateTableId());
		if (userEvaluateTableResults==null || userEvaluateTableResults.isEmpty()) {
			cmEvaluateItemManager.deleteEvaluateItemsByTableId(cmEvaluateTable.getEvaluateTableId());
			for (CmEvaluateItem cmEvaluateItem : cmEvaluateItems) {
				cmEvaluateItemManager.save(cmEvaluateItem);
			}
		}
	}

	/**
	 * <p>getCmEvaluateTablesByRoleIds</p>
	 * 
	 * @param ownRoleIds
	 * @return
	 */
	@Transactional
	public List<CmEvaluateTable> getCmEvaluateTablesByRoleIds(String ownRoleIds)  {
		List<CmEvaluateTable> cmEvaluateTables = dao.findAllWithOrder(CmEvaluateTable.class, "startDate", false); 
		if (cmEvaluateTables!=null && !cmEvaluateTables.isEmpty()) {
			for (int i=0; i<cmEvaluateTables.size(); i++) {
				if (!StringUtil.aCanAccessB(ownRoleIds, cmEvaluateTables.get(i).getRoleIds())) {
					cmEvaluateTables.remove(i);
					--i;
				}
			}
		}
		return cmEvaluateTables;
	}
	
	@Resource(name="cmEvaluateItemManagerImpl")
	private CmEvaluateItemManager cmEvaluateItemManager;

	@Resource(name="baseDaoHibernate")
	private BaseDao<CmEvaluateTable, String> dao;
	
	@Resource(name="cmUserEvaluateTableResultManagerImpl")
	private CmUserEvaluateTableResultManager cmUserEvaluateTableResultManager;
}