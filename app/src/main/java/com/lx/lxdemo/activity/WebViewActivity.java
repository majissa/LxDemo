package com.lx.lxdemo.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;

import com.lx.lxdemo.R;
import com.lx.lxdemo.interfaces.AndroidJSInterface;
import com.lx.lxlibrary.activity.BaseWebViewActivity;

/**
 * Created by 李响
 * 创建日期 2016/12/21
 * 描述：
 */
public class WebViewActivity extends BaseWebViewActivity {
    private Button mButton;
    private Button mButton2;

    @Override
    protected void operateWebView(WebView webView) {
        webViewSetting();
    }

    @Override
    protected void initOtherViews(View view) {
        mButton = (Button) view.findViewById(R.id.button);
        mButton2 = (Button) view.findViewById(R.id.button2);
    }

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    protected int getwebViewId() {
        return R.id.webview;
    }

    @Override
    protected void initEvent() {

        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                webView.loadUrl("javascript:android_call_js_no_parameter()");
            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                testEvaluateJavascript(webView);
            }
        });
    }

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    @JavascriptInterface
    private void webViewSetting() {
        webView.getSettings().setJavaScriptEnabled(true);
        AndroidJSInterface ajsi = new AndroidJSInterface(this);
        webView.addJavascriptInterface(ajsi, ajsi.getInterface());
        webView.loadUrl("file:///android_asset/test.html");
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });

    }

    @Override
    public void onClick(View v) {

    }

    @TargetApi(Build.VERSION_CODES.ECLAIR_MR1)
    @SuppressLint("NewApi")
    private void testEvaluateJavascript(final WebView webView) {
        webView.evaluateJavascript("button2click()",
                new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        webView.loadUrl("javascript:android_call_js_parameter('" + value + "')");
                    }
                });
    }
}
