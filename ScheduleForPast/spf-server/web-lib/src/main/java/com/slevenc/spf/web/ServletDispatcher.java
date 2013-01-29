package com.slevenc.spf.web;

import com.slevenc.spf.context.ApplicationContext;
import com.slevenc.spf.scaner.ClassFinder;
import com.slevenc.spf.web.annotation.Act;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-27
 * Time: 上午10:59
 * To change this template use File | Settings | File Templates.
 */
public class ServletDispatcher extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ServletDispatcher.class);


    public ServletDispatcher() {
        try {
            loadExecutors();
        } catch (Exception ex) {
            logger.info("载入Executor失败", ex);
        }
    }


    private Map<String, Method> executeMethods = new HashMap<String, Method>();
    private Map<String, Class<? extends HttpServletExecutor>> executorMap = new HashMap<String, Class<? extends HttpServletExecutor>>();

    private void loadExecutors() throws Exception {
        ClassFinder cf = ApplicationContext.getClassFinder();
        Set<Method> methodList = cf.findAnnotationedMethod(Act.class);
        HttpServletExecutor exe = null;
        String act = null;
        for (Method m : methodList) {
            if (Modifier.isAbstract(m.getDeclaringClass().getModifiers()) == false && Modifier.isInterface(m.getDeclaringClass().getModifiers()) == false && Modifier.isPublic(m.getModifiers())) {
                try {
                    Act aa = (Act) m.getAnnotation(Act.class);
                    act = aa.name();
                    if (executeMethods.containsKey(act) == false) {
                        executeMethods.put(act, m);
                        executorMap.put(act, aa.executor());
                        logger.debug("load executor act:" + act + " method:" + m.toGenericString() +" executor:"+aa.executor().getName());
                    } else {
                        logger.warn("重复定义act :" + act + " method1:" + executeMethods.get(act).toGenericString() + " method2:" + m.toGenericString());
                    }
                } catch (Exception ex) {
                    logger.info("实例化Class失败：" + m.getName(), ex);
                }
            }
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String act = req.getParameter("act");
        Method method = executeMethods.get(act);
        Class<? extends HttpServletExecutor> executorClass = executorMap.get(act);

        if (method != null) {
            try {
                WebApplicationContextImpl.putRequest(req);
                WebApplicationContextImpl.putResponse(resp);
                WebApplicationContextImpl.putAct(act);
                HttpServletExecutor executor = ApplicationContext.loadClass(executorClass);
                executor.execute(method);
                //执行完成自动完成事务
            } catch (Exception re) {
                //异常时回滚
                throw new RuntimeException(re);
            } finally {
                WebApplicationContextImpl.clearThreadLocal();
            }
        } else {
            resp.sendError(404);
        }
    }
}
