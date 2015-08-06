package com.qianmi.weixin.bean;

import com.qianmi.weixin.bean.back.WXAccessToken;
import com.qianmi.weixin.bean.back.WXJSTicket;

/**
 * Created by Administrator on 2015/7/29.
 */
public class WXContext {

    /**
     * 基础信息
     */
    private String appId;
    private String secret;
    private String token;

    /**
     * 商户信息
     */
    private String partnerId;
    private String partnerKey;

    private WXAccessToken accessToken;
    private WXJSTicket jsTicket;
    private String redirectUri;

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerKey() {
        return partnerKey;
    }

    public void setPartnerKey(String partnerKey) {
        this.partnerKey = partnerKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public WXJSTicket getJsTicket() {
        return jsTicket;
    }

    public void setJsTicket(WXJSTicket jsTicket) {
        this.jsTicket = jsTicket;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public WXAccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(WXAccessToken token) {
        this.accessToken = token;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
}
