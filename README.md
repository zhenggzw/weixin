
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

## 全部集合

* WXSerivce