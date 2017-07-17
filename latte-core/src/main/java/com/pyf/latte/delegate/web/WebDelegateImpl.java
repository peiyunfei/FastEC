package com.pyf.latte.delegate.web;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.pyf.latte.delegate.web.chromeclient.WebChromeClientImpl;
import com.pyf.latte.delegate.web.client.WebViewClientImpl;
import com.pyf.latte.delegate.web.route.Route;
import com.pyf.latte.delegate.web.route.RouteKeys;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/17
 */
public class WebDelegateImpl extends WebDelegate implements IWebViewInitializer {

    public static WebDelegateImpl create(String url) {
        Bundle bundle = new Bundle();
        bundle.putString(RouteKeys.URL.name(), url);
        WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(bundle);
        return delegate;
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        if (getUrl() != null) {
            // 用原生的方式跳转
            Route.getInstance().loadPage(this, getUrl());
        }
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        WebViewClientImpl webViewClient = new WebViewClientImpl(this);
        return webViewClient;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }

}
