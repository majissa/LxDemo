package com.lx.lxdemo.fragment;

import android.view.View;

import com.lx.lxdemo.R;
import com.lx.lxdemo.base.BaseFragment;

/**
 * 创建人：李响
 * 创建日期：2016/8/14
 * 描述：
 *     Scale in X and Y independently,
 *     so that src matches dst exactly. This may change the aspect ratio of the src.
 *
 *      规模在X和Y的独立，使SRC DST完全匹配。这可能会改变src的纵横比
 */
public class FixXYFragment extends BaseFragment {
    @Override
    protected void initView(View view) {

    }

    @Override
    protected int inflatebaseLayout() {
        return R.layout.fragment_fit_xy;
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initDatas() {

    }
}
