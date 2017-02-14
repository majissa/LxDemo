package com.lx.lxdemo.activity;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.View;
import android.widget.Button;

import com.lx.lxdemo.R;
import com.lx.lxdemo.my.MainActivity;
import com.lx.lxlibrary.activity.BaseActivity;
import com.lx.lxlibrary.log.Logger;

import java.util.List;

/**
 * Created by 李响
 * 创建日期 2016/12/22
 * 描述：多种图标的Activity，注意，动态的更换图标的disenableComponent()中的activity必须是主Activity才行，不能用getComponentName();因为当前的ComponentName并不是主activity，
 * 所以会出现两个app的情况。
 */
public class VarietyIconActivity extends BaseActivity {

    private Button btn_default;
    private Button btn_test1;
    private Button btn_test2;
    private PackageManager packageManager;
    private ComponentName mdefault;
    private ComponentName componentName1;
    private ComponentName componentName2;
    private ActivityManager activityManager;
    private String className;


    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_variety;
    }

    @Override
    protected void initView(View view) {
        btn_default = (Button) view.findViewById(R.id.btn_default);
        btn_test1 = (Button) view.findViewById(R.id.btn_test1);
        btn_test2 = (Button) view.findViewById(R.id.btn_test2);
    }

    @Override
    protected void initEvent() {
        btn_default.setOnClickListener(this);
        btn_test1.setOnClickListener(this);
        btn_test2.setOnClickListener(this);
    }

    @Override
    protected void initValue() {
        packageManager = getPackageManager();
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.setPackage(getPackageName());
//        intent.addCategory(Intent.CATEGORY_LAUNCHER);
//        ComponentName componentName = intent.resolveActivity(packageManager);

        className = MainActivity.class.getName();
        Logger.dLi("className:" + className);
//        killbackgroundProcess();
        mdefault = new ComponentName(getBaseContext(), className);
        componentName1 = new ComponentName(getBaseContext(), "com.lx.lxdemo.test_one_MainActivity");
        componentName2 = new ComponentName(getBaseContext(), "com.lx.lxdemo.test_two_MainActivity");

        activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_default:
                enableComponent(mdefault);
                disenableComponent(componentName1);
                disenableComponent(componentName2);
                break;
            case R.id.btn_test1:
                disenableComponent(mdefault);
                enableComponent(componentName1);
                disenableComponent(componentName2);
                break;
            case R.id.btn_test2:
                disenableComponent(mdefault);
                disenableComponent(componentName1);
                enableComponent(componentName2);
                break;
        }
    }

    //去除就图标，不去除的话会出现2个APP图标
    private void enableComponent(ComponentName componentName) {
        packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }

    //显示新图标
    private void disenableComponent(ComponentName componentName) {
        packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }

    private void killbackgroundProcess() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resolveInfos) {
           /* if (resolveInfo.activityInfo != null) {
                activityManager.killBackgroundProcesses(resolveInfo.activityInfo.packageName);

            }*/
            Logger.dLi("launcheractivity" + resolveInfo.activityInfo.name);
        }

    }
}
