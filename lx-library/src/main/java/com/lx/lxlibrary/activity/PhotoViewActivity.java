package com.lx.lxlibrary.activity;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.lx.lxlibrary.R;
import com.lx.lxlibrary.adapter.BaseViewpagerAdapter;
import com.lx.lxlibrary.view.HackyViewPager;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 创建人：LX
 * 创建日期：2016/8/25
 * 描述：
 */
public class PhotoViewActivity extends AppCompatActivity {

    public final static String IMAGE_PATH_LIST = "image_path_list";
    public final static String DEFAULT_DRAWABLE_ID = "default_drawable_id";
    public final static String BITMAP = "bitmap";
    public final static String CURRENT_POSITION = "current_position";
    private List<View> photoViewList = new ArrayList<>();
    private HackyViewPager hackyViewPager;
    private BaseViewpagerAdapter viewPagerAdapter;
    private TextView tv_tip_number;
    private PhotoView photoView;
    private int orignLeft;
    private int orignTop;
    private int orignWidth;
    private int orignHeight;
    private int targetHeight;
    private int targetWidth;
    private int targetLeft;
    private int targetTop;
    private float mscaleX;
    private float mscaleY;
    private float orignCenterX;
    private float orignCenterY;
    private float targetCenterX;
    private float targetCenterY;
    private float mtranslationX;
    private float mtranslationY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        hideStatusBar();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }
        setContentView(R.layout.photoview);
        tv_tip_number = (TextView) findViewById(R.id.tv_tip_number);
        final ArrayList<String> imagePathList = getIntent().getStringArrayListExtra(IMAGE_PATH_LIST);
        int drawableId = getIntent().getIntExtra(DEFAULT_DRAWABLE_ID, 0);
        final int currentPosition = getIntent().getIntExtra(CURRENT_POSITION, 0);
        Bitmap bitmap = getIntent().getParcelableExtra(BITMAP);

        if (imagePathList != null && imagePathList.size() > 0 && drawableId != 0) {//多张图片地址
            tv_tip_number.setText("1/" + imagePathList.size());
            for (String imagePath : imagePathList) {
                photoView = new PhotoView(this);
                photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                    @Override
                    public void onPhotoTap(View view, float x, float y) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            finishAfterTransition();
                        } else {
                            ExitAnimation(photoView);
                        }
                    }
                });
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                photoView.setLayoutParams(layoutParams);
                ImageLoader.getInstance().displayImage(imagePath, photoView);
                photoViewList.add(photoView);
            }
        } else {
            tv_tip_number.setVisibility(View.INVISIBLE);
            photoView = new PhotoView(this);
            photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        finishAfterTransition();
                    } else {
                        ExitAnimation(photoView);
                    }
                }
            });
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            photoView.setLayoutParams(layoutParams);
            photoView.setImageBitmap(bitmap);
            photoViewList.add(photoView);
        }
        hackyViewPager = (HackyViewPager) findViewById(R.id.hackyViewPager);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            hackyViewPager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onGlobalLayout() {
                    hackyViewPager.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    PhotoView pv = (PhotoView) viewPagerAdapter.getList().get(currentPosition);

                    orignLeft = getIntent().getIntExtra("left", 0);
                    orignTop = getIntent().getIntExtra("top", 0);
                    orignWidth = getIntent().getIntExtra("width", 0);
                    orignHeight = getIntent().getIntExtra("height", 0);

                    int[] location = new int[2];

                    pv.getLocationOnScreen(location);

                    targetLeft = location[0];
                    targetTop = location[1];

                    targetHeight = pv.getHeight();
                    targetWidth = pv.getWidth();

                    mscaleX = (float) orignWidth / targetWidth;
                    mscaleY = (float) orignHeight / targetHeight;

                    orignCenterX = orignLeft + orignWidth / 2;
                    orignCenterY = orignTop + orignHeight / 2;

                    targetCenterX = targetLeft + targetWidth / 2;
                    targetCenterY = targetTop + targetHeight / 2;

                    mtranslationX = orignCenterX - targetCenterX;
                    mtranslationY = orignCenterY - targetCenterY;

                    pv.setTranslationX(mtranslationX);
                    pv.setTranslationY(mtranslationY);//设置这个位置的X，y位置是为了可以这页面的图片大小和上一个页面的图片大小一样

                    pv.setScaleX(mscaleX);
                    pv.setScaleY(mscaleY);

                    EnterAnimation(pv);

                }
            });
        }
        viewPagerAdapter = new BaseViewpagerAdapter();
        viewPagerAdapter.setList(photoViewList);
        hackyViewPager.setAdapter(viewPagerAdapter);
        hackyViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_tip_number.setText(position + 1 + "/" + photoViewList.size());
                final PhotoView photoView = (PhotoView) photoViewList.get(position);
                photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                    @Override
                    public void onPhotoTap(View view, float x, float y) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            finishAfterTransition();
                        } else {
                            ExitAnimation(photoView);
                        }
                    }
                });
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        hackyViewPager.setCurrentItem(currentPosition);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewCompat.setTransitionName(hackyViewPager, "image");
        }
    }

    /**
     * 隐藏状态栏，但是会导致布局错位,考虑用全屏设置
     */
    private void hideStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
    }

    private void EnterAnimation(final PhotoView photoView) {//X轴负数向左边，正整数向右
        ValueAnimator translationX = new ValueAnimator().ofFloat(photoView.getX(), 0);
        translationX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                photoView.setX((float) animation.getAnimatedValue());
            }
        });
        translationX.setDuration(300);
        translationX.start();

        ValueAnimator translationY = new ValueAnimator().ofFloat(photoView.getY(), 0);
        translationY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                photoView.setY((float) animation.getAnimatedValue());
            }
        });
        translationY.setDuration(300);
        translationY.start();

        ValueAnimator scaleX = new ValueAnimator().ofFloat(mscaleX, 1);
        scaleX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                photoView.setScaleX((float) animation.getAnimatedValue());
            }
        });
        scaleX.setDuration(300);
        scaleX.start();

        ValueAnimator scaleY = new ValueAnimator().ofFloat(mscaleY, 1);
        scaleY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                photoView.setScaleY((float) animation.getAnimatedValue());
            }
        });
        scaleY.setDuration(300);
        scaleY.start();
    }

    private void ExitAnimation(final PhotoView photoView) {
        ValueAnimator translationX = new ValueAnimator().ofFloat(0, mtranslationX);
        translationX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                photoView.setX((float) animation.getAnimatedValue());
            }
        });
        translationX.setDuration(300);
        translationX.start();

        ValueAnimator translationY = new ValueAnimator().ofFloat(0, mtranslationY);
        translationY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                photoView.setY((float) animation.getAnimatedValue());
            }
        });
        translationY.setDuration(300);
        translationY.start();

        ValueAnimator scaleX = new ValueAnimator().ofFloat(1, mscaleX);
        scaleX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                photoView.setScaleX((float) animation.getAnimatedValue());
            }
        });
        scaleX.setDuration(300);
        scaleX.start();

        ValueAnimator scaleY = new ValueAnimator().ofFloat(1, mscaleX);
        scaleY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                photoView.setScaleY((float) animation.getAnimatedValue());
            }
        });
        scaleY.setDuration(300);
        scaleY.start();

        scaleX.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animation.removeAllListeners();
                finish();
                overridePendingTransition(0, 0);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}




