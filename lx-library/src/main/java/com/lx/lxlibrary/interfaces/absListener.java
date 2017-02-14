package com.lx.lxlibrary.interfaces;

/**
 * 创建人：LX
 * 创建日期：2016/9/19
 * 描述：抽象接口，将不需要的接口方法在抽象类中实现
 */
public abstract class absListener implements SuccessListener, FailureListener, StateListener {

    public absListener() {
        super();
    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onFinish() {

    }
}
