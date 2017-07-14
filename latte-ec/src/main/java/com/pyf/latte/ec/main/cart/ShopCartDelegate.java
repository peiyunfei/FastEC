package com.pyf.latte.ec.main.cart;

import android.os.Bundle;
import android.view.View;

import com.pyf.latte.delegate.bottom.BottomItemDelegate;
import com.pyf.latte.ec.R;

/**
 * 购物车界面
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/14
 */

public class ShopCartDelegate extends BottomItemDelegate {
    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }
}
