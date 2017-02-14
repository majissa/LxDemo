package com.lx.lxdemo.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.PopupWindow;

import com.lx.lxdemo.R;
import com.lx.lxlibrary.activity.BaseActivity;
import com.lx.lxdemo.common.L;
import com.lx.lxdemo.common.Logger;

/**
 * 创建人：LX
 * 创建日期：2016/7/1
 * 描述：
 */
public class PpwActivity extends BaseActivity {
    private Button btn_01;
    private View ppwView;
    private Button btn_02;
    private Button btn_03;
    private Button btn_04;

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_ppw;
    }

    @Override
    protected void initView(View view) {
        btn_01 = (Button) view.findViewById(R.id.btn_01);
        btn_02 = (Button) view.findViewById(R.id.btn_02);
        btn_03 = (Button) view.findViewById(R.id.btn_03);
        btn_04 = (Button) view.findViewById(R.id.btn_04);
        ppwView = LayoutInflater.from(this).inflate(R.layout.ppw, null);
        Logger.d("PpwActivity","TEST_PpwActivity");
    }

    @Override
    protected void initEvent() {
        btn_01.setOnClickListener(this);
        btn_02.setOnClickListener(this);
        btn_03.setOnClickListener(this);
        btn_04.setOnClickListener(this);
    }

    @Override
    protected void initValue() {
        setTitle(R.string.custom_popupwindow);
        int width = btn_01.getWidth();
        int height = btn_01.getHeight();
        L.dLi("btn_01的宽度：" + width);
        L.dLi("btn_01的高度：" + height);
        btn_01.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = btn_01.getWidth();  //得到的结果的单位是像素即pixel todo: 当在xml文件中设置为100dp时，显示是300px,如果是100px,就是显示100px
                int height = btn_01.getHeight();//得到的结果的单位是像素即pixel 300
                //  L.dLi("listener中的width:" + width); //得到的结果的单位是像素即pixel
                // L.dLi("listenr中的height:" + height);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_01:
                testPopWindow(ppwView);
                L.dClick();
                break;
            case R.id.btn_02:
                testPopWindow(ppwView, 30, 40);
                break;
            case R.id.btn_03:
                testPopWindow(30, 40);
                break;
            case R.id.btn_04:

                break;

        }
    }


    private void testPopWindow() {
        PopupWindow popupWindow = new PopupWindow();
        popupWindow.showAsDropDown(btn_01);
    }

    private void testPopWindow(View view) {
        PopupWindow popupWindow = new PopupWindow(view);
        popupWindow.showAsDropDown(btn_01);
    }

    private void testPopWindow(int width, int height) {
        PopupWindow popupWindow = new PopupWindow(width, height);
        popupWindow.showAsDropDown(btn_03);
    }

    private void testPopWindow(View view, int width, int height) {
        PopupWindow popupWindow = new PopupWindow(width, height);
        popupWindow.showAsDropDown(btn_02);
    }
}
