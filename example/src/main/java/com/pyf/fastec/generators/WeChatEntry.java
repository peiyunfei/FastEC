package com.pyf.fastec.generators;

import com.pyf.latte.annotations.EntryGenerator;
import com.pyf.latte.wechat.templates.WXEntryTemplate;

@SuppressWarnings("unused")
@EntryGenerator(
        packageName = "com.pyf.fastec.example",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
