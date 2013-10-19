/**
 * $Id: BytesUtil.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.core.util;

/**
 * <p>deal with Bytes utility</p> 
 *
 * @author GanJianping
 * @since 1.0
 */
public final class BytesUtil {

	private BytesUtil() {
	}

	/**
	 * <p>convert int from byte array</p>
	 * 
	 * @param bytes
	 * @return
	 */
	public static int toInt(byte[] bytes) {
		int result = 0;
		for (int i = 0; i < 4; i++) {
			result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
		}
		return result;
	}
}