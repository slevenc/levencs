package com.slevenc.spf.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-29
 * Time: 下午9:15
 * To change this template use File | Settings | File Templates.
 */
public abstract class WebApplicationContext {

    public static HttpServletRequest getHttpServletRequest() {
        return WebApplicationContextImpl.getHttpServletRequest();
    }

    public static HttpServletResponse getHttpServletResponse() {
        return WebApplicationContextImpl.getHttpServletResponse();
    }

    public static String getAct() {
        return WebApplicationContextImpl.getAct();
    }
}
