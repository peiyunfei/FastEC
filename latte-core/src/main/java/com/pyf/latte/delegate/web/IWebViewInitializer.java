package com.pyf.latte.delegate.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 测试webView的接口
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/17
 */
public interface IWebViewInitializer {

    WebView initWebView(WebView webView);

    WebViewClient initWebViewClient();

    WebChromeClient initWebChromeClient();
}
