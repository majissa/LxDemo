package com.lx.lxlibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lx.lxlibrary.R;


/**
 * Created by 李响
 * 创建日期 2016/10/27
 * 描述：倒计时textView
 */

public class CountDownTextView extends TextView {

    /*
    * 时间标识 年
    * */
    public final static int YEAR = 0;
    /*
   * 时间标识 月
   * */
    public final static int MONTH = 1;
    /*
   * 时间标识 日
   * */
    public final static int DAY = 2;
    /*
   * 时间标识 时
   * */
    public final static int HOUR = 3;
    /*
   * 时间标识 分
   * */
    public final static int MIUNTE = 4;
    /*
   * 时间标识 秒
   * */
    public final static int SECOND = 5;

    public final static int COUNT_DOWN_MSG = 1;
    /**
     * 得到的时间数组
     */
    private int[] result = new int[6];
    /**
     * 倒计时间隔，默认1秒
     */
    public final int durationValue = 1000;

    private int mDuration;

    /**
     * 目标时间
     */
    private long targetTimeMillis;
    /**
     * 当前时间，单位毫秒
     */
    private long currentTimeMillis;
    /**
     * 剩余多少时间，单位毫秒
     */
    private long remainTimeMillis;
    /**
     * 精确单位
     */
    private int unit;

    private CountDownListener listener;

    private final static long YEAR_LEVEL_VALUE = 365 * 24 * 60 * 60 * 1000;

    private final static long MONTH_LEVEL_VALUE = 30 * 24 * 60 * 60 * 1000;

    private final static long DAY_LEVEL_VALUE = 24 * 60 * 60 * 1000;

    private final static long HOUR_LEVEL_VALUE = 60 * 60 * 1000;

    private final static long MINUTE_LEVEL_VALUE = 60 * 1000;

    private final static long SECOND_LEVEL_VALUE = 1000;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case COUNT_DOWN_MSG:
                    if (remainTimeMillis < 0) {
                        if (listener != null) {
                            listener.onFinish();
                            handler.removeMessages(COUNT_DOWN_MSG);
                        }
                    } else {
                        setTimeByUnit(remainTimeMillis);
                        if (listener != null) {
                            listener.onCountDowning(getDifference(remainTimeMillis));
                        }
                        remainTimeMillis = remainTimeMillis - durationValue;
                        handler.sendEmptyMessageDelayed(COUNT_DOWN_MSG, mDuration);
                    }
                    break;
            }
        }
    };

    public CountDownTextView(Context context) {
        super(context);
        init(context, null);
    }

    public CountDownTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CountDownTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CountDownTextView);
        mDuration = a.getInteger(R.styleable.CountDownTextView_duration, durationValue);
        unit = a.getInt(R.styleable.CountDownTextView_preciseUnit, -1);
    }

    public void setCountDownListener(CountDownListener listener) {
        this.listener = listener;
    }

    /**
     * 设置目标时间，单位是毫秒,与现在的时间的倒计时
     *
     * @param targetTimeMillis
     */
    public void setTargetTime(long targetTimeMillis) {
        this.targetTimeMillis = targetTimeMillis;
        currentTimeMillis = System.currentTimeMillis();
        remainTimeMillis = Math.abs(targetTimeMillis - currentTimeMillis);
    }

    /**
     * 设置倒计时的时间差，单位毫秒
     */
    public void setDifferenceTime(long differenceTime) {
        this.remainTimeMillis = differenceTime;
    }

    /**
     * 停止倒计时
     */
    public void stop() {
        if (handler == null) {
            return;
        }
        handler.removeMessages(COUNT_DOWN_MSG);
        if (listener != null) {
            listener.onStop(getDifference(remainTimeMillis));
        }
    }

//    /**
//     * 重新开始倒计时
//     */
//    public void reStart(long targetTimeMillis) {
//        if (handler == null) {
//            return;
//        }
//        handler.sendEmptyMessageDelayed(COUNT_DOWN_MSG, mDuration);
//    }

    /**
     * 开始倒计时
     */
    public void start() {
        if (handler == null) {
            return;
        }
        if (handler.hasMessages(COUNT_DOWN_MSG)) {
            handler.removeMessages(COUNT_DOWN_MSG);
        }
        if (listener != null) {
            listener.onStart();
        }
        handler.sendEmptyMessageDelayed(COUNT_DOWN_MSG, mDuration);
    }

    private void setTimeByUnit(long currentTimeMillis) {
        int[] result = getDifference(currentTimeMillis);
        switch (unit) {
            case YEAR://精确到年
                setText(result[YEAR] + "年");
                break;
            case MONTH://精确到月
                setText(result[YEAR] + "年" + result[MONTH] + "月");
                break;
            case DAY://精确到日
                setText(result[YEAR] + "年" + result[MONTH] + "月" + result[DAY] + "日");
                break;
            case HOUR://精确到时
                setText(result[YEAR] + "年" + result[MONTH] + "月" + result[DAY] + "日" + result[HOUR] + "时");
                break;
            case MIUNTE://精确到分
                setText(result[YEAR] + "年" + result[MONTH] + "月" + result[DAY] + "日" + result[HOUR] + "时" + result[MIUNTE] + "分");
                break;
            case SECOND://精确到秒
                setText(result[YEAR] + "年" + result[MONTH] + "月" + result[DAY] + "日" + result[HOUR] + "时" + result[MIUNTE] + "分" + result[SECOND] + "秒");
                break;
        }
    }

    /**
     * 根据毫秒时间算出时间数组，分别为年，月，日，时，分，秒
     *
     * @param period 单位为毫秒
     * @return 时间数组
     */
    private int[] getDifference(long period) {//根据毫秒差计算时间差
        /*******计算出时间差中的年、月、日、天、时、分、秒*******/
        int year = getYear(period);
        int month = getMonth(period - year * YEAR_LEVEL_VALUE);
        int day = getDay(period - year * YEAR_LEVEL_VALUE - month * MONTH_LEVEL_VALUE);
        int hour = getHour(period - year * YEAR_LEVEL_VALUE - month * MONTH_LEVEL_VALUE - day * DAY_LEVEL_VALUE);
        int minute = getMinute(period - year * YEAR_LEVEL_VALUE - month * MONTH_LEVEL_VALUE - day * DAY_LEVEL_VALUE - hour * HOUR_LEVEL_VALUE);
        int second = getSecond(period - year * YEAR_LEVEL_VALUE - month * MONTH_LEVEL_VALUE - day * DAY_LEVEL_VALUE - hour * HOUR_LEVEL_VALUE - minute * MINUTE_LEVEL_VALUE);
        result[CountDownTextView.YEAR] = year;
        result[CountDownTextView.MONTH] = month;
        result[CountDownTextView.DAY] = day;
        result[CountDownTextView.HOUR] = hour;
        result[CountDownTextView.MIUNTE] = minute;
        result[CountDownTextView.SECOND] = second;
        return result;
    }

    private static int getYear(long period) {
        return (int) (period / YEAR_LEVEL_VALUE);
    }

    private static int getMonth(long period) {
        return (int) (period / MONTH_LEVEL_VALUE);
    }

    private static int getDay(long period) {
        return (int) (period / DAY_LEVEL_VALUE);
    }

    private static int getHour(long period) {
        return (int) (period / HOUR_LEVEL_VALUE);
    }

    private static int getMinute(long period) {
        return (int) (period / MINUTE_LEVEL_VALUE);
    }

    private static int getSecond(long period) {
        return (int) (period / SECOND_LEVEL_VALUE);
    }

    public interface CountDownListener {

        public void onStart();

        public void onStop(int[] result);

        public void onFinish();

        public void onCountDowning(int[] result);

    }
}
