package com.slevenc.ccms.service;

import com.slevenc.ccms.logger.LoggerUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-19
 * Time: 下午12:13
 */
@Service(value="initDbService")
public class InitDbService {

    private SessionFactory sf = null;
    private AnnotationSessionFactoryBean factoryBean;

    public synchronized void initDb() {
        Session se = sf.openSession();
        LoggerUtil.i.info("检查库表存在");
        se.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                DatabaseMetaData metaData = connection.getMetaData();
                ResultSet rs = metaData.getTables("", "system_properties", null, new String[]{"TABLE"});
                if (rs.next()) {
                    LoggerUtil.i.info("库表已存在:");
                } else {
                    LoggerUtil.i.info("库表不存在，初始化数据库");
                    SchemaExport se = new SchemaExport(factoryBean.getConfiguration(), connection);
                    se.create(true, true);
                    connection.commit();
                }
            }
        });
        se.close();
    }

    @Resource
    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    @Resource
    public void setFactoryBean(AnnotationSessionFactoryBean factoryBean) {
        this.factoryBean = factoryBean;
    }
}
