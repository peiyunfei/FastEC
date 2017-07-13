package com.pyf.latte.ec.sign;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/13
 */
public interface ISIgnListener {

    /**
     * 注册成功回调
     */
    void onSignUpSuccess();

    /**
     * 登录成功回调
     */
    void onSignInSuccess();
}
