package com.slevenc.ccms.service.article;

import com.slevenc.ccms.entity.article.Article;
import com.slevenc.ccms.entity.article.StaticFile;
import com.slevenc.ccms.entity.article.Template;
import com.slevenc.ccms.logger.LoggerUtil;
import com.slevenc.ccms.service.system.SystemPropertiesService;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-26
 * Time: 上午8:50
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ArticleService {

    private static final String ARTICLE_DIR_PROPERTY = "ARTICLE_DIR_PROPERTY";

    private String articleDirValue = "";

    private SessionFactory sf = null;
    private FreeMarkerConfigurer conf = null;
    private SystemPropertiesService sps = null;


    public void saveArticle(Article article) {
        Session se = sf.openSession();
        if (article.getCreateDate() == null) {
            article.setCreateDate(new Date());
        }
        if (article.getTemplate() == 0) {
            article.setTemplate(1);
        }
        se.save(article);
        se.beginTransaction().commit();
        se.close();
        generateStaticFile(article);
    }

    public void updateArticle(Article article) {
        Session se = sf.openSession();
        se.update(article);
        se.beginTransaction().commit();
        se.close();
        generateStaticFile(article);
    }

    public StaticFile getArticleView(long articleId) {
        Session se = sf.openSession();
        StaticFile result = (StaticFile) se.get(StaticFile.class, articleId);
        se.close();
        return result;
    }


    public Template getTemplateById(long templateId) {
        Session se = sf.openSession();
        Template tmp = (Template) se.get(Template.class, templateId);
        se.close();
        return tmp;
    }


    public void generateStaticFile(Article article) {
        Session se = sf.openSession();
        Criteria cr = se.createCriteria(StaticFile.class);
        cr.add(Restrictions.idEq(article.getId()));
        StaticFile existsFile = (StaticFile) cr.uniqueResult();
        File parentDirFile = new File(articleDirValue);
        File writeFile = null;
        if (existsFile != null) {
        } else {
            existsFile = new StaticFile();
            existsFile.setArticleId(article.getId());
            String subDirName = String.valueOf(new Random().nextInt(500));
            File subDir = new File(parentDirFile, subDirName);
            if (subDir.exists() == false) {
                subDir.mkdirs();
            }
            existsFile.setUri(subDirName + "/" + UUID.randomUUID().toString() + ".html");
            se.save(existsFile);
            se.beginTransaction().commit();
        }
        writeFile = new File(parentDirFile, existsFile.getUri());
        OutputStreamWriter osw = null;
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("article", article);
        try {
            osw = new OutputStreamWriter(new FileOutputStream(writeFile), "utf-8");
            conf.getConfiguration().getTemplate(getTemplateById(article.getTemplate()).getPath()).process(modelMap, osw);

            LoggerUtil.i.info("save file to " + writeFile.getPath());
        } catch (Exception ex) {
            LoggerUtil.i.info("导出静态文件失败", ex);
        } finally {
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                }
            }
            se.close();
        }
    }


    public String getArticleDirValue() {
        return articleDirValue;
    }

    @Resource
    public void setSps(SystemPropertiesService sps) {
        this.sps = sps;
        try{
        articleDirValue = sps.getProperty(ARTICLE_DIR_PROPERTY);
        }catch (Exception ex){
        }
    }

    @Resource
    public void setConf(FreeMarkerConfigurer conf) {
        this.conf = conf;
    }


    @Resource
    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
}
