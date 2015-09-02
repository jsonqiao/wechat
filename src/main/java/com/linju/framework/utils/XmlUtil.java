package com.linju.framework.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.StringReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * xml类型解析工具
 *
 * @author jsonqiao
 *
 * @date 2015/6/25
 */
public class XmlUtil {

    private static final Log logger = LogFactory.getLog(XmlUtil.class);

    public static final Map<String, Object> parse(final String source) {
        if (StringUtils.isBlank(source)) {
            if (logger.isDebugEnabled()) {
                logger.debug("解析的数据源字符串[source]为空，不进行解析，直接返回空Map");
                return Collections.emptyMap();
            }
        }
        Map<String, Object> parseResult = new HashMap<>();
        SAXReader saxReader = new SAXReader();
        StringReader sr = new StringReader(source);
        Document document;
        try {
            document = saxReader.read(sr);
        } catch (DocumentException e) {
            logger.error("解析数据源[" + source + "]出现异常[DocumentException]，异常详细信息：" + e);
            return Collections.emptyMap();
        }
        Element root = document.getRootElement();
        parse(root, parseResult);
        return parseResult;
    }

    /**
     * 解析
     *
     * @param element
     *
     * @param storeMap
     */
    private static void parse(Element element, Map<String, Object> storeMap) {
        List<Element> elements = element.elements();
        if (elements.isEmpty()) {
            storeMap.put(element.getName(), element.getText());
        } else {
            for (Element e : elements) {
                parse(e, storeMap);
            }
        }

    }

    public static void main(String[] args) throws DocumentException {
        String xml = "<xml><ToUserName><text><![CDATA[toUser]]></text></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName> <CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[this is a test]]></Content><MsgId>1234567890123456</MsgId></xml>";

        Map<String, Object> map = XmlUtil.parse(xml);
        System.out.println(map);

    }
}
