package com.qianmi.weixin;

import com.qianmi.weixin.bean.back.WXPreparePayResult;
import com.qianmi.weixin.bean.send.WXPreparePay;

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
     * 预支付签名
     * @param wxPreparePay
     * @return
     */
    String toPreparePaySign(WXPreparePay wxPreparePay);
}
