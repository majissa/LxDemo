package com.lx.lxdemo.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.lx.lxdemo.R;

/**
 * 创建人：LX
 * 创建日期：2016/7/28
 * 描述：采用的设计的模式为：建造设计模式，creater是指导者，ViewPagerBuilder为建造者，ViewPagerDialog为产品
 */
public class ViewPagerDialog extends Dialog {

    public ViewPagerDialog(Context context) {
        super(context);
    }

    protected ViewPagerDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);

    }

    public ViewPagerDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Creater {
        private Context context;
        private ViewPagerBuilder builder;

        public Creater(Context context) {
            this.context = context;
            ViewPagerDialog dialog = new ViewPagerDialog(context, R.style.AlertDialogStyle);
            builder = new ViewPagerBuilder(context,dialog);
        }

        public Creater(Context context, @LayoutRes int layoutRes) {
            this.context = context;
            ViewPagerDialog dialog = new ViewPagerDialog(context, R.style.AlertDialogStyle);
            builder = new ViewPagerBuilder(context,dialog);
            builder.view = builder.setLayoutRes(layoutRes);
        }

        public Creater setLayoutRes(@LayoutRes int layoutRes) {
            builder.view = builder.setLayoutRes(layoutRes);
            return this;
        }


        public Creater setPostive(@IdRes int postiveId, @StringRes @NonNull int StrId) {
            builder.setPositive(postiveId, StrId);
            return this;
        }

        public Creater setCancle(@IdRes int cancleId, @StringRes @NonNull int StrId) {
            builder.setCancel(cancleId, StrId);
            return this;
        }


        public void show() {
            builder.show();
        }


    }


}
