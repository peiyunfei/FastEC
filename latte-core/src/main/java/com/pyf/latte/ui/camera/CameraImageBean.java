package com.pyf.latte.ui.camera;

import android.net.Uri;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/31
 */
public class CameraImageBean {

    private Uri mPath = null;

    private static final CameraImageBean INSTANCE = new CameraImageBean();

    public static CameraImageBean getInstance(){
        return INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri mPath) {
        this.mPath = mPath;
    }

}
