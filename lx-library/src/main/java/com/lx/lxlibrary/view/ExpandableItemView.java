package com.lx.lxlibrary.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

import com.lx.lxlibrary.R;

/**
 * Created by 李响
 * 创建日期 2017/2/6
 * 描述：可动画扩展的itemView
 */
public class ExpandableItemView extends LinearLayout implements ValueAnimator.AnimatorUpdateListener, View.OnAttachStateChangeListener {
    private int expandViewId;
    private View expandView;
    private int expendViewHeight;
    private int expendViewWidth;
    private int nonExpandViewId;
    private View nonExpandView;
    private ValueAnimator openValueAnimator;
    private float animatedValue;

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    private boolean isExpand;
    private ValueAnimator closeValueAnimator;

    public ExpandableItemView(Context context) {
        super(context);
        init(context, null);

    }

    public ExpandableItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ExpandableItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(VERTICAL);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ExpandableItemView, 0, 0);
        expandViewId = array.getResourceId(R.styleable.ExpandableItemView_expandleView, 0);
        nonExpandViewId = array.getResourceId(R.styleable.ExpandableItemView_NonExpandleView, 0);
        expandView = LayoutInflater.from(context).inflate(expandViewId, null);
        expandView.setVisibility(GONE);
        nonExpandView = LayoutInflater.from(context).inflate(nonExpandViewId, null);
        addView(nonExpandView);
        addView(expandView);
        expandView.setScaleY(0);
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        expendViewHeight = expandView.getMeasuredHeight();
        expendViewWidth = expandView.getMeasuredWidth();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

//        expandView.setScaleX(0);
//        expandView.scrollTo(0, 0);
    }

    /**
     * 得到动画
     *
     * @return
     */
    private ValueAnimator getOpenValueAnimator() {
        openValueAnimator = new ValueAnimator();
        openValueAnimator.setDuration(500);
        openValueAnimator.setInterpolator(new LinearInterpolator());
        openValueAnimator.setFloatValues(0.0f, 1.0f);
        openValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (!isExpand) {
                    animatedValue = (float) animation.getAnimatedValue();
//        expandView.setScaleX(1);
                    expandView.setScaleY(animatedValue);
//        expandView.scrollTo(0, (int) (animatedValue * expendViewHeight));
                    expandView.setAlpha(animatedValue * 255);
                }
            }
        });
        openValueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (expandView.getVisibility() == GONE) {
                    expandView.setVisibility(VISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isExpand = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        return openValueAnimator;
    }

    /**
     * 得到动画
     *
     * @return
     */
    private ValueAnimator getClosedValueAnimator() {
        closeValueAnimator = new ValueAnimator();
        closeValueAnimator.setDuration(200);
        closeValueAnimator.setInterpolator(new LinearInterpolator());
        closeValueAnimator.setFloatValues(1.0f, 0.0f);
        closeValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (isExpand) {
                    animatedValue = (float) animation.getAnimatedValue();
//        expandView.setScaleX(1);
                    expandView.setScaleY(animatedValue);
//        expandView.scrollTo(0, (int) (animatedValue * expendViewHeight));
                    expandView.setAlpha(animatedValue * 255);
                }
            }
        });
        closeValueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isExpand = false;
                if (expandView.getVisibility() == VISIBLE) {
                    expandView.setVisibility(GONE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        return closeValueAnimator;
    }

    /**
     * 展开可扩展部分
     */
    public void expandExpandView() {
        ValueAnimator valueAnimator = getOpenValueAnimator();
        if (!isExpand) {
            valueAnimator.start();
        }
    }

    /**
     * 关闭可扩展部分
     */
    public void closeExpandView() {
        ValueAnimator valueAnimator = getClosedValueAnimator();
        if (isExpand) {
            valueAnimator.start();
        }
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {

    }

    @Override
    public void onViewAttachedToWindow(View v) {

    }

    @Override
    public void onViewDetachedFromWindow(View v) {
        if (openValueAnimator != null) {
            openValueAnimator.cancel();
        }
        if (closeValueAnimator != null) {
            closeValueAnimator.cancel();
        }
    }
}
