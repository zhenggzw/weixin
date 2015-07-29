package com.qianmi.weixin;

import com.qianmi.weixin.bean.back.WXAccessToken;
import com.qianmi.weixin.bean.back.WXUser;
import com.qianmi.weixin.bean.send.WXJSApiSignature;
import com.qianmi.weixin.bean.send.WXServiceMessage;
import com.qianmi.weixin.bean.send.WXTemplateMessage;
import com.qianmi.weixin.exception.WXException;

/**
 * Created by Administrator on 2015/7/29.
 */
public interface WXService {


    /**
     * 刷新
     *
     * @return
     * @throws WXException
     */
    public String getAccessToken() throws WXException;

    /**
     * 强制刷新, 会阻塞, 保证在token过期之前只刷新一次
     *
     * @param force
     * @return
     * @throws WXException
     */
    public String getAccessToken(boolean force) throws WXException;

    /**
     * 获取当然页面调用微信js sdk的签名数据
     *
     * @param url
     * @return
     * @throws WXException
     */
    public WXJSApiSignature getJSApiSignature(String url) throws WXException;


    /**
     * 发送消息给微信客户端
     *
     * @param serviceMessage
     * @throws WXException
     */
    public void sendServiceMessage(WXServiceMessage serviceMessage) throws WXException;

    /**
     * 获取微信客户端详细信息
     *
     * @param openid
     * @return
     * @throws WXException
     */
    public WXUser getUser(String openid) throws WXException;

    /**
     * 发送模板消息
     *
     * @param templateMessage
     * @return
     * @throws WXException
     */
    public String templateSend(WXTemplateMessage templateMessage) throws WXException;

    /**
     * 用于只是获取openid
     *
     * @param state
     * @return
     */
    public String getBaseUrl(String state);

    /**
     * 用于获取info的
     *
     * @param state
     * @return
     */
    public String getInfoUrl(String state);

    /**
     * 自定义
     *
     * @param redirectURI
     * @param isBase
     * @param state
     * @return
     */
    public String getUrl(String redirectURI, boolean isBase, String state);

    /**
     * 根据微信返回的code 获取accessToken
     *
     * @param code
     * @return
     * @throws WXException
     */
    public WXAccessToken getUserAccessToken(String code) throws WXException;

    /**
     * 刷新
     *
     * @param wxAccessToken
     * @return
     * @throws WXException
     */
    public WXAccessToken refreshUserAccessToken(WXAccessToken wxAccessToken) throws WXException;

    /**
     * 当调用getInfoUrl的时候, 后续可以调用这个方法
     *
     * @param accessToken
     * @return
     * @throws WXException
     */
    public WXUser getUser(WXAccessToken accessToken) throws WXException;

    /**
     * 配置项
     *
     * @param context
     */
    public void setContext(WXContext context);
}