package com.qianmi.weixin.mp;

import com.alibaba.fastjson.JSON;
import com.qianmi.weixin.WXMessageService;
import com.qianmi.weixin.bean.WXContext;
import com.qianmi.weixin.bean.back.WXTemplateMessageResult;
import com.qianmi.weixin.bean.send.WXServiceMessage;
import com.qianmi.weixin.bean.send.WXTemplateMessage;
import com.qianmi.weixin.exception.WXException;
import com.qianmi.weixin.kit.http.WXRequest;
import com.qianmi.weixin.kit.http.WXRequestErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * author: Tkk
 * date: 2015/8/4
 */
public class WXMessageServiceImpl implements WXMessageService {

    /**
     *
     */
    @Autowired
    private WXContext context;

    /**
     *
     */
    @Autowired
    private WXRequestErrorHandler errorHandler;

    /**
     *
     */
    private WXRequest request = new WXRequest(errorHandler);

    @Override
    public void sendServiceMessage(WXServiceMessage serviceMessage) throws WXException {
    }

    @Override
    public WXTemplateMessageResult sendTemplateMessage(WXTemplateMessage templateMessage) throws WXException {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s", context.getToken().getAccessToken());
        String jsonParam = JSON.toJSONString(templateMessage);
        WXTemplateMessageResult result = request.jsonPost(url, jsonParam, WXTemplateMessageResult.class);
        return result;
    }
}
