package com.pyf.latte.ec.main.index;

import android.os.Bundle;
import android.view.View;

import com.pyf.latte.delegate.bottom.BottomItemDelegate;
import com.pyf.latte.ec.R;

/**
 * 首页界面
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/14
 */
public class IndexDelegate extends BottomItemDelegate {

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }
}
