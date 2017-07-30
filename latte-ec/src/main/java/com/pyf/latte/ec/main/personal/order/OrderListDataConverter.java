package com.pyf.latte.ec.main.personal.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pyf.latte.ui.recycler.DataConverter;
import com.pyf.latte.ui.recycler.ItemType;
import com.pyf.latte.ui.recycler.MultipleFields;
import com.pyf.latte.ui.recycler.MultipleItemEntity;

import java.util.List;

/**
 * 订单列表数据
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/30
 */
public class OrderListDataConverter extends DataConverter {

    @Override
    public List<MultipleItemEntity> convert() {
        JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            JSONObject jsonObject = dataArray.getJSONObject(i);
            int id = jsonObject.getInteger("id");
            String thumb = jsonObject.getString("thumb");
            String title = jsonObject.getString("title");
            String price = jsonObject.getString("price");
            String time = jsonObject.getString("time");
            MultipleItemEntity multipleItemEntity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.ORDER_LIST)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.IMAGE_URL, thumb)
                    .setField(MultipleFields.ITEM_PRICE, price)
                    .setField(MultipleFields.TIME, time)
                    .setField(MultipleFields.TITLE, title)
                    .build();
            ENTITIES.add(multipleItemEntity);
        }
        return ENTITIES;
    }
}
