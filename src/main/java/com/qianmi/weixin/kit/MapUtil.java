package com.qianmi.weixin.kit;

import java.util.HashMap;
import java.util.Map;

/**
 * author: Tkk
 * date: 2015/8/6
 */
public final class MapUtil {
    public static Map toMap(Object... params) {
        Map map = new HashMap();
        if (params.length % 2 == 0) {
            for (int i = 0; i < params.length; i = i + 2) {
                map.put(params[i], params[i + 1]);
            }
        }
        return map;
    }
}
