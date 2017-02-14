package com.lx.lxdemo.adapter;

import android.view.View;
import android.widget.TextView;

import com.lx.lxdemo.R;
import com.lx.lxdemo.bean.BannerBean;
import com.lx.lxlibrary.adapter.BaseBannerAdapter;
import com.lx.lxlibrary.view.AdapterViewVerticalBanner;

import java.util.List;

/**
 * Created by 李响
 * 创建日期 2017/1/18
 * 描述：
 */
public class BannerAdapter extends BaseBannerAdapter<BannerBean> {

    public BannerAdapter(List datas) {
        super(datas);
    }

    @Override
    public View getView(AdapterViewVerticalBanner parent) {
        return inflate(parent.getContext(), R.layout.adapter_banner);
    }

    @Override
    public void setItem(View view, BannerBean data) {
        TextView tv_mark = (TextView) view.findViewById(R.id.tv_mark);
        TextView tv_content = (TextView) view.findViewById(R.id.tv_content);
        tv_mark.setText(data.getMark());
        tv_content.setText(data.getContent());
    }
}
