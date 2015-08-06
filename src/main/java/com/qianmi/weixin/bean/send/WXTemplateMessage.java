package com.qianmi.weixin.bean.send;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/29.
 */
public class WXTemplateMessage {
    private String toUser;
    private String templateId;
    private String url;
    private String topColor;
    private Map<String, WXTemplateData> templateDataMap;

    public static class WXTemplateData {
        private String name;
        private String value;
        private String color;

        public WXTemplateData(String name, String value, String color) {
            this.name = name;
            this.value = value;
            this.color = color;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopColor() {
        return topColor;
    }

    public void setTopColor(String topColor) {
        this.topColor = topColor;
    }

    public Map<String, WXTemplateData> getTemplateDataMap() {
        return templateDataMap;
    }

    public WXTemplateMessage addData(String name, String value) {
        return this.addData(name, value, null);
    }

    public WXTemplateMessage addData(String name, String value, String color) {
        if (templateDataMap == null) {
            templateDataMap = new HashMap<String, WXTemplateData>();
        }
        templateDataMap.put(name, new WXTemplateData(name, value, color));
        return this;
    }
}
