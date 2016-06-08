package com.dameiren.app.ui.login.bean;

import com.dameiren.app.ui.base.BaseNetBean;
import com.google.gson.annotations.SerializedName;

/**
 * @author Aloneter
 * @ClassName: NetUserInfoDetail
 * @Description: 用户信息详情实体类
 * @date 2015-02-13
 * @Version 1.0
 */
public class NetUserInfoDetail extends BaseNetBean {

    @SerializedName(value = "picIp")
    public String picIp;
    @SerializedName(value = "bindMobile")
    public NetBindThirdPhone bindMobile;
    @SerializedName(value = "userInfo")
    public NetUserInfo userInfo;

    @Override
    public void dealNull() {

        if (isEmpty(this.bindMobile)) {
            this.bindMobile = new NetBindThirdPhone();
        }
        this.bindMobile.dealNull();

        if (isEmpty(this.userInfo)) {
            this.userInfo = new NetUserInfo();
        }
        this.userInfo.dealNull();

    }

    @Override
    public void set(Object object) {

    }

}
