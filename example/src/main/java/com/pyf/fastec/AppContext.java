package com.pyf.fastec;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.pyf.latte.app.Latte;
import com.pyf.latte.ec.icon.FontEcModule;

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
                // 初始化访问网络的域名
                .withApiHost("http://127.0.0.1/")
                // 初始化阿里巴巴矢量字体图标库
                .withIcon(new FontEcModule())
                // 初始化字体图标库
                .withIcon(new FontAwesomeModule())
                // 初始化完成
                .configure();
    }
}
