package com.pyf.latte.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.pyf.latte.delegate.LatteDelegate;
import com.pyf.latte.ec.R;
import com.pyf.latte.ec.R2;
import com.pyf.latte.net.RestClient;
import com.pyf.latte.net.callback.IError;
import com.pyf.latte.net.callback.IFailure;
import com.pyf.latte.net.callback.ISuccess;
import com.pyf.latte.utils.log.LatteLogger;
import com.pyf.latte.wechat.LatteWeChat;
import com.pyf.latte.wechat.callbacks.IWeChatSignInCallback;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录界面
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/12
 */
public class SignInDelegate extends LatteDelegate {

    @BindView(R2.id.et_sign_in_address)
    TextInputEditText mEtSignInAddress;
    @BindView(R2.id.et_sign_in_password)
    TextInputEditText mEtSignInPassword;

    private ISIgnListener mISIgnListener;
    private String mEmail;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISIgnListener) {
            mISIgnListener = (ISIgnListener) activity;
        }
    }

    /**
     * 登录
     */
    @OnClick(R2.id.btn_sign_in)
    void signIn() {
        if (checkForm()) {
            RestClient.builder()
                    .url("SignUpServlet")
                    .params("address", mEmail)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LatteLogger.json("USER_PROFILE", response);
                            SignHandler.onSignIn(response, mISIgnListener);
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                        }
                    })
                    .build()
                    .post();

        }
    }

    /**
     * 微信登录
     */
    @OnClick(R2.id.icon_sign_in_wechat)
    void wechatSignIn() {
        LatteWeChat
                .getInstance()
                .onSignSuccess(new IWeChatSignInCallback() {
                    @Override
                    public void onSignInSuccess(String userInfo) {
                        Toast.makeText(getContext(), userInfo, Toast.LENGTH_LONG).show();
                    }
                })
                .signIn();
    }

    /**
     * 跳转到注册界面
     */
    @OnClick(R2.id.tv_link_sign_up)
    void signUp() {
        getSupportDelegate().start(new SignUpDelegate());
    }

    /**
     * 检验表单
     */
    private boolean checkForm() {
        mEmail = mEtSignInAddress.getText().toString();
        String password = mEtSignInPassword.getText().toString();
        boolean isPass = true;
        if (TextUtils.isEmpty(mEmail) || !Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()) {
            mEtSignInAddress.setError("请输入正确的邮箱");
            isPass = false;
        } else {
            mEtSignInAddress.setError(null);
        }
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            mEtSignInPassword.setError("请输入至少6位数的密码");
            isPass = false;
        } else {
            mEtSignInPassword.setError(null);
        }
        return isPass;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

}
