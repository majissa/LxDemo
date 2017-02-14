package com.lx.jpush;

import android.content.Context;

import cn.jpush.android.api.JPushInterface;

/**
 * 创建人：郑晓辉
 * 创建日期：2016/5/26
 * 描述：
 */
public class JPushConfig {

    public static void init(Context context, boolean debugMode) {
        JPushInterface.setDebugMode(debugMode);// 设置开启日志,发布时请关闭日志
        JPushInterface.init(context);//极光推送
    }

}
