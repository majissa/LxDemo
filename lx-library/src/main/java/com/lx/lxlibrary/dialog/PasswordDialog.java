package com.lx.lxlibrary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.lx.lxlibrary.R;
import com.lx.lxlibrary.view.PasswordEditText;

import static android.content.Context.INPUT_METHOD_SERVICE;


/**
 * Created by 李响
 * 创建日期 2017/3/13
 * 描述：
 */
public class PasswordDialog extends Dialog {
    private Builder builder;

    public PasswordDialog(Context context) {
        super(context, android.R.style.Theme_Holo_Light_Dialog_MinWidth);//定义默认的最小宽度
    }

    public PasswordDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected PasswordDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        private Context context;
        private
        @android.support.annotation.LayoutRes
        int LayoutRes;
        private View view;
        private TextView titleTv;
        private TextView conformTv;
        private TextView cancelTv;
        private TextView forgetTv;
        private PasswordEditText passwordEditText;
        private String title;
        private String cancleStr;
        private String confirmStr;
        private String forgetStr;
        private CancleClickListener cancleClickListener;
        private ConfirmClickListener confirmClickListener;
        private ForgetClickListener forgetClickListener;

        public Builder(Context context, @android.support.annotation.LayoutRes int layoutRes) {
            this(context, LayoutInflater.from(context).inflate(layoutRes, null));
        }

        public Builder(Context context, View contentView) {
            this.context = context;
            view = contentView;
            cancleStr = context.getResources().getString(R.string.cancel);//默认为取消
            confirmStr = context.getResources().getString(R.string.confirm);//默认为确认
            forgetStr = context.getResources().getString(R.string.forget_password);//默认忘记密码
        }

        public Builder setTitleRes(@IdRes int titleRes) {
            if (view != null) {
                titleTv = (TextView) view.findViewById(titleRes);
            }
            return this;
        }

        public Builder setTitle(@StringRes int StringRes) {
            if (StringRes != 0)
                title = context.getResources().getString(StringRes);
            return this;
        }

        public Builder setPasswordEditText(@IdRes int passwordEtRes, PasswordEditText.OnInputFinishListener onInputFinishListener) {
            if (view != null) {
                passwordEditText = (PasswordEditText) view.findViewById(passwordEtRes);
                passwordEditText.setOnInputFinishListener(onInputFinishListener);
            }
            return this;
        }

        public Builder setConfirmRes(@IdRes int confirmRes) {
            if (view != null) {
                conformTv = (TextView) view.findViewById(confirmRes);
            }
            return this;
        }

        public Builder setCancleRes(@IdRes int cancelRes) {
            if (view != null) {
                cancelTv = (TextView) view.findViewById(cancelRes);
            }
            return this;
        }

        public Builder setForgetRes(@IdRes int forgetRes) {
            if (forgetRes != 0)
                forgetTv = (TextView) view.findViewById(forgetRes);
            return this;
        }

        public Builder setForgetStr(@StringRes int strRes) {
            forgetStr = context.getString(strRes);
            return this;
        }

        public Builder setConfirmStr(@StringRes int strRes) {
            if (strRes != 0)
                confirmStr = context.getResources().getString(strRes);
            return this;
        }

        public Builder setCancleStr(@StringRes int strRes) {
            if (strRes != 0)
                cancleStr = context.getResources().getString(strRes);
            return this;
        }

        public Builder setCancleClickListener(CancleClickListener onClickListener) {
            if (onClickListener != null)
                cancleClickListener = onClickListener;
            return this;
        }

        public Builder setConfirmClickListener(ConfirmClickListener onClickListener) {
            if (onClickListener != null)
                confirmClickListener = onClickListener;
            return this;
        }

        public Builder setForgetClickListener(ForgetClickListener onClickListener) {
            if (onClickListener != null)
                forgetClickListener = onClickListener;
            return this;
        }

        public Dialog build() {
            final PasswordDialog passwordDialog = new PasswordDialog(context);
            passwordDialog.setCanceledOnTouchOutside(false);
            passwordDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            if (view != null)
                passwordDialog.setContentView(view);
            if (passwordEditText != null) {
                passwordEditText.setFocusable(true);// 这个很重要，如果没设置，onKey执行不到
                passwordEditText.setFocusableInTouchMode(true);
                passwordEditText.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            passwordDialog.dismiss();
                            return true;
                        }
                        return false;
                    }
                });
            }
            if (titleTv != null && !TextUtils.isEmpty(title)) {
                titleTv.setText(title);
            }
            if (cancelTv != null) {
                cancelTv.setText(cancleStr);
                if (cancleClickListener != null) {
                    cancelTv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            passwordDialog.dismiss();
                            if (cancleClickListener != null)
                                cancleClickListener.onClick(v);

                        }
                    });
                }
            }
            if (conformTv != null) {
                conformTv.setText(confirmStr);
                if (confirmClickListener != null) {
                    conformTv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            passwordDialog.dismiss();
                            if (confirmClickListener != null)
                                confirmClickListener.onClick(v);
                        }
                    });
                }
            }
            if (forgetTv != null) {
                forgetTv.setText(forgetStr);
                if (forgetClickListener != null) {
                    forgetTv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            passwordDialog.dismiss();
                            if (forgetClickListener != null)
                                forgetClickListener.onClick(v);

                        }
                    });
                }
            }
            return passwordDialog;
        }
    }

    public interface CancleClickListener {
        public void onClick(View v);
    }

    public interface ConfirmClickListener {
        public void onClick(View v);
    }

    public interface ForgetClickListener {
        public void onClick(View v);
    }

    @Override
    public void dismiss() {
        if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        super.dismiss();
    }

    @Override
    public void show() {
        super.show();
    }
}
