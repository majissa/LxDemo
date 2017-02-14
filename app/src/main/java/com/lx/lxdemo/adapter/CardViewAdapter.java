package com.lx.lxdemo.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lx.lxdemo.R;
import com.lx.lxlibrary.adapter.BaseRecyclerViewAdapter;

import java.util.ArrayList;

/**
 * Created by 李响
 * 创建日期 2017/1/20
 * 描述：
 */
public class CardViewAdapter extends BaseRecyclerViewAdapter<String> {

    public CardViewAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent) {
        return new CardViewAdapter.TestItemViewHolder(Inflate(R.layout.adapter_cardview, parent));
    }

    class TestItemViewHolder extends BaseitemViewHolder {

        private TextView tv_cardView;
        private CardView cardView;

        public TestItemViewHolder(View itemView) {
            super(itemView);

        }

        @Override
        protected void initValue(String value, int position) {
            tv_cardView.setText(value);
        }

        @Override
        protected ArrayList<View> bindListenerViews(ArrayList<View> list) {
            return null;
        }

        @Override
        protected void initView() {
            cardView = (CardView) itemView.findViewById(R.id.carView);
            tv_cardView = (TextView) itemView.findViewById(R.id.tv_cardView);
        }
    }
}
