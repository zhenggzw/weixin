package com.qianmi.weixin;

import com.qianmi.weixin.bean.back.WXPreparePayJSResult;
import com.qianmi.weixin.bean.back.WXPreparePayResult;
import com.qianmi.weixin.bean.send.WXPreparePay;

import java.util.Map;

/**
 * author: Tkk
 * date: 2015/8/6
 */
public interface WXPayService {

    /**
     * 预支付
     *
     * @param wxPreparePay
     * @return
     */
    WXPreparePayResult toPreparePay(WXPreparePay wxPreparePay);

    /**
     * js 预支付调用
     * @param wxPreparePay
     * @return
     */
    WXPreparePayJSResult toJSPreparePay(WXPreparePay wxPreparePay);

    /**
     * 预支付签名
     * @param wxPreparePay
     * @return
     */
    String toPreparePaySign(WXPreparePay wxPreparePay);

    /**
     *
     * @param params
     * @return
     */
    String toPaySign(Map<String, String> params);
}
