package com.pyf.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pyf.latte.app.AccountManager;
import com.pyf.latte.ec.db.DatabaseManager;
import com.pyf.latte.ec.db.UserProfile;

/**
 * 处理登录注册
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/12
 */
public class SignHandler {

    /**
     * 注册成功后的处理
     */
    public static void onSignUp(String response, ISIgnListener ISIgnListener) {
        JSONObject jsonObject = JSON.parseObject(response).getJSONObject("data");
        long userId = jsonObject.getLong("userId");
        String name = jsonObject.getString("name");
        String avatar = jsonObject.getString("avatar");
        String gender = jsonObject.getString("gender");
        String address = jsonObject.getString("address");
        UserProfile userProfile = new UserProfile(userId, name,
                avatar, gender, address);
        // 添加到数据库
        DatabaseManager.getInstance().getUserProfileDao().insert(userProfile);
        // 设置登录成功状态
        AccountManager.setSignState(true);
        // 注册成功
        ISIgnListener.onSignUpSuccess();
    }

    /**
     * 登录成功后的处理
     */
    public static void onSignIn(String response, ISIgnListener ISIgnListener) {
        JSONObject jsonObject = JSON.parseObject(response).getJSONObject("data");
        long userId = jsonObject.getLong("userId");
        String name = jsonObject.getString("name");
        String avatar = jsonObject.getString("avatar");
        String gender = jsonObject.getString("gender");
        String address = jsonObject.getString("address");
        UserProfile userProfile = new UserProfile(userId, name,
                avatar, gender, address);
        // 添加到数据库
        DatabaseManager.getInstance().getUserProfileDao().insert(userProfile);
        // 设置登录成功状态
        AccountManager.setSignState(true);
        // 登录成功
        ISIgnListener.onSignInSuccess();
    }
}
