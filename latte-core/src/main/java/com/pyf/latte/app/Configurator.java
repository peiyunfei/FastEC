package com.pyf.latte.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Interceptor;

/**
 * 配置信息的存储和获取，使用静态内部类实现单例模式
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/9
 */
public class Configurator {

    // 用于存储和获取配置信息的集合
    private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
    // 存储字体图标库的集合
    private static final List<IconFontDescriptor> ICONS = new ArrayList<>();
    // 存储拦截器的集合
    private static final List<Interceptor> INTERCEPTORS = new ArrayList<>();

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

    public final HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    /**
     * 初始化完成
     */
    public final void configure() {
        // 初始化字体图标库
        initIcons();
        // 初始化完成，将value值设置为true
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
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
     * 添加字体图标库
     *
     * @param descriptor
     *         体图标库
     * @return 本类唯一实例
     */
    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    /**
     * 添加拦截器
     *
     * @param interceptor
     *         拦截器
     * @return 本类唯一实例
     */
    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    /**
     * 添加拦截器
     *
     * @param interceptors
     *         拦截器集合
     * @return 本类唯一实例
     */
    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    /**
     * 初始化延迟加载时间
     *
     * @param delayed
     *         延迟加载时间
     * @return 本类唯一实例
     */
    public final Configurator withLoaderDelayed(long delayed) {
        LATTE_CONFIGS.put(ConfigType.LOADER_DELAYED, delayed);
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
     * 初始化字体图标库
     */
    private void initIcons() {
        if (ICONS.size() > 0) {
            Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
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
    public final <T> T getConfiguration(Object key) {
        if (!checkConfigurator()) {
            throw new RuntimeException("Configuration is not ready, please call configure() method");
        }
        return (T) LATTE_CONFIGS.get(key);
    }
}
