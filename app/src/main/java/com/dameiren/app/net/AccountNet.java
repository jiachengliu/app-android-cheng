package com.dameiren.app.net;

import android.content.Context;

import com.dameiren.app.ui.base.BaseNet;
import com.litesuits.utils.JCDeviceUtils;
import com.lzy.okhttputils.model.HttpParams;

/**
 * @author Aloneter
 * @ClassName: AccountNet
 * @Description: 账号中心相关网络请求处理者
 * @date 2014-12-18
 * @Version 1.0
 */
public class AccountNet extends BaseNet {

    private static volatile AccountNet instance = null;
    public static AccountNet getInstance() {//获取实例对象

        if (instance == null){
            synchronized (AccountNet.class){
                if (instance == null){
                    instance = new AccountNet();
                }
            }

        }

        return instance;
    }


    public static final String TAG = AccountNet.class.getSimpleName();

//    public HttpParams isRegister(Context context, String username) {
//
//        HttpParams map = generateParam(context);
//        map.put("account", username);
//        map.put("sign", sign(map));
//
//        return map;
//    }

    public HttpParams login(Context context, String username, String password) {

        HttpParams map = generateParam(context);

        map.put("clientid", "1a6718c3e7be38dcb3b3393a5a6e37c4");
        map.put("account", username);
        map.put("password", password);
        map.put("mac", JCDeviceUtils.getInstance(context).getMacAddress());
        map.put("sign", sign(map));

        return map;
    }

//    public HashMap<String, String> register(Context context, String username, String password, String code) {
//
//        HashMap<String, String> map = generateParam(context);
//
//        map.put("mobile", username);
//        map.put("password", password);
//        map.put("code", code);
//        map.put("regSource", MyConstants.Type.REG_APP + "");
//        map.put("sign", sign(map));
//
//        return map;
//    }
//
//    public HashMap<String, String> getCode(Context context, String phone, int kl_type) {
//
//        HashMap<String, String> map = generateParam(context);
//
//        map.put("source", kl_type + "");
//        map.put("mobile", phone);
//        map.put("sign", sign(map));
//
//        return map;
//    }
//    public HashMap<String, String> getCode(Context context, String phone, int kl_type,String numbers) {
//
//        HashMap<String, String> map = generateParam(context);
//
//        map.put("source", kl_type + "");
//        map.put("mobile", phone);
//        map.put("internationalNo", numbers);
//        map.put("sign", sign(map));
//
//        return map;
//    }
//    public HashMap<String, String> forgetPwd(Context context, String username, String oldpassWord,String password) {
//
//        HashMap<String, String> map = generateParam(context);
//
//        map.put("account", username);
//        map.put("passwordOld",oldpassWord);
//        map.put("password", password);
//        map.put("sign", sign(map));
//
//        return map;
//    }
//
//    public HashMap<String, String> findPwd(Context context, String phone, String password,String code) {
//
//        HashMap<String, String> map = generateParam(context);
//
//        map.put("account", phone);
//        map.put("password",password);
//        map.put("code", code);
//        map.put("sign", sign(map));
//         return map;
//    }
//
//
//
//
//    public HashMap<String, String> loginAuth(Context context, String nickname, String openId, String token, int kl_type,String picurl) {
//
//        HashMap<String, String> map = generateParam(context);
//        map.put("picurl", picurl);
////        map.put("clientid", KLApplication.getAdminUser().mGTClientId);
//        map.put("nickname", nickname);
//        map.put("authUid", openId);
//        map.put("authToken", token);
//        map.put("authId", kl_type + "");
//        map.put("regSource", MyConstants.Type.REG_APP + "");
//        map.put("sign", sign(map));
//
//        return map;
//    }
//
//    public HashMap<String, String> bindAuth(Context context, String openId, String token, int kl_type) {
//
//        HashMap<String, String> map = generateParam(context);
//
//        map.put("uid", MyApplication.getUid());
//        map.put("authUid", openId);
//        map.put("authToken", token);
//        map.put("authId", kl_type + "");
//        map.put("regSource", MyConstants.Type.REG_APP + "");
//        map.put("sign", sign(map));
//
//        return map;
//    }
//
//    public HashMap<String, String> getBindThird(Context context) {
//
//        HashMap<String, String> map = generateParam(context);
//
//        map.put("uid", MyApplication.getUid());
//        map.put("sign", sign(map));
//
//        return map;
//    }
//
//    public HashMap<String, String> unBindThird(Context context, String authUid) {
//
//        HashMap<String, String> map = generateParam(context);
//
//        map.put("uid", MyApplication.getUid());
//        map.put("authUid", authUid);
//        map.put("sign", sign(map));
//
//        return map;
//    }
//
//    public HashMap<String, String> bindPhone(Context context, String mobile, String internationalNo, String code) {
//
//        HashMap<String, String> map = generateParam(context);
//
//        map.put("uid", MyApplication.getUid());
//        map.put("mobile", mobile);
//        map.put("internationalNo", internationalNo);
//        map.put("code", code + "");
//        map.put("sign", sign(map));
//
//        return map;
//    }
//
//    public HashMap<String, String> checkSession(Context context, String uid) {
//
//        HashMap<String, String> map = generateParam(context);
//
//        map.put("uid", uid);
//        map.put("sign", sign(map));
//
//        return map;
//    }
//
//    public HashMap<String, String> logout(Context context) {
//
//        HashMap<String, String> map = generateParam(context);
//
//        map.put("uid", MyApplication.getUid());
//        map.put("sign", sign(map));
//
//        return map;
//    }

}
