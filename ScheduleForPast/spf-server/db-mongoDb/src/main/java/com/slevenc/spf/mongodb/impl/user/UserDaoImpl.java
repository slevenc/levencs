package com.slevenc.spf.mongodb.impl.user;

import com.google.inject.Inject;
import com.slevenc.spf.user.dao.UserDao;
import com.slevenc.spf.user.entity.LoginEntity;
import com.slevenc.spf.user.entity.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: Slevenc
 * Date: 13-1-28
 * Time: 下午9:32
 */
@Resource(type = UserDao.class)
public class UserDaoImpl implements UserDao {

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
        User result = null;
        String userId = "";

        List<LoginEntity> loginList = mt.find(new Query().addCriteria(Criteria.where("username").is(username))
                , LoginEntity.class);
        if (loginList.isEmpty() == false) {
            userId = loginList.get(0).getUserid();


            List<User> userList = mt.find(new Query().addCriteria(Criteria.where("userId").is(userId)).limit(1), User.class);
            if (userList.isEmpty() == false) {
                result = userList.get(0);
            }
        }

        return result;
    }

    @Override
    public String getPasswordByUserId(String userId) {
        String pas = "";
        List<LoginEntity> userList = mt.find(new Query().addCriteria(Criteria.where("userId").is(userId)).limit(1)
                , LoginEntity.class);
        if (userList.isEmpty() == false) {
            pas = userList.get(0).getPassword();
        }
        return pas;
    }


    @Override
    public void changeUserPassword(String userId, String Password) {
        mt.findAndModify(new Query(Criteria.where("userId").is(userId)), Update.update("password", Password), User.class);
    }

    @Override
    public void insertUser(User user) {
        mt.save(user);
    }

    @Override
    public void insertLoginEntity(String userId, String username, String password) {
        LoginEntity le = new LoginEntity();
        le.setUserid(userId);
        le.setUsername(username);
        le.setPassword(password);
        mt.save(le);
    }

    @Inject
    public void setMt(MongoTemplate mt) {
        this.mt = mt;
    }
}
