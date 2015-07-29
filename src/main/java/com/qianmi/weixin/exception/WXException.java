package com.qianmi.weixin.exception;

/**
 * Created by Administrator on 2015/7/29.
 */
public class WXException extends RuntimeException {

    public WXException(String message) {
        super(message);
    }

    @Override
    public Throwable fillInStackTrace() {
        return super.fillInStackTrace();
    }
}
