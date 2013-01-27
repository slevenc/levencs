package com.slevenc.spf.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-27
 * Time: 上午11:48
 * To change this template use File | Settings | File Templates.
 */
public interface HttpServletExecutor {


    //获取处理的act
   public String getAct();

    //执行方法法
    public void execute(HttpServletRequest request, HttpServletResponse resp) throws IOException;


}
