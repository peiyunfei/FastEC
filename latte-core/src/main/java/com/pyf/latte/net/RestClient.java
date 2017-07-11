package com.pyf.latte.net;

import com.pyf.latte.net.callback.IError;
import com.pyf.latte.net.callback.IFailure;
import com.pyf.latte.net.callback.IRequest;
import com.pyf.latte.net.callback.ISuccess;
import com.pyf.latte.net.callback.RequestCallBacks;

import java.util.Map;
import java.util.WeakHashMap;

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

    private final String URL;
    private final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IError ERROR;
    private final IFailure FAILURE;
    private final ISuccess SUCCESS;
    private final IRequest REQUEST;
    private final RequestBody BODY;

    public RestClient(String url,
                      Map<String, Object> params,
                      IError iError,
                      IFailure iFailure,
                      ISuccess iSuccess,
                      IRequest iRequest,
                      RequestBody body) {
        this.URL = url;
        this.PARAMS.putAll(params);
        this.ERROR = iError;
        this.FAILURE = iFailure;
        this.SUCCESS = iSuccess;
        this.REQUEST = iRequest;
        this.BODY = body;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        RestService restService = RestCreator.getRestService();
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
                break;
        }
        // 执行请求
        call.enqueue(getRequestCallBacks());
    }

    private Callback<String> getRequestCallBacks() {
        return new RequestCallBacks(ERROR, FAILURE, SUCCESS, REQUEST);
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
        request(HttpMethod.POST);
    }

    /**
     * put请求
     */
    public final void put() {
        request(HttpMethod.PUT);
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
}