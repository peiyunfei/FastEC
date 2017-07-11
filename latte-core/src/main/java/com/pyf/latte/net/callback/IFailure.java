package com.pyf.latte.net.callback;

/**
 * 网络访问失败接口
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/10
 */
public interface IFailure {

    /**
     * 当网络访问失败时回调
     */
    void onFailure();
}
