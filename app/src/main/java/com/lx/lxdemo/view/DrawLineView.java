package com.lx.lxdemo.view;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 李响
 * 创建日期 2016/12/29
 * 描述：
 */
public class DrawLineView extends View {
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
}
