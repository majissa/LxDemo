package com.lx.lxlibrary.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建人：LX
 * 创建日期：2016/8/25
 * 描述：
 */
public class BaseViewpagerAdapter<T extends View> extends PagerAdapter {

    private List<T> list = new ArrayList<T>();

    public BaseViewpagerAdapter() {

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public List<T> getList() {
        return list;
    }


    public void setList(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
