package com.pyf.latte.ec.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.pyf.latte.delegate.LatteDelegate;
import com.pyf.latte.ec.R;
import com.pyf.latte.ec.R2;
import com.pyf.latte.net.RestClient;
import com.pyf.latte.net.callback.IError;
import com.pyf.latte.net.callback.IFailure;
import com.pyf.latte.net.callback.ISuccess;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/16
 */

public class ContentDelegate extends LatteDelegate {

    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;

    @BindView(R2.id.rv_content)
    RecyclerView mRvContent;
    private List<SectionBean> mData = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mContentId = bundle.getInt(ARG_CONTENT_ID);
        }
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        // 创建瀑布流布局
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager
                (2, StaggeredGridLayoutManager.VERTICAL);
        // 设置瀑布流布局
        mRvContent.setLayoutManager(manager);
        initData();
    }

    private void initData() {
        RestClient.builder()
                .url("ContentServlet?contentId=" + mContentId)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        mData = new SectionDataConverter().converter(response);
                        final SectionAdapter sectionAdapter =
                                new SectionAdapter(R.layout.item_section_content,
                                        R.layout.item_section_header, mData);
                        mRvContent.setAdapter(sectionAdapter);
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
        return R.layout.delegate_content;
    }

    public static ContentDelegate newInstance(int contentId) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_CONTENT_ID, contentId);
        ContentDelegate contentDelegate = new ContentDelegate();
        contentDelegate.setArguments(bundle);
        return contentDelegate;
    }
}
