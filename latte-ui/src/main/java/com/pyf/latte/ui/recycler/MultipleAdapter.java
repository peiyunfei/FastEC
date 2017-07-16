package com.pyf.latte.ui.recycler;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.SpanSizeLookup;
import com.pyf.latte.ui.R;
import com.pyf.latte.ui.banner.BannerCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页适配器
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/15
 */
public class MultipleAdapter extends
        BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder>
        implements SpanSizeLookup,
        OnItemClickListener {

    // 是否第一次加载轮播图
    private boolean mIsFirstBanner = true;

    // 设置图片加载策略
    private static final RequestOptions REQUEST_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();

    public static RequestOptions getRequestOptions() {
        return REQUEST_OPTIONS;
    }

    protected MultipleAdapter(List<MultipleItemEntity> data) {
        super(data);
        initItemView();
    }

    public static MultipleAdapter create(List<MultipleItemEntity> data) {
        return new MultipleAdapter(data);
    }

    /**
     * 初始化子布局
     */
    private void initItemView() {
        addItemType(ItemType.TEXT, R.layout.item_multiple_text);
        addItemType(ItemType.IMAGE, R.layout.item_multiple_image);
        addItemType(ItemType.TEXT_IMAGE, R.layout.item_multiple_image_text);
        addItemType(ItemType.BANNER, R.layout.item_multiple_banner);
        // 设置宽度监听
        setSpanSizeLookup(this);
        // 加载的时候执行动画
        openLoadAnimation();
        // 多次执行动画
        isFirstOnly(false);
    }

    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return MultipleViewHolder.create(view);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        String text;
        String imageUrl;
        ArrayList<String> bannerImages;
        switch (holder.getItemViewType()) {
            case ItemType.TEXT:
                text = entity.getField(MultipleFields.TEXT);
                holder.setText(R.id.tv_single, text);
                break;
            case ItemType.IMAGE:
                imageUrl = entity.getField(MultipleFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(REQUEST_OPTIONS)
                        .into((ImageView) holder.getView(R.id.iv_single));
                break;
            case ItemType.TEXT_IMAGE:
                text = entity.getField(MultipleFields.TEXT);
                imageUrl = entity.getField(MultipleFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(REQUEST_OPTIONS)
                        .into((ImageView) holder.getView(R.id.iv_multiple));
                holder.setText(R.id.tv_multiple, text);
                break;
            case ItemType.BANNER:
                if (mIsFirstBanner) {
                    bannerImages = entity.getField(MultipleFields.BANNERS);
                    if (bannerImages != null && bannerImages.size() > 0) {
                        ConvenientBanner<String> convenientBanner = holder.getView(R.id.banner_recycler_item);
                        BannerCreator.setDefault(convenientBanner, bannerImages, this);
                    }
                    mIsFirstBanner = false;
                }
                break;
        }
    }

    /**
     * 获取宽度
     */
    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }

    @Override
    public void onItemClick(int position) {

    }
}
