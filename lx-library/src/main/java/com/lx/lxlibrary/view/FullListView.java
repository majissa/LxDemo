package com.lx.lxlibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 放在ScrollView中，高度能够撑开自适应，并且自己不能滑动，让外面的ScrollView滑动
 * 与VerticalLinearListView相比，如果在SrollView中使用的话，会默认显示该ListView的首先，需要手动把ScrollView滚动至顶端
 *
 */
public class FullListView extends ListView {

    public FullListView(Context context) {
        super(context);
    }

    public FullListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FullListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
