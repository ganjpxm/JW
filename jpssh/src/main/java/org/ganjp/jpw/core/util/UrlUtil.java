package org.ganjp.jpw.core.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This utility class provides an abstraction layer for sending multipart HTTP
 * POST requests to a web server. 
 * @author www.codejava.net
 *
 */
public class UrlUtil {
	
	public static final String SUBMIT_PERSONAL_INFO_URL = "http://192.168.1.114:8080/dbs/services/submitPersonalInfo";
	
	/**
	 * 
	 * @param postUrl
	 * @param urlParameter "name=" + URLEncoder.encode("Greg") + "&email=" + URLEncoder.encode("greg at code dot geek dot sh");
	 */
	private static String post(String postUrl, String urlParameter) {
		URL url = null;
        try {
            url = new URL(postUrl);
        } catch (MalformedURLException ex) {
            Logger.getLogger(UrlUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        HttpURLConnection urlConn = null;
        try {
            urlConn = (HttpURLConnection) url.openConnection();
        } catch (IOException ex) {
            Logger.getLogger(UrlUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        urlConn.setDoInput (true);// Let the run-time system (RTS) know that we want input.
        urlConn.setDoOutput (true);// Let the RTS know that we want to do output.
        urlConn.setUseCaches (false);// No caching, we want the real thing.
        try {
            urlConn.setRequestMethod("POST");
        } catch (ProtocolException ex) {
            Logger.getLogger(UrlUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            urlConn.connect();
        } catch (IOException ex) {
            Logger.getLogger(UrlUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        DataOutputStream output = null;
        DataInputStream input = null;

        try {
            output = new DataOutputStream(urlConn.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(UrlUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Specify the content type if needed. urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        // Send the request data.
        try {
            output.writeBytes(urlParameter);
            output.flush();
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(UrlUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Get response data.
        String result = null;
        try {
            input = new DataInputStream (urlConn.getInputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			result = reader.readLine();
            input.close();
        } catch (IOException ex) {
            Logger.getLogger(UrlUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
	}
	
	
	public static void main(String[] args) {
	    String jsonStr= post(SUBMIT_PERSONAL_INFO_URL,"");
	    System.out.println(jsonStr);
	    //ObjectMapper mapper = new ObjectMapper();
        //Result result = mapper.readValue(jsonStr, Result.class);
    }
}