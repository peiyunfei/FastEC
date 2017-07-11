package com.pyf.latte.download;

import android.os.AsyncTask;

import com.pyf.latte.net.RestCreator;
import com.pyf.latte.net.RestService;
import com.pyf.latte.net.callback.IError;
import com.pyf.latte.net.callback.IFailure;
import com.pyf.latte.net.callback.IRequest;
import com.pyf.latte.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 文件下载处理器
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/11
 */
public class DownloadHandler {

    // 网络访问地址
    private final String URL;
    // 文件下载路径
    private final String DOWNLOAD_DIR;
    // 文件扩展名
    private final String EXTENSION;
    // 文件名
    private final String NAME;
    // 存储请求参数的集合
    private final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    // 请求错误
    private final IError IERROR;
    // 请求失败
    private final IFailure IFAILURE;
    // 请求成功
    private final ISuccess ISUCCESS;
    // 开始访问网络和结束访问网络
    private final IRequest IREQUEST;

    public DownloadHandler(String url,
                           String downloadDir,
                           String extension,
                           String name,
                           IError iError,
                           IFailure iFailure,
                           ISuccess iSuccess,
                           IRequest iRequest) {

        this.URL = url;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.IERROR = iError;
        this.IFAILURE = iFailure;
        this.ISUCCESS = iSuccess;
        this.IREQUEST = iRequest;
    }

    /**
     * 处理下载
     */
    public void handlerDownload() {
        if (IREQUEST != null) {
            // 开始下载
            IREQUEST.onRequestStart();
        }
        RestService restService = RestCreator.getRestService();
        Call<ResponseBody> call = restService.download(URL, PARAMS);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    ResponseBody body = response.body();
                    SaveFileTask saveFileTask = new SaveFileTask(ISUCCESS, IREQUEST);
                    saveFileTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                            DOWNLOAD_DIR, EXTENSION, body, NAME);
                    // 这里一定要注意判断，否则文件下载不全
                    if (saveFileTask.isCancelled()) {
                        if (IREQUEST != null) {
                            IREQUEST.onRequestEnd();
                        }
                    }
                } else {
                    if (IERROR != null) {
                        IERROR.onError(response.code(), response.message());
                    }
                }
                RestCreator.getParams().clear();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (IFAILURE != null) {
                    IFAILURE.onFailure();
                    RestCreator.getParams().clear();
                }
            }
        });
    }
}