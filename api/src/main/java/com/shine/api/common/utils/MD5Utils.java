package com.shine.api.common.utils;

import java.security.MessageDigest;

/**
 * MD5加密.
 *
 * @author shineYu
 * @Date 2019/6/12 18:03
 */
public class MD5Utils {

    private static final String hexDigIts[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * MD5加密.
     *
     * @param origin 字符
     * @return 加密后的密码
     */
    public static String MD5Encode(String origin) {
        String charSetName = "utf8";
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (null == charSetName || "".equals(charSetName)) {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charSetName)));
            }
        } catch (Exception e) {
        }
        return resultString;
    }

    public static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    public static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigIts[d1] + hexDigIts[d2];
    }

    public static void main(String[] args) {
        String result = MD5Utils.MD5Encode("Shine_20191");
        System.out.println(result);
    }

}
