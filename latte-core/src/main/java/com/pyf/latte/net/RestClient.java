package com.pyf.latte.net;

import android.content.Context;

import com.pyf.latte.download.DownloadHandler;
import com.pyf.latte.net.callback.IError;
import com.pyf.latte.net.callback.IFailure;
import com.pyf.latte.net.callback.IRequest;
import com.pyf.latte.net.callback.ISuccess;
import com.pyf.latte.net.callback.RequestCallBacks;
import com.pyf.latte.ui.loader.LatterLoader;
import com.pyf.latte.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * 构造者模式构建RestClient对象
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/11
 */
public class RestClient {

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
    // 请求体
    private final RequestBody BODY;
    // 上下文
    private final Context CONTEXT;
    // 加载进度条的样式
    private final LoaderStyle LOADER_STYLE;
    // 文件
    private final File FILE;

    public RestClient(String url,
                      String downloadDir,
                      String extension,
                      String name,
                      Map<String, Object> params,
                      IError iError,
                      IFailure iFailure,
                      ISuccess iSuccess,
                      IRequest iRequest,
                      RequestBody body,
                      Context context,
                      LoaderStyle loaderStyle,
                      File file) {
        this.URL = url;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.PARAMS.putAll(params);
        this.IERROR = iError;
        this.IFAILURE = iFailure;
        this.ISUCCESS = iSuccess;
        this.IREQUEST = iRequest;
        this.BODY = body;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
        this.FILE = file;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    /**
     * 发送网络请求
     *
     * @param method
     *         请求方法
     */
    private void request(HttpMethod method) {
        RestService restService = RestCreator.getRestService();
        if (IREQUEST != null) {
            // 开始网络请求
            IREQUEST.onRequestStart();
        }
        if (LOADER_STYLE != null) {
            // 显示进度条
            LatterLoader.showLoading(CONTEXT, LOADER_STYLE);
        }
        Call<String> call = null;
        // 创建请求
        switch (method) {
            case GET:
                call = restService.get(URL, PARAMS);
                break;
            case POST:
                call = restService.post(URL, PARAMS);
                break;
            case PUT:
                call = restService.put(URL, PARAMS);
                break;
            case DELETE:
                call = restService.delete(URL, PARAMS);
                break;
            case POST_RAW:
                call = restService.postRaw(URL, BODY);
                break;
            case PUT_RAW:
                call = restService.putRaw(URL, BODY);
                break;
            case UPLOAD:
                RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = restService.upload(URL, body);
                break;
            default:
                break;
        }
        // 执行请求
        call.enqueue(getRequestCallBacks());
    }

    private Callback<String> getRequestCallBacks() {
        return new RequestCallBacks(
                IERROR,
                IFAILURE,
                ISUCCESS,
                IREQUEST,
                LOADER_STYLE);
    }

    /**
     * get请求
     */
    public final void get() {
        request(HttpMethod.GET);
    }

    /**
     * post请求
     */
    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be empty");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    /**
     * put请求
     */
    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be empty");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    /**
     * delete请求
     */
    public final void delete() {
        request(HttpMethod.DELETE);
    }

    /**
     * upload请求
     */
    public final void upload() {
        request(HttpMethod.UPLOAD);
    }

    /**
     * 下载文件
     */
    public final void download() {
        new DownloadHandler(URL, DOWNLOAD_DIR, EXTENSION, NAME,
                IERROR, IFAILURE, ISUCCESS, IREQUEST).handlerDownload();
    }
}