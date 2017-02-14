package com.lx.lxdemo.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 公司：福建桔子信息科技有限公司
 * 创建日期：2016/5/3
 * 描述：
 */
public class ViewHolder implements View.OnClickListener{
    private final SparseArray<View> views;
    private View mconventView;
    private onViewHolderListener listener;


    private ViewHolder(Context context, int itemlayoutid, ViewGroup parent, int position) {
        views = new SparseArray<View>();
        mconventView = LayoutInflater.from(context).inflate(itemlayoutid,parent,false);
        mconventView.setTag(this);
    }


    public static ViewHolder getViewHolder(View convertView, Context context, int itemlayoutid, ViewGroup parent, int position) {
        if (convertView == null) {
            return new ViewHolder(context, itemlayoutid, parent, position);
        } else {
            return (ViewHolder) convertView.getTag();
        }

    }


    /**
     * 通过控件id找到View
     * @param viewid
     * @param <T>
     * @return
     */


    public <T extends View> View getView(int viewid) {
        View view = this.views.get(viewid);
        if (view == null) {
            view = mconventView.findViewById(viewid);
            views.put(viewid, view);
        }
        return (T) view;


    }

    public View getConvertView() {
        return this.mconventView;
    }


    public void setonClickListener(View view,View.OnClickListener listener)
    {
        if(view!=null)
        {
            view.setOnClickListener(listener);
        }
    }

    public void setViewHolderOnClickListener(View view)
    {
        view.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        listener.onClick(v);
    }


    public interface onViewHolderListener
    {
       void onClick(View v);
    }
}
