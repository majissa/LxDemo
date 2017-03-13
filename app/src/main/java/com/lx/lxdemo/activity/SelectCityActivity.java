package com.lx.lxdemo.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lx.lxdemo.R;
import com.lx.lxdemo.adapter.SelecorCityAdapter;
import com.lx.lxlibrary.activity.BaseActivity;
import com.lx.lxlibrary.bean.SortModel;
import com.lx.lxlibrary.decoration.SectionDecoration;
import com.lx.lxlibrary.view.SideBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 李响
 * 创建日期 2017/3/1
 * 描述：
 */
public class SelectCityActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private SelecorCityAdapter adapter;
    private TextView textView;
    private SideBar sideBar;
    private boolean move;
    private int mIndex;
    public static final String GET_CITYPY = "http://aixiaoqu.me/v1/" + "Home/GetCityPy";//城市
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected int inflateLayoutId() {
        return R.layout.select_city;
    }

    @Override
    protected void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        textView = (TextView) view.findViewById(R.id.textView);
        sideBar = (SideBar) view.findViewById(R.id.siderBar);
        sideBar.setFloatTextView(textView);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new SelecorCityAdapter(context);
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                int positionForSection = adapter.getPositionForSection(s.charAt(0));
                if (positionForSection != -1) {
                    mIndex = positionForSection;
//                    recyclerView.setVerticalScrollbarPosition(positionForSection);
                    moveToPosition(positionForSection);
//                    recyclerView.getLayoutManager().scrollToPosition(positionForSection);
                }
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //在这里进行第二次滚动（最后的100米！）
                if (move) {
                    move = false;
                    //获取要置顶的项在当前屏幕的位置，mIndex是记录的要置顶项在RecyclerView中的位置
                    int n = mIndex - linearLayoutManager.findFirstVisibleItemPosition();
                    if (0 <= n && n < recyclerView.getChildCount()) {
                        //获取要置顶的项顶部离RecyclerView顶部的距离
                        int top = recyclerView.getChildAt(n).getTop();
                        //最后的移动
                        recyclerView.scrollBy(0, top);
                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initValue() {
        setTitle(R.string.select_city);
        List<String> list = Arrays.asList(context.getResources().getStringArray(R.array.city));
        final ArrayList<SortModel> sortModels = new ArrayList<>();
        for (String s : list) {
            SortModel sortModel = new SortModel();
            sortModel.setName(s);
            sortModels.add(sortModel);
        }
        adapter.setList(sortModels);
        RecyclerView.ItemDecoration itemDecoration = new SectionDecoration.Builder(context, sortModels, new SectionDecoration.DecorationCallback() {
            @Override
            public String getGroupId(int position) {
                if (sortModels.get(position).getSortLetters() != null) {
                    return sortModels.get(position).getSortLetters();
                }
                return "-1";
            }

            @Override
            public String getGroupFirstLine(int position) {
                if (sortModels.get(position).getSortLetters() != null) {
                    return sortModels.get(position).getSortLetters();
                }
                return "";
            }
        }).drawable(R.drawable.rectangle_gridview_line).barColor(R.color.c3).barHeight(R.dimen.sectioned_top).textColor(R.color.blue).textAlignBottom(R.dimen.sectioned_alignBottom).build();
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

    }

    private void moveToPosition(int n) {
        //先从RecyclerView的LayoutManager中获取第一项和最后一项的Position
        int firstItem = linearLayoutManager.findFirstVisibleItemPosition();
        int lastItem = linearLayoutManager.findLastVisibleItemPosition();
        //然后区分情况
        if (n <= firstItem) {
            //当要置顶的项在当前显示的第一个项的前面时
            recyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            //当要置顶的项已经在屏幕上显示时
            int top = recyclerView.getChildAt(n - firstItem).getTop();
            recyclerView.scrollBy(0, top);
        } else {
            //当要置顶的项在当前显示的最后一项的后面时
            recyclerView.scrollToPosition(n);
            //这里这个变量是用在RecyclerView滚动监听里面的
            move = true;
        }
    }
}
