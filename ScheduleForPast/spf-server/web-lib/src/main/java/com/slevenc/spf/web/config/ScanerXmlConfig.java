package com.slevenc.spf.web.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.security.PrivateKey;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-27
 * Time: 上午11:58
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "ScanerConfig")
public class ScanerXmlConfig {
    @XmlElement(name = "package")
    public String[] packages;

}
