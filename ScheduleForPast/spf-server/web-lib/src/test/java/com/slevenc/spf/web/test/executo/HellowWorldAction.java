package com.slevenc.spf.web.test.executo;

import com.slevenc.spf.web.json.JsonExecutor;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-27
 * Time: 下午2:18
 * To change this template use File | Settings | File Templates.
 */
public class HellowWorldAction extends JsonExecutor {
    @Override
    public Object execute(HttpServletRequest request) {
        Map<String, String> mm = new HashMap<String, String>();
        mm.put("name", "slevenc");
        return mm;
    }

    @Override
    public String getAct() {
        return "hello";
    }
}
