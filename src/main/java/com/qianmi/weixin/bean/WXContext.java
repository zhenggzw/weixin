package com.qianmi.weixin.bean;

import com.qianmi.weixin.bean.back.WXAccessToken;
import com.qianmi.weixin.bean.back.WXJSTicket;

/**
 * Created by Administrator on 2015/7/29.
 */
public class WXContext {
    private String appId;
    private String secret;
    private WXAccessToken token;
    private WXJSTicket jsTicket;
    private String redirectUri;

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

    public WXAccessToken getToken() {
        return token;
    }

    public void setToken(WXAccessToken token) {
        this.token = token;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
}
