package com.slevenc.ccms.util.factory;

import com.slevenc.ccms.logger.LoggerUtil;
import com.slevenc.ccms.util.finder.ClassFinder;
import org.springframework.beans.factory.FactoryBean;

import javax.persistence.Entity;
import java.lang.annotation.Annotation;
import java.util.List;

/**
 * 类查找器工厂类
 * User: Slevenc
 * Date: 13-1-24
 * Time: 下午3:43
 */
public class ClassFinderFactory implements FactoryBean<Class[]> {
    private Class<? extends Annotation> annotationClass = null;

    private String[] packageToScan;


    public void setAnnotationClass(String annotationClass) {
        try {
            this.annotationClass = (Class<? extends Annotation>) Class.forName(annotationClass);
        } catch (Exception ex) {
        }
    }

    public void setPackageToScan(String[] packageToScan) {
        this.packageToScan = packageToScan;
    }

    @Override
    public Class[] getObject() throws Exception {
        Class[] result = new Class[0];
        if (annotationClass != null && packageToScan != null) {
            ClassFinder scf = new ClassFinder();
            scf.setPackageList(packageToScan);
            List<Class> clazz = scf.findAnnotatedClasses(Entity.class);
            result = clazz.toArray(new Class[0]);
        }

        LoggerUtil.i.info("output:" + result);
        return result;
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
