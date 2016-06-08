package com.dameiren.app.ui.main;

import android.os.Bundle;

import com.dameiren.app.R;
import com.dameiren.app.ui.base.BaseActivity;
import com.dameiren.app.ui.main.adapter.MainPageAdapter;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.viewpager.SViewPager;

/**
 * 作者:liujiacheng.
 * 日期: 2016/5/31 0031 17:06.
 * 描述：
 */
public class MainActivity extends BaseActivity {
    private IndicatorViewPager indicatorViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SViewPager viewPager = (SViewPager) findViewById(R.id.tabmain_viewPager);
        Indicator indicator = (Indicator) findViewById(R.id.tabmain_indicator);
        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        indicatorViewPager.setAdapter(new MainPageAdapter(getSupportFragmentManager(),this));
        viewPager.setCanScroll(false);// 禁止viewpager的滑动事件
        viewPager.clearAnimation();//注意：清除fragment之间切换动画。
        viewPager.setOffscreenPageLimit(5);// 设置viewpager保留界面不重新加载的页面数量
    }


}
