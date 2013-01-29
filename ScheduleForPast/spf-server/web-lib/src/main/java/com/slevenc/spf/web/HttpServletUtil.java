package com.slevenc.spf.web;

import javax.servlet.http.HttpServletRequest;

/**
 * User: Slevenc
 * Date: 13-1-27
 * Time: 上午11:50
 */
public class HttpServletUtil {

    public static String getRequestUriPath(HttpServletRequest req) {
        String uri = req.getRequestURI();
        return uri.substring(uri.length() - req.getContextPath().length());
    }

    public static String getRootPath(HttpServletRequest req) {
        String url = req.getRequestURL().toString();
        String uri = req.getRequestURI();
        return url.substring(0, url.length() - uri.length()) + req.getContextPath() + "/";
    }
}
