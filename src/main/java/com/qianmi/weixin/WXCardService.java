package com.qianmi.weixin;

import com.alibaba.fastjson.JSONObject;
import com.qianmi.weixin.exception.WXException;

/**
 * author: Tkk
 * date: 2015/8/13
 */
public interface WXCardService {

    /**
     * 获取卡卷信息
     * @param cardId
     * @return
     */
    JSONObject getCardInfo(String cardId) throws WXException;

    /**
     * 核销卡卷
     * @param encyptCode
     * @return
     * @throws WXService
     */
    JSONObject consumeCard(String encyptCode) throws WXException;
}
