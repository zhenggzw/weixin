package com.qianmi.weixin.kit.http;

/**
 * author: Tkk
 * date: 2015/8/4
 */
public interface WXRequestErrorHandler {

    /**
     * 是否是错误码
     * @param code
     * @return
     */
    boolean isError(int code);

    /**
     * 这个错误是否能处理
     * @param errorCode
     * @return
     */
    boolean errorHandler(int errorCode);
}
