package com.pyf.latte.net.interceptors;

import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;

/**
 * 拦截器基类，提供一些可供子类重写的方法，子类也可以直接调用
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/11
 */
public abstract class BaseInterceptor implements Interceptor {

    /**
     * 获取get请求的请求参数
     *
     * @param chain
     *         拦截器链
     * @return 存储get请求的请求参数的集合
     */
    protected LinkedHashMap<String, String> getUrlParameters(Chain chain) {
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        HttpUrl url = chain.request().url();
        int size = url.querySize();
        for (int i = 0; i < size; i++) {
            params.put(url.queryParameterName(i), url.queryParameterValue(i));
        }
        return params;
    }

    /**
     * 根据请求参数的键查询get请求的请求参数的值
     *
     * @param chain
     *         拦截器链
     * @param key
     *         请求参数的键
     * @return get请求的请求参数的值
     */
    protected String getUrlParameters(Chain chain, String key) {
        return chain.request().url().queryParameter(key);
    }

    /**
     * 获取post请求的请求参数
     *
     * @param chain
     *         拦截器链
     * @return 存储post请求的请求参数的集合
     */
    protected LinkedHashMap<String, String> getBodyParameters(Chain chain) {
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        FormBody formBody = (FormBody) chain.request().body();
        int size = formBody.size();
        for (int i = 0; i < size; i++) {
            params.put(formBody.name(i), formBody.value(i));
        }
        return params;
    }

    /**
     * 根据请求参数的键查询post请求的请求参数的值
     *
     * @param chain
     *         拦截器链
     * @param key
     *         请求参数的键
     * @return post请求的请求参数的值
     */
    protected String getBodyParameters(Chain chain, String key) {
        return getBodyParameters(chain).get(key);
    }
}
