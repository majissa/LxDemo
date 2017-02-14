package com.lx.lxdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lx.lxdemo.R;

/**
 * Created by 李响
 * 创建日期 2016/12/29
 * 描述：
 */
public class AddViewLinearLayout extends LinearLayout {
    public AddViewLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public AddViewLinearLayout(Context context) {
        super(context);
        init(context);
    }

    public AddViewLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        setOrientation(HORIZONTAL);
        for (int index = 0; index < 4; index++) {
            View textView = LayoutInflater.from(context).inflate(R.layout.inflate_addview_test, this, false);
            LinearLayout.LayoutParams layoutParams =  new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            addView(textView);
        }
    }
}
