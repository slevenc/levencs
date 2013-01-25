package com.slevenc.ccms.util.factory;

import org.springframework.beans.factory.FactoryBean;

/**
 * 类查找器工厂类
 * User: Slevenc
 * Date: 13-1-24
 * Time: 下午3:43
 */
public class ClassFinderFactory implements FactoryBean<Class[]> {
    private Class annotationClass = null;

    private String packageToScan = "";


    @Override
    public Class[] getObject() throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public Class<?> getObjectType() {
        return (new Class[0]).getClass();
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
