package com.pyf.latte.net.callback;

/**
 * 网络访问成功接口
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/10
 */
public interface ISuccess {

    /**
     * 当网络访问成功时回调
     *
     * @param response
     *         服务器返回的信息
     */
    void onSuccess(String response);
}
