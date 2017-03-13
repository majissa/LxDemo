package com.lx.lxlibrary.common;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.lx.lxlibrary.log.Logger;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cookie.store.PersistentCookieStore;
import com.lzy.okhttputils.model.HttpHeaders;
import com.lzy.okhttputils.model.HttpParams;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * 创建人：李响
 * 创建日期：2016/8/14
 * 描述：
 */
public abstract class AbsAppaction extends Application implements Application.ActivityLifecycleCallbacks {

    private static Context context = null;
    private static final String TAG = "AbsAppaction";
    private static AbsAppaction appaction;

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);
        appaction = getInstance();
        context = this.getApplicationContext();
        initImageLoader();
        initOkhttp();
    }

    public static AbsAppaction getAppaction() {
        return appaction;
    }


    public static Context getContext() {
        return context;
    }


    public abstract AbsAppaction getInstance();


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Logger.d(TAG, activity.getClass().getSimpleName() + "onActivityCreated");
    }


    /**
     * 1.程序一开始启动，2，程序从后台转到前台
     *
     * @param activity
     */
    @Override
    public void onActivityStarted(Activity activity) {
        Logger.d(TAG, activity.getClass().getSimpleName() + "onActivityStarted");
    }


    /**
     * 1.程序一开始启动  2.程序被其他Activity取消覆盖后或者解锁屏的时候  3.后台转到前台时候
     */
    @Override
    public void onActivityResumed(Activity activity) {
        Logger.d(TAG, activity.getClass().getSimpleName() + "onActivityResumed");
    }


    /**
     * 1.Acitvity被其他Activity覆盖时 2.程序被其他Activity覆盖后或者锁屏的时候
     *
     * @param activity
     */
    @Override
    public void onActivityPaused(Activity activity) {
        Logger.d(TAG, activity.getClass().getSimpleName() + "onActivityPaused");
    }


    /**
     * 1.挑战其他Activity 2.程序被其他Activity覆盖后或者锁屏的时候  3.前台转到后台时候
     * 注意：跳转其他Activity时，是先执行跳转的Activity的onCreate()，Onstart(),onResume()，后执行该onStop();
     *
     * @param activity
     */
    @Override
    public void onActivityStopped(Activity activity) {
        Logger.d(TAG, activity.getClass().getSimpleName() + "onActivityStopped");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        //Logger.d(TAG,activity.getClass().getSimpleName()+"onActivityCreated");
    }


    /**
     * 1.回退键的时候  2.finish()的时候  3.  内存不足时，摧毁该Activity
     *
     * @param activity
     */
    @Override
    public void onActivityDestroyed(Activity activity) {
        Logger.d(TAG, activity.getClass().getSimpleName() + "onActivityDestroyed");
    }

    private void initImageLoader() {

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
                .diskCacheExtraOptions(480, 800, null)
//                        .taskExecutor(...)
//                        .taskExecutorForCachedImages(...)
//                        .threadPoolSize(3) // default
                .threadPriority(Thread.NORM_PRIORITY - 1) // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13) // default
//                        .diskCache(new UnlimitedDiscCache(cacheDir)) // default
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
//                        .imageDownloader(new BaseImageDownloader(context)) // default
//                        .imageDecoder(new BaseImageDecoder()) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs()
                .build();

        //ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).build();
        ImageLoader.getInstance().init(config);

        //        DisplayImageOptions options = new DisplayImageOptions.Builder()
        //                .showImageOnLoading(R.drawable.ic_stub) // resource or drawable
        //                .showImageForEmptyUri(R.drawable.ic_empty) // resource or drawable
        //                .showImageOnFail(R.drawable.ic_error) // resource or drawable
        //                .resetViewBeforeLoading(false)  // default
        //                .delayBeforeLoading(1000)
        //                .cacheInMemory(false) // default
        //                .cacheOnDisk(false) // default
        //                .preProcessor(...)
        //                .postProcessor(...)
        //                .extraForDownloader(...)
        //                .considerExifParams(false) // default
        //                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
        //                .bitmapConfig(Bitmap.Config.ARGB_8888) // default
        //                .decodingOptions(...)
        //                .displayer(new SimpleBitmapDisplayer()) // default
        //                .handler(new Handler()) // default
        //                .build();

    }

    private void initOkhttp() {
        HttpHeaders headers = new HttpHeaders();
//        headers.put("commonHeaderKey1", "commonHeaderValue1");    //所有的 header 都 不支持 中文
//        headers.put("commonHeaderKey2", "commonHeaderValue2");
//        HttpParams params = new HttpParams();
//        params.put("commonParamsKey1", "commonParamsValue1");     //所有的 params 都 支持 中文
//        params.put("commonParamsKey2", "这里支持中文参数");

        //必须调用初始化
        OkHttpUtils.init(this);
        //以下都不是必须的，根据需要自行选择
        OkHttpUtils.getInstance()//
                .debug("OkHttpUtils")                                              //是否打开调试
                .setConnectTimeout(OkHttpUtils.DEFAULT_MILLISECONDS)               //全局的连接超时时间
                .setReadTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)                  //全局的读取超时时间
                .setWriteTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)                 //全局的写入超时时间
//                .setCookieStore(new MemoryCookieStore())                           //cookie使用内存缓存（app退出后，cookie消失）
                .setCookieStore(new PersistentCookieStore());                       //cookie持久化存储，如果cookie不过期，则一直有效
//                .addCommonHeaders(headers)                                         //设置全局公共头
//                .addCommonParams(params);                                          //设置全局公共参数
    }


}
