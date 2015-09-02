package com.linju.framework.mapper;

import com.linju.framework.criteria.AccountCriteria;
import com.linju.framework.model.Account;

import java.util.List;

/**
 * @author jsonqiao
 *
 * @date 2015/6/19
 */
public interface AccountMapper {

    /**
     * 通过查询条件查询账户
     *
     * @param criteria 查询条件
     *
     * @return 满足条件的查询结果列表
     */
    public List<Account> getByCriteria(AccountCriteria criteria);

    public int update(Account account);

    public Integer insert(Account account);
}
