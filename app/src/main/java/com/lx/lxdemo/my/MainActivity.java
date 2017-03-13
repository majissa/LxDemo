package com.lx.lxdemo.my;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

import com.lx.lxdemo.R;
import com.lx.lxdemo.activity.ActivityServiceActivity;
import com.lx.lxdemo.activity.ActivityTaskActivity;
import com.lx.lxdemo.activity.AddViewTestActivity;
import com.lx.lxdemo.activity.CanMapResourceObjectsActivity;
import com.lx.lxdemo.activity.CardViewActivity;
import com.lx.lxdemo.activity.CoordinatorLayoutActivity;
import com.lx.lxdemo.activity.CustomWidgetActivity;
import com.lx.lxdemo.activity.EditTextActivity;
import com.lx.lxdemo.activity.ExpandleRecycleViewActivity;
import com.lx.lxdemo.activity.LoadApkActivity;
import com.lx.lxdemo.activity.MarqueeViewActivity;
import com.lx.lxdemo.activity.PermissionActivity;
import com.lx.lxdemo.activity.PhotoActivity;
import com.lx.lxdemo.activity.PpwActivity;
import com.lx.lxdemo.activity.ScaleTypeActivity;
import com.lx.lxdemo.activity.SelectCityActivity;
import com.lx.lxdemo.activity.SpannableStringActivity;
import com.lx.lxdemo.activity.SquareRelativeLayoutActivity;
import com.lx.lxdemo.activity.SwipeLayoutActivity;
import com.lx.lxdemo.activity.SystemActivity;
import com.lx.lxdemo.activity.TabLayoutActivity;
import com.lx.lxdemo.activity.TestWebViewActivity;
import com.lx.lxdemo.activity.TouchActivity;
import com.lx.lxdemo.activity.VarietyIconActivity;
import com.lx.lxdemo.activity.VectorDrawableActivity;
import com.lx.lxdemo.activity.WebViewActivity;
import com.lx.lxdemo.adapter.MainAdapter;
import com.lx.lxdemo.common.Logger;
import com.lx.lxlibrary.activity.BaseActivity;
import com.lx.lxlibrary.activity.BaseWebViewActivity;
import com.lx.lxlibrary.adapter.BaseRecyclerViewAdapter;
import com.lx.lxlibrary.decoration.GridLayoutDecoration;
import com.lx.lxlibrary.utlis.ToastUtils;

import java.util.Arrays;

public class MainActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private SparseArray<Class> activityMap;

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initValue() {
        if (saveInstanceState != null) {
            String saveInstance = saveInstanceState.getString("save");
            Logger.dLi(saveInstance);
        }
        putActivityList();
        setTopVisibility();
        setTitle(R.string.app_name);
        setTopGone();
        setRightImageViewGone();

        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new GridLayoutDecoration.Builder(context).drawable(R.drawable.rectangle_gridview_line).build());

        MainAdapter mainAdapter = new MainAdapter(context);
        mainAdapter.setOnClickListener(this);
        mainAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.ItemOnClickListener() {
            @Override
            public void onItemClick(Object o, View view, int position) {
                if (activityMap.get(position) != null) {
                    switch (position) {
                        case 16:
                            Intent intent = new Intent(context, WebViewActivity.class);
                            intent.putExtra(BaseWebViewActivity.URL, "file:///android_asset/test.html");
                            startActivity(intent);
                            break;
                        default:
                            startActivity(new Intent(context, activityMap.get(position)));
                            break;
                    }
//                    finish();
                    overridePendingTransition(0, 0);
                } else {
                    ToastUtils.shortShow("to be contnue...");
                }
            }
        });
        mainAdapter.setList(Arrays.asList(getResources().getStringArray(R.array.demo_list)));
        recyclerView.setAdapter(mainAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_name:
//                int positon = (int) v.getTag(R.id.tv_name);
//                if (activityMap.get(positon) != null) {
//                    switch (positon) {
//                        case 16:
//                            Intent intent = new Intent(context, WebViewActivity.class);
//                            intent.putExtra(BaseWebViewActivity.URL, "file:///android_asset/test.html");
//                            startActivity(intent);
//                            break;
//                        default:
//                            startActivity(new Intent(context, activityMap.get(positon)));
//                            break;
//                    }
//                    overridePendingTransition(0, 0);
//                } else {
//                    ToastUtils.shortShow("to be contnue...");
//                }
                break;
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("save", "main_lixiang");


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            com.lx.lxlibrary.log.Logger.dLi(savedInstanceState.getString("save"));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    private void putActivityList() {
        activityMap = new SparseArray<Class>();
        activityMap.put(0, PpwActivity.class);
        activityMap.put(1, TabLayoutActivity.class);
        activityMap.put(2, CanMapResourceObjectsActivity.class);
        activityMap.put(3, PhotoActivity.class);
        activityMap.put(4, EditTextActivity.class);
        activityMap.put(5, TouchActivity.class);
        activityMap.put(6, SwipeLayoutActivity.class);
        activityMap.put(7, CardViewActivity.class);
        activityMap.put(8, CoordinatorLayoutActivity.class);
        activityMap.put(9, SquareRelativeLayoutActivity.class);
        activityMap.put(10, ScaleTypeActivity.class);
        activityMap.put(11, SpannableStringActivity.class);
        activityMap.put(12, ActivityTaskActivity.class);
        activityMap.put(13, SystemActivity.class);
        activityMap.put(14, LoadApkActivity.class);
        activityMap.put(15, WebViewActivity.class);
        activityMap.put(16, TestWebViewActivity.class);
        activityMap.put(17, VarietyIconActivity.class);
        activityMap.put(18, ActivityServiceActivity.class);
        activityMap.put(19, AddViewTestActivity.class);
        activityMap.put(20, VectorDrawableActivity.class);
        activityMap.put(21, MarqueeViewActivity.class);
        activityMap.put(22, PermissionActivity.class);//跑马灯控件
        activityMap.put(23, CustomWidgetActivity.class);//自定义控件页面
        activityMap.put(24, ExpandleRecycleViewActivity.class);//
        activityMap.put(25, SelectCityActivity.class);//
    }
}
