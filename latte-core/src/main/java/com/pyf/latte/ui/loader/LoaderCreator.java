package com.pyf.latte.ui.loader;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * 创建加载进度条
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/11
 */
public class LoaderCreator {

    private static final WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();

    /**
     * 创建AVLoadingIndicatorView，使用内存缓存，减少反射的次数
     *
     * @param context
     *         上下文
     * @param type
     *         包名
     */
    public static AVLoadingIndicatorView create(Context context, String type) {
        AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if (LOADING_MAP.get(type) == null) {
            final Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type, indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }

    private static Indicator getIndicator(String type) {
        if (type == null || type.isEmpty()) {
            return null;
        }
        StringBuilder defaultClassName = new StringBuilder();
        if (!type.contains(".")) {
            String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();
            defaultClassName.append(defaultPackageName)
                    .append(".indicators")
                    .append(".");
        }
        defaultClassName.append(type);
        try {
            Class<?> drawableClass = Class.forName(defaultClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
