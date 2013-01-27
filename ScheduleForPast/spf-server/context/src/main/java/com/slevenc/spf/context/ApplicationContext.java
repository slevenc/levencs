package com.slevenc.spf.context;

import com.slevenc.spf.context.factory.ApplicationContextFactory;
import com.slevenc.spf.context.impl.ApplicationContextImpl;
import com.slevenc.spf.scaner.ClassFinder;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-27
 * Time: 下午4:17
 * To change this template use File | Settings | File Templates.
 */
public abstract class ApplicationContext {
    private static ApplicationContextImpl impl = ApplicationContextFactory.getApplicationContext();

    public static ClassFinder getClassFinder() {
        return ApplicationContextFactory.getClassFinder();
    }
}
