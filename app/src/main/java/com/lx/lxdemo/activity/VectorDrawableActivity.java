package com.lx.lxdemo.activity;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.lx.lxdemo.R;
import com.lx.lxlibrary.activity.BaseActivity;
import com.wnafee.vector.compat.AnimatedVectorDrawable;
import com.wnafee.vector.compat.ResourcesCompat;

/**
 * Created by 李响
 * 创建日期 2016/12/30
 * 描述：
 */
public class VectorDrawableActivity extends BaseActivity {

    private ImageView iv_vector;
    private AnimatedVectorDrawable animatable;
    private android.graphics.drawable.AnimatedVectorDrawable animatable1;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        animatable1.start();
                    } else {
                        if (animatable != null && !animatable.isRunning()) {
                            animatable.start();
                        }

                    }
//                    handler.sendEmptyMessage(1);
                    break;
            }
        }
    };

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_vectordrawable;
    }


    @Override
    protected void initView(View view) {
        iv_vector = (ImageView) view.findViewById(R.id.iv_vector);
//        animatable = (Animatable)iv_vector.getDrawable();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            animatable1 = (android.graphics.drawable.AnimatedVectorDrawable) ResourcesCompat.getDrawable(context, R.drawable.svg_path);
            iv_vector.setImageDrawable(animatable1);
        } else {
            animatable = AnimatedVectorDrawable.getDrawable(context, R.drawable.svg_path);
            iv_vector.setImageDrawable(animatable);
        }
//        animatable = (Animatable)iv_vector.getDrawable();
        iv_vector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    animatable1.start();
                } else {
                    animatable.start();
                }
            }
        });
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initValue() {
//        handler.sendEmptyMessage(1);
    }

    @Override
    public void onClick(View v) {

    }
}
