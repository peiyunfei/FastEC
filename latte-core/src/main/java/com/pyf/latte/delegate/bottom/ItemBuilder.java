package com.pyf.latte.delegate.bottom;

import java.util.LinkedHashMap;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/14
 */
public class ItemBuilder {

    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    public static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public ItemBuilder addItem(BottomTabBean bottomTabBean, BottomItemDelegate bottomItemDelegate) {
        ITEMS.put(bottomTabBean, bottomItemDelegate);
        return this;
    }

    public ItemBuilder addItems(LinkedHashMap<BottomTabBean, BottomItemDelegate> items) {
        ITEMS.putAll(items);
        return this;
    }

    public LinkedHashMap<BottomTabBean, BottomItemDelegate> build() {
        return ITEMS;
    }
}
