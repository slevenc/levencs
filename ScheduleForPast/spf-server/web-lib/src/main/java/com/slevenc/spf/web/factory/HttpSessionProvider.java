package com.slevenc.spf.web.factory;

import com.google.inject.Provider;
import com.slevenc.spf.web.WebApplicationContextImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-29
 * Time: 下午10:50
 * To change this template use File | Settings | File Templates.
 */
public class HttpSessionProvider implements Provider<HttpSession> {
    @Override
    public HttpSession get() {
        return WebApplicationContextImpl.getHttpServletRequest().getSession();
    }
}
