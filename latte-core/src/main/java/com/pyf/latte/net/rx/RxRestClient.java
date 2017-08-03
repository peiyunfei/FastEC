package com.pyf.latte.net.rx;

import android.content.Context;

import com.pyf.latte.net.HttpMethod;
import com.pyf.latte.net.RestCreator;
import com.pyf.latte.ui.loader.LatterLoader;
import com.pyf.latte.ui.loader.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import static com.pyf.latte.net.RestCreator.getRxRestService;

/**
 * 构造者模式构建RestClient对象
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/11
 */
public class RxRestClient {

    // 网络访问地址
    private final String URL;
    // 存储请求参数的集合
    private final WeakHashMap<String, Object> PARAMS;
    // 请求体
    private final RequestBody BODY;
    // 上下文
    private final Context CONTEXT;
    // 加载进度条的样式
    private final LoaderStyle LOADER_STYLE;
    // 文件
    private final File FILE;

    public RxRestClient(String url,
                        RequestBody body,
                        WeakHashMap<String, Object> params,
                        Context context,
                        LoaderStyle loaderStyle,
                        File file) {
        this.URL = url;
        this.BODY = body;
        this.PARAMS = params;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
        this.FILE = file;
    }

    public static RxRestClientBuilder builder() {
        return new RxRestClientBuilder();
    }

    /**
     * 发送网络请求
     *
     * @param method
     *         请求方法
     */
    private Observable<String> request(HttpMethod method) {
        RxRestService restService = getRxRestService();
        if (LOADER_STYLE != null) {
            // 显示进度条
            LatterLoader.showLoading(CONTEXT, LOADER_STYLE);
        }
        Observable<String> observable = null;
        // 创建请求
        switch (method) {
            case GET:
                observable = restService.get(URL, PARAMS);
                break;
            case POST:
                observable = restService.post(URL, PARAMS);
                break;
            case PUT:
                observable = restService.put(URL, PARAMS);
                break;
            case DELETE:
                observable = restService.delete(URL, PARAMS);
                break;
            case POST_RAW:
                observable = restService.postRaw(URL, BODY);
                break;
            case PUT_RAW:
                observable = restService.putRaw(URL, BODY);
                break;
            case UPLOAD:
                RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                observable = restService.upload(URL, body);
                break;
            default:
                break;
        }
        return observable;
    }

    /**
     * get请求
     */
    public final Observable<String> get() {
        return request(HttpMethod.GET);
    }

    /**
     * post请求
     */
    public final Observable<String> post() {
        if (BODY == null) {
            return request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be empty");
            }
            return request(HttpMethod.POST_RAW);
        }
    }

    /**
     * put请求
     */
    public final Observable<String> put() {
        if (BODY == null) {
            return request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be empty");
            }
            return request(HttpMethod.PUT_RAW);
        }
    }

    /**
     * delete请求
     */
    public final Observable<String> delete() {
        return request(HttpMethod.DELETE);
    }

    /**
     * upload请求
     */
    public final Observable<String> upload() {
        return request(HttpMethod.UPLOAD);
    }

    /**
     * 下载文件
     */
    public final Observable<ResponseBody> download() {
        return RestCreator.getRxRestService().download(URL, PARAMS);
    }
}