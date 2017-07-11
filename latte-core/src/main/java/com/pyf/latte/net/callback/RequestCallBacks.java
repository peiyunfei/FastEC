package com.pyf.latte.net.callback;

import android.os.Handler;

import com.pyf.latte.ui.loader.LatterLoader;
import com.pyf.latte.ui.loader.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/11
 */

public class RequestCallBacks implements Callback<String> {

    private final IError ERROR;
    private final IFailure FAILURE;
    private final ISuccess SUCCESS;
    private final IRequest REQUEST;
    private final LoaderStyle LOADER_STYLE;
    private static Handler mHandler = new Handler();

    public RequestCallBacks(IError iError,
                            IFailure iFailure,
                            ISuccess iSuccess,
                            IRequest iRequest,
                            LoaderStyle loaderStyle) {
        this.ERROR = iError;
        this.FAILURE = iFailure;
        this.SUCCESS = iSuccess;
        this.REQUEST = iRequest;
        this.LOADER_STYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        onRequestFinish();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        onRequestFinish();
    }

    private void onRequestFinish() {
        if(LOADER_STYLE!=null) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatterLoader.stopLoading();
                }
            }, 1000);
        }
    }
}
