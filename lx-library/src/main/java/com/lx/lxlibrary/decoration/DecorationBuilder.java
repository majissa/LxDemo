package com.lx.lxlibrary.decoration;

import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;

/**
 * Created by 李响
 * 创建日期 2017/2/28
 * 描述：
 */
public interface DecorationBuilder<T extends DecorationBuilder> {

    /**
     * 设置分隔线的资源文件
     */
    public T drawable(@DrawableRes int drawbleRes);

    /**
     * 设置分隔线的颜色
     */
    public T color(@ColorRes int ColorRes);

    /**
     * 设置左边边距
     */
    public T leftPadding(@DimenRes int dimenRes);

    /**
     * 设置右边边距
     */
    public T RightPadding(@DimenRes int dimenRes);

    public RecyclerView.ItemDecoration build();
}
