package com.slevenc.ccms.controller;

import com.slevenc.ccms.entity.user.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.PrivateKey;

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
        if(session.getAttribute("_user") instanceof  UserEntity){
            next = "user/usercenter.ftl";
        }

        return next;
    }

    public final static String DEFAULT_USERNAME = "THIS_IS_A_DEFAULT_USERNAME";
    public final static String DEFAULT_PASSWORD = "THIS_IS_A_DEFAULT_PASSWORD";

    @RequestMapping(value = "login/", method = {RequestMethod.POST})
    public String loginJsonDo(@RequestParam(value = "username", required = true) String username, @RequestParam(value = "password", required = true) String password
            , ModelMap mm , HttpSession session) {
        String next = "user/login.ftl";

        if (username.equals("1")) {
            UserEntity ue = new UserEntity();
            ue.setNickname("撒苦辣");
            session.setAttribute("_user",ue);
            next = "redirect:../";
        }

        return next;

    }

    @RequestMapping(value = "login/", method = RequestMethod.GET)
    public String login(HttpSession session) {
        String next = "user/login.ftl";

        if(session.getAttribute("_user") instanceof  UserEntity){
            next = "redirect:../";
        }


        return next;
    }


}
