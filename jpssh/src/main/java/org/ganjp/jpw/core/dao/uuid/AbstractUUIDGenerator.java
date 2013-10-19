/**
 * $Id: AbstractUUIDGenerator.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */

package org.ganjp.jpw.core.dao.uuid;

import java.net.InetAddress;

import org.ganjp.jpw.core.util.BytesUtil;

/**
 * <p>The base class for identifier generators that use a UUID algorithm. 
 * This class implements the algorithm, subclasses define the identifier format.</p> 
 *
 * @author GanJianping
 * @since 1.0
 * @see UUIDHexGenerator
 */
public abstract class AbstractUUIDGenerator implements IdentifierGenerator {

	private static final int IP;
	static {
		int ipadd;
		try {
			ipadd = BytesUtil.toInt(InetAddress.getLocalHost().getAddress());
		} catch (Exception e) {
			ipadd = 0;
		}
		IP = ipadd;
	}
	private static short counter = (short) 0;
	private static final int JVM = (int) (System.currentTimeMillis() >>> 8);

	public AbstractUUIDGenerator() {
	}

	/**
	 * Unique across JVMs on this machine (unless they load this class in the same quater
	 * second - very unlikely)
	 */
	protected int getJVM() {
		return JVM;
	}

	/**
	 * Unique in a millisecond for this JVM instance (unless there are > Short.MAX_VALUE
	 * instances created in a millisecond)
	 */
	protected short getCount() {
		synchronized (AbstractUUIDGenerator.class) {
			if (counter < 0)
				counter = 0;
			return counter++;
		}
	}

	/**
	 * Unique in a local network
	 */
	protected int getIP() {
		return IP;
	}

	/**
	 * Unique down to millisecond
	 */
	protected short getHiTime() {
		return (short) (System.currentTimeMillis() >>> 32);
	}

	protected int getLoTime() {
		return (int) System.currentTimeMillis();
	}

}
