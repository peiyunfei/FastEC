package com.pyf.latte.ec.main;

import android.graphics.Color;

import com.pyf.latte.delegate.bottom.BaseBottomDelegate;
import com.pyf.latte.delegate.bottom.BottomItemDelegate;
import com.pyf.latte.delegate.bottom.BottomTabBean;
import com.pyf.latte.delegate.bottom.ItemBuilder;
import com.pyf.latte.ec.main.cart.ShopCartDelegate;
import com.pyf.latte.ec.main.discover.DiscoverDelegate;
import com.pyf.latte.ec.main.index.IndexDelegate;
import com.pyf.latte.ec.main.personal.PersonalDelegate;
import com.pyf.latte.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * 底部导航栏
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/14
 */
public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    protected LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        // 添加首页界面
        items.put(new BottomTabBean("{fa-home}", "首页"), new IndexDelegate());
        // 添加分类界面
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        // 添加发现界面
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        // 添加购物车界面
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        // 添加我的界面
        items.put(new BottomTabBean("{fa-user}", "我的"), new PersonalDelegate());
        return items;
    }

    /**
     * 设置按下的颜色
     * @return 按下的颜色
     */
    @Override
    protected int setClickColor() {
        return Color.parseColor("#ffff8800");
    }

    /**
     * 设置默认显示界面的下标
     *
     * @return 默认显示界面的下标
     */
    @Override
    protected int setIndexDelegate() {
        return 0;
    }
}
