package com.lx.lxdemo.activity;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import com.lx.lxdemo.R;
import com.lx.lxlibrary.activity.BaseActivity;

/**
 * Created by 李响
 * 创建日期 2017/1/12
 * 描述：
 */
public class ClipDrawableActivity extends BaseActivity {
    private ImageView iv_top_vertical;
    private ImageView iv_bottom_vertical;
    private ImageView iv_left_horziontal;
    private ImageView iv_right_horziontal;
    private AsyncTask<Void, Integer, Void> execute;

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_clipdrawable;
    }

    @Override
    protected void initView(View view) {
        iv_top_vertical = (ImageView) view.findViewById(R.id.iv_top_vertical);
        iv_bottom_vertical = (ImageView) view.findViewById(R.id.iv_bottom_vertical);
        iv_left_horziontal = (ImageView) view.findViewById(R.id.iv_left_horziontal);
        iv_right_horziontal = (ImageView) view.findViewById(R.id.iv_right_horziontal);

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initValue() {
        loadImage();

    }

    @Override
    public void onClick(View v) {

    }

    private void loadImage() {
        execute = new AsyncTask<Void, Integer, Void>() {

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                iv_top_vertical.getDrawable().setLevel(500 * values[0]);
                iv_bottom_vertical.getDrawable().setLevel(500 * values[0]);
                iv_left_horziontal.getDrawable().setLevel(500 * values[0]);
                iv_right_horziontal.getDrawable().setLevel(500 * values[0]);
            }

            @Override
            protected Void doInBackground(Void... params) {
                int i = 0;
                while (i <= 100) {
                    try {
                        Thread.sleep(500);
                        i++;
                        publishProgress(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        }.execute();
    }

    private void loadImage1() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        execute.cancel(true);
    }
}
