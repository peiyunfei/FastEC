package com.pyf.latte.ec.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pyf.latte.ui.recycler.DataConverter;
import com.pyf.latte.ui.recycler.ItemType;
import com.pyf.latte.ui.recycler.MultipleFields;
import com.pyf.latte.ui.recycler.MultipleItemEntity;

import java.util.List;

/**
 * 左侧列表数据转换器
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/16
 */
public class VerticalListDataConverter extends DataConverter {

    /**
     * 解析json数据
     *
     * @return 存储左侧列表数据的集合
     */
    @Override
    public List<MultipleItemEntity> convert() {
        JSONObject jsonObject = JSON.parseObject(getJsonData());
        JSONObject dataObject = jsonObject.getJSONObject("data");
        JSONArray list = dataObject.getJSONArray("list");
        int size = list.size();
        for (int i = 0; i < size; i++) {
            JSONObject object = list.getJSONObject(i);
            int id = object.getInteger("id");
            String name = object.getString("name");
            MultipleItemEntity multipleItemEntity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.NAME, name)
                    .setField(MultipleFields.TAG, false)
                    .build();
            ENTITIES.add(multipleItemEntity);
        }
        // 默认选中第一个
        ENTITIES.get(0).setField(MultipleFields.TAG, true);
        return ENTITIES;
    }
}
