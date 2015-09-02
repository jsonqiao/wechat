package com.linju.framework.model;

import java.util.Date;

/**
 * 数据库实体基础字段类
 *
 * @author jsonqiao
 *
 * @date 2015/6/18
 */
public class BaseDomain extends BaseModel {

    private Integer id;

    private Date createTimestamp;

    private Date lastChangeTimestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Date getLastChangeTimestamp() {
        return lastChangeTimestamp;
    }

    public void setLastChangeTimestamp(Date lastChangeTimestamp) {
        this.lastChangeTimestamp = lastChangeTimestamp;
    }
}
