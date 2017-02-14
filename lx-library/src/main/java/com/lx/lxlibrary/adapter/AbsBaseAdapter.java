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
 * 描述：抽象baseAdapter
 */
public abstract class AbsBaseAdapter<T> extends BaseAdapter {

    protected Context context;
    protected List<T> datas = new ArrayList<T>();
    protected View.OnClickListener onClickListener;
    private OnItemClickListener<T> onItemClickListener;

    public AbsBaseAdapter(Context context) {
        this.context = context;
    }

    public AbsBaseAdapter(Context context, List<T> datas) {
        this.context = context;
        this.datas = datas;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        AbsViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = onCreateViewHolder(parent);
            convertView = findViewByLayout();
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (AbsViewHolder) convertView.getTag();
        }

        if (datas.size() > 0) {
            viewHolder.findView(convertView);
            viewHolder.initData(datas.get(position));
            if (onClickListener != null) {
                viewHolder.setListener(position, onClickListener);
            }
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.itemClick(datas.get(position), v, position);
                }
            });
        }
        return convertView;
    }


    protected abstract View findViewByLayout();

    public abstract AbsViewHolder onCreateViewHolder(ViewGroup parent);

    public void setonClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setonItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public abstract class AbsViewHolder {

        public abstract void findView(View view);

        public abstract void setListener(int positon, View.OnClickListener onClickListener);

        public abstract void initData(T item);


        /**
         * 通过控件id找到View
         *
         * @param viewid
         * @param <T>
         * @return
         */


      /*  public <T extends View> View getView(int viewid) {
            View view = this.views.get(viewid);
            if (view == null) {
                view = mconventView.findViewById(viewid);
                views.put(viewid, view);
            }
            return (T) view;


        }*/

       /* public View getConvertView() {
            return this.mconventView;
        }
*/

    }


    public List<T> getList() {
        return datas;
    }

    public void setList(List<T> datas) {
        this.datas = datas;
        // notifyDataSetChanged();
    }

    /**
     * 添加集合lIST
     *
     * @param datas
     */
    public void addAllList(List<T> datas) {
        if (this.datas != null) {
            this.datas.addAll(datas);
            notifyDataSetChanged();
        }

    }

    /**
     * 移除集合
     *
     * @param datas
     * @return
     */
    public boolean removeAllList(List<T> datas) {
        if (this.datas != null && this.datas.size() > 0 && this.datas.containsAll(datas)) {
            if (this.datas.removeAll(datas)) {
                notifyDataSetChanged();
                return true;
            } else
                return false;

        } else {
            return false;
        }
    }

    public interface OnItemClickListener<T> {
        public void itemClick(T t, View v, int position);
    }
}
