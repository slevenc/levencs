package com.slevenc.spf.web.test.executo;

import com.slevenc.spf.web.HttpServletExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-27
 * Time: 下午1:06
 * To change this template use File | Settings | File Templates.
 */
public class ActOne implements HttpServletExecutor {
    @Override
    public String getAct() {
        return "one";
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse resp) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
