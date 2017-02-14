package com.lx.lxlibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.lx.lxlibrary.R;


/**
 * Created by 李响
 * 创建日期 2017/2/4
 * 描述：打钩的View
 */
public class TickView extends View {

    private static final int DEFAULT_WIDTH = 5;
    private int roundWidth;
    private int width;
    private int height;
    private Paint mPaint;
    private int color;
    private int centerX;
    private int centerY;
    private float progress;
    private float startX_One;
    private float startY_One;
    private float startX_Two;
    private float startY_Two;
    private float stopX_One;
    private float stopY_One;
    private float line1_X;
    private float line1_Y;
    private float line2_X;
    private float line2_Y;
    private float radius;

    public TickView(Context context) {
        super(context);
        init(context, null);
    }

    public TickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TickView, 0, 0);
        color = array.getColor(R.styleable.TickView_roundColor, Color.BLACK);//默认为黑色
        roundWidth = array.getInteger(R.styleable.TickView_roundWidth, DEFAULT_WIDTH);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(color);
        mPaint.setStrokeWidth(roundWidth);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = width;

        centerX = width / 2;
        centerY = height / 2;

        radius = centerX - roundWidth;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
        drawLine(canvas);
        postInvalidateDelayed(1);//每个十毫米绘画一次
    }

    /**
     * 画圆圈
     */
    private void drawCircle(Canvas canvas) {
        progress++;
        RectF rectF = new RectF(roundWidth, roundWidth, width - roundWidth, height - roundWidth);
        if (progress >= 100) {
            progress = 100;
        }
        canvas.drawArc(rectF, 0, 300 * (progress / 100), false, mPaint);
    }

    private void drawLine(Canvas canvas) {
        if (progress < 100) {
            return;
        }
        drawFirstLine(canvas);
    }

    private void drawFirstLine(Canvas canvas) {
        startX_One = width / 4;
        startY_One = height / 2;
        startX_Two = centerX;
        startY_Two = 3 * (height / 4);
        if (line1_X < (radius / 2)) {
            line1_X++;
            line1_Y++;
            stopX_One = startX_One + line1_X;
            stopY_One = startY_One + line1_Y;
        }
        canvas.drawLine(startX_One, startY_One, stopX_One, stopY_One, mPaint);
        if (line1_X == (radius / 2)) {
            line1_X++;
            line1_Y++;
            stopX_One = startX_One + line1_X;
            stopY_One = startY_One + line1_Y;
            startX_Two = stopX_One - roundWidth-5;
            startY_Two = stopY_One - roundWidth-5;
        }
        if (line1_X > (radius / 2)) {
            if (line2_X < centerX && line2_Y > -centerX) {
                line2_X++;
                line2_Y--;
            }
            canvas.drawLine(stopX_One-(roundWidth/2), stopY_One-(roundWidth/4), stopX_One-(roundWidth/2) + line2_X, stopY_One-(roundWidth/4) + line2_Y, mPaint);
        }
    }
}
