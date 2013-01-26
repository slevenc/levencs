package com.slevenc.levencs.cms;

import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-26
 * Time: 下午12:17
 * To change this template use File | Settings | File Templates.
 */
public class BaseTest {
    protected static   ApplicationContext  applicationContext;

    @BeforeClass
    public static void init(){
        try{
        applicationContext = new ClassPathXmlApplicationContext("cms-application.xml") ;
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

}
