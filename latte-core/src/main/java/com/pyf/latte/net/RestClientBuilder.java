package com.pyf.latte.net;

import android.content.Context;

import com.pyf.latte.net.callback.IError;
import com.pyf.latte.net.callback.IFailure;
import com.pyf.latte.net.callback.IRequest;
import com.pyf.latte.net.callback.ISuccess;
import com.pyf.latte.ui.loader.LoaderStyle;

import java.util.Map;
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

    private String mUrl;
    private WeakHashMap<String, Object> mParams;
    private IError mIError;
    private IFailure mIFailure;
    private ISuccess mISuccess;
    private IRequest mIRequest;
    private RequestBody mBody;
    private Context mContext;
    private LoaderStyle mLoaderStyle;

    RestClientBuilder() {
        mParams = RestCreator.getParams();
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(Map<String, Object> params) {
        this.mParams.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        mParams.put(key, value);
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder request(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        mBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), raw);
        return this;
    }

    public final RestClientBuilder loader(Context context, LoaderStyle loaderStyle) {
        mContext = context;
        mLoaderStyle = loaderStyle;
        return this;
    }

    public final RestClientBuilder loader(Context context) {
        mContext = context;
        mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public RestClient build() {
        return new RestClient(mUrl, mParams, mIError, mIFailure,
                mISuccess, mIRequest, mBody, mContext, mLoaderStyle);
    }
}
