package com.linju.framework.core.crypto;

/**
 * @author jsonqiao
 *
 * @date 2015/6/26
 */
public enum WechatEncryptType {

    AES("aes", "aes加密"), RAW("raw", "不加密");

    private String type;

    private String desc;

    private WechatEncryptType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public static boolean isEncryptEnabled(String encryptType) {
        return AES.getType().equalsIgnoreCase(encryptType);
    }
}
