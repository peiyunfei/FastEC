package com.pyf.latte.ec.main.cart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pyf.latte.ui.recycler.DataConverter;
import com.pyf.latte.ui.recycler.ItemType;
import com.pyf.latte.ui.recycler.MultipleFields;
import com.pyf.latte.ui.recycler.MultipleItemEntity;

import java.util.List;

/**
 * 购物车数据
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/26
 */
public class ShopCartConverter extends DataConverter {

    @Override
    public List<MultipleItemEntity> convert() {
        JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            JSONObject data = dataArray.getJSONObject(i);
            String title = data.getString("title");
            String desc = data.getString("desc");
            String thumb = data.getString("thumb");
            Integer id = data.getInteger("id");
            Integer count = data.getInteger("count");
            double price = data.getDouble("price");
            MultipleItemEntity multipleItemEntity = MultipleItemEntity
                    .builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.SHOP_CART)
                    .setField(MultipleFields.TITLE, title)
                    .setField(MultipleFields.IMAGE_URL, thumb)
                    .setField(MultipleFields.DESC, desc)
                    .setField(MultipleFields.COUNT, count)
                    .setField(MultipleFields.ITEM_PRICE, price)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.IS_SELECTED, false)
                    .setField(MultipleFields.POSITION, i)
                    .build();
            ENTITIES.add(multipleItemEntity);
        }
        return ENTITIES;
    }
}
