package com.lx.lxdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.lx.lxdemo.common.Logger;

/**
 * 创建人：LX
 * 创建日期：2016/7/19
 * 描述：用于测试事件分发机制
 */
public class InLinearLayout extends LinearLayout {

    public InLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public InLinearLayout(Context context) {
        super(context);
    }

    public InLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Logger.dLi("+++++ InLinearLayout++++++dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Logger.dLi("+++++ InLinearLayout+++++onTouchEvent");
        return super.onTouchEvent(event);
    }


}
