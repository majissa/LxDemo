package com.lx.lxdemo.view;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.lx.lxlibrary.log.Logger;

import static android.view.View.MeasureSpec.getMode;

/**
 * Created by 李响
 * 创建日期 2016/12/29
 * 描述：
 */
public class DrawLineView extends View {
    private static final String TAG = "DrawLineView";
    private Paint linePaint;

    public DrawLineView(Context context) {
        super(context);
        init();
    }

    public DrawLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        linePaint = new Paint();
        linePaint.setAntiAlias(true);//设置抗锯齿
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = getMode(widthMeasureSpec);
        int heighMode = MeasureSpec.getMode(heightMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                Logger.d(TAG, "width at most");
                break;
            case MeasureSpec.EXACTLY:
                Logger.d(TAG, "width exactly");
                break;
            case MeasureSpec.UNSPECIFIED:
                Logger.d(TAG, "width unspecified");
                break;

        }
    }
}
