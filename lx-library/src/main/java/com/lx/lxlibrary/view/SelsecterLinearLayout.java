package com.lx.lxlibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 创建人：LX
 * 创建日期：2016/8/12
 * 描述：类似于Android源生的RadioGroup，其中的选择子类只有一种可以SetSelector
 */
public class SelsecterLinearLayout extends LinearLayout {
    public SelsecterLinearLayout(Context context) {
        super(context);
    }

    public SelsecterLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SelsecterLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 设置子类所选的位置为Select
     *
     * @param position
     */
    private void setSelector(int position) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setSelected(false);
        }
        getChildAt(position).setSelected(true);
    }


    /**
     * 判断子类是否选择
     *
     * @return
     */
    private boolean isChildrenSelector() {
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i).isSelected()) {
                return true;
            }
        }
        return false;
    }

}
