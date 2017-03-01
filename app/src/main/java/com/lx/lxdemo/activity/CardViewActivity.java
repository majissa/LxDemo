package com.lx.lxdemo.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lx.lxdemo.R;
import com.lx.lxdemo.adapter.CardViewAdapter;
import com.lx.lxdemo.adapter.RecentAppAdapter;
import com.lx.lxdemo.adapter.RunningTaskAppAdapter;
import com.lx.lxlibrary.activity.BaseActivity;
import com.lx.lxlibrary.decoration.LinearLayoutDecoration;

import java.util.ArrayList;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * 创建人：LX
 * 创建日期：2016/7/26
 * 描述：
 */
public class CardViewActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private PtrClassicFrameLayout ptrClassicFrameLayout;
    private RecentAppAdapter adapter;
    private CardViewAdapter cardViewAdapter;
    private RunningTaskAppAdapter runningTaskAppAdapter;

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_cardview;
    }

    @Override
    protected void initView(View view) {
//        ptrClassicFrameLayout = (PtrClassicFrameLayout) view.findViewById(R.id.ptrClassicFrameLayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new LinearLayoutDecoration.Builder(context).build());
        recyclerView.setHasFixedSize(true);
        cardViewAdapter = new CardViewAdapter(context);
        recyclerView.setAdapter(cardViewAdapter);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initValue() {
        setTitle(R.string.cardView);
        ArrayList<String> list = new ArrayList<>();
        list.add("4元代金券");
        list.add("6元代金券");
        list.add("77元代金券");
        list.add("5元代金券");
        list.add("6元代金券");
        list.add("5元代金券");
        list.add("4元代金券");
        list.add("6元代金券");
        list.add("7元代金券");
        list.add("6元代金券");
        list.add("9元代金券");
        cardViewAdapter.setList(list);
    }

    @Override
    public void onClick(View v) {

    }
}
