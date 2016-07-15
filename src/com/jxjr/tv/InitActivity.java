package com.jxjr.tv;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class InitActivity extends Activity {

	@Override
	@SuppressLint("SetJavaScriptEnabled")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);// ȥ����Ϣ��,����ȫ��
		this.getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);// ������Ļ�����

		setContentView(R.layout.activity_init);

		WebView webview = (WebView) findViewById(R.id.initWebView);
		WebSettings settings = webview.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setDefaultTextEncodingName("UTF-8");// �����ַ�����
		webview.setScrollBarStyle(0);// ���������Ϊ0ָ��������ռ�ÿռ䣬ֱ�Ӹ�������ҳ��
		webview.loadUrl("file:///android_asset/html/init.html");
		webview.addJavascriptInterface(new JavaScriptObject(this), "ServerObj");
		webview.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				Bundle data = new Bundle();
				data.putString("data", (String) HttpUtil.getInstance().post(PropertiesUtil.getInstance().getValue("url"), 
						"{\"pass\":\""+PropertiesUtil.getInstance().getValue("pass")+"\",\"date\":\"\"}"));
				Message msg = new Message();
				msg.setData(data);
				handler.sendMessage(msg);
			}
		}).start();
		
	}
	
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Bundle bundle = msg.getData();
			String data = bundle.getString("data");
			Intent mainIntent = new Intent(InitActivity.this,MainActivity.class);
			mainIntent.putExtra("data", data);
			startActivity(mainIntent);
		}
	};
}

