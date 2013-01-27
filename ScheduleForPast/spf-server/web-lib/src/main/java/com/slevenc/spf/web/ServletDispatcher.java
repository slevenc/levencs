package com.slevenc.spf.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-27
 * Time: 上午10:59
 * To change this template use File | Settings | File Templates.
 */
public class ServletDispatcher extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ServletDispatcher.class);

    private String configurationLocation = "package_scan.xml";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        super.service(req, resp);
    }
}
