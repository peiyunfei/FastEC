package com.pyf.latte.ui.launcher;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/12
 */
public class LauncherHolder implements Holder<Integer> {

    private AppCompatImageView mAppCompatImageView;

    @Override
    public View createView(Context context) {
        return mAppCompatImageView = new AppCompatImageView(context);
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        mAppCompatImageView.setBackgroundResource(data);
    }
}
