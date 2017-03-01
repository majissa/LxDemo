package com.lx.lxlibrary.decoration;

/**
 * 创建人：郑晓辉
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
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
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
    private DrawableProvider mdrawbleProvider;
    private LeftPaddingProvider mleftPaddingProvider;
    private RighPaddingProvider mrighPaddingProvider;

    public LinearLayoutDecoration(Builder builder) {
        setOrientation(LinearLayoutManager.VERTICAL);
        if (builder != null) {
            mdrawbleProvider = builder.drawableProvider;
            mleftPaddingProvider = builder.leftPaddingProvider;
            mrighPaddingProvider = builder.righPaddingProvider;
            if (mdrawbleProvider != null) {
                mDivider = mdrawbleProvider.dividerDrawable();
            } else {
                Context context = builder.context;
                final TypedArray a = context.obtainStyledAttributes(ATTRS);
                mDivider = a.getDrawable(0);
                a.recycle();
            }
            if (mleftPaddingProvider != null) {
                leftPadding = mleftPaddingProvider.dividerLeftPadding();
            }
            if (mrighPaddingProvider != null) {
                rightPadding = mrighPaddingProvider.dividerRighPadding();
            }
        }
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
        for (int i = 0; i < childCount - 1; i++) {
            final View child = parent.getChildAt(i);
            long itemId = parent.getLayoutManager().getItemViewType(child);
            if (itemId == 0) {
                continue;
            }
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
        for (int i = 0; i < childCount - 1; i++) {
            final View child = parent.getChildAt(i);
            long itemId = parent.getLayoutManager().getItemViewType(child);
            if (itemId == 0) {
                continue;
            }
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        int position = parent.getChildAdapterPosition(view);
        if (position != 0 && position != state.getItemCount() - 1 && position != state.getItemCount() - 2) {//去掉ZBaseRecyclerAdapter中默认添加的头部和尾部，占位不画分割线
            if (mOrientation == VERTICAL_LIST) {
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            } else {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            }
        }
    }


    /**
     * 资源图片的提供接口
     */
    public interface DrawableProvider {
        public Drawable dividerDrawable();
    }

    /**
     * 左边边距的提供接口
     */
    public interface LeftPaddingProvider {

        @DimenRes
        public int dividerLeftPadding();
    }

    /**
     * 右边边距的提供接口
     */
    public interface RighPaddingProvider {

        @DimenRes
        public int dividerRighPadding();
    }

    public static class Builder implements DecorationBuilder {
        public Context context;
        private int mleftPadding;
        private int mrightPadding;
        public DrawableProvider drawableProvider;
        public LeftPaddingProvider leftPaddingProvider;
        public RighPaddingProvider righPaddingProvider;


        public Builder(Context context) {
            this.context = context;
        }

        @Override
        public DecorationBuilder drawable(@DrawableRes final int drawbleRes) {

            this.drawableProvider = new DrawableProvider() {
                @Override
                public Drawable dividerDrawable() {
                    return context.getResources().getDrawable(drawbleRes);
                }
            };
            return this;
        }

        @Override
        public DecorationBuilder color(@ColorRes int ColorRes) {
            return this;
        }

        @Override
        public DecorationBuilder leftPadding(@DimenRes int dimenRes) {
            mleftPadding = context.getResources().getDimensionPixelOffset(dimenRes);
            leftPaddingProvider = new LeftPaddingProvider() {
                @Override
                public int dividerLeftPadding() {
                    return mleftPadding;
                }
            };
            return this;
        }

        @Override
        public DecorationBuilder RightPadding(@DimenRes int dimenRes) {
            mrightPadding = context.getResources().getDimensionPixelOffset(dimenRes);
            righPaddingProvider = new RighPaddingProvider() {
                @Override
                public int dividerRighPadding() {
                    return mrightPadding;
                }
            };
            return this;
        }

        @Override
        public RecyclerView.ItemDecoration build() {
            return new LinearLayoutDecoration(this);
        }
    }
}
