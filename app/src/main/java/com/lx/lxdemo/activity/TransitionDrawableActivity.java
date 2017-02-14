package com.lx.lxdemo.activity;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.view.View;
import android.widget.ImageView;

import com.lx.lxdemo.R;
import com.lx.lxlibrary.activity.BaseActivity;

/**
 * Created by 李响
 * 创建日期 2017/1/12
 * 描述：
 */
public class TransitionDrawableActivity extends BaseActivity {
    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_insertdrawable;
    }

    @Override
    protected void initView(View view) {
        ImageView iv_trainsition = (ImageView) view.findViewById(R.id.iv_trainsition);
        ImageView iv_layer = (ImageView) view.findViewById(R.id.iv_layer);
        LayerDrawable drawable1 = (LayerDrawable) iv_layer.getDrawable();
        Drawable d1 = drawable1.findDrawableByLayerId(R.id.item1);
        Drawable d2 = drawable1.findDrawableByLayerId(R.id.item2);
        d1.setAlpha(100);
        d2.setAlpha(255);
        TransitionDrawable drawable = (TransitionDrawable) iv_trainsition.getDrawable();
//        drawable.startTransition(5000);
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
}
