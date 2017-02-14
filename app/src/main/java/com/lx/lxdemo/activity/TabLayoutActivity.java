package com.lx.lxdemo.activity;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lx.lxdemo.R;
import com.lx.lxlibrary.activity.BaseActivity;
import com.lx.lxdemo.fragment.Layout1Fragment;
import com.lx.lxdemo.fragment.Layout2Fragment;
import com.lx.lxdemo.fragment.Layout3Fragment;
import com.lx.lxlibrary.adapter.BaseFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建人：LX
 * 创建日期：2016/7/4
 * 描述: 1.注意 当使用setupWithViewPager(ViewPager viewpager)函数时，如果adapter没有设置getPageTitle(int position)设置标题时，
 * 是没有title标题的。可以使用第二种方法
 * tabLayput.getTabAt(0).setText(list.get(0));
 * tabLayput.getTabAt(1).setText(list.get(1));
 * tabLayput.getTabAt(2).setText(list.get(2));
 * 来设置标题哦！
 **/
public class TabLayoutActivity extends BaseActivity {

    private TabLayout tabLayput;
    private ViewPager viewpager;
    private List<String> list = new ArrayList<String>();
//    private CollapsingToolbarLayout collapsingToolbarLayout;
//    private Toolbar toolbar;

    @Override
    protected void initView(View view) {
        setTopGone();
//        collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.collapsingToolbarLayout);
//        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        collapsingToolbarLayout.setTitle("happy day");
        tabLayput = (TabLayout) view.findViewById(R.id.tabLayout);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        list.add("测试页面1");
        list.add("测试页面2");
        list.add("测试页面3");
        tabLayput.setTabMode(TabLayout.MODE_FIXED);
        tabLayput.addTab(tabLayput.newTab().setText(list.get(0)));
        tabLayput.addTab(tabLayput.newTab().setText(list.get(1)));
        tabLayput.addTab(tabLayput.newTab().setText(list.get(2)));
        Layout1Fragment layout1 = new Layout1Fragment();
        Layout2Fragment layout2 = new Layout2Fragment();
        Layout3Fragment layout3 = new Layout3Fragment();
        List<Fragment> fragmentlist = new ArrayList<>();
        fragmentlist.add(layout1);
        fragmentlist.add(layout2);
        fragmentlist.add(layout3);
        FragmentManager fragmentManager = getSupportFragmentManager();
        BaseFragmentPagerAdapter adapter = new BaseFragmentPagerAdapter<>(fragmentManager);
        adapter.setList(fragmentlist);
        viewpager.setAdapter(adapter);
        adapter.setPageTitle(list);
        // tabLayput.setTabsFromPagerAdapter(adapter);
        tabLayput.setupWithViewPager(viewpager);
       /* tabLayput.getTabAt(0).setText(list.get(0));
        tabLayput.getTabAt(1).setText(list.get(1));
        tabLayput.getTabAt(2).setText(list.get(2));*/
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initValue() {

    }

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_tablayout;
    }

    @Override
    public void onClick(View v) {

    }
}
