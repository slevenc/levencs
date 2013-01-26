package com.slevenc.ccms.listener;

import com.slevenc.ccms.service.system.InitDbService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-24
 * Time: 下午1:37
 * To change this template use File | Settings | File Templates.
 */
public class InitDbServlet extends HttpServlet {


    @Override
    public void init() throws ServletException {
        ServletContext application = this.getServletContext();
        ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        InitDbService initService = (InitDbService) ac.getBean("initDbService");
        initService.initDb();
    }
}
