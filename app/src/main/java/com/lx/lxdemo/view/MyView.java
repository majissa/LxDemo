package com.lx.lxdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lx.lxlibrary.log.Logger;

/**
 * 创建人：李响
 * 创建日期：2016/7/22
 * 描述：
 */
public class MyView extends View {

    String TAG = "touch";

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
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Logger.d(TAG, "view dispatchTouchEvent");
                break;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Logger.d(TAG, "view onTouchEvent");
                break;
        }
        return super.onTouchEvent(event);
    }

    //    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
////        getParent().requestDisallowInterceptTouchEvent(true);
//        Log.d(TAG, "MyView dispatchTouchEvent: ");
//        return false;
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            return true;
//        }
//        Log.d(TAG, "MyView onTouchEvent: ");
//        return super.onTouchEvent(event);
//    }
}
