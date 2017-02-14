package com.lx.lxdemo.activity;

import android.view.View;

import com.lx.lxdemo.R;
import com.lx.lxlibrary.activity.BaseActivity;

import uk.co.senab.photoview.PhotoView;

/**
 * 创建人：LX
 * 创建日期：2016/8/25
 * 描述：
 */
public class SinglePhotoViewActivity extends BaseActivity {

    private PhotoView photoView;

    @Override
    protected void initView(View view) {
        setTitle(R.string.photoView_title);
        photoView = (PhotoView) findViewById(R.id.photoView);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initValue() {

    }

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_singlephotoview;
    }

    @Override
    public void onClick(View v) {

    }
}
