/*
 * Copyright (c) 2015, 张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dameiren.app.core;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebStorage;
import android.widget.TextView;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.dameiren.app.R;
import com.dameiren.app.ui.login.LoginActivity;
import com.dameiren.app.ui.login.bean.AdminUser;
import com.dameiren.app.ui.login.bean.NetUserInfo;
import com.dameiren.app.ui.login.bean.TBUser;
import com.litesuits.utils.JCActivityUtils;
import com.litesuits.utils.JCPerference;
import com.litesuits.utils.JCStringUtils;
import com.litesuits.utils.JCToastUtil;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cookie.store.PersistentCookieStore;
import com.lzy.okhttputils.model.HttpHeaders;
import com.lzy.okhttputils.model.HttpParams;


/**
 * 作者:liujiacheng.
 * 日期: 2016/5/31 0031 17:06.
 * 描述：
 */
public class MyApplication extends Application {
    public static final String TAG = MyApplication.class.getSimpleName();
    private static MyApplication instance;
    private static AdminUser mUser;
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this.getApplicationContext();
        instance = this;
        ActiveAndroid.initialize(this);//初始化数据库
        CrashHandler.create(this);
        HttpHeaders headers = new HttpHeaders();
//        headers.put("commonHeaderKey1", "commonHeaderValue1");    //所有的 header 都 不支持 中文
//        headers.put("commonHeaderKey2", "commonHeaderValue2");
        HttpParams params = new HttpParams();
//        params.put("commonParamsKey1", "commonParamsValue1");     //所有的 params 都 支持 中文
//        params.put("commonParamsKey2", "这里支持中文参数");

        //必须调用初始化
        OkHttpUtils.init(this);
        //以下都不是必须的，根据需要自行选择
        OkHttpUtils.getInstance()//
                .debug("OkHttpUtils")                                              //是否打开调试
                .setConnectTimeout(OkHttpUtils.DEFAULT_MILLISECONDS)               //全局的连接超时时间
                .setReadTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)                  //全局的读取超时时间
                .setWriteTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)                 //全局的写入超时时间
//                .setCookieStore(new MemoryCookieStore())                           //cookie使用内存缓存（app退出后，cookie消失）
                .setCookieStore(new PersistentCookieStore())                       //cookie持久化存储，如果cookie不过期，则一直有效
                .addCommonHeaders(headers)                                         //设置全局公共头
                .addCommonParams(params);                                          //设置全局公共参数
    }
    public static MyApplication getInstance() {
        return instance;
    }
    @Override
    public void onLowMemory() {
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onLowMemory();
    }

    public static AdminUser getAdminUser() {
        if (mUser == null) {
            mUser = new AdminUser();
            String uid = JCPerference.getInstance(mContext).getStringDES(AppConfig.CONFIG_PER_TAG_APP_USER_UID);
            loadUserInfoToDB(uid);
        }
        return mUser;
    }

    public static void clearAdminUser() {
        mUser = new AdminUser();
    }
    public static void loadUserInfoToDB(String uid) {
        TBUser user = new Select()
                    .from(TBUser.class)
                    .where("uid = ?", uid)
                    .orderBy("RANDOM()")
                    .executeSingle();
//        try {
//            List<TBUser> userList = DataSupport.where("uid = ?", uid).find(TBUser.class);
//            if (userList != null && userList.size() > 0) {
//                TBUser user = userList.get(0);
                if (user != null) {
                    NetUserInfo userInfo = user.getNetUserInfo();
                    MyApplication.getAdminUser().uid = userInfo.uid;
                    MyApplication.getAdminUser().userName = userInfo.nickname;
                    MyApplication.getAdminUser().userInfo = userInfo;
                    MyApplication.getAdminUser().userPicIp = user.picIp;
                    MyApplication.getAdminUser().mobileNo = user.mobileNo;
                    MyApplication.getAdminUser().mIsAdmin = user.is_admin;
                    MyApplication.getAdminUser().mIsOpenVideo = user.open_video;
//                    return true;
                }
//            }
//
//            return false;
//        } catch (Exception e) {
//            Log.e(TAG, "loadUserInfoToDB"+e.getMessage());
//
//            return false;
//        }
    }

    public static boolean saveUserInfoToDB() {
        try {
            TBUser tbUser = new TBUser();
            tbUser.mobileNo = MyApplication.getAdminUser().mobileNo;
            tbUser.picIp = MyApplication.getAdminUser().userPicIp;
            tbUser.setNetUserInfo(MyApplication.getAdminUser().userInfo);
            tbUser.loginType = MyApplication.getAdminUser().mLoginType;
            tbUser.is_admin = MyApplication.getAdminUser().mIsAdmin;
            tbUser.open_video = MyApplication.getAdminUser().mIsOpenVideo;
            tbUser.save();
            JCPerference.getInstance(mContext).putStringDES(AppConfig.CONFIG_PER_TAG_APP_USER_UID, MyApplication.getAdminUser().uid);
            JCPerference.getInstance(mContext).putInt(AppConfig.CONFIG_PER_TAG_APP_USER_LOGIN_TYPE, MyApplication.getAdminUser().mLoginType);
            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    public static boolean clearUserInfoToDB() {

//        try {
//            clearAdminUser();
//            DataSupport.deleteAll(TBUser.class);
//            Ex.Perference(getContext()).clearByTag(KLConstants.Config.CONFIG_PER_TAG_APP_USER_UID);
//            Ex.Perference(getContext()).clearByTag(KLConstants.Config.CONFIG_PER_TAG_APP_USER_LOGIN_TYPE);
//
            return true;
//        } catch (Exception e) {
//            Log.e(TAG, "" + e.getMessage());
//
//            return false;
//        }
    }

    public static void clearCookieAndLocalStorage(Context cxt){
        clearCookie(cxt);
        clearLocalStorage();

    }



    public static void clearCookie(Context cxt) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                CookieManager.getInstance().removeAllCookies(null);
                CookieManager.getInstance().flush();
            } else if (cxt != null) {
                CookieSyncManager cookieSync = CookieSyncManager.createInstance(cxt);
                cookieSync.startSync();
                CookieManager cookieManager = CookieManager.getInstance();
                cookieManager.removeAllCookie();
                cookieManager.removeSessionCookie();
                cookieSync.stopSync();
                cookieSync.sync();
            }
        } catch (Exception e) {
        }
    }

    /**
     * 清除LocalStorage
     */
    public static void clearLocalStorage() {
        try {
            WebStorage.getInstance().deleteAllData();
        } catch (Throwable e) {
        }
    }



    /**
     * 设置登录规则
     *
     * @param view
     * @param level
     */
    public static void setUserInfoLevel(TextView view, int level) {
//        int resId = R.drawable.ico_shequ_level1;
//        if (level == 1){
//            resId = R.drawable.ico_shequ_level1;
//        }else if (level == 2){
//            resId = R.drawable.ico_shequ_level2;
//        }else if (level == 3){
//            resId = R.drawable.ico_shequ_level3;
//        }else if (level == 4){
//            resId = R.drawable.ico_shequ_level4;
//        }else if (level == 5){
//            resId = R.drawable.ico_shequ_level5;
//        }else if (level == 6){
//            resId = R.drawable.ico_shequ_level6;
//        }else if (level == 7){
//            resId = R.drawable.ico_shequ_level7;
//        }else if (level == 8){
//            resId = R.drawable.ico_shequ_level8;
//        }else if (level == 9){
//            resId = R.drawable.ico_shequ_level9;
//        }else if (level == 10){
//            resId = R.drawable.ico_shequ_level10;
//        }else if (level == 11){
//            resId = R.drawable.ico_shequ_level11;
//        }else if (level == 12){
//            resId = R.drawable.ico_shequ_level12;
//        }else if (level == 13){
//            resId = R.drawable.ico_shequ_level13;
//        }else if (level == 14){
//            resId = R.drawable.ico_shequ_level14;
//        }else if (level == 15){
//            resId = R.drawable.ico_shequ_level15;
//        }else if (level == 16){
//            resId = R.drawable.ico_shequ_level16;
//        }else if (level == 17){
//            resId = R.drawable.ico_shequ_level17;
//        }else if (level == 18){
//            resId = R.drawable.ico_shequ_level18;
//        }
//        view.setBackgroundResource(resId);
    }

    public static boolean isLogin() {
        if (JCStringUtils.getInstance().isEmpty(getAdminUser().uid)) {

            return false;
        }

        return true;
    }

    /**
     * 调用登录界面
     * @param context
     * @param tag
     */
    public static void showNoLogin(Context context, String tag) {
        JCToastUtil.showToast(context, R.string.content_tip_login);
        Bundle bundle = new Bundle();
        bundle.putInt(LoginActivity.BUNDLE_LOGIN_TYPE, LoginActivity.TYPE_LOGIN_ACTIVITY);
        bundle.putString(LoginActivity.BUNDLE_LOGIN_FLAG, tag);
        JCActivityUtils.getInstance(context).start(LoginActivity.class, bundle);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    /** 初始化数据库用到的方法，http://blog.csdn.net/wangjia55/article/details/11257271 */
    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }
}
