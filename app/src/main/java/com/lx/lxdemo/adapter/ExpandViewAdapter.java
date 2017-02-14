package com.lx.lxdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lx.lxdemo.R;
import com.lx.lxdemo.bean.ExpandViewBean;
import com.lx.lxlibrary.adapter.BaseRecyclerViewAdapter;
import com.lx.lxlibrary.view.ExpandableItemView;

import java.util.ArrayList;


/**
 * Created by 李响
 * 创建日期 2017/1/6
 * 描述：
 */
public class ExpandViewAdapter extends BaseRecyclerViewAdapter<ExpandViewBean> {

    public ExpandViewAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent) {
        return new ExpandViewAdapter.TestItemViewHolder(Inflate(R.layout.adapter_expandrecycleview, parent));
    }

    class TestItemViewHolder extends BaseitemViewHolder {

        private ExpandableItemView expandItemView;
        private TextView tv_title;
        private ImageView iv_open;

        public TestItemViewHolder(View itemView) {
            super(itemView);

        }

        @Override
        protected void initValue(final ExpandViewBean bean, int position) {

            tv_title.setText(bean.getTitle());
            if (bean.isExpand()) {
                expandItemView.expandExpandView();
            } else {
                expandItemView.closeExpandView();
            }
        }

        @Override
        protected ArrayList<View> bindListenerViews(ArrayList<View> list) {
            list.add(iv_open);
            return list;
        }

        @Override
        protected void initView() {
            expandItemView = (ExpandableItemView) itemView.findViewById(R.id.expandItemView1);
            tv_title = (TextView) expandItemView.findViewById(R.id.tv_title);
            iv_open = (ImageView) expandItemView.findViewById(R.id.iv_open1);
        }
    }
}
