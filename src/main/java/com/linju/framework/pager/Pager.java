package com.linju.framework.pager;

import java.util.ArrayList;

/**
 * 分页对象--MyBatis物理分页
 *
 * @author jsonqiao
 *
 * @date 2015/5/29
 */
public class Pager<T> extends ArrayList<T> {

    public static int DEFAULT_PAGE_NO = 1;

    public static int DEFAULT_PAGE_SIZE = 20;

    public static boolean NO_PAGE = false;

    // 页码，从1开始
    private int pageNo = DEFAULT_PAGE_NO;

    private int pageSize = DEFAULT_PAGE_SIZE;

    private int startRow;

    private int endRow;

    // 记录总数
    private long total;

    // 总页数
    private int totalPage;

    // 是否分页[默认不分页]
    private Boolean isPage = NO_PAGE;

    public Pager() {
        super();
    }

    public Pager(int pageNo, int pageSize) {
        if (pageNo < DEFAULT_PAGE_NO) { // 防止错误参数
            pageNo = DEFAULT_PAGE_NO;
        }
        if (pageSize < DEFAULT_PAGE_NO) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        calculateStartRow();
        calculateEndRow();
    }

    public Pager(int pageNo, int pageSize, boolean isPage) {
        this(pageNo, pageSize);
        this.isPage = isPage;
    }

    protected void calculateStartRow() {
        this.startRow = pageNo * (pageSize - 1);
    }

    protected void calculateEndRow() {
        this.endRow = pageNo * pageSize - 1;
    }

    public Boolean isPage() {
        return isPage;
    }
}
