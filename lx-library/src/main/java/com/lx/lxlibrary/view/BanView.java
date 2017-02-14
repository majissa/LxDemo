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
 * 描述：禁止的View
 */
public class BanView extends View {

    private static final int DEFAULT_WIDTH = 5;
    private int roundWidth;
    private int width;
    private int height;
    private Paint mPaint;
    private int color;
    private int centerX;
    private int centerY;
    private float progress;
    private float startX;
    private float startY;
    private float stopX;
    private float stopY;
    private float line_X;
    private float line_Y;
    private float radius;
    private RectF rectF;

    public BanView(Context context) {
        super(context);
        init(context, null);
    }

    public BanView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BanView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TickView, 0, 0);
        color = array.getColor(R.styleable.TickView_roundColor, Color.BLACK);//默认为黑色
        roundWidth = array.getInteger(R.styleable.TickView_roundWidth, DEFAULT_WIDTH);

        array.recycle();
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
        rectF = new RectF(roundWidth, roundWidth, width - roundWidth, height - roundWidth);
        if (progress >= 100) {
            progress = 100;
        }
        canvas.drawArc(rectF, 0, 360 * (progress / 100), false, mPaint);
    }

    private void drawLine(Canvas canvas) {
        if (progress < 100) {
            return;
        }
        drawFirstLine(canvas);
    }

    private void drawFirstLine(Canvas canvas) {
        if (line_X < Math.sqrt(2) * radius) {
            line_X++;
            line_Y++;
        }
        startX = (float) (radius / 2 - 5 * Math.sqrt(2));
        startY = (float) (radius / 2 - 5 * Math.sqrt(2));
//        canvas.drawLine((float) (centerX-roundWidth/1.4), (float) (centerX-roundWidth/1.4), (float) (centerX-roundWidth/1.4) + line_X, (float) (centerX-roundWidth/1.4) + line_Y, mPaint);
        canvas.drawLine(startX, startY, startX + line_X, startY + line_Y, mPaint);
//        canvas.drawLine((3/4)*width, (3/4)*width, (3/4)*width + line_X, (3/4)*width + line_Y, mPaint);
//        canvas.drawLine(rectF.centerX(), rectF.centerY(), rectF.centerX(), rectF.centerY(), mPaint);
    }
}
