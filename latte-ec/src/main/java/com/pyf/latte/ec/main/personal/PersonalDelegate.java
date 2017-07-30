package com.pyf.latte.ec.main.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pyf.latte.delegate.bottom.BottomItemDelegate;
import com.pyf.latte.ec.R;
import com.pyf.latte.ec.R2;
import com.pyf.latte.ec.main.personal.list.ListAdapter;
import com.pyf.latte.ec.main.personal.list.ListBean;
import com.pyf.latte.ec.main.personal.order.OrderListDelegate;
import com.pyf.latte.ui.recycler.ItemType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的界面
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/14
 */
public class PersonalDelegate extends BottomItemDelegate {

    @BindView(R2.id.rv_setting)
    RecyclerView mRvSetting;

    public static final String ORDER_TYPE = "ORDER_TYPE";
    private Bundle mArgs;

    @OnClick(R2.id.rl_my_order)
    void onClickMyOrder() {
        mArgs.putString(ORDER_TYPE, "all");
        startOrderListByType();
    }

    @OnClick(R2.id.ll_pay)
    void onClickPay() {
        mArgs.putString(ORDER_TYPE, "pay");
        startOrderListByType();
    }

    @OnClick(R2.id.ll_receive)
    void onClickReceive() {
        mArgs.putString(ORDER_TYPE, "receive");
        startOrderListByType();
    }

    @OnClick(R2.id.ll_evaluate)
    void onClickEvaluate() {
        mArgs.putString(ORDER_TYPE, "evaluate");
        startOrderListByType();
    }

    @OnClick(R2.id.ll_after_market)
    void onClickAfterMarket() {
        mArgs.putString(ORDER_TYPE, "after_market");
        startOrderListByType();
    }

    private void startOrderListByType() {
        OrderListDelegate delegate = new OrderListDelegate();
        delegate.setArguments(mArgs);
        getParentDelegate().getSupportDelegate().start(delegate);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArgs = new Bundle();
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        ListBean address = new ListBean.Builder()
                .setItemType(ItemType.LIST_SETTING)
                .setId(1)
                .setText("收货地址")
                .build();
        ListBean setting = new ListBean.Builder()
                .setItemType(ItemType.LIST_SETTING)
                .setId(1)
                .setText("系统设置")
                .build();
        List<ListBean> list = new ArrayList<>();
        list.add(address);
        list.add(setting);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        ListAdapter adapter = new ListAdapter(list);
        mRvSetting.setLayoutManager(manager);
        mRvSetting.setAdapter(adapter);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }
}
