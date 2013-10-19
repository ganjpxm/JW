/**
 * $Id: CmUserEvaluateTableResultManagerImpl.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Coss Project
 *
 */
package org.ganjp.jpw.cm.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.map.ListOrderedMap;
import org.ganjp.jpw.am.model.AmRole;
import org.ganjp.jpw.am.model.AmUser;
import org.ganjp.jpw.am.service.AmRoleManager;
import org.ganjp.jpw.am.service.AmUserRoleManager;
import org.ganjp.jpw.bm.model.BmConfig;
import org.ganjp.jpw.bm.service.BmConfigManager;
import org.ganjp.jpw.cm.model.CmEvaluateTable;
import org.ganjp.jpw.cm.model.CmUserEvaluateTableResult;
import org.ganjp.jpw.cm.service.CmEvaluateItemManager;
import org.ganjp.jpw.cm.service.CmUserEvaluateTableResultManager;
import org.ganjp.jpw.core.dao.BaseDao;
import org.ganjp.jpw.core.service.BaseManager;
import org.ganjp.jpw.core.util.DateUtil;
import org.ganjp.jpw.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>CmUserEvaluateTableResultManagerImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service 
public class CmUserEvaluateTableResultManagerImpl extends BaseManager implements CmUserEvaluateTableResultManager {
	
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new cmUserEvaluateTableResult</p>
	 * 
	 * @param CmUserEvaluateTableResult
	 */
	@Transactional
	public void save(CmUserEvaluateTableResult cmUserEvaluateTableResult) {
		dao.save(cmUserEvaluateTableResult);
	}

	/**
	 * <p>update new cmUserEvaluateTableResult</p>
	 * 
	 * @param CmUserEvaluateTableResult
	 */
	@Transactional
	public void update(CmUserEvaluateTableResult cmUserEvaluateTableResult) {
		dao.update(cmUserEvaluateTableResult);
	}
	/**
	 * <p>save or update new cmUserEvaluateTableResult</p>
	 * 
	 * @param CmUserEvaluateTableResult
	 */
	@Transactional
	public void saveOrUpdate(CmUserEvaluateTableResult cmUserEvaluateTableResult) {
		dao.saveOrUpdate(cmUserEvaluateTableResult);
	}
		
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the cmUserEvaluateTableResult</p>
	 * 
	 * @param cmUserEvaluateTableResult the cmUserEvaluateTableResult must be from session or transient object that has primary key attribute
	 */
	@Transactional
	public void delete(CmUserEvaluateTableResult cmUserEvaluateTableResult) {
		dao.delete(cmUserEvaluateTableResult);
	}
	/**
	 * <p>delete a cmUserEvaluateTableResult by primary key</p>
	 * 
	 * @param PK
	 */
	@Transactional
	public void delete(final String pk) {
		dao.delete(CmUserEvaluateTableResult.class, pk);
	}
	/**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
	@Transactional
	public void batchDelete(final String pks) {
		String hql = "delete from CmUserEvaluateTableResult where userEvaluateTableResultId in (" + StringUtil.getStrWithQuote(pks) + ")";
		dao.batchExecute(hql);
	}
	
	/**
	 * <p>delete</p>
	 * 
	 * @param userId
	 * @param evaluateTableId
	 */
	@Transactional
	public void delete(final String userId, final String evaluateTableId) {
		String hql = "delete from CmUserEvaluateTableResult where userId=? and evaluateTableId = ?";
		dao.batchExecute(hql,userId,evaluateTableId);
	}
	
	//-------------------------------------------   find   ------------------------------------------
	//------------return CmUserEvaluateTableResult
	/**
	 * <p>get CmUserEvaluateTableResult object by primary key</p>
	 * 
	 * @param PK
	 * @return CmUserEvaluateTableResult
	 */
	@Transactional
	public CmUserEvaluateTableResult getCmUserEvaluateTableResultById(final String id) {
		CmUserEvaluateTableResult cmUserEvaluateTableResult = dao.findById(CmUserEvaluateTableResult.class, id);
		return cmUserEvaluateTableResult;
	}
	
	/**
	 * <p>get all CmUserEvaluateTableResult objects</p>
	 *
	 * @return List<CmUserEvaluateTableResult>
	 */
	@Transactional
	public List<CmUserEvaluateTableResult> getCmUserEvaluateTableResults() {
		return dao.findAllWithOrder(CmUserEvaluateTableResult.class, "modifyTimestamp", false);
	}
	
	/**
	 * <p>get all CmUserEvaluateTableResult objects and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<CmUserEvaluateTableResult>
	 */
	@Transactional
	public List<CmUserEvaluateTableResult> getCmUserEvaluateTableResultsWithOrder(String orderBy, boolean isAsc)  {
		return dao.findAllWithOrder(CmUserEvaluateTableResult.class, orderBy, isAsc);
	}
	
	/**
	 * <p>get CmUserEvaluateTableResult objects by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmUserEvaluateTableResult>
	 */
	@Transactional
	public List<CmUserEvaluateTableResult> getCmUserEvaluateTableResultsByField(final String fieldName, final Object value) {
		return dao.findByField(CmUserEvaluateTableResult.class, fieldName, value, "modifyTimestamp", false);
	}
	
	/**
	 * <p>getEvaluateTableIdAndResults</p>
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional
	public Map<String, CmUserEvaluateTableResult> getEvaluateTableIdAndResults(final String userId) {
		Map<String, CmUserEvaluateTableResult> map = new HashMap<String, CmUserEvaluateTableResult>();
		List<CmUserEvaluateTableResult> cmUserEvaluateTableResults = this.getCmUserEvaluateTableResultsByField("operatorId", userId);
		for (CmUserEvaluateTableResult cmUserEvaluateTableResult: cmUserEvaluateTableResults) {
			map.put(cmUserEvaluateTableResult.getEvaluateTableId(), cmUserEvaluateTableResult);
		}
		return map;
	}

	/**
	 * <p>getUserEvaluateResultMap</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<CmUserEvaluateTableResult>
	 */
	@Transactional
	public Map<String,String> getUserEvaluateResultMap(final String userId, final String evaluateTableId) {
		Map<String,String> map = null;
		String hql = "select userName, result, score, evaluateDate from CmUserEvaluateTableResult " +
				"where userId=? and evaluateTableId=?";
		List<Object[]> objArrs = dao.findByHql(hql, userId, evaluateTableId);
		if (objArrs!=null && !objArrs.isEmpty()) {
			Object[] objArr = objArrs.get(0);
			map = new HashMap<String,String>();
			map.put("userName", StringUtil.toString(objArr[0]));
			map.put("result", StringUtil.toString(objArr[1]));
			map.put("score", StringUtil.toString(objArr[2]));
			map.put("evaluateDate", DateUtil.getDateString((Date)objArr[3]));
		} 
		return map;
	}

	/**
	 * <p>getUserIdAndEvaluateResultMap</p>
	 * 
	 * @param evaluateTableId
	 * @param roleIds
	 * @return
	 */
	@Transactional
	public Map<String,Map<String,String>> getUserIdAndEvaluateResultMap(final String evaluateTableId, final String roleIds) {
		String hql = "select distinct(a.userId), a.userName, a.result, a.score, a.evaluateDate from CmUserEvaluateTableResult a, AmUserRole b " +
				"where a.userId=a.userId and a.evaluateTableId=? and b.roleId in (" + StringUtil.getStrWithQuote(roleIds) + ")" ;
		List<Object[]> objArrs = dao.findByHql(hql, evaluateTableId);
		Map<String,Map<String,String>> userIdAndEvaluateResultMap = new HashMap<String,Map<String,String>>();
		for (Object[] objArr: objArrs) {
			String userId = StringUtil.toString(objArr[0]);
			Map<String,String> map = new HashMap<String,String>();
			map.put("userName", StringUtil.toString(objArr[1]));
			map.put("result", StringUtil.toString(objArr[2]));
			map.put("score", StringUtil.toString(objArr[3]));
			map.put("evaluateDate", DateUtil.getDateString((Date)objArr[4]));
			userIdAndEvaluateResultMap.put(userId, map);
		} 
		return userIdAndEvaluateResultMap;
	}

	/**
	 * <p>getGroupRoleIdNameScoreAndScoresUserNames</p>
	 * 
	 * 
	 * @param evaluateTableId
	 * @param ownRoleIds
	 * @return groupRoleId:groupRoleName:groupAvgScore=userScores;UserNames
	 * 4028d98139636d9d013963756ccc0024:Group1:1.00=1.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00;G1 User1,G1 User2,G1 User3,G1 User4,G1 User5,G1 User6,G1 User7,G1 user8}
	 */
	@Transactional
	public List<Map<String,String>> getGroupRoleIdNameScoreAndScoresUserNames(final String evaluateTableId, final String ownRoleIds) {
		List<Map<String,String>> roleNameAndUserScores = new ArrayList<Map<String,String>>();
		List<String> groupRoleIdNames = amRoleManager.getGroupRoleIdNames(ownRoleIds);
		String groupRoleIds = "";
		for (String groupRoleIdName : groupRoleIdNames) {
			String[] groupRoleIdNameArr = groupRoleIdName.split(":");
			groupRoleIds += groupRoleIdNameArr[0] + ",";
		}
		Map<String,Map<String,String>> userIdAndEvaluateResultMap = this.getUserIdAndEvaluateResultMap(evaluateTableId, groupRoleIds);
		Map<String,List<String>> roleIdAndUserMap = amUserRoleManager.getRoleIdAndUserMaps(groupRoleIds);
		List<String> leaderUserIds = amUserRoleManager.getUserIds(AmRole.ROLE_ID_MANAGER);
		//1 Disagree:1; 2:2;  3 Neutral:3; 4:4; 5 Agree:5;
		Map<String,List<String>> itemMap = cmEvaluateItemManager.getEvaluateItemMap(evaluateTableId);
		List<String> itemIds = itemMap.get("itemIds");
		List<String> itemNames = itemMap.get("itemNames");
		List<String> itemOptions = itemMap.get("itemOptions");
		
		int allUserSize = 0;
		BigDecimal allAvgScore = new BigDecimal(0);
		
		String showUserScore = bmConfigManager.getValue(BmConfig.SHOW_USER_SURVEY_AVG_SCORE_FOR_LEADER);
		
		for (String groupRoleIdName : groupRoleIdNames) {
			BigDecimal[] qAveScoreArr = new BigDecimal[itemIds.size()];
			String[] groupRoleIdNameArr = groupRoleIdName.split(":");
			String roleId = groupRoleIdNameArr[0];
			String roleName = groupRoleIdNameArr[1];
			List<String> users = roleIdAndUserMap.get(roleId);
			StringBuffer userIdNameScoreSb = new StringBuffer();
			StringBuffer scoreSb = new StringBuffer();
			StringBuffer userNameSb = new StringBuffer();
			StringBuffer qNameSb = new StringBuffer();
			StringBuffer userWithScore = new StringBuffer();
			StringBuffer userWithOutScore = new StringBuffer();
			BigDecimal groupAvgScore = new BigDecimal(0);
			int groupUserSize = 0;
			for (String user : users) {
				String[] userArr = user.split(":");
				String userId = userArr[0];
				String userName = userArr[1];
				String userAlia = userArr[2];
				if (leaderUserIds.contains(userId)) {
				  //continue;	
				}
				Map<String,String> evaluateResultMap = userIdAndEvaluateResultMap.get(userId);
				userIdNameScoreSb.append(userId).append(":").append(userName).append(":");
				if (scoreSb.length()>1) {
					scoreSb.append(",");
					userNameSb.append(",");
				}
				userNameSb.append(userName);
				if (evaluateResultMap!=null && !evaluateResultMap.isEmpty()) {
					groupUserSize++;
					String score = evaluateResultMap.get("score");
					groupAvgScore = groupAvgScore.add(new BigDecimal(score));
					userIdNameScoreSb.append(score).append(";");
					scoreSb.append(score);
					userWithScore.append(userName);
					if ("on".equalsIgnoreCase(showUserScore)) {
						userWithScore.append("(<span class='red bold'>").append(score).append("</span>), ");
					}
					userWithScore.append(", ");
					String itemIdSelOrderScores = evaluateResultMap.get("result");
					String[] itemIdSelOrderScoreArr = itemIdSelOrderScores.split(";");
					Map<String, String> itemIdAndScoreMap = new HashMap<String,String>();
					for (String itemIdSelOrderScore : itemIdSelOrderScoreArr) {
						String[] valueArr = itemIdSelOrderScore.split(":");
						itemIdAndScoreMap.put(valueArr[0], valueArr[2]);
					}
					//402881b33d32ea87013d32fb258900aa:0:1;
					for (int k=0; k<itemIds.size(); k++) {
						String itemId = itemIds.get(k);
						BigDecimal itemScore = new BigDecimal(itemIdAndScoreMap.get(itemId));
						if (qAveScoreArr[k]==null) qAveScoreArr[k]=itemScore; 
						else qAveScoreArr[k] = qAveScoreArr[k].add(itemScore);
					}
				} else {
					userIdNameScoreSb.append("0").append(";");
					scoreSb.append("0.00");
					if (userAlia.indexOf("admin")==-1 && userAlia.indexOf("departmentLeader")==-1) {
						userWithOutScore.append(userName).append(", ");
					}
				}
			}
			if (groupUserSize>1) {
				groupAvgScore = groupAvgScore.divide(new BigDecimal(groupUserSize), 2, BigDecimal.ROUND_FLOOR);
				for (int i=0; i<qAveScoreArr.length; i++) {
					qAveScoreArr[i] = qAveScoreArr[i].divide(new BigDecimal(groupUserSize), 2, BigDecimal.ROUND_FLOOR);
				}
			} 
			if (groupUserSize>0) {
				allUserSize++;
				allAvgScore = allAvgScore.add(groupAvgScore);
			}
			Map<String,String> map = new HashMap<String,String>();
			StringBuffer qAveScoreSb = new StringBuffer();
			StringBuffer qFullNameAndSoreSb = new StringBuffer();
			for (int j= 0; j<qAveScoreArr.length; j++) {
				if (j>0) {
					qAveScoreSb.append(",");
					qNameSb.append(",");
					qFullNameAndSoreSb.append("<br/>");
				}
				if (qAveScoreArr[j]==null) {
					qAveScoreSb.append("0");
				} else {
					qAveScoreSb.append(qAveScoreArr[j].toString());
				}
				qNameSb.append((j+1));
				qFullNameAndSoreSb.append((j+1) + ". " +itemNames.get(j) + "(<span class='red bold'>" + (qAveScoreArr[j]==null?0:qAveScoreArr[j]) + "</span>)");
			}
			if (userWithScore.length()<1) userWithScore.append("none");
			if (userWithOutScore.length()<1) userWithOutScore.append("none");
			map.put(roleId + ":" + roleName + ":" + groupAvgScore, scoreSb.toString() + ";" + qAveScoreSb.toString() +  ";" + userNameSb + ";" + qNameSb + ";" + userWithScore + ";" + userWithOutScore + ";" + qFullNameAndSoreSb);//userIdNameScoreSb.toString()
			roleNameAndUserScores.add(map);
		}
		if (allUserSize>0) {
			allAvgScore = allAvgScore.divide(new BigDecimal(allUserSize), 2, BigDecimal.ROUND_FLOOR);
		}
		Map<String,String> map = new HashMap<String,String>();
		map.put("allAvgScore", allAvgScore.toString());
		roleNameAndUserScores.add(map);
		return roleNameAndUserScores;
	}
	
	/**
	 * <p>getEveryAnswerSumByGroupId</p>
	 * 
	 * 
	 * @param evaluateTableId
	 * @param ownRoleIds
	 * @return groupRoleId:groupRoleName:groupAvgScore=userScores;UserNames
	 * 4028d98139636d9d013963756ccc0024:Group1:1.00=1.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00;G1 User1,G1 User2,G1 User3,G1 User4,G1 User5,G1 User6,G1 User7,G1 user8}
	 */
	@Transactional
	public List<Map<String,String>> getAvgScoreView(final CmEvaluateTable cmEvaluateTable, final String ownRoleIds) {
		List<Map<String,String>> roleNameAndUserScores = new ArrayList<Map<String,String>>();
		List<String> groupRoleIdNames = amRoleManager.getGroupRoleIdNames(ownRoleIds);
		String groupRoleIds = "";
		String tableRoleIds = cmEvaluateTable.getRoleIds();
		for (int index=0; index<groupRoleIdNames.size(); index++) {
			String groupRoleIdName = groupRoleIdNames.get(index);
			String[] groupRoleIdNameArr = groupRoleIdName.split(":");
			if (StringUtil.isNotEmpty(tableRoleIds) && tableRoleIds.indexOf(groupRoleIdNameArr[0])==-1) {
				groupRoleIdNames.remove(index);
				index--;
			} else {
				groupRoleIds += groupRoleIdNameArr[0] + ",";
			}
		}
		Map<String,Map<String,String>> userIdAndEvaluateResultMap = this.getUserIdAndEvaluateResultMap(cmEvaluateTable.getEvaluateTableId(), groupRoleIds);
		Map<String,List<String>> roleIdAndUserMap = amUserRoleManager.getRoleIdAndUserMaps(groupRoleIds);
		//List<String> leaderUserIds = amUserRoleManager.getUserIds(AmRole.ROLE_ID_MANAGER);
		//1 Disagree:1; 2:2;  3 Neutral:3; 4:4; 5 Agree:5;
		Map<String,List<String>> itemMap = cmEvaluateItemManager.getEvaluateItemMap(cmEvaluateTable.getEvaluateTableId());
		List<String> itemIds = itemMap.get("itemIds");
		List<String> itemNames = itemMap.get("itemNames");
		List<String> itemOptions = itemMap.get("itemOptions");
		
		
		int allUserSize = 0;
		BigDecimal allAvgScore = new BigDecimal(0);
		
		String showUserScore = bmConfigManager.getValue(BmConfig.SHOW_USER_SURVEY_AVG_SCORE_FOR_LEADER);
		
		for (String groupRoleIdName : groupRoleIdNames) {
			Map<String,Map<Integer,Integer>> itemAndItemOrderSum = new HashMap<String,Map<Integer,Integer>>();
			BigDecimal[] qAveScoreArr = new BigDecimal[itemIds.size()];
			String[] groupRoleIdNameArr = groupRoleIdName.split(":");
			String roleId = groupRoleIdNameArr[0];
			String roleName = groupRoleIdNameArr[1];
			List<String> users = roleIdAndUserMap.get(roleId);
			StringBuffer userIdNameScoreSb = new StringBuffer();
			StringBuffer scoreSb = new StringBuffer();
			StringBuffer userNameSb = new StringBuffer();
			StringBuffer qNameSb = new StringBuffer();
			StringBuffer userWithScore = new StringBuffer();
			StringBuffer userWithOutScore = new StringBuffer();
			BigDecimal groupAvgScore = new BigDecimal(0);
			int groupUserSize = 0;
			for (String user : users) {
				String[] userArr = user.split(":");
				String userId = userArr[0];
				String userName = userArr[1];
				if (userArr.length==3) {
					if (AmUser.USER_ALIAS_DEPARTMENT_LEADER.equals(userArr[2])) {
						continue;	
					}
				}
				Map<String,String> evaluateResultMap = userIdAndEvaluateResultMap.get(userId);
				userIdNameScoreSb.append(userId).append(":").append(userName).append(":");
				if (scoreSb.length()>1) {
					scoreSb.append(",");
					userNameSb.append(",");
				}
				userNameSb.append(userName);
				if (evaluateResultMap!=null && !evaluateResultMap.isEmpty()) {
					groupUserSize++;
					String score = evaluateResultMap.get("score");
					groupAvgScore = groupAvgScore.add(new BigDecimal(score));
					userIdNameScoreSb.append(score).append(";");
					scoreSb.append(score);
					
					if (userWithScore.length()>1) {
						userWithScore.append(",");
					}
					userWithScore.append(userName);
					if ("on".equalsIgnoreCase(showUserScore)) {
						//userWithScore.append("(<span class='red bold'>").append(score).append("</span>), ");
					}
					String itemIdSelOrderScores = evaluateResultMap.get("result");
					String[] itemIdSelOrderScoreArr = itemIdSelOrderScores.split(";");
					Map<String, String> itemIdAndScoreMap = new HashMap<String,String>();
					Map<String, String> itemIdAndSelOrderMap = new HashMap<String,String>();
					for (String itemIdSelOrderScore : itemIdSelOrderScoreArr) {
						String[] valueArr = itemIdSelOrderScore.split(":");
						itemIdAndScoreMap.put(valueArr[0], valueArr[2]);
						itemIdAndSelOrderMap.put(valueArr[0], valueArr[1]);
					}
					
					//402881b33d32ea87013d32fb258900aa:0:1;
					for (int k=0; k<itemIds.size(); k++) {
						String itemId = itemIds.get(k);
						itemIdAndSelOrderMap.get(itemId);
						Integer selOrder = new Integer(itemIdAndSelOrderMap.get(itemId));
						if (!itemAndItemOrderSum.containsKey(itemId)) {
							Map<Integer,Integer> map = new HashMap<Integer,Integer>();
							map.put(selOrder, 1);
							itemAndItemOrderSum.put(itemId, map);
						} else {
							Map<Integer,Integer> map = itemAndItemOrderSum.get(itemId);
							if (map.containsKey(selOrder)) {
								map.put(selOrder, map.get(selOrder)+1);
							} else {
								map.put(selOrder, 1);
							}
						}
						BigDecimal itemScore = new BigDecimal(itemIdAndScoreMap.get(itemId));
						if (qAveScoreArr[k]==null) qAveScoreArr[k]=itemScore; 
						else qAveScoreArr[k] = qAveScoreArr[k].add(itemScore);
					}
				} else {
					if (userWithOutScore.length()>1) {
						userWithOutScore.append(",");
					}
					userWithOutScore.append(userName);
					userIdNameScoreSb.append("0").append(";");
					scoreSb.append("0.00");
				}
			}
			
			if (groupUserSize>1) {
				groupAvgScore = groupAvgScore.divide(new BigDecimal(groupUserSize), 2, BigDecimal.ROUND_FLOOR);
				for (int i=0; i<qAveScoreArr.length; i++) {
					qAveScoreArr[i] = qAveScoreArr[i].divide(new BigDecimal(groupUserSize), 2, BigDecimal.ROUND_FLOOR);
				}
			} 
			if (groupUserSize>0) {
				allUserSize++;
				allAvgScore = allAvgScore.add(groupAvgScore);
			}
			Map<String,String> map = new HashMap<String,String>();
			StringBuffer qAveScoreSb = new StringBuffer();
			StringBuffer qFullNameAndSoreSb = new StringBuffer();
			for (int j= 0; j<qAveScoreArr.length; j++) {
				if (j>0) {
					qAveScoreSb.append(",");
					qNameSb.append(",");
					qFullNameAndSoreSb.append("<br/>");
				}
				if (qAveScoreArr[j]==null) {
					qAveScoreSb.append("0");
				} else {
					qAveScoreSb.append(qAveScoreArr[j].toString());
				}
				qNameSb.append((j+1));
				qFullNameAndSoreSb.append((j+1) + ". " +itemNames.get(j) + "(<span class='red bold'>" + (qAveScoreArr[j]==null?0:qAveScoreArr[j]) + "</span>)");
				String[] answerNameScore = itemOptions.get(j).split(";");
				qFullNameAndSoreSb.append("<div style='padding-left:20px'>");
				for (int l=0; l<answerNameScore.length; l++) {
					String answerName = answerNameScore[l].split(":")[0];
					Integer value = 0;
					if (itemAndItemOrderSum.containsKey(itemIds.get(j))) {
						Integer tmpValue = itemAndItemOrderSum.get(itemIds.get(j)).get(new Integer(l));
						if (tmpValue!=null) value = tmpValue;
					}
					qFullNameAndSoreSb.append(answerName + " (<span class='red bold'>" + value + "</span>)  ,");
				}
				qFullNameAndSoreSb.append("</div>");
			}
			if (userWithScore.length()<1) userWithScore.append("none");
			
			if (userWithOutScore.length()<1) userWithOutScore.append("none");
			map.put(roleId + ":" + roleName + ":" + groupAvgScore, scoreSb.toString() + ";" + qAveScoreSb.toString() +  ";" + userNameSb + ";" + qNameSb + ";" + userWithScore + ";" + userWithOutScore + ";" + qFullNameAndSoreSb);//userIdNameScoreSb.toString()
			roleNameAndUserScores.add(map);
		}
		if (allUserSize>0) {
			allAvgScore = allAvgScore.divide(new BigDecimal(allUserSize), 2, BigDecimal.ROUND_FLOOR);
		}
		Map<String,String> map = new HashMap<String,String>();
		map.put("allAvgScore", allAvgScore.toString());
		roleNameAndUserScores.add(map);
		return roleNameAndUserScores;
	}
	
	/**
	 * <p>getEveryAnswerChooseView</p>
	 * 
	 * 
	 * @param evaluateTableId
	 * @param ownRoleIds
	 * @return question : {questionName, answers, answersSel}
		  groupInfo: {fillupUsers, noFillupUsers}
	 */
	@Transactional
	public Map<String, Object> getEveryAnswerChooseView(final CmEvaluateTable cmEvaluateTable, final String ownRoleIds) {
		List<String> groupRoleIdNames = amRoleManager.getGroupRoleIdNames(ownRoleIds);
		String groupRoleIds = "";
		String tableRoleIds = cmEvaluateTable.getRoleIds();
		for (int index=0; index<groupRoleIdNames.size(); index++) {
			String groupRoleIdName = groupRoleIdNames.get(index);
			String[] groupRoleIdNameArr = groupRoleIdName.split(":");
			if (StringUtil.isNotEmpty(tableRoleIds) && tableRoleIds.indexOf(groupRoleIdNameArr[0])==-1) {
				groupRoleIdNames.remove(index);
				index--;
			} else {
				groupRoleIds += groupRoleIdNameArr[0] + ",";
			}
		}
		Map<String,List<String>> roleIdAndUserMap = amUserRoleManager.getRoleIdAndUserMaps(groupRoleIds);
		
		Map<String,List<String>> itemMap = cmEvaluateItemManager.getEvaluateItemMap(cmEvaluateTable.getEvaluateTableId());
		List<String> itemIds = itemMap.get("itemIds");
		List<String> itemNames = itemMap.get("itemNames");
		List<String> itemOptions = itemMap.get("itemOptions");//1 Disagree:1; 2:2;  3 Neutral:3; 4:4; 5 Agree:5;
		Map<String,Map<String,String>> userIdAndEvaluateResultMap = this.getUserIdAndEvaluateResultMap(cmEvaluateTable.getEvaluateTableId(), groupRoleIds);
		
		Map<String, Map<String,Object>> itemIdAndInfoMap = (Map<String, Map<String,Object>>)new ListOrderedMap();
		for (int i=0; i<itemIds.size(); i++) {
			Map<String,Object> infoMap = new HashMap<String,Object>();
			infoMap.put("questionName", itemNames.get(i));
			
			String[] answerNameScore = itemOptions.get(i).split(";");
			List<String> answersList = new ArrayList<String>();
			List<Integer> xList = new ArrayList<Integer>();
			List<Integer> answersSelList = new ArrayList<Integer>();
			
			List<List<Integer>> allGroupAnswersSel = new ArrayList<List<Integer>>();
			List<String> jqplotGroupSeries = new ArrayList<String>();
			for (String groupRoleIdName : groupRoleIdNames) {
				List<Integer> groupAnswerList = new ArrayList<Integer>();
				allGroupAnswersSel.add(groupAnswerList);
				
				String[] groupRoleIdNameArr = groupRoleIdName.split(":");
				String roleName = groupRoleIdNameArr[1];
				jqplotGroupSeries.add("{label:'" + roleName +  "'}");
			}
			
			for (int l=0; l<answerNameScore.length; l++) {
				String answerName = answerNameScore[l].split(":")[0];
				answersList.add(answerName);
				answersSelList.add(0);
				xList.add(l+1);
				
				for (List<Integer> list : allGroupAnswersSel) {
					list.add(0);
				}
			}
			infoMap.put("answers", answersList);
			infoMap.put("answersSel", answersSelList);
			
			infoMap.put("allGroupAnswersSel", allGroupAnswersSel);
			infoMap.put("xList", xList);
			infoMap.put("jqplotGroupSeries", jqplotGroupSeries);
			
			itemIdAndInfoMap.put(itemIds.get(i), infoMap);
		}
		
		Map<String, Map<String,String>> groupNameAndInfoMap = (Map<String, Map<String,String>>)new ListOrderedMap();
		int allUserNum = 0;
		for (int index=0;index<groupRoleIdNames.size();index++) {
			String groupRoleIdName = groupRoleIdNames.get(index);
			String[] groupRoleIdNameArr = groupRoleIdName.split(":");
			String roleId = groupRoleIdNameArr[0];
			String roleName = groupRoleIdNameArr[1];
			List<String> users = roleIdAndUserMap.get(roleId);
			Map<String,String> groupInfoMap = new HashMap<String,String>();
			
			BigDecimal groupAvgScore = new BigDecimal(0);
			int groupUserSize = 0;
			
			for (String user : users) {
				String[] userArr = user.split(":");
				String userId = userArr[0];
				String userName = userArr[1];
				if (userArr.length==3) {
					if (AmUser.USER_ALIAS_DEPARTMENT_LEADER.equals(userArr[2])) {
						continue;	
					}
				}
				allUserNum++;
				Map<String,String> evaluateResultMap = userIdAndEvaluateResultMap.get(userId); //402881b33d32ea87013d32fb258900aa:0:1;
				
				if (evaluateResultMap!=null && !evaluateResultMap.isEmpty()) {
					String fillupUsers = groupInfoMap.get("fillupUsers");
					if (fillupUsers==null) {
						groupInfoMap.put("fillupUsers", userName);
					} else {
						groupInfoMap.put("fillupUsers", groupInfoMap.get("fillupUsers") + "," + userName);
					}
					
					String itemIdSelOrderScores = evaluateResultMap.get("result");
					String[] itemIdSelOrderScoreArr = itemIdSelOrderScores.split(";");
					Map<String, String> itemIdAndScoreMap = new HashMap<String,String>();
					Map<String, String> itemIdAndSelOrderMap = new HashMap<String,String>();
					for (String itemIdSelOrderScore : itemIdSelOrderScoreArr) {
						String[] valueArr = itemIdSelOrderScore.split(":");
						itemIdAndScoreMap.put(valueArr[0], valueArr[2]);
						itemIdAndSelOrderMap.put(valueArr[0], valueArr[1]);
					}
					
					//402881b33d32ea87013d32fb258900aa:0:1;
					for (int k=0; k<itemIds.size(); k++) {
						String itemId = itemIds.get(k);
						Map<String,Object> questionMap = itemIdAndInfoMap.get(itemId);
						Integer selOrder = new Integer(itemIdAndSelOrderMap.get(itemId));
						List<Integer> selList = (List<Integer>)questionMap.get("answersSel");
						selList.set(selOrder, selList.get(selOrder).intValue()+1);
						
						List<List<Integer>> list = (List<List<Integer>>)questionMap.get("allGroupAnswersSel");
						List<Integer> groupSelList = list.get(index);
						groupSelList.set(selOrder, groupSelList.get(selOrder).intValue()+1);
					}
				} else {
					String noFillupUsers = groupInfoMap.get("noFillupUsers");
					if (noFillupUsers==null) {
						groupInfoMap.put("noFillupUsers", userName);
					} else {
						groupInfoMap.put("noFillupUsers", groupInfoMap.get("noFillupUsers") + "," + userName);
					}
				}
			}
			groupNameAndInfoMap.put(roleName, groupInfoMap);
		}
		for (Map<String,Object> infoMap : itemIdAndInfoMap.values()) {
			List<String> answers = (List<String>)infoMap.get("answers");
			List<Integer> answersSel = (List<Integer>)infoMap.get("answersSel");
			for (int i=0; i<answers.size(); i++) {
				answers.set(i, answers.get(i) + "<span style='color:red'>(" + answersSel.get(i) + ")</span>");
			}
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("questionInfos", itemIdAndInfoMap);
		resultMap.put("groupInfos", groupNameAndInfoMap);
		resultMap.put("allUserNum", allUserNum);
		return resultMap;
	}
	
	
	@Resource(name="baseDaoHibernate")
	private BaseDao<CmUserEvaluateTableResult, String> dao;
	
	@Resource(name="amRoleManagerImpl")
	private AmRoleManager amRoleManager;
	
	@Resource(name="amUserRoleManagerImpl")
	private AmUserRoleManager amUserRoleManager;

	@Resource(name="cmEvaluateItemManagerImpl")
	private CmEvaluateItemManager cmEvaluateItemManager;
	
	@Resource(name="bmConfigManagerImpl")
	private BmConfigManager bmConfigManager;
}