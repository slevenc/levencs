package com.slevenc.spf.web.test.executo;

import com.slevenc.spf.web.annotation.Act;
import com.slevenc.spf.web.executor.JsonExecutor;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-27
 * Time: 下午1:06
 * To change this template use File | Settings | File Templates.
 */
public class ActOneOne {

    @Act(name = "one", executor = JsonExecutor.class)
    public void te() {


    }

}
