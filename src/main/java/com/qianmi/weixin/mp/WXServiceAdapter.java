package com.qianmi.weixin.mp;

import com.qianmi.weixin.bean.WXContext;
import com.qianmi.weixin.kit.http.WXRequest;
import com.qianmi.weixin.kit.http.WXRequestErrorHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * author: Tkk
 * date: 2015/8/7
 */
public class WXServiceAdapter implements InitializingBean {
    /**
     *
     */
    @Autowired
    protected WXContext context;


    /**
     *
     */
    protected WXRequest request;

    /**
     *
     */
    @Autowired
    protected WXRequestErrorHandler errorHandler;

    /**
     * 给Spring注入
     */
    public WXServiceAdapter() {
    }

    /**
     * 手动构造
     * @param context
     * @param errorHandler
     */
    public WXServiceAdapter(WXContext context, WXRequestErrorHandler errorHandler) {
        this.context = context;
        this.errorHandler = errorHandler;
        this.request = new WXRequest(errorHandler);
    }

    /**
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        this.request = new WXRequest(errorHandler);
    }
}
