package com.slevenc.spf.web.test.executo;

import com.slevenc.spf.web.HttpServletExecutor;
import com.slevenc.spf.web.annotation.Act;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-27
 * Time: 下午1:06
 * To change this template use File | Settings | File Templates.
 */
public class ActOne {
    @Act(name="one")
    public void e(){

    }


}
