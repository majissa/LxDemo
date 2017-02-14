package com.lx.lxdemo.common;

import com.lx.lxdemo.BuildConfig;

import java.util.List;
import java.util.Map;

/**
 * 创建人：LX
 * 创建日期：2016/7/12
 * 描述：使用BuildConfig.DEBUG控制，在调试的可以显示，发布后就不显示log
 */
public class LLogger {

    /**
     * Send a DEBUG log message.
     *
     * @param tag Used to identify the source of a log message. It usually
     *            identifies the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void d(String tag, String msg) {
        if (BuildConfig.DEBUG)
            android.util.Log.d(tag, msg);
    }

    /**
     * Send an ERROR log message.
     *
     * @param tag Used to identify the source of a log message. It usually
     *            identifies the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void e(String tag, String msg) {
        if (BuildConfig.DEBUG)
            android.util.Log.e(tag, msg);
    }

    /**
     * Send a ERROR log message and log the exception.
     *
     * @param tag Used to identify the source of a log message. It usually
     *            identifies the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    public static void e(String tag, String msg, Throwable tr) {
        if (BuildConfig.DEBUG)
            android.util.Log.e(tag, msg, tr);
    }

    /**
     * Send a VERBOSE log message.
     *
     * @param tag Used to identify the source of a log message. It usually
     *            identifies the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void v(String tag, String msg) {
        if (BuildConfig.DEBUG)
            android.util.Log.v(tag, msg);
    }

    /**
     * Send a WARN log message.
     *
     * @param tag Used to identify the source of a log message. It usually
     *            identifies the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void w(String tag, String msg) {
        if (BuildConfig.DEBUG)
            android.util.Log.w(tag, msg);
    }

    /**
     * Send a INFO log message.
     *
     * @param tag Used to identify the source of a log message. It usually
     *            identifies the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void i(String tag, String msg) {
        if (BuildConfig.DEBUG)
            android.util.Log.i(tag, msg);
    }

    /**
     * 打印Map<String, String>类型
     *
     * @param tag
     * @param parames
     */
    public static void d(String tag, Map<String, String> parames) {
        if (BuildConfig.DEBUG)
            for (Map.Entry entry : parames.entrySet()) {
                String strKey = (String) entry.getKey();
                String strValue = entry.getValue().toString();
                android.util.Log.d(tag, strKey);
                android.util.Log.d(tag, strValue);
            }
    }

    /**
     * 打印List<String>类型
     *
     * @param tag
     * @param parames
     */
    public static void d(String tag, List<String> parames) {
        if (BuildConfig.DEBUG)
            for (String strContent : parames) {
                android.util.Log.d(tag, strContent);
            }
    }


    /**
     * li工程师打出来的dlog
     *
     * @param msg
     */
    public static void dLi(String msg) {
        if (BuildConfig.DEBUG)
            android.util.Log.d("lixiang", msg);
    }

    /**
     * 点击测试
     */
    public static void dClick() {
        if (BuildConfig.DEBUG)
            android.util.Log.d("Lixiang", "clicked!");
    }

}
