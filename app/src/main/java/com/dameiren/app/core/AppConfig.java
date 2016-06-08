/*
 * Copyright (c) 2015, 张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dameiren.app.core;

/**
 * 作者:liujiacheng.
 * 日期: 2016/5/31 0031 17:06.
 * 描述：配置文件常量
 */
public class AppConfig {
    public static final String saveFolder = "app/dameiren";
    // 闪退文件的路径
    public static final String crash = saveFolder+"/crash";
    /**配置信息_配置用户UID*/
    public static final String CONFIG_PER_TAG_APP_USER_UID = "config_pre_tag_app_user_uid";
    /**配置信息_配置用户登录类型*/
    public static final String CONFIG_PER_TAG_APP_USER_LOGIN_TYPE = "config_pre_tag_app_user_login_type";

}
