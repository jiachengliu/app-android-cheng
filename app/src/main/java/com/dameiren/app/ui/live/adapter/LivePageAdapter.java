package com.dameiren.app.ui.live.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dameiren.app.R;
import com.dameiren.app.ui.live.FragmentLiveLeft;
import com.dameiren.app.ui.live.FragmentLiveRight;
import com.shizhefei.fragment.LazyFragment;
import com.shizhefei.view.indicator.IndicatorViewPager;

/**
 * 作者:liujiacheng.
 * 日期: 2016/6/2 0002 15:47.
 * 描述：
 */
public class LivePageAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
    private String[] tabNames = {"直播", "预告"};
    private int[] tabIcons = {R.drawable.communitytab_left_selector, R.drawable.communitytab_right_selector};
    private LayoutInflater inflater;

    public LivePageAdapter(FragmentManager fragmentManager, Context context) {
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
            convertView = (TextView) inflater.inflate(R.layout.tab_live_top, container, false);
        }
        TextView textView = (TextView) convertView;
        textView.setText(tabNames[position]);
//        textView.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[position], 0, 0);
        textView.setBackgroundResource(tabIcons[position]);
        return textView;
    }

    @Override
    public Fragment getFragmentForPage(int position) {
        LazyFragment fragment = null;
        if (position==0){
            fragment = new FragmentLiveLeft();
            Bundle bundle = new Bundle();
            bundle.putString(FragmentLiveLeft.INTENT_STRING_TABNAME, tabNames[position]);
            bundle.putInt(FragmentLiveLeft.INTENT_INT_POSITION, position);
            fragment.setArguments(bundle);
        }else if (position==1){
            fragment = new FragmentLiveRight();
            Bundle bundle = new Bundle();
            bundle.putString(FragmentLiveRight.INTENT_STRING_TABNAME, tabNames[position]);
            bundle.putInt(FragmentLiveRight.INTENT_INT_POSITION, position);
            fragment.setArguments(bundle);
        }
        return fragment;
    }
}