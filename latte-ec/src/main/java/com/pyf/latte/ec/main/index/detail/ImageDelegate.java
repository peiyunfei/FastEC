package com.pyf.latte.ec.main.index.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pyf.latte.delegate.LatteDelegate;
import com.pyf.latte.ec.R;
import com.pyf.latte.ec.R2;
import com.pyf.latte.ui.recycler.ItemType;
import com.pyf.latte.ui.recycler.MultipleFields;
import com.pyf.latte.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/8/18
 */
public class ImageDelegate extends LatteDelegate {

    private static final String GOODS_IMAGE = "goods_image";
    private ArrayList<String> mData;
    @BindView(R2.id.rv_image)
    RecyclerView mRvImage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mData = bundle.getStringArrayList(GOODS_IMAGE);
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRvImage.setLayoutManager(manager);
        List<MultipleItemEntity> entities = new ArrayList<>();
        for (String pictureUls : mData) {
            MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.ITEM_IMAGE)
                    .setField(MultipleFields.IMAGE_URL, pictureUls)
                    .build();
            entities.add(entity);
        }
        RecyclerImageAdapter adapter = new RecyclerImageAdapter(entities);
        mRvImage.setAdapter(adapter);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_image;
    }

    public static ImageDelegate create(ArrayList<String> data) {
        ImageDelegate delegate = new ImageDelegate();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(GOODS_IMAGE, data);
        delegate.setArguments(bundle);
        return delegate;
    }
}