package com.lx.lxdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 创建人：LX
 * 创建日期：2016/7/14
 * 描述：一个浮起来的按钮
 */
public class RoundConernButton extends TextView {

    private Paint mPaint;
    private int mWidth;
    private int mHeigh;
    private Paint sPaint;
    private Paint textPaint;
    private String text = "免费领红包";

    public RoundConernButton(Context context) {
        super(context);
        init();
    }

    public RoundConernButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundConernButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeigh = MeasureSpec.getSize(heightMeasureSpec);
        // setMeasuredDimension(mWidth, mHeigh);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }


    /*@Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = getWidth();   //990
        mHeigh = getHeight();  //150


    }*/

    private void init() {
        mPaint = new Paint();
        sPaint = new Paint();
        textPaint = new Paint();
        sPaint.setAntiAlias(true);
        mPaint.setAntiAlias(true);
        textPaint.setAntiAlias(true);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        textPaint.setTextSize(30);
        textPaint.setColor(Color.WHITE);
        mPaint.setColor(Color.rgb(0xff, 0x32, 0x22));  //红色
        sPaint.setColor(Color.rgb(0xc3, 0x13, 0x07));  //按钮阴影颜色
        // mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

    }


    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        RectF bluerectF2 = new RectF(0, 0, mWidth, mHeigh);
        RectF redrectF = new RectF(0, 0, mWidth, mHeigh - 20);
        canvas.drawRoundRect(bluerectF2, 20, 20, sPaint);
        canvas.drawRoundRect(redrectF, 20, 20, mPaint);
        float textWidth = textPaint.measureText(text);
        canvas.drawText(text, mWidth / 2 - textWidth / 2, mHeigh / 2, textPaint);

    }
}
