package com.pyf.latte.ec.main.cart;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.joanzapata.iconify.widget.IconTextView;
import com.pyf.latte.app.Latte;
import com.pyf.latte.ec.R;
import com.pyf.latte.ui.recycler.ItemType;
import com.pyf.latte.ui.recycler.MultipleAdapter;
import com.pyf.latte.ui.recycler.MultipleFields;
import com.pyf.latte.ui.recycler.MultipleItemEntity;
import com.pyf.latte.ui.recycler.MultipleViewHolder;

import java.util.List;


/**
 * 购物车适配器
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/26
 */
public class ShopCartAdapter extends MultipleAdapter {

    // 设置图片加载策略
    private static final RequestOptions REQUEST_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();
    private boolean mIsSelect = false;
    private double mTotalPrice;
    private IShopCartItemListener mListener;
    private IconTextView mItvIsSelectAll;

    protected ShopCartAdapter(List<MultipleItemEntity> data, IconTextView itvIsSelectAll) {
        super(data);
        mItvIsSelectAll = itvIsSelectAll;
        addItemType(ItemType.SHOP_CART, R.layout.item_shop_cart);
        checkSelectAll();
        for (MultipleItemEntity multipleItemEntity : data) {
            boolean isSelect = multipleItemEntity.getField(MultipleFields.IS_SELECTED);
            double price = multipleItemEntity.getField(MultipleFields.ITEM_PRICE);
            int count = multipleItemEntity.getField(MultipleFields.COUNT);
            double itemPrice = price * count;
            if (isSelect) {
                mTotalPrice += itemPrice;
            }
        }
    }

    public void setOnShopCartItemListener(IShopCartItemListener listener) {
        mListener = listener;
    }

    public void setTotalPrice(double totalPrice) {
        mTotalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return mTotalPrice;
    }

    @Override
    protected void convert(MultipleViewHolder holder, final MultipleItemEntity entity) {
        switch (holder.getItemViewType()) {
            case ItemType.SHOP_CART:
                String title = entity.getField(MultipleFields.TITLE);
                String desc = entity.getField(MultipleFields.DESC);
                String thumb = entity.getField(MultipleFields.IMAGE_URL);
                final int count = entity.getField(MultipleFields.COUNT);
                final double price = entity.getField(MultipleFields.ITEM_PRICE);
                AppCompatTextView tvTile = holder.getView(R.id.tv_item_shop_cart_title);
                AppCompatTextView tvDesc = holder.getView(R.id.tv_item_shop_cart_desc);
                final AppCompatTextView tvPrice = holder.getView(R.id.tv_item_shop_cart_price);
                final AppCompatTextView tvCount = holder.getView(R.id.tv_item_shop_cart_count);
                final IconTextView iconSelect = holder.getView(R.id.item_shop_cart_select);
                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                entity.setField(MultipleFields.IS_SELECTED, mIsSelect);
                boolean isSelect = entity.getField(MultipleFields.IS_SELECTED);
                if (isSelect) {
                    iconSelect.setTextColor(ContextCompat.getColor(
                            Latte.getApplicationContext(), R.color.app_main));
                } else {
                    iconSelect.setTextColor(Color.GRAY);
                }
                Glide.with(mContext)
                        .load(thumb)
                        .apply(REQUEST_OPTIONS)
                        .into((ImageView) holder.getView(R.id.iv_item_shop_cart));
                tvTile.setText(title);
                tvDesc.setText(desc);
                tvCount.setText(String.valueOf(count));
                tvPrice.setText(String.valueOf(price));
                iconSelect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isSelect = entity.getField(MultipleFields.IS_SELECTED);
                        double itemPrice = entity.getField(MultipleFields.ITEM_PRICE);
                        if (isSelect) {
                            iconSelect.setTextColor(Color.GRAY);
                            entity.setField(MultipleFields.IS_SELECTED, false);
                            mTotalPrice -= itemPrice;
                        } else {
                            iconSelect.setTextColor(ContextCompat.getColor(
                                    Latte.getApplicationContext(), R.color.app_main));
                            entity.setField(MultipleFields.IS_SELECTED, true);
                            mTotalPrice += itemPrice;
                        }
                        entity.setField(MultipleFields.ITEM_PRICE, itemPrice);
                        if (mListener != null) {
                            mListener.onItemClick(itemPrice);
                        }
                        checkSelectAll();
                    }
                });
                iconMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isSelect = entity.getField(MultipleFields.IS_SELECTED);
                        double itemPrice = entity.getField(MultipleFields.ITEM_PRICE);
                        int count = entity.getField(MultipleFields.COUNT);
                        int countSum = Integer.parseInt(tvCount.getText().toString());
                        if (countSum > 1) {
                            countSum--;
                            tvCount.setText(String.valueOf(countSum));
                            itemPrice = itemPrice / count * countSum;
                            entity.setField(MultipleFields.COUNT, countSum);
                            entity.setField(MultipleFields.ITEM_PRICE, itemPrice);
                            tvPrice.setText(String.valueOf(itemPrice));
                            if (isSelect && mListener != null) {
                                double price = itemPrice / countSum;
                                mTotalPrice -= price;
                                mListener.onItemClick(itemPrice);
                            }
                        }
                    }
                });
                iconPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isSelect = entity.getField(MultipleFields.IS_SELECTED);
                        double itemPrice = entity.getField(MultipleFields.ITEM_PRICE);
                        int count = entity.getField(MultipleFields.COUNT);
                        int countSum = Integer.parseInt(tvCount.getText().toString());
                        if (countSum >= 1) {
                            countSum++;
                            tvCount.setText(String.valueOf(countSum));
                            itemPrice = itemPrice / count * countSum;
                            entity.setField(MultipleFields.COUNT, countSum);
                            entity.setField(MultipleFields.ITEM_PRICE, itemPrice);
                            tvPrice.setText(String.valueOf(itemPrice));
                            if (isSelect && mListener != null) {
                                double price = itemPrice / countSum;
                                mTotalPrice += price;
                                mListener.onItemClick(itemPrice);
                            }
                        }
                    }
                });
                break;
        }
    }

    /**
     * 检查是否有全选
     */
    private void checkSelectAll() {
        int number = 0;
        List<MultipleItemEntity> data = getData();
        for (MultipleItemEntity multipleItemEntity : data) {
            boolean isSelect = multipleItemEntity.getField(MultipleFields.IS_SELECTED);
            if (!isSelect) {
                mItvIsSelectAll.setTextColor(Color.GRAY);
            } else {
                number++;
            }
        }
        if (number == data.size()) {
            mItvIsSelectAll.setTextColor(ContextCompat.getColor(
                    Latte.getApplicationContext(), R.color.app_main));
            mItvIsSelectAll.setTag(1);
        }
    }

    public void setSelectAll(boolean isSelect) {
        mIsSelect = isSelect;
    }
}
