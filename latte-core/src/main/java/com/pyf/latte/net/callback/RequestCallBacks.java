package com.pyf.latte.net.callback;

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

    public RequestCallBacks(IError iError,
                            IFailure iFailure,
                            ISuccess iSuccess,
                            IRequest iRequest) {
        this.ERROR = iError;
        this.FAILURE = iFailure;
        this.SUCCESS = iSuccess;
        this.REQUEST = iRequest;
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
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
    }
}
