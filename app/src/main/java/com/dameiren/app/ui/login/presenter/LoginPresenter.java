package com.dameiren.app.ui.login.presenter;


import android.app.Activity;

import com.dameiren.app.R;
import com.dameiren.app.core.MyApplication;
import com.dameiren.app.core.MyConstants;
import com.dameiren.app.ui.base.BaseResult;
import com.dameiren.app.ui.base.PresenterCallBack;
import com.dameiren.app.ui.login.LoginActivity;
import com.dameiren.app.ui.login.bean.AdminUser;
import com.dameiren.app.ui.login.bean.NetUserInfoDetail;
import com.dameiren.app.ui.login.view.LoginView;
import com.dameiren.app.ui.utils.JCJsonUtils;
import com.google.gson.Gson;
import com.litesuits.utils.log.Log;

/**
 * 作者:liujiacheng.
 * 日期: 2016/6/1 0001 18:15.
 * 描述：LoginActivity处理器
 */
public class LoginPresenter implements PresenterCallBack {
    private static final String TAG = LoginPresenter.class.getSimpleName();
    private LoginView loginView;
    private Activity mActivity;
    private int mLoginType;
    public LoginPresenter(LoginView loginView,Activity activity) {
        this.loginView = loginView;
        this.mActivity = activity;
    }

    @Override
    public void onSuccess(int what, String result, boolean hashCache) {
        BaseResult<?> net = JCJsonUtils.getInstance().getString2Cls(result, BaseResult.class);
        if (net == null || net.status != MyConstants.Code.STATUS_SUCCESS) {
            if (net == null) {
                Log.e(TAG, " ====> 请求结果：net == null");
            } else {
                Log.e(TAG, " ====> 请求结果：status:{" + net.status + "}/message:{" + net.message + "}");
                loginView.showToast(net.message);
            }
            return;
        }
        switch (what){
            case LoginActivity.WHAT_LOGIN:
                loginView.showToast(mActivity.getResources().getString(R.string.content_tip_login_success));
                if (what == LoginActivity.WHAT_LOGIN) {
                    mLoginType = AdminUser.TYPE_LOGIN;
                }
                NetUserInfoDetail user = JCJsonUtils.getInstance().getString2Cls(new Gson().toJson(net.data), NetUserInfoDetail.class);
                if (user == null || user.userInfo == null) {
                    loginView.showToast(mActivity.getResources().getString(R.string.content_tip_login_fail_request));
                    return;
                }
                user.userInfo.dealNull();
                MyApplication.getAdminUser().uid = user.userInfo.uid;
                MyApplication.getAdminUser().userName = user.userInfo.nickname;
                MyApplication.getAdminUser().userPicIp = user.picIp;
                MyApplication.getAdminUser().userInfo = user.userInfo;
                MyApplication.getAdminUser().mobileNo = user.bindMobile.bind_mobile_no;
                MyApplication.getAdminUser().mLoginType = mLoginType;
                MyApplication.getAdminUser().mIsAdmin = user.userInfo.isAdmin;
                MyApplication.getAdminUser().userInfo.open_video = user.userInfo.open_video;
                MyApplication.getAdminUser().mIsOpenVideo = user.userInfo.open_video;

                MyApplication.saveUserInfoToDB();
                loginView.moveToIndex();
                break;
        }

    }

    @Override
    public void onError(int what, int e, String message) {
        switch (what) {
            case LoginActivity.WHAT_LOGIN:

                break;
        }
    }
}
