package com.pyf.latte.net.callback;

import android.os.Handler;

import com.pyf.latte.net.RestCreator;
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

    private final IError IERROR;
    private final IFailure IFAILURE;
    private final ISuccess ISUCCESS;
    private final IRequest IREQUEST;
    private final LoaderStyle LOADER_STYLE;
    private static Handler mHandler = new Handler();

    public RequestCallBacks(IError iError,
                            IFailure iFailure,
                            ISuccess iSuccess,
                            IRequest iRequest,
                            LoaderStyle loaderStyle) {
        this.IERROR = iError;
        this.IFAILURE = iFailure;
        this.ISUCCESS = iSuccess;
        this.IREQUEST = iRequest;
        this.LOADER_STYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (ISUCCESS != null) {
                    ISUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (IERROR != null) {
                IERROR.onError(response.code(), response.message());
            }
        }
        if (IREQUEST != null) {
            IREQUEST.onRequestEnd();
        }
        onRequestFinish();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (IFAILURE != null) {
            IFAILURE.onFailure();
        }
        if (IREQUEST != null) {
            IREQUEST.onRequestEnd();
        }
        onRequestFinish();
    }

    private void onRequestFinish() {
        if(LOADER_STYLE!=null) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    RestCreator.getParams().clear();
                    LatterLoader.stopLoading();
                }
            }, 1000);
        }
    }
}
