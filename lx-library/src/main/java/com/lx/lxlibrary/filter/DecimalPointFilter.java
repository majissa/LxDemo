package com.lx.lxlibrary.filter;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 创建人：LX
 * 创建日期：2016/8/24
 * 描述：
 */
public class DecimalPointFilter implements InputFilter {

    private final Pattern numPattern;
    private int decimalPlace;

    public DecimalPointFilter(int decimalPlace) {
        numPattern = Pattern.compile("[0-9]*");//除数字外的其他的
        this.decimalPlace = decimalPlace;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        String originalStr = dest.toString();
        //验证删除等按键
        if ("".equals(source.toString())) {
            return null;
        }
        //验证非数字或者小数点的情况
        Matcher m = numPattern.matcher(source);
        if (originalStr.contains(".")) {
            //已经存在小数点的情况下，只能输入数字
            if (!m.matches()) {
                return dest.subSequence(dstart, dend);   //不能返回null，放回null没有效果
            }
        } else {
            //未输入小数点的情况下，可以输入小数点和数字
            if (!m.matches() && !source.equals(".")) {
                return null;
            }
        }

        //验证小数位精度是否正确
        if (originalStr.contains(".")) {
            int index = originalStr.indexOf(".");
            int len = dend - index;
            //小数位只能2位
            if (len > decimalPlace) {
                CharSequence newText = dest.subSequence(dstart, dend);
                return newText;
            }
        }
        return dest.subSequence(dstart, dend) + source.toString();

    }
}
