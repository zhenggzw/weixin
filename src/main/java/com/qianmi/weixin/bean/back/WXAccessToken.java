package com.qianmi.weixin.bean.back;

/**
 * Created by Administrator on 2015/7/29.
 */
public class WXAccessToken {
    private String accessToken;

    private int expiresIn = -1;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
