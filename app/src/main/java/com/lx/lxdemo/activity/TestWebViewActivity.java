package com.lx.lxdemo.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.lx.lxdemo.R;
import com.lx.lxlibrary.log.Logger;



public class TestWebViewActivity extends Activity {
	private WebView mWebView;
	private Button mButton;
	private Button mButton2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		mWebView = (WebView) findViewById(R.id.webview);
		mButton = (Button) findViewById(R.id.button);
		mButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mWebView.loadUrl("javascript:android_call_js_no_parameter()");
			}
		});
		mButton2 = (Button) findViewById(R.id.button2);
		mButton2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				testEvaluateJavascript(mWebView);
			}
		});
		webViewSetting();
	}


	@SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface" })
	@JavascriptInterface
	private void webViewSetting() {
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setDefaultTextEncodingName("utf-8");
		mWebView.loadUrl("file:///android_asset/test.html");

		AndroidJSInterface ajsi = new AndroidJSInterface(getApplicationContext());
		mWebView.addJavascriptInterface(ajsi, ajsi.getInterface());

	}

	@JavascriptInterface
	private void addJSInterface() {
		AndroidJSInterface ajsi = new AndroidJSInterface(this);
		mWebView.addJavascriptInterface(ajsi, ajsi.getInterface());
	}

	@TargetApi(Build.VERSION_CODES.ECLAIR_MR1)
	@SuppressLint("NewApi")
	private void testEvaluateJavascript(WebView webView) {
		webView.evaluateJavascript("button2click()",
				new ValueCallback<String>() {
					@Override
					public void onReceiveValue(String value) {
						mWebView.loadUrl("javascript:android_call_js_parameter('"+ value + "')");
					}
				});
	}

	public class AndroidJSInterface {
		private Context mContext;
		public AndroidJSInterface(Context context){
			mContext = context;
		}

		@JavascriptInterface
		public void showResult() {
			AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {

				@Override
				protected Void doInBackground(Void... params) {
					return null;
				}

				@Override
				protected void onPostExecute(Void aVoid) {
					Toast.makeText(mContext, "JS 调用 java 里的方法成功哟。", Toast.LENGTH_LONG).show();
					Logger.dLi("!!!!!");
				}
			};
			asyncTask.execute();


		}

		@JavascriptInterface
		public String getInterface(){
			return "android_js_interface";
		}
	}
}
