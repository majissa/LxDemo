package com.lx.lxlibrary.interfaces;

/**
 * Created by 李响
 * 创建日期 2016/10/28
 * 描述：
 */

public abstract class OnContactObtainListener extends absListener {

    @Override
    public void onFailure() {
        super.onFailure();
    }


    public abstract void onSuccess(String var1, String var2);
}
