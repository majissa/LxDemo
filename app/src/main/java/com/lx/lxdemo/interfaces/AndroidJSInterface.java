package com.lx.lxdemo.interfaces;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.lx.lxlibrary.log.Logger;

public class AndroidJSInterface {
	private Context mContext;
	public AndroidJSInterface(Context context){
		mContext = context;
	}
	
	@JavascriptInterface
    public void showResult() {
        Toast.makeText(mContext, "JS 调用 java 里的方法成功哟。", Toast.LENGTH_LONG).show();
		Logger.dLi("!!!!!");
    }
	
	@JavascriptInterface
	public String getInterface(){
		return "android_js_interface";
	}
}
