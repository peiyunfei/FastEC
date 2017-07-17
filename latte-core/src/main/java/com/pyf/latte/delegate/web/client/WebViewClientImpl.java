package com.pyf.latte.delegate.web.client;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.pyf.latte.delegate.web.WebDelegate;
import com.pyf.latte.delegate.web.route.Route;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/17
 */
public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    /**
     * 在点击请求的是连接是才会调用，重写此方法返回true表明点击网页里面的链接还是在当前的webView里跳转，
     * 不跳到浏览器里边
     */
    @SuppressWarnings("deprecation")
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return Route.getInstance().handleWebUrl(DELEGATE, url);
    }
}
