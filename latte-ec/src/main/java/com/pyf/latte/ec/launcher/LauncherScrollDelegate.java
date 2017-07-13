package com.pyf.latte.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.pyf.latte.app.AccountManager;
import com.pyf.latte.app.IUserCheck;
import com.pyf.latte.delegate.LatteDelegate;
import com.pyf.latte.ec.R;
import com.pyf.latte.ui.launcher.ILauncherListener;
import com.pyf.latte.ui.launcher.LauncherHolderCreator;
import com.pyf.latte.ui.launcher.OnLauncherFinishTag;
import com.pyf.latte.ui.launcher.ScrollLauncherTag;
import com.pyf.latte.utils.storage.LattePreference;

import java.util.ArrayList;
import java.util.List;

/**
 * 滑动启动界面
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/12
 */
public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private List<Integer> mBanners = new ArrayList<>();

    private ILauncherListener mILauncherListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

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
                // 设置滑动页面
                .setPages(new LauncherHolderCreator(), mBanners)
                // 设置指示器的状态
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_foucs})
                // 设置指示器的对齐方式，水平居中
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                // 设置点击事件
                .setOnItemClickListener(this)
                // 不能轮询
                .setCanLoop(false);
    }

    @Override
    public void onItemClick(int position) {
        // 点击了最后一个轮播图
        if (position == mBanners.size() - 1) {
            // 标记第一次进入应用
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            AccountManager.checkAccount(new IUserCheck() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {
                        // 已登录
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null) {
                        // 未登录
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }
}
