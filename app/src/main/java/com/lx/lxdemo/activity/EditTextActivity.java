package com.lx.lxdemo.activity;

import android.os.Handler;
import android.os.Message;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lx.lxdemo.R;
import com.lx.lxdemo.adapter.TestBaseAdapter;
import com.lx.lxlibrary.activity.BaseActivity;
import com.lx.lxlibrary.adapter.AbsBaseAdapter;
import com.lx.lxlibrary.filter.CompareFilter;
import com.lx.lxlibrary.filter.DecimalPointFilter;
import com.lx.lxlibrary.log.Logger;
import com.lx.lxlibrary.utlis.DateTimeUtil;
import com.lx.lxlibrary.utlis.EditTextUtils;
import com.lx.lxlibrary.utlis.ToastUtils;
import com.lx.lxlibrary.view.CountDownTextView;
import com.lx.lxlibrary.view.LinearListView;

import java.util.Arrays;


/**
 * 创建人：LX
 * 创建日期：2016/7/18
 * 描述：
 */
public class EditTextActivity extends BaseActivity {

    private EditText etOne;
    private Button btnOpen;
    private Button btnClose;
    private EditText etTwo;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (l2 == l1) {
                        return;
                    }
                   /* int[] ints = DateTimeUtil.MillisParseTime(l1);
                    int day = ints[2];
                    int hour = ints[3];
                    int minnte = ints[4];
                    int second = ints[5];*/

                    l3 = l3 - 1000;
                    handler.sendEmptyMessageDelayed(1, 1000);
                    break;
            }

            super.handleMessage(msg);

        }
    };
    private long l3;
    private long l1;
    private long l2;
    private LinearListView linearListView;
    private CountDownTextView countDownTextView;
    private Button btn_restatrt;

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_exitttext;
    }

    @Override
    protected void initView(View view) {
        etOne = (EditText) view.findViewById(R.id.et_one);
        etTwo = (EditText) view.findViewById(R.id.et_two);
        btnOpen = (Button) view.findViewById(R.id.btn_open);
        btnClose = (Button) view.findViewById(R.id.btn_close);
        btn_restatrt = (Button) view.findViewById(R.id.btn_restatrt);
        countDownTextView = (CountDownTextView) view.findViewById(R.id.countDownTextView);
        linearListView = (LinearListView) view.findViewById(R.id.linearListView);
    }

    @Override
    protected void initEvent() {
        btnOpen.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        btn_restatrt.setOnClickListener(this);
        countDownTextView.setCountDownListener(new CountDownTextView.CountDownListener() {
            @Override
            public void onStart() {
                Logger.dLi("start!");
            }

            @Override
            public void onStop(int[] result) {
                Logger.dLi("stop!");
            }

            @Override
            public void onFinish() {
                Logger.dLi("finsish!");
            }

            @Override
            public void onCountDowning(int[] result) {
//                countDownTextView.setText(result[countDownTextView.YEAR] + "年" + result[countDownTextView.MONTH] + "月" + result[countDownTextView.DAY] + "日" + result[countDownTextView.HOUR] + "时" + result[countDownTextView.MIUNTE] + "分" + result[countDownTextView.SECOND] + "秒");
            }
        });
    }

    @Override
    protected void initValue() {
        /**
         * 实验发现，小数只能限制一个的，并不是inputFliter的作用是，是在xml中设置inputType的为numberDecimal的作用
         * inputFliter的作用只是判断是否超过大小
         */

        InputFilter[] filters = {new CompareFilter(100, R.string.the_largest_amount_is_100_yuan), new DecimalPointFilter(2)};
        etOne.setFilters(filters);
        //  etTwo.setFilters(filters);

        l1 = DateTimeUtil.TimeParseMillis("2016-11-01 17:28:00");
        l2 = DateTimeUtil.TimeParseMillis("2016-08-26 14:30:42");
        l3 = l1 - l2;
        Logger.dLi("l1:----->" + l1);
        Logger.dLi("l2:----->" + l2);
        Logger.dLi("l3:----->" + l3);

        TestBaseAdapter textadapter = new TestBaseAdapter(context);
        textadapter.setonClickListener(this);
        textadapter.setonItemClickListener(new AbsBaseAdapter.OnItemClickListener<String>() {
            @Override
            public void itemClick(String o, View v, int position) {
                ToastUtils.shortShow(o.toString());
            }
        });
        textadapter.setList(Arrays.asList(getResources().getStringArray(R.array.demo_list)));
        linearListView.setAdapter(textadapter);

        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessageDelayed(1, 1000);
            }
        }).start();

        countDownTextView.setTargetTime(l1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_open:
                EditTextUtils.open(context, etOne);
                countDownTextView.start();
                break;
            case R.id.btn_close:
                countDownTextView.stop();
                EditTextUtils.close(context, etOne);
                break;
            case R.id.btn_restatrt:
                countDownTextView.setDifferenceTime(1 * 60 * 1000);
                countDownTextView.start();
                break;
        }
    }
}
