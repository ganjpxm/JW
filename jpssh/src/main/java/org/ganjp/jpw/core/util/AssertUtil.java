/**
 * $Id: AssertUtil.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.core.util;

/**
 * <p>Assert Utility for internal use.</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public class AssertUtil {
	
	/**
	 * <p>Assert that the given Object has valid text content; that is, it must not</p>
	 * 
	 * @param object eg:new String[]{"a","b"}
	 * @param message eg:"the object must not be empty"
	 * @throws IllegalArgumentException if object is null
	 */
	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}
	
	/**
	 * <p>Assert that the given String has valid text content; that is, it must not
	 * be <code>null</code> and must contain at least one non-whitespace character.</p>
	 * 
	 * @param text " "
	 * @param message eg:"text must not be empty"
	 * @see StringUtils#hasText
	 * @throws IllegalArgumentException if text doesn't have actual text
	 */
	public static void hasText(String text, String message) {
		if (!StringUtil.hasText(text)) {
			throw new IllegalArgumentException(message);
		}
	}
	
	/**
	 * <p>Assert a boolean expression, throwing <code>IllegalArgumentException</code>
	 * if the test result is <code>false</code>.</p>
	 * 
	 * @param expression eg:i &gt; 0
	 * @throws IllegalArgumentException if expression is <code>false</code>
	 */
	public static void isTrue(boolean expression) {
		isTrue(expression, "[Assertion failed] - this expression must be true");
	}
	
	/**
	 * <p>Assert a boolean expression, throwing <code>IllegalArgumentException</code>
	 * if the test result is <code>false</code>.</p>
	 * 
	 * @param expression eg:i &gt; 0
	 * @param message eg:"The value must be greater than zero"
	 * @throws IllegalArgumentException if expression is <code>false</code>
	 */
	public static void isTrue(boolean expression, String message) {
		if (!expression) {
			throw new IllegalArgumentException(message);
		}
	}
	
	public static void main(String[] args) {
		notNull("GanJianping", "the object must not be empty");
		hasText("GanJianping", "text must not be empty");
		isTrue(6>0);
		isTrue(6>0, "The value must be greater than zero");
	}
}
