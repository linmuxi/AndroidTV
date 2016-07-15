package com.jxjr.tv;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
	
	private ProgressDialog dialog;
	
    @Override
	@SuppressLint("SetJavaScriptEnabled")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏,保持全屏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//保持屏幕不变黑

        setContentView(R.layout.activity_main);
        
		//JavaScriptObject.data = this.getIntent().getExtras().getString("data");
        
        WebView webview = (WebView) findViewById(R.id.webView);
		WebSettings settings = webview.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setDefaultTextEncodingName("UTF-8");//设置字符编码  
		webview.setScrollBarStyle(0);//滚动条风格，为0指滚动条不占用空间，直接覆盖在网页上  
		webview.loadUrl("file:///android_asset/html/tv.html");
		//webview.loadUrl("file:///android_asset/html/1.html");
		webview.addJavascriptInterface(new JavaScriptObject(this),"ServerObj");
		/*if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
			WebView.setWebContentsDebuggingEnabled(true);
		}*/
		 
        dialog = ProgressDialog.show(this,null,"数据加载中，请稍后..");  
        
		webview.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view,
					String url) {
				view.loadUrl(url);
				return true;
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				//Toast.makeText(MainActivity.this, "页面加载完成 onPageFinished", Toast.LENGTH_LONG).show();
				//dialog.dismiss();
			}
		});
		
		webview.setWebChromeClient(new WebChromeClient(){
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				Log.i("XX", newProgress+"");
				if(newProgress == 100){
					//Toast.makeText(MainActivity.this, "页面加载完成 onProgressChanged", Toast.LENGTH_LONG).show();
					dialog.dismiss();
				}
			}
		});
		
		
    }
}
