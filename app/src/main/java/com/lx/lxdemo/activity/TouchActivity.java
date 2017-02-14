package com.lx.lxdemo.activity;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.lx.lxdemo.R;
import com.lx.lxlibrary.activity.BaseActivity;

/**
 * 创建人：李响
 * 创建日期：2016/7/22
 * 描述：事件分发机制详解
 */


/*
网址：http://mp.weixin.qq.com/s?__biz=MjM5NDAxNTY4MA==&mid=2649396652&idx=1&sn=74f333f821f988c9b5270da326f87c03&scene=0#wechat_redirect


        1、在哪个View的onTouchEvent 返回true，那么ACTION_MOVE和ACTION_UP的事件从上往下传到这个View后就不再往下传递了，
        而直接传给自己的onTouchEvent 并结束本次事件传递过程。



        1.当ViewGroup的dispatchTouchEvent(Motion event)放回true，
        而onInterceptTouchEvent(MotionEvent ev)不重写，
        是不会走到ViewGroup的onTouchEvent(Motion event)方法


        3.当ACTION_DOWN,ViewGroup1的dispatchTouchEvent(Motion event)返回为super时,
        onTouchEvent(Motion event)为true的时候，是会传递到内部ViewGroup2的dispatchTouchEvent(Motion event)，
        和当前onTouchEvent(Motion event),ACTION_UP和ACTION_MOVE会在onTouchEvent(Motion event)。

        4.ACTION_DOWN事件在哪个控件消费了（return true），
        那么ACTION_MOVE和ACTION_UP就会从上往下（通过dispatchTouchEvent）做事件分发往下传，
        就只会传到这个控件，不会继续往下传，如果ACTION_DOWN事件是在dispatchTouchEvent消费，
        那么事件到此为止停止传递，如果ACTION_DOWN事件是在onTouchEvent消费的，
        那么会把ACTION_MOVE或ACTION_UP事件传给该控件的onTouchEvent处理并结束传递。



       5 但必须要说明的是，这里的Touch事件，只限于Acition_Down事件，即触摸按下事件,而Aciton_UP和Action_MOVE却不会执行。
       事实上，一次完整的Touch事件，应该是由一个Down、一个Up和若干个Move组成的。Down方式通过dispatchTouchEvent分发，分发的目的是为了找到真正需要处理完整Touch请求的View。
       当某个View或者ViewGroup的onTouchEvent事件返回true时，便表示它是真正要处理这次请求的View，之后的Aciton_UP和Action_MOVE将由它处理。当所有子View的onTouchEvent都返回false时，
       这次的Touch请求就由根ViewGroup，即Activity自己处理了。




*/




public class TouchActivity extends BaseActivity {

    String TAG = "LIXIANG";

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initValue() {

    }

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_touch;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "TouchActivity dispatchTouchEvent: ");
        return super.dispatchTouchEvent(ev);
    }
}
