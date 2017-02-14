package com.lx.lxdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * 创建人：李响
 * 创建日期：2016/7/22
 * 描述：
 */
public class MyView extends TextView {

    String TAG = "LIXIANG";

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        Log.d(TAG, "MyView dispatchTouchEvent: ");
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            return true;
        }
        Log.d(TAG, "MyView onTouchEvent: ");
        return super.onTouchEvent(event);
    }
}
