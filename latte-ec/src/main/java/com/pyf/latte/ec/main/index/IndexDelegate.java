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
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.pyf.latte.delegate.bottom.BottomItemDelegate;
import com.pyf.latte.ec.R;
import com.pyf.latte.ec.R2;
import com.pyf.latte.ec.main.EcBottomDelegate;
import com.pyf.latte.ec.main.index.search.SearchDelegate;
import com.pyf.latte.net.RestCreator;
import com.pyf.latte.net.rx.RxRestClient;
import com.pyf.latte.ui.recycler.BaseDecoration;
import com.pyf.latte.ui.refresh.RefreshHandler;

import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 首页界面
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/14
 */
public class IndexDelegate extends BottomItemDelegate implements View.OnFocusChangeListener {

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

    private RefreshHandler mRefreshHandler;

    @OnClick(R2.id.icon_index_scan)
    void onClickScanner() {
        startScanWithCheck(this);
    }

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
        mEtSearch.setOnFocusChangeListener(this);
        //        testRxJava2();
    }

    private void testRxJava2() {
        RxRestClient.builder()
                .url("IndexServlet")
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    void testRxJava() {
        WeakHashMap<String, Object> params = new WeakHashMap<>();
        RestCreator
                .getRxRestService()
                .get("IndexServlet", params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            getParentDelegate().getSupportDelegate().start(new SearchDelegate());
        }
    }
}
