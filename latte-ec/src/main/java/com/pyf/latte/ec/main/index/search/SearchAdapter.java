package com.pyf.latte.ec.main.index.search;

import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.pyf.latte.ec.R;
import com.pyf.latte.ui.recycler.ItemType;
import com.pyf.latte.ui.recycler.MultipleAdapter;
import com.pyf.latte.ui.recycler.MultipleFields;
import com.pyf.latte.ui.recycler.MultipleItemEntity;
import com.pyf.latte.ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * 搜索适配器
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/8/9
 */
public class SearchAdapter extends MultipleAdapter {

    // 设置图片加载策略
    private static final RequestOptions REQUEST_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();

    protected SearchAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ItemType.ITEM_SEARCH_RECORD, R.layout.item_search_record);
        addItemType(ItemType.ITEM_NET_SEARCH, R.layout.item_net_search);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        switch (holder.getItemViewType()) {
            case ItemType.ITEM_SEARCH_RECORD:
                holder.setText(R.id.tv_search_text, (CharSequence) entity.getField(MultipleFields.TEXT));
                break;
            case ItemType.ITEM_NET_SEARCH:
                holder.setText(R.id.tv_search_desc, (CharSequence) entity.getField(MultipleFields.DESC));
                holder.setText(R.id.tv_search_title, (CharSequence) entity.getField(MultipleFields.TITLE));
                holder.setText(R.id.tv_search_time, (CharSequence) entity.getField(MultipleFields.TIME));
                String thumb = entity.getField(MultipleFields.IMAGE_URL);
                AppCompatImageView image = holder.getView(R.id.iv_search_image);
                Glide.with(mContext).load(thumb).apply(REQUEST_OPTIONS).into(image);
                break;
        }
    }
}
