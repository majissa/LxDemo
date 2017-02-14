package com.lx.lxlibrary.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 创建人：郑晓辉
 * 创建日期：2016/3/16
 * 描述：RecyclerView间隔装饰
 */
public class GridSpacesDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public GridSpacesDecoration(int space) {
        this.space=space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left=space;
        outRect.right=space;
        outRect.bottom=space;
        if(parent.getChildAdapterPosition(view)==0){
            outRect.top=space;
        }
    }
}
