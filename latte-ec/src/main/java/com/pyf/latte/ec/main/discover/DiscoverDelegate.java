package com.pyf.latte.ec.main.discover;

import android.os.Bundle;
import android.view.View;

import com.pyf.latte.delegate.bottom.BottomItemDelegate;
import com.pyf.latte.delegate.web.WebDelegateImpl;
import com.pyf.latte.ec.R;

/**
 * 发现界面
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/14
 */
public class DiscoverDelegate extends BottomItemDelegate {
    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        WebDelegateImpl delegate = WebDelegateImpl
                .create("index.html");
        delegate.setTopDelegate(getParentDelegate());
        getSupportDelegate().loadRootFragment(R.id.web_content_container, delegate);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }
}
