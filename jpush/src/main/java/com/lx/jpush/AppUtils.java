package com.lx.jpush;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by 李响
 * 创建日期 2017/1/9
 * 描述：
 */
public class AppUtils {
    /**
     * 判断App是否在运行
     * packageName如：com.goood.casedesign
     *
     * @return
     */
    public static boolean isAppRunning(Context context, String packageName) {
        boolean isAppRunning = false;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(Integer.MAX_VALUE);
        for (ActivityManager.RunningTaskInfo info : list) {
            if (info.topActivity.getPackageName().equals(packageName) && info.baseActivity.getPackageName().equals(packageName)) {
                isAppRunning = true;
                break;
            }
        }
        return isAppRunning;
    }
}
