package com.pyf.latte.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.pyf.latte.app.AccountManager;
import com.pyf.latte.app.IUserCheck;
import com.pyf.latte.delegate.LatteDelegate;
import com.pyf.latte.ec.R;
import com.pyf.latte.ec.R2;
import com.pyf.latte.ui.launcher.ILauncherListener;
import com.pyf.latte.ui.launcher.OnLauncherFinishTag;
import com.pyf.latte.ui.launcher.ScrollLauncherTag;
import com.pyf.latte.utils.storage.LattePreference;
import com.pyf.latte.utils.timer.BaseTimerTask;
import com.pyf.latte.utils.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 欢迎界面，界面有一个倒计时按钮
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/12
 */
public class LauncherDelegate extends LatteDelegate implements ITimerListener {

    // 倒计时按钮
    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer;
    // 定时器
    private Timer mTimer;
    // 倒计时5秒
    private int mCount = 5;

    // 启动监听
    private ILauncherListener mILauncherListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    /**
     * 为倒计时按钮添加事件
     */
    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {
        cancel();
        checkIsShowScroll();
    }

    /**
     * 初始化定时器
     */
    private void initTimer() {
        // 创建定时器
        mTimer = new Timer();
        // 创建倒计时任务
        BaseTimerTask task = new BaseTimerTask(this);
        // 执行倒计时任务
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    /**
     * 检查是否进入滑动启动界面
     */
    private void checkIsShowScroll() {
        boolean flag = LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name());
        // 不是第一次进入应用
        if (!flag) {
            start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
            // 判断是否登录
            AccountManager.checkAccount(new IUserCheck() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {
                        // 已经登录
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null) {
                        // 没有登录
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }

    /**
     * 执行倒计时任务
     */
    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                // 倒计时减一
                mCount--;
                if (mCount < 0) {
                    // 取消倒计时
                    cancel();
                    // 检查是否进入滑动启动界面
                    checkIsShowScroll();
                }
            }
        });
    }

    /**
     * 停止倒计时
     */
    private void cancel() {
        if (mTimer != null) {
            // 停止倒计时
            mTimer.cancel();
            mTimer = null;
        }
    }
}
