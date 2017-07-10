package com.pyf.latte.ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * 自定义字体图标库，使用阿里巴巴矢量图标库
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/10
 */
public class FontEcModule implements IconFontDescriptor {
    @Override
    public String ttfFileName() {
        return "iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return EcIcons.values();
    }
}
