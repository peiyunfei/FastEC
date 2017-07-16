package com.pyf.latte.delegate;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/10
 */
public abstract class LatteDelegate extends PermissionCheckDelegate {

    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate(){
        return (T) getParentFragment();
    }
}
