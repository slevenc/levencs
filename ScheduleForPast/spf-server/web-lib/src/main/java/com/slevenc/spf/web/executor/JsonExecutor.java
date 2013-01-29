package com.slevenc.spf.web.executor;

import com.slevenc.spf.web.WebApplicationContext;
import com.slevenc.spf.web.exception.FlagException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-27
 * Time: 下午1:13
 * To change this template use File | Settings | File Templates.
 */
public class JsonExecutor extends DefaultExecutor {

    public static final String FLAG_SUCCESS = "SUCCESS";
    public static final String FLAG_EXCEPTION = "EXCEPTION";
    public static final String FLAG_NODATA = "NODATA";
    public static final String FLAG_FAIL = "FAIL";
    public static final String FLAG_NO_PRIVILEGE = "NO_PRIVILEGE";
    public static final String FLAG_UNLOGIN = "UNLOGIN";
    public static final Logger logger = LoggerFactory.getLogger(JsonExecutor.class);


    @Override
    public Object execute(Method m) throws IOException {
        HttpServletRequest request = WebApplicationContext.getHttpServletRequest();
        HttpServletResponse resp = WebApplicationContext.getHttpServletResponse();
        String act = WebApplicationContext.getAct();
        Map<String, Object> topMap = new LinkedHashMap<String, Object>();
        String flag = FLAG_SUCCESS;
        topMap.put("act", act);
        try {
            Object data = super.execute(m);
            if (data == null) {
                flag = FLAG_NODATA;
            } else {
                topMap.put("data", data);
            }
        } catch (InvocationTargetException invoe) {
            if (invoe.getTargetException() instanceof FlagException) {
                flag = ((FlagException) invoe.getTargetException()).getFlag();
                logger.info("异常！act=" + act, invoe.getTargetException());
            } else {
                flag = FLAG_EXCEPTION;
                logger.info("异常！act=" + act, invoe.getTargetException());
            }
        } catch (FlagException fe) {
            flag = fe.getFlag();
            logger.info("异常！act=" + act, fe);
        } catch (Exception ex) {
            flag = FLAG_EXCEPTION;
            logger.info("异常！act=" + act, ex);
        }

        topMap.put("flag", flag);
        writeResponse(topMap, resp);
        return null;
    }

    public void writeResponse(Map<String, Object> topMap, HttpServletResponse resp) throws IOException {
        String flag = "" + topMap.get("flag");
        if (flag.toUpperCase().equals(FLAG_EXCEPTION)) {
            resp.sendError(500);
        }
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        String responseString = null;
        try {
            responseString = JSONObject.fromObject(topMap).toString();
            OutputStreamWriter osw = new OutputStreamWriter(resp.getOutputStream(), "utf-8");
            osw.write(responseString);
            osw.flush();
        } catch (Exception ex) {
            logger.info("fail transform object to json string", ex);
            resp.sendError(500);
        }
    }
}
