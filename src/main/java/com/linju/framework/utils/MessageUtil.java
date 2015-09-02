package com.linju.framework.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * 消息处理工具类
 *
 * @author jsonqiao
 *
 * @date 2015/6/23
 */
public class MessageUtil {

    private static final Log logger = LogFactory.getLog(MessageUtil.class);

    public static final String parseHttpRequestToMessage(HttpServletRequest request) {
        String msg = null;
        int len = request.getContentLength();
        if (len <= 0) {
            logger.error("请求头为空");
            return null;
        }
        try {
            InputStream inputStream = request.getInputStream();
            byte[] bytes = new byte[len];
            inputStream.read(bytes);
            msg = new String(bytes);
            if (logger.isDebugEnabled()) {
                logger.debug("解析HttpServeltRequest为微信Message的结果为[" + msg + "]");
            }
        } catch (IOException e) {
            logger.error("在将HttpServeltRequest请求解析为微信Messaged的过程中出现[IOException]异常，异常详细：" + e);
            return null;
        }
        return msg;
    }
}
