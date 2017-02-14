package com.lx.lxdemo.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lx.lxdemo.R;
import com.lx.lxdemo.adapter.TestRecyclerViewAdapter;
import com.lx.lxdemo.base.BaseFragment;

import java.util.Arrays;
import java.util.List;

/**
 * 创建人：LX
 * 创建日期：2016/7/4
 * 描述：
 */
public class Layout1Fragment extends BaseFragment {

    private RecyclerView recyclerView;

    @Override
    protected void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

    }

    @Override
    protected int inflatebaseLayout() {
        return R.layout.fragment_layout1;
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initDatas() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        TestRecyclerViewAdapter adapter = new TestRecyclerViewAdapter(context);
        List<String> strings = Arrays.asList(getResources().getStringArray(R.array.demo_list));
        adapter.setList(strings);
        recyclerView.setAdapter(adapter);
    }
}
