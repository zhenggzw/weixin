package com.qianmi.weixin.kit.http;

import com.alibaba.fastjson.JSONObject;
import com.qianmi.weixin.exception.WXException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

/**
 * author: Tkk
 * date: 2015/8/4
 */
public class Request {

    protected CloseableHttpClient httpClient;

    public Request() {
        this.httpClient = HttpClients.createDefault();
    }

    public String get(String url, Map<String, Object> param) {
        Set<String> keys = param.keySet();
        StringBuilder sb = new StringBuilder(param.size() * 20);
        for (String key : keys) {
            try {
                sb.append(key)
                        .append('=')
                        .append(URLEncoder.encode(param.get(key).toString(), "UTF-8"))
                        .append('&');
            } catch (UnsupportedEncodingException e) {
            }
        }
        sb.trimToSize();
        if (url.indexOf('?') == -1) {
            url = url + '?' + sb.toString();
        } else {
            url = url + '&' + sb.toString();
        }
        return get(url);
    }

    /**
     * @param url
     * @param <T>
     * @return
     */
    public String get(String url) {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            String content = new WXRequestHandler().handleResponse(response).toString();
            return content;
        } catch (IOException e) {
            throw new WXException(e.getMessage());
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * @param url
     * @param <T>
     * @return
     */
    public String post(String url, Object param) {
        HttpPost httpPost = new HttpPost(url);

        //
        if (param instanceof String) {
            StringEntity entity = new StringEntity(param.toString(), "UTF-8");
            httpPost.setEntity(entity);
        }
        //
        else if (param instanceof Map) {
            //TODO: 还没实现
        }

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            String content =  new WXRequestHandler().handleResponse(response).toString();
            return content;
        } catch (IOException e) {
            throw new WXException(e.getMessage());
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
