package com.lx.lxdemo.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lx.lxdemo.R;
import com.lx.lxlibrary.activity.BaseActivity;

/**
 * 创建人：李响
 * 创建日期：2016/8/24
 * 描述：
 */
public class SingleTopActivity extends BaseActivity {

    private Button btn_standard;
    private Button btn_singleTop;
    private Button btn_singTask;
    private Button btn_singInstance;
    private TextView acitivity_name;

    @Override
    protected void initView(View view){
        acitivity_name = (TextView) view.findViewById(R.id.acitivity_name);
        btn_standard = (Button) view.findViewById(R.id.btn_standard);
        btn_singleTop = (Button) view.findViewById(R.id.btn_singleTop);
        btn_singTask = (Button) view.findViewById(R.id.btn_singTask);
        btn_singInstance = (Button) view.findViewById(R.id.btn_singInstance);
    }

    @Override
    protected void initEvent() {
        btn_standard.setOnClickListener(this);
        btn_singleTop.setOnClickListener(this);
        btn_singTask.setOnClickListener(this);
        btn_singInstance.setOnClickListener(this);
    }
    
    @Override
    protected void initValue() {
        acitivity_name.setText(this.toString());
    }

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_task_activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_standard:
                jumpToActivity(ActivityTaskActivity.class);
                break;

            case R.id.btn_singleTop:
                jumpToActivity(SingleTopActivity.class);
                break;

            case R.id.btn_singTask:
                jumpToActivity(SingleTaskActivity.class);
                break;

            case R.id.btn_singInstance:
                jumpToActivity(SingleInstanceActivity.class);
                break;
        }
    }
}
