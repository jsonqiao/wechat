package com.linju.framework.criteria;

import com.linju.framework.pager.Page;
import com.linju.framework.pager.Pager;

/**
 * 分页查询条件
 *
 * @author jsonqiao
 *
 * @date 2015/6/19
 */
public class PagerCriteria {

    private int pageNo = Pager.DEFAULT_PAGE_NO;

    private int pageSize = Pager.DEFAULT_PAGE_SIZE;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
