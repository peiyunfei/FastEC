package com.pyf.latte.ec.main.index.search;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.pyf.latte.delegate.LatteDelegate;
import com.pyf.latte.ec.R;
import com.pyf.latte.ec.R2;
import com.pyf.latte.net.RestClient;
import com.pyf.latte.net.callback.ISuccess;
import com.pyf.latte.ui.recycler.MultipleItemEntity;
import com.pyf.latte.utils.storage.LattePreference;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 搜索界面
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/8/8
 */
public class SearchDelegate extends LatteDelegate {

    @BindView(R2.id.rv_search)
    RecyclerView mRvSearch;
    @BindView(R2.id.et_search)
    AppCompatEditText mEtSearch;

    @OnClick(R2.id.icon_search_back)
    void onClickBack() {
        getSupportDelegate().pop();
    }

    @OnClick(R2.id.tv_search)
    void onClickSearch() {
        final String search = mEtSearch.getText().toString();
        if (TextUtils.isEmpty(search)) {
            Toast.makeText(getContext(), "请输入要搜索的内容", Toast.LENGTH_SHORT).show();
            return;
        }
        RestClient.builder()
                .url("SearchServlet")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        List<MultipleItemEntity> entities =
                                new NetSearchDataConverter().setJsonData(response).convert();
                        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
                        mRvSearch.setLayoutManager(manager);
                        SearchAdapter adapter = new SearchAdapter(entities);
                        mRvSearch.setAdapter(adapter);
                        // 保存搜索记录
                        saveSearchRecord(search);
                        mEtSearch.setText("");

                    }
                })
                .loader(getContext())
                .build()
                .get();
    }

    @SuppressWarnings("unchecked")
    private void saveSearchRecord(String search) {
        List<String> histories;
        String history = LattePreference.getCustomAppProfile(SearchRecordDataConverter.SEARCH_HISTORY);
        if (TextUtils.isEmpty(history)) {
            histories = new ArrayList<>();
        } else {
            histories = JSON.parseObject(history, ArrayList.class);
        }
        histories.add(search);
        String json = JSON.toJSONString(histories);
        LattePreference.addCustomAppProfile(SearchRecordDataConverter.SEARCH_HISTORY, json);
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        List<MultipleItemEntity> entities =
                new SearchRecordDataConverter().convert();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        mRvSearch.setLayoutManager(manager);
        SearchAdapter adapter = new SearchAdapter(entities);
        mRvSearch.setAdapter(adapter);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_search;
    }
}
