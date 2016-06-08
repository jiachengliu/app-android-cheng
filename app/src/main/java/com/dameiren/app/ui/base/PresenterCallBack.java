package com.dameiren.app.ui.base;

/**
 * 作者:liujiacheng.
 * 日期: 2016/6/6 0006 13:54.
 * 描述：Presenter回调父类，实现该接口，网络请求后执行的具体代码逻辑。
 */
public interface PresenterCallBack {

    /**成功处理回调*/
    void onSuccess(int what, String result, boolean hashCache);
    /*** 错误处理回调*/
    void onError(int what, int e, String message);

}
