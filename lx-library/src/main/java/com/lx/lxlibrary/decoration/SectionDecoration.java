package com.lx.lxlibrary.decoration;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.lx.lxlibrary.R;
import com.lx.lxlibrary.bean.NameBean;
import com.lx.lxlibrary.utlis.ScreenUtil;

import java.util.List;

/**
 * Created by 李响
 * 创建日期 2017/2/27
 * 描述：
 */
public class SectionDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = "SectionDecoration";
    private Drawable normalDividerDrawable;
    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };
    private List<NameBean> dataList;

    private DecorationCallback callback;
    private TextPaint textPaint;
    private Paint paint;
    private int topGap;
    private int alignBottom;
    private Paint.FontMetrics fontMetrics;

    public SectionDecoration(List<NameBean> dataList, Context context, DecorationCallback decorationCallback) {
        this(dataList, context, 0, decorationCallback);
    }

    public SectionDecoration(List<NameBean> dataList, Context context, @DrawableRes int normalDividerRes, DecorationCallback decorationCallback) {
        if (normalDividerRes == 0) {
            TypedArray array = context.obtainStyledAttributes(ATTRS);
            normalDividerDrawable = array.getDrawable(0);
        } else {
            normalDividerDrawable = context.getResources().getDrawable(normalDividerRes);
        }
        Resources res = context.getResources();
        this.dataList = dataList;
        this.callback = decorationCallback;
        //设置悬浮栏的画笔---paint
        paint = new Paint();
        paint.setColor(res.getColor(R.color.colorAccent));

        //设置悬浮栏中文本的画笔
        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(ScreenUtil.dip2px(context, 14));
        textPaint.setColor(Color.DKGRAY);
        textPaint.setTextAlign(Paint.Align.LEFT);
        fontMetrics = new Paint.FontMetrics();
        //决定悬浮栏的高度等
        topGap = res.getDimensionPixelOffset(R.dimen.sectioned_top);
        //决定文本的显示位置等
        alignBottom = res.getDimensionPixelOffset(R.dimen.sectioned_alignBottom);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int pos = parent.getChildAdapterPosition(view);
        if (pos == 0 || pos == state.getItemCount() - 1) {
            outRect.top = 0;
            return;
        }
        if (pos == state.getItemCount() - 2) {
            outRect.top = topGap;
            return;
        }
        int pos1 = pos - 1;
        Log.i(TAG, "getItemOffsets：" + pos);
        String groupId = callback.getGroupId(pos1);
        if (groupId.equals("-1")) return;
        //只有是同一组的第一个才显示悬浮栏
        if (pos1 == 0 || isFirstInGroup(pos1)) {
            outRect.top = topGap;
            if (dataList.get(pos1).getName() == "") {
                outRect.set(0, 0, 0, normalDividerDrawable.getIntrinsicHeight());
//                outRect.top = 0;
            }
        } else {
            outRect.set(0, 0, 0, normalDividerDrawable.getIntrinsicHeight());
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawVertical(c, parent);
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
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
            final int bottom = top + normalDividerDrawable.getIntrinsicHeight();
            normalDividerDrawable.setBounds(left, top, right, bottom);
            normalDividerDrawable.draw(c);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int itemCount = state.getItemCount() - 2;
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        float lineHeight = textPaint.getTextSize() + fontMetrics.descent;

        String preGroupId = "";
        String groupId = "-1";
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            long itemId = parent.getLayoutManager().getItemViewType(view);
            if (itemId == 0) {
                continue;
            }
            if (itemId == 1) {
                continue;
            }
            int position = parent.getChildAdapterPosition(view) - 1;

            preGroupId = groupId;
            groupId = callback.getGroupId(position);
            if (groupId.equals("-1") || groupId.equals(preGroupId)) {
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view
                        .getLayoutParams();
                final int top = view.getBottom() + params.bottomMargin;
                final int bottom = top + normalDividerDrawable.getIntrinsicHeight();
                normalDividerDrawable.setBounds(left, top, right, bottom);
                normalDividerDrawable.draw(c);
//                continue;
            } else {
                String textLine = callback.getGroupFirstLine(position).toUpperCase();
                if (TextUtils.isEmpty(textLine)) continue;

                int viewBottom = view.getBottom();
                float textY = Math.max(topGap, view.getTop());
                //下一个和当前不一样移动当前
                if (position + 1 < itemCount) {
                    String nextGroupId = callback.getGroupId(position + 1);
                    //组内最后一个view进入了header
                    if (nextGroupId != groupId && viewBottom < textY) {
                        textY = viewBottom;
                    }
                }
                //textY - topGap决定了悬浮栏绘制的高度和位置
                c.drawRect(left, textY - topGap, right, textY, paint);
                //left+2*alignBottom 决定了文本往左偏移的多少（加-->向左移）
                //textY-alignBottom  决定了文本往右偏移的多少  (减-->向上移)
                c.drawText(textLine, left + 2 * alignBottom, textY - alignBottom, textPaint);
            }
        }
    }


    /**
     * 判断是不是组中的第一个位置
     *
     * @param pos
     * @return
     */
    private boolean isFirstInGroup(int pos) {
        if (pos == 0) {
            return true;
        } else {
            // 因为是根据 字符串内容的相同与否 来判断是不是同意组的，所以此处的标记id 要是String类型
            // 如果你只是做联系人列表，悬浮框里显示的只是一个字母，则标记id直接用 int 类型就行了
            String prevGroupId = callback.getGroupId(pos - 1);
            String groupId = callback.getGroupId(pos);
            //判断前一个字符串 与 当前字符串 是否相同
            if (prevGroupId.equals(groupId)) {
                return false;
            } else {
                return true;
            }
        }
    }

    //定义一个借口方便外界的调用
    public interface DecorationCallback {
        String getGroupId(int position);

        String getGroupFirstLine(int position);
    }

}
