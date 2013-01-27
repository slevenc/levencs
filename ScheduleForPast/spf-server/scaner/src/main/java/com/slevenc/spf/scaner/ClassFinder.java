package com.slevenc.spf.scaner;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class ClassFinder {

    private final static Logger logger = LoggerFactory.getLogger(ClassFinder.class);

    private String[] packageList = new String[0];

    public List<Class> findAnnotatedClasses(Class<? extends Annotation> anno) {
        List<Class> result = new LinkedList<Class>();
        for (Class clazz : allSpiderClass) {
            if (clazz.isAnnotationPresent(anno)) {
                result.add(clazz);
            }
        }
        return result;
    }

    public List<Class> findImplements(Class assignable) {
        List<Class> result = new LinkedList<Class>();
        for (Class clazz : allSpiderClass) {
            if (clazz.equals(assignable) == false && assignable.isAssignableFrom(clazz)) {
                result.add(clazz);
            }
        }
        return result;
    }

    public List<Method> findAnnotationedMethod(Class<? extends Annotation> anno) {
        List<Method> result = new LinkedList<Method>();
        for (Class clazz : allSpiderClass) {
            for (Method m : clazz.getMethods()) {
                if (m.isAnnotationPresent(anno)) {
                    result.add(m);
                }
            }
        }
        return result;
    }

    private List<Class> allSpiderClass = new ArrayList<Class>();

    public ClassFinder(String[] packageList) {
        this.packageList = packageList;
        try {
            if (packageList != null) {
                for (String p : packageList) {
                    classLoader(p);

                }
            }
        } catch (Exception ex) {
            logger.info("类列表构造失败", ex);
        }
    }

    private void classLoader(String p) throws Exception {
        Set<String> resources = loadResource(p);
        Class clazz = null;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        for (String className : resources) {
            try {
                clazz = classLoader.loadClass(className);
                allSpiderClass.add(clazz);
            } catch (Throwable th) {
                logger.info("class:" + className + ";load Fail");
            }

        }

    }

    /**
     * 加载资源
     *
     * @return
     */
    private static Set<String> loadResource(String packageName) throws Exception {
        //将包名中的.装换成/
        String dir = packageName.replace('.', '/');
        Set<String> resources = new HashSet<String>();

        URLClassLoader classLoader = (URLClassLoader) Thread.currentThread().getContextClassLoader();
        Enumeration<URL> hbms = classLoader.findResources(dir);

        URL url = null;
        while (hbms.hasMoreElements()) {
            url = hbms.nextElement();
            if (url.getProtocol().equals("file")) {
                // 本jar包内的时候拿到的是文件的绝对路径
                File f = new File(URLDecoder.decode(url.getFile(), "utf-8"));

                String[] subFiles = f.list();
                if (subFiles != null) {
                    for (String subFile : subFiles) {

                        if (subFile.toLowerCase().endsWith(".class")) {
                            resources.add(packageName.concat("." + subFile.substring(0, subFile.length() - 6)));
                        } else {
                            resources.addAll(loadResource(packageName.concat("." + subFile)));
                        }
                    }
                }
            } else if (url.getProtocol().equals("jar")) {
                // 运行中
                String jarUrlString = url.getFile();
                jarUrlString = jarUrlString.substring(0, jarUrlString.indexOf(".jar!") + 4);
                URL jarUrl = new URL(jarUrlString);
                JarInputStream jis = null;
                try {
                    jis = new JarInputStream(jarUrl.openStream());
                    JarEntry jarE = null;
                    while ((jarE = jis.getNextJarEntry()) != null) {
                        if (jarE.getName().toLowerCase().startsWith(dir)) {
                            if (jarE.getName().toLowerCase().endsWith(".class")) {
                                resources.add(jarE.getName().substring(0, jarE.getName().length() - 6).replace("/", "."));
                            }
                        }
                    }
                } catch (Exception ex) {
                } finally {
                    if (jis != null) {
                        jis.close();
                    }
                }


            }
        }

        return resources;
    }

    /**
     * 查找目录
     *
     * @param f
     * @return
     */
    private static List<File> enumDir(File f) {
        List<File> result = new ArrayList<File>();
        if (f.isDirectory()) {
            File[] subFile = f.listFiles();
            for (int i = 0; i < subFile.length; i++) {
                result.addAll(enumDir(subFile[i]));
            }
        } else {

            result.add(f);

        }
        return result;
    }


    public String[] getPackageList() {
        return packageList;
    }

}
