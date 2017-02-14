package com.lx.lxdemo.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewParent;
import android.widget.CheckBox;

import com.lx.lxdemo.R;
import com.lx.lxdemo.common.L;

/**
 * 创建人：LX
 * 创建日期：2016/7/6
 * 描述：
 */
public class ToggleButton extends CheckBox {
    private Paint mPaint;

    private ViewParent mParent;

    private Bitmap mBottom;

    private Bitmap mCurBtnPic;

    private Bitmap mBtnPressed;

    private Bitmap mBtnNormal;

    private Bitmap mFrame;

    private Bitmap mMask;

    private RectF mSaveLayerRectF;

    private PorterDuffXfermode mXfermode;

    private float mFirstDownY; // 首次按下的Y

    private float mFirstDownX; // 首次按下的X

    private float mRealPos; // 图片的绘制位置

    private float mBtnPos; // 按钮的位置

    private float mBtnOnPos; // 开关打开的位置

    private float mBtnOffPos; // 开关关闭的位置

    private float mMaskWidth;

    private float mMaskHeight;

    private float mBtnWidth;

    private float mBtnInitPos;

    private int mClickTimeout;

    private int mTouchSlop;

    private final int MAX_ALPHA = 255;

    private int mAlpha = MAX_ALPHA;

    private boolean mChecked = false;

    private boolean mBroadcasting;

    private boolean mTurningOn;

    // private PerformClick mPerformClick;

    private OnCheckedChangeListener mOnCheckedChangeListener;

    private OnCheckedChangeListener mOnCheckedChangeWidgetListener;

    private boolean mAnimating;

    private final float VELOCITY = 350;

    private float mVelocity;

    private final float EXTENDED_OFFSET_Y = 15;

    private float mExtendOffsetY; // Y轴方向扩大的区域,增大点击区域

    private float mAnimationPosition;

    private float mAnimatedVelocity;

    public ToggleButton(Context context) {
        super(context);
        initView(context);
    }

    public ToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ToggleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        Resources resources = context.getResources();

        // get viewConfiguration
      /*  mClickTimeout = ViewConfiguration.getPressedStateDuration()
                + ViewConfiguration.getTapTimeout();
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();*/

        // get Bitmap
        mBottom = BitmapFactory.decodeResource(resources, R.mipmap.bottom);
        mBtnPressed = BitmapFactory.decodeResource(resources,
                R.mipmap.btn_pressed);
        mBtnNormal = BitmapFactory.decodeResource(resources,
                R.mipmap.btn_unpressed);
        mFrame = BitmapFactory.decodeResource(resources, R.mipmap.frame);
        mMask = BitmapFactory.decodeResource(resources, R.mipmap.mask);
        mCurBtnPic = mBtnNormal;

        mBtnWidth = mBtnPressed.getWidth();
        mMaskWidth = mMask.getWidth();
        mMaskHeight = mMask.getHeight();

        Log.d("TAG", "mbtnwidth:" + mBtnWidth);
        Log.d("TAG", "mMaskWidth:" + mMaskWidth);
        Log.d("TAG", "mMaskHeight:" + mMaskHeight);


        mBtnOnPos = mBtnWidth / 2;      //当为打开状态时算出中间有一个圆圈的一半距离，如果是打开状态图片距离就是0，圆圈在左边
        mBtnOffPos = mMaskWidth - mBtnWidth / 2;     //其实这个就是中间圆的一半大小


        L.dLi("mBtnOnPos:" + mBtnOnPos);
        L.dLi("mBtnOffPos:" + mBtnOffPos);
        L.dLi("mMaskWidth:" + mMaskWidth);
        //Log.d("TAG", );
        //Log.d("TAG",);

        mBtnPos = mChecked ? mBtnOnPos : mBtnOffPos;
        mRealPos = getRealPos(mBtnPos);//得出真的距离
        L.dLi("mRealPos:" + mRealPos);

        final float density = getResources().getDisplayMetrics().density;

        mVelocity = (int) (VELOCITY * density + 0.5f);
        mExtendOffsetY = (int) (EXTENDED_OFFSET_Y * density + 0.5f);

        Log.d("TAG", "mExtendOffsetY:" + mExtendOffsetY);
        Log.d("TAG", "density:" + density);

       /* mSaveLayerRectF = new RectF(0, 0, mMask.getWidth(),
                mMask.getHeight());*/
        // TODO: 2016/7/6     mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    /**
     * @param widthMeasureSpec
     * @param heightMeasureSpec note:1.如果没有重写这个方法并且设置大小的话，xml文件layout_width和layout_height设置match_parent的话就会充满整个父布局
     *                          设置为wrap_content的话，就是找到一个系统设置最小值，所以一般的自定义控件都需要重写onMeasure()方法。
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension((int) mMask.getWidth(),
                (int) (mMask.getHeight()));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.saveLayerAlpha(mSaveLayerRectF, mAlpha, Canvas.MATRIX_SAVE_FLAG
                | Canvas.CLIP_SAVE_FLAG | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG
                | Canvas.FULL_COLOR_LAYER_SAVE_FLAG
                | Canvas.CLIP_TO_LAYER_SAVE_FLAG);
        // 绘制蒙板
        canvas.drawBitmap(mMask, 0, 0, mPaint);
        mPaint.setXfermode(mXfermode);

        // 绘制底部图片
        canvas.drawBitmap(mBottom, -mMaskWidth+mBtnOffPos*2, 0, mPaint);
          mPaint.setXfermode(null);
        // 绘制边框
        //  canvas.drawBitmap(mFrame, 0, mExtendOffsetY, mPaint);

        // 绘制按钮
        L.dLi("mRealPos:" + mRealPos);
        canvas.drawBitmap(mCurBtnPic, -mMaskWidth+mBtnOffPos*2, 0, mPaint);
        canvas.restore();
    }

    /**
     * 将btnPos转换成RealPos
     *
     * @param btnPos
     * @return
     */
    private float getRealPos(float btnPos) {
        return btnPos - mBtnWidth / 2;
    }
}
