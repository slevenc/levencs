package com.slevenc.ccms.entity.article;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-26
 * Time: 上午10:29
 * To change this template use File | Settings | File Templates.
 */
@Entity(name = "articleStaticFile")
@Table(name = "t_article_static_file")
public class StaticFile {
    @Id
    private Long articleId;

    @Column(length = 255)
    private String uri;


    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
