package com.slevenc.ccms.filter;

import com.slevenc.ccms.entity.user.UserEntity;
import com.slevenc.ccms.util.RequestUtil;
import com.slevenc.ccms.util.regex.MatcherPatterns;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-26
 * Time: 下午7:03
 * To change this template use File | Settings | File Templates.
 */
public class LoginFilter implements Filter {
    ApplicationContext applicationContext = null;

    RequestUtil ru = new RequestUtil();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        boolean normalExe = true;
        if (servletRequest instanceof HttpServletRequest) {
            ((HttpServletRequest) servletRequest).getSession().setAttribute("_rootPath",ru.getRootURL((HttpServletRequest) servletRequest));
            String requestUri = ((HttpServletRequest) servletRequest).getRequestURI();
            MatcherPatterns regexMatcher = applicationContext.getBean(MatcherPatterns.class);
            if (regexMatcher != null) {
                if (regexMatcher.isMatch(requestUri)) {
                    //检查session登录状态
                    if (((HttpServletRequest) servletRequest).getSession().getAttribute("_user") instanceof UserEntity) {
                        //已登录
                    } else {
                        //未登录
                        normalExe = false;
                        if (servletResponse instanceof HttpServletResponse) {
                            String url = ((HttpServletRequest) servletRequest).getRequestURL().toString();
                            ((HttpServletResponse) servletResponse).sendRedirect(ru.getRootURL((HttpServletRequest) servletRequest) + "user/login/?from=" + url);
                        }
                    }
                }
            }
        }
        if (normalExe) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
