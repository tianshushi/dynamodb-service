/**
 * Copyright (c) 2017, TalkingData and/or its affiliates. All rights reserved.
 */
package net.vicp.sealedbook.dynamodb.util;

/**
 * @author shitianshu on 2018/5/4 下午8:13.
 */
public final class ResponseCode {

    /** 处理成功 */
    public static final int SUCCESS_CODE = 0;

    /** 1XXXXX 安全性校验问题 */
    /** 登录状态异常 */
    public static final int LOGIN_STATE_ERROR = 100001;

    /** 权限错误 */
    public static final int PERMISSION_ERROR = 100010;

    /** 2XXXXX 参数类错误. */

    /** 9XX 系统类错误.*/
    public static final int SYSTEM_ERROR = 900001;


    public static final int SYSTEM_NETWORK_ERROR = 900002;

}