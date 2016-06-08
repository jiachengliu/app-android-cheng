package com.dameiren.app.ui.main.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dameiren.app.R;
import com.dameiren.app.ui.community.FragmentCommunityMain;
import com.dameiren.app.ui.live.FragmentLiveMain;
import com.dameiren.app.ui.me.FragmentMeMain;
import com.dameiren.app.ui.shop.FragmentCarMain;
import com.shizhefei.fragment.LazyFragment;
import com.shizhefei.view.indicator.IndicatorViewPager;

/**
 * 作者:liujiacheng.
 * 日期: 2016/6/2 0002 15:47.
 * 描述：
 */
public class MainPageAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
    private String[] tabNames = {"直播间", "视频", "社区","购物车", "我"};
    private int[] tabIcons = {R.drawable.maintab_1_selector, R.drawable.maintab_2_selector, R.drawable.maintab_3_selector,
            R.drawable.maintab_4_selector,R.drawable.maintab_5_selector};
    private LayoutInflater inflater;

    public MainPageAdapter(FragmentManager fragmentManager,Context context) {
        super(fragmentManager);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return tabNames.length;
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = (TextView) inflater.inflate(R.layout.tab_main_bottom, container, false);
        }
        TextView textView = (TextView) convertView;
        textView.setText(tabNames[position]);
        textView.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[position], 0, 0);
        return textView;
    }

    @Override
    public Fragment getFragmentForPage(int position) {
        LazyFragment fragment = null;
        if (position == 0){
            fragment = new FragmentLiveMain();
            Bundle bundle = new Bundle();
            bundle.putString(FragmentLiveMain.INTENT_STRING_TABNAME, tabNames[position]);
            bundle.putInt(FragmentLiveMain.INTENT_INT_INDEX, position);
            fragment.setArguments(bundle);
        }else if (position==2){
            fragment = new FragmentCommunityMain();
            Bundle bundle = new Bundle();
            bundle.putString(FragmentCommunityMain.INTENT_STRING_TABNAME, tabNames[position]);
            bundle.putInt(FragmentCommunityMain.INTENT_INT_INDEX, position);
            fragment.setArguments(bundle);
        }else if (position==3){
            fragment = new FragmentCarMain();
            Bundle bundle = new Bundle();
            bundle.putString(FragmentCarMain.INTENT_STRING_TABNAME, tabNames[position]);
            bundle.putInt(FragmentCarMain.INTENT_INT_POSITION, position);
            fragment.setArguments(bundle);
        }else if (position==4){
            fragment = new FragmentMeMain();
            Bundle bundle = new Bundle();
//            bundle.putString(FragmentMeMain.INTENT_STRING_TABNAME, tabNames[position]);
//            bundle.putInt(FragmentMeMain.INTENT_INT_POSITION, position);
            fragment.setArguments(bundle);
        }else {
            fragment = new FragmentLiveMain();
            Bundle bundle = new Bundle();
            bundle.putString(FragmentLiveMain.INTENT_STRING_TABNAME, tabNames[position]);
            bundle.putInt(FragmentLiveMain.INTENT_INT_INDEX, position);
            fragment.setArguments(bundle);
        }
        return fragment;
    }
}