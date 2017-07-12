package com.pyf.latte.ec.launcher;

import android.os.Bundle;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.pyf.latte.delegate.LatteDelegate;
import com.pyf.latte.ec.R;
import com.pyf.latte.ui.launcher.LauncherHolderCreator;
import com.pyf.latte.ui.launcher.ScrollLauncherTag;
import com.pyf.latte.utils.storage.LattePreference;

import java.util.ArrayList;
import java.util.List;

/**
 * 滚动的欢迎界面
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/12
 */
public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private List<Integer> mBanners = new ArrayList<>();

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    /**
     * 初始化轮播图
     */
    private void initBanner() {
        mBanners.add(R.mipmap.launcher_01);
        mBanners.add(R.mipmap.launcher_02);
        mBanners.add(R.mipmap.launcher_03);
        mBanners.add(R.mipmap.launcher_04);
        mBanners.add(R.mipmap.launcher_05);
        mConvenientBanner
                .setPages(new LauncherHolderCreator(), mBanners)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_foucs})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public void onItemClick(int position) {
        // 点击了最后一个轮播图
        if (position == mBanners.size() - 1) {
            // 标记第一次进入应用
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            // todo 检查用户是否登录
        }
    }
}
