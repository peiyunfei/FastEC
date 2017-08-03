package com.pyf.latte.ec.main.personal.address;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pyf.latte.delegate.LatteDelegate;
import com.pyf.latte.ec.R;
import com.pyf.latte.ec.R2;
import com.pyf.latte.net.RestClient;
import com.pyf.latte.net.callback.IError;
import com.pyf.latte.net.callback.IFailure;
import com.pyf.latte.net.callback.ISuccess;
import com.pyf.latte.ui.recycler.MultipleItemEntity;

import java.util.List;

import butterknife.BindView;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/8/3
 */
public class AddressDelegate extends LatteDelegate {

    @BindView(R2.id.rv_address)
    RecyclerView mRvAddress;

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("AddressServlet")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        List<MultipleItemEntity> multipleItemEntities =
                                new AddressDataConverter().setJsonData(response).convert();
                        LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRvAddress.setLayoutManager(manager);
                        mRvAddress.setAdapter(new AddressAdapter(multipleItemEntities));
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_address;
    }
}
