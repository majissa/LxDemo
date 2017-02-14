package com.lx.lxlibrary.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lx.lxlibrary.R;


/**
 * 创建人：LX
 * 创建日期：2016/6/27
 * 描述：基类Activity 如果使用AppCompatActivity,使用 requestWindowFeature(Window.FEATURE_NO_TITLE);没有效果，
 * 如果使用FragmentActivity,getFragmentManager会报错，具体原因还需了解
 * todo  了解AppCompatActivity
 */
public abstract class BaseActivity extends AbsBaseActivity {

    private ImageView iv_right;
    private TextView tv_Title;

    @Override
    protected int getTitleBarHeight() {
        return getResources().getDimensionPixelOffset(R.dimen.activity_top_padding);
    }

    @Override
    protected int inflateTitleBarLayoutId() {
        return R.layout.include_top_title;
    }

    @Override
    protected void initTiTleBarViews(View view) {
        iv_right = (ImageView) view.findViewById(R.id.iv_right);
        iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tv_Title = (TextView) view.findViewById(R.id.tv_title);
    }

    public void setRightImageViewGone() {
        if (iv_right != null) {
            iv_right.setVisibility(View.GONE);
        }
    }

    public void setRightImageViewVisibility() {
        if (iv_right != null) {
            iv_right.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置标题
     *
     * @param titleId
     */
    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
        tv_Title.setText(titleId);
    }
}
