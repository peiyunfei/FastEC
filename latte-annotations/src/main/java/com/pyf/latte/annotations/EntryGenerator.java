package com.pyf.latte.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/13
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface EntryGenerator {

    String packageName();

    Class<?> entryTemplate();
}
