package com.pyf.latte.ec.main.index.detail;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.joanzapata.iconify.widget.IconTextView;
import com.pyf.latte.delegate.LatteDelegate;
import com.pyf.latte.ec.R;
import com.pyf.latte.ec.R2;
import com.pyf.latte.net.RestClient;
import com.pyf.latte.net.callback.ISuccess;
import com.pyf.latte.ui.animation.BezierAnimation;
import com.pyf.latte.ui.animation.BezierUtil;
import com.pyf.latte.ui.banner.HolderCreator;
import com.pyf.latte.ui.widget.CircleTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 商品详情界面
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/16
 */
public class GoodsDetailDelegate extends LatteDelegate implements AppBarLayout.OnOffsetChangedListener,
        BezierUtil.AnimationListener {

    // 商品id
    private static final String GOODS_ID = "goods_id";
    // 商品id
    private int mGoodsId;
    /**
     * 轮播图
     */
    @BindView(R2.id.detail_banner)
    ConvenientBanner mDetailBanner;
    /**
     * 返回
     */
    @BindView(R2.id.icon_back_detail)
    IconTextView mIconBackDetail;

    @BindView(R2.id.collapsing_toolbar_detail)
    CollapsingToolbarLayout mCollapsingToolbarDetail;

    @BindView(R2.id.tab_layout)
    TabLayout mTabLayout;

    @BindView(R2.id.app_bar_detail)
    AppBarLayout mAppBarDetail;

    @BindView(R2.id.vp_detail)
    ViewPager mVpDetail;

    // 底部
    @BindView(R2.id.tv_shopping_cart_amount)
    CircleTextView mTvShoppingCartAmount;
    @BindView(R2.id.rl_add_shop_cart)
    RelativeLayout mRlAddShopCart;
    @BindView(R2.id.icon_shop_cart)
    IconTextView mIconAddShopCart;

    private String mGoodsThumbUrl = null;
    private int mShopCount = 0;

    @OnClick(R2.id.rl_add_shop_cart)
    void onAddShopCart() {
        final CircleImageView animImg = new CircleImageView(getContext());
        Glide.with(this)
                .load(mGoodsThumbUrl)
                .apply(OPTIONS)
                .into(animImg);
        BezierAnimation.addCart(this, mRlAddShopCart, mIconAddShopCart, animImg, this);
    }

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate()
            .override(100, 100);

    public static GoodsDetailDelegate create(int goodsId) {
        GoodsDetailDelegate delegate = new GoodsDetailDelegate();
        Bundle bundle = new Bundle();
        bundle.putInt(GOODS_ID, goodsId);
        delegate.setArguments(bundle);
        return delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mGoodsId = bundle.getInt(GOODS_ID, -1);
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        mCollapsingToolbarDetail.setContentScrimColor(Color.WHITE);
        mAppBarDetail.addOnOffsetChangedListener(this);
        mTvShoppingCartAmount.setCircleBackground(Color.RED);
        initData();
        initTabLayout();
    }

    private void initTabLayout() {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(),
                R.color.app_main));
        mTabLayout.setTabTextColors(ColorStateList.valueOf(Color.BLACK));
        mTabLayout.setBackgroundColor(Color.WHITE);
        mTabLayout.setupWithViewPager(mVpDetail);
    }

    private void initData() {
        RestClient.builder()
                .url("GoodsDetailServlet")
                .params(GOODS_ID, mGoodsId)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject data = JSON.parseObject(response).getJSONObject("data");
                        initBanners(data);
                        initGoodsInfo(data);
                        initPager(data);
                        initShopCartCount(data);
                    }
                })
                .loader(getContext())
                .build()
                .post();
    }

    /**
     * 初始化购物车数量
     */
    private void initShopCartCount(JSONObject data) {
        mGoodsThumbUrl = data.getString("thumb");
        if (mShopCount == 0) {
            mTvShoppingCartAmount.setVisibility(View.GONE);
        }
    }

    /**
     * 初始化viewpager
     */
    private void initPager(JSONObject data) {
        TabPagerAdapter adapter = new TabPagerAdapter(getFragmentManager(), data);
        mVpDetail.setAdapter(adapter);
    }

    /**
     * 初始化商品信息
     */
    private void initGoodsInfo(JSONObject data) {
        String jsonData = data.toJSONString();
        GoodsInfoDelegate delegate = GoodsInfoDelegate.create(jsonData);
        getSupportDelegate().loadRootFragment(R.id.frame_goods_info, delegate);
    }

    /**
     * 初始化轮播图
     */
    private void initBanners(JSONObject data) {
        JSONArray banners = data.getJSONArray("banners");
        List<String> images = new ArrayList<>();
        int size = banners.size();
        for (int i = 0; i < size; i++) {
            images.add(banners.getString(i));
        }
        mDetailBanner.setPages(new HolderCreator(), images)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_foucs})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setPageTransformer(new DefaultTransformer())
                .startTurning(3000)
                .setCanLoop(true);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    /**
     * 默认动画
     */
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }

    /**
     * 动画结束后回调
     */
    @Override
    public void onAnimationEnd() {
        RestClient.builder()
                .url("AddShopCartServlet")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject jsonObject = JSON.parseObject(response);
                        Boolean data = jsonObject.getBoolean("data");
                        if (data) {
                            mTvShoppingCartAmount.setVisibility(View.VISIBLE);
                            mShopCount++;
                            mTvShoppingCartAmount.setText(String.valueOf(mShopCount));
                        }
                    }
                })
                .build()
                .get();
    }
}
