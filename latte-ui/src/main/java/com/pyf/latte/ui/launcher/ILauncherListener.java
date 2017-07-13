package com.pyf.latte.ui.launcher;

/**
 * 启动监听
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/13
 */
public interface ILauncherListener {

    /**
     * 启动结束后回调
     */
    void onLauncherFinish(OnLauncherFinishTag tag);
}
