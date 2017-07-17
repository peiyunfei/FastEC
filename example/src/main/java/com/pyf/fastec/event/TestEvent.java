package com.pyf.fastec.event;

import android.widget.Toast;

import com.pyf.latte.delegate.web.event.Event;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/17
 */

public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(), params, Toast.LENGTH_SHORT).show();
        return null;
    }
}
