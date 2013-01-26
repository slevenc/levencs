package com.slevenc.levencs.cms.test;

import com.slevenc.ccms.entity.article.Article;
import com.slevenc.ccms.service.article.ArticleService;
import com.slevenc.levencs.cms.BaseTest;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-26
 * Time: 上午11:51
 * To change this template use File | Settings | File Templates.
 */
public class TestArticleGenerate extends BaseTest {


    @Test
    public void testNewArticle() throws Exception {
        Article a = new Article();
        a.setTitle("testArticle");
        a.setContent("我爱北京天安门");


        ArticleService as = applicationContext.getBean(ArticleService.class);

        as.saveArticle(a);


    }
}
