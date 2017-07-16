package com.pyf.latte.ui.recycler;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据转换器
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/15
 */
public abstract class DataConverter {

    protected List<MultipleItemEntity> ENTITIES = new ArrayList<>();
    private String mJsonData;

    public abstract List<MultipleItemEntity> convert();

    public DataConverter setJsonData(String jsonData) {
        this.mJsonData = jsonData;
        return this;
    }

    public String getJsonData() {
        if (TextUtils.isEmpty(mJsonData)) {
            throw new NullPointerException("json data is null");
        }
        return mJsonData;
    }
}
