package com.pyf.latte.ec.main.personal.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.pyf.latte.delegate.LatteDelegate;
import com.pyf.latte.ec.R;
import com.pyf.latte.ec.main.personal.list.ListBean;
import com.pyf.latte.ui.date.DateUtil;
import com.pyf.latte.ui.date.IDateListener;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/30
 */
public class UserProfileClickListener extends SimpleClickListener {

    private final LatteDelegate DELEGATE;
    private String[] gender = new String[]{"男", "女", "保密"};

    public UserProfileClickListener(LatteDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, final View view, int position) {
        ListBean listBean = (ListBean) adapter.getData().get(position);
        int id = listBean.getId();
        switch (id) {
            case 1:
                // 开启相机或者图库
                break;
            case 2: // 姓名
                LatteDelegate nameDelegate = listBean.getDelegate();
                DELEGATE.getSupportDelegate().start(nameDelegate);
                break;
            case 3: // 性别
                genderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AppCompatTextView textView = (AppCompatTextView)
                                view.findViewById(R.id.tv_arrow_value);
                        textView.setText(gender[which]);
                        dialog.cancel();
                    }
                });
                break;
            case 4: // 生日
                DateUtil dateUtil = new DateUtil();
                dateUtil.setOnDateListener(new IDateListener() {
                    @Override
                    public void onDateChange(String date) {
                        AppCompatTextView textView = (AppCompatTextView)
                                view.findViewById(R.id.tv_arrow_value);
                        textView.setText(date);
                    }
                });
                dateUtil.showDateDialog(DELEGATE.getContext());
                break;
            case 5: // 地址

                break;
        }
    }

    private void genderDialog(DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(DELEGATE.getContext())
                .setSingleChoiceItems(gender, 0, listener)
                .show();
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
