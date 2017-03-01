package com.lx.lxlibrary.decoration;

import android.graphics.drawable.Drawable;
import android.support.annotation.DimenRes;

/**
 * Created by 李响
 * 创建日期 2017/2/28
 * 描述：
 */
public interface DecorationProvider {


    /**
     * 资源图片的提供接口
     */
    public Drawable dividerDrawable();

    /**
     * 左边边距的提供接口
     */
    @DimenRes
    public int dividerLeftPadding();

    /**
     * 右边边距的提供接口
     */
    @DimenRes
    public int dividerRighPadding();
}
