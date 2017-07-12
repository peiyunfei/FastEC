package com.pyf.latte.utils.timer;

import java.util.TimerTask;

/**
 * 调度任务
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/12
 */
public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener;

    public BaseTimerTask(ITimerListener ITimerListener) {
        mITimerListener = ITimerListener;
    }

    @Override
    public void run() {
        if (mITimerListener != null) {
            mITimerListener.onTimer();
        }
    }
}
