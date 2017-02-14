package com.lx.lxdemo.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lx.lxdemo.R;
import com.lx.lxdemo.base.BaseFragment;
import com.lx.lxlibrary.utlis.BitmapUtil;

/**
 * 创建人：李响
 * 创建日期：2016/8/14
 * 描述：Scale the image uniformly (maintain the image's aspect ratio)
 * so that both dimensions (width and height) of the image
 * will be equal to or larger than the corresponding dimension of the view (minus padding).
 * <p/>
 * <p/>
 * 尺度的图像均匀地（保持图像的纵横比），使图像的两个尺寸（宽度和高度）将等于或大于视图的相应尺寸（减去填充）。
 */
public class CenterDropFragment extends BaseFragment {
    private TextView textView;
    private ImageView imageView;

    @Override
    protected void initView(View view) {
        imageView = (ImageView) view.findViewById(R.id.imageView);
    }

    @Override
    protected int inflatebaseLayout() {
        return R.layout.fragment_center_drop;
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initDatas() {
        // Drawable drawable = getResources().getDrawable(R.mipmap.icon_scale_type);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_scale_type);
        Bitmap graybitmap = BitmapUtil.createSaturationBitmap(bitmap,0);
        imageView.setImageBitmap(graybitmap);
    }
}
