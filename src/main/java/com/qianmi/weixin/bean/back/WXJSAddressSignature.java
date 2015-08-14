package com.qianmi.weixin.bean.back;

/**
 * Created by Administrator on 2015/7/29.
 */
public class WXJSAddressSignature {

    private String appId;

    private String scope;

    private String signType;

    private String url;

    private String timeStamp;

    private String nonceStr;

    private String addSign;

    public String getAddSign() {
        return addSign;
    }

    public void setAddSign(String addSign) {
        this.addSign = addSign;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
