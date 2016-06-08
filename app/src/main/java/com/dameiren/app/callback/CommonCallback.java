package com.dameiren.app.callback;

import com.lzy.okhttputils.callback.AbsCallback;
import com.lzy.okhttputils.request.BaseRequest;

/**
 * 作者:liujiacheng.
 * 日期: 2016/6/3 0003 10:41.
 * 描述：该类主要用于在所有请求之前添加公共的请求头或请求参数，例如登录授权的 token,使用的设备信息等
 */
public abstract class CommonCallback<T> extends AbsCallback<T> {
    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
//        //如果账户已经登录，就添加 token 等等
//        request.headers("CallBackHeaderKey1", "CallBackHeaderValue1")//
//                .headers("CallBackHeaderKey2", "CallBackHeaderValue2")//
//                .params("CallBackParamsKey1", "CallBackParamsValue1")//
//                .params("CallBackParamsKey2", "CallBackParamsValue1")//
//                .params("token", "3215sdf13ad1f65asd4f3ads1f");
    }
}