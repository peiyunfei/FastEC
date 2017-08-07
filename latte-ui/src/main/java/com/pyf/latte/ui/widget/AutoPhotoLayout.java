package com.pyf.latte.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.joanzapata.iconify.widget.IconTextView;
import com.pyf.latte.delegate.LatteDelegate;
import com.pyf.latte.ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义多图选择控件
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/8/6
 */
public class AutoPhotoLayout extends LinearLayout {

    // 当前图片的下标
    private int mCurrentNum;
    // 图片的最大数
    private int mMaxNum;
    // 每一行的图片个数
    private int mMaxLineNum;
    // 每张图片的间距
    private int mImageMargin;
    // 加号图标的大小
    private int mIconSize;
    // 加号图标
    private IconTextView mIconAdd;
    // 图片和加号图标的布局参数，主要是宽度和高度
    private LayoutParams mParams;
    // 要删除图片的id
    private int mDeleteId;
    // 是否为第一次测量
    private boolean mIsOnceInitOnMeasure = true;
    // 是否为第一次布局
    private boolean mHasInitOnLayout;
    // 加号图标的文本
    private static final String ICON_TEXT = "{fa-plus}";
    private AppCompatImageView mTargetImage;
    // 删除图片时弹出的对话框
    private AlertDialog mTargetDialog;
    private LatteDelegate mDelegate;
    // 存储每行图片的集合
    private List<View> mLineViews;
    // 存储所有图片的集合
    private final List<List<View>> ALL_VIEWS = new ArrayList<>();
    // 存储每一行高度的集合
    private final List<Integer> VIEW_HEIGHT = new ArrayList<>();
    private static final RequestOptions OPTIONS = new RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.NONE);

    public AutoPhotoLayout(Context context) {
        this(context, null);
    }

    public AutoPhotoLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoPhotoLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.camera_photo_layout);
        mIconSize = (int) a.getDimension(R.styleable.camera_photo_layout_icon_size, 20);
        mImageMargin = a.getInt(R.styleable.camera_photo_layout_item_margin, 3);
        mMaxLineNum = a.getInt(R.styleable.camera_photo_layout_line_count, 3);
        mMaxNum = a.getInt(R.styleable.camera_photo_layout_max_count, 1);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = 0;
        int height = 0;
        // 每一行的宽度
        int lineWidth = 0;
        // 每一行的高度
        int lineHeight = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            // 获取子控件
            View child = getChildAt(i);
            // 测量子控件
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            // 获取子控件的布局参数
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            // 子控件的宽度
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            // 子控件的高度
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            // 换行
            if (lineWidth + childWidth > widthSize - getPaddingLeft() - getPaddingRight()) {
                // 设置总体的宽度
                width = Math.max(width, lineWidth);
                // 设置下一行的宽度
                lineWidth = childWidth;
                // 总体高度叠加
                height += lineHeight;
                // 设置下一行的高度
                lineHeight = childHeight;
            } else {
                // 未换行
                // 叠加行宽度
                lineWidth += childWidth;
                // 设置行高度
                lineHeight = Math.max(lineHeight, childHeight);
            }
            if (i == count - 1) {
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }
        }
        setMeasuredDimension(
                widthMode == MeasureSpec.EXACTLY ? widthSize :
                        width + getPaddingLeft() + getPaddingRight(),
                heightMode == MeasureSpec.EXACTLY ? heightSize :
                        height + getPaddingBottom() + getPaddingTop()
        );
        // 计算图片的宽高
        int imageSize = widthSize / mMaxLineNum;
        if (mIsOnceInitOnMeasure) {
            mParams = new LayoutParams(imageSize, imageSize);
            mIsOnceInitOnMeasure = false;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        ALL_VIEWS.clear();
        VIEW_HEIGHT.clear();
        if (!mHasInitOnLayout) {
            mLineViews = new ArrayList<>();
            mHasInitOnLayout = true;
        }
        int width = getWidth();
        // 每一行的宽度
        int lineWidth = 0;
        // 每一行的高度
        int lineHeight = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            // 需要换行
            if (lineWidth + childWidth + lp.leftMargin + lp.rightMargin >
                    width - getPaddingLeft() - getPaddingRight()) {
                // 记录上一行所有的图片
                ALL_VIEWS.add(mLineViews);
                // 记录上一行的高度
                VIEW_HEIGHT.add(lineHeight);
                // 重置宽度
                lineWidth = 0;
                lineHeight = childHeight + lp.topMargin + lp.bottomMargin;
                mLineViews.clear();
            }
            lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
            lineHeight = Math.max(lineHeight, lineHeight + lp.topMargin + lp.bottomMargin);
            mLineViews.add(child);
            // 记录最后一行所有的图片
            ALL_VIEWS.add(mLineViews);
            // 记录最后一行的高度
            VIEW_HEIGHT.add(lineHeight);
        }
        int left = getPaddingLeft();
        int top = getPaddingTop();
        int line = ALL_VIEWS.size();
        for (int i = 0; i < line; i++) {
            mLineViews = ALL_VIEWS.get(i);
            lineHeight = VIEW_HEIGHT.get(i);
            for (View child : mLineViews) {
                //判断child的状态
                if (child.getVisibility() == GONE) {
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc = left + child.getMeasuredWidth() - mImageMargin;
                int bc = top + child.getMeasuredHeight();
                child.layout(lc, tc, rc, bc);
                left += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            }
            left = getPaddingLeft();
            top += lineHeight;
        }
        mIconAdd.setLayoutParams(mParams);
        mHasInitOnLayout = false;
    }

    /**
     * 初始化加号图标
     */
    private void initIconAdd() {
        mIconAdd = new IconTextView(getContext());
        mIconAdd.setText(ICON_TEXT);
        mIconAdd.setGravity(Gravity.CENTER);
        mIconAdd.setTextSize(mIconSize);
        mIconAdd.setBackgroundResource(R.drawable.border_text);
        addView(mIconAdd);
        mIconAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mDelegate.startCameraWithCheck();
            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initIconAdd();
        mTargetDialog = new AlertDialog.Builder(getContext()).create();
    }

    public final void setDelegate(LatteDelegate delegate) {
        this.mDelegate = delegate;
    }

    public void onCrop(Uri uri) {
        createNewImageView();
        Glide.with(mDelegate)
                .load(uri)
                .apply(OPTIONS)
                .into(mTargetImage);
    }

    private void createNewImageView() {
        mTargetImage = new AppCompatImageView(getContext());
        mTargetImage.setId(mCurrentNum);
        mTargetImage.setLayoutParams(mParams);
        addView(mTargetImage, mCurrentNum);
        mCurrentNum++;
        // 当添加的图片个数超过规定的图片个数时，隐藏加号图标
        if (mCurrentNum >= mMaxNum - 1) {
            mIconAdd.setVisibility(GONE);
        }
        mTargetImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeleteId = v.getId();
                mTargetDialog.show();
                Window window = mTargetDialog.getWindow();
                if (window != null) {
                    window.setContentView(R.layout.dialog_image_click_panel);
                    window.setGravity(Gravity.BOTTOM);
                    window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    final WindowManager.LayoutParams params = window.getAttributes();
                    params.width = WindowManager.LayoutParams.MATCH_PARENT;
                    params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                    params.dimAmount = 0.5f;
                    window.setAttributes(params);
                    window.findViewById(R.id.btn_image_delete).setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mTargetDialog.cancel();
                            AppCompatImageView deleteImage = (AppCompatImageView) findViewById(mDeleteId);
                            AlphaAnimation alpha = new AlphaAnimation(1, 0);
                            alpha.setDuration(500);
                            alpha.setFillAfter(true);
                            alpha.setStartOffset(0);
                            alpha.setRepeatCount(0);
                            alpha.start();
                            deleteImage.startAnimation(alpha);
                            removeView(deleteImage);
                            mCurrentNum -= 1;
                            if (mCurrentNum < mMaxNum) {
                                mIconAdd.setVisibility(VISIBLE);
                            }
                        }
                    });
                    window.findViewById(R.id.btn_image_cancel).setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mTargetDialog.cancel();
                        }
                    });
                }
            }
        });
    }
}
