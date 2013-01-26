package com.slevenc.ccms.util;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-26
 * Time: 下午7:56
 * To change this template use File | Settings | File Templates.
 */
@Service
public class RequestUtil {


    public String getRootURL(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String uri = request.getRequestURI();
        return url.substring(0, url.length() - uri.length()) + request.getContextPath()+"/";
    }
}
