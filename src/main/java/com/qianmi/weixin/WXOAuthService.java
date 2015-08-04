package com.qianmi.weixin;

import com.qianmi.weixin.bean.back.WXOAuthAccessToken;
import com.qianmi.weixin.bean.back.WXUser;
import com.qianmi.weixin.exception.WXException;

/**
 * author: Tkk
 * date: 2015/8/4
 */
public interface WXOAuthService {
    /**
     * 获取微信客户端详细信息
     *
     * @param token
     * @return
     * @throws com.qianmi.weixin.exception.WXException
     */
    public WXUser getUser(WXOAuthAccessToken token) throws WXException;

    /**
     * 用于只是获取openid
     *
     * @param state
     * @return
     */
    public String getBaseUrl(String state);

    /**
     * 用于获取info的
     *
     * @param state
     * @return
     */
    public String getInfoUrl(String state);

    /**
     * 自定义
     *
     * @param redirectURI
     * @param isBase
     * @param state
     * @return
     */
    public String getUrl(String redirectURI, boolean isBase, String state);

    /**
     * 根据微信返回的code 获取accessToken
     *
     * @param code
     * @return
     * @throws WXException
     */
    public WXOAuthAccessToken getUserAccessToken(String code) throws WXException;

    /**
     * 刷新
     *
     * @param accessToken
     * @return
     * @throws WXException
     */
    public WXOAuthAccessToken refreshUserAccessToken(WXOAuthAccessToken accessToken) throws WXException;
}
