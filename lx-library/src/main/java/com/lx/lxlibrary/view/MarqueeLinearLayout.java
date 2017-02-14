package com.lx.lxlibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.lx.lxlibrary.R;
import com.lx.lxlibrary.log.Logger;

/**
 * Created by 李响
 * 创建日期 2017/1/5
 * 描述：平移的动画的圆点是以实现该动画的的控件的左上角的点为原点，进行平移的，y轴方向下为正数，y轴方向向上的为负数，x轴向左为负数，向右为正数
 */
public class MarqueeLinearLayout extends LinearLayout {
    private static final int UP = 0;
    private static final int LEFT = 1;
    private static final int DOWN = 2;
    private static final int RIGHT = 3;
    private static final int DEFAULT_SPEED = 35;//缺省速度值,数字越小，速度越快
    private static final int ANIMATION_MILLIMS = 0;//移出动画，与移出动画的时间间隔，
    private static final int DEFAULT_DIRECTION = LEFT;//默认向左循环实现跑马灯效果
    private int DEFAULT_INTERVAL = 0;
    private static final String TAG = MarqueeLinearLayout.class.getSimpleName();
    private int speed;
    private int direction;
    private LinearLayout wrapperLinearLayout;//内部的包裹内容LinearLayout
    private int width;
    private int wrapperHight;
    private int wrapperWidth;
    private int height;
    private int wrapperLeftDimen;//得到包装布局距离父布局左边的距离
    private int wrapperRightDimen;//得到包装布局距离父布局的右边的距离
    private int wrapperTopDimen;//得到包装布局距离父布局的上面距离
    private int wrapperBottomDimen;//得到包装布局距离父布局的下面距离
    private int startOut_X;
    private int endOut_X;
    private TranslateAnimation outAnimation;//x轴移出动画
    private TranslateAnimation InAnimation;//x轴进入动画
    private LinearInterpolator mInterpolator;
    private int outDuration;
    private int inDuration;
    private int srcollViewLeft = 0;
    private int srcollViewRight = 0;
    private int srcollViewTop = 0;
    private int srcollViewBottom = 0;


    public MarqueeLinearLayout(Context context) {
        super(context);
        init(context, null);
    }

    public MarqueeLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MarqueeLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(VERTICAL);
        mInterpolator = new LinearInterpolator();
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MarqueeLinearLayout);
        speed = array.getInteger(R.styleable.MarqueeLinearLayout_marquee_speed, DEFAULT_SPEED);
        direction = array.getInt(R.styleable.MarqueeLinearLayout_direction, DEFAULT_DIRECTION);
        array.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Logger.d(TAG, "onLayout called!" + "changed: " + changed);
        initAnimation();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getChildCount() == 0 || getChildCount() > 1) {
            throw new RuntimeException(
                    "MarqueeView must have exactly one child element.");
        }
        if (direction == LEFT || direction == RIGHT) {
            if (!(getChildAt(0) instanceof HorizontalScrollView)) {
                throw new RuntimeException("If you want to move left and right, MarqueeLinearLayout's child view must be HorizontalScrollView!");
            }
            HorizontalScrollView hsv = (HorizontalScrollView) getChildAt(0);
            srcollViewTop = hsv.getTop();
            srcollViewLeft = hsv.getLeft();
            srcollViewBottom = hsv.getBottom();
            srcollViewRight = hsv.getRight();
            hsv.setOnTouchListener(new OnTouchListener() {//是的滑动的scrollView不能滑动
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
            wrapperLinearLayout = (LinearLayout) hsv.getChildAt(0);
        } else if (direction == UP || direction == DOWN) {
            if (!(getChildAt(0) instanceof ScrollView)) {
                throw new RuntimeException("If you want to move up and down, MarqueeLinearLayout's child view must be ScrollView!");
            }
            ScrollView sv = (ScrollView) getChildAt(0);
            srcollViewTop = sv.getTop();
            srcollViewLeft = sv.getLeft();
            srcollViewBottom = sv.getBottom();
            srcollViewRight = sv.getRight();
            sv.setOnTouchListener(new OnTouchListener() {//是的滑动的scrollView不能滑动
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
            wrapperLinearLayout = (LinearLayout) sv.getChildAt(0);
        }
        wrapperHight = wrapperLinearLayout.getMeasuredHeight();
        wrapperWidth = wrapperLinearLayout.getMeasuredWidth();
    }

    /**
     * 初始化动画
     */
    private void initAnimation() {
        width = getWidth();//得到跑马灯控件的宽度
        height = getHeight();//得到跑马灯的高度
        wrapperLeftDimen = wrapperLinearLayout.getLeft() + srcollViewLeft;
        wrapperRightDimen = wrapperLinearLayout.getRight() + srcollViewRight;

        wrapperTopDimen = wrapperLinearLayout.getTop() + srcollViewTop;
        wrapperBottomDimen = wrapperLinearLayout.getBottom() + srcollViewBottom;

        int[] outDelta = getOutDelta(direction);
        int[] inDelta = getInDelta(direction);
        if (direction == UP || direction == DOWN) {
            outDuration = ((int) Math.abs(outDelta[2] - outDelta[3]) * speed);
            inDuration = ((int) Math.abs(inDelta[2] - inDelta[3]) * speed);
        } else {
            outDuration = ((int) Math.abs(outDelta[0] - outDelta[1]) * speed);
            inDuration = ((int) Math.abs(inDelta[0] - inDelta[1]) * speed);
        }

//        Logger.d(TAG, "width" + width);
//        Logger.d(TAG, "height" + height);
//        Logger.d(TAG, "wrapperLeftDimen" + wrapperLeftDimen);
//        Logger.d(TAG, "wrapperRightDime" + wrapperRightDime);
//        Logger.d(TAG, "startOut_X" + startOut_X);
//        Logger.d(TAG, "endOut_X" + endOut_X);
//        Logger.d(TAG, "duration" + outDuration);
        outAnimation = new TranslateAnimation(outDelta[0], outDelta[1], outDelta[2], outDelta[3]);
        outAnimation.setFillAfter(true);
        outAnimation.setDuration(outDuration);
        outAnimation.setInterpolator(mInterpolator);

        InAnimation = new TranslateAnimation(inDelta[0], inDelta[1], inDelta[2], inDelta[3]);
        InAnimation.setFillAfter(true);
        InAnimation.setDuration(inDuration);
        InAnimation.setRepeatCount(-1);
        InAnimation.setInterpolator(mInterpolator);
        startMarquee();

        outAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startInAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void startInAnimation() {
        postDelayed(inAnimationRunable, ANIMATION_MILLIMS);
    }

    private Runnable inAnimationRunable = new Runnable() {
        @Override
        public void run() {
            wrapperLinearLayout.startAnimation(InAnimation);
        }
    };

    /**
     * 根据方向算出X轴移出的距离
     *
     * @param direction
     * @return 依次是fromX, toX, fromY, toY;
     */
    private int[] getOutDelta(int direction) {
        int[] outDelta = new int[4];
        int fromX = 0;
        int toX = 0;
        int fromY = 0;
        int toY = 0;
        switch (direction) {
            case UP:
                toY = -(wrapperHight + wrapperTopDimen);
                break;
            case LEFT:
                toX = -(wrapperLeftDimen + wrapperWidth);
                break;
            case DOWN:
                toY = height - wrapperTopDimen;
                break;
            case RIGHT:
                toX = width - wrapperLeftDimen;
                break;
        }
        outDelta[0] = fromX;
        outDelta[1] = toX;
        outDelta[2] = fromY;
        outDelta[3] = toY;
        return outDelta;
    }

    /**
     * 根据方向算出X轴移出的距离
     *
     * @param direction
     * @return 依次是fromX, toX, fromY, toY;
     */
    private int[] getInDelta(int direction) {
        int[] intDelta = new int[4];
        int fromX = 0;
        int toX = 0;
        int fromY = 0;
        int toY = 0;
        switch (direction) {
            case UP:
                fromY = height - wrapperTopDimen;
                toY = -(wrapperHight + wrapperTopDimen);
                break;
            case LEFT:
                fromX = width - wrapperLeftDimen;
                toX = -(wrapperLeftDimen + wrapperWidth);
                break;
            case DOWN:
                fromY = -(wrapperHight + wrapperTopDimen);
                toY = height - wrapperTopDimen;
                break;
            case RIGHT:
                fromX = -wrapperWidth;
                toX = width;
                break;
        }
        intDelta[0] = fromX;
        intDelta[1] = toX;
        intDelta[2] = fromY;
        intDelta[3] = toY;
        return intDelta;
    }

    /**
     * 开始跑马灯
     */
    private void startMarquee() {
        post(new Runnable() {
            @Override
            public void run() {
                wrapperLinearLayout.startAnimation(outAnimation);
            }
        });

    }

    /**
     * Disables the animations.
     */
    public void reset() {
        if (inAnimationRunable == null)
            return;
        removeCallbacks(inAnimationRunable);
        wrapperLinearLayout.clearAnimation();
        outAnimation.reset();
        InAnimation.reset();
        invalidate();
    }
}
