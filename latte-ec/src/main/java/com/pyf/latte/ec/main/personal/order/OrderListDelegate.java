package com.pyf.latte.ec.main.personal.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.pyf.latte.delegate.LatteDelegate;
import com.pyf.latte.ec.R;
import com.pyf.latte.ec.R2;
import com.pyf.latte.ec.main.personal.PersonalDelegate;
import com.pyf.latte.net.RestClient;
import com.pyf.latte.net.callback.IError;
import com.pyf.latte.net.callback.IFailure;
import com.pyf.latte.net.callback.ISuccess;
import com.pyf.latte.ui.recycler.MultipleItemEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 订单列表界面
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/30
 */
public class OrderListDelegate extends LatteDelegate {

    @BindView(R2.id.rv_order_list)
    RecyclerView mRvOrderList;
    @BindView(R2.id.btn_order_retry)
    Button mBtnRetry;
    private String mType;

    @OnClick(R2.id.btn_order_retry)
    void retry() {
        request();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        mType = args.getString(PersonalDelegate.ORDER_TYPE);
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        request();
    }

    private void request() {
        RestClient.builder()
                .params("type", mType)
                .url("OrderListServlet")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        mBtnRetry.setVisibility(View.GONE);
                        List<MultipleItemEntity> data =
                                new OrderListDataConverter().setJsonData(response).convert();
                        OrderListAdapter adapter = new OrderListAdapter(data);
                        LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRvOrderList.setLayoutManager(manager);
                        mRvOrderList.setAdapter(adapter);
                        mRvOrderList.addOnItemTouchListener
                                (new OrderListClickListener(OrderListDelegate.this));
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        mBtnRetry.setVisibility(View.VISIBLE);
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        mBtnRetry.setVisibility(View.VISIBLE);
                    }
                })
                .loader(getContext())
                .build()
                .get();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_list;
    }
}
