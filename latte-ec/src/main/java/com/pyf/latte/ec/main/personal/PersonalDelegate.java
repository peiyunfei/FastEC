package com.pyf.latte.ec.main.personal;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pyf.latte.delegate.bottom.BottomItemDelegate;
import com.pyf.latte.ec.R;
import com.pyf.latte.ec.R2;
import com.pyf.latte.ec.main.personal.list.ListAdapter;
import com.pyf.latte.ec.main.personal.list.ListBean;
import com.pyf.latte.ui.recycler.ItemType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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
