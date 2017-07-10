package com.pyf.latte.net;

import com.pyf.latte.app.ConfigType;
import com.pyf.latte.app.Latte;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 创建网络框架实例
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/10
 */
public class RestCreator {

    /**
     * 获取http请求接口实例
     *
     * @return http请求接口实例
     */
    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    /**
     * 创建retrofit实例
     */
    private static final class RetrofitHolder {
        private static final String BASE_URL =
                (String) Latte.getConfigurator().getLatteConfigs().get(ConfigType.API_HOST.name());
        private static final Retrofit RETROFIT_CLENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(OkHttpHolder.OKHTTP_CLIENT)
                .build();
    }

    /**
     * 创建OkHttpClient实例
     */
    private static final class OkHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient OKHTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 创建http请求接口实例
     */
    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLENT.create(RestService.class);
    }
}