package com.pyf.latte.ui.recycler;

import com.google.auto.value.AutoValue;

/**
 * 状态栏渐变的颜色
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/16
 */
@AutoValue
public abstract class RgbValue {

    public abstract int red();

    public abstract int green();

    public abstract int blue();

    public static RgbValue create(int red, int green, int blue) {
        return new AutoValue_RgbValue(red, green, blue);
    }
}
