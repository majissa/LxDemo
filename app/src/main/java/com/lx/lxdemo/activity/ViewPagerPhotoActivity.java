package com.lx.lxdemo.activity;


import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lx.lxdemo.R;
import com.lx.lxlibrary.adapter.BaseViewpagerAdapter;
import com.lx.lxlibrary.activity.BaseActivity;
import com.lx.lxlibrary.utlis.ImageUtil;
import com.lx.lxlibrary.view.HackyViewPager;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;

/**
 * 创建人：LX
 * 创建日期：2016/8/25
 * 描述：
 */
public class ViewPagerPhotoActivity extends BaseActivity {
    private HackyViewPager viewpager;
    private final static String IMAGE_PATH_LIST = "image_path_list";
    private BaseViewpagerAdapter<PhotoView> adapter;
    private TextView tv_tip_number;

    @Override
    protected void initView(View view) {
        tv_tip_number = (TextView) findViewById(R.id.tv_tip_number);
        viewpager = (HackyViewPager) findViewById(R.id.viewpager);
        adapter = new BaseViewpagerAdapter<PhotoView>();
        viewpager.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initValue() {
        setTopGone();
        ArrayList<String> imagelist = getIntent().getStringArrayListExtra(IMAGE_PATH_LIST);
        final ArrayList<String> list = new ArrayList<>();
        list.add("http://h.hiphotos.baidu.com/zhidao/pic/item/6d81800a19d8bc3ed69473cb848ba61ea8d34516.jpg");
        list.add("http://img.taopic.com/uploads/allimg/121209/234928-12120Z0543764.jpg");
        list.add("http://img4.duitang.com/uploads/item/201603/26/20160326193535_dj8cx.jpeg");
        list.add("http://img3.fengniao.com/album/upload/2/287/57227/11445370_600.jpg");
        list.add("http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1209/11/c1/13785072_1347334520032.jpg");
        ArrayList<PhotoView> photoViews = new ArrayList<>();
        if (list.size() > 0 && list != null) {
            tv_tip_number.setText("1/" + list.size());
            for (String s : list) {
                PhotoView photoView = new PhotoView(context);
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                photoView.setLayoutParams(layoutParams);
                photoView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                ImageLoader.getInstance().displayImage(s, photoView, ImageUtil.getDisplayImageOptions());
                photoViews.add(photoView);
            }
        }
        adapter.setList(photoViews);
        adapter.notifyDataSetChanged();
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_tip_number.setText(position + 1 + "/" + list.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected int inflateLayoutId() {
        return R.layout.photoview;
    }

    @Override
    public void onClick(View v) {

    }
}
