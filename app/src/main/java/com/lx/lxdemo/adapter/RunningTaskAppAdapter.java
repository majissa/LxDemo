package com.lx.lxdemo.adapter;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lx.lxdemo.R;
import com.lx.lxlibrary.adapter.BaseRecyclerViewAdapter;

import java.util.ArrayList;


/**
 * 创建人：LX
 * 创建日期：2016/7/26
 * 描述：
 * <p/>
 * 1.注意：适配器中为item设置布局的时候，高度最好是WRAP_CONTENT,不然使用
 * LayoutInflater.from(context).inflate(layoutRes, parent,false);，设置布局的时候，在滑动的时候每个item的高度都会
 * 变成match_parent即撑满屏幕
 */
public class RunningTaskAppAdapter extends BaseRecyclerViewAdapter<ActivityManager.AppTask> {


    public RunningTaskAppAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent) {
        return new TestItemViewHolder(Inflate(R.layout.adapetr_running_task_app, parent));
    }

    class TestItemViewHolder extends BaseitemViewHolder {

        private TextView tv_baseactivity;
        private TextView tv_topbaseactivity;
        private TextView tv_name;


        public TestItemViewHolder(View itemView) {
            super(itemView);
            tv_baseactivity = (TextView) itemView.findViewById(R.id.tv_baseactivity);
            tv_topbaseactivity = (TextView) itemView.findViewById(R.id.tv_topbaseactivity);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        }

        @Override
        protected void initValue(ActivityManager.AppTask value, int position) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tv_baseactivity.setText("baseactivity:"+value.getTaskInfo().baseActivity.getClassName());
                    tv_topbaseactivity.setText("tooactivity:"+value.getTaskInfo().topActivity.getClassName());
                    tv_name.setText("num:"+value.getTaskInfo().numActivities);
                }
            }

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