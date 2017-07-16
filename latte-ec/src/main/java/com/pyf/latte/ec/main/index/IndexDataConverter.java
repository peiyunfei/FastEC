package com.pyf.latte.ec.main.index;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pyf.latte.ui.recycler.DataConverter;
import com.pyf.latte.ui.recycler.ItemType;
import com.pyf.latte.ui.recycler.MultipleFields;
import com.pyf.latte.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/15
 */

public class IndexDataConverter extends DataConverter {

    /**
     * 解析json数据
     *
     * @return 存储首页数据的集合
     */
    @Override
    public List<MultipleItemEntity> convert() {
        JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            JSONObject data = dataArray.getJSONObject(i);
            String text = data.getString("text");
            String imageUrl = data.getString("imageUrl");
            Integer goodsId = data.getInteger("goodsId");
            Integer spanSize = data.getInteger("spanSize");
            JSONArray banners = data.getJSONArray("banners");
            final ArrayList<String> bannerImages = new ArrayList<>();
            int type = 0;
            if (!TextUtils.isEmpty(text) && TextUtils.isEmpty(imageUrl)) {
                type = ItemType.TEXT;
            } else if (TextUtils.isEmpty(text) && !TextUtils.isEmpty(imageUrl)) {
                type = ItemType.IMAGE;
            } else if (!TextUtils.isEmpty(text) && !TextUtils.isEmpty(imageUrl)) {
                type = ItemType.TEXT_IMAGE;
            } else if (banners != null) {
                type = ItemType.BANNER;
                int bannerSize = banners.size();
                for (int j = 0; j < bannerSize; j++) {
                    String banner = banners.getString(j);
                    bannerImages.add(banner);
                }
            }
            MultipleItemEntity multipleItemEntity = MultipleItemEntity
                    .builder()
                    .setField(MultipleFields.ITEM_TYPE, type)
                    .setField(MultipleFields.BANNERS, bannerImages)
                    .setField(MultipleFields.IMAGE_URL, imageUrl)
                    .setField(MultipleFields.SPAN_SIZE, spanSize)
                    .setField(MultipleFields.TEXT, text)
                    .setField(MultipleFields.ID, goodsId)
                    .build();
            ENTITIES.add(multipleItemEntity);

        }
        return ENTITIES;
    }
}
