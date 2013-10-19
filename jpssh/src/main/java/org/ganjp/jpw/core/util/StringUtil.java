/**
 * $Id: StringUtil.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.core.util;

import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>String Utility for internal use.</p>
 *
 * @author GanJianping
 * @since 1.0
 */
public class StringUtil {
	public static final String EMPTY = "";
    /**
     * <p>Checks if a String is empty ("") or null.</p>
     *
     * <pre>
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if the String is empty or null
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * <p>Checks if a String is not empty ("") and not null.</p>
     *
     * <pre>
     * StringUtils.isNotEmpty(null)      = false
     * StringUtils.isNotEmpty("")        = false
     * StringUtils.isNotEmpty(" ")       = true
     * StringUtils.isNotEmpty("bob")     = true
     * StringUtils.isNotEmpty("  bob  ") = true
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if the String is not empty and not null
     */
    public static boolean isNotEmpty(String str) {
        return !StringUtil.isEmpty(str);
    }
    
	/**
	 * Check that the given String is neither <code>null</code> nor of length 0.
	 * Note: return <code>true</code> for a String that purely consists of whitespace.<p>
	 * <pre>
	 * StringUtils.hasLength(null) = false
	 * StringUtils.hasLength("") = false
	 * StringUtils.hasLength(" ") = true
	 * StringUtils.hasLength("Hello") = true
	 * </pre>
	 * 
	 * @param str eg:null|""|" "|"Hello"
	 * @return boolean
	 * @see #hasLength(CharSequence)
	 */
	public static boolean hasLength(String str) {
		return hasLength((CharSequence) str);
	}

	/**
	 * Check whether the given String has actual text.
	 * Note: returns <code>true</code> if the string not <code>null</code>,
	 * its length is greater than 0, and it contains at least one non-whitespace character.<p>
	 * <pre>
	 * StringUtils.hasText(null) = false
	 * StringUtils.hasText("") = false
	 * StringUtils.hasText(" ") = false
	 * StringUtils.hasText("12345") = true
	 * StringUtils.hasText(" 12345 ") = true
	 * </pre>
	 *  
	 * @param str eg:null|""|" "|"Hello"
	 * @return boolean
	 * @see #hasText(CharSequence)
	 */
	public static boolean hasText(String str) {
		return hasText((CharSequence) str);
	}

	/**
	 * <p>split string with delimStr and return String[]</p>
	 * <pre>
	 *  StringUtil.split(null, &quot;&quot;)                  = null
	 *  StringUtil.split(&quot;&quot;, &quot;&quot;)          = []
	 *  StringUtil.split(&quot;a.b.c&quot;, &quot;.&quot;)    = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
	 *  StringUtil.split(&quot;a..b.c&quot;, &quot;.&quot;)   = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
	 *  StringUtil.split(&quot;a:b:c&quot;, &quot;.&quot;)    = [&quot;a:b:c&quot;]
	 *  StringUtil.split(&quot;a b c&quot;, &quot; &quot;)    = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
	 * </pre>
	 *
	 * @param srcStr   "a,b,c"
	 * @param delimStr ","
	 * @return String[]
	 */
	public static String[] split(String srcStr, String delimStr) {
		if (srcStr == null || delimStr == null) {
			return null;
		}
		StringTokenizer st = new StringTokenizer(srcStr, delimStr);
		Vector<String> vct = new Vector<String>();
		while (st.hasMoreTokens()) {
			vct.add(st.nextToken());
		}
		Object[] tks = vct.toArray();
		String rt[] = new String[vct.size()];
		System.arraycopy(tks, 0, rt, 0, vct.size());
		return rt;
	}
	
	/**
	 * 对字符串加上引号并对特殊字符进行处理
	 * 输入：chinasofti\n\r\"beijing\"
	 * 输出：\"chinasofti\\n\\r\\"beijing\\"\"
	 * @param str
	 * @return
	 */
	public static String quoteAndReplaceTransferChar(String str) {
		StringBuffer sb = new StringBuffer(str.length() + 2);
		sb.append('\"');
		sb.append(replaceTransferChar(str));
		sb.append('\"');
		return sb.toString();
	}
	
	/**
	 * 对字符串加强转义
	 * @param str
	 * @return
	 */
	public static String replaceTransferChar(Object obj) {
		if (obj == null){
			return null;
		}
		String str = obj.toString();
		if (!hasText(str)) {
			return "";
		}
		StringBuffer sb = new StringBuffer(str.length() + 20);
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			switch (c) {
			case '\"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '/':
				sb.append("\\/");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			default:
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * <p>to Lower Case</p>
	 * <pre>
	 * 	toLowerCase(&quot;UserFile&quot;) => userfile
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static String toLowerCase(String str) {
		if (hasText(str)) {
			return str.trim().toLowerCase();
		} else {
			return str;
		}
	}
    /**
     * <p>Gets the substring after the first occurrence of a separator.
     * The separator is not returned.</p>
     *
     * <p>A <code>null</code> string input will return <code>null</code>.
     * An empty ("") string input will return the empty string.
     * A <code>null</code> separator will return the empty string if the
     * input string is not <code>null</code>.</p>
     *
     * <pre>
     * StringUtils.substringAfter(null, *)      = null
     * StringUtils.substringAfter("", *)        = ""
     * StringUtils.substringAfter(*, null)      = ""
     * StringUtils.substringAfter("abc", "a")   = "bc"
     * StringUtils.substringAfter("abcba", "b") = "cba"
     * StringUtils.substringAfter("abc", "c")   = ""
     * StringUtils.substringAfter("abc", "d")   = ""
     * StringUtils.substringAfter("abc", "")    = "abc"
     * </pre>
     *
     * @param str  the String to get a substring from, may be null
     * @param separator  the String to search for, may be null
     * @return the substring after the first occurrence of the separator,
     *  <code>null</code> if null String input
     */
    public static String substringAfter(String str, String separator) {
        if (isEmpty(str)) {
            return str;
        }
        if (separator == null) {
            return EMPTY;
        }
        int pos = str.indexOf(separator);
        if (pos == -1) {
            return EMPTY;
        }
        return str.substring(pos + separator.length());
    }
    /**
     * <p>Gets the substring before the first occurrence of a separator.
     * The separator is not returned.</p>
     *
     * <p>A <code>null</code> string input will return <code>null</code>.
     * An empty ("") string input will return the empty string.
     * A <code>null</code> separator will return the input string.</p>
     *
     * <pre>
     * StringUtils.substringBefore(null, *)      = null
     * StringUtils.substringBefore("", *)        = ""
     * StringUtils.substringBefore("abc", "a")   = ""
     * StringUtils.substringBefore("abcba", "b") = "a"
     * StringUtils.substringBefore("abc", "c")   = "ab"
     * StringUtils.substringBefore("abc", "d")   = "abc"
     * StringUtils.substringBefore("abc", "")    = ""
     * StringUtils.substringBefore("abc", null)  = "abc"
     * </pre>
     *
     * @param str  the String to get a substring from, may be null
     * @param separator  the String to search for, may be null
     * @return the substring before the first occurrence of the separator,
     *  <code>null</code> if null String input
     */
    public static String substringBefore(String str, String separator) {
        if (isEmpty(str) || separator == null) {
            return str;
        }
        if (separator.length() == 0) {
            return EMPTY;
        }
        int pos = str.indexOf(separator);
        if (pos == -1) {
            return str;
        }
        return str.substring(0, pos);
    }

    /**
     * <p>Gets a substring from the specified String avoiding exceptions.</p>
     *
     * <p>A negative start position can be used to start <code>n</code>
     * characters from the end of the String.</p>
     *
     * <p>A <code>null</code> String will return <code>null</code>.
     * An empty ("") String will return "".</p>
     *
     * <pre>
     * StringUtils.substring(null, *)   = null
     * StringUtils.substring("", *)     = ""
     * StringUtils.substring("abc", 0)  = "abc"
     * StringUtils.substring("abc", 2)  = "c"
     * StringUtils.substring("abc", 4)  = ""
     * StringUtils.substring("abc", -2) = "bc"
     * StringUtils.substring("abc", -4) = "abc"
     * </pre>
     *
     * @param str  the String to get the substring from, may be null
     * @param start  the position to start from, negative means
     *  count back from the end of the String by this many characters
     * @return substring from start position, <code>null</code> if null String input
     */
    public static String substring(String str, int start) {
        if (str == null) {
            return null;
        }

        // handle negatives, which means last n characters
        if (start < 0) {
            start = str.length() + start; // remember start is negative
        }

        if (start < 0) {
            start = 0;
        }
        if (start > str.length()) {
            return EMPTY;
        }

        return str.substring(start);
    }
    /**
     * <p>Gets a substring from the specified String avoiding exceptions.</p>
     *
     * <p>A negative start position can be used to start/end <code>n</code>
     * characters from the end of the String.</p>
     *
     * <p>The returned substring starts with the character in the <code>start</code>
     * position and ends before the <code>end</code> position. All position counting is
     * zero-based -- i.e., to start at the beginning of the string use
     * <code>start = 0</code>. Negative start and end positions can be used to
     * specify offsets relative to the end of the String.</p>
     *
     * <p>If <code>start</code> is not strictly to the left of <code>end</code>, ""
     * is returned.</p>
     *
     * <pre>
     * StringUtils.substring(null, *, *)    = null
     * StringUtils.substring("", * ,  *)    = "";
     * StringUtils.substring("abc", 0, 2)   = "ab"
     * StringUtils.substring("abc", 2, 0)   = ""
     * StringUtils.substring("abc", 2, 4)   = "c"
     * StringUtils.substring("abc", 4, 6)   = ""
     * StringUtils.substring("abc", 2, 2)   = ""
     * StringUtils.substring("abc", -2, -1) = "b"
     * StringUtils.substring("abc", -4, 2)  = "ab"
     * </pre>
     *
     * @param str  the String to get the substring from, may be null
     * @param start  the position to start from, negative means
     *  count back from the end of the String by this many characters
     * @param end  the position to end at (exclusive), negative means
     *  count back from the end of the String by this many characters
     * @return substring from start position to end positon,
     *  <code>null</code> if null String input
     */
    public static String substring(String str, int start, int end) {
        if (str == null) {
            return null;
        }

        // handle negatives
        if (end < 0) {
            end = str.length() + end; // remember end is negative
        }
        if (start < 0) {
            start = str.length() + start; // remember start is negative
        }

        // check length next
        if (end > str.length()) {
            end = str.length();
        }

        // if start is greater than end, return ""
        if (start > end) {
            return EMPTY;
        }

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }
        return str.substring(start, end);
    }

	//------------------------------------- methods below are not always used -------------------------------- 
	/**
	 * Check that the given CharSequence is neither <code>null</code> nor of length 0.
	 * Note: Will return <code>true</code> for a CharSequence that purely consists of whitespace.<p>
	 * 
	 * @param str eg:(CharSequence)"Hello"
	 * @return boolean
	 * @see #hasText(String)
	 */
	public static boolean hasLength(CharSequence str) {
		return (str != null && str.length() > 0);
	}
	
	/**
	 * Check whether the given CharSequence has actual text.
	 * More specifically, returns <code>true</code> if the string not <code>null</code>,
	 * its length is greater than 0, and it contains at least one non-whitespace character.<p>
	 * 
	 * @param str eg:(CharSequence)"Hello"
	 * @return boolean
	 * @see java.lang.Character#isWhitespace
	 */
	public static boolean hasText(CharSequence str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <p>from net.sf.json.util.JSONUtils</p>
	 *  
	 * @param string
	 * @return String
	 */
	public static String quoteJsonLib(String string) {  
        char b;  
        char c = 0;  
        int i;  
        int len = string.length();  
        StringBuffer sb = new StringBuffer(len * 2);  
        String t;  
        char[] chars = string.toCharArray();  
        char[] buffer = new char[1030];  
        int bufferIndex = 0;  
        sb.append('"');  
        for (i = 0; i < len; i += 1) {  
            if (bufferIndex > 1024) {  
                sb.append(buffer, 0, bufferIndex);  
                bufferIndex = 0;  
            }  
            b = c;  
            c = chars[i];  
            switch (c) {  
            case '\\':  
            case '"':  
                buffer[bufferIndex++] = '\\';  
                buffer[bufferIndex++] = c;  
                break;  
            case '/':  
                if (b == '<') {  
                    buffer[bufferIndex++] = '\\';  
                }  
                buffer[bufferIndex++] = c;  
                break;  
            default:  
                if (c < ' ') {  
                    switch (c) {  
                    case '\b':  
                        buffer[bufferIndex++] = '\\';  
                        buffer[bufferIndex++] = 'b';  
                        break;  
                    case '\t':  
                        buffer[bufferIndex++] = '\\';  
                        buffer[bufferIndex++] = 't';  
                        break;  
                    case '\n':  
                        buffer[bufferIndex++] = '\\';  
                        buffer[bufferIndex++] = 'n';  
                        break;  
                    case '\f':  
                        buffer[bufferIndex++] = '\\';  
                        buffer[bufferIndex++] = 'f';  
                        break;  
                    case '\r':  
                        buffer[bufferIndex++] = '\\';  
                        buffer[bufferIndex++] = 'r';  
                        break;  
                    default:  
                        t = "000" + Integer.toHexString(c);  
                        int tLength = t.length();  
                        buffer[bufferIndex++] = '\\';  
                        buffer[bufferIndex++] = 'u';  
                        buffer[bufferIndex++] = t.charAt(tLength - 4);  
                        buffer[bufferIndex++] = t.charAt(tLength - 3);  
                        buffer[bufferIndex++] = t.charAt(tLength - 2);  
                        buffer[bufferIndex++] = t.charAt(tLength - 1);  
                    }  
                } else {  
                    buffer[bufferIndex++] = c;  
                }  
            }  
        }  
        sb.append(buffer, 0, bufferIndex);  
        sb.append('"');  
        return sb.toString();  
    }
	
	/**
	 * 通过冒号前面的名称获取冒号后面的值
	 * 输入：fullKeyValue="name:ganjp,id:12", key="id"
	 * 输出：12
	 * @param fullKeyValue
	 * @return
	 */
	public static String getAfterColonValue(String fullKeyValue, String key) {
		int index = fullKeyValue.indexOf(key);
		if (index!=-1) {
			int nextIndex = index + key.length();
			if (nextIndex!=fullKeyValue.length() && fullKeyValue.charAt(nextIndex)==':') {
				if (fullKeyValue.indexOf(",")==-1 || fullKeyValue.lastIndexOf(",") < nextIndex) {
					return fullKeyValue.substring(nextIndex+1, fullKeyValue.length());
				} else {
					return fullKeyValue.substring(nextIndex+1, fullKeyValue.indexOf(",", nextIndex));
				}
			}
		}
		return null;
	}

	public static String toString(Object obj) {
		return toString(obj, "");
	}

	public static String toString(Object obj, String defaultVal) {
		if (obj==null) {
			return defaultVal;
		}
		return obj.toString();
	}
	
	public static String toString(String[] strs) {
		String strWithComma = "";
		if (strs==null) {
			return "";
		} else {
			for (int i = 0; i < strs.length; i++) {
				if (i==0) {
					strWithComma = strs[i]; 
				} else {
					strWithComma += "," + strs[i];
				}
			}
		}
		return strWithComma;
	}
	
	public static String getStrWithQuote(String strWithSplit) {
		String[] strArr = strWithSplit.split(",");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strArr.length; i++) {
			String value = strArr[i];
			if (i==0) {
				sb.append("'").append(value).append("'");
			} else {
				sb.append(",'").append(value).append("'");
			}
		}
		return sb.toString();
	}

	public static String getStrWithQuote(String... strArr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strArr.length; i++) {
			String value = strArr[i];
			if (i==0) {
				sb.append("'").append(value).append("'");
			} else {
				sb.append(",'").append(value).append("'");
			}
		}
		return sb.toString();
	}
	
	public static String convertForHtml(String src) {
		if (src == null || src.equals("")) {
			return src;
		}
		int length = src.length();
		StringBuffer tmp = new StringBuffer();
		for (int i = 0; i < length; i++) {
			switch (src.charAt(i)) {
				case '<':
					tmp.append("&lt;");
					break;
				case '>':
					tmp.append("&gt;");
					break;
				case '"':
					tmp.append("&quot;");
					break;
				case ' ': {
					int spaceCount = 0;
					for (; src.charAt(i) == ' '; i++, spaceCount++)
						;
					for (int j = 0; j < spaceCount / 2; j++) {
						tmp.append("　");
					}
					if (spaceCount % 2 != 0) {
						tmp.append("&#160;");
					}
					--i;
					break;
				}
				case '\n':
					tmp.append("&lt;br/&gt;");
					break;
				case '&':
					tmp.append("&amp;");
					break;
				case '\r':
					break;
				default:
					tmp.append(src.charAt(i));
					break;
			}
		}
		return tmp.toString();
	}

	/**
	 * <p/>
	 * <pre>
	 * convertSingleQuot(&quot;insert into table ('abc')&quot;) =&gt; insert into table (''abc'')
	 * </pre>
	 *
	 */
	public static String convertSingleQuot(String src) {
		if (src == null || src.equals("")) {
			return src;
		}
		int length = src.length();
		StringBuffer tmp = new StringBuffer();
		for (int i = 0; i < length; i++) {
			if (src.charAt(i) == '\'') {
				tmp.append("&#39;");
			} else {
				tmp.append(src.charAt(i));
			}
		}
		return tmp.toString();
	}

	/**
	 * <p>aCanAccessB</p>
	 * 
	 * @param a 1,2,3
	 * @param b 2,4
	 * @return
	 */
	public static boolean aCanAccessB(String a, String b) {
		boolean canAccess = true;
		if (StringUtil.isNotEmpty(b)) {
			canAccess = false;
			if (StringUtil.isNotEmpty(a)) {
				String[] aArr = a.split(",");
				for (String tmp : aArr) {
					if (b.indexOf(tmp)!=-1) {
						canAccess = true;
						break;
					} else {
						canAccess = false;
					}
				}
			}
		}
		return canAccess;
	}
	
	public static String getABC(int i) {
		String result = "Z";
		if (i==1) {
			result = "A";
		} else if (i==2) {
			result = "B";
		} else if (i==3) {
			result = "C";
		} else if (i==4) {
			result = "D";
		} else if (i==5) {
			result = "E";
		} else if (i==6) {
			result = "F";
		} else if (i==7) {
			result = "G";
		} else if (i==8) {
			result = "H";
		} else if (i==9) {
			result = "I";
		} else if (i==19) {
			result = "J";
		} else if (i==11) {
			result = "K";
		}         
		return result;
	}

	public static String toStrWithSplit(List<String> list, String split) {
		String strWithSplit = "";
		if (list!=null && !list.isEmpty()) {
			for (int i=0; i<list.size(); i++) {
				if (i==0) {
					strWithSplit = list.get(i);
				} else {
					strWithSplit += split + list.get(i);
				}
			}
		}
		return strWithSplit;
	}
	private static Logger log = LoggerFactory.getLogger(HtmlUtil.class);
	public static void main(String[] args) {
		boolean hasLength = hasLength("  ");
		boolean hasText = hasText("  ");
		
		log.debug("hasLength('  '):" + hasLength);
		log.debug("hasText('  s'):" + hasText);
	}

}
