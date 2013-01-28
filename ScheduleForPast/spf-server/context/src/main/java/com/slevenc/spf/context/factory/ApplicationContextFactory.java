package com.slevenc.spf.context.factory;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.slevenc.spf.context.config.ScanerXmlConfig;
import com.slevenc.spf.context.impl.ApplicationContextImpl;
import com.slevenc.spf.scaner.ClassFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-27
 * Time: 下午4:40
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationContextFactory {
    private static ApplicationContextFactory instance = new ApplicationContextFactory();
    private static Logger logger = LoggerFactory.getLogger(ApplicationContextFactory.class);
    private ClassFinder classFinder = null;
    public static final String configurationLocation = "package_scan.xml";
    private String[] packageToScan = new String[0];
    private Injector injector = null;



    private ApplicationContextFactory() {
        logger = LoggerFactory.getLogger(ApplicationContextFactory.class);
        try {
            loadPackageToScan();
            classFinder = new ClassFinder(packageToScan);
        } catch (Exception ex) {
            logger.info("载入配置失败", ex);
        }
    }


    private Iterable<Module> scanModules() {
        List<Class> clazz = classFinder.findImplements(AbstractModule.class);
        List<Module> result = new LinkedList<Module>();
        for (Class c : clazz) {
            try {
                result.add((AbstractModule) c.newInstance());
            } catch (Exception ex) {
                logger.info("实例化失败 class:" + c.getName(), ex);
            }
        }
        return result;
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

    public static ApplicationContextImpl getApplicationContext() {
        return getInjector().getInstance(ApplicationContextImpl.class);
    }

    public static ClassFinder getClassFinder() {
        return instance.classFinder;
    }

    public static Injector getInjector() {
        if(instance.injector==null){
            instance.injector = Guice.createInjector(instance.scanModules());
        }
        return instance.injector;
    }

}
