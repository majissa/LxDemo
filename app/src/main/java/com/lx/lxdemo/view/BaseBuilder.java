package com.lx.lxdemo.view;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.view.View;

/**
 * 创建人：LX
 * 创建日期：2016/7/28
 * 描述：dialog弹窗的建造者接口
 */
public interface BaseBuilder {

    /**
     * 设置布局
     *
     * @param layoutRes
     * @return
     */
    public View setLayoutRes(@LayoutRes int layoutRes);

    /**
     * 设置取消按钮id与文本
     *
     * @param cancelId
     * @param strId
     */
    public void setCancel(@IdRes int cancelId, @StringRes int strId);


    public void setCancel(@IdRes int cancelId);

    /**
     * 设置确认键的id与文本
     *
     * @param positiveId
     * @param strId
     */

    public void setPositive(@IdRes int positiveId, @StringRes int strId);


    public void setPositive(@IdRes int positiveId);


    /**
     * 展示弹窗
     */

    public void show();


    /**
     * 弹窗消失
     */
    public void dismiss();


}
