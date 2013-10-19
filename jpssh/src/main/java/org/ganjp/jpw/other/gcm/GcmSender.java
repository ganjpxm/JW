package org.ganjp.jpw.other.gcm;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class GcmSender {
	public static void main(String[] args) {
		String msg1 = "hi";
		String msg2 = "hi";
		System.out.println(msg1 + msg2);
		HttpClient client = new DefaultHttpClient();
		HttpPost post = null;
		try {
			post = new HttpPost("https://android.googleapis.com/gcm/send");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String regisID = "APA91bGDcgSnVO3HlHVyWAwZNlYBLqAUcXQlNaeaoi3-oF9gLNL0DT8rp9ZTEU1-psY7a6cAkHjEKnbmgLsFKEJDMEbi3kieltFgNILt_HDWO8xnLQUEgKuOM_x28xyA6zHIlwUkPALxsuvgAnw1utHiVZk0cFqfa66ujjP3ENMt4o8-nBPWbOc";
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		nameValuePairs.add(new BasicNameValuePair("registration_id", regisID));

		nameValuePairs.add(new BasicNameValuePair("data1", msg1));
		nameValuePairs.add(new BasicNameValuePair("data2", msg2));

		post.setHeader("Authorization", "key=AIzaSyDKj6TV6RLlSRFi_CXPzntKx6m7U2M7mbk");
		post.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

		try {
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpResponse response = null;
		try {
			response = client.execute(post);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Hi response is : " + response.toString());

	}
}