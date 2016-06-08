package com.dameiren.app.callback;

import com.lzy.okhttputils.model.HttpParams;

/**
 * 作者:liujiacheng.
 * 日期: 2016/6/1 0001 18:15.
 * 描述：网络请求回调接口
 */
public interface NetCallBack {

    /** Method_启动处理回调*/
    HttpParams onStart(int what);
    /** Method_成功处理回调*/
    void onSuccess(int what, String result, boolean hashCache);
    /** Method_错误处理回调*/
    void onError(int what, int e, String message);

}
