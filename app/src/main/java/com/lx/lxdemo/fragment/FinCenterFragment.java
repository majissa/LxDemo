package com.lx.lxdemo.fragment;

import android.view.View;

import com.lx.lxdemo.R;
import com.lx.lxdemo.base.BaseFragment;

/**
 * 创建人：李响
 * 创建日期：2016/8/14
 * 描述：Compute a scale that will maintain the original src aspect ratio,
 *       but will also ensure that src fits entirely inside dst. At least one axis (X or Y) will fit exactly.
 *       The result is centered inside dst.
 *
 *    计算规模将保持原有的SRC的纵横比，但也将确保完全符合DST SRC里面。至少一个轴（X或Y）将完全适合。结果DST里面的中心。
 *
 *
 *
 */
public class FinCenterFragment extends BaseFragment {
    @Override
    protected void initView(View view) {

    }

    @Override
    protected int inflatebaseLayout() {
        return R.layout.fragment_fit_center;
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initDatas() {

    }
}
