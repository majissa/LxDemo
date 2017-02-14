package com.lx.lxdemo.activity;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.lx.lxdemo.R;
import com.lx.lxdemo.adapter.NewsAdapter;
import com.lx.lxdemo.base.JsonCallback;
import com.lx.lxdemo.bean.NewsBean;
import com.lx.lxdemo.constant.HttpConstant;
import com.lx.lxlibrary.activity.BaseActivity;
import com.lx.lxlibrary.adapter.BaseRecyclerViewAdapter;
import com.lx.lxlibrary.decoration.LinearLayoutDecoration;
import com.lx.lxlibrary.utlis.ToastUtils;
import com.lzy.okhttputils.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 创建人：李响
 * 创建日期：2016/7/24
 * 描述：
 */
public class SwipeLayoutActivity extends BaseActivity {
    private SwipeRefreshLayout swipefreshLayout;
    private NewsAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void initView(View view) {
        swipefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipefreshlayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new LinearLayoutDecoration(context));
        adapter = new NewsAdapter(context);
        adapter.setOnClickListener(this);
        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.ItemOnClickListener<NewsBean.Result.Data>() {
            @Override
            public void onItemClick(NewsBean.Result.Data bean,View view, int position) {

            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {
        swipefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.blue, R.color.yellow);
        swipefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        swipefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestNews();
            }
        });
    }

    @Override
    protected void initValue() {

    }

    /**
     * 请求数据
     */
    private void requestNews() {
        OkHttpUtils.get(HttpConstant.JU_HE_NEWS)
                .tag(this)
                .params("key", HttpConstant.JU_HE_APP_KEY)
                .params("type", "caijing")
                .execute(new JsonCallback<NewsBean>(context, NewsBean.class) {


                    @Override
                    public void onAfter(boolean isFromCache, @Nullable NewsBean newsBean, Call call, @Nullable Response response, @Nullable Exception e) {
                        super.onAfter(isFromCache, newsBean, call, response, e);
                        swipefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onResponse(boolean isFromCache, NewsBean newsBean, Request request, @Nullable Response response) {
                        if (newsBean.getError_code() == 0) {
                            List<NewsBean.Result.Data> data = newsBean.getResult().getData();
                            if (data != null && data.size() > 0) {
                                adapter.setList(data);
                            }
                        } else {
                            ToastUtils.longShow(newsBean.getReason());
                        }
                    }
                });
    }

    @Override
    protected int inflateLayoutId() {
        return R.layout.activiity_swipelayout;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_pic:
                int position = (int) v.getTag(v.getId());
                ArrayList<String> list = new ArrayList<>();
                String imagePath = adapter.getList().get(position).getThumbnail_pic_s();
                list.add(imagePath);
//                jumpToDragPhotoViewActivity((ImageView) v, imagePath, R.mipmap.bird);
                jumpToPhotoViewActivity((ImageView) v, list, R.mipmap.bird, 0);
                break;
        }
    }
}
