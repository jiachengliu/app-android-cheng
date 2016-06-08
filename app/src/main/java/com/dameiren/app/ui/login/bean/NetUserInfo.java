package com.dameiren.app.ui.login.bean;

import com.dameiren.app.ui.base.BaseNetBean;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Aloneter
 * @ClassName: NetUserInfo
 * @Description: 用户信息实体类
 * @date 2014-12-31
 * @Version 1.0
 */
public class NetUserInfo extends BaseNetBean {

    @SerializedName(value = "uid")
    public String uid;
    @SerializedName(value = "nickname")
    public String nickname;
    @SerializedName(value = "head_img_url")
    public String head_img_url;
    @SerializedName(value = "sex")
    public int sex;
    @SerializedName(value = "age")
    public int age;
    @SerializedName(value = "country")
    public String country;
    @SerializedName(value = "province")
    public String province;
    @SerializedName(value = "city")
    public String city;
    @SerializedName(value = "level")
    public int level;
    @SerializedName(value = "experience")
    public int experience;
    @SerializedName(value = "jfNo")
    public int jfNo;
    @SerializedName(value = "gold")
    public int gold;
    @SerializedName(value = "is_master")
    public int is_master;
    @SerializedName(value = "open_video")
    public int open_video;//是否允许达人直播,0:否，1:是
    @SerializedName(value = "isAdmin")
    public int isAdmin;  // isAdmin=1可以管理帖子
    @SerializedName(value = "create_time")
    public long create_time;
    @SerializedName(value = "birthday")
    public String birthday;
    @SerializedName(value = "description")
    public String description;
    @SerializedName(value = "district")
    public String district;
    @SerializedName(value = "tags")
    public List<String> tags;

//            "uid":"123",                                        //用户id
//            "nickname":"abc",                                   //昵称
//            "head_img_url":"0",                                 //头像地址
//            "sex":0,                                          //性别 0-未知 1-男 2-女
//            "age":1,                                          //年龄
//            "country":"中国",                                 //国家
//            "province":"湖南",                                    //省份
//            "city":"长沙",                                        //城市
//            "level":1,                                            //等级
//            "experience":100                                  //经验值
//            "jfNo":123,                                           //积分值
//            "gold":123                                            //金币
//            "is_master":0                                     //是否是达人老师 0-不是 1-是
//            "create_time":1423549556                          //创建时间 精确到秒的时间戳
//            "birthday":"2012/12/12"                             //生日 格式 1999/01/01
//              "district":"123"                                    //地区
//              "tags":['美白','护肤']                                //标签

    @Override
    public void dealNull() {

        dealEmpty(this.uid);
        dealEmpty(this.nickname);
        dealEmpty(this.head_img_url);
        dealEmpty(this.country);
        dealEmpty(this.province);
        dealEmpty(this.city);
        dealEmpty(this.birthday);
        dealEmpty(this.description);
        dealEmpty(this.district);
    }

    @Override
    public void set(Object object) {

        NetUserInfo obj = (NetUserInfo) object;

        this.uid = obj.uid;
        this.nickname = obj.nickname;
        this.head_img_url = obj.head_img_url;
        this.sex = obj.sex;
        this.age = obj.age;
        this.country = obj.country;
        this.province = obj.province;
        this.city = obj.city;
        this.level = obj.level;
        this.experience = obj.experience;
        this.jfNo = obj.jfNo;
        this.gold = obj.gold;
        this.is_master = obj.is_master;
        this.create_time = obj.create_time;
        this.birthday = obj.birthday;
        this.description = obj.description;
        this.district = obj.district;
        this.tags = obj.tags;
        this.open_video = obj.open_video;
    }

}
