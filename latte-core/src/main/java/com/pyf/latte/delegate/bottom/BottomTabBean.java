package com.pyf.latte.delegate.bottom;

/**
 * 底部导航栏的数据，包括图标和标题
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/14
 */
public class BottomTabBean {

    // 图标
    private final CharSequence ICON;
    // 标题
    private final CharSequence TITLE;

    public BottomTabBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }

}
