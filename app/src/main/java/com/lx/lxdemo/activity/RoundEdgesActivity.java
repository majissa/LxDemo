package com.lx.lxdemo.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.lx.lxdemo.R;
import com.lx.lxlibrary.activity.BaseActivity;
import com.lx.lxdemo.service.TestService;
import com.lx.lxdemo.view.ViewPagerDialog;
import com.lx.lxlibrary.view.ProcessImageView;

/**
 * 创建人：LX
 * 创建日期：2016/7/4
 * 描述：中间是矩形，两边是半圆的效果
 */
public class RoundEdgesActivity extends BaseActivity {


    private View btn_dialog;
    private View btn_viewpager_dailog;
    private Button btnLoad;
    private ProcessImageView processImageView;
    private int i;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    i = i + 10;
                    processImageView.setProgress(i);
                    if (i > 100) {
                        break;
                    }
                    handler.sendEmptyMessageDelayed(0, 1000);
                    break;
            }
            super.handleMessage(msg);
        }
    };
    private Button btn_start_btn;

    @Override
    protected void initView(View view) {
        btn_dialog = view.findViewById(R.id.btn_dialog);
        btn_viewpager_dailog = view.findViewById(R.id.btn_viewpager_dailog);
        btnLoad = (Button) view.findViewById(R.id.btn_load);
        btn_start_btn = (Button)view.findViewById(R.id.btn_start_btn);
        processImageView = (ProcessImageView) view.findViewById(R.id.processImageView);
    }
    
    @Override
    protected void initEvent() {
        btn_dialog.setOnClickListener(this);
        btn_viewpager_dailog.setOnClickListener(this);
        btnLoad.setOnClickListener(this);
        btn_start_btn.setOnClickListener(this);
    }
    
    @Override
    protected void initValue() {

    }

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_round_edges;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog:
                showDialog();
                break;
            case R.id.btn_viewpager_dailog:
                showviewPagerDialog();
                break;
            case R.id.btn_load:
                showprocess();
                break;
            case R.id.btn_start_btn:
                Intent intent1 = new Intent(context, TestService.class);
                context.startService(intent1);
                break;
        }
    }

    /**
     * 展示process
     */
    private void showprocess() {
        handler.sendEmptyMessageDelayed(0, 1000);
    }


    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("这是一个测试");
        builder.setMessage("信息");
        builder.show();

    }

    private void showviewPagerDialog() {
        ViewPagerDialog.Creater creater = new ViewPagerDialog.Creater(this, R.layout.dialog_viewpager);
        creater.setCancle(R.id.tv_cancle, R.string.cancel);
        creater.setPostive(R.id.tv_positive, R.string.postive);
        creater.show();
    }


}
