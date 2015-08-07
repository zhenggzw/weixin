package com.qianmi.weixin.mp;

import com.qianmi.weixin.WXOAuthService;
import com.qianmi.weixin.bean.WXContext;
import com.qianmi.weixin.bean.back.WXOAuthAccessToken;
import com.qianmi.weixin.bean.back.WXUser;
import com.qianmi.weixin.exception.WXException;
import com.qianmi.weixin.kit.http.WXRequest;
import com.qianmi.weixin.kit.http.WXRequestErrorHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * author: Tkk
 * date: 2015/8/4
 */
@Service
public class WXOAuthServiceImpl extends WXServiceAdapter implements WXOAuthService {

    public WXOAuthServiceImpl() {
    }

    public WXOAuthServiceImpl(WXContext context, WXRequestErrorHandler errorHandler) {
        super(context, errorHandler);
    }

    @Override
    public WXUser getUser(WXOAuthAccessToken token) throws WXException {
        String url = String.format("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN", token.getAccessToken(), token.getOpenId());
        WXUser wxUser = request.jsonGet(url, WXUser.class);
        return wxUser;
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
    public WXOAuthAccessToken refreshUserAccessToken(WXOAuthAccessToken accessToken) throws WXException {
        String url = String.format("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=", context.getAppId(), accessToken.getRefreshToken());
        accessToken = request.jsonGet(url, WXOAuthAccessToken.class);
        return accessToken;
    }
}
