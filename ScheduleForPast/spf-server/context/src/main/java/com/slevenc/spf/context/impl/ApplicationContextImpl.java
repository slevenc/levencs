package com.slevenc.spf.context.impl;

import com.slevenc.spf.scaner.ClassFinder;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-27
 * Time: 下午4:38
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationContextImpl {


    private ClassFinder classFinder = null;

    public ClassFinder getClassFinder() {
        return classFinder;
    }


    public void setClassFinder(ClassFinder classFinder) {
        if (this.classFinder == null) {
            this.classFinder = classFinder;
        }
    }
}
