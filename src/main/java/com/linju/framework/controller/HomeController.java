package com.linju.framework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 主页
 *
 * @author jsonqiao
 *
 * @date 2015/6/19
 */
@Controller
public class HomeController {

    public static final String HOME_PAGE = "/home";

    @RequestMapping(value = HOME_PAGE, method = RequestMethod.GET)
    public String home(Model model) {
        if (model.containsAttribute(LoginController.ACCOUNT_KEY)) {
            System.out.println("======contain account========");
        }
        return HOME_PAGE;
    }
}
