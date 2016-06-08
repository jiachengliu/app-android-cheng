package com.litesuits.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 作者:liujiacheng.
 * 日期: 2016/6/3 0003 13:30.
 * 描述：Activity管理类
 */
public class JCActivityUtils {
    private static CopyOnWriteArrayList<Activity> mActivities;
    private static Context mContext;

    private static volatile JCActivityUtils instance = null;

    /**
     * 获取当前实例
     *
     * @param context
     * @return
     */
    public static JCActivityUtils getInstance(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null.");
        }
        mContext = context;
        if (instance == null) {
            synchronized (JCActivityUtils.class) {
                if (instance == null) {
                    instance = new JCActivityUtils();
                }
            }

        }


        return instance;
    }

    /**
     * Method_添加 Activity 至管理队列
     *
     * @param activity _添加的 Activity
     */
    public void add(Activity activity) {

        if (mActivities == null) {
            mActivities = new CopyOnWriteArrayList<Activity>();
        }

        mActivities.add(activity);
    }

    /**
     * Method_获取当前 Activity
     *
     * @return Activity 对象
     */
    public Activity getCurrent() {

        if (mActivities.size() - 1 > -1) {

        }

        Activity activity = mActivities.get(mActivities.size() - 1);

        return activity;
    }

    /**
     * Method_结束当前 Activity
     */
    public void finish() {

        Activity activity = getCurrent();

        if (activity != null) {
            mActivities.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * Method_结束指定 Activity
     *
     * @param activity _指定的 Activity
     */
    public void finish(Activity activity) {

        if (activity != null) {
            mActivities.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * Method_结束指定类名的 Activity
     *
     * @param cls _类名信息
     */
    public void finish(Class<?> cls) {

        for (int i = 0; i < mActivities.size(); i++) {
            Activity activity = mActivities.get(i);

            if (activity != null && activity.getClass().equals(cls)) {
                finish(activity);
            }
        }
    }

    /**
     * Method_结束所有 Activity
     */
    public void finishAll() {

        for (int i = 0; i < mActivities.size(); i++) {
            Activity activity = mActivities.get(i);

            if (activity != null) {
                finish(activity);
            }
        }

        mActivities.clear();
    }

    /**
     * Method_结束非当前所有的 Activity
     */
    public void finishAllExceptLast(Class<?> cls) {

        for (int i = 0; i < mActivities.size(); i++) {
            Activity activity = mActivities.get(i);

            if (activity != null && !activity.getClass().equals(cls)) {
                i = 0;
                finish(activity);
            }
        }
    }

    /**
     * Method_系统退出
     */
    public void exit() {

        try {
            finishAll();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method_回到主页
     */
    public void goHome() {

        Intent mHomeIntent = new Intent(Intent.ACTION_MAIN);

        mHomeIntent.addCategory(Intent.CATEGORY_HOME);
        mHomeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

        mContext.startActivity(mHomeIntent);
    }

    /**
     * Method_通过 Intent 启动 Activity
     *
     * @param intent _意图对象
     */
    public void start(Intent intent) {

        mContext.startActivity(intent);
    }

    /**
     * Method_启动通过类名启动
     *
     * @param cls _类名信息
     */
    public void start(Class<?> cls) {

        start(cls, null);
    }

    /**
     * Method_启动通过类名和数据启动
     *
     * @param cls    _类名
     * @param bundle _参数数据
     */
    public void start(Class<?> cls, Bundle bundle) {

        Intent intent = new Intent(mContext, cls);

        if (bundle != null) {
            intent.putExtras(bundle);
        }

        start(intent);
    }

    /**
     * Method_启动可以指定类名和返回码返回
     *
     * @param cls         _类名
     * @param requestCode _返回码
     */
    public void startForResult(Class<?> cls, int requestCode) {

        startForResult(cls, null, requestCode);
    }

    /**
     * Method_启动可以指定类名，数据和返回码返回
     *
     * @param cls         _类名
     * @param bundle      _参数数据
     * @param requestCode _返回码
     */
    public void startForResult(Class<?> cls, Bundle bundle, int requestCode) {

        Intent intent = new Intent(mContext, cls);

        if (bundle != null) {
            intent.putExtras(bundle);
        }

        ((Activity) mContext).startActivityForResult(intent, requestCode);
    }

    /**
     * Method_启动创建新的实例
     *
     * @param cls _指定类名启动
     */
    public void startNew(Class<?> cls) {

        startNew(cls, null);
    }

    /**
     * Method_启动创建新的实例通过类名和数据
     *
     * @param cls    _指定类名启动
     * @param bundle _参数数据
     */
    public void startNew(Class<?> cls, Bundle bundle) {

        Intent intent = new Intent(mContext, cls);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (bundle != null) {
            intent.putExtras(bundle);
        }

        start(intent);
    }

    /**
     * Method_发送广播消息
     *
     * @param action _广播对象的  Action
     * @param bundle _广播携带的参数
     */
    public void sendBroadcast(String action, Bundle bundle) {

        if (action == null || action.length() <= 0) {
            return;
        }

        Intent intent = new Intent(action);

        if (bundle != null) {
            intent.putExtras(bundle);
        }
        mContext.sendBroadcast(intent);
    }
}
