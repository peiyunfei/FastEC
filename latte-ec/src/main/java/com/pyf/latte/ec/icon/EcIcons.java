package com.pyf.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * 从阿里巴巴矢量图标库下载的图标
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/10
 */
public enum EcIcons implements Icon {

    // 二维码扫描图标
    icon_scan('\ue602'),
    // 支付宝图标
    icon_ali_pay('\ue606');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
