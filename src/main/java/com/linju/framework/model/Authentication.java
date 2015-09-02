package com.linju.framework.model;

/**
 * 微信认证参数
 *
 * @author jsonqiao
 *
 * @date 2015/6/16
 */
public class Authentication {

    // 微信签名
    private String signature;

    // 时间戳
    private String timestamp;

    // 随机数
    private String nonce;

    // 随机字符串
    private String echostr;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getEchostr() {
        return echostr;
    }

    public void setEchostr(String echostr) {
        this.echostr = echostr;
    }
}
