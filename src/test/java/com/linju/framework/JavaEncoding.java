package com.linju.framework;

import java.io.UnsupportedEncodingException;

/**
 * JAVA编码问题
 *
 * @author jsonqiao
 *
 * @date 2015/6/29
 */
public class JavaEncoding {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "abc中文";
        System.out.println(System.getProperty("file.encoding"));
        System.out.println(s);
    }
}
