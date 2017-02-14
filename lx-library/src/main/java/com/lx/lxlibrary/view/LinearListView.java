package com.lx.lxlibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

/**
 * 创建人：LX
 * 创建日期：2016/8/10
 * 描述：垂直的LinearLayout,如果没有添加方向的话，向其中添加子View的时候，是不会显示出来的
 * 与FullListView相比要设置分隔线，什么效果都要自己设置
 */
public class LinearListView extends SelsecterLinearLayout {
    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };
    private Drawable mDivider;

    public LinearListView(Context context) {
        super(context);
        checkOrizontal();
    }

    public LinearListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        checkOrizontal();
    }


    public LinearListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        checkOrizontal();
    }

    private void checkOrizontal() {
        int orientation = getOrientation();
        if (orientation != LinearLayout.VERTICAL || orientation != LinearLayout.HORIZONTAL) {
            new IllegalArgumentException("you must set the linearLayout orientation");
        }
    }

    public void setAdapter(BaseAdapter adapter) {
        setAdapter(adapter, mDivider, 0, 0);
    }


    /**
     * 设置adapter和分割线的长，宽，颜色（长，宽和颜色必须同时都有值才有效）
     * 例子：linearListView.setAdapter(followCommunityAdapter, ViewGroup.LayoutParams.MATCH_PARENT,R.dimen.line_thickness,R.color.line_color);
     *
     * @param adapter
     * @param lineWidth       可以传具体的px值也可以传match,wrap
     * @param lineHeightResId R.dimen.demo形式，如 R.dimen.line_thickness
     * @param lingColorResId  R.color.demo形式， 如 R.color.line_color
     */
    public void setAdapter(BaseAdapter adapter, Drawable drawable, int lineWidth, int lineHeightResId, int lingColorResId, int leftPadding, int rightPadding) {
        if (adapter != null && adapter.getCount() > 0) {
            removeAllViews();
            for (int i = 0; i < adapter.getCount(); i++) {
                addView(adapter.getView(i, null, this));
                if (i != adapter.getCount() - 1) {//不是最后一条
                    if (lineWidth != 0 && lineHeightResId != 0 && lingColorResId != 0) {
                        View lineView = new View(getContext());
                        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        layoutParams.setMargins(leftPadding, 0, rightPadding, 0);
                        lineView.setLayoutParams(layoutParams);
                        lineView.setBackgroundDrawable(drawable);
                        addView(lineView);
                    }
                }
            }
        }
    }

    public void setAdapter(BaseAdapter adapter, Drawable drawable, int leftPadding, int rightPadding) {
        if (adapter != null && adapter.getCount() > 0) {
            removeAllViews();
            for (int i = 0; i < adapter.getCount() * 2; i++) {
                if ((i % 2 == 0) ? true : false) {
                    View itemView = adapter.getView(i / 2, null, this);
                    addView(itemView, i);
                } else {
                    if (i == (adapter.getCount() * 2) - 1) {//不是最后一条
                        return;
                    }
                    View lineView = new View(getContext());
                    LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(leftPadding, 0, rightPadding, 0);
                    lineView.setLayoutParams(layoutParams);
                    lineView.setBackgroundDrawable(drawable);
                    addView(lineView, i);
                }
            }

        }
    }


}
