package com.lx.lxdemo.interfaces;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by 李响
 * 创建日期 2016/12/21
 * 描述：
 */
public class webAppInterface {
    private Context context;

    /**
     * Instantiate the interface and set the context
     *
     * @param context
     */
    public webAppInterface(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(context, "JS 调用 java 里的方法成功哟。", Toast.LENGTH_LONG).show();
    }

    @JavascriptInterface
    public String addinterface() {
        return "webappinterface";
    }
}
