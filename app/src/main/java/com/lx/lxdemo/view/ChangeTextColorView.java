package com.lx.lxdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 创建人：LX
 * 创建日期：2016/7/13
 * 描述：
 */
public class ChangeTextColorView extends View {

    private final static String TAG = "ChangeTextColorView";
    private final static boolean isDbug = Log.isLoggable(TAG, Log.DEBUG);
    private Paint mPaint;  //画笔
    private String str = "我是中国人";
    private int width;   //整张图片的宽度
    private int height;  //整张图片的高度
    private int getWidth;
    private int getHeight;

    public ChangeTextColorView(Context context) {
        super(context);
        init();
    }

    public ChangeTextColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ChangeTextColorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mPaint = new Paint();
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(150);  //大小先写死
    }

    /**
     * 1.The base class implementation of measure defaults to the background size, unless a larger size is allowed by the MeasureSpec
     * 如果没有重写这个方法，无论是wrap_content还是是match_parent都是充满match_parent的效果，除非设置固定的大小
     * 2.getMeasuredHeight()和getMeasuredWidth()方法是在onMeasure()中测量出来
     * 3.getHeight()和getWidth()方法在onLayout中测量，在onMeasure()中测量出来的值为0
     * 4.重写onMeasure()设置setMeasuredDimension(width, heitht)width,height固定大小后，无论在布局文件设置多大或多小，控件的大小还是显示设置的大小
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);  //得到的是屏幕的宽度1080px
        height = width;
        setMeasuredDimension(300, 300);
        int measureHeight = getMeasuredHeight();
        int measureWidth = getMeasuredWidth();
        getWidth = getWidth();
        getHeight = getHeight();

        if (isDbug) {
            Log.d(TAG, "----------onMeasure--------------");

            Log.d(TAG, "----------measureHeight=" + measureHeight);
            Log.d(TAG, "----------measureWidth=" + measureWidth);


            Log.d(TAG, "----------getWidth=" + getWidth);
            Log.d(TAG, "----------getHeight=" + getHeight);
        }

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        getWidth = getWidth();
        getHeight = getHeight();

        if (isDbug) {
            Log.d(TAG, "----------onLayout--------------");
            Log.d(TAG, "----------getWidth=" + getWidth);
            Log.d(TAG, "----------getHeight=" + getHeight);
        }
    }


    /* @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getWidth();
        // int mode = MeasureSpec.getMode(MeasureSpec.EXACTLY);
        height = getHeight();
        // getWidth();
        Logger.dLi("width=" + width);
        Logger.dLi("height=" + height);
        setMeasuredDimension(, );

    }*/


    /**
     * 1.xml布局为wrap_content时：width=measureWidth=1080 (pixel)
     * 2.
     *
     * @param canvas
     */


    @Override
    protected void onDraw(Canvas canvas) {
        // super.onDraw(canvas);
        width = getWidth();
        // int mode = MeasureSpec.getMode(MeasureSpec.EXACTLY);
        height = getHeight();


        int measureHeight = getMeasuredHeight();
        int measureWidth = getMeasuredWidth();

        // getWidth();

        if (isDbug) {
            Log.d(TAG, "----------ondraw-------");

           /* Log.d(TAG, "----------width=" + width);
            Log.d(TAG, "----------width=" + width);
            Log.d(TAG, "----------measureHeight=" + measureHeight);
            Log.d(TAG, "----------measureWidth=" + measureWidth);*/
        }

        int mWidth = width / 2;
        int mHeigh = height / 2;
        int mPos = mWidth - (int) mPaint.measureText(str) / 2;
        canvas.drawText(str, mPos, mHeigh, mPaint);
        setBackgroundColor(Color.WHITE);
    }


}
