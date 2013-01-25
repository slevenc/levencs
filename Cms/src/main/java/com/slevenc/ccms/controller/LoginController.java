package com.slevenc.ccms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-24
 * Time: 上午11:56
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("user/")
public class LoginController {
    public final static String ATTRIBUTE_OF_USERID_IN_SESSION = "ATTRIBUTE_OF_USERID_IN_SESSION";


    @RequestMapping("/")
    public String loginPage(HttpSession session) {
        String next = "redirect:login/";
        Object userId = session.getAttribute(ATTRIBUTE_OF_USERID_IN_SESSION);
        if (userId != null) {
            //TODO  已登录的进入用户中心
        }

        return next;
    }

    public final static String DEFAULT_USERNAME = "THIS_IS_A_DEFAULT_USERNAME";
    public final static String DEFAULT_PASSWORD = "THIS_IS_A_DEFAULT_PASSWORD";

    @RequestMapping(value = "login/", method = {RequestMethod.POST})
    public String loginJsonDo(String username, String password) {
        String next = "user/login.ftl";

        if (username.equals("1")) {
            next = "user/usercenter.ftl";
        }

        return next;

    }


}
