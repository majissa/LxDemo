package com.lx.lxdemo.utils;

import android.support.annotation.IdRes;
import android.text.Editable;
import android.text.TextWatcher;

/**
 * 创建人：LX
 * 创建日期：2016/7/21
 * 描述：
 */
public class LTextWatcher implements TextWatcher {
    @IdRes
    int resId;

    LTextWatcher( int resId) {
        this.resId = resId;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
