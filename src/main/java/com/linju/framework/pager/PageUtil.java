package com.linju.framework.pager;

/**
 * 分页工具
 *
 * @author jsonqiao
 *
 * @date 2015/6/1
 */
public class PageUtil {
    /**
     * 开始分页
     *
     * @param pageNum  页码
     * @param pageSize 每页显示数量
     */
    public static Page startPage(int pageNum, int pageSize) {
        return startPage(pageNum, pageSize, true);
    }

    /**
     * 开始分页
     *
     * @param pageNum  页码
     * @param pageSize 每页显示数量
     * @param count    是否进行count查询
     */
    public static Page startPage(int pageNum, int pageSize, boolean count) {
        return startPage(pageNum, pageSize, count, null);
    }

    /**
     * 开始分页
     *
     * @param pageNum    页码
     * @param pageSize   每页显示数量
     * @param count      是否进行count查询
     * @param reasonable 分页合理化,null时用默认配置
     */
    public static Page startPage(int pageNum, int pageSize, boolean count, Boolean reasonable) {
        return startPage(pageNum, pageSize, count, reasonable, null);
    }

    /**
     * 开始分页
     *
     * @param pageNum      页码
     * @param pageSize     每页显示数量
     * @param count        是否进行count查询
     * @param reasonable   分页合理化,null时用默认配置
     * @param pageSizeZero true且pageSize=0时返回全部结果，false时分页,null时用默认配置
     */
    public static Page startPage(int pageNum, int pageSize, boolean count, Boolean reasonable, Boolean pageSizeZero) {
        Page page = new Page(pageNum, pageSize, count);
        page.setReasonable(reasonable);
        page.setPageSizeZero(pageSizeZero);
        SqlUtil.setLocalPage(page);
        return page;
    }

    /**
     * 开始分页
     *
     * @param params
     */
    public static Page startPage(Object params) {
        Page page = SqlUtil.getPageFromObject(params);
        SqlUtil.setLocalPage(page);
        return page;
    }
}
