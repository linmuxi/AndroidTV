package com.jxjr.tv;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

public class JavaScriptObject {

	//private Context context;

	public JavaScriptObject(Context context) {
		//this.context = context;
	}

	@JavascriptInterface
	public String getAllResultData(String date){
		Log.i("JavaScriptObject", date);
		return (String) HttpUtil.getInstance().post(PropertiesUtil.getInstance().getValue("url"), "{\"pass\":\""+PropertiesUtil.getInstance().getValue("pass")+"\",\"date\":\""+date+"\"}");			
	}
}
