package com.qianmi.weixin;

import com.qianmi.weixin.bean.back.*;
import com.qianmi.weixin.exception.WXException;

/**
 * author: Tkk
 * date: 2015/8/4
 */
public interface WXJSService {

    /**
     * 获取当然页面调用微信js sdk的签名数据
     *
     * @param url
     * @return
     * @throws com.qianmi.weixin.exception.WXException
     */
    public WXJSSignature getJSApiSignature(String url) throws WXException;

    /**
     * 获取用户地址的签名数据
     * @param url
     * @return
     * @throws WXException
     */
    public WXJSAddressSignature getJSAddressSignature(WXOAuthAccessToken accessToken, String url) throws WXException;

    /**
     * 获取前端拉起列表
     * @param shopId
     * @param cardId
     * @param cardType
     * @return
     * @throws WXException
     */
    public WXJSCardSignature getJSCardSignature(String shopId, String cardId, String cardType) throws WXException;

    /**
     * @return
     * @throws WXException
     */
    public WXJSTicket getJSTicket() throws WXException;

    /**
     * @return
     * @throws WXException
     */
    public WXJSTicket getJSTicket(boolean force) throws WXException;

}
