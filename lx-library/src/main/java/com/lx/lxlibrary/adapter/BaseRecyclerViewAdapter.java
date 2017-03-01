package com.lx.lxlibrary.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

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
    private FrameLayout headFrameLayout;
    private FrameLayout footFrameLayout;

    public FrameLayout getHeadFrameLayout() {
        return headFrameLayout;
    }

    public BaseRecyclerViewAdapter(Context context) {
        this.context = context;
        init();
    }

    //如果第三参数为true,以headFrameLayout为根布局，如果为false的话则以xml的文件的根布局
    public View inflateHeadView(@LayoutRes int headViewRes) {
        View headView = LayoutInflater.from(context).inflate(headViewRes, headFrameLayout, true);
        headFrameLayout.addView(headView);
        return headView;
    }

    public View inflateFootView(@LayoutRes int footViewRes) {
        View footView = LayoutInflater.from(context).inflate(footViewRes, footFrameLayout, true);
        footFrameLayout.addView(footView);
        return footView;
    }

    private void init() {
        headFrameLayout = new FrameLayout(context);
        headFrameLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
        footFrameLayout = new FrameLayout(context);
        footFrameLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT));
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
        if (viewType == ItemType.HEAD) {
            return new HeadViewHolder(headFrameLayout);
        } else if (viewType == ItemType.FOOT) {
            return new FootViewHolder(footFrameLayout);
        } else if (viewType == ItemType.ITEM) {
            return onCreateItemViewHolder(parent);
        } else {
            return null;
        }

    }

    protected abstract RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent);

    /**
     * Called by RecyclerView to display the data at the specified position.
     * This method should update the contents of the itemView to reflect the item at the given position.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     *                 用于绑定数据，刷新数据使用
     */


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (holder == null) {
            return;
        }
        if (itemViewType == ItemType.ITEM) {
            position = position - 1;//减去头部
            final BaseitemViewHolder baseholder = (BaseitemViewHolder) holder;
            baseholder.initView();
            baseholder.initEvent(position, onClickListener);
            final T t = list.get(position);
            final int finalPosition = position;
            baseholder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemOnClickListener != null) {
                        itemOnClickListener.onItemClick(t, baseholder.itemView, finalPosition);
                    }
                }
            });
            baseholder.initValue(t, position);
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 2;//默认有个头布局和尾布局
    }

    protected View Inflate(@LayoutRes int layoutRes, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(layoutRes, parent, false);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ItemType.HEAD;
        } else if (position == getItemCount() - 1) {
            //尾布局
            return ItemType.FOOT;
        } else {
            return ItemType.ITEM;
        }
    }

    private class HeadViewHolder extends RecyclerView.ViewHolder {
        public HeadViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class FootViewHolder extends RecyclerView.ViewHolder {
        public FootViewHolder(View itemView) {
            super(itemView);
        }
    }

    public abstract class BaseitemViewHolder extends RecyclerView.ViewHolder {

        public BaseitemViewHolder(View itemView) {
            super(itemView);
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

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) layoutManager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (getItemViewType(position) == ItemType.FOOT || getItemViewType(position) == ItemType.HEAD) {
                        return gridManager.getSpanCount();
                    } else {
                        return 1;
                    }
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams != null
                && layoutParams instanceof StaggeredGridLayoutManager.LayoutParams
                && holder.getLayoutPosition() == 0) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) layoutParams;
            p.setFullSpan(true);//头部占满列
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
        public void onItemClick(T t, View view, int position);
    }

    public class ItemType {
        public static final int HEAD = 0;
        public static final int FOOT = 1;
        public static final int ITEM = 2;
    }
}
