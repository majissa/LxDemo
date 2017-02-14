package com.lx.lxdemo.fragment;

import android.view.View;

import com.lx.lxdemo.R;
import com.lx.lxdemo.base.BaseFragment;

/**
 * 创建人：李响
 * 创建日期：2016/8/14
 * 描述：    Scale the image uniformly (maintain the image's aspect ratio)
 *          so that both dimensions (width and height) of the image
 *          will be equal to or less than the corresponding dimension of the view (minus padding).
 *
 *
 *
 *     尺度的图像均匀地（保持图像的纵横比），使图像的两个尺寸（宽度和高度）将等于或小于视图（减去填充）的相应尺寸。
 *
 */
public class CenterInsideFragment extends BaseFragment {
    @Override
    protected void initView(View view) {

    }

    @Override
    protected int inflatebaseLayout() {
        return R.layout.fragment_center_inside;
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initDatas() {

    }
}
