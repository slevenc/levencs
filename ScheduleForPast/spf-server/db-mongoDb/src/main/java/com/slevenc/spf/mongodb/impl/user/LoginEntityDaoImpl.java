package com.slevenc.spf.mongodb.impl.user;

import com.google.inject.Inject;
import com.slevenc.spf.user.dao.LoginEntityDao;
import com.slevenc.spf.user.entity.LoginEntity;
import com.slevenc.spf.user.entity.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * User: Slevenc
 * Date: 13-1-28
 * Time: 下午9:32
 */
public class LoginEntityDaoImpl implements LoginEntityDao {

    private MongoTemplate mt = null;

    @Override
    public String getUserIdByUsernameAndPassword(String username, String password) {
        String result = "";
        List<LoginEntity> userList = mt.find(new Query().addCriteria(Criteria.where("username").is(username))
                .addCriteria(Criteria.where("password").is(password)), LoginEntity.class);
        if (userList.isEmpty() == false) {
            result = userList.get(0).getUserid();
        }
        return result;
    }

    @Override
    public User getUserByUserId(String userId) {
        User result = null;
        List<User> userList = mt.find(new Query().addCriteria(Criteria.where("userId").is(userId)).limit(1), User.class);
        if (userList.isEmpty() == false) {
            result = userList.get(0);
        }
        return result;
    }

    @Override
    public User getUserByUserName(String username) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void changeUserPassword(String userId, String Password) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Inject
    public void setMt(MongoTemplate mt) {
        this.mt = mt;
    }
}
