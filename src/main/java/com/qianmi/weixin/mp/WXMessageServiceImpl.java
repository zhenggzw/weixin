package com.qianmi.weixin.mp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qianmi.weixin.WXMessageService;
import com.qianmi.weixin.bean.WXContext;
import com.qianmi.weixin.bean.send.WXServiceMessage;
import com.qianmi.weixin.bean.send.WXTemplateMessage;
import com.qianmi.weixin.exception.WXException;
import com.qianmi.weixin.kit.MapUtil;
import com.qianmi.weixin.kit.http.WXRequest;
import com.qianmi.weixin.kit.http.WXRequestErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * author: Tkk
 * date: 2015/8/4
 */
@Service
public class WXMessageServiceImpl extends WXServiceAdapter implements WXMessageService {

    public WXMessageServiceImpl() {
    }

    public WXMessageServiceImpl(WXContext context, WXRequestErrorHandler errorHandler) {
        super(context, errorHandler);
    }

    @Override
    public void sendServiceMessage(WXServiceMessage serviceMessage) throws WXException {
    }

    @Override
    public String sendTemplateMessage(WXTemplateMessage templateMessage) throws WXException {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s", context.getAccessToken().getAccessToken());
        Map<String, Object> o = MapUtil.toMap(
                "touser", templateMessage.getToUser(),
                "template_id", templateMessage.getTemplateId(),
                "data", templateMessage.getTemplateDataMap(),
                "url", templateMessage.getUrl(),
                "topcolor", templateMessage.getTopColor());
        String jsonParam = JSON.toJSONString(o);
        JSONObject result = request.jsonPost(url, jsonParam, null);
        return result.getString("msgid");
    }
}
