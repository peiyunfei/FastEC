package com.pyf.latte.delegate.bottom;

import android.widget.Toast;

import com.pyf.latte.R;
import com.pyf.latte.app.Latte;
import com.pyf.latte.delegate.LatteDelegate;

/**
 * 底部导航栏各个界面的基类
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/14
 */
public abstract class BottomItemDelegate extends LatteDelegate {

    // 延迟时间
    private static final long WAIT_TIME = 2000;
    // 按下的时间
    private static long TOUCH_TIME = 0;

    /**
     * 再按一次退出
     */
    @Override
    public boolean onBackPressedSupport() {
        if ((System.currentTimeMillis() - TOUCH_TIME) < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "再按一次退出" + Latte.getApplicationContext()
                    .getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
