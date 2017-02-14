package com.lx.lxlibrary.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lx.lxlibrary.log.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建人：LX
 * 创建日期：2016/7/26
 * 描述：
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int oncreateViewHolder;
    private int onbindViewHolder;
    private List<T> list = new ArrayList<T>();
    protected Context context;
    protected View.OnClickListener onClickListener;
    private ItemOnClickListener itemOnClickListener;

    public BaseRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    /**
     * Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.
     * <p/>
     * This new ViewHolder should be constructed with a new View that can represent the items of the given type.
     * You can either create a new View manually or inflate it from an XML layout file.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return
     */


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        oncreateViewHolder++;
        Logger.dLi("onCreateViewHolder:" + oncreateViewHolder);
        return onCreateItemViewHolder(parent);
    }

    protected abstract RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent);

    /**
     * Called by RecyclerView to display the data at the specified position.
     * This method should update the contents of the itemView to reflect the item at the given position.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        onbindViewHolder++;
        Logger.dLi("onBindViewHolder:" + onbindViewHolder);
        if (holder != null) {
            if (holder instanceof RecyclerView.ViewHolder) {
                Logger.dLi("holder的实例是RecyclerView.ViewHolder");
            } else if (holder instanceof BaseRecyclerViewAdapter.BaseitemViewHolder) {
                Logger.dLi("holder的实例是BaseitemViewHolder");
            }

            final BaseitemViewHolder baseholder = (BaseitemViewHolder) holder;
            baseholder.initView();
            baseholder.initEvent(position, onClickListener);
            final T t = list.get(position);
            baseholder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemOnClickListener != null) {
                        itemOnClickListener.onItemClick(t,baseholder.itemView, position);
                    }
                }
            });
            baseholder.initValue(t, position);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    protected View Inflate(@LayoutRes int layoutRes, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(layoutRes, parent, false);

    }


    public abstract class BaseitemViewHolder extends RecyclerView.ViewHolder {

        public BaseitemViewHolder(View itemView) {
            super(itemView);
            // itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        protected abstract void initValue(T value, int position);

        protected abstract ArrayList<View> bindListenerViews(ArrayList<View> list);

        protected abstract void initView();

        public void initEvent(int position, View.OnClickListener onClickListener) {
            ArrayList<View> bindListenerViews = new ArrayList<View>();
            bindListenerViews = bindListenerViews(bindListenerViews);
            if (bindListenerViews != null && bindListenerViews.size() > 0) {//没有需要监听的控件
                for (View bindListenerView : bindListenerViews) {
                    bindListenerView.setTag(bindListenerView.getId(), position);
                    bindListenerView.setOnClickListener(onClickListener);
                }
            }
        }
    }


    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnItemClickListener(ItemOnClickListener onItemClickListener) {
        this.itemOnClickListener = onItemClickListener;
    }

    public interface ItemOnClickListener<T> {
        public void onItemClick(T t,View view, int position);
    }
}
