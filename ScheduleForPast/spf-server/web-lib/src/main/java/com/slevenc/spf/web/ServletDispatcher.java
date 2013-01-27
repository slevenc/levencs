package com.slevenc.spf.web;

import com.slevenc.spf.context.ApplicationContext;
import com.slevenc.spf.scaner.ClassFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    private Map<String, HttpServletExecutor> executors = new HashMap<String, HttpServletExecutor>();

    private void loadExecutors() throws Exception {
        ClassFinder cf = ApplicationContext.getClassFinder();
        List<Class> ClassList = cf.findImplements(HttpServletExecutor.class);
        HttpServletExecutor exe = null;
        String act = null;
        for (Class c : ClassList) {
            try {
                exe = (HttpServletExecutor) c.newInstance();
                act = exe.getAct();
                if (executors.containsKey(act) == false) {
                    executors.put(exe.getAct(), exe);
                    logger.debug("load executor act:" + act + " class:" + c.getName());
                } else {
                    logger.warn("重复定义act :" + act + " class1:" + executors.get(act).getClass().getName() + " class2:" + c.getName());
                }
            } catch (Exception ex) {
                logger.info("实例化Class失败：" + c.getName(), ex);
            }
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String act = req.getParameter("act");
        HttpServletExecutor executor = executors.get(act);
        if (executor != null) {
            try {
                executor.execute(req, resp);
                //执行完成自动完成事务
            } catch (RuntimeException re) {
                //异常时回滚
                throw re;
            }
        } else {
            resp.sendError(404);
        }
    }
}
