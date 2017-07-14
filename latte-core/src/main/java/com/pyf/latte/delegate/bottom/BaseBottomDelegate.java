package com.pyf.latte.delegate.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.joanzapata.iconify.widget.IconTextView;
import com.pyf.latte.R;
import com.pyf.latte.R2;
import com.pyf.latte.delegate.LatteDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;

/**
 * 底部导航栏
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/14
 */
public abstract class BaseBottomDelegate extends LatteDelegate implements View.OnClickListener {

    // 存储底部导航栏界面的集合
    private final List<BottomItemDelegate> ITEM_DELEGATE = new ArrayList<>();
    // 存储底部导航栏数据的集合
    private final List<BottomTabBean> TAB_BEAN = new ArrayList<>();
    // 存储底部导航栏数据和界面的集合
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();
    // 默认显示首页界面
    private int mIndexDelegate = 0;
    // 当前显示的界面
    private int mCurrentDelegate = 0;
    // 点击后的颜色
    private int mClickColor = Color.RED;

    /**
     * 底部导航栏
     */
    @BindView(R2.id.ll_bottom_bar)
    LinearLayoutCompat mBottomBar;

    /**
     * 设置底部导航栏的数据和界面
     *
     * @return 存储底部导航栏数据和界面的集合
     */
    protected abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder);

    /**
     * 设置按下的颜色
     *
     * @return 按下的颜色
     */
    protected abstract int setClickColor();

    /**
     * 设置默认显示界面的下标
     *
     * @return 默认显示界面的下标
     */
    protected abstract int setIndexDelegate();

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置默认显示界面的下标
        mIndexDelegate = setIndexDelegate();
        if (mClickColor != 0) {
            // 设置按下的颜色
            mClickColor = setClickColor();
        }
        ItemBuilder builder = ItemBuilder.builder();
        // 设置底部导航栏的数据和界面
        LinkedHashMap<BottomTabBean, BottomItemDelegate> items = setItems(builder);
        // 添加到集合
        ITEMS.putAll(items);
        // 遍历，取出集合里面存储的底部导航栏的数据和界面
        for (Map.Entry<BottomTabBean, BottomItemDelegate> entry : ITEMS.entrySet()) {
            BottomTabBean bottomTabBean = entry.getKey();
            BottomItemDelegate bottomItemDelegate = entry.getValue();
            // 将底部导航栏的数据放入集合
            TAB_BEAN.add(bottomTabBean);
            // 将底部导航栏的界面放入集合
            ITEM_DELEGATE.add(bottomItemDelegate);
        }
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        int size = ITEMS.size();
        for (int i = 0; i < size; i++) {
            LayoutInflater.from(getContext())
                    .inflate(R.layout.bottom_item_icon_text_layout, mBottomBar);
            RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            item.setTag(i);
            item.setOnClickListener(this);
            IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            BottomTabBean tabBean = TAB_BEAN.get(i);
            // 初始化数据
            itemIcon.setText(tabBean.getIcon());
            itemTitle.setText(tabBean.getTitle());
            // 默认的状态
            if (i == mIndexDelegate) {
                // 按下的颜色
                itemIcon.setTextColor(mClickColor);
                itemTitle.setTextColor(mClickColor);
            }
        }
        final ISupportFragment[] delegateArray = ITEM_DELEGATE.toArray(new ISupportFragment[size]);
        // 加载各个界面
        getSupportDelegate().loadMultipleRootFragment(R.id.fl_content, mIndexDelegate, delegateArray);
    }

    /**
     * 重置图标和字体的颜色
     */
    private void resetColor() {
        int size = mBottomBar.getChildCount();
        for (int i = 0; i < size; i++) {
            RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            // 设置颜色为灰色
            itemIcon.setTextColor(Color.GRAY);
            itemTitle.setTextColor(Color.GRAY);
        }
    }

    @Override
    public void onClick(View v) {
        // 重置图标和字体的颜色
        resetColor();
        int tag = (int) v.getTag();
        RelativeLayout item = (RelativeLayout) v;
        IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        // 设置颜色为按下的颜色
        itemIcon.setTextColor(mClickColor);
        itemTitle.setTextColor(mClickColor);
        // 显示按下的界面，隐藏之前的界面
        getSupportDelegate().showHideFragment(ITEM_DELEGATE.get(tag), ITEM_DELEGATE.get(mCurrentDelegate));
        mCurrentDelegate = tag;
    }
}
