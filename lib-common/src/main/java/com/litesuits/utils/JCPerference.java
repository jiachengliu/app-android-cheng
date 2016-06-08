/*      
 * Copyright (c) 2014 by EagleXad
 * Team: EagleXad 
 * Create: 2014-08-29
 */

package com.litesuits.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.litesuits.utils.des.DES;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

/**
 * @author Aloneter
 * @ClassName: MgrPerference
 * @Description: MgrPerference 管理类
 * @date 2014-8-26 下午4:06:25
 * @Version 1.0
 */
public class JCPerference {

    private String mMainPre = "EX_PRE_MAIN"; // 标签

    private static Context mContext; // 上下文
    private static volatile JCPerference instance = null;


    /**
     * 获取当前实例对象
     *
     * @param context
     * @return
     */
    public static JCPerference getInstance(Context context) {

        if (context == null) {
            throw new IllegalArgumentException("Context must not be null.");
        }
        mContext = context;
        if (instance == null){
            synchronized (JCPerference.class){
                if (instance == null){
                    instance = new JCPerference();
                }
            }

        }

        return instance;
    }

    /**
     * 设置主缓存文件名称
     *
     * @param mainPre
     */
    public void setMainPre(String mainPre) {

        mMainPre = mainPre;
    }

    /**
     * 获取内容 （字符串）
     *
     * @param tag
     * @return
     */
    public String getStringDES(String tag) {

        return DES.decrypt(getString(tag), mContext);
    }

    /**
     * 获取内容 （字符串）
     *
     * @param tag
     * @return
     */
    public String getString(String tag) {

        SharedPreferences mgr = getDefaultSharedPreferences();

        if (mgr == null) {

            return "";
        }

        return mgr.getString(tag, "");
    }

    /**
     * 添加内容 （字符串）
     *
     * @param tag
     * @param value
     */
    public void putString(String tag, String value) {

        SharedPreferences mgr = getDefaultSharedPreferences();

        if (mgr == null) {

            return;
        }

        SharedPreferences.Editor editor = mgr.edit();

        editor.putString(tag, value);

        editor.commit();
    }

    /**
     * 添加内容 （字符串）
     *
     * @param tag
     * @param value
     */
    public void putStringDES(String tag, String value) {

        putString(tag, DES.encrypt(value, mContext));
    }

    /**
     * 获取内容 （Boolean）
     *
     * @param tag
     * @return
     */
    public boolean getBoolean(String tag) {

        SharedPreferences mgr = getDefaultSharedPreferences();

        if (mgr == null) {

            return false;
        }

        return mgr.getBoolean(tag, false);
    }

    /**
     * 添加内容 （Boolean）
     *
     * @param tag
     * @param value
     */
    public void putBoolean(String tag, boolean value) {

        SharedPreferences mgr = getDefaultSharedPreferences();

        if (mgr == null) {

            return;
        }

        SharedPreferences.Editor editor = mgr.edit();

        editor.putBoolean(tag, value);

        editor.commit();
    }

    /**
     * 获取内容 （int）
     *
     * @param tag
     * @return
     */
    public int getInt(String tag) {

        SharedPreferences mgr = getDefaultSharedPreferences();

        if (mgr == null) {

            return 0;
        }

        return mgr.getInt(tag, 0);
    }

    /**
     * 添加内容 （int）
     *
     * @param tag
     * @param value
     */
    public void putInt(String tag, int value) {

        SharedPreferences mgr = getDefaultSharedPreferences();

        if (mgr == null) {

            return;
        }

        SharedPreferences.Editor editor = mgr.edit();

        editor.putInt(tag, value);

        editor.commit();
    }

    /**
     * 获取内容 （Long）
     *
     * @param tag
     * @return
     */
    public long getLong(String tag) {

        SharedPreferences mgr = getDefaultSharedPreferences();

        if (mgr == null) {

            return 0;
        }

        return mgr.getLong(tag, 0);
    }

    /**
     * 添加内容 （Long）
     *
     * @param tag
     * @param value
     */
    public void putLong(String tag, long value) {

        SharedPreferences mgr = getDefaultSharedPreferences();

        if (mgr == null) {

            return;
        }

        SharedPreferences.Editor editor = mgr.edit();

        editor.putLong(tag, value);

        editor.commit();
    }

    /**
     * 获取内容 （对象）
     *
     * @param tag
     * @return
     */
    public Object getObject(String tag) {

        SharedPreferences mgr = getDefaultSharedPreferences();

        if (mgr == null) {

            return null;
        }

        String objStr = mgr.getString(tag, "");

        if (JCStringUtils.getInstance().getInstance().isEmpty(objStr)) {

            return null;
        }

        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        Object obj = null;

        try {
            byte[] byte64 = Base64.decode(objStr, Base64.DEFAULT);

            bais = new ByteArrayInputStream(byte64);
            ois = new ObjectInputStream(bais);

            while (true) {
                obj = ois.readObject();
            }

        } catch (EOFException e) {
            return obj;
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

            try {
                if (ois != null) {
                    ois.close();
                }
                if (bais != null) {
                    bais.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return obj;
    }

    /**
     * 添加内容 （对象）
     *
     * @param tag
     * @param value
     */
    public void putObject(String tag, Object value) {

        SharedPreferences mgr = getDefaultSharedPreferences();

        if (mgr == null || value == null) {

            return;
        }

        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        String objStr = "";

        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);

            oos.writeObject(value);

            objStr = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (oos != null) {
                    oos.close();
                }
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        putString(tag, objStr);
    }

    /**
     * 通过便签删除内容
     *
     * @param tag
     */
    public void clearByTag(String tag) {

        SharedPreferences mgr = getDefaultSharedPreferences();

        if (mgr == null) {

            return;
        }

        SharedPreferences.Editor editor = mgr.edit();

        editor.remove(tag);

        editor.commit();
    }

    /**
     * 通过文件名称删除内容
     *
     * @param pre
     */
    public void clearByPre(String pre) {

        SharedPreferences mgr = getSharedPreferences(pre);

        if (mgr == null) {

            return;
        }

        SharedPreferences.Editor editor = mgr.edit();

        editor.clear();
        editor.commit();
    }

    /**
     * 获取默认对象实例
     *
     * @return
     */
    public SharedPreferences getDefaultSharedPreferences() {

        if (mContext == null || mMainPre == null) {

            return null;
        }

        SharedPreferences preMgr = mContext.getSharedPreferences(mMainPre, 0);

        return preMgr;
    }

    /**
     * 通过文件名获取实例
     *
     * @param pre
     * @return
     */
    public SharedPreferences getSharedPreferences(String pre) {

        if (mContext == null || pre == null) {

            return null;
        }

        SharedPreferences preMgr = mContext.getSharedPreferences(pre, 0);

        return preMgr;
    }

}
