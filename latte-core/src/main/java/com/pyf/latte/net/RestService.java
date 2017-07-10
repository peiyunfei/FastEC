package com.pyf.latte.net;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * http请求接口
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/10
 */
public interface RestService {

    /**
     * get请求
     *
     * @param url
     *         请求地址
     * @param params
     *         请求参数
     * @return call实例，通过回调获取服务器返回的数据
     */
    @GET
    Call<String> get(@Url String url, @QueryMap Map<String, Object> params);

    /**
     * post请求
     *
     * @param url
     *         请求地址
     * @param params
     *         请求参数
     * @return call实例，通过回调获取服务器返回的数据
     */
    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap Map<String, Object> params);

    /**
     * put请求
     *
     * @param url
     *         请求地址
     * @param params
     *         请求参数
     * @return call实例，通过回调获取服务器返回的数据
     */
    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url, @FieldMap Map<String, Object> params);

    /**
     * delete请求
     *
     * @param url
     *         请求地址
     * @param params
     *         请求参数
     * @return call实例，通过回调获取服务器返回的数据
     */
    @DELETE
    Call<String> delete(@Url String url, @QueryMap Map<String, Object> params);

    /**
     * 下载请求
     *
     * @param url
     *         请求地址
     * @param params
     *         请求参数
     * @return call实例，通过回调获取服务器返回的数据
     */
    @Streaming
    @GET
    Call<RequestBody> download(@Url String url, @QueryMap Map<String, Object> params);

    /**
     * 上传文件请求
     *
     * @param url
     *         请求地址
     * @param file
     *         要上传的文件
     * @return call实例，通过回调获取服务器返回的数据
     */
    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part file);
}
