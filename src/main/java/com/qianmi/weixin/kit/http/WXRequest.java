package com.qianmi.weixin.kit.http;

import com.alibaba.fastjson.JSONObject;
import com.qianmi.weixin.exception.WXException;

import java.util.Map;

/**
 * author: Tkk
 * date: 2015/7/30
 */
public class WXRequest extends Request {

    private WXRequestErrorHandler errorHandler;

    public WXRequest(WXRequestErrorHandler errorHandler) {
        super();
        this.errorHandler = errorHandler;
    }

    /**
     * @param url
     * @param resultType
     * @param <T>
     * @return
     */
    public <T> T jsonGet(String url, Class<T> resultType) throws WXException {
        return jsonGet(url, null, resultType);
    }

    /**
     * @param url
     * @param <T>
     * @return
     */
    public <T> T jsonGet(String url, Map<String, Object> param, Class<T> resultType) throws WXException {
        String result = get(url, param);
        JSONObject object = JSONObject.parseObject(result);
        Integer errorCode = null;
        try {
            object.getInteger("errcode");
        } catch (Exception e) {
        }

        //如果是错误, 并且这个错误能消化
        if (errorCode != null && errorHandler.isError(errorCode) && errorHandler.errorHandler(errorCode)) {
            return jsonGet(url, param, resultType);
        }
        //
        else if (errorCode != null && errorHandler.isError(errorCode)) {
            throw new WXException(String.format("请求: %s 错误码: %s", url, errorCode));
        }
        //
        else {
            if (resultType != null) {
                return JSONObject.parseObject(result, resultType);
            } else {
                return (T) object;
            }
        }
    }

    /**
     * @param url
     * @param resultType
     * @param <T>
     * @return
     * @throws WXException
     */
    public <T> T jsonPost(String url, Class<T> resultType) throws WXException {
        return jsonPost(url, null, resultType);
    }

    /**
     * @param url
     * @param <T>
     * @return
     */
    public <T> T jsonPost(String url, Object param, Class<T> resultType) throws WXException {
        String result = post(url, param);
        JSONObject object = JSONObject.parseObject(result);
        //
        Integer errorCode = null;
        try {
            object.getInteger("errcode");
        } catch (Exception e) {
        }

        //如果是错误, 并且这个错误能消化
        if (errorCode != null && errorHandler.isError(errorCode) && errorHandler.errorHandler(errorCode)) {
            return jsonPost(url, param, resultType);
        }
        //
        else if (errorCode != null && errorHandler.isError(errorCode)) {
            throw new WXException(String.format("请求: %s 错误码: %s", url, errorCode));
        }
        //
        else {
            if (resultType == null) {
                return (T) object;
            } else {
                return JSONObject.parseObject(result, resultType);
            }
        }
    }
}
