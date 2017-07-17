package com.pyf.latte.delegate.web.event;

import android.content.Context;

import com.pyf.latte.delegate.LatteDelegate;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/17
 */
public abstract class Event implements IEvent {

    private Context mContext;
    private String mAction;
    private LatteDelegate mDelegate;
    private String mUrl;

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String action) {
        mAction = action;
    }

    public LatteDelegate getDelegate() {
        return mDelegate;
    }

    public void setDelegate(LatteDelegate delegate) {
        mDelegate = delegate;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}