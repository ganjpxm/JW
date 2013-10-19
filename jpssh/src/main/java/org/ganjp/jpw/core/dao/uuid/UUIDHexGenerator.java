/**
 * $Id: UUIDHexGenerator.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */

package org.ganjp.jpw.core.dao.uuid;

import java.io.Serializable;
import java.sql.Connection;

/**
 * <b>uuid</b><br>
 * <br>
 * A <tt>UUIDGenerator</tt> that returns a string of length 32, This string will consist
 * of only hex digits. Optionally, the string may be generated with separators between
 * each component of the UUID.
 * 
 * @author GanJianping
 * @since 1.0
 */
public class UUIDHexGenerator extends AbstractUUIDGenerator {
	
	public static void main(String[] args){
		UUIDHexGenerator uuid = new UUIDHexGenerator();
		System.out.println(((String)uuid.generate(null, null)));
	}

	private String sep = "";

	protected String format(int intval) {
		String formatted = Integer.toHexString(intval);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}

	protected String format(short shortval) {
		String formatted = Integer.toHexString(shortval);
		StringBuffer buf = new StringBuffer("0000");
		buf.replace(4 - formatted.length(), 4, formatted);
		return buf.toString();
	}

	public Serializable generate(Connection conn, Object obj) {
		return new StringBuffer(36).append(format(getIP())).append(sep).append(format(getJVM())).append(
				sep).append(format(getHiTime())).append(sep).append(format(getLoTime())).append(sep)
				.append(format(getCount())).toString();
	}
	
	public Serializable generate() {
		return new StringBuffer(36).append(format(getIP())).append(sep).append(format(getJVM())).append(
				sep).append(format(getHiTime())).append(sep).append(format(getLoTime())).append(sep)
				.append(format(getCount())).toString();
	}
	
	public static String getUuid() {
		UUIDHexGenerator uuidHexGenerator = new UUIDHexGenerator();
		return (String)uuidHexGenerator.generate();
	}
}
