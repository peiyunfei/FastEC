package com.pyf.latte.ec.main.personal.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pyf.latte.delegate.LatteDelegate;
import com.pyf.latte.ec.R;
import com.pyf.latte.ec.R2;
import com.pyf.latte.ec.main.personal.list.ListAdapter;
import com.pyf.latte.ec.main.personal.list.ListBean;
import com.pyf.latte.ui.recycler.ItemType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/8/3
 */
public class SettingDelegate extends LatteDelegate {

    @BindView(R2.id.rv_system_setting)
    RecyclerView mRvSystemSetting;

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        ListBean push = new ListBean.Builder()
                .setItemType(ItemType.ITEM_SWITCH)
                .setId(1)
                .setText("消息推送")
                .build();
        ListBean about = new ListBean.Builder()
                .setItemType(ItemType.ITEM_NORMAL)
                .setId(2)
                .setText("关于")
                .build();
        List<ListBean> list = new ArrayList<>();
        list.add(push);
        list.add(about);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        ListAdapter adapter = new ListAdapter(list);
        mRvSystemSetting.setLayoutManager(manager);
        mRvSystemSetting.setAdapter(adapter);
        //        mRvSystemSetting.addOnItemTouchListener(new PersonalClickListener(this));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_setting;
    }
}
