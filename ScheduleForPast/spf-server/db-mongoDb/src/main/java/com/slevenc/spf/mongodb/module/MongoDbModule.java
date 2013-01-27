package com.slevenc.spf.mongodb.module;

import com.google.inject.AbstractModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-27
 * Time: 下午7:17
 * To change this template use File | Settings | File Templates.
 */
public class MongoDbModule extends AbstractModule {
    private static Logger logger = LoggerFactory.getLogger(MongoDbModule.class);

    @Override
    protected void configure() {
        logger.info("start load mongodb module");
    }
}
