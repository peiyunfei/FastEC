package com.pyf.latte.ui.banner;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.pyf.latte.ui.recycler.IndexMultipleAdapter;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/16
 */
public class ImageHolder implements Holder<String> {

    private AppCompatImageView mAppCompatImageView;

    @Override
    public View createView(Context context) {
        mAppCompatImageView = new AppCompatImageView(context);
        return mAppCompatImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        Glide.with(context)
                .load(data)
                .apply(IndexMultipleAdapter.getRequestOptions())
                .into(mAppCompatImageView);
    }
}
