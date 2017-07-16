package com.pyf.latte.ui.refresh;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pyf.latte.app.Latte;
import com.pyf.latte.net.RestClient;
import com.pyf.latte.net.callback.IError;
import com.pyf.latte.net.callback.IFailure;
import com.pyf.latte.net.callback.ISuccess;
import com.pyf.latte.ui.recycler.DataConverter;
import com.pyf.latte.ui.recycler.IndexMultipleAdapter;
import com.pyf.latte.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/15
 */

public class RefreshHandler implements
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {

    private final SwipeRefreshLayout REFRESHLAYOUT;
    private final RecyclerView RECYCLERVIEW;
    private final PageBean PAGEBEAN;
    private final DataConverter DATACONVERTER;
    private final Context CONTEXT;
    private IndexMultipleAdapter mAdapter;
    private List<MultipleItemEntity> mMultipleItemEntities;
    private boolean mIsFirstPage = true;

    private RefreshHandler(Context context, SwipeRefreshLayout refreshLayout, RecyclerView recyclerView,
                           DataConverter converter, PageBean pageBean) {
        CONTEXT = context;
        REFRESHLAYOUT = refreshLayout;
        RECYCLERVIEW = recyclerView;
        DATACONVERTER = converter;
        PAGEBEAN = pageBean;
        mMultipleItemEntities = new ArrayList<>();
        REFRESHLAYOUT.setOnRefreshListener(this);
    }

    public static RefreshHandler create(Context context, SwipeRefreshLayout refreshLayout,
                                        RecyclerView recyclerView, DataConverter converter) {
        return new RefreshHandler(context, refreshLayout, recyclerView, converter, new PageBean());
    }

    private void refresh() {
        // 正在刷新
        REFRESHLAYOUT.setRefreshing(true);
        request("IndexServlet");
        // 设置监听
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 停止刷新
                REFRESHLAYOUT.setRefreshing(false);
            }
        }, 1000);
    }

    public void request(String url) {
        RestClient
                .builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject jsonObject = JSON.parseObject(response);
                        DATACONVERTER.setJsonData(response);
                        List<MultipleItemEntity> multipleItemEntities = DATACONVERTER.convert();
                        mMultipleItemEntities.addAll(multipleItemEntities);
                        if (mIsFirstPage) {
                            initPageBean(jsonObject);
                            mAdapter = IndexMultipleAdapter.create(mMultipleItemEntities);
                            RECYCLERVIEW.setAdapter(mAdapter);
                        } else {
                            mAdapter.setNewData(mMultipleItemEntities);
                        }
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLERVIEW);

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .loader(CONTEXT)
                .build()
                .get();
    }

    /**
     * 初始化分页
     *
     * @param jsonObject
     *         json数据
     */
    private void initPageBean(JSONObject jsonObject) {
        PAGEBEAN.setDelay(100);
        PAGEBEAN.setTotal(jsonObject.getInteger("total"))
                .setPageSize(jsonObject.getInteger("page_size"));
        PAGEBEAN.addIndex();
        mIsFirstPage = false;
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    @Override
    public void onLoadMoreRequested() {
        PAGEBEAN.addIndex();
        int pageIndex = PAGEBEAN.getPageIndex();
        request("IndexServlet?pageIndex=" + pageIndex);
    }
}
