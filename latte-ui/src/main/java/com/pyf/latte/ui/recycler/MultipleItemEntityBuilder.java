package com.pyf.latte.ui.recycler;

import java.util.LinkedHashMap;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/15
 */

public class MultipleItemEntityBuilder {

    private final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

    public MultipleItemEntityBuilder() {
        FIELDS.clear();
    }

    public final MultipleItemEntityBuilder setField(Object key, Object value) {
        FIELDS.put(key, value);
        return this;
    }

    public final MultipleItemEntityBuilder setFields(LinkedHashMap<Object, Object> fields) {
        FIELDS.putAll(fields);
        return this;
    }

    public final MultipleItemEntity build() {
        return new MultipleItemEntity(FIELDS);
    }
}
