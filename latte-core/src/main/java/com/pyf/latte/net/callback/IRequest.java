package com.pyf.latte.net.callback;

/**
 * 开始访问网络和结束访问网络接口
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/10
 */
public interface IRequest {

    /**
     * 开始访问网络
     */
    void onRequestStart();

    /**
     * 网络访问结束
     */
    void onRequestEnd();
}
