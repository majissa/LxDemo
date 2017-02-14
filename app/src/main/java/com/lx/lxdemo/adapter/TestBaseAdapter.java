package com.lx.lxdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lx.lxdemo.R;
import com.lx.lxlibrary.adapter.AbsBaseAdapter;

import java.util.List;

/**
 * 创建人：LX
 * 创建日期：2016/8/11
 * 描述：
 */
public class TestBaseAdapter extends AbsBaseAdapter<String> {

    public TestBaseAdapter(Context context) {
        super(context);
    }

    public TestBaseAdapter(Context context, List<String> datas) {
        super(context, datas);
    }

    @Override
    protected View findViewByLayout() {
        return LayoutInflater.from(context).inflate(R.layout.adapter_demo_list, null);
    }


    @Override
    public AbsViewHolder onCreateViewHolder(ViewGroup parent) {
        return new viewHolder();
    }


    public class viewHolder extends AbsBaseAdapter<String>.AbsViewHolder {

        private TextView tv_name;


        @Override
        public void findView(View view) {
            tv_name = (TextView) view.findViewById(R.id.tv_name);
        }

        @Override
        public void setListener(int positon, View.OnClickListener onClickListener) {
            tv_name.setTag(R.id.tv_name, positon);
//            tv_name.setOnClickListener(onClickListener);
        }

        @Override
        public void initData(String item) {
            tv_name.setText(item);
        }

    }
}
