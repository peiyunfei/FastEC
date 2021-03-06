package com.pyf.fastec;

import android.app.Application;
import android.support.annotation.Nullable;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.mob.MobSDK;
import com.pyf.fastec.event.ShareEvent;
import com.pyf.fastec.event.TestEvent;
import com.pyf.latte.app.Latte;
import com.pyf.latte.ec.db.DatabaseManager;
import com.pyf.latte.ec.icon.FontEcModule;
import com.pyf.latte.net.interceptors.AddCookieInterceptor;
import com.pyf.latte.utils.callback.CallbackManager;
import com.pyf.latte.utils.callback.CallbackType;
import com.pyf.latte.utils.callback.IGlobalCallback;

import cn.jpush.android.api.JPushInterface;

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
        // 初始化全局参数
        Latte.init(this)
                // 初始化访问网络的域名
                .withApiHost("http://192.168.1.228:8080/Ec/")
                .withWebHost("http://www.baidu.com")
                // 初始化阿里巴巴矢量字体图标库
                .withIcon(new FontEcModule())
                // 初始化字体图标库
                .withIcon(new FontAwesomeModule())
                .withWeChatAppId("wxfcdcecd9df8e0faa")
                .withWeChatAppSecret("a0560f75335b06e3ebea70f29ff219bf")
                // 初始化延迟加载时间
                .withLoaderDelayed(1000)
                // 初始化cookie拦截器
                .withInterceptor(new AddCookieInterceptor())
                .withJavaScriptInterface("latte")
                .withWebEvent("test", new TestEvent())
                .withWebEvent("share", new ShareEvent())
                // 初始化完成
                .configure();
        MobSDK.init(this);
        // 初始化greenDao
        DatabaseManager.getInstance().init(this);
        // 初始化极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        // 打开推送
        CallbackManager.getInstance().addCallback(CallbackType.TAG_OPEN_PUSH, new IGlobalCallback() {
            @Override
            public void executeCallback(@Nullable Object args) {
                if (JPushInterface.isPushStopped(Latte.getApplicationContext())) {
                    JPushInterface.setDebugMode(true);
                    JPushInterface.init(Latte.getApplicationContext());
                }
            }
        });
        // 关闭推送
        CallbackManager.getInstance().addCallback(CallbackType.TAG_STOP_PUSH, new IGlobalCallback() {
            @Override
            public void executeCallback(@Nullable Object args) {
                if (!JPushInterface.isPushStopped(Latte.getApplicationContext())) {
                    JPushInterface.stopPush(Latte.getApplicationContext());
                }
            }
        });
    }
}
