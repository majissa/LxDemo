package com.lx.lxdemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.lx.lxlibrary.log.Logger;

/**
 * Created by 李响
 * 创建日期 2017/1/9
 * 描述：
 */
public class TestService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.dLi("TestService");
    }
}
