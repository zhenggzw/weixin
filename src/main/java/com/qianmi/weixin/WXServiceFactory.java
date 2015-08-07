package com.qianmi.weixin;

import com.qianmi.weixin.bean.WXContext;
import com.qianmi.weixin.kit.http.WXRequestErrorHandler;
import com.qianmi.weixin.mp.*;

/**
 * author: Tkk
 * date: 2015/8/7
 */
public final class WXServiceFactory {

    /**
     * @param context
     * @return
     */
    public static WXAccessTokenService getAccessTokenService(WXContext context) {
        return new WXAccessTokenServiceImpl(context);
    }

    /**
     * @param context
     * @return
     */
    public static WXJSService getJSService(WXContext context) {
        WXRequestErrorHandler errorHandler = new WXAccessTokenServiceImpl(context);
        return new WXJSServiceImpl(context, errorHandler);
    }

    /**
     * @param context
     * @return
     */
    public static WXMessageService getMessagerService(WXContext context) {
        WXRequestErrorHandler errorHandler = new WXAccessTokenServiceImpl(context);
        return new WXMessageServiceImpl(context, errorHandler);
    }

    /**
     * @param context
     * @return
     */
    public static WXOAuthService getOAuthService(WXContext context) {
        WXRequestErrorHandler errorHandler = new WXAccessTokenServiceImpl(context);
        return new WXOAuthServiceImpl(context, errorHandler);
    }

    /**
     * @param context
     * @return
     */
    public static WXPayService getPayService(WXContext context) {
        WXRequestErrorHandler errorHandler = new WXAccessTokenServiceImpl(context);
        return new WXPayServiceImpl(context, errorHandler);
    }

    /**
     * @param context
     * @return
     */
    public static WXService getService(WXContext context) {
        WXAccessTokenServiceImpl errorHandler = new WXAccessTokenServiceImpl(context);
        WXJSService wxjsService = new WXJSServiceImpl(context, errorHandler);
        WXMessageService wxMessageService = new WXMessageServiceImpl(context, errorHandler);
        WXOAuthService wxoAuthService = new WXOAuthServiceImpl(context, errorHandler);
        WXPayService wxPayService = new WXPayServiceImpl(context, errorHandler);

        //
        WXServiceImpl wxService = new WXServiceImpl();
        wxService.setWxAccessTokenService(errorHandler);
        wxService.setWxjsService(wxjsService);
        wxService.setWxMessageService(wxMessageService);
        wxService.setWxoAuthService(wxoAuthService);
        return wxService;
    }

    /**
     * @return
     */
    public static WXService getService(String appId, String secret, String token, String partnerId, String partnerKey) {
        WXContext context = new WXContext();
        context.setAppId(appId);
        context.setSecret(secret);
        context.setToken(token);
        context.setPartnerId(partnerId);
        context.setPartnerKey(partnerKey);
        return getService(context);
    }
}
