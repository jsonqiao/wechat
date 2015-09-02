package com.linju.framework.controller;

import com.linju.framework.model.Account;
import com.linju.framework.model.ResultModel;
import com.linju.framework.service.AccountService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jsonqiao
 *
 * @date 2015/6/18
 */
@Controller
public class LoginController {

    private static final Log logger = LogFactory.getLog(LoginController.class);

    public static final String LOGIN_PAGE = "/login";

    public static final String LOGIN_KEY = "login";

    public static final String ACCOUNT_KEY = "account";

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = LOGIN_PAGE, method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("copyright", createCopyRight());
        return LOGIN_PAGE;
    }

    @RequestMapping(value = LOGIN_PAGE, method = RequestMethod.POST)
    @ResponseBody
    public ResultModel<Account> login(Model model, Account accout, HttpServletRequest request, HttpSession session) {
        ResultModel<Account> resultModel = new ResultModel<>();
        accout.setLastLoginAddress(request.getRemoteAddr());
        accout.setLastLoginTime(new Date());
        Account localAccount = accountService.getByUsername(accout.getUsername());
        accountService.authentiication(accout, localAccount, resultModel);
        // 认证成功, 同步用户信息到SESSION
        if (ResultModel.SUCCESS_CODE.equals(resultModel.getErrCode())) {
            session.setAttribute(LOGIN_KEY, true);
            session.setAttribute(ACCOUNT_KEY, localAccount);
        }
        return resultModel;
    }

    public static final String createCopyRight() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        StringBuffer copy = new StringBuffer();
        copy.append("杭州邻聚科技有限公司版权所有 &copy; " + sdf.format(new Date()));
        return copy.toString();
    }
}
