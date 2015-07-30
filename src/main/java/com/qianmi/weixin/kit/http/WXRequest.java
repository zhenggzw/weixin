package com.qianmi.weixin.kit.http;

import com.qianmi.weixin.exception.WXException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * author: Tkk
 * date: 2015/7/30
 */
public class WXRequest {

    private CloseableHttpClient httpClient;

    public WXRequest() {
        httpClient = HttpClients.createDefault();
    }

    /**
     *
     * @param url
     * @param <T>
     * @return
     */
    public <T> T get(String url, Class<T> resultType) {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            T t = (T)new WXRequestHandler(resultType).handleResponse(response);
            return t;
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
