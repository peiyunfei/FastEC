package com.pyf.latte.ec.main.personal;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.pyf.latte.delegate.LatteDelegate;
import com.pyf.latte.ec.main.personal.list.ListBean;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/8/3
 */
public class PersonalClickListener extends SimpleClickListener {

    private final LatteDelegate DELEGATE;

    public PersonalClickListener(LatteDelegate delegate) {
        DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ListBean listBean = (ListBean) adapter.getData().get(position);
        switch (listBean.getId()) {
            case 1:
                DELEGATE.getParentDelegate().getSupportDelegate().start(listBean.getDelegate());
                break;
            case 2:
                DELEGATE.getParentDelegate().getSupportDelegate().start(listBean.getDelegate());
                break;
        }
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
