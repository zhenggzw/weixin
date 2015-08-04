package com.qianmi.weixin.bean.back;

/**
 * author: Tkk
 * date: 2015/7/30
 */
public class WXJSTicket {

    private String ticket;

    private int expiresIn;

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
