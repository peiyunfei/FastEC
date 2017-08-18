package com.pyf.latte.ec.main.index.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pyf.latte.delegate.LatteDelegate;
import com.pyf.latte.ec.R;
import com.pyf.latte.ec.R2;

import butterknife.BindView;

/**
 * 商品信息界面
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/8/17
 */
public class GoodsInfoDelegate extends LatteDelegate {

    @BindView(R2.id.tv_goods_info_name)
    AppCompatTextView mTvGoodsInfoName;
    @BindView(R2.id.tv_goods_info_desc)
    AppCompatTextView mTvGoodsInfoDesc;
    @BindView(R2.id.tv_goods_info_price)
    AppCompatTextView mTvGoodsInfoPrice;
    private static final String GOODS_INFO = "goods_info";
    private JSONObject mInfo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        String jsonData = bundle.getString(GOODS_INFO);
        mInfo = JSON.parseObject(jsonData);
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        String name = mInfo.getString("name");
        String description = mInfo.getString("description");
        double price = mInfo.getDouble("price");
        mTvGoodsInfoName.setText(name);
        mTvGoodsInfoDesc.setText(description);
        mTvGoodsInfoPrice.setText(String.valueOf(price));
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_info;
    }

    public static GoodsInfoDelegate create(String jsonData) {
        GoodsInfoDelegate delegate = new GoodsInfoDelegate();
        Bundle bundle = new Bundle();
        bundle.putString(GOODS_INFO, jsonData);
        delegate.setArguments(bundle);
        return delegate;
    }
}
