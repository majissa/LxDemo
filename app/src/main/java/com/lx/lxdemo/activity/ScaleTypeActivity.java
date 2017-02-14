package com.lx.lxdemo.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lx.lxdemo.R;
import com.lx.lxlibrary.activity.BaseActivity;
import com.lx.lxdemo.fragment.CenterDropFragment;
import com.lx.lxdemo.fragment.CenterFragment;
import com.lx.lxdemo.fragment.CenterInsideFragment;
import com.lx.lxdemo.fragment.FinCenterFragment;
import com.lx.lxdemo.fragment.FixXYFragment;
import com.lx.lxlibrary.adapter.BaseFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建人：李响
 * 创建日期：2016/8/14
 * 描述：
 */
public class ScaleTypeActivity extends BaseActivity {
    private TabLayout tabLayput;
    private ViewPager viewpager;

    @Override
    protected void initView(View view) {
        tabLayput = (TabLayout) view.findViewById(R.id.tabLayout);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        List<String> list = new ArrayList<String>();
        list.add("center");     // 不放大缩小图片，图片居中，超过View截取图片居中显示
        list.add("centerDrop");  //drop(修饰，切割)，即按比例放大缩小图片，
        list.add("centerInside");
        list.add("finCenter");  //
        list.add("fixXY");    //不按比例，充满屏幕
        tabLayput.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayput.addTab(tabLayput.newTab().setText(list.get(0)));
        tabLayput.addTab(tabLayput.newTab().setText(list.get(1)));
        tabLayput.addTab(tabLayput.newTab().setText(list.get(2)));
        tabLayput.addTab(tabLayput.newTab().setText(list.get(3)));
        tabLayput.addTab(tabLayput.newTab().setText(list.get(4)));
        CenterFragment centerFragment = new CenterFragment();
        CenterDropFragment centerDropFragment = new CenterDropFragment();
        CenterInsideFragment centerInsideFragment = new CenterInsideFragment();
        FinCenterFragment finCenterFragment = new FinCenterFragment();
        FixXYFragment fixXYFragment = new FixXYFragment();
        List<Fragment> fragmentlist = new ArrayList<>();
        fragmentlist.add(centerFragment);
        fragmentlist.add(centerDropFragment);
        fragmentlist.add(centerInsideFragment);
        fragmentlist.add(finCenterFragment);
        fragmentlist.add(fixXYFragment);
        FragmentManager fm = getSupportFragmentManager();
        BaseFragmentPagerAdapter adapter = new BaseFragmentPagerAdapter(fm);
        adapter.setList(fragmentlist);
        viewpager.setAdapter(adapter);
        adapter.setPageTitle(list);
        tabLayput.setupWithViewPager(viewpager);
    }
    
    

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initValue() {

    }
    

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_scale_type;
    }

    @Override
    public void onClick(View v) {

    }
}
