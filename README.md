

# 使用方法

## 基于spring

```xml
<context:component-scan base-package="com.qianmi.weixin"></context:component-scan>

<bean class="com.qianmi.weixin.bean.WXContext">
    <property name="appId" value=""/>
    <property name="secret" value=""/>
    <property name="token" value=""/>
</bean>
```

## 直接使用

* WXServiceFactory
    * getAccessTokenService(WXContext context)
    * getJSService(WXContext context)
    * getMessagerService(WXContext context)
    * getOAuthService(WXContext context)
    * getPayService(WXContext context)
    * getService(WXContext context)
    * getService(String appId, String secret, String token, String partnerId, String partnerKey)

<hr/>

# Api

## 初始化

* WXAccessTokenService
    * getAccessToken 获取全局token
    * getAccessToken(boolan force) 获取全局token, 可以刷新, 会锁

## 网页授权

* WXOAuthService
    * getUrl(String redirectURI, boolean isBase, String state) 获取网页授权重定向地址
    * getBaseUrl(String state) 获取简单网页授权
    * getInfoUrl(String state) 获取详情网页授权
    * getUserAccessToken(String code) 网页授权回调获取局部token
    * refreshUserAccessToken(WXOAuthAccessToken accessToken) 刷新局部token

## 发送消息

* WXMessageService
    * sendServiceMessage(WXServiceMessage serviceMessage) 发送客服消息
    * sendTemplateMessage(WXTemplateMessage templateMessage) 发送模板消息

## JS操作

* WXJSService
    * getJSApiSignature(String url) 获取JSAPI签名, 就是wx.config
    * getJSTicket() 获取jsapi_ticket
    * getJSTicket(boolean force) 获取jsapi_tiket, 可刷新, 会锁

## 支付操作

* WXPayService
    * toPreparePay(WXPreparePay wxPreparePay) 创建预支付订单
    * toPreparePaySign(WXPreparePay wxPreparePay) 预支单订单签名
    * toJSPreparePay(WXPreparePay wxPreparePay) 创建JS预支付订单
    * 

## 加密
* WXSecurity
    * SHA1(String... arr)
    * SHA1(String[] arr, String c)  按字符串c进行join, ([1,2,3], &) => 1&2&3
    * SHA1(Map<String, String> params) 先排序, 然后按&相加后加密
    * SHA1(Map<String, String> params, String key) 先排序后, 然后按&相加后加上key=${key}, 然后加密
    * order(Map<String, String> params) 按&排序相加
    * MD5(String src) 加密后全部转大写
    * MD5(Map<String, String> params) 先排序, 然后按&相加后加密后全部转大写
    * MD5(Map<String, String> params, String key) 先排序, 然后按&相加后key=${key}, 然后加密, 然后转大写

## 全部集合

* WXSerivce
