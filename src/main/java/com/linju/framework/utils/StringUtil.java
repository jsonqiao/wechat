package com.linju.framework.utils;

/**
 * 字符串操作相关的工具类
 *
 * @author jsonqiao
 *
 * @date 2015/6/17
 */
public abstract class StringUtil {

    public static final String EMPTY_STRING = "";

    /**
     * 将对象数组拼接成字符串
     *
     * <p>
     *     StringUtil.join(null, *) return null
     *     StringUtil.join([], *) return ""
     *     StringUtil.join(["a", "b"], "-") return a-b
     *     StringUtil.join(["a", "b"], "") return ab
     * </p>
     *
     * @param o 对象数组
     *
     * @param connector 连接符号
     *
     * @return 字符串
     */
    public static final String join(Object[] o, String connector) {
        if (o == null) {
            return null;
        }
        if (connector == null) {
            connector = EMPTY_STRING;
        }
        if (o.length == 0) {
            return EMPTY_STRING;
        }
        StringBuffer toStringBuffer = new StringBuffer();
        for (Object obj : o) {
            if (o != null) {
                toStringBuffer.append(obj);
            }
            toStringBuffer.append(connector);
        }
        String result = toStringBuffer.toString();
        if (!EMPTY_STRING.equals(connector)) {
            result = toStringBuffer.deleteCharAt(toStringBuffer.length() - 1).toString();
        }
        return result;
    }

    public static void main(String[] args) {
        String[] strings = new String[]{"a", "b", "c"};
        System.out.println(StringUtil.join(strings, "-"));
        System.out.println(StringUtil.join(strings, ""));
    }
}
