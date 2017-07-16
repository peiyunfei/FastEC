package com.pyf.latte.ui.banner;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.pyf.latte.ui.R;

import java.util.ArrayList;

/**
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
                .setPages(new HolderCreator(), bannerImages)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_foucs})
                .setOnItemClickListener(clickListener)
                .setPageTransformer(new DefaultTransformer())
                .startTurning(3000)
                .setCanLoop(true);


    }
}
