package com.pyf.latte.ec.main.sort;

import android.os.Bundle;
import android.view.View;

import com.pyf.latte.delegate.bottom.BottomItemDelegate;
import com.pyf.latte.ec.R;
import com.pyf.latte.ec.main.sort.content.ContentDelegate;
import com.pyf.latte.ec.main.sort.list.VerticalListDelegate;

/**
 * 分类界面
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/14
 */
public class SortDelegate extends BottomItemDelegate {
    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        // 加载左侧列表界面
        loadRootFragment(R.id.fl_list_container, new VerticalListDelegate());
        // 加载右侧内容界面
        loadRootFragment(R.id.fl_content_container, ContentDelegate.newInstance(1));

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }
}
