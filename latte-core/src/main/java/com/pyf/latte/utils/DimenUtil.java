package com.pyf.latte.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.pyf.latte.app.Latte;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/11
 */

public class DimenUtil {

    /**
     * 获取屏幕的宽度
     *
     * @return 屏幕的宽度
     */
    public static int getScreenWidth() {
        Context context = Latte.getApplicationContext();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    /**
     * 获取屏幕的高度
     *
     * @return 屏幕的高度
     */
    public static int getScreenHeight() {
        Context context = Latte.getApplicationContext();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }
}
