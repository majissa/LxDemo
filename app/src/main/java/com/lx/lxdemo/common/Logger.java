package com.lx.lxdemo.common;

import java.util.List;
import java.util.Map;

/**
 * 创建人：LX
 * 创建日期：2016/7/12
 * 描述：统一打印log
 */
public class Logger {


    private final static String TAG = "Lixiang";

    /**
     * Checks to see whether or not a log for the specified tag is loggable at the specified level.
     * <p/>
     * The default level of any tag is set to INFO. This means that any level above and including
     * INFO will be logged. Before you make any calls to a logging method you should check to see
     * if your tag should be logged. You can change the default level by setting a system property:
     * 'setprop log.tag.<YOUR_LOG_TAG> <LEVEL>'
     * Where level is either VERBOSE, DEBUG, INFO, WARN, ERROR, ASSERT, or SUPPRESS. SUPPRESS will
     * turn off all logging for your tag. You can also create a local.prop file that with the
     * following in it:
     * 'log.tag.<YOUR_LOG_TAG>=<LEVEL>'
     * and place that in /data/local.prop.
     *
     * @param tag The tag to check.
     * @param level The level to check.
     * @return Whether or not that this is allowed to be logged.
     * @throws IllegalArgumentException is thrown if the tag.length() > 23.
     * <p/>
     * 作用：用于统一关闭或者打开日志
     * 操作：1.例如：参数1：当前的TAG="Lixiang".参数2：你需要的level为DEBUG等级。
     * 2.第二个参数可以是 VERBOSE < DEBUG < INFO < WARN < ERROR < ASSERT, or SUPPRESS ，【V,D,I,W,E,A,S】设置参数等级大于当前adb设置的等级返回true，小于则放回false
     * 2.打开Terminal，输入adb shell setprop log.tag.Lixiang D;
     * 结果：只有当前这个手机TAG为Lixiang的标志都可以显示了，即返回回true（如果重启或者换一部手机将不显示）。
     * 注意：跟BuildConfig.DEBUG还是不同的，此方法打包发布后仍然是可以显示。只是除了开发者的手机不能显示。
     */
//    private final static boolean isDbug = Log.isLoggable(TAG, Log.DEBUG);

    private final static boolean isDbug = true;

    /**
     * Send a DEBUG log message.
     *
     * @param tag Used to identify the source of a log message. It usually
     *            identifies the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void d(String tag, String msg) {
        if (isDbug)
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
        if (isDbug)
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
        if (isDbug)
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
        if (isDbug)
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
        if (isDbug)
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
        if (isDbug)
            android.util.Log.i(tag, msg);
    }

    /**
     * 打印Map<String, String>类型
     *
     * @param tag
     * @param parames
     */
    public static void d(String tag, Map<String, String> parames) {
        if (isDbug)
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
        if (isDbug)
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
        if (isDbug)
            android.util.Log.d("lixiang", msg);
    }

    /**
     * 点击测试
     */
    public static void dClick() {
        if (isDbug)
            android.util.Log.d("lixiang", "clicked!");
    }
}
