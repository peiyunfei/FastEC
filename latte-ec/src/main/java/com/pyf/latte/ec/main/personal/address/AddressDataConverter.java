package com.pyf.latte.ec.main.personal.address;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pyf.latte.ui.recycler.DataConverter;
import com.pyf.latte.ui.recycler.ItemType;
import com.pyf.latte.ui.recycler.MultipleFields;
import com.pyf.latte.ui.recycler.MultipleItemEntity;

import java.util.List;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/8/3
 */

public class AddressDataConverter extends DataConverter {

    @Override
    public List<MultipleItemEntity> convert() {
        JSONArray jsonArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Integer id = jsonObject.getInteger("id");
            boolean defaultAddress = jsonObject.getBoolean("default");
            String name = jsonObject.getString("name");
            String phone = jsonObject.getString("phone");
            String address = jsonObject.getString("address");
            MultipleItemEntity multipleItemEntity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.ITEM_ADDRESS)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.NAME, name)
                    .setField(MultipleFields.PHONE, phone)
                    .setField(MultipleFields.ADDRESS, address)
                    .setField(MultipleFields.DEFAULT_ADDRESS, defaultAddress)
                    .build();
            ENTITIES.add(multipleItemEntity);
        }
        return ENTITIES;
    }
}
