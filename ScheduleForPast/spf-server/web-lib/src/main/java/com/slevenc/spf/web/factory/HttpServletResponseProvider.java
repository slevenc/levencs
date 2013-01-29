package com.slevenc.spf.web.factory;

import com.google.inject.Provider;
import com.slevenc.spf.web.WebApplicationContextImpl;

import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-29
 * Time: 下午10:50
 * To change this template use File | Settings | File Templates.
 */
public class HttpServletResponseProvider implements Provider<HttpServletResponse> {
    @Override
    public HttpServletResponse get() {
        return WebApplicationContextImpl.getHttpServletResponse();
    }
}
