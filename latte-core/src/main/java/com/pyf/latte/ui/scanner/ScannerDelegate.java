package com.pyf.latte.ui.scanner;

import android.os.Bundle;
import android.view.View;

import com.pyf.latte.R;
import com.pyf.latte.delegate.LatteDelegate;

/**
 * 二维码扫描急么
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/8/7
 */
public class ScannerDelegate extends LatteDelegate {

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_scanner;
    }
}
