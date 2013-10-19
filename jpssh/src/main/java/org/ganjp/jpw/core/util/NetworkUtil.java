/**
 * NetworkUtil.java
 * 
 * Created by Gan Jianping on 12/09/13.
 * Copyright (c) 2013 GANJP. All rights reserved.
 */
package org.ganjp.jpw.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>Network Utility</p>
 * 
 * @author Gan Jianping
 * @since 1.0.0
 */
public class NetworkUtil {
   
	
	/**
	 * <p>Post to server</P>
	 * 
	 * @param postUrl
	 * @param urlParameter "name=" + URLEncoder.encode("Greg") + "&email=" + URLEncoder.encode("greg at code dot geek dot sh");
	 */
	public static String post(String postUrl, String urlParameter) {
		URL url = null;
        try {
            url = new URL(postUrl);
        } catch (MalformedURLException ex) {
            Logger.getLogger(NetworkUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        HttpURLConnection urlConn = null;
        try {
            urlConn = (HttpURLConnection) url.openConnection();
        } catch (IOException ex) {
            Logger.getLogger(NetworkUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        urlConn.setDoInput (true);// Let the run-time system (RTS) know that we want input.
        urlConn.setDoOutput (true);// Let the RTS know that we want to do output.
        urlConn.setUseCaches (false);// No caching, we want the real thing.
        try {
            urlConn.setRequestMethod("POST");
        } catch (ProtocolException ex) {
            Logger.getLogger(NetworkUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            urlConn.connect();
        } catch (IOException ex) {
            Logger.getLogger(NetworkUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        DataOutputStream output = null;
        DataInputStream input = null;

        try {
            output = new DataOutputStream(urlConn.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(NetworkUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Specify the content type if needed. urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        // Send the request data.
        try {
            output.writeBytes(urlParameter);
            output.flush();
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(NetworkUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Get response data.
        String result = null;
        try {
            input = new DataInputStream (urlConn.getInputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			result = reader.readLine();
            input.close();
        } catch (IOException ex) {
            Logger.getLogger(NetworkUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
	}
	
	public static boolean canAccess(String urlStr) {

        boolean canAccess = false;

        //make the android homepage as the requested url
        //this is a critical decision point.
        //the belief here is that this url can be accessed all around the world

        final int TIMEOUT_VALUE_IN_MILLISECONDS = 6000;

        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(urlStr);
            urlConnection = (HttpURLConnection) url.openConnection();

            //set the respective timeouts for the connection
            urlConnection.setConnectTimeout(TIMEOUT_VALUE_IN_MILLISECONDS);
            urlConnection.setReadTimeout(TIMEOUT_VALUE_IN_MILLISECONDS);
            urlConnection.connect();
            //the redirect check is valid only after the response headers have been received this is triggered by the getInputStream() method
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            System.out.println(FileUtil.getStringFromInputStream(in));
            //check if the urls of the requested and landing pages are equal if yes, then we don't have a redirect
            if (url.getHost().equals(urlConnection.getURL().getHost())) 
                canAccess =  true;
        } catch (Exception e) {
        	System.out.print(e);
        }
        finally {
            urlConnection.disconnect();
        }

        return canAccess;
    }
	
	public static void main(String[] args) {
		System.out.println(canAccess("http://192.168.1.100:8080/jp"));
	}
}
