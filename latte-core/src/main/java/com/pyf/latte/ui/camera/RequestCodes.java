package com.pyf.latte.ui.camera;

import com.yalantis.ucrop.UCrop;

/**
 * 请求码
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/31
 */
public class RequestCodes {

    // 拍照
    public static final int TAKE_PHOTO = 4;
    // 相册
    public static final int PICK_PHOTO = 5;
    // 裁剪照片
    public static final int CROP_PHOTO = UCrop.REQUEST_CROP;
    // 裁剪失败
    public static final int CROP_ERROR = UCrop.RESULT_ERROR;
    // 扫描
    public static final int SCAN = 7;
}
