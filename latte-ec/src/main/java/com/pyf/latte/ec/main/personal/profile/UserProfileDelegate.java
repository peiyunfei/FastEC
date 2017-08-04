package com.pyf.latte.ec.main.personal.profile;

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
 * 用户信息界面
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/30
 */
public class UserProfileDelegate extends LatteDelegate {

    @BindView(R2.id.rv_user_profile)
    RecyclerView mRvUserProfile;

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        ListBean avatar = new ListBean.Builder()
                .setItemType(ItemType.ITEM_AVATAR)
                .setId(1)
                .setImageUrl("http://img.mukewang.com/user/57ce4a780001169b02790279-100-100.jpg")
                .build();
        ListBean name = new ListBean.Builder()
                .setItemType(ItemType.ITEM_NORMAL)
                .setId(2)
                .setText("姓名")
                .setDelegate(new NameDelegate())
                .setValue("")
                .build();
        ListBean gender = new ListBean.Builder()
                .setItemType(ItemType.ITEM_NORMAL)
                .setId(3)
                .setText("性别")
                .setValue("")
                .build();
        ListBean birthday = new ListBean.Builder()
                .setItemType(ItemType.ITEM_NORMAL)
                .setId(4)
                .setText("生日")
                .setValue("")
                .build();
        ListBean address = new ListBean.Builder()
                .setItemType(ItemType.ITEM_NORMAL)
                .setId(5)
                .setText("所在地")
                .setValue("")
                .build();
        List<ListBean> list = new ArrayList<>();
        list.add(avatar);
        list.add(name);
        list.add(gender);
        list.add(birthday);
        list.add(address);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        ListAdapter adapter = new ListAdapter(list);
        mRvUserProfile.setLayoutManager(manager);
        mRvUserProfile.setAdapter(adapter);
        mRvUserProfile.addOnItemTouchListener(new UserProfileClickListener(this));
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_user_profile;
    }
}
