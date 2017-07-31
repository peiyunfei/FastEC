package com.pyf.latte.ui.camera;

import android.net.Uri;

import com.pyf.latte.delegate.PermissionCheckDelegate;
import com.pyf.latte.utils.file.FileUtil;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/31
 */
public class LatteCamera {

    public static Uri createCropFile() {
        return Uri.parse
                (FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckDelegate delegate) {
        new CameraHandler(delegate).showCameraDialog();
    }
}
