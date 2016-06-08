package com.litesuits.utils;

import android.annotation.SuppressLint;
import android.text.Html;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者:liujiacheng.
 * 日期: 2016/6/3 0003 13:48.
 * 描述：JCStringUtils工具类
 */
public class JCStringUtils {
    private static volatile JCStringUtils instance = null;
    /**
     * 获取实例对象
     *
     * @return
     */
    public static JCStringUtils getInstance() {

        if (instance == null){
            synchronized (JCStringUtils.class){
                if (instance == null){
                    instance = new JCStringUtils();
                }
            }

        }

        return instance;
    }

    /**
     * 是否为空
     *
     * @param str
     * @return
     */
    public boolean isEmpty(String str) {

        if (str == null || str.trim().equals("") || "".equals(str) || str.equalsIgnoreCase("null") || Html.fromHtml(str).toString().equals("null")) {

            return true;
        }

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {

                return false;
            }
        }

        return true;
    }

    /**
     * 毫秒转化
     * @param ms
     * @return
     */
    public String timeToData(long ms){
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;
        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;
        String strDay = day < 10 ? "0" + day : "" + day; //天
        String strHour = hour < 10 ? "0" + hour : "" + hour;//小时
        String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟
        String strSecond = second < 10 ? "0" + second : "" + second;//秒
//            String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//毫秒
        String strMilliSecond = milliSecond < 100 ? "00" : "0"+milliSecond/100;
        return strDay + "," + strHour+","+strMinute+","+strSecond + ","+strMilliSecond;
    }
    /**
     * 是否为数字
     *
     * @param str
     * @return
     */
    public boolean isNumber(String str) {

        try {
            Integer.parseInt(str.toString());
        } catch (Exception e) {

            return false;
        }

        return true;
    }

    /**
     * 是否是今天
     *
     * @param todayStr
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    @SuppressWarnings("deprecation")
    public boolean isToday(String todayStr) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date toDayDate = sdf.parse(todayStr);
            Date nowDate = new Date();

            if (nowDate.getYear() == toDayDate.getYear() && nowDate.getMonth() == toDayDate.getMonth() && nowDate.getDate() == toDayDate.getDate()) {

                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 判断给定字符串是否为空格、空字符串
     *
     * @param input
     * @return
     */
    public boolean isWhiteSpace(String input) {

        boolean retBool = input.matches("^\\s*$");

        return retBool;
    }

    /**
     * 判断给定字符串中是否包含空格
     *
     * @param input
     * @return
     */
    public boolean containWhiteSpace(String input) {

        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(input);

        boolean found = matcher.find();

        return found;
    }

    /**
     * 是否匹配正则表达式
     *
     * @param patternStr
     * @param regStr
     * @return
     */
    public boolean isPattern(String patternStr, String regStr) {

        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(patternStr);

        if (matcher.matches()) {

            return true;
        }

        return false;
    }

    /**
     * 处理为空的字符串
     *
     * @param valStr
     * @return
     */
    public String dealEmpty(String valStr) {

        if (isEmpty(valStr)) {

            return "";
        }

        return valStr;
    }

    /**
     * 获取文件格式大小
     *
     * @param sizeStr
     * @return
     */
    public String getFileSize(String sizeStr) {

        long size = Long.parseLong(sizeStr);

        if (size < 0) {

            return "0";
        }
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};

        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));

        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    /**
     * 获取星期内容
     *
     * @param weekInt
     * @return
     */
    public String getWeek(int weekInt) {

        switch (weekInt) {
            case 1:
                return "一";
            case 2:
                return "二";
            case 3:
                return "三";
            case 4:
                return "四";
            case 5:
                return "五";
            case 6:
                return "六";
            case 7:
                return "日";
        }

        return weekInt + "";
    }

    /**
     * 获取地址域名
     *
     * @param hostStr
     * @return
     */
    public String getHost(String hostStr) {

        String result = hostStr;

        int index = hostStr.indexOf('/', 7);

        if (index > 0) {
            result = result.substring(0, index);
        }

        result += "/";

        return result;
    }

    /**
     * 转换 <br/>
     * 为 \n
     *
     * @param brStr
     * @return
     */
    public String getReplaceBR(String brStr) {

        String[] content = brStr.split("<br/>");
        StringBuffer result = new StringBuffer();

        int n = content.length;

        for (int i = 0; i < n; i++) {
            result.append(content[i]);

            if (i != n - 1) {
                result.append("\n");
            }
        }

        return result.toString();
    }

    /**
     * 替换 \r \n 为空
     *
     * @param rnStr
     * @return
     */
    public String getReplaceRN(String rnStr) {

        String result = rnStr.replace("\n", "").replace("\r", "");

        return result;
    }

    /**
     * 获取请求参数
     *
     * @param urlStr
     * @param params
     * @return
     */
    public String getUrlParam(String urlStr, Map<String, String> params) {

        Set<String> keys = params.keySet();

        String result = urlStr;

        for (String key : keys) {

            String value = params.get(key);
            result = getUrlParam(result, key, value);
        }

        return result;
    }

    /**
     * 获取请求参数集合
     *
     * @param urlStr
     * @param key
     * @param value
     * @return
     */
    public String getUrlParam(String urlStr, String key, String value) {

        if (!urlStr.contains("&" + key + "=") && !urlStr.contains("?" + key + "=")) {

            if (urlStr.indexOf('?') > 0) {
                urlStr += "&" + key + "=" + value;
            } else {
                urlStr += "?" + key + "=" + value;
            }
        }

        return urlStr;
    }

    public String getGenerateUrl(String url, Map<String, String> params) {

        StringBuilder urlBuilder = new StringBuilder(url);

        if (null != params) {
            urlBuilder.append("?");
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

        String realUrl = urlEncode(urlBuilder.toString());

        // 去除url编码对JsonArray的影响
        if (realUrl.contains("%22[")) {
            realUrl = realUrl.replace("%22[", "[");
        }
        if (realUrl.contains("]%22")) {
            realUrl = realUrl.replace("]%22", "]");
        }

        return realUrl;
    }

    private String urlEncode(String url) {
        return url.replace("\"", "%22").replace("{", "%7B").replace("}", "%7D").replace(" ", "%20");
    }

    private String removeSpecialCharacter(String content) {
        if (content == null) {
            return "";
        }
        return content.replace("\"{", "{").replace("}\"", "}").replace("\"[", "[").replace("]\"", "]").replace("]\\\"", "]").replace("\\", "");
    }

}
