package com.lx.lxdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lx.lxdemo.R;
import com.lx.lxlibrary.adapter.BaseRecyclerViewAdapter;
import com.lx.lxlibrary.utlis.ImageUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by 李响
 * 创建日期 2017/1/20
 * 描述：
 */
public class ImageViewAdapter extends BaseRecyclerViewAdapter<String> {

    public ImageViewAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent) {
        return new ImageViewAdapter.TestItemViewHolder(Inflate(R.layout.adapter_imageview, parent));
    }

    class TestItemViewHolder extends BaseitemViewHolder {

        private ImageView iv_pic;

        public TestItemViewHolder(View itemView) {
            super(itemView);
            iv_pic = (ImageView) itemView.findViewById(R.id.iv_pic);
        }

        @Override
        protected void initValue(String value, int position) {
            ImageLoader.getInstance().displayImage(value, iv_pic, ImageUtil.getDisplayImageOptions(R.mipmap.bird));
        }

        @Override
        protected ArrayList<View> bindListenerViews(ArrayList<View> list) {
            list.add(iv_pic);
            return list;
        }

        @Override
        protected void initView() {

        }
    }
}
