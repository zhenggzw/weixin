package com.qianmi.weixin.mp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qianmi.weixin.WXMessageService;
import com.qianmi.weixin.bean.WXContext;
import com.qianmi.weixin.bean.send.WXServiceMessage;
import com.qianmi.weixin.bean.send.WXTemplateMessage;
import com.qianmi.weixin.exception.WXException;
import com.qianmi.weixin.kit.http.WXRequest;
import com.qianmi.weixin.kit.http.WXRequestErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author: Tkk
 * date: 2015/8/4
 */
@Service
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
    public String sendTemplateMessage(WXTemplateMessage templateMessage) throws WXException {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s", context.getAccessToken().getAccessToken());
        String jsonParam = JSON.toJSONString(templateMessage);
        JSONObject result = request.jsonPost(url, jsonParam, null);
        return result.getString("msgid");
    }
}
