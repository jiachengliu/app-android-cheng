package com.dameiren.app.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dameiren.app.R;

/**
 * 作者:liujiacheng.
 * 日期: 2016/6/7 0007 16:00.
 * 描述：公用标题栏
 */
public class JCTittleBar extends LinearLayout {

    public static final String TAG = JCTittleBar.class.getSimpleName();

    private TextView mTvBack;
    private TextView mTvTitle;
//    private TextView mTvExt;
//    private TextView pub_tv_send;
    private Button mSubmit;

    private Context mContext;

    public JCTittleBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        LayoutInflater.from(mContext).inflate(R.layout.widget_title_bar, this, true);

        init();
    }

    private void init() {

        mTvBack = (TextView) this.findViewById(R.id.pub_tv_back);
        mTvTitle = (TextView) this.findViewById(R.id.pub_tv_title);
//        mTvExt = (TextView) this.findViewById(R.id.pub_tv_ext);
//        pub_tv_send = (TextView) this.findViewById(R.id.pub_tv_send);
        mSubmit= (Button) this.findViewById(R.id.pub_button_submit);

        mTvBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);

                }

                ((Activity) mContext).finish();
            }
        });
    }

    public void setTitle(String title, boolean isShow) {

        mTvTitle.setText(title);

        if (isShow) {
            mTvTitle.setVisibility(View.VISIBLE);
        } else {
            mTvTitle.setVisibility(View.INVISIBLE);
        }
    }

    public void setBack(String back, boolean isShow) {

        mTvBack.setText(back);

        if (isShow) {
            mTvBack.setVisibility(View.VISIBLE);
        } else {
            mTvBack.setVisibility(View.INVISIBLE);
        }
    }

    public void setExt(String ext, boolean isShow, OnClickListener listener) {

//        mTvExt.setText(ext);
//        mTvExt.setOnClickListener(listener);
//
//        if (isShow) {
//            mTvExt.setVisibility(View.VISIBLE);
//        } else {
//            mTvExt.setVisibility(View.INVISIBLE);
//        }
    }
    public void setSend(String ext, boolean isShow, OnClickListener listener) {

//        pub_tv_send.setText(ext);
//        pub_tv_send.setOnClickListener(listener);
//
//        if (isShow) {
//            pub_tv_send.setVisibility(View.VISIBLE);
//        } else {
//            pub_tv_send.setVisibility(View.INVISIBLE);
//        }
    }

    public void setSubmit(String ext, boolean isShow, OnClickListener listener) {

        mSubmit.setText(ext);
        mSubmit.setOnClickListener(listener);

        if (isShow) {
            mSubmit.setVisibility(View.VISIBLE);
        } else {
            mSubmit.setVisibility(View.INVISIBLE);
        }
    }

//    public TextView getExt() {
//
//        return mTvExt;
//    }
//    public TextView getSend() {
//
//        return pub_tv_send;
//    }

    public TextView getTitle() {

        return mTvTitle;
    }

    public TextView getBack() {

        return mTvBack;
    }

    public Button getSubmit() {

        return mSubmit;
    }

}
