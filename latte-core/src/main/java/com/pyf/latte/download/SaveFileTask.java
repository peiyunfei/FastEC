package com.pyf.latte.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.pyf.latte.app.Latte;
import com.pyf.latte.net.callback.IRequest;
import com.pyf.latte.net.callback.ISuccess;
import com.pyf.latte.utils.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * 开启异步任务将文件写入磁盘
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/11
 */
public class SaveFileTask extends AsyncTask<Object, Void, File> {

    // 请求成功
    private final ISuccess ISUCCESS;
    // 开始访问网络和结束访问网络
    private final IRequest IREQUEST;

    public SaveFileTask(ISuccess ISUCCESS, IRequest IREQUEST) {
        this.ISUCCESS = ISUCCESS;
        this.IREQUEST = IREQUEST;
    }

    @Override
    protected File doInBackground(Object... params) {
        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        ResponseBody responseBody = (ResponseBody) params[2];
        String name = (String) params[3];
        InputStream is = responseBody.byteStream();
        if (TextUtils.isEmpty(downloadDir)) {
            downloadDir = "downloads";
        }
        if (TextUtils.isEmpty(extension)) {
            extension = "";
        }
        if (TextUtils.isEmpty(name)) {
            return FileUtil.writeToDisk(is, downloadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(is, downloadDir, name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (ISUCCESS != null) {
            ISUCCESS.onSuccess(file.getAbsolutePath());
        }
        if (IREQUEST != null) {
            IREQUEST.onRequestEnd();
        }
        autoInstallApk(file);
    }

    /**
     * 安装文件
     */
    private void autoInstallApk(File file) {
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Latte.getApplicationContext().startActivity(install);
        }
    }
}
