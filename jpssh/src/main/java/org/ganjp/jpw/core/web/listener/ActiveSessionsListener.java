package org.ganjp.jpw.core.web.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.ganjp.jpw.am.model.AmUser;
import org.ganjp.jpw.core.Config;
import org.ganjp.jpw.core.Const;

/**
 * <p>ActiveSessionsListener</p>
 * 
 * @author johnny
 *
 */
public class ActiveSessionsListener implements HttpSessionListener  {  
	private static int totalActiveSessions=0;
	
	
	public static int getTotalActiveSession(){
	  	return totalActiveSessions;
	}
	
	public static void subTotalActiveSession(){
	  	totalActiveSessions--;
	}
	
	public static void addTotalActiveSession(){
	  	totalActiveSessions++;
	}
	
    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
    	//totalActiveSessions++;
//    	sessionEvent.getSession().setMaxInactiveInterval(5);
    	//System.out.println("sessionCreated");
    }
 
    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
    	//totalActiveSessions--;
    	//System.out.println("sessionDestroyed - deduct one session from counter");
    	
    	Object obj = null;  
        try {  
            obj = sessionEvent.getSession().getAttribute(Const.USER);
        } catch (Exception e) {
            obj = null;
        }  
        if (null != obj) {  
        	AmUser amUser = (AmUser) obj;
        	Config.loginAmUserMap.remove(amUser.getUserId());
        	System.out.println(amUser.getUserName() + " sign out");
        }  
    }
    
    /**
    private ServletContext context = null;  
  
    public ActiveSessionsListener(ServletContext context) {  
        if (null == context) {  
            throw new IllegalArgumentException("Null servletContent is accept.");  
        }  
        this.context = context;  
    }  
    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {  
        ConcurrentHashMap<String, Object> map = (ConcurrentHashMap<String, Object>) context.getAttribute(Const.ACTIVE_SESSIONS);  
  
        if (null == map) {  
            map = new ConcurrentHashMap<String, Object>();  
            context.setAttribute(Const.ACTIVE_SESSIONS, map);  
        }  
  
        HttpSession session = sessionEvent.getSession();  
        map.put(session.getId(), session.getAttribute(Const.USER) + "   Login Time :" + new Date().toLocaleString());  
  
        context.setAttribute(Const.ACTIVE_SESSIONS, map);
    }
  
  
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {  
        Object obj = null;  
        try {  
            obj = sessionEvent.getSession().getAttribute(Const.USER);
        } catch (Exception e) {
            obj = null;
        }  
        if (null == obj) {  
            ConcurrentHashMap<String, Object> map = (ConcurrentHashMap<String, Object>) context.getAttribute(Const.ACTIVE_SESSIONS);  
            if (null != map) {  
                if(map.containsKey(sessionEvent.getSession().getId())) {  
                    map.remove(sessionEvent.getSession().getId());  
                    context.setAttribute(Const.ACTIVE_SESSIONS, map);  
                }  
            }  
        }  
    }  
    */
}  