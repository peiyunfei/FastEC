package com.pyf.latte.app;

/**
 * 配置信息的类型（有哪些配置信息），包括域名、上下文、字体等，使用枚举来实现单例
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/9
 */
public enum ConfigType {
    // 域名
    API_HOST,
    // 上下文
    APPLICATION_CONTEXT,
    // 是否初始化完成
    CONFIG_READY,
    // 字体
    ICON,
    // 延迟加载
    LOADER_DELAYED,
    // 拦截器
    INTERCEPTOR

}
