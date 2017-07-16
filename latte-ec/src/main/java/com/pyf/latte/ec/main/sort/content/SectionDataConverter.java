package com.pyf.latte.ec.main.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/16
 */
public class SectionDataConverter {

    public List<SectionBean> converter(String json) {
        List<SectionBean> data = new ArrayList<>();
        JSONArray dataArray = JSON.parseObject(json).getJSONArray("data");
        int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            JSONObject jsonObject = dataArray.getJSONObject(i);
            Integer id = jsonObject.getInteger("id");
            String section = jsonObject.getString("section");
            SectionBean sectionTitleBean = new SectionBean(true, section);
            sectionTitleBean.setId(id);
            sectionTitleBean.setMore(true);
            // 添加标题
            data.add(sectionTitleBean);
            JSONArray goodsArray = jsonObject.getJSONArray("goods");
            int goodsSize = goodsArray.size();
            for (int j = 0; j < goodsSize; j++) {
                JSONObject goodsObject = goodsArray.getJSONObject(j);
                String goodsThumb = goodsObject.getString("goods_thumb");
                String goodsName = goodsObject.getString("goods_name");
                int goodsId = goodsObject.getInteger("goods_id");
                SectionContentItemEntity sectionContentItemEntity = new SectionContentItemEntity
                        (goodsId, goodsName, goodsThumb);
                SectionBean sectionBean = new SectionBean(sectionContentItemEntity);
                data.add(sectionBean);
            }
        }
        return data;
    }
}
