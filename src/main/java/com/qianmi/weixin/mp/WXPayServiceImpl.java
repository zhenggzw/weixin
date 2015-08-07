package com.qianmi.weixin.mp;

import com.qianmi.weixin.WXPayService;
import com.qianmi.weixin.bean.WXContext;
import com.qianmi.weixin.bean.back.WXPayResult;
import com.qianmi.weixin.bean.back.WXPreparePayJSResult;
import com.qianmi.weixin.bean.back.WXPreparePayResult;
import com.qianmi.weixin.bean.send.WXPreparePay;
import com.qianmi.weixin.exception.WXException;
import com.qianmi.weixin.kit.http.WXRequestErrorHandler;
import com.qianmi.weixin.kit.security.WXSecurity;
import com.qianmi.weixin.kit.xml.XMLUtil;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * author: Tkk
 * date: 2015/8/6
 */
@Service
public class WXPayServiceImpl extends WXServiceAdapter implements WXPayService {


    public WXPayServiceImpl() {
    }

    public WXPayServiceImpl(WXContext context, WXRequestErrorHandler errorHandler) {
        super(context, errorHandler);
    }

    @Override
    public WXPreparePayResult toPreparePay(WXPreparePay wxPreparePay) {
        String sign = toPreparePaySign(wxPreparePay);
        String postXml = "<xml>" +
                "<appid>" + context.getAppId() + "</appid>" +
                "<mch_id>" + context.getPartnerId() + "</mch_id>" +
                "<nonce_str>" + wxPreparePay.getNonceStr() + "</nonce_str>" +
                "<sign>" + sign + "</sign>" +
                "<body><![CDATA[" + wxPreparePay.getBody() + "]]></body>" +
                "<out_trade_no>" + wxPreparePay.getOutTradeNo() + "</out_trade_no>" +
                "<total_fee>" + wxPreparePay.getAmt() + "</total_fee>" +
                "<spbill_create_ip>" + wxPreparePay.getIp() + "</spbill_create_ip>" +
                "<notify_url>" + wxPreparePay.getCallback() + "</notify_url>" +
                "<trade_type>" + wxPreparePay.getTradeType() + "</trade_type>" +
                "<openid>" + wxPreparePay.getOpenid() + "</openid>" +
                "</xml>";
        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        String content = request.post(url, postXml);
        WXPreparePayResult result;
        try {
            Document document = DocumentHelper.parseText(content);
            Node xml = document.selectSingleNode("xml");
            result = new WXPreparePayResult();
            result.setNonceStr(xml.selectSingleNode("nonce_str").getText());
            result.setAppid(xml.selectSingleNode("app_id").getText());
            result.setResultCode(xml.selectSingleNode("result_code").getText());
            result.setReturnCode(xml.selectSingleNode("return_msg").getText());
            result.setReturnMsg(xml.selectSingleNode("return_msg").getText());
            result.setMchId(xml.selectSingleNode("mch_id").getText());
            result.setSign(xml.selectSingleNode("sign").getText());
            result.setPrepayId(xml.selectSingleNode("prepay_id").getText());
            result.setTradeType(xml.selectSingleNode("trade_type").getText());
        } catch (DocumentException e) {
            throw new WXException(e.getMessage());
        }
        return result;
    }

    @Override
    public WXPreparePayJSResult toJSPreparePay(WXPreparePay wxPreparePay) {
        WXPreparePayJSResult result = new WXPreparePayJSResult();
        WXPreparePayResult preparePayResult = toPreparePay(wxPreparePay);
        result.setAppId(context.getAppId());
        result.setTimeStamp(new Date().getTime() + "");
        result.setNonceStr(RandomStringUtils.random(16));
        result.setSignType("MD5");
        result.setPackage("prepay_id=" + preparePayResult.getPrepayId());
        try {
            String sign = WXSecurity.MD5(BeanUtils.describe(preparePayResult));
            result.setPaySign(sign);
            result.setTimestamp(result.getTimestamp());
        } catch (Exception e) {
            throw new WXException("签名失败");
        }
        return result;
    }

    @Override
    public String toPreparePaySign(WXPreparePay wxPreparePay) {
        if (StringUtils.isEmpty(wxPreparePay.getNonceStr())) {
            wxPreparePay.setNonceStr(RandomStringUtils.random(16));
        }
        SortedMap<String, String> signParamMap = new TreeMap<String, String>();
        signParamMap.put("appid", context.getAppId());
        signParamMap.put("mch_id", context.getPartnerId());
        signParamMap.put("nonce_str", wxPreparePay.getNonceStr());
        signParamMap.put("body", wxPreparePay.getBody());
        signParamMap.put("out_trade_no", wxPreparePay.getOutTradeNo());
        signParamMap.put("total_fee", wxPreparePay.getAmt() + "");
        signParamMap.put("spbill_create_ip", wxPreparePay.getIp());
        signParamMap.put("notify_url", wxPreparePay.getCallback());
        signParamMap.put("trade_type", wxPreparePay.getTradeType());
        signParamMap.put("openid", wxPreparePay.getOpenid());
        return WXSecurity.SHA1(signParamMap, context.getPartnerKey());
    }

    @Override
    public WXPayResult toPayResult(String xmlContent) {
        try {
            WXPayResult result = XMLUtil.toBean(xmlContent, "xml", WXPayResult.class);
            return result;
        } catch (Exception e) {
            throw new WXException("解析报文失败");
        }
    }

    @Override
    public boolean checkPayResult(WXPayResult result) {
        try {
            if (result == null) {
                return false;
            }
            if (StringUtils.isBlank(result.getSign())) {
                throw new WXException("签名不能为空");
            }

            Map<String, String> map = BeanUtils.describe(result);
            map.remove("sign");
            String toSign = WXSecurity.SHA1(map, context.getPartnerKey());
            return result.getSign().equals(toSign);
        } catch (Exception e) {
            throw new WXException(e.getMessage());
        }
    }

}
