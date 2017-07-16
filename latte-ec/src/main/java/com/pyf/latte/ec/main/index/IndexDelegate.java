package com.pyf.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;
import com.pyf.latte.delegate.bottom.BottomItemDelegate;
import com.pyf.latte.ec.R;
import com.pyf.latte.ec.R2;
import com.pyf.latte.ec.main.EcBottomDelegate;
import com.pyf.latte.ui.recycler.BaseDecoration;
import com.pyf.latte.ui.refresh.RefreshHandler;

import butterknife.BindView;

/**
 * 首页界面
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/14
 */
public class IndexDelegate extends BottomItemDelegate {

    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R2.id.rv_index)
    RecyclerView mRvIndex;
    @BindView(R2.id.tl_index)
    Toolbar mTlIndex;
    @BindView(R2.id.icon_index_message)
    IconTextView mIconMessage;
    @BindView(R2.id.et_index_search)
    AppCompatEditText mEtSearch;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan;

    private RefreshHandler mRefreshHandler;

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mRefreshLayout.setProgressViewOffset(true, 120, 300);

    }

    private void initRecyclerView() {
        mRvIndex.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mRvIndex.addItemDecoration(BaseDecoration.create(
                ContextCompat.getColor(getContext(), R.color.recycler_decoration), 5));
        EcBottomDelegate ecBottomDelegate = getParentDelegate();
        // 添加点击事件
        mRvIndex.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.request("IndexServlet");
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        mRefreshHandler = RefreshHandler.create(getContext(), mRefreshLayout,
                mRvIndex, new IndexDataConverter());
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }
}
