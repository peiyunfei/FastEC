package com.pyf.fastec.delegate;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.pyf.fastec.R;
import com.pyf.latte.delegate.LatteDelegate;
import com.pyf.latte.net.RestClient;
import com.pyf.latte.net.callback.IError;
import com.pyf.latte.net.callback.IFailure;
import com.pyf.latte.net.callback.ISuccess;

/**
 * 用于测试框架的fragment
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/10
 */
public class ExampleDelegate extends LatteDelegate {

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    private void testRestClient() {
        RestClient.builder()
                .url("http://news.baidu.com/")
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();
    }
}
