package com.dameiren.app.ui.base;

import android.content.Context;

import com.dameiren.app.core.MyConstants;
import com.litesuits.utils.JCDeviceUtils;
import com.litesuits.utils.JCMD5Utils;
import com.lzy.okhttputils.model.HttpParams;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 作者:liujiacheng.
 * 日期: 2016/6/3 0003 15:43.
 * 描述：
 */
public class BaseNet {

    public static final String TAG = BaseNet.class.getSimpleName();

    public static HttpParams generateParam(Context context) {

        HttpParams params = new HttpParams();
        params.put("deviceId", JCDeviceUtils.getInstance(context).getDeviceId());
        params.put("versionId", JCDeviceUtils.getInstance(context).getVersionCode() + "");
        params.put("reqTime", System.currentTimeMillis() + "");
        params.put("operatorUid", "");

        return params;
    }

    public static String sign(HttpParams map) {

        map.put("appid", MyConstants.Global.APP_ID);

        TreeMap<String, String> temp = new TreeMap<>();

        Iterator<String> it = map.urlParamsMap.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            String value = map.urlParamsMap.get(key);
            temp.put(key, value);
        }
        String params = getGenerateUrl(temp) + "&" + MyConstants.Global.APP_SECRET;
        String sign = JCMD5Utils.getMD5(params);
        return sign;
    }
    private static String getGenerateUrl(Map<String, String> params) {

        StringBuilder urlBuilder = new StringBuilder();

        if (null != params) {
            Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry<String, String> param = iterator.next();
                String key = param.getKey();
                String value = removeSpecialCharacter(param.getValue());
                urlBuilder.append(key).append('=').append(value);

                if (iterator.hasNext()) {
                    urlBuilder.append('&');
                }
            }
        }

        return urlBuilder.toString();
    }

    private String urlEncode(String url) {
        return url.replace("\"", "%22").replace("{", "%7B").replace("}", "%7D").replace(" ", "%20");
    }

    private static String removeSpecialCharacter(String content) {
        if (content == null) {
            return "";
        }
        return content.replace("\"{", "{").replace("}\"", "}").replace("\"[", "[").replace("]\"", "]").replace("]\\\"", "]").replace("\\", "");
    }

}
