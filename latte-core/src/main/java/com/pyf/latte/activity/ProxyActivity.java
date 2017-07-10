package com.pyf.latte.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.pyf.latte.R;
import com.pyf.latte.delegate.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * activity基类
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/10
 */
public abstract class ProxyActivity extends SupportActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    /**
     * 初始化父容器
     *
     * @param savedInstanceState
     *         用于保存activity的状态
     */
    private void initContainer(Bundle savedInstanceState) {
        ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        // 在页面重启时，Fragment会被保存恢复，而此时再加载Fragment会重复加载，导致重叠
        // 第一次加载（当页面没有重启时），加载根布局
        if (savedInstanceState == null) {
            // 加载根布局
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }


    /**
     * 设置根布局，子类必须实现
     *
     * @return 根布局
     */
    public abstract LatteDelegate setRootDelegate();
}
