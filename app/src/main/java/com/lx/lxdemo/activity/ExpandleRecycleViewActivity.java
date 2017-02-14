package com.lx.lxdemo.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lx.lxdemo.R;
import com.lx.lxdemo.adapter.ExpandViewAdapter;
import com.lx.lxdemo.bean.ExpandViewBean;
import com.lx.lxlibrary.activity.BaseActivity;
import com.lx.lxlibrary.adapter.BaseRecyclerViewAdapter;
import com.lx.lxlibrary.decoration.LinearLayoutDecoration;

import java.util.ArrayList;


/**
 * Created by 李响
 * 创建日期 2017/2/6
 * 描述：可扩展的RecycleView
 */
public class ExpandleRecycleViewActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private ExpandViewAdapter adapter;
    private int lastPosition = -1;

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_expand_recycleview;
    }

    @Override
    protected void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new LinearLayoutDecoration(context));
        adapter = new ExpandViewAdapter(context);
        adapter.setOnClickListener(this);
        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.ItemOnClickListener<ExpandViewBean>() {
            @Override
            public void onItemClick(ExpandViewBean bean, View view, int position) {
                if (bean.isExpand()) {
                    bean.setExpand(false);
                } else {
                    bean.setExpand(true);
                }
                adapter.notifyDataSetChanged();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initValue() {
        ArrayList<ExpandViewBean> list = new ArrayList<>();
        ExpandViewBean expandViewBean1 = new ExpandViewBean();
        ExpandViewBean expandViewBean2 = new ExpandViewBean();
        ExpandViewBean expandViewBean3 = new ExpandViewBean();
        ExpandViewBean expandViewBean4 = new ExpandViewBean();
        ExpandViewBean expandViewBean5 = new ExpandViewBean();

        expandViewBean1.setTitle("水果");
        expandViewBean1.setExpand(false);
        expandViewBean2.setTitle("li");
        expandViewBean2.setExpand(false);
        expandViewBean3.setTitle("yy");
        expandViewBean3.setExpand(false);
        expandViewBean4.setTitle("gg");
        expandViewBean4.setExpand(false);

        list.add(expandViewBean1);
        list.add(expandViewBean2);
        list.add(expandViewBean3);
        list.add(expandViewBean4);

        adapter.setList(list);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_open1:

                break;
        }
    }
}
