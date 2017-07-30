package com.pyf.latte.ec.main.personal.order;

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
 * 订单适配器
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/30
 */
public class OrderListAdapter extends MultipleAdapter {

    // 设置图片加载策略
    private static final RequestOptions REQUEST_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();

    protected OrderListAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ItemType.ORDER_LIST, R.layout.item_order_list);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        switch (holder.getItemViewType()) {
            case ItemType.ORDER_LIST:
                holder.setText(R.id.tv_order_price, (CharSequence) entity.getField(MultipleFields.ITEM_PRICE));
                holder.setText(R.id.tv_order_title, (CharSequence) entity.getField(MultipleFields.TITLE));
                holder.setText(R.id.tv_order_time, (CharSequence) entity.getField(MultipleFields.TIME));
                String thumb = entity.getField(MultipleFields.IMAGE_URL);
                AppCompatImageView image = holder.getView(R.id.iv_order_image);
                Glide.with(mContext).load(thumb).apply(REQUEST_OPTIONS).into(image);
                break;
        }
    }
}
