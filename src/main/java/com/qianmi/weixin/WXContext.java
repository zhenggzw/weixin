package com.qianmi.weixin;

import com.qianmi.weixin.bean.back.WXAccessToken;

/**
 * Created by Administrator on 2015/7/29.
 */
public class WXContext {
    protected volatile String appId;
    protected volatile String secret;
    protected volatile WXAccessToken token;
    protected volatile String redirectUri;

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
