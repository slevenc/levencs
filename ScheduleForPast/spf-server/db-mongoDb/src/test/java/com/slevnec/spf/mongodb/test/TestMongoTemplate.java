package com.slevnec.spf.mongodb.test;

import com.mongodb.Mongo;
import com.slevenc.spf.context.ApplicationContext;
import com.slevenc.spf.user.dao.UserDao;
import com.slevenc.spf.user.entity.LoginEntity;
import com.slevenc.spf.user.entity.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-27
 * Time: 下午10:21
 * To change this template use File | Settings | File Templates.
 */
public class TestMongoTemplate {

    Logger logger = LoggerFactory.getLogger(TestMongoTemplate.class);

    @Test
    public void testTestMongoTemplate() throws Exception {

        Mongo mongo = new Mongo("127.0.0.1", 27017);

        MongoTemplate mt = new MongoTemplate(mongo, "test");
        com.slevenc.spf.user.entity.LoginEntity le = new com.slevenc.spf.user.entity.LoginEntity();
        le.setUserid(UUID.randomUUID().toString());
        le.setPassword("768");
        le.setUsername("1024");
        le.setCreateTime(new Date());


        mt.save(le);


    }

    @Test
    public void testFind() throws Exception {
        Mongo mongo = new Mongo("127.0.0.1", 27017);

        MongoTemplate mt = new MongoTemplate(mongo, "test");

        List<LoginEntity> back = mt.find(new Query(Criteria.where("username").is("1024")), LoginEntity.class);

        for (LoginEntity le : back) {
               logger.info("userid:"+le.getUserid());
               logger.info("username:"+le.getUsername());
               logger.info("password:"+le.getPassword());
        }
    }


    @Test
    public void testAddUser() throws Exception {

        String userId = UUID.randomUUID().toString();

        User user = new User();
        user.setUserId(userId);
        user.setNickname("admin");
        user.setUserEmail("admin@admin.com");


        LoginEntity le = new LoginEntity();
        le.setUserid(userId);
        le.setUsername("admin");
        le.setPassword("admin");

        UserDao userDao = ApplicationContext.loadClass(UserDao.class);
        userDao.insertLoginEntity(userId,le.getUsername(),le.getPassword());
        userDao.insertUser(user);



    }
}
