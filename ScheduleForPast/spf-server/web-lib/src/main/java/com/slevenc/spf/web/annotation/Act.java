package com.slevenc.spf.web.annotation;

import com.slevenc.spf.web.HttpServletExecutor;
import com.slevenc.spf.web.executor.DefaultExecutor;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-29
 * Time: 下午11:27
 * To change this template use File | Settings | File Templates.
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface Act {

    Class<? extends HttpServletExecutor> executor() default DefaultExecutor.class;

    String name();
}
