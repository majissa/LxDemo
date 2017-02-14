package com.lx.lxdemo.receiver;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;
import com.lx.jpush.MyReceiver;
import com.lx.jpush.PushBean;
import com.lx.lxdemo.activity.PushTest02Activity;
import com.lx.lxdemo.activity.PushTestActivity;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by 李响
 * 创建日期 2017/1/9
 * 描述：主要是应用被用户手动清除以后，收到通知以后可以用来跳转Activity广播，如果不需要的话，就不需要做任何炒作
 */
public class JumpActivityReceiver extends MyReceiver {

    @Override
    protected void startPushActivity(Context context, Bundle bundle) {
        String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
        Gson gson = new Gson();
        PushBean pushBean = gson.fromJson(extra, PushBean.class);
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if (pushBean.getData() != null) {
            switch (pushBean.getData().getType()) {
                case 1:
                    intent.setClass(context, PushTestActivity.class);
                    context.startActivity(intent);
                    break;
                case 2:
                    intent.setClass(context, PushTest02Activity.class);
                    context.startActivity(intent);
                    break;
            }
        }
    }
}
