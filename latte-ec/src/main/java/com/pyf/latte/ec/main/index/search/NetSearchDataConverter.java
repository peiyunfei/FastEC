package com.pyf.latte.ec.main.index.search;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pyf.latte.ui.recycler.DataConverter;
import com.pyf.latte.ui.recycler.ItemType;
import com.pyf.latte.ui.recycler.MultipleFields;
import com.pyf.latte.ui.recycler.MultipleItemEntity;

import java.util.List;

/**
 * 从服务器搜索
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/8/9
 */
public class NetSearchDataConverter extends DataConverter {

    @Override
    public List<MultipleItemEntity> convert() {
        JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            JSONObject jsonObject = dataArray.getJSONObject(i);
            int id = jsonObject.getInteger("id");
            String thumb = jsonObject.getString("thumb");
            String title = jsonObject.getString("title");
            String desc = jsonObject.getString("desc");
            String time = jsonObject.getString("time");
            MultipleItemEntity multipleItemEntity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.ITEM_NET_SEARCH)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.IMAGE_URL, thumb)
                    .setField(MultipleFields.DESC, desc)
                    .setField(MultipleFields.TIME, time)
                    .setField(MultipleFields.TITLE, title)
                    .build();
            ENTITIES.add(multipleItemEntity);
        }
        return ENTITIES;
    }
}
