package com.lx.lxdemo.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.lx.lxdemo.R;
import com.lx.lxlibrary.activity.BaseActivity;
import com.lx.lxlibrary.log.Logger;
import com.lx.lxlibrary.utlis.PhoneUtil;
import com.lx.lxlibrary.utlis.ToastUtils;

/**
 * Created by 李响
 * 创建日期 2017/1/13
 * 描述：动态权限测试，在设备android6.0(API23)以上的，或者targetSdkVersion为23以上的，如果需要用到危险的权限，（权限分为动态权限（危险权限）和正常权限（不侵害用户信息的权限））
 * 都是需要用户分别取同意该权限，危险权限分是按组分类，只要用户同意组中其中一种的权限的话，该组中的其他权限也就不需要弹出窗口让用户同意该权限
 * 可通过命令查看危险权限的 adb shell pm list permissions -d -g 进行查看
 */
public class PermissionActivity extends BaseActivity {
    private String TAG = this.getClass().getSimpleName();
    private Button btn_call_permission;
    private static final int CALL_PERMISSION_REQUEST_CODE = 0x02;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 0x03;
    private Button btn_location;
    private Boolean granted;

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_permission;
    }

    @Override
    protected void initView(View view) {
        btn_call_permission = (Button) view.findViewById(R.id.btn_call_permission);
        btn_location = (Button) view.findViewById(R.id.btn_location);
    }

    @Override
    protected void initEvent() {
        btn_call_permission.setOnClickListener(this);
        btn_location.setOnClickListener(this);
    }

    @Override
    protected void initValue() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_call_permission:
                callPermission();
                break;
            case R.id.btn_location:
                callLocation();
                break;
        }
    }

    private void callPermission() {
        //当返回DENIED ,需要申请授,没有申请权限而且打电话会闪退
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.CALL_PHONE)) {
                ToastUtils.shortShow("should show request permissionrationable true");
            } else {
                ToastUtils.shortShow("should show request permissionrationable false");
            }
            Logger.d(TAG, "denied");
            ToastUtils.shortShow("需要打电话的功能");
//            PhoneUtil.callPhone(context,"13655003033");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, CALL_PERMISSION_REQUEST_CODE);
        } else if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Logger.d(TAG, "granted");
            ToastUtils.shortShow("运行打电话！");
            PhoneUtil.callPhone(context, "13655003033");
        }
    }

    /**
     * 呼叫定位
     */
    private void callLocation() {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED || ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {
                ToastUtils.shortShow("需要定位功能才能完成！请开启定位功能");
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CALL_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {//with the user is interrupted. In this case you will receive empty permissions
                    if (permissions[0].equals(Manifest.permission.CALL_PHONE)) {
                        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                            Logger.d(TAG, "granted");
                            ToastUtils.shortShow("运行打电话！");
//                            PhoneUtil.callPhone(context,"13655003033");
                        } else {
                            Logger.d(TAG, "denied");
                            ToastUtils.shortShow("需要打电话的功能");
                        }
                    }
                } else {
                    Logger.d(TAG, "interrupted");
                }
                break;

            case LOCATION_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {//with the user is interrupted. In this case you will receive empty permissions
                    if (permissions[0].equals(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                            ToastUtils.shortShow("允许粗略定位！");
//                            PhoneUtil.callPhone(context,"13655003033");
                        } else {
                            Logger.d(TAG, "denied");
                            ToastUtils.shortShow("禁止粗略定位");
                        }
                    }
                    if (permissions[1].equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
                        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                            ToastUtils.shortShow("允许精确定位！");
//                            PhoneUtil.callPhone(context,"13655003033");
                        } else {
                            Logger.d(TAG, "denied");
                            ToastUtils.shortShow("禁止精确定位！");
                        }
                    }
                } else {
                    Logger.d(TAG, "interrupted");
                }
                break;
        }
    }
}
