package com.linju.framework.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 数据加密
 *
 * @author jsonqiao
 *
 * @date 2015/6/17
 */
public abstract class DigestUtil {

    private static final Log logger = LogFactory.getLog(DigestUtil.class);

    /**
     * 对字符串进行SHA1加密，当字符串为空或者null时不进行加密处理，直接返回，采用apache commone-codec jar
     *
     * @param s 加密字符串
     *
     * @return 加密后的字符串
     */
    public static final String sha1Hex(String s) {
        if (StringUtils.isBlank(s)) {
            return s;
        }
        if (logger.isDebugEnabled()) {
            logger.debug("将要进行加密的字符串[ " + s + "]");
        }
        String digestedString = DigestUtils.sha1Hex(s);
        if (logger.isDebugEnabled()) {
            logger.debug("字符串[" + s + "]加密后的内容为[" + digestedString + "]");
        }
        return digestedString;
    }

    public static final String md5(String source) {
        if (StringUtils.isBlank(source)) {
            return source;
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(source.getBytes());
            return bytesToHex(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            logger.error("进行MD5加密过程中出现[NoSuchAlgorithmException]异常, 异常详细信息：" + e);
        }
        return null;
    }

    /**
     * 将字节转化为十六进制
     *
     * @param bytes 字节数组
     *
     * @return
     */
    public static final String bytesToHex(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        StringBuffer hexBuffer = new StringBuffer(bytes.length * 2);
        for (byte b : bytes) {
            int v = b & 0xFF;
            String hexString = Integer.toHexString(v);
            if (hexString.length() < 2) {
                hexBuffer.append("0");
            }
            hexBuffer.append(hexString);
        }
        return hexBuffer.toString();
    }

    public static void main(String[] args) {
        //String md5Result = md5("123456");
        //System.out.println(md5Result);
        //ystem.out.println(md5Result.length());
        System.out.println(sha1Hex("123434"));
    }
}
