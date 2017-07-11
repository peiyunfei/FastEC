package com.pyf.latte.ui.loader;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.pyf.latte.R;
import com.pyf.latte.utils.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用对话框来显示进度条
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/11
 */
public class LatterLoader {

    // 缩放比例
    private static final int LOADER_SIZE_SCALE = 6;
    // 偏移比例
    private static final int LOADER_OFFSET_SCALE = 8;

    // 存储对话框的集合
    private static final List<AppCompatDialog> LOADERS = new ArrayList<>();
    // 默认进度条样式
    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();

    /**
     * 显示进度条
     *
     * @param context
     *         上下文
     * @param type
     *         进度条类型
     */
    public static void showLoading(Context context, String type) {
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(context, type);
        dialog.setContentView(avLoadingIndicatorView);
        int screenWidth = DimenUtil.getScreenWidth();
        int screenHeight = DimenUtil.getScreenHeight();
        Window dialogWindow = dialog.getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            // 确定高度
            lp.height = screenHeight / LOADER_SIZE_SCALE;
            lp.height = lp.height + screenHeight / LOADER_OFFSET_SCALE;
            // 确定宽度
            lp.width = screenWidth / LOADER_SIZE_SCALE;
            // 居中
            lp.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }

    /**
     * 显示进度条
     *
     * @param context
     *         上下文
     * @param type
     *         进度条样式
     */
    public static void showLoading(Context context, Enum<LoaderStyle> type) {
        showLoading(context, type.name());
    }

    /**
     * 显示默认的对话框
     *
     * @param context
     *         上下文
     */
    public static void showLoading(Context context) {
        showLoading(context, DEFAULT_LOADER);
    }

    /**
     * 隐藏进度条
     */
    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null) {
                dialog.cancel();
            }
        }
    }
}
