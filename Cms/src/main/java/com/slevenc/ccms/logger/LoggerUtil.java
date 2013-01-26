package com.slevenc.ccms.logger;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-19
 * Time: 下午12:02
 * To change this template use File | Settings | File Templates.
 */
public class LoggerUtil {

    public static final LoggerUtil i = new LoggerUtil();

    public void info(String msg) {
        System.out.println(msg);
    }

    public void info(String msg, Throwable th) {
        System.out.println(msg);
        th.printStackTrace();
    }
}
