package com.slevenc.spf.web;

import com.slevenc.spf.scaner.ClassFinder;
import com.slevenc.spf.web.config.ScanerXmlConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

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
    private String[] packageToScan = new String[0];

    public ServletDispatcher() {
        try {
            loadPackageToScan();
        } catch (Exception ex) {
            logger.info("载入配置失败", ex);
        }
        try {
            loadExecutors();
        } catch (Exception ex) {
            logger.info("载入Executor失败", ex);
        }
    }

    private void loadPackageToScan() throws Exception {
        List<String> packageList = new LinkedList<String>();
        Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(configurationLocation);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            InputStream in = null;
            try {
                in = url.openStream();
                String[] pks = loadFromXml(in);
                if (pks != null && pks.length > 0) {
                    for (String pk : pks) {
                        packageList.add(pk);
                        logger.info("package found:" + pk);
                    }
                }
                logger.info("loadXml:" + url.toString());
            } catch (Exception ex) {
                logger.info("读取xml失败:" + url.toString() + " for " + ex.getMessage(), ex);
            } finally {
                if (in != null) {
                    in.close();
                }
            }
        }
        if (packageList.isEmpty() == false) {
            packageToScan = packageList.toArray(new String[0]);
        }
    }

    private JAXBContext jc = null;

    private String[] loadFromXml(InputStream in) throws Exception {
        if (jc == null) {
            jc = JAXBContext.newInstance(ScanerXmlConfig.class);
        }
        Unmarshaller umar = jc.createUnmarshaller();
        ScanerXmlConfig config = (ScanerXmlConfig) umar.unmarshal(in);
        String[] result = null;
        if (config != null && config.packages != null) {
            result = new String[config.packages.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = config.packages[i];
            }
        }
        return result;
    }

    private Map<String, HttpServletExecutor> executors = new HashMap<String, HttpServletExecutor>();

    private void loadExecutors() throws Exception {
        if (packageToScan != null && packageToScan.length > 0) {
            ClassFinder cf = new ClassFinder();
            cf.setPackageList(this.packageToScan);
            List<Class> ClassList = cf.findImplements(HttpServletExecutor.class);
            HttpServletExecutor exe = null;
            String act = null;
            for (Class c : ClassList) {
                try {
                    exe = (HttpServletExecutor) c.newInstance();
                    act = exe.getAct();
                    if (executors.containsKey(act) == false) {
                        executors.put(exe.getAct(), exe);
                        logger.debug("load executor act:" + act + " class:" + c.getName());
                    } else {
                        logger.warn("重复定义act :" + act + " class1:" + executors.get(act).getClass().getName() + " class2:" + c.getName());
                    }
                } catch (Exception ex) {
                    logger.info("实例化Class失败：" + c.getName(), ex);
                }
            }

        }
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String act = req.getParameter("act");
        HttpServletExecutor executor = executors.get(act);
        if (executor != null) {
            executor.execute(req, resp);
        } else {
            resp.sendError(404);
        }
    }
}
