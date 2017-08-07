package com.pyf.latte.ec.main.personal.order;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

/**
 * 订单列表的点击事件
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/8/5
 */
public class OrderListClickListener extends SimpleClickListener {

    private final OrderListDelegate DELEGATE;

    public OrderListClickListener(OrderListDelegate delegate) {
        DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        DELEGATE.getSupportDelegate().start(new OrderCommentDelegate());
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
