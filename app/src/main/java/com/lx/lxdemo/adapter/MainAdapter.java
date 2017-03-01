package com.lx.lxdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lx.lxdemo.R;
import com.lx.lxlibrary.adapter.BaseRecyclerViewAdapter;

import java.util.ArrayList;

/**
 * Created by 李响
 * 创建日期 2017/2/27
 * 描述：
 */
public class MainAdapter extends BaseRecyclerViewAdapter<String> {

    public MainAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent) {
        return new ItemViewHolder(Inflate(R.layout.adapter_demo_list, parent));
    }

    class ItemViewHolder extends BaseitemViewHolder {

        private TextView tv_name;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        }

        @Override
        protected void initValue(String bean, int position) {
            tv_name.setText(bean);
        }

        @Override
        protected ArrayList<View> bindListenerViews(ArrayList<View> list) {
            return list;
        }

        @Override
        protected void initView() {

        }
    }
}

