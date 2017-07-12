package com.pyf.latte.utils.timer;

/**
 * 调度任务接口
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/12
 */
public interface ITimerListener {

    /**
     * 执行调度任务
     */
    void onTimer();
}
