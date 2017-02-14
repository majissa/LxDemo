package com.lx.lxdemo.activity;

import com.lx.lxlibrary.activity.BaseActivity;
import com.lx.lxlibrary.utlis.AppUtil;

/**
 * Created by 李响
 * 创建日期 2017/1/9
 * 描述：主要为了退出推送的页面以后可以打开主页面，打开app,如果不需要的话可以不用继承
 */
public abstract class PushActivity extends BaseActivity {

    @Override
    public void onBackPressed() {
        if (!AppUtil.isAppRunning(context, getPackageName())) {
            AppUtil.getLaunchIntent(context);
        }
        super.onBackPressed();
    }
}
