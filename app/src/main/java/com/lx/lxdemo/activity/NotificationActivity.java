package com.lx.lxdemo.activity;

import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lx.lxdemo.R;
import com.lx.lxlibrary.activity.BaseActivity;
import com.lx.lxlibrary.log.Logger;
import com.lx.lxlibrary.utlis.PhoneUtil;

/**
 * Created by 李响
 * 创建日期 2016/11/30
 * 描述：
 */
public class NotificationActivity extends BaseActivity {
    private Button btn_go_to_setting;
    private TextView tv_notification_state;
    private boolean b;


    @Override
    protected void initView(View view) {
        btn_go_to_setting = (Button) view.findViewById(R.id.btn_go_to_setting);
        tv_notification_state = (TextView) view.findViewById(R.id.tv_notification_state);
    }

    @Override
    protected void initEvent() {
        btn_go_to_setting.setOnClickListener(this);
    }

    @Override
    protected void initValue() {
        NotificationManagerCompat compat = NotificationManagerCompat.from(context);
        b = compat.areNotificationsEnabled();
        if (b) {//打开
            Logger.dLi("NotificationManagerCompat true");
            tv_notification_state.setText("NotificationManagerCompat true");
        } else {//关闭
            Logger.dLi("NotificationManagerCompat false");
            tv_notification_state.setText("NotificationManagerCompat false");
        }
    }

    @Override
    protected int inflateLayoutId() {
        return R.layout.activiity_notification;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_go_to_setting:
                PhoneUtil.openApplicationSetting(context, getPackageName());
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        NotificationManagerCompat compat = NotificationManagerCompat.from(context);
        b = compat.areNotificationsEnabled();
        if (b) {//打开
            Logger.dLi("NotificationManagerCompat true");
            tv_notification_state.setText("NotificationManagerCompat true");
        } else {//关闭
            Logger.dLi("NotificationManagerCompat false");
            tv_notification_state.setText("NotificationManagerCompat false");
        }
    }
}
