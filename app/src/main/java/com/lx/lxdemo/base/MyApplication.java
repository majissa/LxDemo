package com.lx.lxdemo.base;

import android.content.Intent;
import android.content.IntentFilter;

import com.lx.jpush.JPushConfig;
import com.lx.lxdemo.receiver.TickBroaderReceiver;
import com.lx.lxlibrary.common.AbsAppaction;

/**
 * 创建人：LX
 * 创建日期：2016/6/27
 * 描述：
 */
public class MyApplication extends AbsAppaction {

    public static boolean debugMode = true;

    @Override
    public void onCreate() {
        super.onCreate();
        JPushConfig.init(getApplicationContext(), true);
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_TIME_TICK);
        TickBroaderReceiver tickBroaderReceiver = new TickBroaderReceiver();
        registerReceiver(tickBroaderReceiver, intentFilter);
    }

    @Override
    public AbsAppaction getInstance() {
        return this;
    }

}
