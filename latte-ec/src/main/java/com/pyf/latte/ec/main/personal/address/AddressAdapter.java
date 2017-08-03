package com.pyf.latte.ec.main.personal.address;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.pyf.latte.ec.R;
import com.pyf.latte.net.RestClient;
import com.pyf.latte.net.callback.ISuccess;
import com.pyf.latte.ui.recycler.ItemType;
import com.pyf.latte.ui.recycler.MultipleAdapter;
import com.pyf.latte.ui.recycler.MultipleFields;
import com.pyf.latte.ui.recycler.MultipleItemEntity;
import com.pyf.latte.ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/8/3
 */

public class AddressAdapter extends MultipleAdapter {

    protected AddressAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ItemType.ITEM_ADDRESS, R.layout.item_address);
    }

    @Override
    protected void convert(final MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ItemType.ITEM_ADDRESS:
                AppCompatTextView tvAddress = holder.getView(R.id.tv_address_address);
                AppCompatTextView tvAddressName = holder.getView(R.id.tv_address_name);
                AppCompatTextView tvAddressPhone = holder.getView(R.id.tv_address_phone);
                AppCompatTextView tvAddressDelete = holder.getView(R.id.tv_address_delete);
                String name = entity.getField(MultipleFields.NAME);
                String phone = entity.getField(MultipleFields.PHONE);
                String address = entity.getField(MultipleFields.ADDRESS);
                tvAddress.setText(address);
                tvAddressName.setText(name);
                tvAddressPhone.setText(phone);
                tvAddressDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RestClient.builder()
                                .url("AddressServlet")
                                .params("id", "id")
                                .success(new ISuccess() {
                                    @Override
                                    public void onSuccess(String response) {
                                        remove(holder.getLayoutPosition());
                                    }
                                })
                                .build()
                                .post();
                    }
                });
                break;
        }
    }
}
