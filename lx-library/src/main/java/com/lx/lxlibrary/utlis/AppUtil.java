package com.lx.lxlibrary.utlis;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.FileCallback;

import java.io.File;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 创建人：郑晓辉
 * 创建日期：2016/3/18
 * 描述：
 */
public class AppUtil {

    /**
     * 获取应用程序名称
     *
     * @return
     */
    public static String getApplicationName(Context context) {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        String applicationName = (String) packageManager.getApplicationLabel(applicationInfo);
        return applicationName;
    }

    /**
     * 获得软件版号versionCode
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        try {
            PackageInfo packInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packInfo.versionCode;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 获得软件版号versionName
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        try {
            PackageInfo packInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packInfo.versionName;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取apk包的信息
     *
     * @return
     */
    public static PackageInfo getPackageInfo(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packInfo = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            return packInfo;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取application下的metaData值
     *
     * @param context
     * @param metaKey
     * @return
     */
    public static String getApplicationMetaValue(Context context, String metaKey) {
        Bundle metaData = null;
        if (context == null || metaKey == null) {
            return null;
        }
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                Object apiKey = metaData.get(metaKey);
                if (null != apiKey) {
                    return apiKey.toString();
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        return null;
    }

    /**
     * 获取activity 中的的metaData值
     *
     * @param
     * @param metaKey
     * @return
     */
    public static String getActivityMetaValue(Activity activity, String metaKey) {
        Bundle metaData = null;
        if (activity == null || metaKey == null) {
            return null;
        }
        try {
            ActivityInfo ai = activity.getPackageManager().getActivityInfo(activity.getComponentName(), PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                Object apiKey = metaData.get(metaKey);
                if (null != apiKey) {
                    return apiKey.toString();
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        return null;
    }

    /**
     * 判断第三方应用是否安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            PackageManager pm = context.getPackageManager();
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * 判断软件是否已安装
     *
     * @param apkFilePath
     * @return true 已安装,false 未安装
     */
    public static boolean checkIsInstalled(Context context, String apkFilePath) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(
                    getPackagePathOfApk(context, apkFilePath), 0);

        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
            // e.printStackTrace();
        }
        if (packageInfo == null) {
            System.out.println("没有安装");
            return false;
        } else {
            System.out.println("已经安装");
            return true;
        }
    }

    /**
     * 安装一个apk文件
     */
    private static void install(Context context, File uriFile) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(uriFile), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   // 询问完成安装还是打开
        context.startActivity(intent);
    }

    /**
     * 安装APK
     *
     * @param context
     * @param apkFilePath
     */
    public static void install(Context context, String apkFilePath) {
        Intent intallIntent = new Intent(Intent.ACTION_VIEW);
        intallIntent.setDataAndType(Uri.parse("file://" + apkFilePath), "application/vnd.android.package-archive");
        context.startActivity(intallIntent);
    }

    /**
     * 卸载
     *
     * @param context
     */
    public static void uninstall(Context context) {
        Uri packageURI = Uri.parse("package:" + context.getPackageName());
        Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
        context.startActivity(uninstallIntent);
    }

    /**
     * 读取本地apk文件的包路径
     *
     * @param apkFilePath
     * @return 返回包路径, 如果文件不存在, 则返回空字符串
     */
    public static String getPackagePathOfApk(Context context, String apkFilePath) {
        String packagePath = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageArchiveInfo(apkFilePath,
                    PackageManager.GET_ACTIVITIES);
            if (info != null) {
                ApplicationInfo appInfo = info.applicationInfo;
                packagePath = appInfo.packageName; // 得到安装包名称
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return packagePath;
    }

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

    /**
     * 得到该APP的launch页面
     *
     * @param context
     * @return
     */
    public static Intent getLaunchIntent(Context context) {
        return context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
    }

    /**
     * 判断服务已经启动运行
     *
     * @param context
     * @param serviceFullName serviceFullName service全名
     * @return
     */
    public static boolean isServiceLaungle(Context context, String serviceFullName) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> infos = am.getRunningServices(30); //30是最大值
        for (ActivityManager.RunningServiceInfo info : infos) {
            if (info.service.getClassName().equals(serviceFullName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断服务是否在运行
     *
     * @return
     */
    public static boolean  isServiceRuning(Context context, Class<?> cls) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServices = manager.getRunningServices(Integer.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo runningService : runningServices) {
            if (cls.getName().equals(runningService.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 添加当前应用的桌面快捷方式
     *
     * @param context
     */
    public static void createShortcut(Context context, int resId, int appNameResId) {
        if (hasShortcut(context, appNameResId)) {//自己加的代码，还没测试是不是要加
            return;
        }
        final PackageManager pm = context.getPackageManager();
        final String packageName = context.getPackageName();
        Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        Intent shortcutIntent = pm.getLaunchIntentForPackage(packageName);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        // 获取当前应用名称
        String title = null;
        try {
            title = pm.getApplicationLabel(pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA)).toString();
        } catch (Exception e) {
        }
        if (null == title) {
            title = context.getString(appNameResId);
        }
        // 快捷方式名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
        // 不允许重复创建（不一定有效）
        shortcut.putExtra("duplicate", false);
        // 快捷方式的图标
        Intent.ShortcutIconResource iconResource = Intent.ShortcutIconResource.fromContext(context, resId);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconResource);
        context.sendBroadcast(shortcut);
    }

    // 判断是否存在快捷方式
    public static boolean hasShortcut(Context context, int appNameResId) {
        final PackageManager pm = context.getPackageManager();
        final String packageName = context.getPackageName();
        // 获取当前应用名称
        String title = null;
        try {
            title = pm.getApplicationLabel(pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA)).toString();
        } catch (Exception e) {
        }
        if (null == title) {
            title = context.getString(appNameResId);
        }
        String url = "";
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO) {
            url = "content://com.android.launcher.settings/favorites?notify=true";
        } else {
            url = "content://com.android.launcher2.settings/favorites?notify=true";
        }
        Cursor cursor = null;
        try {
            ContentResolver resolver = context.getContentResolver();
            cursor = resolver.query(Uri.parse(url), null, "title=?", new String[]{title}, null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.close();
                return true;
            }
        } catch (Exception e) {
        } finally {
            if (null != cursor) {
                cursor.close();
            }
        }
        return false;
    }

    /**
     * 删除当前应用的桌面快捷方式
     *
     * @param context
     */
    public static void delShortcut(Context context, int appNameResId) {
        Intent shortcut = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
        // 获取当前应用名称
        String title = null;
        final PackageManager pm = context.getPackageManager();
        final String packageName = context.getPackageName();
        try {
            title = pm.getApplicationLabel(pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA)).toString();
        } catch (Exception e) {
        }
        if (null == title) {
            title = context.getString(appNameResId);
        }
        // 快捷方式名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
        Intent shortcutIntent = pm.getLaunchIntentForPackage(packageName);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        context.sendBroadcast(shortcut);
    }

    /**
     * 打开第三方应用
     *
     * @param activity
     * @param sPackage
     * @param uiClass
     */
    // 另：几个常用的Package命令：
    // 新浪微博（编辑界面）：com.sina.weibo //com.sina.weibo.EditActivity
    // 腾讯微博（编辑界面）：com.tencent.WBlog// com.tencent.WBlog.activity.MicroblogInput
    // 微信： com.tencent.mm //com.tencent.mm.ui.LauncherUI
    // QQ: com.tencent.mobileqq// com.tencent.mobileqq.activity.HomeActivity
    public static void openLauncherPlatfrom(Activity activity, String sPackage, String uiClass) {
        Intent intent = new Intent();
        ComponentName cmp = new ComponentName(sPackage, uiClass);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setComponent(cmp);
        activity.startActivityForResult(intent, 0);
    }

    /**
     * 下载新版本apk并更新，文件名默认为应用程序名称ApplicationName
     *
     * @param context
     * @param apkUrl
     */
    public static void updateApk(final Context context, String apkUrl) {
        updateApk(context, apkUrl, getApplicationName(context));
    }

    /**
     * 下载新版本apk并更新，文件名自定义
     *
     * @param context
     * @param apkUrl
     * @param apkName
     */
    public static void updateApk(final Context context, String apkUrl, String apkName) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("下载");
        progressDialog.setMessage("正在下载,请稍后...");
        progressDialog.show();
        OkHttpUtils.get(apkUrl).tag(context).execute(new FileCallback(new StringBuffer(apkName).append(".apk").toString()) {

            @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                super.onError(isFromCache, call, response, e);
                ToastUtils.shortShow("下载失败");
                progressDialog.dismiss();
            }

            @Override
            public void onResponse(boolean isFromCache, File file, Request request, @Nullable Response response) {
                progressDialog.dismiss();
                install(context, file);
            }

            @Override
            public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                double currentSizes = FileUtil.FormetFileSize(currentSize, FileUtil.SIZETYPE_KB);
                double totalSizes = FileUtil.FormetFileSize(totalSize, FileUtil.SIZETYPE_KB);
                progressDialog.setMax((int) totalSizes);
                progressDialog.setProgressNumberFormat("%1d KB/%2d KB");
                progressDialog.setProgress((int) currentSizes);
            }
        });
    }
}
