package com.slevenc.spf.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-29
 * Time: 下午9:20
 * To change this template use File | Settings | File Templates.
 */
public class WebApplicationContextImpl {

    private static ThreadLocal<HttpServletRequest> threadLocalRequest = new ThreadLocal<HttpServletRequest>();
    private static ThreadLocal<HttpServletResponse> threadLocalResponse = new ThreadLocal<HttpServletResponse>();
    private static ThreadLocal<String> threadLocalAct = new ThreadLocal<String>();


    public static HttpServletRequest getHttpServletRequest() {
        return threadLocalRequest.get();
    }

    public static HttpServletResponse getHttpServletResponse() {
        return threadLocalResponse.get();
    }

    public static String getAct() {
        return threadLocalAct.get();
    }

    public static void putRequest(HttpServletRequest threadLocalRequest) {
        WebApplicationContextImpl.threadLocalRequest.set(threadLocalRequest);
    }

    public static void putResponse(HttpServletResponse threadLocalResponse) {
        WebApplicationContextImpl.threadLocalResponse.set(threadLocalResponse);
    }


    public static void putAct(String act) {
        threadLocalAct.set(act);
    }

    public static void clearThreadLocal() {
        threadLocalRequest.remove();
        threadLocalResponse.remove();
        threadLocalAct.remove();
    }


}
