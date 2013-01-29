package com.slevenc.spf.web.test.executo;

import com.slevenc.spf.web.annotation.Act;
import com.slevenc.spf.web.executor.JsonExecutor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-27
 * Time: 下午2:18
 * To change this template use File | Settings | File Templates.
 */
public class HellowWorldAction {

    @Act(name = "hello", executor = JsonExecutor.class)
    public Object getMessage() {
        Map<String, String> mm = new HashMap<String, String>();
        mm.put("name", "slevenc");
        return mm;
    }
}
