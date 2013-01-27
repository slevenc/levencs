package com.slevenc.spf.context.factory;

import com.google.inject.AbstractModule;
import com.slevenc.spf.context.impl.ApplicationContextImpl;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-27
 * Time: 下午4:44
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationContextFactoryModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ApplicationContextImpl.class);
    }
}
