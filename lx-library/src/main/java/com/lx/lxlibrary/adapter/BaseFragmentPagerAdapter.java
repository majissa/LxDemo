package com.lx.lxlibrary.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;



/**
 * 创建人：LX
 * 创建日期：2016/7/4
 * 描述：
 */
public class BaseFragmentPagerAdapter<T extends Fragment> extends FragmentPagerAdapter {

    protected List<T> list = new ArrayList<T>();
    protected List<String> titleList;

    public BaseFragmentPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    public BaseFragmentPagerAdapter(FragmentManager fm, List<T> list) {
        super(fm);
        this.list = list.size() > 0 ? list : this.list;
    }

    @Override
    public Fragment getItem(int position) {
        return (Fragment) list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }


    public void setList(List list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        if (titleList != null) {
            return titleList.get(position).toString();
        } else {
            return super.getPageTitle(position);
        }

    }

    public void setPageTitle(List<String> titlelist) {
        this.titleList = titlelist;
    }


}
