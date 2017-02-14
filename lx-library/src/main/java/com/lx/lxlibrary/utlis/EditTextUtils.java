package com.lx.lxlibrary.utlis;

import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 创建人：LX
 * 创建日期：2016/7/18
 * 描述：输入控件的工具类
 */
public class EditTextUtils {


    /**
     * 手动打开软键盘
     *
     * @param context
     * @param editText
     */
    public static void open(Context context, View editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * 手动关闭软键盘，比如在搜索框的时候，点击删除按钮，清除按钮的同时，关闭键盘
     *
     * @param context
     * @param editText
     */
    public static void close(Context context, View editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    /**
     *
     * @param editText
     * @param inputStr      输入的长度
     * @param exceedlength  超过的长度
     */
    public static void prohibitExceedInput(EditText editText, String inputStr, int exceedlength) {
        if (inputStr.length() > exceedlength) {
            editText.setInputType(InputType.TYPE_NULL);
        } else {
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
        }
    }


}
