package com.slevenc.levencs.cms;

import com.slevenc.ccms.service.system.InitDbService;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-26
 * Time: 下午9:54
 * To change this template use File | Settings | File Templates.
 */
public class InitDb extends BaseTest {
    private static InitDbService is = null;

    @BeforeClass
    public static void loadService() {
        is = applicationContext.getBean(InitDbService.class);
    }


    @Test
    public void testInitDb() throws Exception {
        is.initDb();
    }
}
