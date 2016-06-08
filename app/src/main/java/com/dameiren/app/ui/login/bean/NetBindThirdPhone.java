package com.dameiren.app.ui.login.bean;

import com.dameiren.app.ui.base.BaseNetBean;
import com.google.gson.annotations.SerializedName;

/**
 * @author Aloneter
 * @ClassName: NetBindThridPhone
 * @Description: 手机绑定信息请求实体类
 * @date 2015-03-04
 * @Version 1.0
 */
public class NetBindThirdPhone extends BaseNetBean {

    @SerializedName(value = "uid")
    public String uid;
    @SerializedName(value = "international_no")
    public String international_no;
    @SerializedName(value = "bind_mobile_no")
    public String bind_mobile_no;

//            "uid":"123"                         //uid
//            "international_no":86             //国际号码段
//            "bind_mobile_no":13800138000      //手机号码

    @Override
    public void dealNull() {

        dealEmpty(this.international_no);
        dealEmpty(this.bind_mobile_no);
    }

    @Override
    public void set(Object object) {

    }

}
