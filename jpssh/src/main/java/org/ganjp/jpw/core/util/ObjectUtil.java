/**
 * $Id: ObjectUtil.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.core.util;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Object Utility for internal use.</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public class ObjectUtil {
	
    //-------------------------------- ToString ------------------------------ 
    /**
     * <p>Gets the <code>toString</code> of an <code>Object</code> returning
     * an empty string ("") if <code>null</code> input.</p>
     * 
     * <pre>
     * ObjectUtils.toString(null)         = ""
     * ObjectUtils.toString("")           = ""
     * ObjectUtils.toString("bat")        = "bat"
     * ObjectUtils.toString(Boolean.TRUE) = "true"
     * </pre>
     * 
     * @param obj  eg:null|"2"
     * @return String
     */
    public static String toString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    /**
     * <p>Gets the <code>toString</code> of an <code>Object</code> returning
     * a specified text if <code>null</code> input.</p>
     * 
     * <pre>
     * ObjectUtils.toString(null, null)           = null
     * ObjectUtils.toString(null, "null")         = "null"
     * ObjectUtils.toString("", "null")           = ""
     * ObjectUtils.toString("bat", "null")        = "bat"
     * ObjectUtils.toString(Boolean.TRUE, "null") = "true"
     * </pre>
     * 
     * @see StringUtil#defaultString(String,String)
     * @see String#valueOf(Object)
     * @param obj  "Hello"
     * @param nullStr ""
     * @return String
     */
    public static String toString(Object obj, String nullStr) {
        return obj == null ? nullStr : obj.toString();
    }

    /**
	 * <p>convert to HashMap, new HashMap<String, Object>() if obj is null or not Map</p>
	 * 
	 * @param obj 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> convertOrCreateHashMap(Object obj) {
		Map<String, Object> map = null;
		if (obj instanceof Map) {
			map = (Map<String, Object>)obj;
		} else {
			map = new HashMap<String, Object>() ;
		}
		return map;
	}
}
