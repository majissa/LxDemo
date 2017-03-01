package com.lx.lxdemo.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lx.lxdemo.R;
import com.lx.lxdemo.adapter.CanMapResourceObjectsAdapter;
import com.lx.lxlibrary.activity.BaseActivity;
import com.lx.lxlibrary.adapter.BaseRecyclerViewAdapter;
import com.lx.lxlibrary.decoration.LinearLayoutDecoration;

import java.util.Arrays;

/**
 * Created by 李响
 * 创建日期 2017/1/12
 * 描述：可绘制资源对象
 */
public class CanMapResourceObjectsActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private CanMapResourceObjectsAdapter canMapResourceObjectsAdapter;

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_can_map_resource_objects;
    }

    @Override
    protected void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new LinearLayoutDecoration.Builder(context).build());
        recyclerView.setHasFixedSize(true);
        canMapResourceObjectsAdapter = new CanMapResourceObjectsAdapter(context);
        canMapResourceObjectsAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.ItemOnClickListener<String>() {
            @Override
            public void onItemClick(String str,View view, int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(context, ShapeActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(context, ShapeActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(context, TransitionDrawableActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(context, TransitionDrawableActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(context, TransitionDrawableActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(context, ShapeActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(context, TransitionDrawableActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(context, ClipDrawableActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(context, InsertDrawableActivity.class));
                        break;
                }
            }
        });
        recyclerView.setAdapter(canMapResourceObjectsAdapter);
    }

    @Override
    protected void initEvent() {
      runOnUiThread(new Thread());
    }

    @Override
    protected void initValue() {
        setTitle(R.string.can_map_resource_objects);
        canMapResourceObjectsAdapter.setList(Arrays.asList(getResources().getStringArray(R.array.can_map_resource_objects)));
    }

    @Override
    public void onClick(View v) {

    }
}
