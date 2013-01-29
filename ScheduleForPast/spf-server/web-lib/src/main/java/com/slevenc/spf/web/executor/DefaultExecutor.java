package com.slevenc.spf.web.executor;

import com.slevenc.spf.context.ApplicationContext;
import com.slevenc.spf.web.HttpServletExecutor;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-29
 * Time: 下午11:30
 * To change this template use File | Settings | File Templates.
 */
public class DefaultExecutor implements HttpServletExecutor {


    @Override
    public Object execute(Method m) throws Exception {
        Object instance = ApplicationContext.loadClass(m.getDeclaringClass());

        Object[] args = new Object[m.getParameterTypes().length];

        for (int i = 0; i < args.length; i++) {
            args[i] = ApplicationContext.loadClass(m.getParameterTypes()[i]);
        }
        return  m.invoke(instance, args);
    }
}
