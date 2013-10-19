/**
 * $Id: CmUserEvaluateTableResultManager.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.service;

import java.util.List;
import java.util.Map;

import org.ganjp.jpw.cm.model.CmEvaluateTable;
import org.ganjp.jpw.cm.model.CmUserEvaluateTableResult;

/**
 * <p>CmUserEvaluateTableResultManager</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface CmUserEvaluateTableResultManager {
		//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmUserEvaluateTableResult</p>
	 * 
	 * @param T
	 */
	public void save(CmUserEvaluateTableResult cmUserEvaluateTableResult);
	/**
	 * <p>update new cmUserEvaluateTableResult</p>
	 * 
	 * @param T
	 */
	public void update(CmUserEvaluateTableResult cmUserEvaluateTableResult);
	/**
	 * <p>save or update new cmUserEvaluateTableResult</p>
	 * 
	 * @param T
	 */
	public void saveOrUpdate(CmUserEvaluateTableResult cmUserEvaluateTableResult);
	
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmUserEvaluateTableResult</p>
	 * 
	 * @param cmUserEvaluateTableResult the cmUserEvaluateTableResult must be from session or transient object that has primary key attribute
	 */
	public void delete(CmUserEvaluateTableResult cmUserEvaluateTableResult);
	/**
	 * <p>delete a cmUserEvaluateTableResult by primary key</p>
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
	//------------return CmUserEvaluateTableResult
/**
	 * <p>get CmUserEvaluateTableResult object by primary key</p>
	 * 
	 * @param PK
	 * @return CmUserEvaluateTableResult
	 */
	public CmUserEvaluateTableResult getCmUserEvaluateTableResultById(final String id);
	
	/**
	 * <p>get all CmUserEvaluateTableResult objects</p>
	 *
	 * @return List<CmUserEvaluateTableResult>
	 */
	public List<CmUserEvaluateTableResult> getCmUserEvaluateTableResults();
	
	/**
	 * <p>get all CmUserEvaluateTableResult records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmUserEvaluateTableResult>
	 */
	public List<CmUserEvaluateTableResult> getCmUserEvaluateTableResultsWithOrder(String orderBy, boolean isAsc);
	
	/**
	 * <p>get CmUserEvaluateTableResult objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmUserEvaluateTableResult>
	 */
	public List<CmUserEvaluateTableResult> getCmUserEvaluateTableResultsByField(final String fieldName, final Object value);
	
	/**
	 * <p>getUserEvaluateResultMap</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmUserEvaluateTableResult>
	 */
	public Map<String,String> getUserEvaluateResultMap(final String userId, final String evaluateTableId);
	
	/**
	 * <p>getUserIdAndEvaluateResultMap</p>
	 * 
	 * @param evaluateTableId
	 * @param roleIds
	 * @return
	 */
	public Map<String,Map<String,String>> getUserIdAndEvaluateResultMap(final String evaluateTableId, final String roleIds);
	
	/**
	 * <p>getGroupRoleIdNameScoreAndScoresUserNames</p>
	 * 
	 * 
	 * @param evaluateTableId
	 * @param ownRoleIds
	 * @return groupRoleId:groupRoleName:groupAvgScore=userScores;UserNames
	 * 4028d98139636d9d013963756ccc0024:Group1:1.00=1.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00;G1 User1,G1 User2,G1 User3,G1 User4,G1 User5,G1 User6,G1 User7,G1 user8}
	 */
	public List<Map<String,String>> getGroupRoleIdNameScoreAndScoresUserNames(final String evaluateTableId, final String ownRoleIds);
	
	/**
	 * <p>getEvaluateTableIdAndResults</p>
	 * 
	 * @param userId
	 * @return
	 */
	public Map<String, CmUserEvaluateTableResult> getEvaluateTableIdAndResults(final String userId);
	
	/**
	 * getAvgScoreView
	 * @param cmEvaluateTable
	 * @param ownRoleIds
	 * @return
	 */
	public List<Map<String,String>> getAvgScoreView(final CmEvaluateTable cmEvaluateTable, final String ownRoleIds);
	
	/**
	 * <p>getEveryAnswerChooseView</p>
	 * 
	 * 
	 * @param cmEvaluateTable
	 * @param ownRoleIds
	 * @return question : {questionName, answers, answersSel}
		  groupInfo: {fillupUsers, noFillupUsers}
	 */
	public Map<String, Object> getEveryAnswerChooseView(final CmEvaluateTable cmEvaluateTable, final String ownRoleIds);
	
	/**
	 * <p>delete</p>
	 * 
	 * @param userId
	 * @param evaluateTableId
	 */
	public void delete(final String userId, final String evaluateTableId);
}