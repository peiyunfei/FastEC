package com.pyf.latte.net.rx;

import android.content.Context;

import com.pyf.latte.ui.loader.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/11
 */
public class RxRestClientBuilder {

    private final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    private String mUrl;
    // 请求体
    private RequestBody mBody;
    // 上下文
    private Context mContext;
    // 加载进度条的样式
    private LoaderStyle mLoaderStyle;
    // 文件
    private File mFile;

    RxRestClientBuilder() {
    }

    /**
     * 设置网络访问地址
     *
     * @param url
     *         网络访问地址
     */
    public final RxRestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    /**
     * 设置原始请求
     *
     * @param raw
     *         原始请求
     */
    public final RxRestClientBuilder raw(String raw) {
        mBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), raw);
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RxRestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    /**
     * 设置加载进度条样式
     *
     * @param context
     *         上下文
     * @param loaderStyle
     *         加载进度条样式
     */
    public final RxRestClientBuilder loader(Context context, LoaderStyle loaderStyle) {
        mContext = context;
        mLoaderStyle = loaderStyle;
        return this;
    }

    /**
     * 设置默认加载进度条样式
     *
     * @param context
     *         上下文
     */
    public final RxRestClientBuilder loader(Context context) {
        mContext = context;
        mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    /**
     * 通过file对象创建file实例
     *
     * @param file
     *         file对象
     */
    public final RxRestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    /**
     * 通过文件所在路径创建file实例
     *
     * @param file
     *         文件所在路径
     */
    public final RxRestClientBuilder file(String file) {
        mFile = new File(file);
        return this;
    }

    /**
     * 创建file实例
     *
     * @param dir
     *         文件所在目录
     * @param fileName
     *         文件名
     */
    public final RxRestClientBuilder file(File dir, String fileName) {
        mFile = new File(dir, fileName);
        return this;
    }

    public RxRestClient build() {
        return new RxRestClient(
                mUrl,
                mBody,
                PARAMS,
                mContext,
                mLoaderStyle,
                mFile);
    }
}
