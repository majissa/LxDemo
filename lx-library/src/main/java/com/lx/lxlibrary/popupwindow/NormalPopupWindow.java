package com.lx.lxlibrary.popupwindow;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

/**
 * 创建日期：2015/10/13
 * 主题：普通的PopupWindow，只设置了可点击等属性以及可以自适应的宽高
 * 描述：
 */
public class NormalPopupWindow extends PopupWindow {

    private int popViewWidth;
    private int popViewHeight;

    public NormalPopupWindow(View view) {
        super(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        init(view);
    }

    /**
     * @param view
     * @param width  可以使用LinearLayout.LayoutParams.WRAP_CONTENT||LinearLayout.LayoutParams.MATCH_CONTENT||pixl
     * @param height 可以使用LinearLayout.LayoutParams.WRAP_CONTENT||LinearLayout.LayoutParams.MATCH_CONTENT||pixl
     */
    public NormalPopupWindow(View view, int width, int height) {
        super(view, width, height);
        init(view);
    }

    private void init(View view) {
        ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
        setBackgroundDrawable(colorDrawable);
        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);
        /**
         * 这句话很重要，可以让外部调用popupwindow显示位置时候，可以计算出popupwindow的宽和高度，然后用showAsDropDown()很好显示位置
         *  没有这句话的话listViewPopupWindow.getContentView().getMeasuredWidth()=0
         */
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        popViewWidth = view.getMeasuredWidth();
        popViewHeight = view.getMeasuredHeight();
    }

    /**
     * 显示在参照物view的上面正中间
     * 注意：这里面的RecyclerView的宽度和高度必须是固定值dp才能计算出popViewWidth和popViewHeight的值
     *
     * @param anchor
     */
    public void showUpCenter(View anchor) {

        /**
         * getLocationOnScreen(location)获得该View在屏幕的相对标
         */

        int[] location = new int[2];
        anchor.getLocationOnScreen(location);
        showAtLocation(anchor, Gravity.NO_GRAVITY,
                location[0] + (anchor.getWidth() - popViewWidth) / 2, location[1] - popViewHeight);
    }

    /**
     * 显示在参照物view的下面正中间，showAsDropDown默认方法是显示在下面的左边
     * 注意：这里面的RecyclerView的宽度必须是固定值dp才能计算出popViewWidth的值
     *
     * @param anchor
     */
    public void showDownCenter(View anchor) {
        int[] location = new int[2];
        anchor.getLocationOnScreen(location);
        showAsDropDown(anchor, (anchor.getWidth() - popViewWidth) / 2, 0);
    }


    /**
     * 注意：在锚View的左边下边显示popupwindow，
     * 1,当x轴方向超过屏幕的时候，popupwindow仍然可以显示，只是显示在屏幕边缘
     * 2.当y轴方向距离等于屏幕的高度的时候，popupwindow不显示出来
     * 说明是相对于锚的左下角那个店坐标
     *
     * @param anchor
     * @param xoff   距离锚View的X轴方向的偏移量，单位为dip
     * @param yoff   距离锚View的y轴方向的偏移量，单位为dip
     */
    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        super.showAsDropDown(anchor, xoff, yoff);
    }
}
