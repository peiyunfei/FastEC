package com.pyf.fastec.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.pyf.latte.activity.ProxyActivity;
import com.pyf.latte.app.Latte;
import com.pyf.latte.delegate.LatteDelegate;
import com.pyf.latte.ec.launcher.LauncherDelegate;
import com.pyf.latte.ec.main.EcBottomDelegate;
import com.pyf.latte.ec.sign.ISIgnListener;
import com.pyf.latte.ec.sign.SignInDelegate;
import com.pyf.latte.ui.launcher.ILauncherListener;
import com.pyf.latte.ui.launcher.OnLauncherFinishTag;

import qiu.niorgai.StatusBarCompat;

/**
 * 用于测试框架的activity
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/10
 */
public class ExampleActivity extends ProxyActivity implements
        ILauncherListener,
        ISIgnListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Latte.getConfigurator().withActivity(this);
        // 去掉状态栏
        StatusBarCompat.translucentStatusBar(this, true);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(ExampleActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(ExampleActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(ExampleActivity.this, "用户已登录", Toast.LENGTH_SHORT).show();
                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(ExampleActivity.this, "用户未登录", Toast.LENGTH_SHORT).show();
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
        }
    }
}
