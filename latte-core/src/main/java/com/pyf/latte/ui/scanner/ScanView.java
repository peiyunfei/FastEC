package com.pyf.latte.ui.scanner;

import android.content.Context;
import android.util.AttributeSet;

import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/8/7
 */

public class ScanView extends ZBarScannerView {
    public ScanView(Context context) {
        this(context, null);
    }

    public ScanView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
