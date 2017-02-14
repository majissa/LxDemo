//package com.lx.lxdemo.base;
//
//import android.app.ActionBar;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.ActivityInfo;
//import android.os.Bundle;
//import android.os.PersistableBundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.lx.lxdemo.R;
//
///**
// * 创建人：LX
// * 创建日期：2016/6/27
// * 描述：基类Activity 如果使用AppCompatActivity,使用 requestWindowFeature(Window.FEATURE_NO_TITLE);没有效果，
// * 如果使用FragmentActivity,getFragmentManager会报错，具体原因还需了解
// * todo  了解AppCompatActivity
// */
//public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
//
//    private ViewGroup viewGroup;  //根控件
//    private View titleView;
//    private ImageView iv_right;
//    private TextView tv_Title;
//    protected Context context;
//    protected Context applicationContext;
//
//
//    /**
//     * UI布局如果填写在这个函数当中是不显示布局的。应写在oncreate()方法中
//     *
//     * @param savedInstanceState
//     * @param persistentState
//     */
//
//    @Override
//    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        // getWindow().setFlags(WindowManager.LayoutParams.TYPE_STATUS_BAR, WindowManager.LayoutParams.TYPE_STATUS_BAR);
//        setContentView(inflateBaseLayoutId());
//        init();
//        initTitle();
//        initViews();
//        initEvent();
//        initDatas();
//    }
//
//    /**
//     * 初始化标题
//     */
//    protected void initTitle() {
//        viewGroup = (ViewGroup) findViewById(android.R.id.content);
//        titleView = getLayoutInflater().inflate(R.layout.include_top_title, null);
//        iv_right = (ImageView) titleView.findViewById(R.id.iv_right);
//        iv_right.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//        tv_Title = (TextView) titleView.findViewById(R.id.tv_title);
//        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
//                ActionBar.LayoutParams.MATCH_PARENT, (int) (getResources().getDimension(R.dimen.activity_top_padding)));
//        titleView.setLayoutParams(lp);
//        viewGroup.addView(titleView);
//        titleView.setVisibility(View.GONE);
//
//    }
//
//
//    public void setTopGone() {
//        if (titleView != null) {
//            titleView.setVisibility(View.GONE);
//        }
//    }
//
//    public void setTopVisibility() {
//        if (titleView != null) {
//            titleView.setVisibility(View.VISIBLE);
//        }
//    }
//
//    public void setRightImageViewGone() {
//        if (iv_right != null) {
//            iv_right.setVisibility(View.GONE);
//        }
//    }
//
//    public void setRightImageViewVisibility() {
//        if (iv_right != null) {
//            iv_right.setVisibility(View.VISIBLE);
//        }
//    }
//
//    private void init() {
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//全部竖屏，防止横竖屏切换
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//不自动弹出软键盘
//        applicationContext = getApplicationContext();
//        context = this;
//    }
//
//
//    /**
//     * 设置标题
//     *
//     * @param titleId
//     */
//    @Override
//    public void setTitle(int titleId) {
//        super.setTitle(titleId);
//        tv_Title.setText(titleId);
//    }
//
//    protected abstract void initViews();
//
//    protected abstract void initEvent();
//
//    protected abstract void initDatas();
//
//    protected abstract int inflateBaseLayoutId();
//
//
//    /**
//     * 普通跳转activity
//     *
//     * @param cls
//     */
//    public void jumpToActivity(Class<?> cls) {
//        startActivity(new Intent(this, cls));
//    }
//
//    /**
//     * 跳转activity传递Bundle
//     *
//     * @param cls
//     * @param bundle
//     */
//    public void jumpToActivity(Class<?> cls, Bundle bundle) {
//        Intent intent = new Intent(this, cls);
//        intent.putExtras(bundle);
//        startActivity(new Intent(this, cls));
//    }
//
//    /**
//     * 跳转到Activity并且处理完后返回
//     *
//     * @param cls
//     * @param bundle
//     * @param requestCode 请求码
//     */
//    public void jumpToActivityAndGoBack(Class<?> cls, Bundle bundle, int requestCode) {
//        Intent intent = new Intent(this, cls);
//        intent.putExtras(bundle);
//        startActivityForResult(intent, requestCode);
//    }
//
//   /* *//**
//     * 跳转到WebViewActivity
//     * @param url 网页地址
//     *//*
//    public void jumpToWebViewActivity(String url) {
//        Intent intent = new Intent(this, WebViewActivity.class);
//        intent.putExtra(WebViewActivity.URL, url);
//        startActivity(intent);
//    }*/
//
//   /* *//**
//     * 跳转到PhotoViewActivity
//     * @param anchorImageView
//     *//*
//    public void jumpToPhotoViewActivity(ImageView anchorImageView) {
//        Intent intent = new Intent(this, PhotoViewActivity.class);
//        intent.putExtra("bitmap", ImageUtils.drawableToBitmap(anchorImageView.getDrawable()));
//        startActivity(intent);
//    }*/
//
//
//}
