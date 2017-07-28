package com.pyf.latte.ec.pay;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.pyf.latte.delegate.LatteDelegate;
import com.pyf.latte.ec.R;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/28
 */
public class FastPay implements View.OnClickListener {

    private AlertDialog mDialog;

    private FastPay(LatteDelegate delegate) {
        mDialog = new AlertDialog.Builder(delegate.getContext()).create();
    }

    public static FastPay create(LatteDelegate delegate) {
        return new FastPay(delegate);
    }

    public void showPayDialog() {
        mDialog.show();
        Window window = mDialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_pay_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(layoutParams);
            window.findViewById(R.id.btn_pay_cancel).setOnClickListener(this);
            window.findViewById(R.id.icon_pay_alipay).setOnClickListener(this);
            window.findViewById(R.id.icon_pay_weixin).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_pay_cancel) {
            mDialog.cancel();
        }
        if (id == R.id.icon_pay_alipay) {
            mDialog.cancel();
        }
        if (id == R.id.icon_pay_weixin) {
            mDialog.cancel();
        }
    }
}
