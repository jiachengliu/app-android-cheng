package com.dameiren.app.ui.login.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Aloneter
 * @ClassName: AdminUser
 * @Description: 全局管理用户实体类
 * @date 2014-12-18
 * @Version 1.0
 */
public class AdminUser {

    public static final int TYPE_LOGIN = 1;
    public static final int TYPE_THIRD_LOGIN = 2;


    public String uid;
    public String userName;
    public String mobileNo;
    public String userPicIp;
    public String mBDChannelId;
    public String mBDUserId;
    public String mGTClientId;
    public int mLoginType;
    public int mIsAdmin;
    public int mIsOpenVideo;
    public boolean mIsWarnReceive;
    public boolean mIsWarnNotice;

    public NetUserInfo userInfo;

    public String myQuesion;
    public List<String> myQuestionImages;
    public List<String> myQuestionTags;
    public LinkedHashMap<String, String> myQuestionImageNames;

    public String myLog;
    public String myLogTitle;
    public List<String> myLogTags;
    public Map<String, String> myLogImageNames;
    public Map<String, Integer> myLogImageLocations;

    public AdminUser() {

        uid = "";
        userName = "";
        mobileNo = "";
        userPicIp = "";

        mBDChannelId = "";
        mBDUserId = "";

        mIsWarnReceive = false;
        mIsWarnNotice = false;

        userInfo = new NetUserInfo();

        myQuestionImages = new ArrayList<>();
        myQuestionTags = new ArrayList<>();
        myQuestionImageNames = new LinkedHashMap<>();

        myLogTags = new ArrayList<>();
        myLogImageNames = new HashMap<>();
        myLogImageLocations = new HashMap<>();
    }

}
