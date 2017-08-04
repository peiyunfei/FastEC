package com.pyf.latte.ec.main.personal.list;

import android.support.v7.widget.SwitchCompat;
import android.widget.CompoundButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pyf.latte.ec.R;
import com.pyf.latte.ec.main.personal.setting.SwitchPushTag;
import com.pyf.latte.ui.recycler.ItemType;
import com.pyf.latte.utils.callback.CallbackManager;
import com.pyf.latte.utils.callback.CallbackType;
import com.pyf.latte.utils.storage.LattePreference;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/30
 */
public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {

    // 设置图片加载策略
    private static final RequestOptions REQUEST_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();

    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(ItemType.ITEM_NORMAL, R.layout.arrow_item_layout);
        addItemType(ItemType.ITEM_AVATAR, R.layout.arrow_avatar_layout);
        addItemType(ItemType.ITEM_SWITCH, R.layout.arrow_push_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListBean item) {
        switch (helper.getItemViewType()) {
            case ItemType.ITEM_NORMAL:
                helper.setText(R.id.tv_arrow_text, item.getText());
                helper.setText(R.id.tv_arrow_value, item.getValue());
                break;
            case ItemType.ITEM_AVATAR:
                CircleImageView image = helper.getView(R.id.iv_arrow_avatar);
                Glide.with(mContext)
                        .load(item.getImageUrl())
                        .apply(REQUEST_OPTIONS)
                        .into(image);
                break;
            case ItemType.ITEM_SWITCH:
                helper.setText(R.id.tv_push_text, item.getText());
                final SwitchCompat switchCompat = helper.getView(R.id.sw_push);
                boolean isChecked = LattePreference.getAppFlag(SwitchPushTag.SWITCH_PUSH_TAG.name());
                switchCompat.setChecked(isChecked);
                switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @SuppressWarnings("unchecked")
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            switchCompat.setChecked(true);
                            LattePreference.setAppFlag(SwitchPushTag.SWITCH_PUSH_TAG.name(), true);
                            CallbackManager.getInstance().getCallback(CallbackType.TAG_OPEN_PUSH)
                                    .executeCallback(null);
                        } else {
                            switchCompat.setChecked(false);
                            LattePreference.setAppFlag(SwitchPushTag.SWITCH_PUSH_TAG.name(), false);
                            CallbackManager.getInstance().getCallback(CallbackType.TAG_STOP_PUSH)
                                    .executeCallback(null);

                        }
                    }
                });
                break;
        }
    }
}
