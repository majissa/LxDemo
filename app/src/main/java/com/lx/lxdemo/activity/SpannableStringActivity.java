package com.lx.lxdemo.activity;

import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;

import com.lx.lxdemo.R;
import com.lx.lxlibrary.activity.BaseActivity;
import com.lx.lxdemo.my.MainActivity;
import com.lx.lxlibrary.utlis.SpannableStringUtil;

/**
 * 创建人：LX
 * 创建日期：2016/8/17
 * 描述：
 */
public class SpannableStringActivity extends BaseActivity {
    private TextView textView;
    private TextView tv_biaoji;
    private TextView tv_biaoji2;

    @Override
    protected void initView(View view) {
        textView = (TextView) view.findViewById(R.id.textView);
        tv_biaoji = (TextView) view.findViewById(R.id.tv_biaoji);
        tv_biaoji2 = (TextView) view.findViewById(R.id.tv_biaoji2);
    }

    @Override
    protected void initEvent() {
    }

    @Override
    protected void initValue() {
        setSpanString();
        // tv_biaoji.setText(getString(R.string.underline_the_sentence_underlined_sentences));
        // tv_biaoji.setText(getResources().getString(R.string.underline_the_sentence_underlined_sentences));
        String string = getString(R.string.underline_the_sentence_underlined_sentence2s);
        String s = TextUtils.htmlEncode(string);
        Spanned spanned = Html.fromHtml("这只是一个测试，测试<u>下划线</u>、<i>uuuuuuiiiii</i>、<font color='red'>红色字</font>的格式");
        tv_biaoji2.setText(spanned);
    }

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_spannable_string;
    }


    private void setSpanString() {
        SpannableString spannableString = new SpannableString("超链接连接");
        URLSpan urlSpan = new URLSpan("http://www.baidu.com");
        ForegroundColorSpan backgroundColorSpan = new ForegroundColorSpan(getResources().getColor(R.color.blue));
        SpannableStringBuilder spString = SpannableStringUtil.setTextJumpActivity(context, "超链接连接", 0, 2, MainActivity.class);
        spString.setSpan(backgroundColorSpan, 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onClick(View v) {

    }
}
