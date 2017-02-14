package com.lx.lxdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lx.lxdemo.R;
import com.lx.lxdemo.bean.NewsBean;
import com.lx.lxlibrary.adapter.BaseRecyclerViewAdapter;
import com.lx.lxlibrary.utlis.ImageUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by 李响
 * 创建日期 2017/1/6
 * 描述：
 */
public class NewsAdapter extends BaseRecyclerViewAdapter<NewsBean.Result.Data> {

    public NewsAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent) {
        return new NewsAdapter.TestItemViewHolder(Inflate(R.layout.adapter_news, parent));
    }

    class TestItemViewHolder extends BaseitemViewHolder {

        private TextView tv_title;
        private TextView tv_auther;
        private TextView tv_date;
        private ImageView iv_pic;

        public TestItemViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_auther = (TextView) itemView.findViewById(R.id.tv_auther);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            iv_pic = (ImageView) itemView.findViewById(R.id.iv_pic);
        }

        @Override
        protected void initValue(NewsBean.Result.Data bean, int position) {
            String author_name = bean.getAuthor_name();
            String thumbnail_pic_s = bean.getThumbnail_pic_s();
            if (!TextUtils.isEmpty(thumbnail_pic_s)) {
                ImageLoader.getInstance().displayImage(thumbnail_pic_s, iv_pic, ImageUtil.getDisplayImageOptions(R.mipmap.ic_launcher));
            }
            tv_title.setText(bean.getTitle());
            tv_auther.setText(author_name);
            tv_date.setText(bean.getDate());
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
