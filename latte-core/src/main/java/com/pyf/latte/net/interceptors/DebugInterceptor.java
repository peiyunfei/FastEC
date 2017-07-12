package com.pyf.latte.net.interceptors;

import com.pyf.latte.utils.file.FileUtil;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 测试拦截器
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/12
 */
public class DebugInterceptor extends BaseInterceptor{

    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;

    public DebugInterceptor(String debugUrl, int debugRawId) {
        this.DEBUG_URL = debugUrl;
        this.DEBUG_RAW_ID = debugRawId;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String url = chain.request().url().toString();
        if(url.contains(DEBUG_URL)) {
            return debugResponse(chain,DEBUG_RAW_ID);
        }
        return chain.proceed(chain.request());
    }

    private Response debugResponse(Chain chain, int rawId) {
        String json = FileUtil.getRawFile(rawId);
        return getResponse(chain,json);
    }

    private Response getResponse(Chain chain, String json) {
        return new Response.Builder()
                .code(200)
                .addHeader("Content-type","application/json")
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .body(ResponseBody.create(MediaType.parse("application/json"),json))
                .build();

    }
}
