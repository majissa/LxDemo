package com.lx.lxdemo.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lx.lxlibrary.activity.BaseActivity;

/**
 * 创建人：LX
 * 创建日期：2016/7/4
 * 描述：
 */
public abstract class BaseFragment extends Fragment {

    protected BaseActivity baseActivtiy;
    protected Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        baseActivtiy = (BaseActivity) getActivity();
        context = getActivity();
        View view = inflater.from(getActivity()).inflate(inflatebaseLayout(), container, false);
        initView(view);
        setListener();
        initDatas();
        return view;
    }

    protected abstract void initView(View view);

    protected abstract int inflatebaseLayout();

    protected abstract void setListener();

    protected abstract void initDatas();
}
