package com.dameiren.app.core;

/**
 * 作者:liujiacheng.
 * 日期: 2016/6/1 0001 10:36.
 * 描述：网络请求地址、
 */
public class MyConstants {
    /**
     * 全局定义
     */
    public static class Global {
        // APP - 测试环境配置
        public static final String APP_URL = "http://test.app.dameiren.com"; // 应用主请求地址
        public static final String RES_URL = "http://test.res.app.dameiren.com"; // 资源请求地址
        public static final String APP_ID = "9c2eab04-d675-4d79-ba2d-7a3cbcdbbcbc";
        public static final String APP_SECRET = "ad0e1390-9d75-4434-acf4-ee30d4ad71a9";
        public static final String APP_URL_WEIXIN_AUTH = "https://api.weixin.qq.com/sns/oauth2/access_token"; // 微信AccessToken获取地址
        public static final String AUTH_WEIXIN_APPID = "wx382eaad790b61b88"; // 微信APPID
        public static final String AUTH_WEIXIN_SECRET = "b98f065924e67a8944f09e913883d3c8"; // 微信SECRET
        public static final String AUTH_TENCENT_APPID = "1103465632"; // QQAPPID 1103572223
        public static final String AUTH_TENCENT_APPKEY = "hm4WwXCyjQ2zmqgR"; // QQAPPKEY
        public static final String AUTH_SINA_APPID = "1202441551"; // 新浪APPID
        public static final String AUTH_SINA_REDIRECT_URL= "http://www.dameiren.com"; // 新浪 redirect_url
        public static final String AUTH_RENREN_ID = ""; // 人人ID
        public static final String AUTH_RENREN_APPID = ""; // 人人APPID
        public static final String AUTH_RENREN_APPKEY = ""; // 人人APPKEY
        public static final String AUTH_YIXIN_APPID = ""; // 易信APPID
        public static final String AUTH_LAIWANG_APPID = ""; // 来往APPID
        public static final String AUTH_LAIWANG_APPKEY = ""; // 来往APPKEY
        public static final String SHARE_JSLIEW_PC_URL="http://test.res.app.dameiren.com/html/appshare_v2/jshan_pc.html?videoId=";
        public static final String SHARE_JSLIEW_MOBILE_URL="http://test.res.app.dameiren.com/html/appshare_v2/jshan_mobile.html?videoId=";
        public static final String SHARE_JSLIEW_PC_CC_URL="http://test.res.app.dameiren.com/html/tapeshare.html?videoId=";

        // APP - 生产环境配置
//        public static final String APP_URL = "http://app.dameiren.com"; // 应用主请求地址
//        public static final String RES_URL = "http://res.app.dameiren.com"; // 资源请求地址
//        public static final String TEST_USER_UID = "123456";
//        public static final String APP_ID = "894d177b-8b0f-4aa4-88f0-7459be0772cf";
//        public static final String APP_SECRET = "bc65b047-2929-4651-9c7a-139f801fdb21";
//        public static final String APP_URL_WEIXIN_AUTH = "https://api.weixin.qq.com/sns/oauth2/access_token"; // 微信AccessToken获取地址
//        public static final String AUTH_WEIXIN_APPID = "wxcdf9208af15cbf4f"; // 微信APPID
//        public static final String AUTH_WEIXIN_SECRET = "2b3cc05222d6cd70a5e028945b18fe08"; // 微信SECRET
//        public static final String AUTH_TENCENT_APPID = "1104495107"; // QQAPPID 1103572223
//        public static final String AUTH_TENCENT_APPKEY = "ejj5aDDxJ6t6vjVP"; // QQAPPKEY
//        public static final String AUTH_SINA_APPID = "1507111219"; // 新浪APPID
//        public static final String AUTH_SINA_REDIRECT_URL = "http://www.dameiren.com"; // 新浪 redirect_url
//        public static final String AUTH_RENREN_ID = ""; // 人人ID
//        public static final String AUTH_RENREN_APPID = ""; // 人人APPID
//        public static final String AUTH_RENREN_APPKEY = ""; // 人人APPKEY
//        public static final String AUTH_YIXIN_APPID = ""; // 易信APPID
//        public static final String AUTH_LAIWANG_APPID = ""; // 来往APPID
//        public static final String AUTH_LAIWANG_APPKEY = ""; // 来往APPKEY
//        public static final String SHARE_JSLIEW_PC_URL="http://res.app.dameiren.com/html/appshare_v2/jshan_pc.html?videoId=";
//        public static final String SHARE_JSLIEW_MOBILE_URL="http://res.app.dameiren.com/html/appshare_v2/jshan_mobile.html?videoId=";
//        public static final String SHARE_JSLIEW_PC_CC_URL="http://res.app.dameiren.com/html/tapeshare.html?videoId=";



        public static final String APP_WEB_JS_ANDROID_API = "AndroidAPI"; // WEBVIEW API
        public static final String JINSHAN_VEDIO_APPID = "QYA06644B135374A453B"; // 金山云视频的appid
        public static final String JINSHAN_VEDIO_AK = "a920feb77c9f32a1c4eb5fe6a4951f21"; // 金山云视频的ak码
        public static final String JINSHAN_VEDIO_SK = "s1322d030dd514920febcfb15a8ad2e7"; // 金山云视频的sk码

    }

    /**请求Action定义*/
    public static class Action {
        /** 请求动作_登录*/
        public static final String ACTION_ACCOUNT_LOGIN = Global.APP_URL + "/user/login.do";
    }
    /**状态码定义*/
    public static class Code {
        /**状态码_成功*/
        public final static int STATUS_SUCCESS = 0;
        /**状态码_失败*/
        public final static int STATUS_FAIL = -1;
        /**判断值_真*/
        public final static int TRUE = 1;
        /**判断值_假*/
        public final static int FALSE = 0;
    }
    /** 类型定义*/
    public static class Type {
        public static final int CODE_LOGIN = 2;//验证码类型_登录
        public static final int CODE_REGISTER = 1;//验证码类型_注册
        public static final int CODE_FORGET_PWD = 3;//验证码类型_找回密码
        public static final int CODE_BIND_PHONE = 4;//验证码类型_找回密码
        public static final int REG_WEIXIN = 1;//注册类型_微信
        public static final int REG_APP = 0;//注册类型_应用
        public static final int AUTH_WEIXIN = 1;//授权类型_微信
        public static final int AUTH_TENCENT = 2;//授权类型_QQ
        public static final int AUTH_SINA = 0;//授权类型_新浪

    }
    }
