package com.pyf.latte.ec.main.cart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.pyf.latte.delegate.bottom.BottomItemDelegate;
import com.pyf.latte.ec.R;
import com.pyf.latte.ec.R2;
import com.pyf.latte.ec.pay.FastPay;
import com.pyf.latte.net.RestClient;
import com.pyf.latte.net.callback.IError;
import com.pyf.latte.net.callback.IFailure;
import com.pyf.latte.net.callback.ISuccess;
import com.pyf.latte.ui.recycler.MultipleFields;
import com.pyf.latte.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 购物车界面
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/14
 */
public class ShopCartDelegate extends BottomItemDelegate implements ISuccess, IShopCartItemListener {

    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRvShopCart;
    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mItvIsSelectAll;
    @BindView(R2.id.tv_shop_cart_price)
    AppCompatTextView mTvTotalPrice;
    @BindView(R2.id.stub_no_item)
    ViewStubCompat mVsNoItem;
    @BindView(R2.id.ll_shop_cart_bottom)
    LinearLayoutCompat mLlBottom;
    @BindView(R2.id.btn_retry)
    Button mBtnRetry;

    // 未全选标记
    private static final int UN_SELECT = 0;
    // 全选标记
    private static final int SELECT = 1;
    //购物车数量标记
    private int mCurrentCount = 0;
    private int mTotalCount = 0;
    // 总价
    private double mTotalPrice;
    private ShopCartAdapter mAdapter;

    /**
     * 全选
     */
    @OnClick(R2.id.icon_shop_cart_select_all)
    void onSelectAll() {
        int tag = (int) mItvIsSelectAll.getTag();
        if (tag == UN_SELECT) {
            mItvIsSelectAll.setTag(SELECT);
            mAdapter.setSelectAll(true);
            mItvIsSelectAll.setTextColor(ContextCompat.getColor(getContext(), R.color.app_main));
            List<MultipleItemEntity> data = mAdapter.getData();
            for (MultipleItemEntity multipleItemEntity : data) {
                double itemPrice = multipleItemEntity.getField(MultipleFields.ITEM_PRICE);
                mTotalPrice += itemPrice;
            }
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
            mAdapter.setTotalPrice(mTotalPrice);
            mTvTotalPrice.setText(String.valueOf(mTotalPrice));
        } else {
            mItvIsSelectAll.setTag(UN_SELECT);
            mAdapter.setSelectAll(false);
            mItvIsSelectAll.setTextColor(Color.GRAY);
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
            mTotalPrice = 0.0;
            mTvTotalPrice.setText(String.valueOf(mTotalPrice));
            mAdapter.setTotalPrice(mTotalPrice);
        }
    }

    /**
     * 重试
     */
    @OnClick(R2.id.btn_retry)
    void retry() {
        request();
    }

    /**
     * 清空购物车
     */
    @OnClick(R2.id.tv_shop_cart_clear)
    void clear() {
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
        checkItemCount();
    }

    /**
     * 删除指定的商品
     */
    @OnClick(R2.id.tv_shop_cart_delete)
    void delete() {
        final List<MultipleItemEntity> data = mAdapter.getData();
        //要删除的数据
        final List<MultipleItemEntity> deleteEntities = new ArrayList<>();
        for (MultipleItemEntity entity : data) {
            final boolean isSelected = entity.getField(MultipleFields.IS_SELECTED);
            if (isSelected) {
                deleteEntities.add(entity);
            }
        }
        for (MultipleItemEntity entity : deleteEntities) {
            int removePosition;
            final int entityPosition = entity.getField(MultipleFields.POSITION);
            if (entityPosition > mCurrentCount - 1) {
                removePosition = entityPosition - (mTotalCount - mCurrentCount);
            } else {
                removePosition = entityPosition;
            }
            if (removePosition <= mAdapter.getItemCount()) {
                mAdapter.remove(removePosition);
                mCurrentCount = mAdapter.getItemCount();
                //更新数据
                mAdapter.notifyItemRangeChanged(removePosition, mAdapter.getItemCount());
            }
        }
        checkItemCount();
    }

    /**
     * 支付
     */
    @OnClick(R2.id.tv_shop_cart_pay)
    void pay() {
        FastPay.create(this).showPayDialog();
    }

    private void createOrder() {

    }

    /**
     * 检查购物车是否有数据
     */
    private void checkItemCount() {
        int count = mAdapter.getItemCount();
        if (count == 0) {
            View stubView = mVsNoItem.inflate();
            AppCompatTextView tvNoItem = (AppCompatTextView) stubView.findViewById(R.id.tv_stub_to_buy);
            tvNoItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "去购物", Toast.LENGTH_SHORT).show();
                }
            });
            mRvShopCart.setVisibility(View.GONE);
            mLlBottom.setVisibility(View.GONE);
        } else {
            mRvShopCart.setVisibility(View.VISIBLE);
            mLlBottom.setVisibility(View.VISIBLE);
            mTotalPrice = 0.0;
            mTvTotalPrice.setText(String.valueOf(mTotalPrice));
            mAdapter.setTotalPrice(mTotalPrice);
        }
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        mItvIsSelectAll.setTag(UN_SELECT);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        request();
    }

    private void request() {
        RestClient.builder()
                .url("ShopCartServlet")
                .loader(getContext())
                .success(this)
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(), "请求失败，点击按钮重试", Toast.LENGTH_SHORT).show();
                        mBtnRetry.setVisibility(View.VISIBLE);
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(), "请求失败，点击按钮重试", Toast.LENGTH_SHORT).show();
                        mBtnRetry.setVisibility(View.VISIBLE);
                    }
                })
                .build()
                .get();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onSuccess(String response) {
        mBtnRetry.setVisibility(View.GONE);
        List<MultipleItemEntity> multipleItemEntities = new ShopCartConverter().setJsonData(response).convert();
        mAdapter = new ShopCartAdapter(multipleItemEntities, mItvIsSelectAll);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRvShopCart.setLayoutManager(manager);
        mRvShopCart.setAdapter(mAdapter);
        mTvTotalPrice.setText(String.valueOf(mAdapter.getTotalPrice()));
        checkItemCount();
        mAdapter.setOnShopCartItemListener(this);
    }

    @Override
    public void onItemClick(double itemTotalPrice) {
        mTvTotalPrice.setText(String.valueOf(mAdapter.getTotalPrice()));
    }
}
