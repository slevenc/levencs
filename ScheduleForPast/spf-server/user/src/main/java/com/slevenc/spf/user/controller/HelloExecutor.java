package com.slevenc.spf.user.controller;

import com.slevenc.spf.web.json.JsonExecutor;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-27
 * Time: 下午2:35
 * To change this template use File | Settings | File Templates.
 */
public class HelloExecutor extends JsonExecutor {
    @Override
    public Object execute(HttpServletRequest request) {
        String back = "hello";


        return back;
    }

    @Override
    public String getAct() {
        return "hello";
    }
}
