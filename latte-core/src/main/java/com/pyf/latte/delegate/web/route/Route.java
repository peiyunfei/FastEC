package com.pyf.latte.delegate.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.pyf.latte.delegate.LatteDelegate;
import com.pyf.latte.delegate.web.WebDelegateImpl;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/17
 */
public class Route {

    private Route() {
    }

    private static final class Holder {
        private static final Route INSTANCE = new Route();
    }

    public static Route getInstance() {
        return Holder.INSTANCE;
    }

    public boolean handleWebUrl(com.pyf.latte.delegate.web.WebDelegate delegate, String url) {
        if (url.contains("tel:")) {
            callPhone(delegate.getContext(), url);
            return true;
        }
        LatteDelegate topDelegate = delegate.getTopDelegate();
        WebDelegateImpl webDelegate = WebDelegateImpl.create(url);
        topDelegate.getSupportDelegate().start(webDelegate);
        return true;
    }

    public void loadPage(com.pyf.latte.delegate.web.WebDelegate delegate, String url) {
        loadPage(delegate.getWebView(), url);
    }

    /**
     * 加载页面
     */
    private void loadPage(WebView webView, String url) {
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)) {
            loadWebPage(webView, url);
        } else {
            loadLocalPage(webView, url);
        }
    }

    /**
     * 加载本地页面
     */
    private void loadLocalPage(WebView webView, String url) {
        loadWebPage(webView, "file:///android_asset/" + url);
    }

    /**
     * 加载网页
     */
    private void loadWebPage(WebView webView, String url) {
        if (webView != null) {
            webView.loadUrl(url);
        }
    }

    /**
     * 打电话
     */
    private void callPhone(Context context, String uri) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse(uri);
        intent.setData(data);
        ContextCompat.startActivity(context, intent, null);

    }
}
