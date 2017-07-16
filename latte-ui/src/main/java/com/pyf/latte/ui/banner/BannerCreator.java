package com.pyf.latte.ui.banner;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.pyf.latte.ui.R;

import java.util.ArrayList;

/**
 * 创建轮播图
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/15
 */
public class BannerCreator {

    public static void setDefault(ConvenientBanner<String> convenientBanner,
                                  ArrayList<String> bannerImages,
                                  OnItemClickListener clickListener) {
        convenientBanner
                // 设置滑动页面
                .setPages(new HolderCreator(), bannerImages)
                // 设置指示器的状态
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_foucs})
                // 设置点击事件
                .setOnItemClickListener(clickListener)
                // 默认的转场动画
                .setPageTransformer(new DefaultTransformer())
                // 3秒划定一次
                .startTurning(3000)
                // 轮询
                .setCanLoop(true);


    }
}
