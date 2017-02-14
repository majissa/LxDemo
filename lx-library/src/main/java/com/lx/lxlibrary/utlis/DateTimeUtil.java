package com.lx.lxlibrary.utlis;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期和时间工具类
 *
 * @author z
 */
public class DateTimeUtil {

    /*
    * 时间标识 年
    * */
    private final static int YEAR = 0;
    /*
   * 时间标识 月
   * */
    private final static int MONTH = 1;
    /*
   * 时间标识 日
   * */
    private final static int DAY = 2;
    /*
   * 时间标识 时
   * */
    private final static int HOUR = 3;
    /*
   * 时间标识 分
   * */
    private final static int MIUNTE = 4;
    /*
   * 时间标识 秒
   * */
    private final static int SECOND = 5;

    /**
     * yyyy-MM
     */
    public static final String dateYM = "yyyy-MM";// 日期
    /**
     * yyyy-MM-dd
     */
    public static final String dateYMD = "yyyy-MM-dd";// 日期
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String dateTimeYMDHMS = "yyyy-MM-dd HH:mm:ss";// 日期时间
    /**
     * yyyy-MM-dd HH:mm:ss:SSS
     */
    public static final String dateTimeYMDHMSS = "yyyy-MM-dd HH:mm:ss:SSS";// 日期时间精确到毫秒
    /**
     * yyyy年MM月dd日
     */
    public static final String Chinadate = "yyyy年MM月dd日";// 中国日期
    /**
     * yyyy年MM月dd日 HH时mm分ss秒
     */
    public static final String ChinadateTime = "yyyy年MM月dd日 HH时mm分ss秒";// 中国日期时间
    /**
     * yyyy年MM月dd日 HH:mm:ss
     */
    public static final String ChinadateTime2 = "yyyy年MM月dd日 HH:mm:ss";// 中国日期时间2

    private final static long YEAR_LEVEL_VALUE = 365 * 24 * 60 * 60 * 1000;
    private final static long MONTH_LEVEL_VALUE = 30 * 24 * 60 * 60 * 1000;
    private final static long DAY_LEVEL_VALUE = 24 * 60 * 60 * 1000;
    private final static long HOUR_LEVEL_VALUE = 60 * 60 * 1000;
    private final static long MINUTE_LEVEL_VALUE = 60 * 1000;
    private final static long SECOND_LEVEL_VALUE = 1000;

    /**
     * 获取现在时间
     *
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static Date getNowDateTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取到时间组成文件名的一部分，一般是生成图片，照片，音乐，视频，文件等时使用，格式中不能有
     * 空格，英文的冒号:，只能用中文的冒号：
     *
     * @return
     */
    public static String getNowDateTimeAsFileName() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH：mm：ss");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String time = formatter.format(curDate);
        return time;
    }

    /**
     * 获取现在时间
     *
     * @return返回短时间格式 yyyy-MM-dd
     */
    public static Date getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getNowDateTimeString() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getNowDateString() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间 小时:分;秒 HH:mm:ss
     *
     * @return
     */
    public static String getNowTimeString() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param dateDate
     * @return
     */
    public static String dateToStrLong(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     *
     * @param dateDate
     * @return
     */
    public static String dateToStr(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 得到现在时间
     *
     * @return
     */
    public static Date getNow() {
        Date currentTime = new Date();
        return currentTime;
    }

    /**
     * 得到现在时间
     *
     * @return 字符串 yyyyMMdd HHmmss
     */
    public static String getStringToday() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 得到现在小时
     */
    public static String getHour() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String hour;
        hour = dateString.substring(11, 13);
        return hour;
    }

    /**
     * 得到现在分钟
     *
     * @return
     */
    public static String getTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String min;
        min = dateString.substring(14, 16);
        return min;
    }

    /**
     * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
     *
     * @param format yyyyMMddhhmmss
     * @return
     */
    public static String getNowDateTimeByFormatter(String format) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 得到两个日期间的间隔天数
     */
    public static String getTwoDay(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0;
        try {
            Date date = myFormatter.parse(sj1);
            Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return "";
        }
        return day + "";
    }

    /**
     * 时间前推或后推分钟,其中JJ表示分钟.
     */
    public static String getPreTime(String sj1, String jj) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mydate1 = "";
        try {
            Date date1 = format.parse(sj1);
            long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
            date1.setTime(Time * 1000);
            mydate1 = format.format(date1);
        } catch (Exception e) {
        }
        return mydate1;
    }

    /**
     * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
     */
    public static String getNextDay(String nowdate, String delay) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String mdate = "";
            Date d = strToDate(nowdate);
            long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24
                    * 60 * 60;
            d.setTime(myTime * 1000);
            mdate = format.format(d);
            return mdate;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 判断是否润年
     *
     * @param ddate
     * @return
     */
    public static boolean isLeapYear(String ddate) {

        /**
         * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
         * 3.能被4整除同时能被100整除则不是闰年
         */
        Date d = strToDate(ddate);
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(d);
        int year = gc.get(Calendar.YEAR);
        if ((year % 400) == 0)
            return true;
        else if ((year % 4) == 0) {
            if ((year % 100) == 0)
                return false;
            else
                return true;
        } else
            return false;
    }

    /**
     * 普通时间格式转为 带星期提示的 时间格式
     * 如： 2014-7-1 12:12:31 --> 2014-7-1(周二 ) 12:12:31
     *
     * @param dateStr
     * @return
     */
    public static String formatDateToDay(String dateStr) {
        if (dateStr == null || "".equals(dateStr))
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(dateStr);
            String dateArr[] = dateStr.split(" ");
            return dateArr[0] + "(周" + formatNumberDayToStr(date.getDay()) + ") " + dateArr[1];
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    public static String dateFormatWeek(String date) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return "星期" + formatNumberDayToStr(sdf1.parse(date).getDay());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String formatNumberDayToStr(int day) {
        switch (day) {
            case 0:
                return "日";
            case 1:
                return "一";
            case 2:
                return "二";
            case 3:
                return "三";
            case 4:
                return "四";
            case 5:
                return "五";
            case 6:
                return "六";
        }
        return null;
    }

    /**
     * 自定义日期时间格式
     *
     * @param date       日期时间字符串，如2014-8-1 13:11:33.0
     * @param dateFormat 格式化字符串，如HH:mm:ss
     * @return
     */
    public static String formatDate(String date, String dateFormat) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat(dateFormat);
        try {
            Date d = sdf1.parse(date);
            return sdf2.format(d);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 判断是否为合法的日期时间字符串
     *
     * @param str_input
     * @param str_input
     * @return boolean;符合为true,不符合为false
     */
    public static boolean isDate(String str_input, String rDateFormat) {
        if (str_input == null) {
            SimpleDateFormat formatter = new SimpleDateFormat(rDateFormat);
            formatter.setLenient(false);
            try {
                formatter.format(formatter.parse(str_input));
            } catch (Exception e) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * 传入年月日，返回2016-01-15格式的字符串，常用于提交到接口给数据库使用
     *
     * @param year
     * @param monthOfYear
     * @param dayOfMonth
     * @return
     */
    public static String getFormattedDate(int year, int monthOfYear, int dayOfMonth) {
        String month = monthOfYear + "";
        if (month.length() == 1) {
            month = "0" + month;
        }
        String day = dayOfMonth + "";
        if (day.length() == 1) {
            day = "0" + day;
        }
        return year + "-" + month + "-" + day;
    }

    /**
     * 传入年月日时分，返回2016-01-15 06:30格式的字符串，常用于提交到接口给数据库使用
     *
     * @param year
     * @param monthOfYear
     * @param dayOfMonth
     * @param hourOfDay
     * @param min
     * @return
     */
    public static String getFormattedDateTime(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int min) {
        String month = monthOfYear + "";
        if (month.length() == 1) {
            month = "0" + month;
        }
        String day = dayOfMonth + "";
        if (day.length() == 1) {
            day = "0" + day;
        }
        String hour = hourOfDay + "";
        if (hour.length() == 1) {
            hour = "0" + hour;
        }
        return year + "-" + month + "-" + day + " " + hour + ":" + min + ":00";
    }

    /**
     * 固定时间格式转换成为毫秒
     *
     * @param date
     * @return
     */
    public static long TimeParseMillis(String date) {
        Date date1 = null;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date1 = simpleDateFormat.parse(date);
            calendar.setTime(date1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        return calendar.getTimeInMillis();
    }


    public static int[] MillisParseTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        calendar.get(Calendar.YEAR);  //得到年
        calendar.get(Calendar.MONTH);  //月 要加1，因为从0开始计数
        calendar.get(Calendar.DAY_OF_MONTH); //月
        calendar.get(Calendar.HOUR_OF_DAY); //小时
        calendar.get(Calendar.MINUTE); //分钟
        calendar.get(Calendar.SECOND); //秒钟
        int[] times = {calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND)};
        return times;
    }

    /**
     * 根据传入时间和与目标时间算出时间差，放回的是毫秒
     *
     * @param nowTime    单位为毫秒
     * @param targetTime 单位为毫秒
     * @return 时间数组
     */
    public static int[] getDifference(long nowTime, long targetTime) {
        long period = targetTime - nowTime;
        long abs = Math.abs(period);
        return getDifference(abs);
    }

    /**
     * 根据毫秒时间算出时间数组，分别为年，月，日，时，分，秒
     *
     * @param period 单位为毫秒
     * @return 时间数组
     */
    public static int[] getDifference(long period) {//根据毫秒差计算时间差
        int[] result = new int[6];
        /*******计算出时间差中的年、月、日、天、时、分、秒*******/
        int year = getYear(period);
        int month = getMonth(period - year * YEAR_LEVEL_VALUE);
        int day = getDay(period - year * YEAR_LEVEL_VALUE - month * MONTH_LEVEL_VALUE);
        int hour = getHour(period - year * YEAR_LEVEL_VALUE - month * MONTH_LEVEL_VALUE - day * DAY_LEVEL_VALUE);
        int minute = getMinute(period - year * YEAR_LEVEL_VALUE - month * MONTH_LEVEL_VALUE - day * DAY_LEVEL_VALUE - hour * HOUR_LEVEL_VALUE);
        int second = getSecond(period - year * YEAR_LEVEL_VALUE - month * MONTH_LEVEL_VALUE - day * DAY_LEVEL_VALUE - hour * HOUR_LEVEL_VALUE - minute * MINUTE_LEVEL_VALUE);
        result[DateTimeUtil.YEAR] = year;
        result[DateTimeUtil.MONTH] = month;
        result[DateTimeUtil.DAY] = day;
        result[DateTimeUtil.HOUR] = hour;
        result[DateTimeUtil.MIUNTE] = minute;
        result[DateTimeUtil.SECOND] = second;
        return result;
    }

    public static int getYear(long period) {
        return (int) (period / YEAR_LEVEL_VALUE);
    }

    public static int getMonth(long period) {
        return (int) (period / MONTH_LEVEL_VALUE);
    }

    public static int getDay(long period) {
        return (int) (period / DAY_LEVEL_VALUE);
    }

    public static int getHour(long period) {
        return (int) (period / HOUR_LEVEL_VALUE);
    }

    public static int getMinute(long period) {
        return (int) (period / MINUTE_LEVEL_VALUE);
    }

    public static int getSecond(long period) {
        return (int) (period / SECOND_LEVEL_VALUE);
    }

}
