package com.qianmi.weixin.kit.security;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * author: Tkk
 * date: 2015/8/4
 */
public class SHA {

    /**
     * 串接arr参数，生成sha1 digest
     *
     * @param arr
     * @return
     */
    public static String gen(String... arr) throws NoSuchAlgorithmException {
        return gen(arr, null);
    }

    /**
     * 用&串接arr参数，生成sha1 digest
     *
     * @param arr
     * @return
     */
    public static String gen(String[] arr, String c) throws NoSuchAlgorithmException {
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
}
