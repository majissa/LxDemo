package com.lx.lxdemo.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * 创建人：LX
 * 创建日期：2016/7/28
 * 描述：viewpager弹窗的建造者
 */
public class ViewPagerBuilder implements BaseBuilder {

    private Context context;
    public View view;
    private TextView cancel;
    private TextView positive;
    private Dialog dialog;

    public ViewPagerBuilder(Context context,Dialog dialog) {
        this.context = context;
        this.dialog = dialog;
    }


    @Override
    public View setLayoutRes(@LayoutRes int layoutRes) {
        view = LayoutInflater.from(context).inflate(layoutRes, null);
        dialog.setContentView(view);
        return view;
    }


    @Override
    public void setCancel(@IdRes int cancelId, @StringRes int strId) {
        if (view != null) {
            cancel = (TextView) view.findViewById(cancelId);
            cancel.setText(context.getString(strId));
        }
    }

    @Override
    public void setCancel(@IdRes int cancelId) {
        if (view != null) {
            cancel = (TextView) view.findViewById(cancelId);

        }
    }

    @Override
    public void setPositive(@IdRes int positiveId, @StringRes int strId) {
        if (view != null) {
            positive = (TextView) view.findViewById(positiveId);
            positive.setText(context.getString(strId));
        }

    }

    @Override
    public void setPositive(@IdRes int positiveId) {
        if (view != null) {
            positive = (TextView) view.findViewById(positiveId);
        }
    }

    @Override
    public void show() {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }

    }

    @Override
    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

}
