package com.pyf.latte.delegate.web.client;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.pyf.latte.app.ConfigType;
import com.pyf.latte.app.Latte;
import com.pyf.latte.delegate.web.WebDelegate;
import com.pyf.latte.delegate.web.route.Route;
import com.pyf.latte.ui.loader.LatterLoader;
import com.pyf.latte.utils.storage.LattePreference;

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
     * 同步cookie
     */
    private void syncCookie() {
        CookieManager manager = CookieManager.getInstance();
        String webHost = Latte.getConfiguration(ConfigType.WEB_HOST);
        if (!TextUtils.isEmpty(webHost)) {
            if (manager.hasCookies()) {
                String cookie = manager.getCookie(webHost);
                if (!TextUtils.isEmpty(cookie)) {
                    LattePreference.addCustomAppProfile("cookie", cookie);
                }
            }
        }
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

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        LatterLoader.showLoading(DELEGATE.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        syncCookie();
        LatterLoader.stopLoading();
    }
}
