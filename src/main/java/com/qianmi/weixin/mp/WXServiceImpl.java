package com.qianmi.weixin.mp;

import com.qianmi.weixin.*;
import com.qianmi.weixin.bean.back.*;
import com.qianmi.weixin.bean.send.WXServiceMessage;
import com.qianmi.weixin.bean.send.WXTemplateMessage;
import com.qianmi.weixin.exception.WXException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author: Tkk
 * date: 2015/7/30
 */
@Service
public class WXServiceImpl implements WXService {

    @Autowired
    private WXAccessTokenService wxAccessTokenService;

    @Autowired
    private WXJSService wxjsService;

    @Autowired
    private WXOAuthService wxoAuthService;

    @Autowired
    private WXMessageService wxMessageService;


    @Override
    public WXAccessToken getAccessToken() throws WXException {
        return wxAccessTokenService.getAccessToken();
    }

    @Override
    public WXAccessToken getAccessToken(boolean force) throws WXException {
        return wxAccessTokenService.getAccessToken(force);
    }

    @Override
    public WXJSSignature getJSApiSignature(String url) throws WXException {
        return wxjsService.getJSApiSignature(url);
    }

    @Override
    public WXJSTicket getJSTicket() throws WXException {
        return wxjsService.getJSTicket();
    }

    @Override
    public WXJSTicket getJSTicket(boolean force) throws WXException {
        return wxjsService.getJSTicket(force);
    }

    @Override
    public void sendServiceMessage(WXServiceMessage serviceMessage) throws WXException {
        wxMessageService.sendServiceMessage(serviceMessage);
    }

    @Override
    public WXUser getUser(WXOAuthAccessToken token) throws WXException {
        return wxoAuthService.getUser(token);
    }

    @Override
    public WXTemplateMessageResult sendTemplateMessage(WXTemplateMessage templateMessage) throws WXException {
        return wxMessageService.sendTemplateMessage(templateMessage);
    }

    @Override
    public String getBaseUrl(String state) {
        return wxoAuthService.getBaseUrl(state);
    }

    @Override
    public String getInfoUrl(String state) {
        return wxoAuthService.getInfoUrl(state);
    }

    @Override
    public String getUrl(String redirectURI, boolean isBase, String state) {
        return wxoAuthService.getUrl(redirectURI, isBase, state);
    }

    @Override
    public WXOAuthAccessToken getUserAccessToken(String code) throws WXException {
        return wxoAuthService.getUserAccessToken(code);
    }

    @Override
    public WXOAuthAccessToken refreshUserAccessToken(WXOAuthAccessToken wxAccessToken) throws WXException {
        return wxoAuthService.refreshUserAccessToken(wxAccessToken);
    }
}
