package com.qianmi.weixin.kit.http;


import com.alibaba.fastjson.JSON;
import com.qianmi.weixin.exception.WXException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * author: Tkk
 * date: 2015/7/30
 */
public class WXRequestHandler implements ResponseHandler {

    private Class resultType;

    public WXRequestHandler(Class resultType) {
        this.resultType = resultType;
    }

    @Override
    public Object handleResponse(HttpResponse httpResponse) throws IOException {
        StatusLine statusLine = httpResponse.getStatusLine();
        HttpEntity entity = httpResponse.getEntity();
        if (statusLine.getStatusCode() >= 300) {
            EntityUtils.consume(entity);
            throw new WXException("");
        } else {
            String content = EntityUtils.toString(entity);
            Object o = JSON.parseObject(content, resultType);
            return o;
        }
    }
}
