/**
 * $Id: CollectionUtil.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */

package org.ganjp.jpw.core.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Collection Utility for internal use.</p>
 *
 * @author GanJianping
 * @since 1.0
 */
public class CollectionUtil {
	private static Logger log = LoggerFactory.getLogger(CollectionUtil.class);
	
	/**
	 * <p>create an ArrayList<Object> and add objects</p>
	 * 
	 * @param objects eg:"a",new Integer("1")
	 * @return List<Object>
	 */
	public static List<Object> createArrayList(Object...objects) {
		List<Object> objetList = new ArrayList<Object>();
	    for (Object object : objects) {
	    	objetList.add(object);
	    }
	    log.debug("creat List<Objet> : " + objetList.toString());
	    return objetList;
	}

	public static List<String[]> createStrArrList(String[]... strArrs) {
		List<String[]> objetList = new ArrayList<String[]>();
	    for (String[] strArr : strArrs) {
	    	objetList.add(strArr);
	    }
	    log.debug("creat List<String[]> : " + objetList.toString());
	    return objetList;
	}
	/**
	 * <p>get list by string with split</p>
	 * 
	 * @param strs  eg:"gan,jian,ping"
	 * @param split eg:","
	 * @return List<String>
	 */
	public static List<String> getList(String strs, String split) {
		String[] strArr = strs.split(split);
		List<String> strList = new ArrayList<String>();
	    for (String str : strArr) {
	    	strList.add(str);
		}
	    return strList;
	}
	
	/**
	 * <p>get string with split</p>
	 * 
	 * @param objs eg:List(gan,jian,ping)
	 * @param split eg:","
	 * @return
	 */
	public static String getStringWithSplit(List<Object> objs, String split) {
		String str = "";
		for (int i = 0; i < objs.size(); i++) {
			if (i==0) {
				str = objs.get(i).toString();
			} else {
				str += split + objs.get(i).toString();
			}
		}
	    return str;
	}
	
	/**
	 * <p>get string with split</p>
	 * 
	 * @param objs eg:List(gan,jian,ping)
	 * @param split eg:","
	 * @return
	 */
	public static String getStringWithSplit(List<String> objs) {
		String str = "";
		for (int i = 0; i < objs.size(); i++) {
			if (i==0) {
				str = objs.get(i).toString();
			} else {
				str += "," + objs.get(i).toString();
			}
		}
	    return str;
	}
	
	/**
	 * <p>to object array</p>
	 * 
	 * @param list
	 * @return
	 */
	public static Object[] toObjArr(List<Object> list) {
		if (list==null || list.isEmpty()) {
			return null;
		}
		for (int i = 0; i < list.size(); i++) {
			Object object = list.get(i);
			if (object==null) {
				list.remove(i);
				i--;
			}
		}
		return list.toArray();
	}
	
	public static void main(String[] args) {
		List list = createArrayList(null,null,null);
		Object[] objArr = toObjArr(list);
		System.out.println(objArr);
	}
}
