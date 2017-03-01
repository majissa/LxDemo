package com.lx.lxlibrary.decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by qibin on 2015/11/7.
 */
public class GridLayoutDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private Drawable horizontalDrawable;
    private Drawable verticalDrawable;
    private DrawableProvider drawableProvider;

    public GridLayoutDecoration(Builder builder) {
        if (builder != null) {
            drawableProvider = builder.drawableProvider;
            if (drawableProvider != null) {
                horizontalDrawable = drawableProvider.dividerHorizontalDrawable();
                verticalDrawable = drawableProvider.dividerVerticalDrawable();
            } else {
                final TypedArray a = builder.context.obtainStyledAttributes(ATTRS);
                verticalDrawable = a.getDrawable(0);
                horizontalDrawable = a.getDrawable(0);
                a.recycle();
            }
        }
    }

//    public GridLayoutDecoration(Context context) {
//        this(context, 0, true);
//    }
//
//    public GridLayoutDecoration(Context context, boolean drawHorizontal) {
//        this(context, 0, drawHorizontal);
//    }
//
//    public GridLayoutDecoration(Context context, @DrawableRes int id) {
//        this(context, id, true);
//    }

//    public GridLayoutDecoration(Context context, @DrawableRes int id, boolean drawHorizontal) {
//        this.drawHorizontal = drawHorizontal;
//        final TypedArray a = context.obtainStyledAttributes(ATTRS);
//        if (id == 0) {
//            mDivider = a.getDrawable(0);
//        } else {
//            mDivider = context.getResources().getDrawable(id);
//        }
//        a.recycle();
//    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (horizontalDrawable != null)
            drawHorizontal(c, parent, horizontalDrawable);

        if (verticalDrawable != null)
            drawVertical(c, parent, verticalDrawable);
    }

    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {

            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager)
                    .getSpanCount();
        }
        return spanCount;
    }

    public void drawHorizontal(Canvas c, RecyclerView parent, Drawable drawable) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {//因为ZBaseRecyclerAdapter默认有头部和尾部，所以i从1开始，不是0，childCount要减1
            final View child = parent.getChildAt(i);
            long itemId = parent.getLayoutManager().getItemViewType(child);
            if (itemId == 0) {
                continue;
            }
            if (itemId == 1) {
                continue;
            }
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getLeft() - params.leftMargin;
            final int right = child.getRight() + params.rightMargin
                    + drawable.getIntrinsicWidth();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + drawable.getIntrinsicHeight();
            drawable.setBounds(left, top, right, bottom);
            drawable.draw(c);
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent, Drawable drawable) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {//因为ZBaseRecyclerAdapter默认有头部和尾部，所以i从1开始，不是0，childCount要减1
            final View child = parent.getChildAt(i);
            long itemId = parent.getLayoutManager().getItemViewType(child);
            if (itemId == 0) {
                continue;
            }
            if (itemId == 1) {
                continue;
            }
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;
            final int left = child.getRight() + params.rightMargin;
            final int right = left + drawable.getIntrinsicWidth();

            drawable.setBounds(left, top, right, bottom);
            drawable.draw(c);
        }
    }

    private boolean isLastColum(RecyclerView parent, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
            {
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                {
                    return true;
                }
            } else {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
                    return true;
            }
        }
        return false;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        int pos = position;
        Drawable mdrawable = null;
        if (horizontalDrawable != null) {
            mdrawable = horizontalDrawable;
        }
        if (verticalDrawable != null) {
            mdrawable = verticalDrawable;
        }
        if (position == 0) {
            outRect.set(0, 0, 0, mdrawable.getIntrinsicHeight());
            return;
        } else {
            pos = position - 1;
        }
        if (isLastColum(parent, pos, spanCount, childCount)) {
            outRect.set(0, 0, 0, mdrawable.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, mdrawable.getIntrinsicWidth(), mdrawable.getIntrinsicHeight());
        }
    }

    /**
     * 资源图片的提供接口
     */
    public interface DrawableProvider {
        public Drawable dividerHorizontalDrawable();

        public Drawable dividerVerticalDrawable();
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

        public DecorationBuilder drawableHorizontalDrawable(@DrawableRes final int horizontalDrawableRes) {
            drawableProvider = new DrawableProvider() {
                @Override
                public Drawable dividerHorizontalDrawable() {
                    return context.getResources().getDrawable(horizontalDrawableRes);
                }

                @Override
                public Drawable dividerVerticalDrawable() {
                    return null;
                }
            };
            return this;
        }

        public DecorationBuilder drawableVeriticalDrawable(@DrawableRes final int veriticalDrawableRes) {
            drawableProvider = new DrawableProvider() {
                @Override
                public Drawable dividerHorizontalDrawable() {
                    return null;
                }

                @Override
                public Drawable dividerVerticalDrawable() {
                    return context.getResources().getDrawable(veriticalDrawableRes);
                }
            };
            return this;
        }

        @Override
        public DecorationBuilder drawable(@DrawableRes final int drawbleRes) {
            drawableProvider = new DrawableProvider() {
                @Override
                public Drawable dividerHorizontalDrawable() {
                    return context.getResources().getDrawable(drawbleRes);
                }

                @Override
                public Drawable dividerVerticalDrawable() {
                    return context.getResources().getDrawable(drawbleRes);
                }
            };
            return this;
        }

        @Override
        public DecorationBuilder color(@ColorRes int ColorRes) {
            return null;
        }

        @Override
        public DecorationBuilder leftPadding(@DimenRes int dimenRes) {
            return null;
        }

        @Override
        public DecorationBuilder RightPadding(@DimenRes int dimenRes) {
            return null;
        }

        @Override
        public RecyclerView.ItemDecoration build() {
            return new GridLayoutDecoration(this);
        }
    }
}
