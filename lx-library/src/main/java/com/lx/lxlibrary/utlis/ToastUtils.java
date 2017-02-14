package com.lx.lxlibrary.utlis;

import android.support.annotation.StringRes;
import android.widget.Toast;

import com.lx.lxlibrary.common.AbsAppaction;

/**
 * Created by LX on 2016/3/8.
 */
public class ToastUtils {

    public static void longShow(String str) {
        Toast.makeText(AbsAppaction.getAppaction(), str, Toast.LENGTH_LONG).show();
    }

    public static void shortShow(String str) {
        Toast.makeText(AbsAppaction.getAppaction().getInstance(), str, Toast.LENGTH_SHORT).show();
    }

    public static void shortShow(@StringRes int resId) {
        Toast.makeText(AbsAppaction.getAppaction().getInstance(), resId, Toast.LENGTH_SHORT).show();
    }

    /**
     *
     */
    public static void toastNetwork() {
        shortShow("请检查网络");
    }

}
