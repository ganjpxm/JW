/**
 * $Id: RedOxygenSMS.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.core.other;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class RedOxygenSMS {
	public static String strResponse = "";

	public static int SendSMS(String strAccountId, String strEmail, String strPassword, String strMSISDN, String strMessage) {
		String sRequestURL;
		String sData;
		int nResult = -1;
		sRequestURL = "http://www.redoxygen.net/sms.dll?Action=SendSMS";
		try {
			sData = ("AccountId=" +	URLEncoder.encode(strAccountId,"UTF-8"));
				sData += ("&Email=" + URLEncoder.encode(strEmail,"UTF-8"));
				sData += ("&Password=" + URLEncoder.encode(strPassword,"UTF-8"));
				sData += ("&Recipient=" + URLEncoder.encode(strMSISDN,"UTF-8"));
				sData += ("&Message=" + URLEncoder.encode(strMessage,"UTF-8"));
				URL urlObject = new URL(sRequestURL);
				HttpURLConnection con = (HttpURLConnection)	urlObject.openConnection();
				con.setRequestMethod("POST");
				con.setDoInput (true);
				con.setDoOutput (true);
				DataOutputStream out = new DataOutputStream(con.getOutputStream());
				out.writeBytes (sData);
				out.flush();
				out.close();
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer responseBuffer = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					responseBuffer = responseBuffer.append(inputLine);
					responseBuffer = responseBuffer.append("\n\n\n");
				}
				strResponse = responseBuffer.toString();
				String sResultCode = strResponse.substring(0,4);
				nResult = Integer.parseInt(sResultCode);
				in.close();
		} catch (Exception e) {
			System.out.println("Exception caught sending SMS\n");
			nResult = -2;
		}
		return nResult;
	}

	public static void main(String[] args) throws Exception {
		String strAccountId = "CI00088533"; // Put your	AccountId here
		String strEmail = "ganjpxm@gmail.com"; // Put your Email address here
		String strPassword = "1ybUcaQ1"; // Put your Password here
		String strMSISDN = "83100171"; // Put a recipient mobile number here
		String strMessage = "Test SMS via Red Oxygen API"; //Put your SMS message text here
		int nResult = SendSMS(strAccountId,strEmail,strPassword,strMSISDN,strMessage);
		System.out.println("Result Code = " + nResult + "\n");
		System.out.println("Result Text = " + strResponse + "\n");
	}
}

