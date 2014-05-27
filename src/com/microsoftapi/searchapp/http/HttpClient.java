package com.microsoftapi.searchapp.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.microsoftapi.searchapp.request.Request;

public class HttpClient {
	
		URL url;
		String URL_str;
		
		public HttpClient() {
			
		}
	
		public InputStream getResponseInputStream(String URL_str){
			
			InputStream response = null;
			this.URL_str = URL_str;
			
			try {
				url = new URL(this.URL_str);
				HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
				httpConn.setRequestMethod("GET");
				httpConn.connect();
				response = (InputStream)httpConn.getContent();
				return response;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return response;
		}
		
}
