package com.lx.lxdemo.activity;

import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lx.lxdemo.R;
import com.lx.lxlibrary.activity.BaseActivity;
import com.lx.lxlibrary.interfaces.OnContactObtainListener;
import com.lx.lxlibrary.log.Logger;
import com.lx.lxlibrary.utlis.PhoneUtil;
import com.lx.lxlibrary.utlis.ToastUtils;

/**
 * 创建人：LX
 * 创建日期：2016/9/19
 * 描述：
 */
public class SystemActivity extends BaseActivity {

    private EditText et_contactPhone;
    private Button btn_access_to_contact;
    private String username;
    private String usernumber;
    private Button btn_go_to_setting;
    private TextView tv_notification_state;
    private boolean b;

    @Override
    protected void initView(View view) {
        et_contactPhone = (EditText) findViewById(R.id.et_contactPhone);
        btn_access_to_contact = (Button) findViewById(R.id.btn_access_to_contact);
        btn_go_to_setting = (Button) view.findViewById(R.id.btn_go_to_setting);
        tv_notification_state = (TextView) view.findViewById(R.id.tv_notification_state);
    }

    @Override
    protected void initEvent() {
        btn_access_to_contact.setOnClickListener(this);
        btn_go_to_setting.setOnClickListener(this);
    }

    @Override
    protected void initValue() {
        setTitle(R.string.call_system_page);
        NotificationManagerCompat compat = NotificationManagerCompat.from(context);
        b = compat.areNotificationsEnabled();
        if (b) {//打开
            Logger.dLi("NotificationManagerCompat true");
            tv_notification_state.setText("通知打开");
        } else {//关闭
            Logger.dLi("NotificationManagerCompat false");
            tv_notification_state.setText("通知关闭");
        }
    }

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_system_page;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_access_to_contact:
                PhoneUtil.openContact(this);
                break;
            case R.id.btn_go_to_setting:
                PhoneUtil.openApplicationSetting(context, getPackageName());
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PhoneUtil.contactOnActivityResult(requestCode, resultCode, data, this, new OnContactObtainListener() {
            @Override
            public void onFailure() {
                ToastUtils.shortShow("获取失败！");
            }

            @Override
            public void onSuccess(String var1, String var2) {
                et_contactPhone.setText(var2 + "(" + var1 + ")");
            }
        });
    }
}
