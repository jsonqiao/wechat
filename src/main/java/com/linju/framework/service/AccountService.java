package com.linju.framework.service;

import com.linju.framework.criteria.AccountCriteria;
import com.linju.framework.criteria.PagerCriteria;
import com.linju.framework.model.Account;
import com.linju.framework.model.ResultModel;
import com.linju.framework.pager.PageInfo;

/**
 * 帐号服务接口
 *
 * @author jsonqiao
 *
 * @date 2015/6/18
 */
public interface AccountService {

    public Account getByUsername(String username);

    /**
     * 用户认证
     *
     * @param requestAccount 要请求认证的帐号
     *
     * @param account 本地帐号认证的帐号
     *
     * @param resultModel 认证结果
     */
    public void authentiication(Account requestAccount, Account account, ResultModel<Account> resultModel);

    public int update(Account account);

    /**
     * 分页查询账户信息
     *
     * @param criteria 查询条件
     *
     * @return
     */
    public PageInfo<Account> getPageDataByCritera(PagerCriteria pagerCriteria, AccountCriteria criteria);
}
