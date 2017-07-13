package com.pyf.latte.app;

/**
 * 检查用户是否登录的接口
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/13
 */
public interface IUserCheck {

    /**
     * 用户登录回调
     */
    void onSignIn();

    /**
     * 用户没有登录回调
     */
    void onNotSignIn();
}
