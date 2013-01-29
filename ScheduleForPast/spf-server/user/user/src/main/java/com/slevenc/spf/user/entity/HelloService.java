package com.slevenc.spf.user.entity;

import com.slevenc.spf.web.annotation.Act;
import com.slevenc.spf.web.executor.JsonExecutor;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-30
 * Time: 上午12:13
 * To change this template use File | Settings | File Templates.
 */
public class HelloService {

    @Act(name = "hello", executor = JsonExecutor.class)
    public Map<String, Object> hello(HttpServletRequest req) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("req", req.getRequestURL().toString());

        return map;
    }
}
