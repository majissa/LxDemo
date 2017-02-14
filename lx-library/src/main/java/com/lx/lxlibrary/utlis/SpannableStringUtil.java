package com.lx.lxlibrary.utlis;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.ColorRes;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 创建人：CXQ
 * 创建日期：2016/8/1
 * 描述：
 */
public class SpannableStringUtil {

    /**
     * @param context       context
     * @param s             需要改变的字串
     * @param start         开始位置
     * @param end           结束位置
     * @param colorResource 字体颜色的资源
     * @param sp            字体大小
     * @return 已改变的字串
     */
    public static SpannableStringBuilder setTextStyle(Context context, String s, int start, int end, @ColorRes int colorResource, int sp) {
        SpannableStringBuilder spanString = new SpannableStringBuilder(s);
        if (colorResource != 0) {
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(context.getResources().getColor(colorResource));
            spanString.setSpan(colorSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (sp != 0) {
            AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(sp);
            spanString.setSpan(sizeSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spanString;
    }

    /**
     * @param context
     * @param s
     * @param start
     * @param end
     * @param jumpActivity 跳转的Activity
     * @return
     */
    public static SpannableStringBuilder setTextJumpActivity(Context context, String s, int start, int end, Class jumpActivity) {
        SpannableStringBuilder spanString = new SpannableStringBuilder(s);
        IntentSpan intentSpan = new IntentSpan(context, jumpActivity);
        spanString.setSpan(intentSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanString;
    }


    /**
     * 传入从sp取出来的Set<String>集合，由于直接炒作取出来的集合会导致APP数据清理后，只剩下Set集合数据只剩下一条
     * 故新建一个set存放
     *
     * @param set 从sp取出来的Set<String>集合
     * @return 新建一个set存放
     */
    public static Set<String> getTempSet(Set<String> set) {
        HashSet<String> tempSet = new HashSet<String>();
        if (!set.isEmpty()) {
            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                tempSet.add(next);
            }
            return tempSet;
        } else {
            return tempSet;
        }

    }


    static class IntentSpan extends ClickableSpan {
        private Context context;
        private Class jumpCls;

        public IntentSpan(Context cls, Class jumpCls) {
            this.context = cls;
            this.jumpCls = jumpCls;
        }

        @Override
        public void onClick(View widget) {
            context.startActivity(new Intent(context, jumpCls));
        }
    }
}
