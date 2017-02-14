package com.lx.lxlibrary.decoration;

/**
 * 创建人：lx
 * 创建日期：2016/6/15
 * 描述：
 */
/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * limitations under the License.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

/**
 * This class is from the v7 samples of the Android SDK. It's not by me!
 * <p/>
 * See the license above for details.
 */
public class LinearLayoutDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private Drawable mDivider;

    private int mOrientation;

    private int leftPadding;
    private int rightPadding;

    public LinearLayoutDecoration(Context context) {
        this(context, LinearLayoutManager.VERTICAL, null, 0, 0);
    }

    public LinearLayoutDecoration(Context context, Drawable drawable) {
        this(context, LinearLayoutManager.VERTICAL, drawable, 0, 0);
    }

    public LinearLayoutDecoration(Context context, Drawable drawable, int leftPadding, int rightPadding) {
        this(context, LinearLayoutManager.VERTICAL, drawable, leftPadding, rightPadding);
    }

    public LinearLayoutDecoration(Context context, int orientation, Drawable drawable, int leftPadding, int rightPadding) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        if (drawable == null) {
            mDivider = a.getDrawable(0);
        } else {
            mDivider = drawable;
        }
        this.leftPadding = leftPadding;
        this.rightPadding = rightPadding;
        a.recycle();
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, State state) {

        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }

    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft() + leftPadding;
        final int right = parent.getWidth() - parent.getPaddingRight() - rightPadding;

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    /**
     * 用于偏移recycleView的每个item
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        int position = parent.getChildAdapterPosition(view);
//        if (position != 0 && position != state.getItemCount() - 1 && position != state.getItemCount() - 2) {//去掉ZBaseRecyclerAdapter中默认添加的头部和尾部，占位不画分割线
            if (mOrientation == VERTICAL_LIST) {
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            } else {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            }
        }
//    }
}
