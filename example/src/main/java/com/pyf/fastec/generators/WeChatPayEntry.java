package com.pyf.fastec.generators;

import com.pyf.latte.annotations.PayEntryGenerator;
import com.pyf.latte.wechat.templates.WXPayEntryTemplate;

@SuppressWarnings("unused")
@PayEntryGenerator(
        packageName = "com.pyf.fastec.example",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
