package com.slevenc.spf.scaner;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 12-9-26
 * Time: 下午1:31
 * To change this template use File | Settings | File Templates.
 */
//xml 文件查找器 TODO 尚未实现
public class XmlFinder {

    private final static Logger logger = LoggerFactory.getLogger(XmlFinder.class);

    public List<String> findXmlInClassPath(String path, String ends) throws Exception {
        List<String> result = new ArrayList<String>();
        URLClassLoader urlClassLoader = (URLClassLoader) Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = urlClassLoader.findResources(path);
        URL resourceURL = null;
        while (resources.hasMoreElements()) {
            resourceURL = resources.nextElement();
            if (resourceURL.getProtocol().equals("file")) {
                //classpath 为文件路径  单元测试的情况
                result.addAll(underFileSystem(resourceURL.getFile(), path, ends));


            } else if (resourceURL.getProtocol().equals("jar")) {
                //classpath为jar包内的   已经被打成包的情况
                result.addAll(underClassPath(resourceURL.getFile(), path, ends));

            }
        }
        return result;
    }
    public List<String> findXmlInPath(String path, String ends) {
        if (path.endsWith(File.separator) == false) {
            path = path + File.separator;
        }
        List<String> results = new ArrayList<String>();
        results.addAll(underFileSystem(path, path, ends));
        return results;
    }

    private List<String> underFileSystem(String path, String prefix, String ends) {
        List<String> results = new ArrayList<String>();
        //TODO 基于文件系统目录的遍历
        File f = new File(path);
        String[] subFiles = f.list();
        if (subFiles != null) {
            for (String subFile : subFiles) {

                if (subFile.toLowerCase().endsWith(ends + ".xml")) {
                    results.add(prefix + subFile);
                } else {
                    results.addAll(underFileSystem(path + "/" + subFile, prefix + subFile + "/", ends));
                }
            }
        }
        return results;
    }
    private List<String> underClassPath(String resourcePath, String classPath, String ends) throws Exception {
        List<String> results = new ArrayList<String>();
        //TODO 基于classPath的遍历
        String jarPath = resourcePath.substring(0, resourcePath.indexOf(".jar!") + 4);
        JarInputStream jis = null;
        try {
            URL jarUrl = new URL(jarPath);
            jis = new JarInputStream(jarUrl.openStream());
            JarEntry jarEntry = null;
            while ((jarEntry = jis.getNextJarEntry()) != null) {
                if (jarEntry.getName().startsWith(classPath) && jarEntry.getName().toLowerCase().endsWith(ends + ".xml")) {
                    results.add(jarEntry.getName());
                }
            }
        } catch (Exception ex) {
            logger.info("jar文件读取失败:" + resourcePath, ex);
        } finally {
            if (jis != null) {
                jis.close();
            }
        }
        return results;
    }


}
