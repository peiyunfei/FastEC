package com.pyf.latte.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.joanzapata.iconify.widget.IconTextView;
import com.pyf.latte.ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义星级评分控件
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/8/5
 */
public class StarLayout extends LinearLayoutCompat implements View.OnClickListener {

    // 未选中的星级评分图标
    private static final CharSequence ICON_UN_SELECT = "{fa-star-o}";
    // 选中的星级评分图标
    private static final CharSequence ICON_SELECT = "{fa-star}";
    // 星级评分图标的个数
    private static final int STAR_TOTAL_COUNT = 5;
    // 存储星级评分图标的集合
    private static final List<IconTextView> STARS = new ArrayList<>();

    public StarLayout(Context context) {
        this(context, null);
    }

    public StarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initStarIcon();
    }

    /**
     * 初始化星级评分图标
     */
    private void initStarIcon() {
        for (int i = 0; i < STAR_TOTAL_COUNT; i++) {
            IconTextView star = new IconTextView(getContext());
            star.setGravity(Gravity.CENTER);
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            // 5个星星平分
            lp.weight = 1;
            star.setLayoutParams(lp);
            star.setText(ICON_UN_SELECT);
            // 当前星星的下标
            star.setTag(R.id.star_count, i);
            // 默认没有选中
            star.setTag(R.id.star_is_select, false);
            STARS.add(star);
            star.setOnClickListener(this);
            addView(star);
        }
    }

    @Override
    public void onClick(View v) {
        IconTextView star = (IconTextView) v;
        int count = (int) star.getTag(R.id.star_count);
        boolean isSelect = (boolean) star.getTag(R.id.star_is_select);
        // 没有选中
        if (!isSelect) {
            selectStar(count);
        } else {
            unSelectStar(count);
        }
    }

    private void unSelectStar(int count) {
        for (int i = count; i < STAR_TOTAL_COUNT; i++) {
            IconTextView star = STARS.get(i);
            star.setText(ICON_UN_SELECT);
            star.setTextColor(Color.GRAY);
            star.setTag(R.id.star_is_select, false);
        }
    }

    private void selectStar(int count) {
        for (int i = 0; i <= count; i++) {
            IconTextView star = STARS.get(i);
            star.setText(ICON_SELECT);
            star.setTextColor(Color.RED);
            star.setTag(R.id.star_is_select, true);
        }
    }

    public int getStarCount() {
        int count = 0;
        for (int i = 0; i < STAR_TOTAL_COUNT; i++) {
            IconTextView star = STARS.get(i);
            boolean isSelect = (boolean) star.getTag(R.id.star_is_select);
            if (isSelect) {
                count++;
            }
        }
        return count;
    }
}
