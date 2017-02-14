package com.lx.lxdemo.activity;

import android.view.View;
import android.widget.ImageView;

import com.lx.lxdemo.R;
import com.lx.lxlibrary.activity.BaseActivity;

/**
 * Created by 李响
 * 创建日期 2017/1/12
 * 描述：插入可绘制图像
 */
public class InsertDrawableActivity extends BaseActivity {
    private ImageView iv_insert;
    private ImageView iv_bottom_vertical;
    private ImageView iv_left_horziontal;
    private ImageView iv_right_horziontal;
    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_clipdrawable;
    }

    @Override
    protected void initView(View view) {
        iv_insert = (ImageView) view.findViewById(R.id.iv_insert);
        iv_bottom_vertical = (ImageView) view.findViewById(R.id.iv_bottom_vertical);
        iv_left_horziontal = (ImageView) view.findViewById(R.id.iv_left_horziontal);
        iv_right_horziontal = (ImageView) view.findViewById(R.id.iv_right_horziontal);

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initValue() {


    }

    @Override
    public void onClick(View v) {

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
