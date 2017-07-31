package com.pyf.latte.ec.main.personal.list;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pyf.latte.ec.R;
import com.pyf.latte.ui.recycler.ItemType;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/30
 */
public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {

    // 设置图片加载策略
    private static final RequestOptions REQUEST_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();

    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(ItemType.LIST_NORMAL, R.layout.arrow_item_layout);
        addItemType(ItemType.LIST_AVATAR, R.layout.arrow_avatar_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListBean item) {
        switch (helper.getItemViewType()) {
            case ItemType.LIST_NORMAL:
                helper.setText(R.id.tv_arrow_text, item.getText());
                helper.setText(R.id.tv_arrow_value, item.getValue());
                break;
            case ItemType.LIST_AVATAR:
                CircleImageView image = helper.getView(R.id.iv_arrow_avatar);
                Glide.with(mContext)
                        .load(item.getImageUrl())
                        .apply(REQUEST_OPTIONS)
                        .into(image);
                break;
        }
    }
}
