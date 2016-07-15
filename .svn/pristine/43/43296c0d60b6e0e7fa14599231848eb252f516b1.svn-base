package com.jxjr.tv;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;


public class HttpUtil {
	private static final HttpUtil util = new HttpUtil();
	
	private static HttpClient httpClient;
	
	private HttpUtil(){
		httpClient = new DefaultHttpClient();
	}
	
	public static HttpUtil getInstance(){
		return util;
	}
	
	public Object get(String url){
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			return EntityUtils.toString(httpResponse.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object post(String url,String params){
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader(HTTP.CONTENT_TYPE, "appliction/json");
		httpPost.addHeader(HTTP.CONTENT_ENCODING, "UTF-8");
		try {
			httpPost.setEntity(new StringEntity(params));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			return EntityUtils.toString(httpResponse.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
