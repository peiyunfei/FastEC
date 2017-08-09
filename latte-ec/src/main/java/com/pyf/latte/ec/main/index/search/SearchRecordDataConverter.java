package com.pyf.latte.ec.main.index.search;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONArray;
import com.pyf.latte.ui.recycler.DataConverter;
import com.pyf.latte.ui.recycler.ItemType;
import com.pyf.latte.ui.recycler.MultipleFields;
import com.pyf.latte.ui.recycler.MultipleItemEntity;
import com.pyf.latte.utils.storage.LattePreference;

import java.util.List;

/**
 * 搜索记录
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/8/9
 */
public class SearchRecordDataConverter extends DataConverter {

    public static final String SEARCH_HISTORY = "search_history";

    @Override
    public List<MultipleItemEntity> convert() {
        String jsonHistory = LattePreference.getCustomAppProfile(SEARCH_HISTORY);
        if (!TextUtils.isEmpty(jsonHistory)) {
            JSONArray jsonArray = JSONArray.parseArray(jsonHistory);
            int size = jsonArray.size();
            for (int i = 0; i < size; i++) {
                String history = jsonArray.getString(i);
                MultipleItemEntity entity = MultipleItemEntity.builder()
                        .setField(MultipleFields.ITEM_TYPE, ItemType.ITEM_SEARCH_RECORD)
                        .setField(MultipleFields.TEXT, history)
                        .build();
                ENTITIES.add(entity);
            }
        }
        return ENTITIES;
    }
}
