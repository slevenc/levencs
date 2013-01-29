package com.slevenc.spf.web;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-27
 * Time: 上午11:48
 * To change this template use File | Settings | File Templates.
 */
public interface HttpServletExecutor {

    public Object execute(Method m)throws Exception;





}
