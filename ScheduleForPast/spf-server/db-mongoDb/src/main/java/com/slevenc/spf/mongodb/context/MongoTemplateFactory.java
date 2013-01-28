package com.slevenc.spf.mongodb.context;

import com.google.inject.Provider;
import com.mongodb.Mongo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-27
 * Time: 下午8:25
 * To change this template use File | Settings | File Templates.
 */
public class MongoTemplateFactory implements Provider<MongoTemplate> {
    private static final Logger logger = LoggerFactory.getLogger(MongoTemplateFactory.class);


    @Override
    public MongoTemplate get() {
        MongoTemplate mt = null;
        try {
            Mongo mongo = new Mongo("127.0.0.1", 27017);
            mt = new MongoTemplate(mongo, "spf");
        } catch (Exception ex) {
            logger.warn("load MongoTemplate fail",ex);
        }
        return mt;
    }
}
