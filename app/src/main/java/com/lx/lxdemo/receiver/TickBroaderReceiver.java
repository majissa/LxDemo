package com.lx.lxdemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.lx.lxdemo.service.TestService;
import com.lx.lxlibrary.log.Logger;
import com.lx.lxlibrary.utlis.AppUtil;

/**
 * Created by 李响
 * 创建日期 2017/1/9
 * 描述：ACTION_TIME_TICK 广播只能在代码中注册，在mainfesttival中注册是没有用的
 */
public class TickBroaderReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_TIME_TICK)) {
            Logger.dLi("ACTION_TIME_TICK");
            if (!AppUtil.isServiceRuning(context, TestService.class)) {
                Intent intent1 = new Intent(context, TestService.class);
                context.startService(intent1);
            }
        }
    }
}
