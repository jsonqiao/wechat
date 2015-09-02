package com.linju.framework.service.impl;

import com.linju.framework.criteria.AccountCriteria;
import com.linju.framework.criteria.PagerCriteria;
import com.linju.framework.mapper.AccountMapper;
import com.linju.framework.model.Account;
import com.linju.framework.model.ResultModel;
import com.linju.framework.pager.PageInfo;
import com.linju.framework.pager.PageUtil;
import com.linju.framework.service.AccountService;
import com.linju.framework.utils.DigestUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 帐号服务接口实现
 *
 * @author jsonqiao
 *
 * @date 2015/6/19
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static final Log logger = LogFactory.getLog(AccountServiceImpl.class);

    public static final String NO_ACCOUNT_ERROR = "-1";

    public static final String PASSWORD_WRONG_ERROR = "-2";

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account getByUsername(String username) {
        AccountCriteria criteria = new AccountCriteria();
        criteria.setUsername(username);
        List<Account> accounts = accountMapper.getByCriteria(criteria);
        if (accounts.size() > 1) {
            if (logger.isDebugEnabled()) {
                logger.debug("通过姓名查询出的记录有多条");
            }
        }
        return accounts.size() == 0 ? null : accounts.get(0);
    }

    @Override
    public void authentiication(Account requestAccount, Account account, ResultModel<Account> resultModel) {
        if (account == null || requestAccount == null) {
            resultModel.setError(NO_ACCOUNT_ERROR, "帐号信息为空!");
            return;
        }
        if (!DigestUtil.md5(requestAccount.getPassword()).equals(account.getPassword())) {
            resultModel.setError(PASSWORD_WRONG_ERROR, "密码错误");
            return;
        }
        account.setLastLoginTime(requestAccount.getLastLoginTime());
        account.setLastLoginAddress(requestAccount.getLastLoginAddress());
        update(account);
        resultModel.setError(ResultModel.SUCCESS_CODE, "用户认证成功");
        resultModel.setData(account);
    }

    @Override
    public int update(Account account) {
        return accountMapper.update(account);
    }

    @Override
    public PageInfo<Account> getPageDataByCritera(PagerCriteria pagerCriteria, AccountCriteria criteria) {
        PageUtil.startPage(pagerCriteria.getPageNo(), pagerCriteria.getPageSize());
        List<Account> pageData = accountMapper.getByCriteria(criteria);
        return new PageInfo<Account>(pageData);
    }
}
