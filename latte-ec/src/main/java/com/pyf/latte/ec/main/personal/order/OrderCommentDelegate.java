package com.pyf.latte.ec.main.personal.order;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.pyf.latte.delegate.LatteDelegate;
import com.pyf.latte.ec.R;
import com.pyf.latte.ec.R2;
import com.pyf.latte.ui.widget.AutoPhotoLayout;
import com.pyf.latte.ui.widget.StarLayout;
import com.pyf.latte.utils.callback.CallbackManager;
import com.pyf.latte.utils.callback.CallbackType;
import com.pyf.latte.utils.callback.IGlobalCallback;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 评论界面
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/8/5
 */
public class OrderCommentDelegate extends LatteDelegate {

    @BindView(R2.id.custom_star_layout)
    StarLayout mStarLayout;
    @BindView(R2.id.iv_order_comment_image)
    ImageView mCommentImage;
    @BindView(R2.id.custom_auto_photo_layout)
    AutoPhotoLayout mAutoPhotoLayout;
    @BindView(R2.id.et_order_comment)
    EditText mEtOrderComment;

    @OnClick(R2.id.tv_order_comment_submit)
    void onClickSubmit() {
        Toast.makeText(getContext(), "" + mStarLayout.getStarCount(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        mAutoPhotoLayout.setDelegate(this);
        CallbackManager.getInstance().addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
            @Override
            public void executeCallback(@Nullable Uri args) {
                mAutoPhotoLayout.onCrop(args);
            }
        });
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_comment;
    }

}
