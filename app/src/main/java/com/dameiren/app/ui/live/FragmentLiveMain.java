package com.dameiren.app.ui.live;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.dameiren.app.R;
import com.dameiren.app.ui.base.BaseFragment;
import com.dameiren.app.ui.live.adapter.LivePageAdapter;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.shizhefei.view.viewpager.SViewPager;

/**
 * 作者:liujiacheng.
 * 日期: 2016/6/2 0002 16:17.
 * 描述：
 */
public class FragmentLiveMain extends BaseFragment {
    private static final String TAG = FragmentLiveMain.class.getSimpleName();
    private IndicatorViewPager indicatorViewPager;
    public static final String INTENT_STRING_TABNAME = "intent_String_tabname";
    public static final String INTENT_INT_INDEX = "intent_int_index";
    private String tabName;
    private int index;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_live_main);
        Resources res = getResources();

        Bundle bundle = getArguments();
        tabName = bundle.getString(INTENT_STRING_TABNAME);
        index = bundle.getInt(INTENT_INT_INDEX);

        SViewPager viewPager = (SViewPager) findViewById(R.id.fragment_tabmain_viewPager);
        Indicator indicator = (Indicator) findViewById(R.id.fragment_tabmain_indicator);

//        switch (index) {
//            case 0:
//                indicator.setScrollBar(new ColorBar(getApplicationContext(), Color.RED, 5));
//                break;
//            case 1:
//                indicator.setScrollBar(new ColorBar(getApplicationContext(), Color.RED, 0, ScrollBar.Gravity.CENTENT_BACKGROUND));
//                break;
//            case 2:
//                indicator.setScrollBar(new ColorBar(getApplicationContext(), Color.RED, 5, ScrollBar.Gravity.TOP));
//                break;
//            case 3:
//                indicator.setScrollBar(new LayoutBar(getApplicationContext(), R.layout.layout_slidebar, ScrollBar.Gravity.CENTENT_BACKGROUND));
//                break;
//        }
//
        float unSelectSize = 16;
        float selectSize = unSelectSize * 1.2f;

        int selectColor = res.getColor(R.color.white);
        int unSelectColor = res.getColor(R.color.jc_ff759f);
        indicator.setOnTransitionListener(new OnTransitionTextListener().setColor(selectColor, unSelectColor));
        viewPager.setCanScroll(true);// 可以禁止viewpager的滑动事件
        viewPager.clearAnimation();//注意：清除fragment之间切换动画。
        viewPager.setOffscreenPageLimit(2);

        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        // 注意这里 的FragmentManager 是 getChildFragmentManager(); 因为是在Fragment里面
        // 而在activity里面用FragmentManager 是 getSupportFragmentManager()
        indicatorViewPager.setAdapter(new LivePageAdapter(getChildFragmentManager(),getActivity()));

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
