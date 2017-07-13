package com.pyf.latte.app;

import com.pyf.latte.utils.storage.LattePreference;

/**
 * 用户信息管理器
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/13
 */
public class AccountManager {

    private enum SignTag {

        SIGN_TAG;
    }

    /**
     * 设置用户登录状态
     *
     * @param state
     *         登录状态
     */
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    /**
     * 判断用户是否登录
     */
    public static boolean isSign() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserCheck userCheck) {
        if (isSign()) {
            userCheck.onSignIn();
        } else {
            userCheck.onNotSignIn();
        }
    }
}
