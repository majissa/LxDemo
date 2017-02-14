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
 * 创建日期 2017/1/6
 * 描述：
 */
public class CanMapResourceObjectsAdapter extends BaseRecyclerViewAdapter<String> {

    public CanMapResourceObjectsAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent) {
        return new CanMapResourceObjectsAdapter.TestItemViewHolder(Inflate(R.layout.adapter_can_map_resource_objects, parent));
    }

    class TestItemViewHolder extends BaseitemViewHolder {
        private TextView tv_title;

        public TestItemViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        }

        @Override
        protected void initValue(String bean, int position) {
            tv_title.setText(bean);
        }

        @Override
        protected ArrayList<View> bindListenerViews(ArrayList<View> list) {
            return null;
        }


        @Override
        protected void initView() {
        }
    }
}
