package com.pyf.latte.ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pyf.latte.delegate.LatteDelegate;
import com.pyf.latte.ec.R;
import com.pyf.latte.ec.R2;
import com.pyf.latte.ec.main.sort.SortDelegate;
import com.pyf.latte.net.RestClient;
import com.pyf.latte.net.callback.IError;
import com.pyf.latte.net.callback.IFailure;
import com.pyf.latte.net.callback.ISuccess;
import com.pyf.latte.ui.recycler.MultipleItemEntity;

import java.util.List;

import butterknife.BindView;

/**
 * 左侧列表界面
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/16
 */
public class VerticalListDelegate extends LatteDelegate {

    @BindView(R2.id.rv_vertical_list)
    RecyclerView mRvVerticalList;

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRvVerticalList.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        // 取消动画
        mRvVerticalList.setItemAnimator(null);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("SortListServlet")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        VerticalListDataConverter converter = new VerticalListDataConverter();
                        List<MultipleItemEntity> data = converter.setJsonData(response).convert();
                        SortDelegate sortDelegate = getParentDelegate();
                        VerticalListAdapter adapter = new VerticalListAdapter(data, sortDelegate);
                        mRvVerticalList.setAdapter(adapter);
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
                .loader(getContext())
                .build()
                .get();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }
}
