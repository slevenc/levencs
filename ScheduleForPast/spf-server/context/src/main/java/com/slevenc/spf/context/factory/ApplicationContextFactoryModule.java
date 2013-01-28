package com.slevenc.spf.context.factory;

import com.google.inject.AbstractModule;
import com.slevenc.spf.scaner.ClassFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.lang.reflect.Modifier;
import java.util.List;

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
        Logger logger = LoggerFactory.getLogger(ApplicationContextFactory.class);
        //扫描所有@Resource  自动绑定
        ClassFinder classFinder = ApplicationContextFactory.getClassFinder();
        List<Class> classList = classFinder.findAnnotatedClasses(Resource.class);
        for (Class c : classList) {
            if (Modifier.isAbstract(c.getModifiers()) == false && Modifier.isInterface(c.getModifiers()) == false) {
                Resource resource = (Resource) c.getAnnotation(Resource.class);
                if (resource.type().equals(Object.class) == false) {
                    bind(resource.type()).to(c);
                    logger.info("auto bind resource:" + c.getName());
                }
            }
        }
    }
}
