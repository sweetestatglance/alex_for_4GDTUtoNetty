package com.rxkj.util;

/**
 * 编解码常量配置
 * @author alex
 * @date 2020年11月16日
 * @Description
 *
 */
public class ProtoInstant {

    /**
     * 字头
     */
    public static final int FIELD_HEAD = 0xcc;

    public static final int DEVICE_ID = 8662;

    /**
     * 字头长度（字头 + 数据长度）
     */
    public static final int FILED_LEN = 3;

    //心跳
    public static final int HEART_BEAT = 0;

    //登录
    public static final int LOGIN = 1;

}
