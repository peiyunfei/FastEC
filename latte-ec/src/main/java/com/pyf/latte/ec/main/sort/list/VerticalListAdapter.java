package com.pyf.latte.ec.main.sort.list;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.pyf.latte.delegate.LatteDelegate;
import com.pyf.latte.ec.R;
import com.pyf.latte.ec.main.sort.SortDelegate;
import com.pyf.latte.ec.main.sort.content.ContentDelegate;
import com.pyf.latte.ui.recycler.ItemType;
import com.pyf.latte.ui.recycler.MultipleAdapter;
import com.pyf.latte.ui.recycler.MultipleFields;
import com.pyf.latte.ui.recycler.MultipleItemEntity;
import com.pyf.latte.ui.recycler.MultipleViewHolder;

import java.util.List;

import me.yokeyword.fragmentation.SupportHelper;

/**
 * 左侧列表适配器
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/16
 */
public class VerticalListAdapter extends MultipleAdapter {

    private SortDelegate DELEGATE;
    // 上一个点击的位置
    private int mPrePosition = 0;

    protected VerticalListAdapter(List<MultipleItemEntity> data, SortDelegate delegate) {
        super(data);
        DELEGATE = delegate;
        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_list);
    }

    @Override
    protected void convert(final MultipleViewHolder holder, final MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ItemType.VERTICAL_MENU_LIST:
                String name = entity.getField(MultipleFields.NAME);
                boolean isChecked = entity.getField(MultipleFields.TAG);
                AppCompatTextView tvName = holder.getView(R.id.tv_name);
                tvName.setText(name);
                View line = holder.getView(R.id.line);
                View itemView = holder.itemView;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 获取点击的位置
                        int currentPosition = holder.getAdapterPosition();
                        if (mPrePosition != currentPosition) {
                            // 还原上一个子项
                            getData().get(mPrePosition).setField(MultipleFields.TAG, false);
                            notifyItemChanged(mPrePosition);
                            // 更新选中子项
                            entity.setField(MultipleFields.TAG, true);
                            notifyItemChanged(currentPosition);
                            mPrePosition = currentPosition;
                        }
                        int contentId = getData().get(currentPosition).getField(MultipleFields.ID);
                        // 显示右侧内容界面
                        showContent(contentId);
                    }
                });
                if (!isChecked) {
                    line.setVisibility(View.GONE);
                    tvName.setTextColor(ContextCompat.getColor(mContext, R.color.we_chat_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.item_background));
                } else {
                    line.setVisibility(View.VISIBLE);
                    tvName.setTextColor(ContextCompat.getColor(mContext, R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
                    line.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_main));
                }
                break;
        }
    }

    /**
     * 根据id显示右侧内容界面
     */
    private void showContent(int contentId) {
        ContentDelegate contentDelegate = ContentDelegate.newInstance(contentId);
        switchContent(contentDelegate);
    }

    /**
     * 切换右侧内容界面
     */
    private void switchContent(ContentDelegate delegate) {
        final LatteDelegate contentDelegate =
                SupportHelper.findFragment(DELEGATE.getChildFragmentManager(), ContentDelegate.class);
        if (contentDelegate != null) {
            contentDelegate.getSupportDelegate().replaceFragment(delegate, false);
        }
    }
}
