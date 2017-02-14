package com.lx.lxdemo.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.lx.lxdemo.R;
import com.lx.lxdemo.adapter.ImageViewAdapter;
import com.lx.lxdemo.constant.IntentConstant;
import com.lx.lxlibrary.activity.BaseActivity;
import com.lx.lxlibrary.adapter.BaseRecyclerViewAdapter;
import com.lx.lxlibrary.decoration.LinearLayoutDecoration;

import java.util.ArrayList;

/**
 * Created by 李响
 * 创建日期 2017/1/20
 * 描述：
 */
public class ImageViewActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private ImageViewAdapter imageViewAdapter;
    private ArrayList<String> list;
    private int from;

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_imageview;
    }

    @Override
    protected void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new LinearLayoutDecoration(context));
        recyclerView.setHasFixedSize(true);
        imageViewAdapter = new ImageViewAdapter(context);
        imageViewAdapter.setOnClickListener(this);
        imageViewAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.ItemOnClickListener<String>() {
            @Override
            public void onItemClick(String str, View view, int position) {

            }
        });

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initValue() {
        from = getIntent().getIntExtra(IntentConstant.FROM, 0);
        list = new ArrayList<>();
        list.add("http://h.hiphotos.baidu.com/zhidao/pic/item/6d81800a19d8bc3ed69473cb848ba61ea8d34516.jpg");
        list.add("http://img.taopic.com/uploads/allimg/121209/234928-12120Z0543764.jpg");
        list.add("http://img4.duitang.com/uploads/item/201603/26/20160326193535_dj8cx.jpeg");
        list.add("http://img3.fengniao.com/album/upload/2/287/57227/11445370_600.jpg");
        imageViewAdapter.setList(list);
        recyclerView.setAdapter(imageViewAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_pic:
                int position = (int) v.getTag(v.getId());
                if (from == 1) {
                    jumpToPhotoViewActivity((ImageView) v, list, R.mipmap.bird, position);
                } else if (from == 0) {
                    jumpToDragPhotoViewActivity((ImageView) v, list, position, R.mipmap.bird);
                }
                break;
        }
    }
}
