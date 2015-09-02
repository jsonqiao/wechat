package com.linju.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author jsonqiao
 *
 * @date 2015/6/18
 */
@Controller
public class ErrorController {

    public static final String PAGE_404 = "404";

    public static final String PAGE_500 = "500";

    @RequestMapping(value = PAGE_404, method = RequestMethod.GET)
    public String _404(Model model) {
        model.addAttribute("errorCode", 404);
        model.addAttribute("errorMsg", "Sorry, but the page you are looking for has note been found. Try checking the URL for error, then hit the\n" +
                "        refresh button on your browser or try found something else in our app.");
        model.addAttribute("backToUrl", "/home");
        return PAGE_404;
    }


    @RequestMapping(value = PAGE_500, method = RequestMethod.GET)
    public String _500(Model model) {
        model.addAttribute("errorCode", 500);
        model.addAttribute("errorMsg", "尊敬的用户，非常抱歉的通知您，服务器在运行的过程中出现未知异常，不能完成请求处理，请您重新浏览");
        model.addAttribute("backToUrl", "/home");
        return PAGE_500;
    }

    @RequestMapping(value = "/doPageSearch", method = RequestMethod.POST)
    public String doPageSearch(Model model) {
        return redirect(PAGE_404);
    }

    protected String redirect(String path) {
        return "redirect:" + path;
    }
}
