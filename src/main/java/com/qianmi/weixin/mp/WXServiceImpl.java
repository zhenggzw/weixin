package com.qianmi.weixin.mp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qianmi.weixin.WXContext;
import com.qianmi.weixin.WXService;
import com.qianmi.weixin.bean.back.*;
import com.qianmi.weixin.bean.send.WXServiceMessage;
import com.qianmi.weixin.bean.send.WXTemplateMessage;
import com.qianmi.weixin.exception.WXException;
import com.qianmi.weixin.kit.http.WXRequest;
import com.qianmi.weixin.kit.http.WXRequestErrorHandler;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author: Tkk
 * date: 2015/7/30
 */
public class WXServiceImpl implements WXService, WXRequestErrorHandler {

    /**
     *
     */
    private WXContext context;

    /**
     * 刷新锁
     */
    private Lock refreshTokenLock = new ReentrantLock();

    /**
     *
     */
    private WXRequest request;

    @Override
    public String getAccessToken() throws WXException {
        return getAccessToken(false);
    }

    @Override
    public String getAccessToken(boolean force) throws WXException {
        //强制刷新, 就重置
        if (force) {
            context.setToken(null);
        }

        //锁, 防止其他再去请求了, 有限制
        refreshTokenLock.lock();
        try {
            if (context.getToken() == null) {
                String url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", context.getAppId(), context.getSecret());
                WXAccessToken accessToken = request.jsonGet(url, WXAccessToken.class);
                context.setToken(accessToken);
            }
        }
        //
        finally {
            refreshTokenLock.unlock();
        }
        return context.getToken().getAccessToken();
    }

    @Override
    public WXJSApiSignature getJSApiSignature(String url) throws WXException {
        return null;
    }

    @Override
    public void sendServiceMessage(WXServiceMessage serviceMessage) throws WXException {

    }

    @Override
    public WXUser getUser(WXOAuthAccessToken token) throws WXException {
        String url = String.format("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN", token.getAccessToken(), token.getOpenId());
        WXUser wxUser = request.jsonGet(url, WXUser.class);
        return wxUser;
    }

    @Override
    public WXTemplateResult sendTemplateMessage(WXTemplateMessage templateMessage) throws WXException {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s", context.getToken().getAccessToken());
        String jsonParam = JSON.toJSONString(templateMessage);
        WXTemplateResult result = request.jsonPost(url, jsonParam, WXTemplateResult.class);
        return null;
    }

    @Override
    public String getBaseUrl(String state) {
        return getUrl(context.getRedirectUri(), true, state);
    }

    @Override
    public String getInfoUrl(String state) {
        return getUrl(context.getRedirectUri(), false, state);
    }

    @Override
    public String getUrl(String redirectURI, boolean isBase, String state) {
        if (StringUtils.isEmpty(redirectURI)) {
            throw new WXException("网页授权构造失败, 回调地址为空");
        }
        try {
            redirectURI = URLEncoder.encode(redirectURI, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        String scope = isBase ? "snsapi_base" : "snsapi_userinfo";
        String url = String.format("https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s", context.getAppId(), redirectURI, scope, state);
        return url;
    }

    @Override
    public WXOAuthAccessToken getUserAccessToken(String code) throws WXException {
        String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code", context.getAppId(), context.getSecret(), code);
        WXOAuthAccessToken accessToken = request.jsonGet(url, WXOAuthAccessToken.class);
        return accessToken;
    }

    @Override
    public WXOAuthAccessToken refreshUserAccessToken(WXOAuthAccessToken wxAccessToken) throws WXException {
        String url = String.format("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=", context.getAppId(), wxAccessToken.getRefreshToken());
        WXOAuthAccessToken accessToken = request.jsonGet(url, WXOAuthAccessToken.class);
        return accessToken;
    }

    @Override
    public void setContext(WXContext context) {
        this.context = context;
        this.request = new WXRequest(this);
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
}
