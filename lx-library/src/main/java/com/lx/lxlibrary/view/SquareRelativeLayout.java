package com.lx.lxlibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.lx.lxlibrary.log.Logger;

/**
 * 创建人：LX
 * 创建日期：2016/8/10
 * 描述：正方形的布局
 */
public class SquareRelativeLayout extends RelativeLayout {
    public SquareRelativeLayout(Context context) {
        super(context);
    }

    public SquareRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @SuppressWarnings("unused")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // For simple implementation, or internal size is always 0.
        // We depend on the container to specify the layout size of
        // our view. We can't really know what it is since we will be
        // adding and removing different arbitrary views and do not
        // want the layout to change as this happens.


       /* 1.getDefaultSize(size, widthMeasureSpec) 用于得到View的大小，如果父类没有指定子类widthMeasureSpec的大小的时候，
           会输出第一个参数的size的大小，也就是缺省的大小

          2.当没有执行setMeasuredDimension()时 getMeasuredWidth()返回的值为0，当在执行setMeasuredDimension()之后执行的getMeasuredWidth()是有值的

          3.MeasureSpec.makeMeasureSpec(),该函数用于指定MeasureSpec的大小和模式
        */

        //只是测量出widthMeasureSpec，heightMeasureSpec
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));

        // Children are just made to fill our space.
        int childWidthSize = getMeasuredWidth();
        int childHeightSize = getMeasuredHeight();
        Logger.dLi("width" + childWidthSize);
        Logger.dLi("hidth" + childHeightSize);

        /*heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.EXACTLY);*/

        //高度和宽度一样
        heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }


}
