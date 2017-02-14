package com.lx.lxdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * 创建人：李响
 * 创建日期：2016/7/22
 * 描述：
 */
public class OutLayout extends LinearLayout {
    String TAG = "LIXIANG";

    public OutLayout(Context context) {
        super(context);
    }

    public OutLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OutLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "OutLayout dispatchTouchEvent");
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "OutLayout dispatchTouchEvent---> ACTION_DOWN  ");
                break;

            case MotionEvent.ACTION_UP:
                Log.d(TAG, "OutLayout dispatchTouchEvent---> ACTION_UP  ");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "OutLayout dispatchTouchEvent---> ACTION_MOVE  ");
                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "OutLayout onTouchEvent");

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "OutLayout onTouchEvent---> ACTION_DOWN  ");
                break;

            case MotionEvent.ACTION_UP:
                Log.d(TAG, "OutLayout onTouchEvent---> ACTION_UP  ");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "OutLayout onTouchEvent---> ACTION_MOVE  ");
                break;
        }

        return true;
    }
  /* 1. You will receive the down event here.
   2. The down event will be handled either by a child of this view group, or given to your own onTouchEvent() method to handle; this means you should implement onTouchEvent() to return true, so you will continue to see the rest of the gesture (instead of looking for a parent view to handle it). Also, by returning true from onTouchEvent(), you will not receive any following events in onInterceptTouchEvent() and all touch processing must happen in onTouchEvent() like normal.
   3. For as long as you return false from this function, each following event (up to and including the final up) will be delivered first here and then to the target's onTouchEvent().
   4. If you return true from here, you will not receive any following events: the target view will receive the same event but with the action MotionEvent.ACTION_CANCEL, and all further events will be delivered to your onTouchEvent() method and no longer appear here.

   3.只要放回false，接下来的每个触摸事件（抬起包括最后一次抬起）第一次将会送达到这个函数，然后传递到目标的onTouchEvent()

   4.如果这里放回正确，你将不会接受到任何接下的事件如ACTION_MOVE等，将会发生在onTouchEvent()


   */

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "OutLayout onInterceptTouchEvent");
        return false;

    }
}
