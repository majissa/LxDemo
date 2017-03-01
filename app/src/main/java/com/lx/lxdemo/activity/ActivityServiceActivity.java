package com.lx.lxdemo.activity;

import android.app.ActivityManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.lx.lxdemo.R;
import com.lx.lxdemo.adapter.RecentAppAdapter;
import com.lx.lxdemo.adapter.RunningTaskAppAdapter;
import com.lx.lxdemo.adapter.TestRecyclerViewAdapter;
import com.lx.lxlibrary.activity.BaseActivity;
import com.lx.lxlibrary.decoration.LinearLayoutDecoration;

import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * 创建人：LX
 * 创建日期：2016/7/26
 * 描述：
 */
public class ActivityServiceActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private PtrClassicFrameLayout ptrClassicFrameLayout;
    private RecentAppAdapter adapter;
    private TestRecyclerViewAdapter testBaseAdapter;
    private RunningTaskAppAdapter runningTaskAppAdapter;
    private Button btn_system_process;
    private Button btn_system_background;
    private ActivityManager activityManager;

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_activity_service;
    }

    @Override
    protected void initView(View view) {
        //        ptrClassicFrameLayout = (PtrClassicFrameLayout) view.findViewById(R.id.ptrClassicFrameLayout);
        btn_system_process = (Button) view.findViewById(R.id.btn_system_process);
        btn_system_background = (Button) view.findViewById(R.id.btn_system_background);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new LinearLayoutDecoration.Builder(context).build());
        recyclerView.setHasFixedSize(true);
//        testBaseAdapter = new TestRecyclerViewAdapter(context);
//        runningTaskAppAdapter = new RunningTaskAppAdapter(context);
        adapter = new RecentAppAdapter(context);
//        List<String> strings = Arrays.asList(getResources().getStringArray(R.array.demo_list));
//        adapter.setList(strings);
    }

    @Override
    protected void initEvent() {
        btn_system_background.setOnClickListener(this);
        btn_system_process.setOnClickListener(this);
    }

    @Override
    protected void initValue() {
        activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_system_background:
                List<ActivityManager.AppTask> appTasks = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    appTasks = activityManager.getAppTasks();
                    runningTaskAppAdapter.setList(appTasks);
                    recyclerView.setAdapter(runningTaskAppAdapter);
                }
                break;
            case R.id.btn_system_process:
                List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();//获得正在运行的APP进程
                if (runningAppProcesses!=null&&runningAppProcesses.size() > 0) {
                    adapter.setList(runningAppProcesses);
                    recyclerView.setAdapter(adapter);
                }
                break;
        }
    }
}
