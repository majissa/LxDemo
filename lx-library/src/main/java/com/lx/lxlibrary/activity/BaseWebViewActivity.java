package com.lx.lxlibrary.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by 李响
 * 创建日期 2016/12/21
 * 描述：
 */
public abstract class BaseWebViewActivity extends BaseActivity {
    protected WebView webView;
    public static final String URL = "url";
    private String url;

    @Override
    protected void initView(View view) {
        webView = (WebView) view.findViewById(getwebViewId());
        initOtherViews(view);
    }

    @Override
    protected void initValue() {
        url = getIntent().getStringExtra(URL);
        if (!TextUtils.isEmpty(url)) {
            webView.loadUrl(url);
        }
        WebSettings webViewSettings = webView.getSettings();
        webViewSettings.setBlockNetworkImage(true);
        webViewSettings.setJavaScriptEnabled(true);//设置webview可以执行javascript的代码
        webViewSettings.setDomStorageEnabled(true);//使用DOM存储的API
        webViewSettings.setUseWideViewPort(true);//设置流量器的可是范围，如果html中没有设置话，布局中的宽度就是会是css的宽度，如果这函数设置true的话就会适应不同手机的宽度
        webViewSettings.setDefaultTextEncodingName("utf-8");
        webViewSettings.setLoadWithOverviewMode(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//android5.0系统，sdk版本是21
            webViewSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.setWebViewClient(new MyWebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        operateWebView(webView);
    }

    protected abstract  @IdRes int getwebViewId();
    protected abstract void operateWebView(WebView webView);
    protected abstract void initOtherViews(View view);

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals("www.example.com")) {
                // This is my web site, so do not override; let my WebView load the page
                return false;
            }
            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
}
