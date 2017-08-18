package com.pyf.latte.ec.main.index.detail;

import android.widget.ImageView;

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
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/8/18
 */

public class RecyclerImageAdapter extends MultipleAdapter {

    private final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();

    protected RecyclerImageAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ItemType.ITEM_IMAGE, R.layout.item_image);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ItemType.ITEM_IMAGE:
                Glide.with(mContext)
                        .load(entity.getField(MultipleFields.IMAGE_URL))
                        .apply(OPTIONS)
                        .into((ImageView) holder.getView(R.id.iv_detail));
                break;
        }
    }
}
