package com.pyf.latte.ec.main.personal.list;

import android.text.TextUtils;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.pyf.latte.delegate.LatteDelegate;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/30
 */
public class ListBean implements MultiItemEntity {

    private int itemType;
    private String imageUrl;
    private String text;
    private String value;
    private int id;
    private LatteDelegate delegate;
    private CompoundButton.OnCheckedChangeListener listener;

    public ListBean(int itemType, String imageUrl,
                    String text, String value,
                    int id, LatteDelegate delegate,
                    CompoundButton.OnCheckedChangeListener listener) {
        this.itemType = itemType;
        this.imageUrl = imageUrl;
        this.text = text;
        this.value = value;
        this.id = id;
        this.delegate = delegate;
        this.listener = listener;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if (TextUtils.isEmpty(text)) {
            text = "";
        }
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        if (TextUtils.isEmpty(value)) {
            value = "";
        }
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LatteDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(LatteDelegate delegate) {
        this.delegate = delegate;
    }

    public CompoundButton.OnCheckedChangeListener getListener() {
        return listener;
    }

    public void setListener(CompoundButton.OnCheckedChangeListener listener) {
        this.listener = listener;
    }

    public static class Builder {

        private int itemType;
        private String imageUrl;
        private String text;
        private String value;
        private int id;
        private LatteDelegate delegate;
        private CompoundButton.OnCheckedChangeListener listener;

        public Builder setItemType(int itemType) {
            this.itemType = itemType;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setDelegate(LatteDelegate delegate) {
            this.delegate = delegate;
            return this;
        }

        public Builder setListener(CompoundButton.OnCheckedChangeListener listener) {
            this.listener = listener;
            return this;
        }

        public ListBean build() {
            return new ListBean(itemType, imageUrl, text, value, id, delegate, listener);
        }
    }
}
