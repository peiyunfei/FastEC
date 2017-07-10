package com.pyf.fastec.delegate;

import android.os.Bundle;
import android.view.View;

import com.pyf.fastec.R;
import com.pyf.latte.delegate.LatteDelegate;

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

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }
}
