package com.lx.lxdemo.activity;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.TextView;

import com.lx.lxdemo.R;
import com.lx.lxdemo.adapter.BannerAdapter;
import com.lx.lxdemo.adapter.ViewFlipperAdapter;
import com.lx.lxdemo.bean.BannerBean;
import com.lx.lxlibrary.activity.BaseActivity;
import com.lx.lxlibrary.adapter.AbsBaseAdapter;
import com.lx.lxlibrary.utlis.ToastUtils;
import com.lx.lxlibrary.view.AdapterViewVerticalBanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 李响
 * 创建日期 2017/1/4
 * 描述：各种轮播跑马灯控件，其中包括安卓控件ViewFlipper （视图翻转）,AdapterViewFlipper（适配的视图翻转）
 */
public class MarqueeViewActivity extends BaseActivity {
    private TextView tv_left;
    private AdapterViewFlipper adapterViewFlipper;
    private ViewFlipperAdapter viewFlipperAdapter;
    private Button btn_next;
    private Button btn_perious;
    private AdapterViewVerticalBanner adapterViewVerticalBanner;

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_marqueenview;
    }

    @Override
    protected void initView(View view) {
        tv_left = (TextView) view.findViewById(R.id.tv_left);
        adapterViewFlipper = (AdapterViewFlipper) view.findViewById(R.id.adapterViewFlipper);
        btn_next = (Button) view.findViewById(R.id.btn_next);
        btn_perious = (Button) view.findViewById(R.id.btn_perious);
        adapterViewVerticalBanner = (AdapterViewVerticalBanner) view.findViewById(R.id.adapterViewVerticalBanner);
        List<BannerBean> demoList = new ArrayList<BannerBean>();
        BannerBean bannerBean1 = new BannerBean();
        bannerBean1.setMark("小米");
        bannerBean1.setContent("小米MAX要卖疯了！");

        BannerBean bannerBean2 = new BannerBean();
        bannerBean2.setMark("APPLE");
        bannerBean2.setContent("全新iphone7,你的7代！");

        BannerBean bannerBean3 = new BannerBean();
        bannerBean3.setMark("小米");
        bannerBean3.setContent("小米MAX要卖疯了！");

        BannerBean bannerBean4 = new BannerBean();
        bannerBean4.setMark("多肉");
        bannerBean4.setContent("多省包邮！！！");

        BannerBean bannerBean5 = new BannerBean();
        bannerBean5.setMark("面膜神器");
        bannerBean5.setContent("你要的面膜都在这里！");
        demoList.add(bannerBean1);
        demoList.add(bannerBean2);
        demoList.add(bannerBean3);
        demoList.add(bannerBean4);
        demoList.add(bannerBean5);
        BannerAdapter bannerAdapter = new BannerAdapter(demoList);
        adapterViewVerticalBanner.setAdapter(bannerAdapter);
        adapterViewVerticalBanner.start();

//        ObjectAnimator translationUp = ObjectAnimator.ofFloat(adapterViewFlipper, "Y",
//                0, adapterViewFlipper.getY());
//        translationUp.setInterpolator(new LinearInterpolator());
//        translationUp.setDuration(1500);
//
//        ObjectAnimator translationDown = ObjectAnimator.ofFloat(adapterViewFlipper, "Y",
//                -adapterViewFlipper.getY(), 0);
//        translationUp.setInterpolator(new LinearInterpolator());
//        translationUp.setDuration(1500);

        ObjectAnimator animator = (ObjectAnimator) AnimatorInflater.loadAnimator(context, R.animator.marquee_adapterviewflipper_in);
        ObjectAnimator animatorOUT = (ObjectAnimator) AnimatorInflater.loadAnimator(context, R.animator.marquee_adapterviewflipper_out);
        adapterViewFlipper.setInAnimation(animator);
        adapterViewFlipper.setOutAnimation(animatorOUT);
//        adapterViewFlipper.setInAnimation(context, R.anim.marquee_out);
        viewFlipperAdapter = new ViewFlipperAdapter(context);
        viewFlipperAdapter.setonItemClickListener(new AbsBaseAdapter.OnItemClickListener<String>() {
            @Override
            public void itemClick(String o, View v, int position) {
                ToastUtils.shortShow(position + o);
            }
        });
    }

    @Override
    protected void initEvent() {
        btn_perious.setOnClickListener(this);
        btn_next.setOnClickListener(this);

    }

    @Override
    protected void initValue() {
        setTitle(R.string.all_kinds_of_wheel_and_entertaining_diversions_controls);
        tv_left.setText(R.string.underline_the_sentence_underlined_sentence2s);
        viewFlipperAdapter.setList(Arrays.asList(getResources().getStringArray(R.array.demo_list)));
        adapterViewFlipper.setAdapter(viewFlipperAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                adapterViewFlipper.setInAnimation(context, R.animator.marquee_adapterviewflipper_in);
                adapterViewFlipper.setOutAnimation(context, R.animator.marquee_adapterviewflipper_out);
                adapterViewFlipper.showNext();
                break;
            case R.id.btn_perious:
                adapterViewFlipper.setInAnimation(context, R.animator.marquee_adapterviewflipper_in);
                adapterViewFlipper.setOutAnimation(context, R.animator.marquee_adapterviewflipper_out);
                adapterViewFlipper.showPrevious();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapterViewVerticalBanner.stop();
    }
}
