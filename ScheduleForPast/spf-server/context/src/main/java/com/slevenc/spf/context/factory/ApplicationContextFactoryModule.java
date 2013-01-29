package com.slevenc.spf.context.factory;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.slevenc.spf.scaner.ClassFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;

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
        Set<Class> classList = classFinder.findAnnotatedClasses(Resource.class);
        for (Class c : classList) {
            if (Modifier.isAbstract(c.getModifiers()) == false && Modifier.isInterface(c.getModifiers()) == false) {
                Resource resource = (Resource) c.getAnnotation(Resource.class);
                if (resource.type().equals(Object.class) == false) {
                    bind(resource.type()).to(c);
                    logger.info("auto bind resource:" + c.getName()+" to "+resource.type());
                }         else{
                    bind(c);
                    logger.info("auto bind resource:" + c.getName());
                }
            }
        }
        //provider
        classList = classFinder.findImplements(Provider.class);
        for (Class c : classList) {
            if (Modifier.isAbstract(c.getModifiers()) == false && Modifier.isInterface(c.getModifiers()) == false) {
                Method[] methods = c.getMethods();
                for (Method m : methods) {
                    if (m.getName().equals("get") && m.getParameterTypes().length == 0 && m.getReturnType().equals(Object.class) == false) {
                        bind(m.getReturnType()).toProvider(c);
                        logger.info("bind " + m.getReturnType().getName() + " to provider:" + c.getName());
                        break;
                    }
                }
            }
        }


    }
}
