package com.dameiren.app.ui.login.bean;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 作者:liujiacheng.
 * 日期: 2016/6/7 0007 14:46.
 * 描述：用户数据表
 */
@Table(name = "TBUser")
public class TBUser extends Model{
    @Column(name = "tid")
    public int id;
    @Column(name = "uid")
    public String uid;
    @Column(name = "userName")
    public String userName;
    @Column(name = "time")
    public String time;
    @Column(name = "head_img_url")
    public String head_img_url;
    @Column(name = "sex")
    public int sex;
    @Column(name = "age")
    public int age;
    @Column(name = "country")
    public String country;
    @Column(name = "province")
    public String province;
    @Column(name = "city")
    public String city;
    @Column(name = "level")
    public int level;
    @Column(name = "experience")
    public int experience;
    @Column(name = "jfNo")
    public int jfNo;
    @Column(name = "gold")
    public int gold;
    @Column(name = "is_master")
    public int is_master;
    @Column(name = "is_admin")
    public int is_admin;
    @Column(name = "open_video")
    public int open_video;
    @Column(name = "create_time")
    public long create_time;
    @Column(name = "birthday")
    public String birthday;
    @Column(name = "description")
    public String description;
    @Column(name = "district")
    public String district;
    @Column(name = "tags")
    public String tags;
    @Column(name = "loginType")
    public int loginType;
    @Column(name = "picIp")
    public String picIp;
    @Column(name = "mobileNo")
    public String mobileNo;
    public void setNetUserInfo(NetUserInfo user) {
        this.uid = user.uid;
        this.userName = user.nickname;
        this.head_img_url = user.head_img_url;
        this.sex = user.sex;
        this.age = user.age;
        this.country = user.country;
        this.province = user.province;
        this.city = user.city;
        this.level = user.level;
        this.experience = user.experience;
        this.jfNo = user.jfNo;
        this.gold = user.gold;
        this.create_time = user.create_time;
        this.is_master = user.is_master;
        this.is_admin = user.isAdmin;
        this.birthday = user.birthday;
        this.description = user.description;
        this.district = user.district;
        this.tags = getString(user.tags);
    }

    public NetUserInfo getNetUserInfo() {
        NetUserInfo user = new NetUserInfo();
        user.uid = this.uid;
        user.nickname = this.userName;
        user.head_img_url = this.head_img_url;
        user.sex = this.sex;
        user.age = this.age;
        user.country = this.country;
        user.province = this.province;
        user.city = this.city;
        user.level = this.level;
        user.experience = this.experience;
        user.jfNo = this.jfNo;
        user.gold = this.gold;
        user.create_time = this.create_time;
        user.is_master = this.is_master;
        user.isAdmin = this.is_admin;
        user.birthday = this.birthday;
        user.description = this.description;
        user.district = this.district;
        user.open_video = this.open_video;
        user.tags = getList(this.tags);

        return user;
    }
    private String getString(List list) {

        if (list == null || list.size() == 0) {

            return "";
        }

        StringBuffer sb = new StringBuffer();
        int size = list.size();

        for (int i = 0; i < size; i++) {
            String temp = (String) list.get(i);

            sb.append(temp).append(",");
        }
        String result = sb.toString();

        return result.substring(0, result.length() - 1);
    }

    private List getList(String string) {

        List result = new ArrayList();

        if (string == null || string.equalsIgnoreCase("null") || string.length() == 0) {

            return result;
        }

        String[] temp = string.split(",");

        if (temp == null || temp.length == 0) {

            return result;
        }

        int length = temp.length;

        result.addAll(Arrays.asList(temp).subList(0, length));

        return result;
    }
}
