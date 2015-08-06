package com.qianmi.weixin.bean.back;

import java.util.Date;

/**
 * Created by Administrator on 2015/7/29.
 */
public class WXAccessToken {
    private String accessToken;

    private int expiresIn = -1;

    private Date expireTime;

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

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
