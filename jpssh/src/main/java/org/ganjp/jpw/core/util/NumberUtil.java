/**
 * $Id: NumberUtil.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.core.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtil {

	public NumberUtil() {
		super();
	}

	/**
	 * 规范小数位
	 *
	 * @param num:  原始数字
	 * @param unit: 要求小数位数
	 * @return: 返回String类型的数字
	 */
	public static String formatNumFraction(double num, int unit) {
		String zero = "";
		if (unit == 0) {
			zero = "0";
		} else {
			zero = "0.";
		}
		for (int i = 0; i < unit; i++) {
			zero += "0";
		}
		DecimalFormat df = new DecimalFormat(zero);
		return df.format(num);
	}

	/**
	 * 根据传入的格式进行格式化数字
	 *
	 * @param num
	 * @param format：格式的样子，如0.000,#.###等
	 * @return
	 */
	public static String formatNumFraction(double num, String format) {
		DecimalFormat df = new DecimalFormat(format);
		return df.format(num);
	}

	/**
	 * 根据传入的格式进行格式化数字
	 *
	 * @param num
	 * @param format：格式的样子，如0.000,#.###等
	 * @return
	 */
	public static String formatNumFraction(float num, String format) {
		DecimalFormat df = new DecimalFormat(format);
		return df.format(num);
	}

	/**
	 * 根据传入的格式进行格式化数字
	 *
	 * @param num
	 * @param format：格式的样子，如0.000,#.###等
	 * @return
	 */
	public static String formatNumFraction(int num, String format) {
		DecimalFormat df = new DecimalFormat(format);
		return df.format(num);
	}

	/**
	 * 根据传入的格式进行格式化数字
	 *
	 * @param num
	 * @param format：格式的样子，如0.000,#.###等
	 * @return
	 */
	public static String formatNumFraction(long num, String format) {
		DecimalFormat df = new DecimalFormat(format);
		return df.format(num);
	}

	/**
	 * 根据传入的格式进行格式化数字
	 *
	 * @param num
	 * @param format：格式的样子，如0.000,#.###等
	 * @return
	 */
	public static String formatNumFraction(String num, String format) {
		DecimalFormat df = new DecimalFormat(format);
		return df.format(new Double(num));
	}

	public static String formatNumFraction(float num, int unit) {
		String zero = "";
		if (unit == 0) {
			zero = "0";
		} else {
			zero = "0.";
		}
		for (int i = 0; i < unit; i++) {
			zero += "0";
		}
		DecimalFormat df = new DecimalFormat(zero);
		return df.format(num);
	}

	public static String formatNumFraction(long num, int unit) {
		String zero = "";
		if (unit == 0) {
			zero = "0";
		} else {
			zero = "0.";
		}
		for (int i = 0; i < unit; i++) {
			zero += "0";
		}
		DecimalFormat df = new DecimalFormat(zero);
		return df.format(num);
	}

	/**
	 * <p>
	 * 给整数前面补零
	 * 如果带有小数部分，会根据四舍五入的原则处理
	 * </p>
	 * <p/>
	 * <pre>
	 * formatNumInteger(199.1,4) =>0199
	 * formatNumInteger(199.9,4) =>0200
	 * </pre>
	 *
	 * @param num  原始数字
	 * @param unit 整数位数
	 * @return 整数
	 */
	public static String formatNumInteger(double num, int unit) {
		String zero = "";
		if (unit == 0) {
			zero = "0";
		} else {
			zero = "";
		}
		for (int i = 0; i < unit; i++) {
			zero += "0";
		}
		DecimalFormat df = new DecimalFormat(zero);
		return df.format(num);
	}

	/**
	 * <p>
	 * 给整数前面补零
	 * 如果带有小数部分，会根据四舍五入的原则处理
	 * </p>
	 * <pre>
	 * formatNumInteger(199.1,4) =>0199
	 * formatNumInteger(199.9,4) =>0200
	 * </pre>
	 *
	 * @param num  浮点型数字
	 * @param unit 整数位数
	 * @return
	 */
	public static String formatNumInteger(float num, int unit) {
		String zero = "";
		if (unit == 0) {
			zero = "0";
		} else {
			zero = "";
		}
		for (int i = 0; i < unit; i++) {
			zero += "0";
		}
		DecimalFormat df = new DecimalFormat(zero);
		return df.format(num);
	}

	/**
	 * <p>
	 * 给整数前面补零
	 * </p>
	 * <pre>
	 * 	formatNumInteger(199,4) => 0199
	 * </pre>
	 *
	 * @param num  长整型数字
	 * @param unit 整数位数
	 * @return
	 */
	public static String formatNumInteger(long num, int unit) {
		String zero = "";
		if (unit == 0) {
			zero = "0";
		} else {
			zero = "";
		}
		for (int i = 0; i < unit; i++) {
			zero += "0";
		}
		DecimalFormat df = new DecimalFormat(zero);
		return df.format(num);
	}

	/**
	 * <p>
	 * 给整数前面补零
	 * </p>
	 * <pre>
	 * 	formatNumInteger(199,4) => 0199
	 * </pre>
	 *
	 * @param num  整型数字
	 * @param unit 整数位数
	 * @return
	 */
	public static String formatNumInteger(int num, int unit) {
		String zero = "";
		if (unit == 0) {
			zero = "0";
		} else {
			zero = "";
		}
		for (int i = 0; i < unit; i++) {
			zero += "0";
		}
		DecimalFormat df = new DecimalFormat(zero);
		return df.format(num);
	}

	/**
	 * <p>
	 * 根据输入的位数限制截取int。
	 * </p>
	 * <p/>
	 * <pre>
	 * 	limitNumLength(1234,2) => 34
	 * 	limitNumLength(1234,3) => 234
	 *  limitNumLength(1000,2) => 0
	 * </pre>
	 *
	 * @param num  要截取的int
	 * @param unit 整数位数
	 * @return
	 */
	public static int limitNumLength(int num, int unit) {
		int e = (int) (Math.pow(10, unit));
		if (num > (e - 1)) {
			int tempUnm = num / e;
			num = num - tempUnm * e;
		}
		return num;
	}
	
	public static String toString(BigDecimal num) {
		if (num==null) {
			return "0";
		} else {
			return num.setScale(2).toString();
		}
	}
	
	public static BigDecimal toBigDecimal(Object object) {
		if (object==null) {
			return new BigDecimal(0);
		} else {
			return new BigDecimal(object.toString());
		}
	}
	
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9|.]*");
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ) {
			return false;
		}
		return true;
	}
	
	public static BigDecimal subtract(BigDecimal a, BigDecimal b) {
		if (a==null) a = new BigDecimal("0.00");
		if (b==null) b = new BigDecimal("0.00");
		return a.subtract(b).setScale(2);
	}
}
