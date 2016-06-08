package com.dameiren.app.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.dameiren.app.R;
import com.dameiren.app.callback.NetCallBack;
import com.dameiren.app.callback.StringCallback;
import com.litesuits.utils.JCNetworkUtils;
import com.litesuits.utils.JCStringUtils;
import com.litesuits.utils.log.Log;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.model.HttpParams;
import com.shizhefei.fragment.LazyFragment;

import java.util.Set;

import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者:liujiacheng.
 * 日期: 2016/6/3 0003 09:56.
 * 描述：
 */
public abstract class BaseFragment extends LazyFragment {
    protected FragmentActivity mActivity;
    protected Context mContext;
    private Fragment mFragment;
    private NetCallBack mNetCallBack; // 网络请求回调接口
    public static final int NET_METHOD_GET = 102;//GET 数据请求
    public static final int NET_METHOD_POST = 103;//POST 数据请求
    public static final int NET_METHOD_PUT = 104;//PUT 数据请求
    public static final int NET_METHOD_DELETE = 105;//DELETE 数据请求
    public static final int REQUEST_ERROR = 1;//网络请求错误
    public static final int REQUEST_UNAVAILABLE = 600;//未联网
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (FragmentActivity) getActivity();
        mFragment = this;
        mContext = this.getApplicationContext();
    }
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);

    }
    /**网络请求处理接口*/
    protected void initIble(NetCallBack netCallBack) {
        mNetCallBack = netCallBack;
    }

    /**启动任务请求数据*/
    protected void startTask(String url, int what, int method, boolean isCache) {
        startTask(url, what, false, method, isCache);
    }

    /**启动任务请求数据*/
    protected void startTask(final String url, final int what, final boolean isShow, final int method, final boolean isCache) {
        // 判断请求地址是否为空
        if (JCStringUtils.getInstance().isEmpty(url)) {
            mNetCallBack.onError(what, REQUEST_ERROR, mContext.getResources().getString(R.string.ex_str_param_not_empty));
            return;
        }
        // 判断网络是否可以使用
        if (!JCNetworkUtils.isConnected(mContext)) {
            mNetCallBack.onError(what, REQUEST_UNAVAILABLE, mContext.getResources().getString(R.string.ex_str_net_no));
            return;
        }
        // 接口回调回去操作参数
        HttpParams params = mNetCallBack.onStart(what);
        Log.e(mFragment, "请求开始  请求参数：" + params.toString());
        StringCallback httpCallBack = new StringCallback() {

            @Override
            public void onResponse(boolean isFromCache, String data, Request request, Response response) {
                Log.e(mFragment,"---------------请求成功 log start---------------");
                Log.e(mFragment,"请求成功success  是否来自缓存：" + isFromCache + "  请求方式：" + request.method() + "\n" +"url：" + request.url());
                Headers requestHeadersString = request.headers();
                Set<String> requestNames = requestHeadersString.names();
                StringBuilder sb = new StringBuilder();
                for (String name : requestNames) {
                    sb.append(name).append(" ： ").append(requestHeadersString.get(name)).append("\n");
                }
                Log.e(mFragment, "请求成功success  请求头：" + sb.toString());
                if (data == null) {
                    mNetCallBack.onSuccess(what, data+"", isFromCache);
                } else {
                    if (data instanceof String) {
                        mNetCallBack.onSuccess(what, data, isFromCache);
                    }
                }
                Log.e(mFragment, "请求成功success  返回数据为："+data );
                Log.e(mFragment,"---------------请求成功 log end---------------");
            }

            @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                Request request = call.request();
                Log.e(mFragment,"---------------请求失败 log start---------------");
                Log.e(mFragment,"请求失败fail  是否来自缓存：" + isFromCache + "  请求方式：" + request.method() + "\n" +"url：" + request.url());
                Headers requestHeadersString = request.headers();
                Set<String> requestNames = requestHeadersString.names();
                StringBuilder sb = new StringBuilder();
                for (String name : requestNames) {
                    sb.append(name).append(" ： ").append(requestHeadersString.get(name)).append("\n");
                }
                Log.e(mFragment, "请求失败fail  请求头：" + sb.toString());
                if (response != null) {
                    Headers responseHeadersString = response.headers();
                    Set<String> names = responseHeadersString.names();
                    sb = new StringBuilder();
                    sb.append("stateCode ： ").append(response.code()).append("\n");
                    for (String name : names) {
                        sb.append(name).append(" ： ").append(responseHeadersString.get(name)).append("\n");
                    }
                    Log.e(mFragment, "请求失败fail  返回数据：" + sb.toString());
                } else {
                    Log.e(mFragment, "请求失败fail  返回数据：" );
                }
                Log.e(mFragment,"---------------请求失败 log end---------------");
                mNetCallBack.onError(what, response.code(), sb.toString());
            }
        };
        // 启动网络请求
        if (method == NET_METHOD_GET) {
            OkHttpUtils.get(url).tag(mFragment).params(params).execute(httpCallBack);
        } else if (method == NET_METHOD_POST) {
            OkHttpUtils.post(url).tag(mFragment).params(params).execute(httpCallBack);
        } else if (method == NET_METHOD_DELETE){
            OkHttpUtils.delete(url).tag(mFragment).params(params).execute(httpCallBack);
        }else if (method == NET_METHOD_PUT){
            OkHttpUtils.put(url).tag(mFragment).params(params).execute(httpCallBack);
        }else {
            OkHttpUtils.post(url).tag(mFragment).params(params).execute(httpCallBack);
        }
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        OkHttpUtils.getInstance().cancelTag(TAG);//Activity销毁时，取消网络请求
//    }
    @Override
    protected void onDestroyViewLazy() {
        super.onDestroyViewLazy();
        OkHttpUtils.getInstance().cancelTag(mFragment);//Activity销毁时，取消网络请求
    }
}
