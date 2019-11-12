package com.shine.api.common.utils;

/**
 * *^_^*
 *
 * @author shineYu
 * @Date 2019/6/3 1:31
 */
public class KeyUtils{
    /**
     * 生成订单主表唯一的主键.
     * 格式OM+时间戳
     *
     * @return
     */
    public static synchronized  String genUniqueMasterKey(){
        return "OM" + System.currentTimeMillis();
    }

    /**
     * 生成订单详情表唯一的主键.
     * 格式OD+时间戳
     *
     * @return
     */
    public static synchronized  String genUniqueDetailKey(){
        return "OD" + System.currentTimeMillis();
    }
}
