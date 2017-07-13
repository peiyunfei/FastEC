package com.pyf.latte.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

import com.pyf.latte.delegate.LatteDelegate;
import com.pyf.latte.ec.R;
import com.pyf.latte.ec.R2;
import com.pyf.latte.net.RestClient;
import com.pyf.latte.net.callback.IError;
import com.pyf.latte.net.callback.IFailure;
import com.pyf.latte.net.callback.ISuccess;
import com.pyf.latte.utils.log.LatteLogger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册界面
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/12
 */
public class SignUpDelegate extends LatteDelegate {

    @BindView(R2.id.et_sign_up_name)
    TextInputEditText mEtSignUpName;
    @BindView(R2.id.et_sign_up_address)
    TextInputEditText mEtSignUpAddress;
    @BindView(R2.id.et_sign_up_phone)
    TextInputEditText mEtSignUpPhone;
    @BindView(R2.id.et_sign_up_password)
    TextInputEditText mEtSignUpPassword;
    @BindView(R2.id.et_sign_up_re_password)
    TextInputEditText mEtSignUpRePassword;
    private String mName;
    private String mAddress;
    private String mPhone;

    private ISIgnListener mISIgnListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISIgnListener) {
            mISIgnListener = (ISIgnListener) activity;
        }
    }

    /**
     * 注册
     */
    @OnClick(R2.id.btn_sign_up)
    void signUp() {
        if (checkForm()) {
            RestClient.builder()
                    .url("http://192.168.1.229:8080/Ec/SignUpServlet")
                    .params("name", mName)
                    .params("address", mAddress)
                    .params("phone", mPhone)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LatteLogger.json("USER_PROFILE", response);
                            SignHandler.onSignUp(response, mISIgnListener);
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
     * 跳转到登录界面
     */
    @OnClick(R2.id.tv_link_sign_in)
    void signIn() {
        start(new SignInDelegate());
    }

    /**
     * 检验表单
     */
    private boolean checkForm() {
        mName = mEtSignUpName.getText().toString();
        mAddress = mEtSignUpAddress.getText().toString();
        mPhone = mEtSignUpPhone.getText().toString();
        String password = mEtSignUpPassword.getText().toString();
        String rePassword = mEtSignUpRePassword.getText().toString();
        boolean isPass = true;
        if (TextUtils.isEmpty(mName)) {
            mEtSignUpName.setError("请输入姓名");
            isPass = false;
        } else {
            mEtSignUpName.setError(null);
        }

        if (TextUtils.isEmpty(mAddress) || !Patterns.EMAIL_ADDRESS.matcher(mAddress).matches()) {
            mEtSignUpAddress.setError("请输入正确的邮箱");
            isPass = false;
        } else {
            mEtSignUpAddress.setError(null);
        }

        if (TextUtils.isEmpty(mPhone) || mPhone.length() != 11) {
            mEtSignUpPhone.setError("请输入正确的手机号码");
            isPass = false;
        } else {
            mEtSignUpPhone.setError(null);
        }

        if (TextUtils.isEmpty(password) || password.length() < 6) {
            mEtSignUpPassword.setError("请输入至少6位数的密码");
            isPass = false;
        } else {
            mEtSignUpPassword.setError(null);
        }

        if (TextUtils.isEmpty(rePassword) || rePassword.length() < 6 || !rePassword.equals(password)) {
            mEtSignUpRePassword.setError("密码验证错误");
            isPass = false;
        } else {
            mEtSignUpRePassword.setError(null);
        }

        return isPass;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

}
