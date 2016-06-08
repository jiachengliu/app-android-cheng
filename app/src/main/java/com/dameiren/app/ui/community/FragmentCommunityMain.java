package com.dameiren.app.ui.community;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.dameiren.app.R;
import com.dameiren.app.ui.base.BaseFragment;
import com.dameiren.app.ui.community.adapter.CommunityPageAdapter;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.shizhefei.view.viewpager.SViewPager;

/**
 * 作者:liujiacheng.
 * 日期: 2016/6/3 0003 10:55.
 * 描述：社区首页
 */
public class FragmentCommunityMain extends BaseFragment {
    private static final String TAG = FragmentCommunityMain.class.getSimpleName();
    private IndicatorViewPager indicatorViewPager;
    public static final String INTENT_STRING_TABNAME = "intent_String_tabname";
    public static final String INTENT_INT_INDEX = "intent_int_index";
    private String tabName;
    private int index;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_community_main);
        Resources res = getResources();

        Bundle bundle = getArguments();
        tabName = bundle.getString(INTENT_STRING_TABNAME);
        index = bundle.getInt(INTENT_INT_INDEX);

        SViewPager viewPager = (SViewPager) findViewById(R.id.fragment_community_viewPager);
        Indicator indicator = (Indicator) findViewById(R.id.fragment_community_indicator);
        int selectColor = res.getColor(R.color.white);
        int unSelectColor = res.getColor(R.color.jc_ff759f);
        indicator.setOnTransitionListener(new OnTransitionTextListener().setColor(selectColor, unSelectColor));
        viewPager.setCanScroll(true);// 可以禁止viewpager的滑动事件
        viewPager.clearAnimation();//注意：清除fragment之间切换动画。
//        viewPager.setOffscreenPageLimit(3);
        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        indicatorViewPager.setPageOffscreenLimit(3);
        // 注意这里 的FragmentManager 是 getChildFragmentManager(); 因为是在Fragment里面
        // 而在activity里面用FragmentManager 是 getSupportFragmentManager()
        indicatorViewPager.setAdapter(new CommunityPageAdapter(getChildFragmentManager(),getActivity()));
        Log.d("cccc", "Fragment 将要创建View " + this);
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
