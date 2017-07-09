package com.pyf.latte.app;

import java.util.WeakHashMap;

/**
 * 配置信息的存储和获取，使用静态内部类实现单例模式
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/9
 */
public class Configurator {

    // 用于存储和获取配置信息的集合
    private static final WeakHashMap<Object, Object> LATTE_CONFIGS = new WeakHashMap<>();

    private Configurator() {
        // 默认情况下初始化还没有完成，将value值设置为false
        LATTE_CONFIGS.put(ConfigType.APPLICATION_CONTEXT.name(), false);
    }

    /**
     * 获取本类唯一实例
     *
     * @return 本类唯一实例
     */
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 使用静态内部类实现单例模式
     */
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public final WeakHashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    /**
     * 初始化完成
     */
    public final void configure() {
        // 初始化完成，将value值设置为true
        LATTE_CONFIGS.put(ConfigType.APPLICATION_CONTEXT.name(), true);
    }

    /**
     * 配置访问网络的域名
     *
     * @param host
     *         访问网络的域名
     * @return 本类唯一实例
     */
    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    /**
     * 判断是否初始化完成
     *
     * @return true 初始化完成。 false 初始化还没有完成
     */
    private boolean checkConfigurator() {
        return (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
    }

    /**
     * 根据键来获取对应的配置信息
     *
     * @param key
     *         键
     * @param <T>
     *         泛型
     * @return 对应的配置信息
     */
    @SuppressWarnings("unchecked")
    public final <T> T getConfiguration(String key) {
        if (!checkConfigurator()) {
            throw new RuntimeException("Configuration is not ready, please call configure() method");
        }
        return (T) LATTE_CONFIGS.get(key);
    }
}
