package com.dameiren.app.ui.base;

import android.content.Context;
import android.widget.TextView;

import com.dameiren.app.R;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.internal.SuperViewHolder;

import java.util.List;

/**
 * 作者:liujiacheng.
 * 日期: 2016/6/1 0001 18:16.
 * 描述：
 */
public class SingleAdapter extends SuperAdapter<String> {
    public SingleAdapter(Context context, List<String> list, int layoutResId) {
        super(context, list, layoutResId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, String item) {
        holder.setText(R.id.tv, item);
        TextView textView = holder.getView(R.id.tv);
//        textView.setT
    }

}
