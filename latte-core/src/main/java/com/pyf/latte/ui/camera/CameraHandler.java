package com.pyf.latte.ui.camera;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.FileUtils;
import com.pyf.latte.R;
import com.pyf.latte.delegate.PermissionCheckDelegate;
import com.pyf.latte.utils.file.FileUtil;

import java.io.File;

/**
 * 照片处理类
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/31
 */
public class CameraHandler implements View.OnClickListener {

    private final AlertDialog DIALOG;
    private final PermissionCheckDelegate DELEGATE;

    public CameraHandler(PermissionCheckDelegate delegate) {
        this.DELEGATE = delegate;
        DIALOG = new AlertDialog.Builder(delegate.getContext()).create();
    }

    public void showCameraDialog() {
        DIALOG.show();
        Window window = DIALOG.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_camera_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            layoutParams.dimAmount = 0.5f;
            window.setAttributes(layoutParams);
            window.findViewById(R.id.btn_camera_take_photo).setOnClickListener(this);
            window.findViewById(R.id.btn_camera_album).setOnClickListener(this);
            window.findViewById(R.id.btn_camera_cancel).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_camera_take_photo) {
            DIALOG.cancel();
            // 拍照
            takePhoto();
        }
        if (id == R.id.btn_camera_album) {
            DIALOG.cancel();
            // 从相册选择照片
            pickPhoto();
        }
        if (id == R.id.btn_camera_cancel) {
            DIALOG.cancel();
        }
    }

    private String getPhotoName() {
        return FileUtil.getFileNameByTime("IMG", "jpg");
    }

    private void takePhoto() {
        String photoName = getPhotoName();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File tempFile = new File(FileUtil.CAMERA_PHOTO_DIR, photoName);
        // 兼容7.0系统
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, tempFile.getPath());
            Uri uri = DELEGATE.getContext().getContentResolver()
                    .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            // 需要讲Uri路径转化为实际路径
            final File realFile =
                    FileUtils.getFileByPath(FileUtil.getRealFilePath(DELEGATE.getContext(), uri));
            final Uri realUri = Uri.fromFile(realFile);
            CameraImageBean.getInstance().setPath(realUri);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        } else {
            final Uri fileUri = Uri.fromFile(tempFile);
            CameraImageBean.getInstance().setPath(fileUri);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        }
        DELEGATE.startActivityForResult(intent, RequestCodes.TAKE_PHOTO);
    }

    /**
     * 从相册选择照片
     */
    private void pickPhoto() {
        final Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        DELEGATE.startActivityForResult
                (Intent.createChooser(intent, "选择获取图片的方式"), RequestCodes.PICK_PHOTO);
    }
}
