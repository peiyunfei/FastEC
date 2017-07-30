package com.pyf.latte.ec.main.personal.list;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pyf.latte.ec.R;
import com.pyf.latte.ui.recycler.ItemType;

import java.util.List;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/30
 */

public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {

    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(ItemType.LIST_SETTING, R.layout.arrow_item_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListBean item) {
        switch (helper.getItemViewType()) {
            case ItemType.LIST_SETTING:
                helper.setText(R.id.tv_arrow_text, item.getText());
                helper.setText(R.id.tv_arrow_value, item.getValue());
                break;
        }
    }
}
