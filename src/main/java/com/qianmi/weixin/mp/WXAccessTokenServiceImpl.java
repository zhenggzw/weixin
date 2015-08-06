package com.qianmi.weixin.mp;

import com.alibaba.fastjson.JSONObject;
import com.qianmi.weixin.WXAccessTokenService;
import com.qianmi.weixin.bean.WXContext;
import com.qianmi.weixin.bean.back.WXAccessToken;
import com.qianmi.weixin.exception.WXException;
import com.qianmi.weixin.kit.http.WXRequest;
import com.qianmi.weixin.kit.http.WXRequestErrorHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author: Tkk
 * date: 2015/8/4
 */
@Service
public class WXAccessTokenServiceImpl implements WXAccessTokenService, WXRequestErrorHandler, InitializingBean {

    /**
     *
     */
    @Autowired
    private WXContext context;

    /**
     * 刷新锁
     */
    private Lock refreshTokenLock = new ReentrantLock();

    /**
     *
     */
    private WXRequest request = new WXRequest(this);

    @Override
    public WXAccessToken getAccessToken() throws WXException {
        return getAccessToken(false);
    }

    @Override
    public WXAccessToken getAccessToken(boolean force) throws WXException {
        //强制刷新, 就重置
        if (force) {
            context.setAccessToken(null);
        }

        //锁, 防止其他再去请求了, 有限制
        refreshTokenLock.lock();
        try {
            if (context.getAccessToken() == null) {
                String url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", context.getAppId(), context.getSecret());
                Date now = new Date();
                JSONObject jsonObject = request.jsonGet(url, null);
                WXAccessToken accessToken = new WXAccessToken();
                accessToken.setAccessToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getInteger("expires_in"));
                now.setTime(now.getTime() + accessToken.getExpiresIn() * 1000);
                accessToken.setExpireTime(now);
                context.setAccessToken(accessToken);
            }
        }
        //
        finally {
            refreshTokenLock.unlock();
        }
        return context.getAccessToken();
    }

    @Override
    public boolean isError(int code) {
        return code != 0;
    }

    @Override
    public boolean errorHandler(int errorCode) {
        boolean canbeReplay = false;
        switch (errorCode) {

            //过期了, 可以重新发送
            case 42001:
            case 40001:
                this.getAccessToken(true);
                canbeReplay = true;
        }
        return canbeReplay;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        WXAccessToken accessToken = getAccessToken();
        context.setAccessToken(accessToken);
    }
}
