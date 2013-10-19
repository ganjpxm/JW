/**
 * $Id: RegexUtil.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

	public static List<String> getMatches(String regex, String source) {
	   Pattern p = Pattern.compile(regex);
	   Matcher m = p.matcher(source);
	   List<String> list = new ArrayList<String>();
	   while (m.find()) {
		   for (int i = 0; i <= m.groupCount(); i++) {
			   list.add(m.group(i));
		   }
	   }
	   return list;
	}
	
	public static String getMatchStr(String regex, String source) {
		String match = null;
		List<String> list = getMatches(regex, source);
		if (list!=null && list.size()>0) {
			match = list.get(0);
		}
		return match;
	}
	
	public static int getFirstMatchIndex(String regex, String source) {
		return getFirstMatchIndex(regex, source, 0);
	}
	
	public static int getFirstMatchIndex(String regex, String source, int start) {
		source = source.substring(start, source.length());
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(source);
		if(m.find()) {
			return m.start() + start;
		}
		return -1;
	}
	
	public static void main(String[] arg) {
	    String source = "220.00WSC1 ANG LIANG TZE T0933453F LOK SHU CHING S7914018Z Child Full Day Trial Enrolment 220.00";
	    System.out.println(getMatchStr("T[0-9]{7}[A-Z]{1}", source));
	    System.out.println(getFirstMatchIndex("\\.00", source, 10));
	}

}
