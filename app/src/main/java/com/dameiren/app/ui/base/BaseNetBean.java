package com.dameiren.app.ui.base;

/**
 * 作者:liujiacheng.
 * 日期: 2016/6/6 0006 13:42.
 * 描述：网络请求父类实体类
 */
public abstract class BaseNetBean {
    public boolean isEmpty(String str) {

        if (str == null || str.equalsIgnoreCase("null")) {

            return true;
        }

        return false;
    }

    public boolean isEmpty(Object obj) {

        if (obj == null) {

            return true;
        }

        return false;
    }

    public void dealEmpty(String str) {

        if (isEmpty(str)) {
            str = "";
        }
    }

    public abstract void dealNull();

    public abstract void set(Object object);
}
