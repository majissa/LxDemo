package com.lx.lxlibrary.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 公司：福建桔子信息科技有限公司
 * 创建日期：2016/5/3
 * 描述：
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    private Context context;
    private List<T> datas = new ArrayList<T>();
    private int itemlayoutid;
    private ViewHolder viewholder;
    private getPositonListener listener;

    public CommonAdapter(Context context, int itemlayoutid) {
        this.context = context;
        this.itemlayoutid = itemlayoutid;

    }

    public CommonAdapter(Context context, List<T> datas, int itemlayoutid) {
        this.context = context;
        this.datas = datas;
        this.itemlayoutid = itemlayoutid;

    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public T getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewholder = ViewHolder.getViewHolder(convertView, context, itemlayoutid, parent, position);
        convert(viewholder, getItem(position), position);
        return viewholder.getConvertView();
    }

    public abstract void convert(ViewHolder viewholder, T item, int position);

    private interface getPositonListener {
        public void getPosition(int position);
    }

    public void setPosition(getPositonListener listener) {
        this.listener = listener;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}
