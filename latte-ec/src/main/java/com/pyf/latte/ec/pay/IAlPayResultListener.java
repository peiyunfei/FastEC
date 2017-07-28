package com.pyf.latte.ec.pay;

/**
 * 支付接口
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/28
 */
public interface IAlPayResultListener {

    /**
     * 支付成功
     */
    void onPaySuccess();

    /**
     * 支付失败
     */
    void onPayFail();

    /**
     * 支付中
     */
    void onPaying();

    /**
     * 取消支付
     */
    void onPayCancel();

    /**
     * 连接失败
     */
    void onPayConnectError();

}
