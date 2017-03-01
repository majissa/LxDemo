package com.lx.lxdemo.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lx.lxdemo.R;
import com.lx.lxdemo.adapter.TestRecyclerViewAdapter;
import com.lx.lxdemo.constant.IntentConstant;
import com.lx.lxlibrary.activity.BaseActivity;
import com.lx.lxlibrary.adapter.BaseRecyclerViewAdapter;
import com.lx.lxlibrary.decoration.LinearLayoutDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 创建人：LX
 * 创建日期：2016/7/12
 * 描述： https://github.com/chrisbanes/PhotoView
 * <p/>
 * PhotoView aims to help produce an easily usable implementation of a zooming Android ImageView.
 */
public class PhotoActivity extends BaseActivity {

    private RecyclerView recyclerView;

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_photo;
    }

    @Override
    protected void initView(View view) {
        setTitle(R.string.photoView_title);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new LinearLayoutDecoration.Builder(context).build());
        recyclerView.setHasFixedSize(true);
        final TestRecyclerViewAdapter adapter = new TestRecyclerViewAdapter(context);
        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.ItemOnClickListener<String>() {
            @Override
            public void onItemClick(String str, View view, int position) {
                switch (position) {
                    case 0:
                        jumpToActivity(SinglePhotoViewActivity.class);
                        break;
                    case 1:
                        final ArrayList<String> list = new ArrayList<>();
                        list.add("http://h.hiphotos.baidu.com/zhidao/pic/item/6d81800a19d8bc3ed69473cb848ba61ea8d34516.jpg");
                        list.add("http://img.taopic.com/uploads/allimg/121209/234928-12120Z0543764.jpg");
                        list.add("http://img4.duitang.com/uploads/item/201603/26/20160326193535_dj8cx.jpeg");
                        list.add("http://img3.fengniao.com/album/upload/2/287/57227/11445370_600.jpg");
                        jumpToPhotoViewActivity(list, R.mipmap.bird, 0);
                        break;
                    case 2:
                        Intent intent = new Intent(context, ImageViewActivity.class);
                        intent.putExtra(IntentConstant.FROM, 1);
                        startActivity(intent);
                        break;
                    case 3:
                        Intent intent1 = new Intent(context, ImageViewActivity.class);
                        intent1.putExtra(IntentConstant.FROM, 0);
                        startActivity(new Intent(context, ImageViewActivity.class));
                        break;
                }
            }
        });
        List<String> strings = Arrays.asList(getResources().getStringArray(R.array.photoView_list));
        adapter.setList(strings);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initValue() {

    }

    @Override
    public void onClick(View v) {

    }
}
