package com.pyf.latte.app;

import android.content.Context;

/**
 * 用于初始化全局信息的类。一般情况下，我们会在application中初始化一些全局信息，但这样会使application中的
 * 代码非常多。为了减少application中的代码量，我们创建一个用于初始化全局信息的类。
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/9
 */
public final class Latte {

    /**
     * 初始化全局信息
     *
     * @param context
     *         上下文
     */
    public static Configurator init(Context context) {
        getConfigurator().getLatteConfigs().put(ConfigType.APPLICATION_CONTEXT, context);
        return getConfigurator();
    }

    /**
     * 获取Configurator实例
     *
     * @return Configurator实例
     */
    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    /**
     * 获取上下文
     *
     * @return 上下文
     */
    public static Context getApplicationContext() {
        return getConfigurator().
                getConfiguration(ConfigType.APPLICATION_CONTEXT);
    }

}
