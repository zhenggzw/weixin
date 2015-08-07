package com.qianmi.weixin.mp;

import com.qianmi.weixin.WXJSService;
import com.qianmi.weixin.bean.WXContext;
import com.qianmi.weixin.bean.back.WXJSSignature;
import com.qianmi.weixin.bean.back.WXJSTicket;
import com.qianmi.weixin.exception.WXException;
import com.qianmi.weixin.kit.http.WXRequest;
import com.qianmi.weixin.kit.http.WXRequestErrorHandler;
import com.qianmi.weixin.kit.security.WXSecurity;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author: Tkk
 * date: 2015/8/4
 */
@Service
public class WXJSServiceImpl extends WXServiceAdapter implements WXJSService {

    public WXJSServiceImpl() {
        super();
    }

    public WXJSServiceImpl(WXContext context, WXRequestErrorHandler errorHandler) {
        super(context, errorHandler);
    }

    /**
     * 刷新锁
     */
    private Lock refreshTokenLock = new ReentrantLock();

    @Override
    public WXJSSignature getJSApiSignature(String url) throws WXException {
        long timestamp = System.currentTimeMillis() / 1000;
        String noncestr = RandomStringUtils.random(16);
        String jsapiTicket = getJSTicket().getTicket();
        try {
            String signature = WXSecurity.SHA1(new String[]{
                    "jsapi_ticket=" + jsapiTicket,
                    "noncestr=" + noncestr,
                    "timestamp=" + timestamp,
                    "url=" + url}, "&");
            WXJSSignature result = new WXJSSignature();
            result.setTimestamp(timestamp);
            result.setNonceStr(noncestr);
            result.setUrl(url);
            result.setSignature(signature);
            return result;
        } catch (NoSuchAlgorithmException e) {
            throw new WXException(e.getMessage());
        }
    }

    @Override
    public WXJSTicket getJSTicket() throws WXException {
        return getJSTicket(false);
    }

    @Override
    public WXJSTicket getJSTicket(boolean force) throws WXException {
        if (force) {
            context.setJsTicket(null);
        }

        //锁, 防止其他再去请求了, 有限制
        refreshTokenLock.lock();
        try {
            if (context.getAccessToken() == null) {
                String url = String.format("https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token", context.getAccessToken().getAccessToken());
                WXJSTicket jsonTicket = request.jsonGet(url, WXJSTicket.class);
                context.setJsTicket(jsonTicket);
            }
        }
        //
        finally {
            refreshTokenLock.unlock();
        }

        return context.getJsTicket();
    }
}
