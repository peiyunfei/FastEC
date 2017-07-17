package com.pyf.latte.delegate.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.pyf.latte.delegate.web.event.Event;
import com.pyf.latte.delegate.web.event.EventManager;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/17
 */
public class LatteWebInterface {

    private final WebDelegate DELEGATE;

    public LatteWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static LatteWebInterface create(WebDelegate delegate) {
        return new LatteWebInterface(delegate);
    }

    @JavascriptInterface
    public String event(String params) {
        String action = JSON.parseObject(params).getString("action");
        Event event = EventManager.getInstance().getEvent(action);
        if (event != null) {
            event.setAction(action);
            event.setContext(DELEGATE.getContext());
            event.setDelegate(DELEGATE);
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }
}
