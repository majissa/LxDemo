package com.lx.lxdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.lx.lxdemo.common.Logger;

/**
 * 创建人：LX
 * 创建日期：2016/7/19
 * 描述：用于测试事件丰富机制
 */
public class OutLinearLayout extends LinearLayout {

    public OutLinearLayout(Context context) {
        super(context);
    }

    public OutLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OutLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Logger.dLi("+++++ OutLinearLayout++++++dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Logger.dLi("+++++ OutLinearLayout+++++onTouchEvent");
        return super.onTouchEvent(event);
    }
}
