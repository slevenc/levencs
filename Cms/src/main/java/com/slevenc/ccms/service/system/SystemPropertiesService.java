package com.slevenc.ccms.service.system;

import com.slevenc.ccms.entity.system.SystemPropertyEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-25
 * Time: 下午10:08
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SystemPropertiesService {

    private SessionFactory sf = null;


    public String getProperty(String name) {
        String result = null;

        Session se = sf.openSession();

        Criteria cr = se.createCriteria(SystemPropertyEntity.class);
        cr.add(Restrictions.eq("name", name));
        SystemPropertyEntity spe = (SystemPropertyEntity) cr.uniqueResult();
        if (spe != null) {
            result = spe.getValue();
        }
        se.close();
        return result;
    }

    public void updateProperty(String name, String value) {
        Session se = sf.openSession();
        Criteria cr = se.createCriteria(SystemPropertyEntity.class);
        cr.add(Restrictions.eq("name", name));
        SystemPropertyEntity spe = (SystemPropertyEntity) cr.uniqueResult();
        if (spe != null) {
            //存在则更新
            spe.setValue(value);
            se.update(spe);
        } else {
            //不存在则创建
            spe = new SystemPropertyEntity();
            spe.setName(name);
            spe.setValue(value);
            se.save(spe);
        }

        se.beginTransaction().commit();
        se.close();
    }

    public List<SystemPropertyEntity> listAllProperties() {
        Session se = sf.openSession();
        return se.createCriteria(SystemPropertyEntity.class).setMaxResults(1000).list();
    }


    @Resource
    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
}
