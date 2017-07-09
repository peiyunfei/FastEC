package com.pyf.fastec;

import android.app.Application;

import com.pyf.latte.app.Latte;

/**
 * 全局应用程序类
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/9
 */
public class AppContext extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        Latte.init(this)
                .withApiHost("")
                .configure();
    }
}
