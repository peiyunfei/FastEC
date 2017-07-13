package com.pyf.fastec.generators;

import com.pyf.latte.annotations.AppRegisterGenerator;
import com.pyf.latte.wechat.templates.AppRegisterTemplate;

@SuppressWarnings("unused")
@AppRegisterGenerator(
        packageName = "com.pyf.fastec.example",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
