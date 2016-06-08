package com.dameiren.app.callback;

import android.support.annotation.Nullable;

import com.lzy.okhttputils.request.BaseRequest;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 作者:liujiacheng.
 * 日期: 2016/6/3 0003 10:49.
 * 描述：
 */
public abstract class StringCallback extends CommonCallback<String> {



    @Override
    public String parseNetworkResponse(Response response) throws Exception {
        return response.body().string();
    }

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);

    }

    @Override
    public void onAfter(boolean isFromCache, @Nullable String s, Call call, Response response, @Nullable Exception e) {
        super.onAfter(isFromCache, s, call, response, e);

    }
}
