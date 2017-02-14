package com.lx.lxdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lx.lxdemo.R;

/**
 * 创建人：李响
 * 创建日期：2016/7/22
 * 描述：
 */
public class IntLayout extends LinearLayout {
    String TAG = "LIXIANG";

    public IntLayout(Context context) {
        super(context);
        init(context);
    }

    public IntLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public IntLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }


    private void init(Context context) {
        setGravity(Gravity.LEFT);
        TextView textView = new TextView(context);
        textView.setTextSize(30);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setGravity(Gravity.RIGHT | Gravity.CENTER_HORIZONTAL);
       // textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setText("是真的吗");
        ImageView imageVIEW = new ImageView(context);
        imageVIEW.setImageResource(R.mipmap.ic_launcher);
        addView(textView);
        addView(imageVIEW);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "IntLayout  dispatchTouchEvent");
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "IntLayout dispatchTouchEvent---> ACTION_DOWN  ");
                break;

            case MotionEvent.ACTION_UP:
                Log.d(TAG, "IntLayout dispatchTouchEvent---> ACTION_UP  ");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "IntLayout dispatchTouchEvent---> ACTION_MOVE  ");
                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "IntLayout onTouchEvent");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "IntLayout onTouchEvent---> ACTION_DOWN  ");
                break;

            case MotionEvent.ACTION_UP:
                Log.d(TAG, "IntLayout onTouchEvent---> ACTION_UP  ");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "IntLayout onTouchEvent---> ACTION_MOVE  ");
                break;
        }

        return super.onTouchEvent(event);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }
}
