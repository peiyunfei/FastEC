package com.pyf.latte.net;

import android.content.Context;

import com.pyf.latte.net.callback.IError;
import com.pyf.latte.net.callback.IFailure;
import com.pyf.latte.net.callback.IRequest;
import com.pyf.latte.net.callback.ISuccess;
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
public class RestClientBuilder {

    // 网络访问地址
    private String mUrl;
    // 文件下载路径
    private String mDownloadDir;
    // 文件扩展名
    private String mExtension;
    // 文件名
    private String mName;
    // 存储请求参数的集合
    private WeakHashMap<String, Object> mParams;
    // 请求错误
    private IError mIError;
    // 请求失败
    private IFailure mIFailure;
    // 请求成功
    private ISuccess mISuccess;
    // 开始访问网络和结束访问网络
    private IRequest mIRequest;
    // 请求体
    private RequestBody mBody;
    // 上下文
    private Context mContext;
    // 加载进度条的样式
    private LoaderStyle mLoaderStyle;
    // 文件
    private File mFile;

    RestClientBuilder() {
        mParams = new WeakHashMap<>();
    }

    /**
     * 设置网络访问地址
     *
     * @param url
     *         网络访问地址
     */
    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    /**
     * 设置下载目录
     *
     * @param downloadDir
     *         下载目录
     */
    public final RestClientBuilder downloadDir(String downloadDir) {
        this.mDownloadDir = downloadDir;
        return this;
    }

    /**
     * 设置文件扩展名
     *
     * @param extension
     *         文件扩展名
     */
    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    /**
     * 设置文件名
     *
     * @param name
     *         文件名
     */
    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    /**
     * 设置请求参数
     *
     * @param params
     *         请求参数
     */
    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        this.mParams.putAll(params);
        return this;
    }

    /**
     * 设置请求参数
     *
     * @param key
     *         键
     * @param value
     *         值
     */
    public final RestClientBuilder params(String key, Object value) {
        mParams.put(key, value);
        return this;
    }

    /**
     * 设置请求错误回调接口
     *
     * @param iError
     *         请求错误回调接口
     */
    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    /**
     * 设置请求失败回调接口
     *
     * @param iFailure
     *         请求失败回调接口
     */
    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    /**
     * 设置请求成功回调接口
     *
     * @param iSuccess
     *         请求成功回调接口
     */
    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    /**
     * 设置开始请求和结束请求接口
     *
     * @param iRequest
     *         开始请求和结束请求接口
     */
    public final RestClientBuilder request(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    /**
     * 设置原始请求
     *
     * @param raw
     *         原始请求
     */
    public final RestClientBuilder raw(String raw) {
        mBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), raw);
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
    public final RestClientBuilder loader(Context context, LoaderStyle loaderStyle) {
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
    public final RestClientBuilder loader(Context context) {
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
    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    /**
     * 通过文件所在路径创建file实例
     *
     * @param file
     *         文件所在路径
     */
    public final RestClientBuilder file(String file) {
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
    public final RestClientBuilder file(File dir, String fileName) {
        mFile = new File(dir, fileName);
        return this;
    }

    public RestClient build() {
        return new RestClient(
                mUrl,
                mDownloadDir,
                mExtension,
                mName,
                mParams,
                mIError,
                mIFailure,
                mISuccess,
                mIRequest,
                mBody,
                mContext,
                mLoaderStyle,
                mFile);
    }
}
