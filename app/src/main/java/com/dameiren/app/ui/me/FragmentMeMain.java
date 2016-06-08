package com.dameiren.app.ui.me;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dameiren.app.R;
import com.dameiren.app.ui.base.BaseFragment;
import com.dameiren.app.ui.login.LoginActivity;
import com.litesuits.utils.JCActivityUtils;
import com.makeramen.roundedimageview.RoundedImageView;

/**
 * 作者:liujiacheng.
 * 日期: 2016/6/2 0002 17:25.
 * 描述：
 */
public class FragmentMeMain extends BaseFragment implements View.OnClickListener{

    RoundedImageView icon;
    TextView fml_tv_nick;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_me_main);
        fml_tv_nick = (TextView)findViewById(R.id.fml_tv_nick);
        fml_tv_nick.setOnClickListener(this);
//        icon.setOnClickListener(this);
        fml_tv_nick.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch ( view.getId() ) {
            case R.id.fml_tv_nick:
                JCActivityUtils.getInstance(mActivity).start(LoginActivity.class);
                break;
            default:
                break;
        }
    }
    @Override
    protected void onResumeLazy() {
        super.onResumeLazy();
        Log.d("cccc", "Fragment所在的Activity onResume, onResumeLazy " + this);
    }

    @Override
    protected void onFragmentStartLazy() {
        super.onFragmentStartLazy();
        Log.d("cccc", "Fragment 显示 " + this);
    }

    @Override
    protected void onFragmentStopLazy() {
        super.onFragmentStopLazy();
        Log.d("cccc", "Fragment 掩藏 " + this);
    }

    @Override
    protected void onPauseLazy() {
        super.onPauseLazy();
        Log.d("cccc", "Fragment所在的Activity onPause, onPauseLazy " + this);
    }

    @Override
    protected void onDestroyViewLazy() {
        super.onDestroyViewLazy();
        Log.d("cccc", "Fragment View将被销毁 " + this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("cccc", "Fragment 所在的Activity onDestroy " + this);
    }
}
