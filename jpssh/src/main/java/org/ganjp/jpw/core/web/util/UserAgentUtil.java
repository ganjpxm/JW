package org.ganjp.jpw.core.web.util;

import java.util.HashMap;
import java.util.Map;

public class UserAgentUtil {  
	public static final String DEVICE_VENDER = "deviceVender";
	public static final String DEVICE_SERIES = "deviceSeries";
	public static final String DEVICE_VERSION = "deviceVersion";
	public static final String BROWSER_TYPE = "browserType";
	public static final String BROWSER_VERSION = "browserVersion";
	public static final String PLATFORM_TYPE = "platformType";
	public static final String PLATFORM_SERIES = "platformSeries";
	public static final String PLATFORM_VERSION = "platformVersion";
    
    /** 
     * <P>getUserAgent</P>
     * @param userAgentStr 
     * @return 
     */  
    public static Map<String,String> getUserAgent(String userAgent) {  
    	Map<String,String> map = new HashMap<String,String>();
          
        if (userAgent.contains("Windows")) { 
            /** 
             * Windows NT 6.2   -   Windows 8 
             * Windows NT 6.1   -   Windows 7 
             * Windows NT 6.0   -   Windows Vista 
             * Windows NT 5.2   -   Windows Server 2003; Windows XP x64 Edition 
             * Windows NT 5.1   -   Windows XP 
             * Windows NT 5.01  -   Windows 2000, Service Pack 1 (SP1) 
             * Windows NT 5.0   -   Windows 2000 
             * Windows NT 4.0   -   Microsoft Windows NT 4.0 
             * Windows 98; Win 9x 4.90  -   Windows Millennium Edition (Windows Me) 
             * Windows 98   -   Windows 98 
             * Windows 95   -   Windows 95 
             * Windows CE   -   Windows CE 
             * http://msdn.microsoft.com/en-us/library/ms537503(v=vs.85).aspx 
             */  
            if (userAgent.contains("Windows NT 6.2")) {//Windows 8  
            	map.put(PLATFORM_SERIES, "8");
            } else if (userAgent.contains("Windows NT 6.1")) {//Windows 7  
            	map.put(PLATFORM_SERIES, "7");
            } else if (userAgent.contains("Windows NT 6.0")) {//Windows Vista  
            	map.put(PLATFORM_SERIES, "Vista");
            } else if (userAgent.contains("Windows NT 5.2")) {//Windows XP x64 Edition  
            	map.put(PLATFORM_SERIES, "XP");
            	map.put(PLATFORM_VERSION, "x64 Edition");
            } else if (userAgent.contains("Windows NT 5.1")) {//Windows XP  
            	map.put(PLATFORM_SERIES, "XP");
            } else if (userAgent.contains("Windows NT 5.01")) {//Windows 2000, Service Pack 1 (SP1)  
            	map.put(PLATFORM_SERIES, "2000");
            	map.put(PLATFORM_VERSION, "SP1");
            } else if (userAgent.contains("Windows NT 5.0")) {//Windows 2000  
            	map.put(PLATFORM_SERIES, "2000");
            } else if (userAgent.contains("Windows NT 4.0")) {//Microsoft Windows NT 4.0  
            	map.put(PLATFORM_SERIES, "NT 4.0");
            } else if (userAgent.contains("Windows 98; Win 9x 4.90")) {//Windows Millennium Edition (Windows Me)  
            	map.put(PLATFORM_SERIES, "ME");
            } else if (userAgent.contains("Windows 98")) {//Windows 98  
            	map.put(PLATFORM_SERIES, "98");
            } else if (userAgent.contains("Windows 95")) {//Windows 95  
            	map.put(PLATFORM_SERIES, "95");
            } else if (userAgent.contains("Windows CE")) {//Windows CE  
            	map.put(PLATFORM_SERIES, "CE");
            }   
            map.put(PLATFORM_TYPE, "Windows");
            judgeBrowser(userAgent, map);
        } else if (userAgent.contains("Mac OS X")) {  
            /** 
             * iPod -       Mozilla/5.0 (iPod; U; CPU iPhone OS 4_3_1 like Mac OS X; zh-cn) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8G4 Safari/6533.18.5 
             * iPad -       Mozilla/5.0 (iPad; U; CPU OS 3_2 like Mac OS X; en-us) AppleWebKit/531.21.10 (KHTML, like Gecko) Version/4.0.4 Mobile/7B334b Safari/531.21.10 
             * iPad2    -       Mozilla/5.0 (iPad; CPU OS 5_1 like Mac OS X; en-us) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9B176 Safari/7534.48.3 
             * iPhone 4 -   Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_0 like Mac OS X; en-us) AppleWebKit/532.9 (KHTML, like Gecko) Version/4.0.5 Mobile/8A293 Safari/6531.22.7 
             * iPhone 5 -   Mozilla/5.0 (iPhone; CPU iPhone OS 5_0 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9A334 Safari/7534.48.3 
             * http://www.useragentstring.com/pages/Safari/ 
             * http://stackoverflow.com/questions/7825873/what-is-the-ios-5-0-user-agent-string 
             * http://stackoverflow.com/questions/3105555/what-is-the-iphone-4-user-agent 
             */  
            if (userAgent.contains("iPhone")) {  
            	map.put(DEVICE_SERIES, "iPhone");
            	map.put(PLATFORM_TYPE, "iOS");
            } else if (userAgent.contains("iPad")) {  
            	map.put(DEVICE_SERIES, "iPad");
            	map.put(PLATFORM_TYPE, "iOS");
            } else if (userAgent.contains("iPod")) {  
            	map.put(DEVICE_SERIES, "iPod");
            	map.put(PLATFORM_TYPE, "iOS");
            } else {
            	map.put(PLATFORM_TYPE, "Macx");
            }
        } else if (userAgent.contains("Android")) {
        	/**
        	 * Mozilla/5.0 (Linux; U; Android 4.0.3; ko-kr; LG-L160L Build/IML74K) AppleWebkit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30
        	 */
        	map.put(PLATFORM_TYPE, "Android");
        }
        
        judgeBrowser(userAgent, map);
        return map;
    }  
      
    /** 
     *  >>Browser:Chrome > FF > IE > ... 
     *  
     * @param userAgent:user agent 
     * @param Map 
     * @return 
     */  
    private static void judgeBrowser(String userAgent, Map<String,String> map) {  
    	String browserType = "Other";
    	String browserVersion = "";
        if (userAgent.contains("Chrome")) {  
            /** 
             * Chrome 24.0.1295.0   -   Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.15 (KHTML, like Gecko) Chrome/24.0.1295.0 Safari/537.15 
             * Chrome 24.0.1292.0   -   Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.14 (KHTML, like Gecko) Chrome/24.0.1292.0 Safari/537.14 
             * Chrome 24.0.1290.1   -   Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) AppleWebKit/537.13 (KHTML, like Gecko) Chrome/24.0.1290.1 Safari/537.13 
             * http://www.useragentstring.com/pages/Chrome/ 
             */  
            String temp = userAgent.substring(userAgent.indexOf("Chrome/") + 7);//User Agent String "Chrome/" "24.0.1295.0 Safari/537.15" or "24.0.1295.0"  
            if (temp.indexOf(" ") < 0) {//temp "24.0.1295.0"  
            	browserVersion = temp;  
            } else { //temp "24.0.1295.0 Safari/537.15"  
            	browserVersion = temp.substring(0, temp.indexOf(" "));  
            }  
            browserType = "Chrome";
        } else if (userAgent.contains("Firefox")) {  
            /** 
             * Firefox 16.0.1   -   Mozilla/5.0 (Windows NT 6.2; Win64; x64; rv:16.0.1) Gecko/20121011 Firefox/16.0.1 
             * Firefox 15.0a2   -   Mozilla/5.0 (Windows NT 6.1; rv:15.0) Gecko/20120716 Firefox/15.0a2 
             * Firefox 15.0.2   -   Mozilla/5.0 (Windows NT 6.2; WOW64; rv:15.0) Gecko/20120910144328 Firefox/15.0.2 
             * http://www.useragentstring.com/pages/Firefox/ 
             */  
            String temp = userAgent.substring(userAgent.indexOf("Firefox/") + 8);//User Agent String "Firefox/" "16.0.1 Gecko/20121011" or "16.0.1"  
            if (temp.indexOf(" ") < 0) {//"16.0.1"  
            	browserVersion = temp;  
            } else {//"16.0.1 Gecko/20121011"  
            	browserVersion = temp.substring(0, temp.indexOf(" "));  
            }  
            browserType = "Firefox";
        } else if (userAgent.contains("MSIE")) {  
            /** 
             * MSIE 10.0    -   Internet Explorer 10 
             * MSIE 9.0 -   Internet Explorer 9 
             * MSIE 8.0 -   Internet Explorer 8 or IE8 Compatibility View/Browser Mode 
             * MSIE 7.0 -   Windows Internet Explorer 7 or IE7 Compatibility View/Browser Mode 
             * MSIE 6.0 -   Microsoft Internet Explorer 6 
             * http://msdn.microsoft.com/en-us/library/ms537503(v=vs.85).aspx 
             */  
            if (userAgent.contains("MSIE 10.0")) {//Internet Explorer 10  
            	browserVersion = "10";  
            } else if (userAgent.contains("MSIE 9.0")) {//Internet Explorer 9  
            	browserVersion = "9";
            } else if (userAgent.contains("MSIE 8.0")) {//Internet Explorer 8  
            	browserVersion = "8";  
            } else if (userAgent.contains("MSIE 7.0")) {//Internet Explorer 7  
            	browserVersion = "7";  
            } else if (userAgent.contains("MSIE 6.0")) {//Internet Explorer 6  
            	browserVersion = "6";  
            } else {
            	browserVersion = "5";
            }
            browserType = "Internet Explorer";
        }
        map.put(UserAgentUtil.BROWSER_TYPE, browserType);
        map.put(UserAgentUtil.BROWSER_VERSION, browserVersion); 
    }  
}  