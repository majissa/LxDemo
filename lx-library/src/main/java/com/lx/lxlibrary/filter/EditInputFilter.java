package com.lx.lxlibrary.filter;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;

import com.lx.lxlibrary.utlis.ToastUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 创建人：CXQ
 * 创建日期：2016/5/24
 * 描述：
 */
public class EditInputFilter implements InputFilter {
    /**
     * 最大数字
     */
    private double MAX_VALUE = 3000;
    private String ToastMessage = "单笔充值金额超过3000元上限";
    /**
     * 小数点后的数字的位数
     */
    private int PONTINT_LENGTH = 2;
    Pattern p;
    private Context context;

    public EditInputFilter(Context context) {
        this.context = context;
        p = Pattern.compile("[0-9]*");   //除数字外的其他的
    }


    /**
     * @param src    将要输入的字符
     * @param start  将要输入的字符的范围start~end
     * @param end
     * @param dest   输入之前的字符
     * @param dstart 输入之前的字符范围，比如当一开始输入是dstart为0，dend为0，说明输入之前范围0~0，
     *               输入第一位时为 dstart为1，dend为1
     *               输入第二位时为 dstart为2，dend为2
     *               比如显示三位“345” 当减少输入的时候“5”时，dstart为3，dend为2
     * @param dend
     * @return 1.return null 保持原操作
     */

    @Override
    public CharSequence filter(CharSequence src, int start, int end,
                               Spanned dest, int dstart, int dend) {
        String oldtext = dest.toString();
        System.out.println(oldtext);
        //验证删除等按键
        if ("".equals(src.toString())) {
            return null;        //返回null说明接下来不输入
        }
        //验证非数字或者小数点的情况
        Matcher m = p.matcher(src);
        if ("".equals(oldtext)) {
            if (!m.matches() && src.equals(".")) {
                return "0.";
            }
        }
        if (oldtext.equals("0")) {
            if ("0".equals(src.toString())) {
                return null;
            } else {
                return src.toString();
            }
        }

        if (oldtext.contains(".")) {
            //已经存在小数点的情况下，只能输入数字
            if (!m.matches()) {
                return dest.subSequence(dstart, dend);
            }
        } else {//不存在的情况下

            if (!m.matches() && !src.equals(".")) {//未输入小数点的情况下，可以输入小数点和数字
                return null;
            }

        }
        //验证输入金额的大小
        if (!src.toString().equals("")) {
            double dold = Double.parseDouble(oldtext + src.toString());
            if (dold > MAX_VALUE) {
                ToastUtils.shortShow(ToastMessage);
                return dest.subSequence(dstart, dend);
            }
        }
        //验证小数位精度是否正确
        if (oldtext.contains(".")) {
            int index = oldtext.indexOf(".");
            int len = dend - index;
            //小数位只能2位
            if (len > PONTINT_LENGTH) {
                CharSequence newText = dest.subSequence(dstart, dend);
                return newText = "100";
            }
        }
        return dest.subSequence(dstart, dend) + src.toString();
    }

    public void setMAX_VALUE(double max_value) {
        MAX_VALUE = max_value;
    }

    public void setToastMessage(String toastMessage) {
        ToastMessage = toastMessage;
    }
}
