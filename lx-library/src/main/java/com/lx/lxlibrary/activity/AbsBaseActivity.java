package com.lx.lxlibrary.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.lx.lxlibrary.R;
import com.lx.lxlibrary.interfaces.IGlobalReceiver;
import com.lx.lxlibrary.utlis.ImageUtil;
import com.lzy.okhttputils.OkHttpUtils;

import java.util.ArrayList;


/**
 * 创建人：LX
 * 创建日期：2016/6/27
 * 描述：基类Activity 如果使用AppCompatActivity,使用 requestWindowFeature(Window.FEATURE_NO_TITLE);没有效果，
 * 如果使用FragmentActivity,getFragmentManager会报错，具体原因还需了解
 * todo  了解AppCompatActivity
 */
public abstract class AbsBaseActivity extends AppCompatActivity implements View.OnClickListener, IGlobalReceiver {

    private ViewGroup viewGroup;  //根控件
    protected View titleView;
    protected Context context;
    protected Context applicationContext;
    private FrameLayout titleBarWrapperFrameLayout;//标题栏的包装布局
    private FrameLayout wrapperFrameLayout;//内容布局
    private View wrapperView;
    private FrameLayout.LayoutParams wrapperParams;
    protected Bundle saveInstanceState;

    /**
     * UI布局如果填写在这个函数当中是不显示布局的。应写在oncreate()方法中
     *
     * @param savedInstanceState
     * @param persistentState
     */

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.saveInstanceState = savedInstanceState;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // getWindow().setFlags(WindowManager.LayoutParams.TYPE_STATUS_BAR, WindowManager.LayoutParams.TYPE_STATUS_BAR);
        setContentView(R.layout.activity_base);
        wrapperFrameLayout = (FrameLayout) findViewById(R.id.fl_base);
        wrapperParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        wrapperParams.topMargin = getTitleBarHeight();
        wrapperFrameLayout.setLayoutParams(wrapperParams);
        wrapperView = LayoutInflater.from(this).inflate(inflateLayoutId(), null);
        wrapperFrameLayout.addView(wrapperView);
        init();
        initTitle();
        initView(getWindow().getDecorView());
        initEvent();
        initValue();
    }

    /**
     * 初始化标题
     */
    protected void initTitle() {
        viewGroup = (ViewGroup) findViewById(android.R.id.content);
        titleBarWrapperFrameLayout = new FrameLayout(context);
        titleView = LayoutInflater.from(context).inflate(inflateTitleBarLayoutId(), titleBarWrapperFrameLayout, true);
        viewGroup.addView(titleBarWrapperFrameLayout);
        initTiTleBarViews(titleView);
        titleView.setVisibility(View.VISIBLE);
//        titleBarWrapperFrameLayout.setVisibility(View.VISIBLE);
    }

    private void init() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//全部竖屏，防止横竖屏切换
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//不自动弹出软键盘
        applicationContext = getApplicationContext();
        context = this;
    }

    public void setTopGone() {
        if (titleView != null) {
            if (titleView.getVisibility() == View.VISIBLE) {
                titleView.setVisibility(View.GONE);
                wrapperParams.topMargin = 0;
                wrapperFrameLayout.setLayoutParams(wrapperParams);
            }
        }
    }

    public void setTopVisibility() {
        if (titleView != null) {
            if (titleView.getVisibility() == View.GONE) {
                titleView.setVisibility(View.VISIBLE);
                wrapperParams.topMargin = getTitleBarHeight();
                wrapperFrameLayout.setLayoutParams(wrapperParams);
            }
        }
    }

    /**
     * 标题的高度
     *
     * @return
     */
    protected abstract int getTitleBarHeight();

    /**
     * 标题布局
     *
     * @return
     */
    protected abstract int inflateTitleBarLayoutId();

    /**
     * 主要内容布局
     *
     * @return
     */
    protected abstract int inflateLayoutId();

    /**
     * 标题的控件初始化
     *
     * @param titleView
     */
    protected abstract void initTiTleBarViews(View titleView);

    /**
     * 主要控件初始化
     *
     * @param view
     */
    protected abstract void initView(View view);

    protected abstract void initEvent();

    protected abstract void initValue();


    /**
     * 普通跳转activity
     *
     * @param cls
     */
    public void jumpToActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }

    /**
     * 跳转activity传递Bundle
     *
     * @param cls
     * @param bundle
     */
    public void jumpToActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);
        startActivity(new Intent(this, cls));
    }

    /**
     * 跳转到Activity并且处理完后返回
     *
     * @param cls
     * @param bundle
     * @param requestCode 请求码
     */
    public void jumpToActivityAndGoBack(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 跳转PhotoViewActivity，加载多张网络或本地图片
     *
     * @param imagePath         图片地址集合,网络和本地的都可以
     * @param defaultDrawableId 默认显示的本地图片id
     */
    public void jumpToPhotoViewActivity(String imagePath, int defaultDrawableId) {
        ArrayList<String> list = new ArrayList<>();
        list.add(imagePath);
        jumpToPhotoViewActivity(list, defaultDrawableId, 0);
    }

    /**
     * 跳转PhotoViewActivity，加载多张网络或本地图片
     *
     * @param imagePathList     图片地址集合,网络和本地的都可以
     * @param defaultDrawableId 默认显示的本地图片id
     * @param currentPosition   默认显示第几张图
     */
    @Deprecated
    public void jumpToPhotoViewActivity(ArrayList<String> imagePathList, int defaultDrawableId, int currentPosition) {
        Intent intent = new Intent(this, PhotoViewActivity.class);
        intent.putStringArrayListExtra(PhotoViewActivity.IMAGE_PATH_LIST, imagePathList);
        intent.putExtra(PhotoViewActivity.DEFAULT_DRAWABLE_ID, defaultDrawableId);
        intent.putExtra(PhotoViewActivity.CURRENT_POSITION, currentPosition);
        startActivity(intent);
    }

    /**
     * 跳转PhotoViewActivity，加载多张网络或本地图片
     *
     * @param imagePathList     图片地址集合,网络和本地的都可以
     * @param defaultDrawableId 默认显示的本地图片id
     * @param currentPosition   默认显示第几张图
     */
    public void jumpToPhotoViewActivity(ImageView imageView, ArrayList<String> imagePathList, int defaultDrawableId, int currentPosition) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Intent intent = new Intent(context, PhotoViewActivity.class);
            ActivityOptionsCompat options = ActivityOptionsCompat
                    .makeSceneTransitionAnimation((Activity) context, imageView, "image");

            intent.putStringArrayListExtra(PhotoViewActivity.IMAGE_PATH_LIST, imagePathList);
            intent.putExtra(PhotoViewActivity.DEFAULT_DRAWABLE_ID, defaultDrawableId);
            intent.putExtra(PhotoViewActivity.CURRENT_POSITION, currentPosition);
            startActivity(intent, options.toBundle());
        } else {
            int[] location = new int[2];
            imageView.getLocationOnScreen(location);

            Intent intent = new Intent(this, PhotoViewActivity.class);
            intent.putExtra("left", location[0]);
            intent.putExtra("top", location[1]);
            intent.putExtra("width", imageView.getWidth());
            intent.putExtra("height", imageView.getHeight());
            intent.putStringArrayListExtra(PhotoViewActivity.IMAGE_PATH_LIST, imagePathList);
            intent.putExtra(PhotoViewActivity.DEFAULT_DRAWABLE_ID, defaultDrawableId);
            intent.putExtra(PhotoViewActivity.CURRENT_POSITION, currentPosition);
            startActivity(intent);
            overridePendingTransition(0, 0);
        }
    }

    /**
     * 跳转到PhotoViewActivity，加载单张本地资源图片
     *
     * @param anchorImageView
     */
    public void jumpToPhotoViewActivity(ImageView anchorImageView) {
        Intent intent = new Intent(this, PhotoViewActivity.class);
        intent.putExtra(PhotoViewActivity.BITMAP, ImageUtil.drawableToBitmap(anchorImageView.getDrawable()));
        startActivity(intent);
    }

    public void jumpToDragPhotoViewActivity(ImageView imageView, String imagePath, @IdRes int resId) {
        ArrayList<String> list = new ArrayList<>();
        list.add(imagePath);
        jumpToDragPhotoViewActivity(imageView, list, 0, resId);
    }


    public void jumpToDragPhotoViewActivity(ImageView imageView, ArrayList<String> imagePaths, int posiiton, @IdRes int resId) {
        Intent intent = new Intent(context, DragPhotoActivity.class);
        int location[] = new int[2];

        imageView.getLocationOnScreen(location);
        intent.putExtra("left", location[0]);
        intent.putExtra("top", location[1]);
        intent.putExtra("height", imageView.getHeight());
        intent.putExtra("width", imageView.getWidth());
        intent.putStringArrayListExtra(DragPhotoActivity.IMAGE_PATH, imagePaths);
        intent.putExtra(DragPhotoActivity.DEFAULT_RESID, resId);
        intent.putExtra(DragPhotoActivity.POSITION, posiiton);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    @Override
    public void registerGlobleReceiver() {

    }

    @Override
    public void unRegisterGlobleReceiver() {

    }

    @Override
    public void sendCommonBroadcast(String identifyCode) {

    }

    @Override
    public void onCommonBroadcastReceive(Intent intent, String identifyCode) {

    }

    @Override
    public void sendLoginBroadcast() {

    }

    @Override
    public void sendLogoutBroadcast() {

    }

    @Override
    public void onLogin(Intent intent) {

    }

    @Override
    public void onLogout(Intent intent) {

    }

    @Override
    public void onMessageReceived(String title, String message, String extras, String contentType) {

    }

    @Override
    public void onNotificationReceived(String title, String content, String extras, String contentType) {

    }

    @Override
    public void onNotificationOpened(String title, String content, String extras) {

    }

   /* *//**
     * 跳转到WebViewActivity
     * @param url 网页地址
     *//*
    public void jumpToWebViewActivity(String url) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra(WebViewActivity.URL, url);
        startActivity(intent);
    }*/

   /* */

    /**
     * 跳转到PhotoViewActivity
     *
     * @param anchorImageView
     *//*
    public void jumpToPhotoViewActivity(ImageView anchorImageView) {
        Intent intent = new Intent(this, PhotoViewActivity.class);
        intent.putExtra("bitmap", ImageUtils.drawableToBitmap(anchorImageView.getDrawable()));
        startActivity(intent);
    }*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(this);
    }
}
