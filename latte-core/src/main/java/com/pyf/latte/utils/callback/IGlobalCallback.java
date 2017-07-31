package com.pyf.latte.utils.callback;

import android.support.annotation.Nullable;

public interface IGlobalCallback<T> {

    void executeCallback(@Nullable T args);
}
