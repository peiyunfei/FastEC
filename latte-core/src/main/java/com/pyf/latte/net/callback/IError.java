package com.pyf.latte.net.callback;

/**
 * 网络访问错误接口
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/10
 */
public interface IError {

    /**
     * 当网络访问错误时回调
     *
     * @param code
     *         错误码
     * @param msg
     *         错误信息
     */
    void onError(int code, String msg);
}
