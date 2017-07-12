package com.pyf.fastec.activity;

import com.pyf.latte.activity.ProxyActivity;
import com.pyf.latte.delegate.LatteDelegate;
import com.pyf.latte.ec.launcher.LauncherDelegate;

/**
 * 用于测试框架的activity
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/10
 */
public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }
}
