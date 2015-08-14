package com.qianmi.weixin.kit.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * author: Tkk
 * date: 2015/8/4
 */
public class WXSecurity {

    /**
     * 串接arr参数，生成sha1 digest
     *
     * @param arr
     * @return
     */
    public static String SHA1(String... arr) throws NoSuchAlgorithmException {
        return SHA1(arr, null);
    }

    /**
     * 用&串接arr参数，生成sha1 digest
     *
     * @param arr
     * @return
     */
    public static String SHA1(String[] arr, String c) throws NoSuchAlgorithmException {
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            String a = arr[i];
            sb.append(a);
            if (c != null && i != arr.length - 1) {
                sb.append(c);
            }
        }
        return DigestUtils.sha1Hex(sb.toString());
    }

    /**
     * 参数排序
     *
     * @param params
     * @return
     */
    public static String SHA1(Map<String, String> params) {
        String order = order(params);
        String sign = DigestUtils.sha1Hex(order.toString());
        return sign;
    }

    /**
     * 参数排序
     *
     * @param params
     * @return
     */
    public static String SHA1(Map<String, String> params, String key) {
        String order = order(params).concat("key=" + key);
        String sign = DigestUtils.sha1Hex(order.toString());
        return sign;
    }

    /**
     * 排序
     *
     * @param params
     * @return
     */
    public static String order(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        StringBuilder order = new StringBuilder(params.size() * 20);
        for (String key : keys) {
            String value = params.get(key);
            if (StringUtils.isNotBlank(value)) {
                order.append(key.toLowerCase() + "=" + value + "&");
            }
        }
        order.trimToSize();
        return order.substring(0, order.length() - 1);
    }

    /**
     * @param src
     * @return
     */
    public static String MD5(String src) {
        return DigestUtils.md5Hex(src).toUpperCase();
    }

    /**
     * 参数排序
     *
     * @param params
     * @return
     */
    public static String MD5(Map<String, String> params) {
        String order = order(params);
        String sign = MD5(order.toString());
        return sign;
    }

    /**
     * 参数排序
     *
     * @param params
     * @return
     */
    public static String MD5(Map<String, String> params, String key) {
        String order = order(params).concat("key=" + key);
        String sign = MD5(order.toString());
        return sign;
    }
}
